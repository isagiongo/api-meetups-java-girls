package com.isagiongo.meetupsjavagirls;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MeetupNotFoundException extends RuntimeException {

    public MeetupNotFoundException(String message) {
        super(message);
    }
}
