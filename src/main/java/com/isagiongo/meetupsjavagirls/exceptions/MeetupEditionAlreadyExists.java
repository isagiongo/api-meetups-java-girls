package com.isagiongo.meetupsjavagirls.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class MeetupEditionAlreadyExists extends RuntimeException {

    public MeetupEditionAlreadyExists(String message) {
        super(message);
    }
}
