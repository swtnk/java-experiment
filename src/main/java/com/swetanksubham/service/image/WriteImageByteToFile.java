package com.swetanksubham.service.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.function.TriFunction;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WriteImageByteToFile implements TriFunction<byte[], String, String, String> {

    @Override
    public String apply(final byte[] image, @NonNull final String type, String imagePath) {

        String fileName;

        if (imagePath == null) {
            fileName = UUID.randomUUID().toString();
        } else {
            Path originalImagePath = Paths.get(imagePath);
            String imageFilenameWithExt = originalImagePath.getFileName().toString();
            fileName = imageFilenameWithExt.substring(0, imageFilenameWithExt.lastIndexOf("."));
        }

        String outPutPath =
                String.format("src/main/resources/image/generated_%s/%s.png", type, fileName);
        String outPutFormat = "PNG";
        try (ByteArrayInputStream byteArrayImage = new ByteArrayInputStream(image)) {
            BufferedImage bufferedImage = ImageIO.read(byteArrayImage);
            File imageFile = new File(outPutPath);
            ImageOutputStream imageOutputStream = new FileImageOutputStream(imageFile);
            ImageIO.write(bufferedImage, outPutFormat, imageOutputStream);
            log.info(String.format("%s GENERATED: %s", type.toUpperCase(), outPutPath));
            imageOutputStream.close();
            return outPutPath;
        } catch (Exception e) {
            log.info("Failed to write image byte to file.", e);
            return StringUtils.EMPTY;
        }
    }

}
