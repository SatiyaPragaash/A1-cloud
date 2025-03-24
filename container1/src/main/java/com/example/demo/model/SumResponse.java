package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SumResponse {
    @JsonProperty("file")
    private String file;

    @JsonProperty("sum")
    private Integer sum;

    public SumResponse(String file, Integer sum) {
        this.file = file;
        this.sum = sum;
    }

    public String getFile() { return file; }
    public Integer getSum() { return sum; }
}
