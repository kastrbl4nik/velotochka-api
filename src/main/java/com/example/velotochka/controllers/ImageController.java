package com.example.velotochka.controllers;

import com.example.velotochka.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("images")
public class ImageController {

    @GetMapping("{name}")
    public void getImageByName(@PathVariable String name, HttpServletResponse response) throws IOException{


        String folderName = System.getProperty("user.home") + File.separator + "velotochka" + File.separator;
        String filename = folderName + name;
        File image = new File(filename);

        String mimeType = "image";
        response.setContentType(mimeType);
        response.setContentLength((int) image.length());

        FileInputStream in  = new FileInputStream(image);
        OutputStream out = response.getOutputStream();

        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        in.close();
        out.close();
    }
}
