package exception;

public class InvalidPlayerSizeException extends RuntimeException{
    public InvalidPlayerSizeException() {
        super();
    }

    public InvalidPlayerSizeException(String message) {
        super(message);
    }

    public InvalidPlayerSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPlayerSizeException(Throwable cause) {
        super(cause);
    }

    protected InvalidPlayerSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
