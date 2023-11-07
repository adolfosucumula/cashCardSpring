package com.cashcardspring.cashCardSpring.layer_architecture.controller;

import com.cashcardspring.cashCardSpring.layer_architecture.repositories.CashCardRepository;
import com.cashcardspring.cashCardSpring.mappers.CashCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cashcard")
public class CashCardController {

    private CashCardRepository cardRepository;

    public CashCardController(CashCardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
    @GetMapping
    public ResponseEntity <List<CashCard>> findAll(Pageable pageable, Principal principal) {
        /*Page<CashCard> page = cardRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
                ));*/
        Page<CashCard> page = cardRepository.findByOwner(
                principal.getName(),
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
                )
        );
        return ResponseEntity.ok(page.getContent());
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<CashCard> findById(@PathVariable Long requestId, Principal principal) {

        Optional<CashCard> optionalCashCard =
                Optional.ofNullable(cardRepository.findByIdAndOwner(requestId, principal.getName()) );
        System.out.println("ROLE: "+ principal.getName());
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
