package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    @JsonProperty("file")
    private String file;

    @JsonProperty("error")
    private String error;

  

    public ErrorResponse(String file,String error) {
        this.file = file;
        this.error = error;
        
    }

    public String getError() { return error; }

    public String geString(){
        return file;
    }



    
}