package com.ghmulti.appengine.exception;

public class AlreadyVerifiedException extends BaseWebApplicationException {

    public AlreadyVerifiedException() {
        super(409, "40905", "Already verified", "The token has already been verified");
    }
}
