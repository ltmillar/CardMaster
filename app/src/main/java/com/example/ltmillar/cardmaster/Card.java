package com.example.ltmillar.cardmaster;

import java.util.List;

/**
 * Created by Rajat Bhatia on 12/6/2017.
 */

public class Card {

    public String cardName, bankName, cardLastFourNumber, cardExpDate;
    public List categoryLst;

    public Card() {

    }

    public Card (String cardName, String bankName, String cardNumber, String cardExpDate, List categoryLst) {
        this.cardName = cardName;
        this.bankName = bankName;
        this.cardLastFourNumber = cardNumber;
        this.cardExpDate = cardExpDate;
        this.categoryLst = categoryLst;
//        this.category1 = "Gas";
//        this.category2 = "Grocery";
//        this.category3 = "eCommerce";
//        this.category1Percent = category1Percent;
//        this.category2Percent = category2Percent;
//        this.category3Percent = category3Percent;
    }
}
