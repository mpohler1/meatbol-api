package com.meatbol.webserver;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RestController
class WebServerController {
    private static final String TEMP_FILE_FOLDER_PATH_ENVIRONMENT_VARIABLE = "TEMP_FILE_FOLDER_PATH";

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/interpret/file", consumes = {"multipart/form-data"})
    static MeatbolOutput interpretFile(@RequestParam("file")MultipartFile multipartFile) {
        try {
            String filePath = System.getenv(TEMP_FILE_FOLDER_PATH_ENVIRONMENT_VARIABLE) + "/" + multipartFile.getName();
            multipartFile.transferTo(new File(filePath));
            String output = MeatbolRunner.runMeatbolInterpreter(filePath);
            return new MeatbolOutput(false, output);
        } catch (IOException e) {
            return new MeatbolOutput(true, e.getMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/interpret/text", consumes = {"application/json"})
    static MeatbolOutput interpretText(@RequestBody String text) {
        try {
            String tmpDirectory = System.getenv(TEMP_FILE_FOLDER_PATH_ENVIRONMENT_VARIABLE);
            File file = File.createTempFile("tmp", ".txt", new File(tmpDirectory));
            String filePath = tmpDirectory + "/" + file.getName();

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(text);
            writer.close();

            String output = MeatbolRunner.runMeatbolInterpreter(filePath);
            return new MeatbolOutput(false, output);
        } catch (IOException e) {
            return new MeatbolOutput(true, e.getMessage());
        }
    }
}
