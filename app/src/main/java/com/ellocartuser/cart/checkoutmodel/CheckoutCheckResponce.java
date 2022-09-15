package com.ellocartuser.cart.checkoutmodel;

import com.ellocartuser.apiservices.model.HomeOrders;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CheckoutCheckResponce {

    @SerializedName("status")
    private String status;
    @SerializedName("live_key")
    private String live_key;



    @SerializedName("seller_promo_eligible")
    private String seller_promo_eligible;

    @SerializedName("message")
    private String message;
    @SerializedName("currency")
    private String currency;
    @SerializedName("total")
    private String total;
    @SerializedName("seller_type")
    private String sellerType;
    @SerializedName("seller_id")
    private String sellerId;
    @SerializedName("cod_status")
    private String codStatus;
    @SerializedName("online_status")
    private String onlineStatus;
    @SerializedName("offers")
    private OffersModel offers;
    @SerializedName("tips")
    private TipsModel tips;
    @SerializedName("wallets")
    List<Wallets> wallets;
    @SerializedName("seller_details")
    List<SellerDetails> sellerdtls;
    @SerializedName("user_promo_elligible")
    private String user_promo_elligible;
//    @SerializedName("details")

//    private Details details; //not taken if req , need to add
    @SerializedName("charges")
    private List<HomeOrders> charges; // only p1 and p2

    public CheckoutCheckResponce(String status, String live_key, String seller_promo_eligible, String message, String currency, String total, String sellerType, String sellerId, String codStatus, String onlineStatus, OffersModel offers, TipsModel tips, List<Wallets> wallets, List<SellerDetails> sellerdtls, String user_promo_elligible, List<HomeOrders> charges) {
        this.status = status;
        this.live_key = live_key;
        this.seller_promo_eligible = seller_promo_eligible;
        this.message = message;
        this.currency = currency;
        this.total = total;
        this.sellerType = sellerType;
        this.sellerId = sellerId;
        this.codStatus = codStatus;
        this.onlineStatus = onlineStatus;
        this.offers = offers;
        this.tips = tips;
        this.wallets = wallets;
        this.sellerdtls = sellerdtls;
        this.user_promo_elligible = user_promo_elligible;
        this.charges = charges;
    }

    public String getUser_promo_elligible() {
        return user_promo_elligible;
    }

    public void setUser_promo_elligible(String user_promo_elligible) {
        this.user_promo_elligible = user_promo_elligible;
    }

    public String getLive_key() {
        return live_key;
    }

    public void setLive_key(String live_key) {
        this.live_key = live_key;
    }

    public List<SellerDetails> getSellerdtls() {
        return sellerdtls;
    }

    public void setSellerdtls(List<SellerDetails> sellerdtls) {
        this.sellerdtls = sellerdtls;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeller_promo_eligible() {
        return seller_promo_eligible;
    }

    public void setSeller_promo_eligible(String seller_promo_eligible) {
        this.seller_promo_eligible = seller_promo_eligible;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getCodStatus() {
        return codStatus;
    }

    public void setCodStatus(String codStatus) {
        this.codStatus = codStatus;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public OffersModel getOffers() {
        return offers;
    }

    public void setOffers(OffersModel offers) {
        this.offers = offers;
    }

    public TipsModel getTips() {
        return tips;
    }

    public void setTips(TipsModel tips) {
        this.tips = tips;
    }

    public List<Wallets> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallets> wallets) {
        this.wallets = wallets;
    }

    public List<HomeOrders> getCharges() {
        return charges;
    }

    public void setCharges(List<HomeOrders> charges) {
        this.charges = charges;
    }
}
