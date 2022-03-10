package com.mpesaapp.mpesaapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class C2bRequest {
    private String ShortCode;
    private String ResponseType;
    private String ConfirmationURL;
    private String ValidationURL;

}
