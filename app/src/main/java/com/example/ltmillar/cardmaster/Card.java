package com.example.ltmillar.cardmaster;

/**
 * Created by Rajat Bhatia on 12/6/2017.
 */

public class Card {

    public String cardName, bankName, cardLastFourNumber, cardExpDate, category1, category2, category3, category1Percent, category2Percent, category3Percent;

    public Card() {

    }

    public Card (String cardName, String bankName, String cardNumber, String cardExpDate, String category1Percent, String category2Percent, String category3Percent) {
        this.cardName = cardName;
        this.bankName = bankName;
        this.cardLastFourNumber = cardNumber;
        this.cardExpDate = cardExpDate;
        this.category1 = "Gas";
        this.category2 = "Grocery";
        this.category3 = "eCommerce";
        this.category1Percent = category1Percent;
        this.category2Percent = category2Percent;
        this.category3Percent = category3Percent;
    }
}
