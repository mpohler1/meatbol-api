package com.meatbol.webserver;

import lombok.Data;

@Data
class MeatbolOutput {
    private boolean ioError;
    private String text;

    MeatbolOutput(boolean ioError, String text) {
        this.ioError = ioError;
        this.text = text;
    }
}
