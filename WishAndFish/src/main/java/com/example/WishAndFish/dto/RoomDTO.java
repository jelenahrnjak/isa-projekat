package com.example.WishAndFish.dto;

public class RoomDTO {
    private Integer bedNumber;

    public RoomDTO() {
    }

    public RoomDTO(Integer bedNumber) {
        this.bedNumber = bedNumber;
    }

    public Integer getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber = bedNumber;
    }

}
