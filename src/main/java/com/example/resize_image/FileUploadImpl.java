package com.example.resize_image;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
@Slf4j
public class FileUploadImpl implements FileUpload{

    private final static String folderPath = "../resize_image/uploads";

    @Autowired
    public FileUploadImpl() {
        try{
            log.info("Buscando pasta -> " + folderPath);
            Files.exists(Path.of(folderPath));
        } catch (Exception exception){
            throw new RuntimeException("Folder not found !");
        }
    }

    public Metadata checkImageProperties(MultipartFile multipartFile){

        if(multipartFile.isEmpty()) throw new RuntimeException("Image is empty");

        Metadata metadata = new Metadata(multipartFile.getOriginalFilename(), "https://fakeurl.com.br",
                multipartFile.getContentType(), multipartFile.getSize(), "https://fakedownload.com.br");

        return metadata;
    }

}
