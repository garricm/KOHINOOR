package com.kohinoor.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.kohinoor.stream.FileUploadStream;

@EnableBinding(FileUploadStream.class)
public class StreamsConfig {
}
