package com.example.backen.service.impl;

import com.example.backen.entity.FilePath;
import com.example.backen.mapper.PhotoMapper;
import com.example.backen.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Value("${upload.file.path}")
	private String uploadPathStr;
	@Resource
	private PhotoMapper mapper;
	@Override
	public void upload(InputStream inputStream, String fileName) throws Exception {
		String host = InetAddress.getLocalHost().getHostAddress();
		Path uploadPath = Paths.get(uploadPathStr);
		if(!uploadPath.toFile().exists()){
			if (uploadPath.toFile().mkdirs()){
				System.out.println("mkdir success.");
			}
		}
		Files.copy(inputStream, Paths.get(uploadPathStr).resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		System.out.println("upload file , filename is "+fileName);
		System.out.println(host+"/upload/"+fileName);
		FilePath filePath = new FilePath();
		filePath.setPath(host+"/upload/"+fileName);
		mapper.UploadPhotoPath(filePath);
	}
}
