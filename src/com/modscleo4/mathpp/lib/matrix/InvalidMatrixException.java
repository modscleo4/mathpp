package com.modscleo4.mathpp.lib.matrix;

public class InvalidMatrixException extends RuntimeException {
    public InvalidMatrixException() { super(); }
    public InvalidMatrixException(String message) { super(message); }
    public InvalidMatrixException(String message, Throwable cause) { super(message, cause); }
    public InvalidMatrixException(Throwable cause) { super(cause); }
}
