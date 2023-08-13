package com.mame.appbancaire.dto;


import com.mame.appbancaire.models.Address;
import com.mame.appbancaire.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder

public class AddressDto {

    private Integer id;

    private String street;

    private Integer houseNumber;

    private Integer zipCode;

    private String city;

    private String country;

    private Integer userId;

    public static AddressDto fromEntity(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .country(address.getCountry())
                .city(address.getCity())
                .userId(address.getUser().getId())
                .build();
    }

    public static Address toEntity(AddressDto address) {
        return Address.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .country(address.getCountry())
                .city(address.getCity())
                .user(
                        User.builder()
                                .id(address.getId())
                                .build()
                )
                .build();
    }
}
