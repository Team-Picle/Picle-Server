package gaedianz.org.Picle.exception.model;

import lombok.Getter;
import gaedianz.org.Picle.exception.Error;

@Getter
public class ExpiredAccessTokenException extends RuntimeException {
    private final Error error;

    public ExpiredAccessTokenException(Error error, String message) {
        super(message);
        this.error = error;
    }

    public int getHttpStatus() {
        return error.getHttpStatusCode();
    }
}
