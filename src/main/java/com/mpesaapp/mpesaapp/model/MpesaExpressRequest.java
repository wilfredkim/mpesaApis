package com.mpesaapp.mpesaapp.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MpesaExpressRequest {

    private String BusinessShortCode;
    private String Password;
    private String Timestamp;
    private String TransactionType;
    private BigDecimal Amount;
    private String PartyA;
    private String PartyB;
    private String PhoneNumber;
    private String CallBackURL;
    private String AccountReference;
    private String TransactionDesc;
}
