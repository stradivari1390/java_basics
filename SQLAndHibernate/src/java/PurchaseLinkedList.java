import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "purchase_linked_list")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class PurchaseLinkedList {
    @EmbeddedId
    private PurchaseLinkedListKey id;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;
}
