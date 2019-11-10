package com.kohinoor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.kohinoor.model.FileMessage;
import com.kohinoor.stream.FileSignatureStream;

@Component
public class FileSignatureListener {

	@Autowired
	FileSignatureService fileSignatureService;

	@StreamListener(FileSignatureStream.INPUT)
	@SendTo(FileSignatureStream.OUTPUT)
	public FileMessage processMessage(@Payload FileMessage fileMessage) {
		System.out.println("Received Message for Signature: " + fileMessage);

		return fileSignatureService.processMessage(fileMessage);
	}
}
