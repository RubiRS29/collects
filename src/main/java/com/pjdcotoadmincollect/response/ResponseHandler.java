package com.pjdcotoadmincollect.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ResponseHandler {

    public static ResponseEntity<Response> successResponse(Object response) {
        Response resp =
                new Response(HttpStatus.OK, response, "SUCCESSFUL", LocalDateTime.now());

        return new ResponseEntity<>(resp, null, HttpStatus.CREATED);
    }

}
