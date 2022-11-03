import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class VoterKey implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private Date birthday;
}