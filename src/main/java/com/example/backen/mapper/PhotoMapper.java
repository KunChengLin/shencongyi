package com.example.backen.mapper;


import com.example.backen.entity.FilePath;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PhotoMapper {
    void UploadPhotoPath(FilePath filePath);
}
