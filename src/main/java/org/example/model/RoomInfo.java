package org.example.model;

public class RoomInfo {
    private final String roomNo;
    private final String availability;

    public RoomInfo(String roomNo, String availability) {
        this.roomNo = roomNo;
        this.availability = availability;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getAvailability() {
        return availability;
    }
}
