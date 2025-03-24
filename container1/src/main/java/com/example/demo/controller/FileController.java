package com.example.demo.controller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store-file")
public class FileController {

    @GetMapping("/test")
    public String test() {
        return "FileController is working";
    }

    @PostMapping
    public ResponseEntity<?> storeFile(@RequestBody Map<String, String> body) {
        String fileName = body.get("file");
        String data = body.get("data");

        Map<String, String> response = new HashMap<>();

        if (fileName == null || fileName.isEmpty()) {
            response.put("file", null);
            response.put("error", "Invalid JSON input.");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            File file = new File("/data/" + fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(data);
            writer.close();

            response.put("file", fileName);
            response.put("message", "Success.");
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            response.put("file", fileName);
            response.put("error", "Error while storing the file to the storage.");
            return ResponseEntity.status(500).body(response);
        }
    }
}
