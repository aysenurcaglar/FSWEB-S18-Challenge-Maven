package com.workintech.fswebs18challengemaven.util;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import org.springframework.http.HttpStatus;

public class CardValidation {

    public static void validateCard(Card card) {
        if (card == null) {
            throw new CardException("Request invalid: Card cannot be null");
        }

        if (card.getType() == null && card.getValue() == null) {
            throw new CardException("Request invalid: Card must have a type or value");
        }

        if (card.getType() != null && card.getValue() != null) {
            throw new CardException("Request invalid: Card cannot have both type and value");
        }

        if (card.getType() == Type.JOKER && (card.getValue() != null || card.getColor() != null)) {
            throw new CardException("Request invalid: JOKER card must not have value or color");
        }

//        if (card.getColor() == null && card.getType() != Type.JOKER) {
//            throw new CardException("Request invalid: Non-JOKER cards must have a color");
//        }
    }

    public static void validateColor(String color) {
        boolean validColor = false;
        for (Color cardColor : Color.values()) {
            if (cardColor.name().equalsIgnoreCase(color)) {
                validColor = true;
                break;
            }
        }

        if (!validColor) {
            throw new CardException("Card not found");
        }
    }

//    public static void validateType(String type) {
//        boolean validType = false;
//        for (Type cardType : Type.values()) {
//            if (cardType.name().equalsIgnoreCase(type)) {
//                validType = true;
//                break;
//            }
//        }
//
//        if (!validType) {
//            throw new CardException("Card not found");
//        }
//    }
}
