package com.kohinoor.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface FileUploadStream {

	String OUTPUT = "cleanup-queue";
	String INPUT = "upload-queue";

	@Input(INPUT)
	SubscribableChannel inbound();

	@Output(OUTPUT)
	MessageChannel outbound();
}
