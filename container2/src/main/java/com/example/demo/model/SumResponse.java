package com.example.demo.model;

public class SumResponse {
    private String file;
    private int sum;

    public SumResponse(String file, int sum) {
        this.file = file;
        this.sum = sum;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}