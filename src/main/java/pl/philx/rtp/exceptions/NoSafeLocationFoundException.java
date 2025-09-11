package pl.philx.rtp.exceptions;

public class NoSafeLocationFoundException extends Exception {
    public NoSafeLocationFoundException() {
        super("No safe location can be found");
    }
}
