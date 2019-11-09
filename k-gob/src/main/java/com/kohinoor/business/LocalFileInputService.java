package com.kohinoor.business;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kohinoor.model.FileMessage;
import com.kohinoor.source.FileMessageSource;

@Component
@EnableBinding(FileMessageSource.class)
public class LocalFileInputService {

	private static final Logger logger = LogManager.getLogger(LocalFileInputService.class);

	@Autowired
	FileMessageSource fileInputSource;

	@Value("#{'${local.file.input.directory.list}'.split(',')}")
	private List<String> directoryList;

	@Value("#{'${local.file.input.file.extensions}'.split(',')}")
	private String[] fileExtensions;

	@Value("${local.file.input.processed.folder.name}")
	private String processedFolderName;

	@Scheduled(fixedDelayString = "${local.file.input.scan.fixed.delay}", initialDelay = 1000)
	public void scanForFiles() {
		logger.debug("Scanning for Files.. ");

		for (String dir : directoryList) {
			logger.debug("Scanning for Files in Directory - " + dir);
			List<File> files = (List<File>) FileUtils.listFiles(new File(dir), fileExtensions, false);

			if (files != null && files.size() != 0) {
				for (File file : files) {
					logger.debug("Processing File - " + dir);
					try {
						FileMessage msg = new FileMessage();
						msg.setFileName(file.getCanonicalPath());
						Message<FileMessage> message = MessageBuilder.withPayload(msg).build();
						fileInputSource.kexchange().send(message);
						logger.debug("Message Sent - " + msg);

						FileUtils.moveFileToDirectory(file, new File(dir + File.separator + processedFolderName), true);
					} catch (IOException e) {
						logger.error(e);
					}

				}
			}
		}

	}
}