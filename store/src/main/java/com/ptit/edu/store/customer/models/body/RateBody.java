package com.ptit.edu.store.customer.models.body;

public class RateBody {
    private int rating;
    private String cmt;
    private String reason;

    public RateBody() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public RateBody(int rating, String cmt, String reason) {
        this.rating = rating;
        this.cmt = cmt;
        this.reason = reason;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }
}
