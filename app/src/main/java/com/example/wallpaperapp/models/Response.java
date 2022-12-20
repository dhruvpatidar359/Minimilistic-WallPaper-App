package com.example.wallpaperapp.models;

import java.util.List;

public class Response {

    private int total, totalHits;
    private List<imageModel> hits;

    public Response(int total, int totalHits, List<imageModel> hits) {
        this.total = total;
        this.totalHits = totalHits;
        this.hits = hits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<imageModel> getHits() {
        return hits;
    }

    public void setHits(List<imageModel> hits) {
        this.hits = hits;
    }
}