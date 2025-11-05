package model.additional_components;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public final class Capacity {
    @Min(200)
    @Max(100000)
    private final int generalCapacity;
    private final int vipSeats;
    private final int standardSeats;
    private final int seatsForAwayGuests;

    public Capacity(int generalCapacity) {
        this.generalCapacity = generalCapacity;
        this.seatsForAwayGuests = generalCapacity * 8 / 100;
        this.vipSeats = generalCapacity * 2 / 100;
        this.standardSeats = generalCapacity - seatsForAwayGuests - vipSeats;
    }

    public int getGeneralCapacity() {
        return generalCapacity;
    }

    public int getVipSeats() {
        return vipSeats;
    }

    public int getStandardSeats() {
        return standardSeats;
    }

    public int getSeatsForAwayGuests() {
        return seatsForAwayGuests;
    }

    @Override
    public String toString() {
        return "Capacity{" +
                "generalCapacity=" + generalCapacity +
                ", vipSeats=" + vipSeats +
                ", standardSeats=" + standardSeats +
                ", seatsForAwayGuests=" + seatsForAwayGuests +
                '}' + '\n';
    }
}
