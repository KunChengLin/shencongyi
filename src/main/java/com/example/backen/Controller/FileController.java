package com.example.backen.Controller;

import com.example.backen.Mapper.PhotoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Controller
public class FileController {
    @Value("${upload.file.path}")
    private String uploadPathStr;

    public FileController(){}

    @PostMapping("/upload")
    @ResponseBody
    public boolean upload(@RequestParam MultipartFile file, @RequestParam String filename)
    {
        String host;
        if(file == null || file.isEmpty() || filename == null || filename.isEmpty())
            return false;
        try(InputStream inputStream = file.getInputStream())
        {
            host = InetAddress.getLocalHost().getHostAddress();
            Path uploadPath = Paths.get(uploadPathStr);
            if(!uploadPath.toFile().exists())
                uploadPath.toFile().mkdirs();
            Files.copy(inputStream, Paths.get(uploadPathStr).resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("upload file , filename is "+filename);
            System.out.println(host+"/upload/"+filename);
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(in);
            SqlSession session = factory.openSession();
            PhotoMapper userDao = session.getMapper(PhotoMapper.class);
            userDao.UploadPhotoPath(host+"/upload/"+filename);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
