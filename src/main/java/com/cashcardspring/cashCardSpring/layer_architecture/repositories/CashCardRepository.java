package com.cashcardspring.cashCardSpring.layer_architecture.repositories;

import com.cashcardspring.cashCardSpring.mappers.CashCard;
import org.springframework.data.repository.CrudRepository;

public interface CashCardRepository extends CrudRepository  <CashCard, Long>{
}
