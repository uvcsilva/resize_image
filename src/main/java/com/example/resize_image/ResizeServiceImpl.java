package com.example.resize_image;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class ResizeServiceImpl implements ResizeService{

    private BufferedImage bufferedImage;

    public void execute(File file) throws IOException {
        log.info("Rodando batch do Resize Service");

        BufferedImage bufferedImage;
        String outputPathWithName = "../resize_image/resizes/";

        try{
            bufferedImage = ImageIO.read(file);
        }catch (IOException ioException){
            throw new IOException("Erro na leitura do arquivo.");
        }

        log.info("Leitura de arquivo realizada");

        rotateImage(bufferedImage, outputPathWithName);
        resizeImage(bufferedImage, outputPathWithName);


    }

    private void rotateImage(BufferedImage srcImg, String outputPathWithName) throws IOException {

        int width = srcImg.getWidth();
        int height = srcImg.getHeight();
        int type = srcImg.getType();

        BufferedImage destImg = new BufferedImage(width, height, type);

        Graphics2D graphics2D = destImg.createGraphics();
        graphics2D.rotate(Math.toRadians(90), width / 2, height / 2);
        graphics2D.drawImage(srcImg, null, 0, 0);

        File rotatedImageFile = new File(outputPathWithName.concat("rotated.jpg"));

        ImageIO.write(destImg,"jpg", rotatedImageFile);
        log.info("Imagem rotacionada com sucesso");
    }

    private void resizeImage(BufferedImage srcImg, String outputPathWithName) throws IOException {

        BufferedImage destImg = Scalr.resize(srcImg, 200);
        File resizedImageFile = new File(outputPathWithName.concat("resized.jpg"));
        ImageIO.write(destImg, "jpg", resizedImageFile);
        destImg.flush();
        log.info("Imagem redimensionada com sucesso");
    }

    /*@Override
    public Boolean resizeImage(File sourceFile) {

        try {
            BufferedImage bufferedImage = ImageIO.read(sourceFile);
            bufferedImage.getR
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
    }*/
}
