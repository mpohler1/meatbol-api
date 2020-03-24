package com.meatbol.webserver;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class MeatbolRunner {

    static String runMeatbolInterpreter(String filepath) throws IOException {
        String pathToJAR = System.getenv("PATH_TO_INTERPRETER_JAR");

        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", pathToJAR, filepath);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
}
