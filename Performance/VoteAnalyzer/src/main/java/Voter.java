import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

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
public class Voter {

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
    private Date birthday;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "vote_count", nullable = true)
    @ToString.Exclude
    int voteCount;

    public String toString() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
        return name + " (" + dayFormat.format(birthday) + ")";
    }

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
}