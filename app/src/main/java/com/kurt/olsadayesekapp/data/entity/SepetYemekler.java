package com.kurt.olsadayesekapp.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SepetYemekler {
    @SerializedName("sepet_yemekler")
    private List<SepetYemek> sepetYemekList;
    @SerializedName("success")
    private int success;

    public SepetYemekler() {
    }

    public SepetYemekler(List<SepetYemek> sepetYemekList, int success) {
        this.sepetYemekList = sepetYemekList;
        this.success = success;
    }

    public List<SepetYemek> getSepetYemekList() {
        return sepetYemekList;
    }

    public void setSepetYemekList(List<SepetYemek> sepetYemekList) {
        this.sepetYemekList = sepetYemekList;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
