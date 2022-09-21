import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "courses")
@Getter
@Setter
@EqualsAndHashCode
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Teacher teacher;

    @Column(name = "students_count", nullable = true)
    private Integer studentsCount;

    private Integer price;

    @Column(name = "price_per_hour")
    private Float pricePerHour;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "subscriptions",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private List<Student> students;

    @Override
    public String toString() {
        return "Course name: " + name;
    }
}
