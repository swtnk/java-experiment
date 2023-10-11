package com.swetanksubham.service.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiFunction;

import javax.imageio.ImageIO;

import lombok.NonNull;

public class GenerateRandomImage implements BiFunction<Integer, Integer, Optional<byte[]>> {

    private static Random random = new Random();

    @Override
    public Optional<byte[]> apply(
            @NonNull Integer width,
            @NonNull Integer height) {

        int quadWidth = width / 2;
        int quadHeight = height / 2;

        try {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            Color red = getRandomColor();
            Color green = getRandomColor();
            Color blue = getRandomColor();
            Color white = getRandomColor();

            fillQaud(image, 0, 0, quadWidth, quadHeight, red);
            fillQaud(image, quadWidth, 0, quadWidth, quadHeight, blue);
            fillQaud(image, 0, quadHeight, quadWidth, quadHeight, green);
            fillQaud(image, quadWidth, quadHeight, quadWidth, quadHeight, white);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);
            byte[] imageData = outputStream.toByteArray();
            outputStream.flush();
            outputStream.close();
            return Optional.of(imageData);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.of(null);
    }

    private static Color getRandomColor() {

        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        return new Color(red, green, blue);
    }

    private static void fillQaud(BufferedImage image, int x, int y, int width, int height, Color color) {
        for (int yAx = y; yAx < y + height; yAx++) {
            for (int xAx = x; xAx < x + width; xAx++) {
                image.setRGB(xAx, yAx, color.getRGB());
            }
        }
    }

}
