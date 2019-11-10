package com.kohinoor.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.kohinoor.stream.FileEncryptionStream;

@EnableBinding(FileEncryptionStream.class)
public class StreamsConfig {
}
