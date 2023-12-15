package gaedianz.org.Picle.exception.model;

import gaedianz.org.Picle.exception.Error;

public class UnauthorizedException extends PicleException {
    public UnauthorizedException(Error error, String message) {
        super(error, message);
    }
}