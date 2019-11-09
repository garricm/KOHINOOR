package com.kohinoor.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface FileMessageSource {
	
	@Output
	MessageChannel kexchange();
}
