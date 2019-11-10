package com.kohinoor.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface FileSignatureStream {

	String OUTPUT = "encryption-queue";
	String INPUT = "signature-queue";

	@Input(INPUT)
	SubscribableChannel inbound();

	@Output(OUTPUT)
	MessageChannel outbound();
}
