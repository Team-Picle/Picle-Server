package gaedianz.org.Picle.exception.model;

import gaedianz.org.Picle.exception.Error;

public class ConflictException extends PicleException {
    public ConflictException(Error error, String message) {
        super(error, message);
    }
}