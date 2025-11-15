package com.innowise.userservice.controller;

import com.innowise.userservice.dto.request.PaymentCardCreateDto;
import com.innowise.userservice.dto.request.PaymentCardUpdateDto;
import com.innowise.userservice.dto.response.PaymentCardResponseDto;
import com.innowise.userservice.service.PaymentCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class PaymentCardController {

    private final PaymentCardService paymentCardService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentCardResponseDto> getById(@PathVariable Long id) {
        PaymentCardResponseDto dto = paymentCardService.findDtoById(id);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentCardResponseDto>> findAllCardsForUser(@PathVariable Long userId) {
        List<PaymentCardResponseDto> res = paymentCardService.findAllByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping
    public ResponseEntity<Page<PaymentCardResponseDto>> findAll(
            @RequestParam(required = false) Boolean active,
            @RequestParam(value = "expires_after", required = false) LocalDate expiresAfter,
            @RequestParam(value = "expires_before", required = false) LocalDate expiresBefore,
            Pageable pageable) {
        Page<PaymentCardResponseDto> page = paymentCardService.findAll(active, expiresAfter, expiresBefore, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @PostMapping
    public ResponseEntity<PaymentCardResponseDto> create(@RequestBody @Valid PaymentCardCreateDto dto) {
        PaymentCardResponseDto created = paymentCardService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentCardResponseDto> update(@PathVariable Long id,
                                                         @RequestBody @Valid PaymentCardUpdateDto dto) {
        PaymentCardResponseDto updated = paymentCardService.update(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentCardService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<PaymentCardResponseDto> activate(@PathVariable Long id) {
        PaymentCardResponseDto updated = paymentCardService.changeStatus(id, true);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<PaymentCardResponseDto> deactivate(@PathVariable Long id) {
        PaymentCardResponseDto updated = paymentCardService.changeStatus(id, false);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
}
