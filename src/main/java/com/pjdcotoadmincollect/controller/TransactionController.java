package com.pjdcotoadmincollect.controller;


import com.pjdcotoadmincollect.dto.BalanceDto;
import com.pjdcotoadmincollect.dto.ConceptDto;
import com.pjdcotoadmincollect.dto.TransactionDto;
import com.pjdcotoadmincollect.dto.TransactionRequestDto;
import com.pjdcotoadmincollect.response.Response;
import com.pjdcotoadmincollect.response.ResponseHandler;
import com.pjdcotoadmincollect.service.BalanceService;
import com.pjdcotoadmincollect.service.ConceptService;
import com.pjdcotoadmincollect.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/pjd-cotoadmin-collect/api/v1")
public class TransactionController {

    private final TransactionService service;

    private final BalanceService balanceService;

    private final ConceptService conceptService;


    public TransactionController(TransactionService service, BalanceService balanceService, ConceptService conceptService) {
        this.service = service;
        this.balanceService = balanceService;
        this.conceptService = conceptService;
    }

    @PostMapping(value = "/create",
            consumes = {"application/json" })
    public ResponseEntity<Response> createAnnouncement(@RequestBody TransactionRequestDto transactionDTO) {

        TransactionDto response = service.saveTransaction(transactionDTO);

        return ResponseHandler.successResponse(response);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> getAllCollects(@PathVariable(value = "id") Long appId) {

        List<TransactionDto> response = service.getAllTransactions(appId);

        return ResponseHandler.successResponse(response);
    }

    @GetMapping(value = "/balances/{id}",
            consumes = {"application/json" })
    public ResponseEntity<Response> getAllBalances(@PathVariable(value = "id") Long appId) {
        List<BalanceDto> response = balanceService.getAllBalances(appId);

        return ResponseHandler.successResponse(response);
    }

    @GetMapping(value = "/concepts")
    public ResponseEntity<Response> getAllConcepts() {

        List<ConceptDto> response = conceptService.getAllConcepts();

        return ResponseHandler.successResponse(response);
    }

    @GetMapping(value = "/collect/{unitId}/{appId}")
    public ResponseEntity<Response> getAllCollectsByUnitIdAndAppId(@PathVariable(value = "unitId") Long unitId,
                                                                   @PathVariable(value = "appId") Long appId) {
        List<TransactionDto> response = service.findTransactionByUnitIdAndAppId(unitId, appId);

        return ResponseHandler.successResponse(response);
    }

    @GetMapping(value = "/balance/{unitId}/{appId}")
    public ResponseEntity<Response> getBalanceByUnitIdAndAppId(@PathVariable(value = "unitId") Long unitId,
                                                                   @PathVariable(value = "appId") Long appId) {

        BalanceDto response = balanceService.getBalanceByUnitIdAndAppId(unitId, appId);

        return ResponseHandler.successResponse(response);
    }



}
