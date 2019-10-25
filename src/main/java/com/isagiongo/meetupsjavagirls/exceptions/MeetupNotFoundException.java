package com.isagiongo.meetupsjavagirls.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class MeetupNotFoundException extends RuntimeException {

    public MeetupNotFoundException(String message) {
        super(message);
    }
}
