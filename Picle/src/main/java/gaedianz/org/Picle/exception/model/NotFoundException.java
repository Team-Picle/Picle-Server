package gaedianz.org.Picle.exception.model;

import gaedianz.org.Picle.exception.Error;

public class NotFoundException extends PicleException {
    public NotFoundException(Error error, String message) {
        super(error, message);
    }
}
