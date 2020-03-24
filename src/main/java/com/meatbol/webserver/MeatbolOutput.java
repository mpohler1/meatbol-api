package com.meatbol.webserver;

import lombok.Data;

@Data
public class MeatbolOutput {
    private boolean ioError;
    private String output;

    public MeatbolOutput(boolean ioError, String output) {
        this.ioError = ioError;
        this.output = output;
    }
}
