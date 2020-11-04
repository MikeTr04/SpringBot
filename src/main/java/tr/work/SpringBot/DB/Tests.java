package tr.work.SpringBot.DB;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="Words")
@Getter
@Setter
public class Tests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long test_id;

    @Column
    private String name;

    @Column
    private long[] words_id;
}
