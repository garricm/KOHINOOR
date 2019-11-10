package com.kohinoor.service;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.kohinoor.model.FileMessage;
import com.kohinoor.util.CommonUtil;

@Service
public class FileSignatureService {

	private static final Logger logger = LogManager.getLogger(FileSignatureService.class);

	public FileMessage processMessage(final FileMessage fileMessage) {
		System.out.println("In Signature Service - " + fileMessage);

		String fullFileName = fileMessage.getTempDirectory() + File.separator + fileMessage.getFileName();
		if (CommonUtil.isFileAccessible(fullFileName)) {
			processFile(fileMessage, fullFileName);
		}

		return fileMessage;
	}

	protected FileMessage processFile(FileMessage fileMessage, String fullFileName) {
		// Sign File here
		logger.info("Signing File - " + fullFileName);
		fileMessage.setSignedTimestamp(System.currentTimeMillis());
		return fileMessage;
	}

}
