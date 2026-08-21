package com.taskmanagement.backend.service;

import com.taskmanagement.backend.dto.CardResponse;
import com.taskmanagement.backend.entity.Card;
import com.taskmanagement.backend.repository.CardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<CardResponse> searchCards(String keyword) {
        List<Card> cards = (keyword == null || keyword.isBlank())
                ? cardRepository.findAll()
                : cardRepository.findByTitleContainingIgnoreCase(keyword);
        return cards.stream().map(CardResponse::from).toList();
    }

    public CardResponse getCardById(UUID id) {
        return cardRepository.findById(id)
                .map(CardResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found: " + id));
    }
}
