package com.kohinoor.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

import com.kohinoor.stream.LocalFileInputStream;

@EnableBinding(LocalFileInputStream.class)
public class StreamsConfig {
}
