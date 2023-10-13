package com.swetanksubham.service.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

import javax.imageio.ImageIO;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
public class GenerateThumbnailImage implements Function<byte[], Optional<byte[]>> {

    @Override
    public Optional<byte[]> apply(@NonNull final byte[] image) {
        try (ByteArrayOutputStream imageByteArrayOutputStream = new ByteArrayOutputStream()) {
            ByteArrayInputStream imageByteArrayInputStream = new ByteArrayInputStream(image);
            BufferedImage bufferedImage = ImageIO.read(imageByteArrayInputStream);
            Thumbnails.of(bufferedImage).size(200, 200).outputFormat("png")
                    .toOutputStream(imageByteArrayOutputStream);
            byte[] thumbnailImage = imageByteArrayOutputStream.toByteArray();

            log.info("THUMBNAIL GENERATED");
            ByteArrayInputStream thumbnailByteArrayInputStream = new ByteArrayInputStream(thumbnailImage);
            BufferedImage thumbnailBufferedImage = ImageIO.read(thumbnailByteArrayInputStream);
            log.info("THUMBNAIL DIMENSION: {}x{}", thumbnailBufferedImage.getWidth(), thumbnailBufferedImage.getHeight());
            return Optional.of(thumbnailImage);
        } catch (IOException e) {
            log.error("Failed to generate thumbnail.", e);
            return Optional.ofNullable(null);
        }
    }

}
