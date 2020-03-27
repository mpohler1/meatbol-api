package com.meatbol.webserver;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class WebServerControllerTest {

    @Mock
    private HttpServletRequest mockedRequest;

    @InjectMocks
    private WebServerController controller;

    @BeforeEach
    void set_up() {
        MockitoAnnotations.initMocks(this);
        ServletContext mockServletContext = new MockServletContext();
        when(mockedRequest.getServletContext()).thenReturn(mockServletContext);
    }

    @Test
    void web_server_controller_interpret_file_returns_meatbol_output_when_given_a_valid_file() throws IOException {
        String filePath = this.getClass().getClassLoader().getResource("simpleFor.txt").getPath();
        File file = new File(filePath);
        InputStream inputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);

        MeatbolOutput expectedMeatbolOutput = new MeatbolOutput(
                false,
                (
                        "\t 0 \n" +
                        "\t 1 \n" +
                        "\t 2 \n" +
                        "ONLY ONE OF THESE SHOULD PRINT \n" +
                        "EOF         EMPTY        \n"
                )
        );
        MeatbolOutput actualMeatbolOutput = controller.interpretFile(multipartFile);
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
                        "\t 0 \n" +
                        "\t 1 \n" +
                        "\t 2 \n" +
                        "ONLY ONE OF THESE SHOULD PRINT \n" +
                        "EOF         EMPTY        \n"
                )
        );
        MeatbolOutput actualMeatbolOutput = controller.interpretText(meatbolText);
        assertEquals(expectedMeatbolOutput, actualMeatbolOutput);
    }
}
