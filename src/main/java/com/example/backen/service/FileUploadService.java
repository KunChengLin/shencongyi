package com.example.backen.service;

import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public interface FileUploadService {
	void upload(InputStream inputStream, String fileName) throws Exception;
}
