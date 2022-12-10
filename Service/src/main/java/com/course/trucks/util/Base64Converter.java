package com.course.trucks.util;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class Base64Converter {

    private static final Logger LOGGER = Logger.getLogger(Base64Converter.class);
    private static final String STORAGE_URL = "C:\\Projects\\Trucks\\Web\\src\\main\\resources\\img\\";
    private static final String TXT = ".txt";
    private static final String JPG = ".jpg";

    public String saveImage(String base64Str, String login) {
        try {
            createFileWithBase64Txt(base64Str, login);
            String base64 = readBase64File(login);
            createImageFromBytes(base64, login);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return STORAGE_URL + login + JPG;
    }

    public String getBase64FromImagePath(String imagePath) {
        byte[] bytes = new byte[0];
        try {
            File file = new File(imagePath);
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                bytes = fileInputStream.readAllBytes();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    private void createFileWithBase64Txt(String base64Str, String login) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(STORAGE_URL + login + TXT)) {
            fileOutputStream.write(base64Str.getBytes(StandardCharsets.UTF_8));
        }
    }

    private String readBase64File(String login) throws IOException {
        String base64;
        try (FileInputStream fileInputStream = new FileInputStream(STORAGE_URL + login + TXT)) {
            base64 = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        }
        return base64;
    }

    private void createImageFromBytes(String base64, String login) throws IOException {
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64);
        try (FileOutputStream fileOutputStream = new FileOutputStream(STORAGE_URL + login + JPG)) {
            fileOutputStream.write(imageBytes);
        }
    }

}
