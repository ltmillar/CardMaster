package com.example.ltmillar.cardmaster;

/**
 * Created by Rajat Bhatia on 12/6/2017.
 */

public class Card {

    public String cardName, bankName, cardNumber, cardExpDate;
    public Card() {

    }

    public Card (String cardName, String bankName, String cardNumber, String cardExpDate) {
        this.cardName = cardName;
        this.bankName = bankName;
        this.cardNumber = cardNumber;
        this.cardExpDate = cardExpDate;
    }
}
