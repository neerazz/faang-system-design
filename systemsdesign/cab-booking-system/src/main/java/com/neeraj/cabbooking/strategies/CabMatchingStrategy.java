package com.neeraj.cabbooking.strategies;

import com.neeraj.cabbooking.model.Cab;
import com.neeraj.cabbooking.model.Location;
import com.neeraj.cabbooking.model.Rider;

import java.util.List;

public interface CabMatchingStrategy {

  Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint);
}
