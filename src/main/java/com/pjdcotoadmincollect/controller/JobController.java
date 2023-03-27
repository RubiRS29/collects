package com.pjdcotoadmincollect.controller;

import com.pjdcotoadmincollect.dto.*;
import com.pjdcotoadmincollect.response.Response;
import com.pjdcotoadmincollect.response.ResponseHandler;
import com.pjdcotoadmincollect.service.CronJobService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/pjd-cotoadmin-collect/api/v1/job")
public class JobController {
    private final CronJobService jobService;

    public JobController(CronJobService jobService) {
        this.jobService = jobService;
    }


    @PostMapping(value = "/create",
            consumes = {"application/json" })
    public ResponseEntity<Response> createAnnouncement(@RequestBody JobRequestDto jobRequestDto ) {

        jobService.saveJob(jobRequestDto);

        return ResponseHandler.successResponse("job was success create");
    }

//    @GetMapping(value = "/recurrent_jobs/{id}")
//    public ResponseEntity<Response> getAllRecurrentJobs(@PathVariable(value = "id") Long appId) {
//        List<JobDto> response = jobService.getAllJobs(appId);
//
//        return ResponseHandler.successResponse(response);
//    }
//
//    @GetMapping(value = "/cron")
//    public ResponseEntity<Response> getAllBalances() {
//
//        List<CronDto> response = jobService.getAllCrons();
//
//        return ResponseHandler.successResponse(response);
//    }

}
