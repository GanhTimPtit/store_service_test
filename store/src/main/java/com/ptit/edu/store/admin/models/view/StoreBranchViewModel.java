package com.ptit.edu.store.admin.models.view;

import com.ptit.edu.store.admin.models.StoreBranch;

public class StoreBranchViewModel {
    private String id;
    private String branch_name;
    private double lat;
    private double lng;
    private String logoUrl;
    private String address;
    private double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
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

    public StoreBranchViewModel() {

    }

    public StoreBranchViewModel(StoreBranch storeBranch) {
        this.id = storeBranch.getId();
        this.branch_name = storeBranch.getBranch_name();
        this.lat = storeBranch.getLat();
        this.lng = storeBranch.getLng();
        this.logoUrl = storeBranch.getLogoUrl();
        this.address = storeBranch.getAddress();
    }
}
