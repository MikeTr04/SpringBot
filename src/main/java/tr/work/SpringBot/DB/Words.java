package tr.work.SpringBot.DB;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="Words")
@Getter
@Setter
public class Words {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String word;

    @Column
    private String definition;
}
