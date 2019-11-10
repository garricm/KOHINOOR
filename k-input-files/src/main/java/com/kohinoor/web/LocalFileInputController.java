package com.kohinoor.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kohinoor.model.FileMessage;
import com.kohinoor.service.LocalFileInputService;

@RestController
public class LocalFileInputController {
	private final LocalFileInputService localFileInputService;

	public LocalFileInputController(LocalFileInputService localFileInputService) {
		this.localFileInputService = localFileInputService;
	}

	@GetMapping("/greetings")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void greetings(@RequestParam("message") String message) {
		FileMessage msg = new FileMessage();
		msg.setFileName(message);
		msg.setTimestamp(System.currentTimeMillis());

		localFileInputService.processMessage(msg);
	}
}
