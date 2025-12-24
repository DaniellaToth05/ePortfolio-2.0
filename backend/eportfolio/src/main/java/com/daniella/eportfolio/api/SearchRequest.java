package com.daniella.eportfolio.api;

public class SearchRequest {
    private String symbol;
    private String keywords;
    private String priceRange;

    public SearchRequest() {}

    public String getSymbol() {
        return symbol == null ? "" : symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getKeywords() {
        return keywords == null ? "" : keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getPriceRange() {
        return priceRange == null ? "" : priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }
}
