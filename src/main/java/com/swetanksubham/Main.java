package com.swetanksubham;

import com.swetanksubham.utils.RandomImageGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("\n\n----------- [Starting Program] -----------\n");
        RandomImageGenerator image = new RandomImageGenerator();
        image.getRandomGeneratedImage();
        log.info("\n\n--------------- [Completed] ---------------\n");
    }
}
