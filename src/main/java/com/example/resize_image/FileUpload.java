package com.example.resize_image;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

public interface FileUpload {


    Metadata checkImageProperties(MultipartFile multipartFile);
}
