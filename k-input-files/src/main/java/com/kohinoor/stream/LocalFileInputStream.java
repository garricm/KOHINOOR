package com.kohinoor.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface LocalFileInputStream {

	String OUTPUT = "signature-queue";

	@Output(OUTPUT)
	MessageChannel outbound();
}
