import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PurchaseLinkedListKey implements Serializable {

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    public PurchaseLinkedListKey() {
    }

    public PurchaseLinkedListKey(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }
}