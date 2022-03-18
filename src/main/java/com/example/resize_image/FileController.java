package com.example.resize_image;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
public class FileController {

    private FileUpload fileService;

    @Autowired
    public FileController(FileUpload fileService){
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Metadata> uploadFile(@RequestParam("file") MultipartFile file){

        return ResponseEntity.ok(fileService.checkImageProperties(file));
    }



}
