package com.swetanksubham.utils;

import java.util.Optional;

import com.swetanksubham.service.image.GenerateRandomImage;
import com.swetanksubham.service.image.GenerateThumbnailImage;
import com.swetanksubham.service.image.WriteImageByteToFile;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class RandomImageGenerator {

    private byte[] originalImage;
    private String originalImageFilePath;

    public void getRandomGeneratedImage() {
        GenerateRandomImage genImage = new GenerateRandomImage();
        WriteImageByteToFile writeImageByteToFile = new WriteImageByteToFile();
        GenerateThumbnailImage generateThumbnailImage = new GenerateThumbnailImage();

        log.info("Generating Random Image.");
        Optional<byte[]> image = genImage.apply(1280, 720);

        image.ifPresentOrElse(data -> {
            this.setOriginalImage(data);

            log.info("Writing Image Byte to File.");
            this.originalImageFilePath = writeImageByteToFile.apply(data, "image", null);

        }, () -> log.info("Failed to Generate Image."));

        log.info("Generating Thumbnail Image.");
        Optional<byte[]> thumbImage = generateThumbnailImage.apply(this.originalImage);

        thumbImage.ifPresentOrElse(data -> {
            log.info("Writing Generated Thumbnail to File.");
            writeImageByteToFile.apply(data, "thumb", this.originalImageFilePath);
        }, () -> log.info("Failed to Generate thumbnail."));
    }
}
