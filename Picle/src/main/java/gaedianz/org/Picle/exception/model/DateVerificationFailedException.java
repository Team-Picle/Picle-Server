package gaedianz.org.Picle.exception.model;

import gaedianz.org.Picle.exception.Error;

public class DateVerificationFailedException extends PicleException {
    public DateVerificationFailedException(Error error, String message) {
        super(error, message);
    }
}