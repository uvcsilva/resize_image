package com.example.resize_image;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

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
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException,
            ExecutionException, InterruptedException {

        StopWatch timeMeasure = new StopWatch();
        timeMeasure.start();

        File file = fileCheck.execute(multipartFile);
        resizeService.execute(file);

        timeMeasure.stop();
        log.info("Seconds to complete: " + timeMeasure.getTotalTimeSeconds());

        return ResponseEntity.ok(null);
    }



}
