package com.ptit.edu.store.product.models.data;

import com.ptit.edu.store.customer.models.data.Customer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "save_clothes")
public class SaveClothes {
    public final static String SAVED_DATE = "saveDate";
    public final static String CLOTHES = "CLOTHES";
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
    private Date saveDate;

    public SaveClothes() {
    }

    public SaveClothes(Clothes clothes, Customer customer) {
        this.clothes = clothes;
        this.customer = customer;
        this.saveDate= new Date();
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

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }
}
