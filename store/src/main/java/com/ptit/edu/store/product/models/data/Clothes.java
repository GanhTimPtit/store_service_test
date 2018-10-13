package com.ptit.edu.store.product.models.data;


import com.ptit.edu.store.product.models.body.ClothesBody;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "clothes")
public class Clothes {
    public static final String CREATED_DATE = "createdDate";
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    private String name;
    private int price;
    private String description;
    private Date createdDate;
    private String logoUrl;

    private int totalSave;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "categoryID")
    private Category category;

    @OneToMany(mappedBy = "clothes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RateClothes> rateClothes;

    public Clothes() {
    }

    public Clothes(ClothesBody body) {
        this.name = body.getName();
        this.price = body.getPrice();
        this.description = body.getDescription();
        this.createdDate = new Date();
        this.logoUrl = body.getLogoUrl();
        this.totalSave = 0;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }



    public Set<RateClothes> getRateClothes() {
        return rateClothes;
    }

    public void setRateClothes(Set<RateClothes> rateClothes) {
        this.rateClothes = rateClothes;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getTotalSave() {
        return totalSave;
    }
    public void addSave(){
        totalSave++;
    }
    public void subSave(){
        totalSave--;
    }
    public void setTotalSave(int totalSave) {
        this.totalSave = totalSave;
    }
}
