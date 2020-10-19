package tr.work.SpringBot.DAO;

import javax.persistence.*;


@Entity
@Table(name="Words")
public class Words {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String word;

    @Column
    private String definition;

    @Column
    private Long test_id;

}
