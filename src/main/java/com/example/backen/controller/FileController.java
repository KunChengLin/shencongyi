package com.example.backen.controller;

import com.example.backen.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;

@RestController
public class FileController {
    @Value("${upload.file.path}")
    private String uploadPathStr;
    @Resource
    private FileUploadService service;
    public FileController(){}

    @PostMapping("/upload")
    @ResponseBody
    public boolean upload(@RequestParam MultipartFile file, @RequestParam String filename)
    {
        if(file == null || file.isEmpty() || filename == null || filename.isEmpty())
            return false;
        try(InputStream inputStream = file.getInputStream())
        {
            service.upload(inputStream, filename);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
