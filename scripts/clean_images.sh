#!/bin/bash

echo "---------- [Starting] ----------"

echo "Clearing Generated Images"
rm -rf src/main/resources/image/generated_image/*

echo "Clearing Generated Thumbnails"
rm -rf src/main/resources/image/generated_thumb/*

echo "---------- [Completed] ----------"
