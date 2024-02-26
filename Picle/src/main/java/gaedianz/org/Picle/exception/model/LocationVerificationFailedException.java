package gaedianz.org.Picle.exception.model;

import gaedianz.org.Picle.exception.Error;

public class LocationVerificationFailedException extends PicleException{
    public LocationVerificationFailedException(Error error, String message) {
        super(error, message);
    }
}
