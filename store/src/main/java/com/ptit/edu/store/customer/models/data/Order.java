package com.ptit.edu.store.customer.models.data;

import com.ptit.edu.store.customer.models.body.OrderBody;
import com.ptit.edu.store.customer.models.body.SetOrderBody;
import com.ptit.edu.store.product.models.data.Clothes;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer_order")
public class Order {
    public static final String CREATED_DATE = "creatDate";
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    @OneToOne
    @JoinColumn(name = "clothesID")
    private Clothes clothes;
    @OneToOne
    @JoinColumn(name = "customerID")
    private Customer customer;
    private String color;
    private String size;
    private int amount;
    private int price;
    private Date creatDate;

    public Order() {
    }

    public Order(Clothes clothes, Customer customer, OrderBody orderBody) {
        setColor(orderBody.getColor());
        setAmount(orderBody.getAmount());
        setClothes(clothes);
        setCustomer(customer);
        setSize(orderBody.getSize());
        setCreatDate(new Date());
        setPrice(orderBody.getPrice());
    }
    public Order(Clothes clothes,Customer customer, SetOrderBody orderBody) {
        setColor(orderBody.getColor());
        setClothes(clothes);
        setAmount(orderBody.getAmount());
        setCustomer(customer);
        setSize(orderBody.getSize());
        setCreatDate(new Date());
        setPrice(orderBody.getPrice());
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }
}
