package com.kohinoor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kohinoor.model.FileMessage;
import com.kohinoor.source.FileMessageSource;

@RestController
@EnableBinding(FileMessageSource.class)
public class FileInputController {

	@Autowired
	FileMessageSource fileInputSource;

	@RequestMapping("/inputFile")
	@ResponseBody
	public String inputFile(@RequestBody FileMessage fileMessage) {
		fileInputSource.kexchange().send(MessageBuilder.withPayload(fileMessage).build());

		System.out.println(fileMessage.toString());

		return "File Processed";
	}
}
