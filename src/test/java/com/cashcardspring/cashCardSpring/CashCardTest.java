package com.cashcardspring.cashCardSpring;

import com.cashcardspring.cashCardSpring.mappers.CashCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class CashCardTest{

    @Autowired
    private JacksonTester<CashCard> json;

    @Test
    public void serialiZationTest() throws Exception {
        CashCard cashCard = new CashCard(99L, 123.45);
       //assertThat(1).isEqualTo(1);

       // assertThat(json.write(cashCard)).isStrictlyEqualToJson("single.json");

        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");

        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);

        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");

        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
                .isEqualTo(123.45);

    }

    @Test
    public void DeserializationTest() throws Exception {
        String expected = """
           {
               "id":1000,
               "amount":123.45
           }
           """;

        assertThat(json.parse(expected)).isEqualTo(new CashCard(1000L, 123.45));
        assertThat(json.parseObject(expected).id()).isEqualTo(1000);
        assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);

    }


}
