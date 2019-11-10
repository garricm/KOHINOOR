package com.kohinoor.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.kohinoor.stream.FileSignatureStream;

@EnableBinding(FileSignatureStream.class)
public class StreamsConfig {
}
