package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.repository.CardRepositoryImpl;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workintech/cards")
@CrossOrigin("*")
public class CardController {

    private CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("")
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @GetMapping("/byColor/{color}")
    public List<Card> getCardsByColor(@PathVariable String color) {
        return cardRepository.findByColor(color.toUpperCase());

    }

    @PostMapping("")
    public Card createCard(@RequestBody Card card) {
        CardValidation.validateCard(card);
        return cardRepository.save(card);
    }

    @PutMapping("/{id}")
    public Card updateCard(@PathVariable Long id, @RequestBody Card updatedCard) {
        Card existingCard = cardRepository.findAll().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new CardException("Card with id " + id + " not found"));

        CardValidation.validateCard(updatedCard);
        updatedCard.setId(id);
        return cardRepository.update(updatedCard);
    }

    @DeleteMapping("/{id}")
    public Card deleteCard(@PathVariable Long id) {
        Card removedCard = cardRepository.remove(id);
        if (removedCard == null) {
            throw new CardException("Card with id " + id + " not found");
        }
        return removedCard;
    }

    @GetMapping("/byValue/{value}")
    public List<Card> getCardsByValue(@PathVariable Integer value) {
        return cardRepository.findByValue(value);
    }

    @GetMapping("/byType/{type}")
    public List<Card> getCardsByType(@PathVariable String type) {
        return cardRepository.findByType(type.toUpperCase());
    }
}
