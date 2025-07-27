package movieTicketSystem;

import java.time.LocalDate;
import java.util.List;

public class Movie {
    String name;
    List<String> actors;
}
class PlayingMovie {
    Movie movie;
    LocalDate startDate;
    LocalDate endDate;

    boolean inRange(LocalDate date){
        return startDate.equals(date) || (startDate.isAfter(date) && endDate.isBefore(date)) || endDate.equals(date);
    }
}