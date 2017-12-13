package com.example.ltmillar.cardmaster;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rajat Bhatia on 12/6/2017.
 */

public class Card implements Parcelable {

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

    protected Card(Parcel in) {
        cardName = in.readString();
        bankName = in.readString();
        cardLastFourNumber = in.readString();
        cardExpDate = in.readString();
        category1Percent = in.readString();
        category2Percent = in.readString();
        category3Percent = in.readString();
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    public String getCardName(){
        return cardName;
    }
    public String getBankName() {return bankName;}
    public String getCardLastFourNumber() {return cardLastFourNumber;}
    public String getCardExpDate() {return cardExpDate;}
    public String getCategory1Percent(){return category1Percent;}
    public String getCategory2Percent() {return category2Percent;}
    public String getCategory3Percent() {return category3Percent;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cardName);
        parcel.writeString(bankName);
        parcel.writeString(cardLastFourNumber);
        parcel.writeString(cardExpDate);
        parcel.writeString(category1Percent);
        parcel.writeString(category2Percent);
        parcel.writeString(category3Percent);
    }

}
