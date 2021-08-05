package com.neeraj.cabbooking.model;

import lombok.NonNull;
import lombok.ToString;

import static com.neeraj.cabbooking.model.TripStatus.FINISHED;
import static com.neeraj.cabbooking.model.TripStatus.IN_PROGRESS;

enum TripStatus {
    IN_PROGRESS,
    FINISHED
}

@ToString
public class Trip {
    private final Rider rider;
    private final Cab cab;
    private final Double price;
    private final Location fromPoint;
    private final Location toPoint;
    private TripStatus status;

    public Trip(
            @NonNull final Rider rider,
            @NonNull final Cab cab,
            @NonNull final Double price,
            @NonNull final Location fromPoint,
            @NonNull final Location toPoint) {
        this.rider = rider;
        this.cab = cab;
        this.price = price;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.status = IN_PROGRESS;
    }

    public void endTrip() {
        this.status = FINISHED;
    }
}
