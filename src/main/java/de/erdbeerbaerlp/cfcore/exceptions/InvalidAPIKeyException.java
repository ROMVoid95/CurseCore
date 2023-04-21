package de.erdbeerbaerlp.cfcore.exceptions;

public class InvalidAPIKeyException extends RuntimeException {
    private static final long serialVersionUID = 4897017566693718014L;

    public InvalidAPIKeyException() {
        super("CFCore API Key invalid or unset");
    }
}
