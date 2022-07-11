package com.example.velotochka.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public Image() {}

    public Image(MultipartFile file) throws IOException {
        String generatedFileName= UUID.randomUUID().toString() + file.getOriginalFilename();
        this.fileName = generatedFileName;
        // paste an upload path below
        String folderName = System.getProperty("user.home") + File.separator + "velotochka" + File.separator;
        File folder = new File(folderName);
        folder.mkdirs();
        if (!folder.isDirectory()) {
            throw new IOException(folderName + " is not a folder");
        }
        file.transferTo(new File(folderName + generatedFileName));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
