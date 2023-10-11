package com.swetanksubham.service.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
        try {
            ByteArrayInputStream imageByteArrayInputStream = new ByteArrayInputStream(image);
            ByteArrayOutputStream imageByteArrayOutputStream = new ByteArrayOutputStream();
            BufferedImage bufferedImage = ImageIO.read(imageByteArrayInputStream);
            Thumbnails.of(bufferedImage).size(200, 200).outputFormat("png")
                    .toOutputStream(imageByteArrayOutputStream);
            byte[] thumbnailImage = imageByteArrayOutputStream.toByteArray();
            imageByteArrayInputStream.close();
            imageByteArrayOutputStream.close();
            bufferedImage.flush();
            log.info("THUMBNAIL GENERATED");
            return Optional.of(thumbnailImage);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.ofNullable(null);
        }
    }

}
