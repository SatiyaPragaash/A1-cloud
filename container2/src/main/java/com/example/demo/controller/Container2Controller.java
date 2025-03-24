package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.model.ErrorResponse;
import com.example.demo.model.RequestPayload;
import com.example.demo.model.SumResponse;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@RestController
@RequestMapping("/calculate")
public class Container2Controller {

    // Define the mounted directory path inside the container
    private static final String MOUNTED_DIRECTORY = "/Satiya_PV_dir/";
    @GetMapping("/test")
    public String test() {
        return "Hello world in container 2s ";
    }
    @PostMapping
    public ResponseEntity<Object> calculate(@RequestBody RequestPayload request) {
        String fileName = request.getFile();
        String product = request.getProduct();

        File file = new File(MOUNTED_DIRECTORY + fileName);


        // Load and process CSV file
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String header = br.readLine();
              br.close();
git             if (header == null || !header.trim().equalsIgnoreCase("product,amount")) {
                return ResponseEntity.ok().body(new ErrorResponse(request.getFile(),"Input file not in CSV format."));
                
            }
          

            int sum = calculateSum(file, product);
            return ResponseEntity.ok(new SumResponse(fileName, sum));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ErrorResponse(fileName, "Input file not in CSV format."));
        }
    }

    private int calculateSum(File file, String product) throws IOException {
        int sum = 0;
        boolean isCsvValid = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;


            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                if (columns.length == 2 && columns[0].equalsIgnoreCase(product)) {
                    sum += Integer.parseInt(columns[1]);
                    isCsvValid = true;
                }
            }
        }

        if (!isCsvValid) {
            throw new IOException("Input file not in CSV format.");
        }

        return sum;
    }
}
