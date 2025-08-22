package com.tenancy.multitenant.dto.roomtypes;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomTypesRequest {

    private String type;

    private Integer guestCount;
}
