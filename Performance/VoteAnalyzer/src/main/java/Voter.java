import jakarta.persistence.*;
import jakarta.xml.bind.annotation.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GeneratedColumn;

//import javax.xml.bind.annotation.XmlType;
//import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity(name = "voter")
@Table(name = "electors", schema = "elections")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Voter implements Serializable {

    @NonNull
    @EmbeddedId
    private VoterKey voterKey;

    @Basic(fetch = FetchType.LAZY)
    @Column(insertable = false, updatable = false)
    @NonNull
    @ToString.Exclude
    private String name;

    @Basic(fetch = FetchType.LAZY)
    @Column(insertable = false, updatable = false)
    @NonNull
    @ToString.Exclude
//    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date birthDay;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "vote_count", nullable = true)
    @ToString.Exclude
    int voteCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Voter voter = (Voter) o;
        return voterKey != null && Objects.equals(voterKey, voter.voterKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voterKey);
    }

    public String toString() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
        return name + " (" + dayFormat.format(birthDay) + ")";
    }
}