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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class WebserverControllerTest {

    @Mock
    private HttpServletRequest mockedRequest;

    @InjectMocks
    private WebserverController controller;

    @BeforeEach
    void set_up() {
        MockitoAnnotations.initMocks(this);
        ServletContext mockServletContext = new MockServletContext();
        when(mockedRequest.getServletContext()).thenReturn(mockServletContext);
    }

    @Test
    void webserver_controller_interpret_returns_meatbol_output_when_given_a_valid_file() throws IOException {
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
        MeatbolOutput actualMeatbolOutput = controller.interpret(multipartFile);
        assertEquals(expectedMeatbolOutput, actualMeatbolOutput);
    }
}
