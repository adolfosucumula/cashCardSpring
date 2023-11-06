package com.cashcardspring.cashCardSpring.layer_architecture.controller;

import com.cashcardspring.cashCardSpring.layer_architecture.repositories.CashCardRepository;
import com.cashcardspring.cashCardSpring.mappers.CashCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cashcard")
public class CashCardController {

    @Autowired
    private CashCardRepository cardRepository;

    @GetMapping("/{requestId}")
    public ResponseEntity<CashCard> findById(@PathVariable Long requestId) {
        if(requestId.equals(99L)){
            CashCard cashCard = new CashCard(12L, 23.2);
            return ResponseEntity.ok(cashCard);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createEntity(@RequestBody CashCard newCashCard, UriComponentsBuilder ucb) {
        CashCard savedCashCard = cardRepository.save(newCashCard);
        URI locationOfNewCashCard = ucb.path("/cashcard/{id}")
                .buildAndExpand(savedCashCard.id()).toUri();

        return ResponseEntity.created(locationOfNewCashCard).build();
    }
}
