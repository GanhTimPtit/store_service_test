package com.ptit.edu.store.admin.models;

import com.ptit.edu.store.admin.models.body.StoreBranchBody;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "store_branch")
public class StoreBranch {
    public static final String CREATED_DATE = "createdDate";
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    private String branch_name;
    private double lat;
    private double lng;
    private String logoUrl;
    private String address;
    private Date createdDate;

    public StoreBranch() {
    }

    public StoreBranch(StoreBranchBody storeBranchBody) {
        setAddress(storeBranchBody.getAddress());
        setBranch_name(storeBranchBody.getBranch_name());
        setLat(storeBranchBody.getLat());
        setLng(storeBranchBody.getLng());
        setLogoUrl(storeBranchBody.getLogoUrl());
        createdDate = new Date();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
