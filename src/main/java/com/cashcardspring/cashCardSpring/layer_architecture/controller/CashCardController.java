package com.cashcardspring.cashCardSpring.layer_architecture.controller;

import com.cashcardspring.cashCardSpring.layer_architecture.repositories.CashCardRepository;
import com.cashcardspring.cashCardSpring.mappers.CashCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cashcard")
public class CashCardController {

    private CashCardRepository cardRepository;

    public CashCardController(CashCardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    @GetMapping("/{requestId}")
    public ResponseEntity<CashCard> findById(@PathVariable Long requestId) {

        Optional<CashCard> optionalCashCard = cardRepository.findById(requestId);

        if(optionalCashCard.isPresent()){
            return ResponseEntity.ok(optionalCashCard.get());
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
