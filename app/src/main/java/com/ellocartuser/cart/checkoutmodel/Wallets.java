package com.ellocartuser.cart.checkoutmodel;

import com.ellocartuser.apiservices.model.HomeOrders;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Wallets {


    @SerializedName("ello_wallet")
    private List<TipsModel> wallet; //option undadu inside de
    @SerializedName("promo_wallet")
    private List<TipsModel> promowallet;

    public Wallets(List<TipsModel> wallet, List<TipsModel> promowallet) {
        this.wallet = wallet;
        this.promowallet = promowallet;
    }

    public List<TipsModel> getWallet() {
        return wallet;
    }

    public void setWallet(List<TipsModel> wallet) {
        this.wallet = wallet;
    }

    public List<TipsModel> getPromowallet() {
        return promowallet;
    }

    public void setPromowallet(List<TipsModel> promowallet) {
        this.promowallet = promowallet;
    }
}
