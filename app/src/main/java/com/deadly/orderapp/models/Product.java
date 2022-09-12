package com.deadly.orderapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class Product {

    // Core information
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("uuid")
    @Expose
    public String uuid;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("brand")
    @Expose
    public String brand;
    @SerializedName("price")
    @Expose
    public double price;

    // Unique product information
    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("image_path")
    @Expose
    public String imagePath;
    @SerializedName("original_link")
    @Expose
    public String originalLink;

    // Tracking and analytics information
    @SerializedName("nutri_score")
    @Expose
    public String nutriScore;
    @SerializedName("quantity")
    @Expose
    public String quantity;
    @SerializedName("allergens")
    @Expose
    public List<String> allergens;
    @SerializedName("ingredients")
    @Expose
    public List<String> ingredients;

    // Product nutrition information
    @SerializedName("energy")
    @Expose
    public double energy;
    @SerializedName("fat")
    @Expose
    public double fat;
    @SerializedName("saturated_fat")
    @Expose
    public double saturatedFat;
    @SerializedName("unsaturated_fat")
    @Expose
    public double unsaturatedFat;
    @SerializedName("carbohydrates")
    @Expose
    public double carbohydrates;
    @SerializedName("sugars")
    @Expose
    public double sugars;
    @SerializedName("fibers")
    @Expose
    public double fibers;
    @SerializedName("proteins")
    @Expose
    public double proteins;
    @SerializedName("salt")
    @Expose
    public double salt;
    @SerializedName("extra")
    @Expose
    public List<HashMap<String, Double>> extra;
}
