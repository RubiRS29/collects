package com.pjdcotoadmincollect.response;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class Response {

    private HttpStatus httpStatus;
    private Object response;
    private String details;
    private LocalDateTime time;

    public Response(HttpStatus httpStatus,
                    Object response,
                    String details,
                    LocalDateTime time) {
        this.httpStatus = httpStatus;
        this.response = response;
        this.details = details;
        this.time = time;
    }
}
