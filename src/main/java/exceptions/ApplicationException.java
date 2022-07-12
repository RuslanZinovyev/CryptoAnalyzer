package exceptions;

import enumerators.ErrorCode;

public class ApplicationException extends RuntimeException {
    private ErrorCode errorCode;

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApplicationException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
