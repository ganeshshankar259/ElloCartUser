package com.ellocartuser.home.adapterandmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Products {

    @SerializedName("product_id")
    @Expose
    public String productId;
    @SerializedName("product_name")
    @Expose
    public String productName;
    @SerializedName("product_measure")
    @Expose
    public String productMeasure;
    @SerializedName("product_mrp")
    @Expose
    public String productMrp;
    @SerializedName("product_sale")
    @Expose
    public String productSale;
    @SerializedName("product_var")
    @Expose
    public String productVar;
    @SerializedName("product_type")
    @Expose
    public String productType;

    @SerializedName("product_description")
    @Expose
    public String productDescription;
    @SerializedName("product_discount")
    @Expose
    public Integer productDiscount;
    @SerializedName("product_qty")
    @Expose
    public Integer product_qty;
    @SerializedName("product_stock")
    @Expose
    public String productStock;
    @SerializedName("product_images")
    @Expose
    public String productImages;
    @SerializedName("seller_id")
    @Expose
    public String seller_id;
    @SerializedName("seller_day")
    @Expose
    public String seller_day;
    @SerializedName("product_rating")
    @Expose
    public String product_rating;

    @SerializedName("product_save")
    @Expose
    public String product_save;

    public Products(String productId, String productName, String productMeasure, String productMrp, String productSale, String productVar, String productType, String productDescription, Integer productDiscount, Integer product_qty, String productStock, String productImages, String seller_id, String seller_day, String product_rating, String product_save) {
        this.productId = productId;
        this.productName = productName;
        this.productMeasure = productMeasure;
        this.productMrp = productMrp;
        this.productSale = productSale;
        this.productVar = productVar;
        this.productType = productType;
        this.productDescription = productDescription;
        this.productDiscount = productDiscount;
        this.product_qty = product_qty;
        this.productStock = productStock;
        this.productImages = productImages;
        this.seller_id = seller_id;
        this.seller_day = seller_day;
        this.product_rating = product_rating;
        this.product_save = product_save;
    }

    public String getSeller_day() {
        return seller_day;
    }

    public void setSeller_day(String seller_day) {
        this.seller_day = seller_day;
    }

    public Integer getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(Integer product_qty) {
        this.product_qty = product_qty;
    }

    public String getProduct_rating() {
        return product_rating;
    }

    public void setProduct_rating(String product_rating) {
        this.product_rating = product_rating;
    }

    public String getProduct_save() {
        return product_save;
    }

    public void setProduct_save(String product_save) {
        this.product_save = product_save;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductMeasure() {
        return productMeasure;
    }

    public void setProductMeasure(String productMeasure) {
        this.productMeasure = productMeasure;
    }

    public String getProductMrp() {
        return productMrp;
    }

    public void setProductMrp(String productMrp) {
        this.productMrp = productMrp;
    }

    public String getProductSale() {
        return productSale;
    }

    public void setProductSale(String productSale) {
        this.productSale = productSale;
    }

    public String getProductVar() {
        return productVar;
    }

    public void setProductVar(String productVar) {
        this.productVar = productVar;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(Integer productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductStock() {
        return productStock;
    }

    public void setProductStock(String productStock) {
        this.productStock = productStock;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }

}
