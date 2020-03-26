package com.meatbol.webserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
class WebServerController {
    private static final String TEMP_FILE_FOLDER_PATH_ENVIRONMENT_VARIABLE = "TEMP_FILE_FOLDER_PATH";

    @Autowired
    private HttpServletRequest request;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/interpret", consumes = {"multipart/form-data"})
    MeatbolOutput interpret(@RequestParam("file")MultipartFile multipartFile) {
        try {
            String filePath = System.getenv(TEMP_FILE_FOLDER_PATH_ENVIRONMENT_VARIABLE) + "/" + multipartFile.getName();
            multipartFile.transferTo(new File(filePath));
            String output = MeatbolRunner.runMeatbolInterpreter(filePath);
            return new MeatbolOutput(false, output);
        } catch (IOException e) {
            return new MeatbolOutput(true, e.getMessage());
        }
    }
}
