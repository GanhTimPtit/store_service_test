package com.ptit.edu.store.customer.models.data;

import com.ptit.edu.store.customer.models.body.RateBody;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rate")
public class Rate {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;
    private int rating;
    private String cmt;
    private String reason;
    private Date createdDate;

    public Rate(Customer customer, RateBody rateBody) {
        this.customer = customer;
        this.rating = rateBody.getRating();
        this.cmt = rateBody.getCmt();
        this.reason = rateBody.getReason();
        createdDate = new Date();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Rate() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
