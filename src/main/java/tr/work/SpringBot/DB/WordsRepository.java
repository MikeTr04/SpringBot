package tr.work.SpringBot.DB;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface WordsRepository extends JpaRepository<Words, Long> {
    Optional<Words> findById(Long id);
}
