package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestPayload {
    @JsonProperty("file")
    private String file;

    @JsonProperty("product")
    private String product;

    public String getFile() { return file; }
    public void setFile(String file) { this.file = file; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }
}
