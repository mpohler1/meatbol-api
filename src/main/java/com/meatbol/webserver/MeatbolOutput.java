package com.meatbol.webserver;

import lombok.Data;

@Data
public class MeatbolOutput {
    private boolean ioError;
    private String text;

    public MeatbolOutput(boolean ioError, String text) {
        this.ioError = ioError;
        this.text = text;
    }
}
