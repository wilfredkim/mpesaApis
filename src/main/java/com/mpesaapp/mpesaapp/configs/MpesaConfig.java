package com.mpesaapp.mpesaapp.configs;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MpesaConfig implements Serializable {
    private String customerKey;
    private String customerSecret;
}
