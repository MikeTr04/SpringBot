package tr.work.SpringBot.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WordsService {

    private final WordsRepository wordsRepository;

    public WordsService(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;

    }



    public void createWord(Words word){
        wordsRepository.save(word);
    }


    public Optional<Words> findById(Long id) {
        return wordsRepository.findById(id);
    }

}