package com.example.resize_image;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RestController
@Slf4j
public class FileController {

    private FileCheck fileCheck;
    private ResizeService resizeService;

    @Autowired
    public FileController(FileCheck fileCheck, ResizeService resizeService){
        this.fileCheck = fileCheck;
        this.resizeService = resizeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Metadata> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        File file = fileCheck.execute(multipartFile);
        


        /*Metadata metadata = fileCheck.checkImageProperties(multipartFile);
        log.info("[uploadFile] Metadados coletados.");

        File file = fileCheck.uploadAndSaveLocal(metadata);
        boolean result = resizeService.resizeImage(file);
        log.info("[uploadFile] Redimensionamento finalizado com sucesso");*/

        //return ResponseEntity.ok(metadata);
    }



}
