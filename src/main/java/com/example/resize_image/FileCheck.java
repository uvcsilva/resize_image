package com.example.resize_image;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class FileCheck {

    private final static String folderPath = "../resize_image/uploads";

    public File execute(MultipartFile multipartFile) throws IOException {

        if(Files.notExists(Path.of(folderPath))) throw new NotDirectoryException("Diretorio não encontrado");
        if(multipartFile.isEmpty()) throw new FileNotFoundException("Arquivo nulo recebido");

        Metadata metadata = checkImageProperties(multipartFile);
        log.info("Metadados criado");
        File file = uploadAndSaveLocal(metadata);
        log.info("Arquivo salvo no disco local");

        return file;
    }

    private Metadata checkImageProperties(MultipartFile multipartFile) throws IOException {

        try{
            Metadata metadata = new Metadata(multipartFile.getOriginalFilename(), "https://fakeurl.com.br",
                    multipartFile.getContentType(), multipartFile.getSize(), "https://fakedownload.com.br",
                    multipartFile.getBytes());
            return metadata;

        }catch (IOException ioException){
            throw new IOException("Arquivo vazio recebido 2");
        }
    }


    private File uploadAndSaveLocal(Metadata metadata) throws IOException {

        try{
            Path path = Paths.get(folderPath, metadata.getName());
            Files.write(path, metadata.getBytes());
            return path.toFile();
        }catch (IOException ioException){
            throw new NotDirectoryException("Arquivo vazio recebido 3");
        }
    }

}
