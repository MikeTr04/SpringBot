package tr.work.SpringBot;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.telegram.telegrambots.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import tr.work.SpringBot.DB.Words;
import tr.work.SpringBot.DB.WordsRepository;
import tr.work.SpringBot.DB.WordsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class Handler extends TelegramLongPollingBot {
    private static final String TOKEN = "1127398277:AAGlTNdr0tp2I1YJVzcd--5x38a5_UWbNXw";
    private static final String USERNAME = "TrLangBot";


    private WordsRepository wordsRepository;

    private WordsService wordsService = new WordsService(wordsRepository);

    public Handler(WordsService wordsService){
        this.wordsService = wordsService;
    }



    final String test_paper =  new String(Character.toChars(0x1F4DD));
    final String home = new String(Character.toChars(0x1F3E0));
    final String robot = new String(Character.toChars(0x1F916));
    final String hello = new String(Character.toChars(0x270B));
    public final String[] StartCommand = {home+"Головне меню", test_paper+"Тести", robot+"Про бота"};
    




    @Override
    public String getBotUsername() {

        return USERNAME;
    }

    @Override
    public String getBotToken() {

        return TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage()){
            Message message = update.getMessage();
            if(message.hasText()){
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString());
                String text = update.getMessage().getText();
                if("/start".equalsIgnoreCase(text) || text.equalsIgnoreCase(home+"Головне меню")){
                    sendMessageRequest.setText("<b>Привіт!</b>"+hello+"\nЯ - бот для вивчення слів англійською мовю.");
                    sendMessageRequest.setParseMode("HTML");
                    setButtons(sendMessageRequest, StartCommand);

                }else if(text.equalsIgnoreCase(robot+"Про бота")){
                    sendMessageRequest.setText("Я - бот для вивчення слів англійською мовою. Я був створений як кваліфікаційна робота з інформатики учнем Ліцею інформаційних технологій. Створювач цього бота хотів зробити легшим процес вивчення іноземної мови та дізнатися щось нове в програмуванні."+"\n\n" +
                            "Бот дозволяє проходити тести, знаходячи правильний переклад слів. Саме така тематика є нині дуже актуальною, так як без іноземної мови, такої як англійська, я сьогоденному світі прожити майже неможливо, а така форма, тобто бот у телеграмі, дозволяє вчитись прямо в цьому популярному додатку, яким користуються мільйони людей по всьому світу. Бот є зручним і дозволяє вчити нові слова в будь-який момент часу за умови підключення до мережі."+"\n\n" +
                            "Під час написання цього боту використовувалася мова програмування Java. Основні технології - це фреймворк Spring, база даних Postgresql і хостинг Heroku."+"\n\n" +
                            "Ці технології є дуже затребуваними для програмістів, які спеціалізуються на мові Java, і використовуються для створення великої кількості різноманітних додатків і сайтів."
                    );
                    sendMessageRequest.setParseMode("HTML");
                    setButtons(sendMessageRequest, StartCommand);

                }else if(text.equalsIgnoreCase("/info1")){
                    sendMessageRequest.setText("Щоб створити свого бота, потрібно спочатку зареєструвати його, застосовуючи спеціального бота від Telegram 'BotFather'. Після цього ви оттримаєте токен та зможете встановити ім'я бота, та змінювати фото й інформацію про бота." + "\n\n" +
                            "Далі, потрібно написати код, який допомагає зареєструвати бота. Для зручності й розширення можливостей може використовуватися популярний фреймворк Spring."
                    );
                    sendMessageRequest.setParseMode("HTML");
                    setButtons(sendMessageRequest, StartCommand);

                }else if(text.equalsIgnoreCase("/info2")){
                    sendMessageRequest.setText("Для того, щоб хостити бота не зі свого комп'ютера, так як це є дуже незручним, можна використовувати різні сервіси. Але я зупинився на Heroku, тому що він є безкоштовним і відкриває нові можливості для різноманітних додатків." + "\n\n" +
                            "Наприклад, одна з можливостей Heroku э створення бази даних Postgresql і її хостинг, який також є безкоштовним. Єдиним мінусом є те, що додаток має 'спати' протягом 6 годин. В моєму випадку я вибрав години відпочинку з 2 до 8 ранку, так як у цей час є маловірогідним те, що хтось буде навчатися."
                    );
                    sendMessageRequest.setParseMode("HTML");
                    setButtons(sendMessageRequest, StartCommand);

                }
                else{
                    Optional<Words> word = wordsService.findById(1L);
                    if(word.isPresent()) {

                        sendMessageRequest.setText("Value found - " + word.get());
                    } else {

                        sendMessageRequest.setText("Optional is empty");
                    }
                    sendMessageRequest.setText(word.get().getWord());

                    try {
                        sendMessage(sendMessageRequest);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    sendMessageRequest.setText("Hello");

                    setButtons(sendMessageRequest, StartCommand);
                }





                try {
                    sendMessage(sendMessageRequest);
                } catch (TelegramApiException e) {
                    e.printStackTrace();

                }
            }

        }


    }
    public synchronized void setButtons(SendMessage sendMessage, String[] command) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);


        List<KeyboardRow> keyboard = new ArrayList<>();
        for(int i = 0; i< command.length; i++){
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton(command[i]));
            keyboard.add(keyboardRow);
        }

        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public synchronized void answerCallbackQuery(String callbackId, String message) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackId);
        answer.setText(message);
        answer.setShowAlert(true);
        try {
            answerCallbackQuery(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    private void setInline(SendMessage sendMessage) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        buttons1.add(new InlineKeyboardButton().setText("Кнопка").setCallbackData(String.valueOf(17)));
        buttons.add(buttons1);


        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        sendMessage.setReplyMarkup(markupKeyboard);
    }


}
