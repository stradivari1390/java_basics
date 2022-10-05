package main.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.Period;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "tasks")
@Table(schema = "todolist")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "add_date")
    private Date addDateTime;

    @Column(length = 40)
    private String shortDescription;

    @Lob
    private String fullDescription;

    @Column(name = "to_complete_date")
    private Date ShouldBeCompletedBeforeDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "importance")
    private Importance level;

    @Column(nullable = false)
    private boolean accomplished;

    @Column(name = "completed_date")
    private Date completedOnDateTime;

    public void reminder(Period period) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Task task = (Task) o;
        return id != null && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
