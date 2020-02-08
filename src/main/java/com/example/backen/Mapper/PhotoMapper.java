package com.example.backen.Mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PhotoMapper {
    void UploadPhotoPath(String s);
}
