package com.neeraj.cabbooking.strategies;

import com.neeraj.cabbooking.model.Location;

public interface PricingStrategy {
    Double findPrice(Location fromPoint, Location toPoint);
}
