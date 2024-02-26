package gaedianz.org.Picle.exception.model;

import gaedianz.org.Picle.exception.Error;

public class ImageVerificationFailedException extends PicleException{
    public ImageVerificationFailedException(Error error, String message) {
        super(error, message);
    }
}
