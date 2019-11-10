package com.kohinoor.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.kohinoor.model.FileMessage;
import com.kohinoor.stream.LocalFileInputStream;
import com.kohinoor.util.CommonUtil;

@Service
public class LocalFileInputService {

	private static final Logger logger = LogManager.getLogger(LocalFileInputService.class);

	private final LocalFileInputStream localFileInputStream;

	public LocalFileInputService(LocalFileInputStream localFileInputStream) {
		this.localFileInputStream = localFileInputStream;
	}

	@Value("#{'${local.file.input.directoryList}'.split(',')}")
	private List<String> directoryList;

	@Value("#{'${local.file.input.fileExtensions}'.split(',')}")
	private String[] fileExtensions;

	@Value("${local.file.input.processedFolderName}")
	private String processedFolderName;

	@Value("${local.file.input.duplicateFolderName}")
	private String duplicateFolderName;

	@Value("${local.file.input.tempFolderName}")
	private String tempFolderName;

	@Value("${local.file.input.direction}")
	private String fileDirection;

	@Scheduled(fixedDelayString = "${local.file.input.scanFixedDelay}", initialDelay = 1000)
	public void scanForFiles() {
		logger.debug("Scanning for Files.. ");

		for (String dir : directoryList) {
			logger.debug("Scanning for Files in Directory - " + dir);
			List<File> files = (List<File>) FileUtils.listFiles(new File(dir), fileExtensions, false);

			if (files != null && files.size() != 0) {
				for (File file : files) {
					logger.debug("Processing File - " + dir);

					try {
						if (CommonUtil.isFileAccessible(file.getCanonicalPath())) {
							processFile(dir, file);
						}
					} catch (IOException e) {
						logger.error(e);
					}
				}
			}
		}
	}

	protected void processFile(String dir, File file) {
		FileMessage msg = null;

		try {
			File processedDir = new File(dir + File.separator + processedFolderName);

			// Check if Duplicate
			File f = new File(processedDir + File.separator + file.getName());
			if (f.exists()) {
				try {
					// Move file to Duplicate Directory
					FileUtils.moveFile(file, new File(dir + File.separator + duplicateFolderName + File.separator
							+ file.getName() + "_" + System.currentTimeMillis()));
				} catch (IOException e) {
					logger.error(e);
				}

				return;
			}

			String tempDirName = dir + File.separator + tempFolderName;
			File tempDir = new File(tempDirName);
			msg = new FileMessage();
			msg.setFileName(file.getName());
			msg.setInputDirectory(dir);
			msg.setTempDirectory(tempDirName);
			msg.setTimestamp(System.currentTimeMillis());
			msg.setFileDirection(fileDirection);

			FileUtils.forceMkdir(tempDir);
			FileUtils.copyFileToDirectory(file, tempDir);
			FileUtils.moveFileToDirectory(file, processedDir, true);
		} catch (Exception e) {
			logger.error(e);
		}

		processMessage(msg);
		logger.debug("Message Sent - " + msg);
	}

	public void processMessage(final FileMessage fileMessage) {
		System.out.println("Local File Input - " + fileMessage);

		MessageChannel messageChannel = localFileInputStream.outbound();
		messageChannel.send(MessageBuilder.withPayload(fileMessage)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
	}

}
