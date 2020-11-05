package tr.work.SpringBot.DB;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface WordsRepository extends JpaRepository<Words, Long> {
    @Query(nativeQuery = true, value = "Select * from words where id = :id")
    Optional <Words> findById(@Param("id") Long id);
}