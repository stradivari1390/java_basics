package response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Period;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class Task {

    private int id;

    private Date addDateTime;

    private String shortDescription;

    private String fullDescription;

    private Date ShouldBeCompletedBeforeDateTime;

    private Importance level;

    private boolean accomplished = false;

    private Date completedOnDateTime;

    public void reminder(Period period) {}

}
