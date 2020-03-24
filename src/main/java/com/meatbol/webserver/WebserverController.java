package com.meatbol.webserver;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
class WebserverController {

    @PostMapping(value = "/interpret", consumes = {"multipart/form-data"})
    MeatbolOutput interpret(@RequestParam("file")MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String output = MeatbolRunner.runMeatbolInterpreter(inputStream);
            return new MeatbolOutput(false, output);
        } catch (IOException e) {
            MeatbolOutput meatbolOutput = new MeatbolOutput();
            return new MeatbolOutput(true, e.getMessage());
        }
    }
}
