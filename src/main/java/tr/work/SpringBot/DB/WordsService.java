package tr.work.SpringBot.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class WordsService {
    @Autowired
    private final WordsRepository wordsRepository;

    @Transactional
    public void testWordsRepository(){
        Optional<Words> wordsOptional = wordsRepository.findById(8L);
    }


    public WordsService(WordsRepository wordsRepository){
        this.wordsRepository = wordsRepository;

    }

    public void createWord(Words word){
        wordsRepository.save(word);
    }



}
