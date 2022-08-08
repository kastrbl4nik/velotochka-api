package com.example.velotochka.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Embeddable
public class Image {

    private String fileName;

    public Image() {}

    public Image(MultipartFile file) throws IOException {
        String generatedFileName= UUID.randomUUID() + file.getOriginalFilename();
        this.fileName = generatedFileName;
        // paste an upload path below
        File folder = new File(FOLDER_NAME);
        folder.mkdirs();
        if (!folder.isDirectory()) {
            throw new IOException(FOLDER_NAME + " is not a folder");
        }
        file.transferTo(new File(FOLDER_NAME + generatedFileName));
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public static String FOLDER_NAME = System.getProperty("user.home") + File.separator + "velotochka" + File.separator + "images" + File.separator;

}
