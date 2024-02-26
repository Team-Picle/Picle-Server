package gaedianz.org.Picle.exception.model;

import gaedianz.org.Picle.exception.Error;

public class BadRequestException extends PicleException {
    public BadRequestException(Error error, String message) {
        super(error, message);
    }
}
