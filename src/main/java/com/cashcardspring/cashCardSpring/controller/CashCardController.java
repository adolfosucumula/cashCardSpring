package com.cashcardspring.cashCardSpring.controller;

import com.cashcardspring.cashCardSpring.mappers.CashCard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CashCardController {

    @GetMapping("/cashcard/{requestId}")
    public ResponseEntity<CashCard> findById(@PathVariable Long requestId) {
        if(requestId.equals(99L)){
            CashCard cashCard = new CashCard(12L, 23.2);
            return ResponseEntity.ok(cashCard);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
