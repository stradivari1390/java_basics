import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@EqualsAndHashCode
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer salary;
    private Integer age;

    @Override
    public String toString() {
        return "Teacher's name: " + name;
    }
}
