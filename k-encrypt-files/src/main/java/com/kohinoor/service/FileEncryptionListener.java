package com.kohinoor.service;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.kohinoor.model.FileMessage;
import com.kohinoor.stream.FileEncryptionStream;

@Component
public class FileEncryptionListener {

	@StreamListener(FileEncryptionStream.INPUT)
	@SendTo(FileEncryptionStream.OUTPUT)
	public FileMessage processMessage(@Payload FileMessage fileMessage) {
		fileMessage.setEncryptedTimestamp(System.currentTimeMillis());
		System.out.println("Received Message for Encryption: " + fileMessage);
		return fileMessage;
	}
}
