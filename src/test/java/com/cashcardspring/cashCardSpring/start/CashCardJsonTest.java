package com.cashcardspring.cashCardSpring.start;

import com.cashcardspring.cashCardSpring.mappers.CashCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class CashCardJsonTest {

    @Autowired
    private JacksonTester<CashCard> json;

    @Test
    private void cashCardSerializationTest() throws Exception {
        CashCard cashcard = new CashCard(99L, 123.45);

        assertThat(json.write(cashcard)).isStrictlyEqualToJson("expected.json");

        assertThat(json.write(cashcard)).hasJsonPathNumberValue("@.id");

        assertThat(json.write(cashcard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);

        assertThat(json.write(cashcard)).hasJsonPathNumberValue("@.amount");

        assertThat(json.write(cashcard)).extractingJsonPathNumberValue("@.amount")
                .isEqualTo(123.45);


    }

}
