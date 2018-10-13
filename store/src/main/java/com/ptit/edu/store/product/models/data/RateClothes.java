package com.ptit.edu.store.product.models.data;

import com.ptit.edu.store.customer.models.data.Customer;
import com.ptit.edu.store.product.models.body.RateClothesBody;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rate_clothes")
public class RateClothes {
    public static final String RATE_DATE = "rateDate";
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clothesID")
    private Clothes clothes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;
    private Date rateDate;
    private String message;
    private int rating;

    public RateClothes() {
    }

    public RateClothes(RateClothesBody body) {
        this.rateDate = new Date();
        this.message = body.getMessage();
        this.rating = body.getRating();
    }

    public void update(RateClothesBody body){
        this.rateDate = new Date();
        this.message = body.getMessage();
        this.rating = body.getRating();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getRateDate() {
        return rateDate;
    }

    public void setRateDate(Date rateDate) {
        this.rateDate = rateDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
