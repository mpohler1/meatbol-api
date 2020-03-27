package com.meatbol.webserver;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WebServerControllerTest {

    @Test
    void web_server_controller_interpret_file_returns_meatbol_output_when_given_a_valid_file() throws IOException {
        // Convert test File to MultipartFile
        String filePath = this.getClass().getClassLoader().getResource("simpleFor.txt").getPath();
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);

        // Test
        MeatbolOutput expectedMeatbolOutput = new MeatbolOutput(
                false,
                (
                        "\t 0\n" +
                        "\t 1\n" +
                        "\t 2\n" +
                        "ONLY ONE OF THESE SHOULD PRINT\n"
                )
        );
        MeatbolOutput actualMeatbolOutput = WebServerController.interpretFile(multipartFile);
        assertEquals(expectedMeatbolOutput, actualMeatbolOutput);
    }

    @Test
    void web_server_controller_interpret_text_returns_output_when_given_valid_text() throws IOException {
        // Objects needed to get file's contents into a String
        String filePath = this.getClass().getClassLoader().getResource("simpleFor.txt").getPath();
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder meatbolTextBuilder = new StringBuilder();
        String line = null;

        // Placing file's contents into a String
        while ((line = reader.readLine()) != null) {
            meatbolTextBuilder.append(line);
            meatbolTextBuilder.append("\n");
        }
        String meatbolText = meatbolTextBuilder.toString();

        // Test
        MeatbolOutput expectedMeatbolOutput = new MeatbolOutput(
                false,
                (
                        "\t 0\n" +
                        "\t 1\n" +
                        "\t 2\n" +
                        "ONLY ONE OF THESE SHOULD PRINT\n"
                )
        );
        MeatbolOutput actualMeatbolOutput = WebServerController.interpretText(meatbolText);
        assertEquals(expectedMeatbolOutput, actualMeatbolOutput);
    }
}
