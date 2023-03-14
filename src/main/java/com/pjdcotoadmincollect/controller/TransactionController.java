package com.pjdcotoadmincollect.controller;


import com.pjdcotoadmincollect.dto.TransactionDto;
import com.pjdcotoadmincollect.response.Response;
import com.pjdcotoadmincollect.response.ResponseHandler;
import com.pjdcotoadmincollect.service.TransactionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/pjd-cotoadmin-collect/api/v1")
public class TransactionController {

    private final TransactionService service;


    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping(value = "/create",
            consumes = {"application/json" })
    public ResponseEntity<Response> createAnnouncement(@RequestBody TransactionDto announcementDTO) {

        TransactionDto response = service.saveTransaction(announcementDTO);

        return ResponseHandler.successResponse(response);
    }

}
