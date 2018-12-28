package com.modscleo4.mathpp.lib.matrix;

public class MatrixException extends RuntimeException {
    public MatrixException() {
        super();
    }

    public MatrixException(String message) {
        super(message);
    }

    public MatrixException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatrixException(Throwable cause) {
        super(cause);
    }
}
