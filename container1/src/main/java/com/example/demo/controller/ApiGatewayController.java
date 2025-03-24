package com.example.demo.controller;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.ErrorResponse;
import com.example.demo.model.RequestPayload;

@RestController
@RequestMapping("/calculate")
public class ApiGatewayController {

    @Value("${container2Url}")
    private String container2Url;

    @GetMapping("/test")
    public String test() {
        return "Hello world ";
    }

    @PostMapping
    public ResponseEntity<?> calculate(@RequestBody RequestPayload request) {
        // Check if the file is provided in the request
        if (request.getFile() == null || request.getFile().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(null, "Invalid JSON input."));
        }

        File file = new File("/Satiya_PV_dir/" + request.getFile());

        // Check if the file exists
        if (!file.exists()) {
            return ResponseEntity.badRequest().body(new ErrorResponse(request.getFile(), "File not found."));
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<?> response = restTemplate.postForEntity(container2Url + "/calculate", request, Object.class);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTraceString = sw.toString();
            System.out.println(stackTraceString);


            return ResponseEntity.status(500).body(new ErrorResponse(request.getFile(), stackTraceString));
        }
    }
}
