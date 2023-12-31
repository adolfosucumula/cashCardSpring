package com.cashcardspring.cashCardSpring;

import com.cashcardspring.cashCardSpring.mappers.CashCard;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class CashCardTest{

    @Autowired
    private JacksonTester<CashCard> json;

    private CashCard[] cashCards;

    @Autowired
    private JacksonTester<CashCard[]> jsonList;


    @BeforeEach
    void setUp() {
        cashCards = Arrays.array(
                new CashCard(99L, 123.45, "sarah1"),
                new CashCard(100L, 1.00, "sarah1"),
                new CashCard(101L, 150.00, "sarah1"));
    }

    @Test
    public void serialiZationTest() throws Exception {
        //CashCard cashCard = new CashCard(99L, 123.45, "sarah1");
        CashCard cashCard = this.cashCards[0];
       //assertThat(1).isEqualTo(1);

        assertThat(json.write(cashCard)).isStrictlyEqualToJson("/data/single.json");

        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");

        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);

        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");

        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
                .isEqualTo(123.45);

        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.owner");

        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.owner")
                .isEqualTo("sarah1");


    }

    @Test
    public void DeserializationTest() throws Exception {
        String expected = """
           {
               "id":1000,
               "amount":123.45,
               "owner": "sarah1"
           }
           """;

        assertThat(json.parse(expected)).isEqualTo(new CashCard(1000L, 123.45, "sarah1"));
        assertThat(json.parseObject(expected).id()).isEqualTo(1000);
        assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);

    }


}
