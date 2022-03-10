package com.mpesaapp.mpesaapp.endpoints;

import com.mpesaapp.mpesaapp.model.C2bRequest;
import com.mpesaapp.mpesaapp.model.C2bResponse;
import com.mpesaapp.mpesaapp.model.MpesaExpressRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moneyapi")
public interface MoneyController {

    @PostMapping("/mpesaexpress")
    ResponseEntity<Object> mpesaExpress(@RequestBody MpesaExpressRequest mpesaExpressRequest);


    @PostMapping("/c2b")
    ResponseEntity<C2bResponse> c2b(@RequestBody C2bRequest c2bRequest);

}
