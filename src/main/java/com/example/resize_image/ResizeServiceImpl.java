package com.example.resize_image;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class ResizeServiceImpl implements ResizeService{

    @Value("${image.folder}")
    private String imageFolder;

    @Value("${image.size}")
    private Integer imageSize;

    @Override
    public Boolean resizeImage(File sourceFile) {

        try {
            BufferedImage bufferedImage = ImageIO.read(sourceFile);
            BufferedImage outputImage = Scalr.resize(bufferedImage, imageSize);
            String newFileName = FilenameUtils.getBaseName(sourceFile.getName())
                    + "_" + imageSize.toString() + "."
                    + FilenameUtils.getExtension(sourceFile.getName());
            Path path = Paths.get(imageFolder,newFileName);
            File newImageFile = path.toFile();
            ImageIO.write(outputImage, "jpg", newImageFile);
            outputImage.flush();
            return true;
        } catch (IOException e) {
            log.error("Deu ruim");
            return false;
        }
    }
}
