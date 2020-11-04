package tr.work.SpringBot.DB;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WordsService {

    private final WordsRepository wordsRepository;


    @Transactional
    public void testWordsRepository(){
        Optional<Words> wordsOptional = wordsRepository.findById(8L);
    }

    public void createWord(Words word){
        wordsRepository.save(word);
    }


    public Optional<Words> findById(Long id) {
        return wordsRepository.findById(id);
    }

}