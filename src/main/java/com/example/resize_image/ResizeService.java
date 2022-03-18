package com.example.resize_image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface ResizeService {

    void execute(File file) throws IOException, ExecutionException, InterruptedException;
}
