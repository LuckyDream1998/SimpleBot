import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot{
  private String botName = "LuckyDream1998Bot";
  private String botToken = "871420849:AAH8vGpmdbeoWYhTOTT8UfPiz8ThszgnSh4";

  @Override
  public void onUpdateReceived(Update update){
    if (update.hasMessage()) {
      switch (update.getMessage().getText()) {
        case "/start":
          sendMsg(update.getMessage(), "Здравствуйте, " + update.getMessage().getFrom().getFirstName() + ". Чем могу помочь?"); break;
        case "Хочу узнать курс тенге":
          sendMsg(update.getMessage(), "К какой валюте вы желаете узнать курс тенге?"); break;
        case "Доллар": sendMsg(update.getMessage(), Util.getCurrency(update.getMessage().getText()) + " KZT"); break;
        case "Рубль": sendMsg(update.getMessage(), Util.getCurrency(update.getMessage().getText()) + " KZT"); break;
        case "Евро": sendMsg(update.getMessage(), Util.getCurrency(update.getMessage().getText()) + " KZT"); break;
      }
    }
  }

  @Override
  public String getBotUsername() {
    return botName;
  }

  @Override
  public String getBotToken() {
    return botToken;
  }

  private void sendMsg(Message message, String answer) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(true);
    sendMessage.setChatId(message.getChatId());
    sendMessage.setText(answer);

    try{
      if (message.getText().equalsIgnoreCase("Хочу узнать курс тенге")) {
        setSecondaryMenu(sendMessage);
      } else {
      setMainMenu(sendMessage);
      }
      execute(sendMessage);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }

  private void setMainMenu(SendMessage sendMessage) {
    List<KeyboardRow> keyboardRowList = new ArrayList<>();
    KeyboardRow keyboardFirstRow = new KeyboardRow();
    keyboardFirstRow.add(new KeyboardButton("Хочу узнать курс тенге"));
    keyboardRowList.add(keyboardFirstRow);
    sendMessage.setReplyMarkup(getReplyKeyboardMarkup(keyboardRowList));
  }

  private void setSecondaryMenu(SendMessage sendMessage) {
    List<KeyboardRow> keyboardRowList = new ArrayList<>();
    KeyboardRow keyboardFirstRow = new KeyboardRow();
    keyboardFirstRow.add(new KeyboardButton("Рубль"));
    KeyboardRow keyboardSecondRow = new KeyboardRow();
    keyboardSecondRow.add(new KeyboardButton("Доллар"));
    KeyboardRow keyboardThirdRow = new KeyboardRow();
    keyboardThirdRow.add(new KeyboardButton("Евро"));
    keyboardRowList.add(keyboardFirstRow);
    keyboardRowList.add(keyboardSecondRow);
    keyboardRowList.add(keyboardThirdRow);
    sendMessage.setReplyMarkup(getReplyKeyboardMarkup(keyboardRowList));
  }

  private ReplyKeyboardMarkup getReplyKeyboardMarkup(List<KeyboardRow> keyboardRowList) {
    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    replyKeyboardMarkup.setSelective(true);
    replyKeyboardMarkup.setResizeKeyboard(true);
    replyKeyboardMarkup.setOneTimeKeyboard(false);
    replyKeyboardMarkup.setKeyboard(keyboardRowList);
    return replyKeyboardMarkup;
  }
}
