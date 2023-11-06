package com.cashcardspring.cashCardSpring.start;

import com.cashcardspring.cashCardSpring.mappers.CashCard;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class CashCardJsonTest {

    @Autowired
    private JacksonTester<CashCard> json;

    @Autowired
    private JacksonTester<CashCard[]> jsonList;

    private CashCard[] cashCards;

    @BeforeEach
    void setUp() {
        cashCards = Arrays.array(
                new CashCard(99L, 123.45),
                new CashCard(100L, 100.00),
                new CashCard(101L, 150.00));
    }


    @Test
    private void cashCardSerializationTest() throws Exception {
        CashCard cashcard = new CashCard(99L, 123.45);

        assertThat(json.write(cashcard)).isStrictlyEqualToJson("single.json");

        assertThat(json.write(cashcard)).hasJsonPathNumberValue("@.id");

        assertThat(json.write(cashcard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);

        assertThat(json.write(cashcard)).hasJsonPathNumberValue("@.amount");

        assertThat(json.write(cashcard)).extractingJsonPathNumberValue("@.amount")
                .isEqualTo(123.45);

        assertThat(jsonList.write(cashCards)).isStrictlyEqualToJson("list.json");

    }

}
