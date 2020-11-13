package com.uditagarwal.cabbooking;

import com.neeraj.cabbooking.controllers.CabsController;
import com.neeraj.cabbooking.controllers.RidersController;
import com.neeraj.cabbooking.database.CabsManager;
import com.neeraj.cabbooking.database.RidersManager;
import com.neeraj.cabbooking.database.TripsManager;
import com.neeraj.cabbooking.exceptions.*;
import com.neeraj.cabbooking.strategies.CabMatchingStrategy;
import com.neeraj.cabbooking.strategies.DefaultCabMatchingStrategy;
import com.neeraj.cabbooking.strategies.DefaultPricingStrategy;
import com.neeraj.cabbooking.strategies.PricingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RunnerTest {
  CabsController cabsController;
  RidersController ridersController;

  @BeforeEach
  void setUp() {
    CabsManager cabsManager = new CabsManager();
    RidersManager ridersManager = new RidersManager();

    CabMatchingStrategy cabMatchingStrategy = new DefaultCabMatchingStrategy();
    PricingStrategy pricingStrategy = new DefaultPricingStrategy();

    TripsManager tripsManager = new TripsManager(cabsManager, ridersManager, cabMatchingStrategy, pricingStrategy);

    cabsController = new CabsController(cabsManager, tripsManager);
    ridersController = new RidersController(ridersManager, tripsManager);
  }

  @Test
  void testCabBookingFlow() {

    String r1 = "r1";
    ridersController.registerRider(r1, "ud");
    String r2 = "r2";
    ridersController.registerRider(r2, "du");
    String r3 = "r3";
    ridersController.registerRider(r3, "rider3");
    String r4 = "r4";
    ridersController.registerRider(r4, "rider4");

    String c1 = "c1";
    cabsController.regiserCab(c1, "driver1");
    String c2 = "c2";
    cabsController.regiserCab(c2, "driver2");
    String c3 = "c3";
    cabsController.regiserCab(c3, "driver3");
    String c4 = "c4";
    cabsController.regiserCab(c4, "driver4");
    String c5 = "c5";
    cabsController.regiserCab(c5, "driver5");

    cabsController.updateCabLocation(c1, 1.0, 1.0);
    cabsController.updateCabLocation(c2, 2.0, 2.0); //na
    cabsController.updateCabLocation(c3, 100.0, 100.0);
    cabsController.updateCabLocation(c4, 110.0, 110.0); //na
    cabsController.updateCabLocation(c5, 4.0, 4.0);

    cabsController.updateCabAvailability(c2, false);
    cabsController.updateCabAvailability(c4, false);

    ridersController.book(r1, 0.0, 0.0, 500.0, 500.0);
    ridersController.book(r2, 0.0, 0.0, 500.0, 500.0);

    System.out.println("\n### Printing current trips for r1 and r2");
    System.out.println(ridersController.fetchHistory(r1).getBody());
    System.out.println(ridersController.fetchHistory(r2).getBody());

    cabsController.updateCabLocation(c5, 50.0, 50.0);

    System.out.println("\n### Printing current trips for r1 and r2");
    System.out.println(ridersController.fetchHistory(r1).getBody());
    System.out.println(ridersController.fetchHistory(r2).getBody());

    cabsController.endTrip(c5);

    System.out.println("\n### Printing current trips for r1 and r2");
    System.out.println(ridersController.fetchHistory(r1).getBody());
    System.out.println(ridersController.fetchHistory(r2).getBody());


    assertThrows(NoCabsAvailableException.class, () -> {
      ridersController.book(r3, 0.0, 0.0, 500.0, 500.0);
    });

    ridersController.book(r4, 48.0, 48.0, 500.0, 500.0);
    System.out.println("\n### Printing current trips for r1, r2 and r4");
    System.out.println(ridersController.fetchHistory(r1).getBody());
    System.out.println(ridersController.fetchHistory(r2).getBody());
    System.out.println(ridersController.fetchHistory(r4).getBody());

    assertThrows(RiderNotFoundException.class, () -> {
      ridersController.book("abcd", 0.0, 0.0, 500.0, 500.0);
    });

    assertThrows(RiderAlreadyExistsException.class, () -> {
      ridersController.registerRider("r1", "shjgf");
    });

    assertThrows(CabAlreadyExistsException.class, () -> {
      cabsController.regiserCab("c1", "skjhsfkj");
    });

    assertThrows(CabNotFoundException.class, () -> {
      cabsController.updateCabLocation("shss", 110.0, 110.0);
    });

    assertThrows(CabNotFoundException.class, () -> {
      cabsController.updateCabAvailability("shss", false);
    });
  }
}
