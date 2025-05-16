package com.bookmyshow.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment extends BaseModel{
    private Provider provider;
    private String referenceNumber;
    private PaymentStatus paymentStatus;
    private double amount;
}
