package com.neeraj.cabbooking.strategies;

import com.neeraj.cabbooking.model.Cab;
import com.neeraj.cabbooking.model.Location;
import com.neeraj.cabbooking.model.Rider;
import lombok.NonNull;

import java.util.List;

public class DefaultCabMatchingStrategy implements CabMatchingStrategy {

  @Override
  public Cab matchCabToRider(
          @NonNull final Rider rider,
          @NonNull final List<Cab> candidateCabs,
          @NonNull final Location fromPoint,
          @NonNull final Location toPoint) {
    if (candidateCabs.isEmpty()) {
      return null;
    }
    return candidateCabs.get(0);
  }
}
