package com.meatbol.webserver;

import lombok.Data;

@Data
class MeatbolOutput {
    private boolean error;
    private String text;

    MeatbolOutput(boolean error, String text) {
        this.error = error;
        this.text = text;
    }
}
