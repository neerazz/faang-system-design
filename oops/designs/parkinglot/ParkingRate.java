package parkinglot;

import java.util.*;
import java.io.*;

class ParkingRate {

    public static Double getFare(ParkingSpotType parkingSpotType, long hours) {
        if (hours >= 3) {
            return 3 * parkingSpotType.per3Hour;
        } else if (hours >= 2) {
            return 2 * parkingSpotType.per2Hour;
        }
        return 1 * parkingSpotType.per1Hour;
    }
}
