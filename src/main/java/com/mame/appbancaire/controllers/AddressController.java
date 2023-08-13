package com.mame.appbancaire.controllers;

import com.mame.appbancaire.dto.AddressDto;
import com.mame.appbancaire.models.Address;
import com.mame.appbancaire.services.AddressService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody AddressDto addressDto
            ) {
              return ResponseEntity.ok(service.save(addressDto));
            }

    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> findAll() {
             return ResponseEntity.ok(service.findAll());
            }

    @GetMapping("/{address-id}")
    public ResponseEntity<AddressDto> findById(
            @PathVariable("address-id") Integer addressId
           ) {
             return ResponseEntity.ok(service.findById(addressId));
           }
    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("address-id") Integer addressId
           ) {
              service.delete(addressId);
              return ResponseEntity.accepted().build();
    }




}
