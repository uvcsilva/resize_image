package com.example.resize_image;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
        rotateImageH(bufferedImage, outputPathWithName);
        resizeCustomImage(bufferedImage, outputPathWithName);
        cropImage(bufferedImage, outputPathWithName);
        
        //getImagePixels(bufferedImage);


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

    private void rotateImageH(BufferedImage srcImg, String outputPathWithName) throws IOException{

        AffineTransform transf = AffineTransform.getScaleInstance(-1, 1);
        transf.translate(-srcImg.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(transf,
                AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage destImg = op.filter(srcImg, null);

        File rotatedHImageFile = new File(outputPathWithName.concat("rotatedH.jpg"));
        ImageIO.write(destImg,"jpg", rotatedHImageFile);
        log.info("Imagem rotacionada com sucesso");
    }

    private void resizeImage(BufferedImage srcImg, String outputPathWithName) throws IOException {

        BufferedImage destImg = Scalr.resize(srcImg, 200);
        File resizedImageFile = new File(outputPathWithName.concat("resized.jpg"));
        ImageIO.write(destImg, "jpg", resizedImageFile);
        destImg.flush();
        log.info("Imagem redimensionada com sucesso");
    }

    private void resizeCustomImage(BufferedImage srcImg, String outputPathWithName) throws IOException{

        int width = 1920;
        int height = 1080;

        BufferedImage destImg = Scalr.resize(srcImg, Scalr.Method.BALANCED, width, height);
        File resizedImageFile = new File(outputPathWithName.concat("resizedCustom.jpg"));
        ImageIO.write(destImg, "jpg", resizedImageFile);
        destImg.flush();
        log.info("Imagem custom redimensionada com sucesso");
    }

    private void cropImage(BufferedImage srcImg, String outputPathWithName) throws IOException {

        BufferedImage croppedImage = srcImg.getSubimage(300, 200, 200, 100);
        File cropImageFile = new File(outputPathWithName.concat("cropedImage.jpg"));
        ImageIO.write(croppedImage, "jpg", cropImageFile);
        croppedImage.flush();
        log.info("Imagem cortada com sucesso");

    }

    private void getImagePixels(BufferedImage srcImg){

        int[][] pixels = getImageToPixels(srcImg);
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                System.out.print(pixels[i][j] + " ");
            }
            System.out.println();
        }
        log.info(""+ pixels);
    }

    private static int[][] getImageToPixels(BufferedImage bufferedImage) {
        if (bufferedImage == null) {
            throw new IllegalArgumentException();
        }
        int h = bufferedImage.getHeight();
        int w = bufferedImage.getWidth();
        int[][] pixels = new int[h][w];
        for (int i = 0; i < h; i++) {
            /**
             * get pixels from image
             */
            bufferedImage.getRGB(0, i, w, 1, pixels[i], 0, w);
        }
        return pixels;
    }

    // Juntar duas imagens em uma
    /*private void addImageInAnother(BufferedImage srcImg1, BufferedImage srcImg2, String outputPathWithName){

        Graphics2D g2d = srcImg1.createGraphics();
        g2d.setComposite(
                AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g2d.drawImage(srcImg2, 1, 1, null);
        g2d.dispose();


    }*/

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
