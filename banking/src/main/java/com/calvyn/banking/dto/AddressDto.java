package com.calvyn.banking.dto;

import com.calvyn.banking.models.Address;
import com.calvyn.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AddressDto {

    private Integer id;

    private String street;

    private Integer houseNumber;

    private Integer zipCode;

    private String city;

    private String county;

    private Integer userId;

    public static AddressDto fromEntity(Address address){
        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .county(address.getCounty())
                .userId(address.getUser().getId())
                .build();
    }

    public static Address toEntity(AddressDto address){
        return Address.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .county(address.getCounty())
                .user(
                        User.builder()
                            .id(address.getUserId())
                            .build()
                )
                .build();
    }

}
