package com.daniella.eportfolio.api;

public class UpdatePriceRequest {
    private String symbol;
    private double price;

    public UpdatePriceRequest() {}

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
