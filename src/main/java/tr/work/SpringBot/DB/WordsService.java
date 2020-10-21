package tr.work.SpringBot.DB;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor    //генерирует конструктор с одним параметром для каждого поля требующего специальной обработки (на сайте ломбока расписано подробнее)
public class WordsService {

    private final WordsRepository wordsRepository;



    @Transactional
    public void testWordsRepository(){
        Optional<Words> wordsOptional = wordsRepository.findById(8L);
    }

    public void createWord(Words word){
        wordsRepository.save(word);
    }
}