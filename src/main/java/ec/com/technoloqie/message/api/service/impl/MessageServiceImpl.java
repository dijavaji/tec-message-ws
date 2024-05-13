package ec.com.technoloqie.message.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import ec.com.technoloqie.message.api.dto.ChatDto;
import ec.com.technoloqie.message.api.service.IChatBotService;
import ec.com.technoloqie.message.api.service.IMessageService;

@Service
public class MessageServiceImpl  extends TelegramLongPollingBot implements IMessageService{

	Logger logger= LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Autowired
	private IChatBotService chatbotservice;
	
	@Override
	public void onUpdateReceived(Update update) {
		
		logger.info("actualizando--");
		
		Message msg = update.getMessage();
		//se obtiene el id de chat del usuario
		final long chatId = update.getMessage().getChatId();
		
		//se crea un objeto mensaje
		SendMessage sendmessage = new SendMessage();
		sendmessage.setChatId(chatId);
		//sendmessage.setParseMode(ParseMode.MARKDOWN);
		if(msg.hasText()) {
			
			//Se obtiene el mensaje escrito por el usuario
			final String messageTextReceived = update.getMessage().getText();
			logger.info("excribieron en el bot." + messageTextReceived);
			
			if(StringUtils.equals(StringUtils.lowerCase(msg.getText()), "hola bot") ) {
				
				// Create a keyboard
		        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
		        //sendMessage.setReplyMarkup(replyKeyboardMarkup);
		        replyKeyboardMarkup.setSelective(true);
		        replyKeyboardMarkup.setResizeKeyboard(true);
		        replyKeyboardMarkup.setOneTimeKeyboard(false);

		        // Create a list of keyboard rows
		        List<KeyboardRow> keyboard = new ArrayList<>();
		        
				//keyboardbutton
				//inlinekeyboardbutton
		        // Add all of the keyboard rows to the list
		        sendmessage.setText("Hola, bienvenido");
		        keyboard.add(createButton("Ayuda"));
		        keyboard.add(createButton("Login"));
		        keyboard.add(getButtonContact("Compartir contacto"));
		        keyboard.add(getButtonLocation("Compartir ubicacion"));
		        //keyboard.add(keyboardSecondRow);
		        // and assign this list to our keyboard
		        replyKeyboardMarkup.setKeyboard(keyboard);
				
				sendmessage.setReplyMarkup(replyKeyboardMarkup);
			}else if(StringUtils.equals(StringUtils.lowerCase(msg.getText()) , "ayuda")){
				sendmessage.setText("Puedes navegar por el menu para acceder a las distintas respuestas automaticas.");
			}else if(StringUtils.equals(StringUtils.lowerCase(msg.getText()) , "login")){
				sendmessage.setText("Ingresa tu nombre de usuario");
			}else if(StringUtils.equals(StringUtils.lowerCase(msg.getText()) , "admin")){
				sendmessage.setText("Ingresa tu contrasena");
			}else if(StringUtils.equals(StringUtils.lowerCase(msg.getText()) , "123456")){
				sendmessage.setText("Bienvenido Pepito , Hardvard");
			}else {
				//sendmessage.setText("No reconozco tu pregunta o solicitud.");
				ChatDto chat = chatbotservice.getChatMessage(messageTextReceived);
				logger.info("respuesta del servicio"+ chat.getText());
				sendmessage.setText(chat.getText());
			}
			

		}else if(msg.hasContact()){
			Contact contact = msg.getContact();
			logger.info("compartio contacto." + contact.getFirstName());
			sendmessage.setText("Tu numero de telefono " + contact.getPhoneNumber());
		}else if(msg.hasLocation()){
			Location location = msg.getLocation();
			logger.info("compartio ubicacion." + location.getProximityAlertRadius());
			sendmessage.setText("Tu ubicacion " + location.getLatitude() +", " +location.getLatitude());
		}
		
		try {
			execute(sendmessage);
		}catch(Exception e) {
			logger.error("Error al enviar msj ",e);
		}
		
		
	}
	
	/**
     * Method for creating a message and sending it.
     * @param chatId chat id
     * @param s The String that you want to send as a message.
     */
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
        	logger.error("Error al momento de enviar msg: ", e);
        }
    }
    
    private ReplyKeyboardMarkup getButtons() {
        // Create a keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        //sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Create a list of keyboard rows
        List<KeyboardRow> keyboard = new ArrayList<>();

        // First keyboard row
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Add buttons to the first keyboard row
        keyboardFirstRow.add(new KeyboardButton("Hi"));

        // Second keyboard row
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Add the buttons to the second keyboard row
        keyboardSecondRow.add(new KeyboardButton("Help"));

        // Add all of the keyboard rows to the list
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // and assign this list to our keyboard
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }
    
    private KeyboardRow createButton(String textButton) {
        // First keyboard row
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Add buttons to the first keyboard row
        keyboardFirstRow.add(new KeyboardButton(textButton));
        return keyboardFirstRow;
    }
    
    private KeyboardRow getButtonContact(String text) {
    	//ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    	//replyKeyboardMarkup.setResizeKeyboard(true);
    	//replyKeyboardMarkup.setSelective(true);

    	//List<KeyboardRow> keyboardRowLst = new ArrayList<>();
    	// First keyboard row
    	KeyboardRow keyboardRow1 = new KeyboardRow();
    	KeyboardButton keyboardButton1 = new KeyboardButton();
    	keyboardButton1.setText(text);
    	keyboardButton1.setRequestContact(true);
    	keyboardRow1.add(keyboardButton1);
    	//keyboardRowLst.add(keyboardRow1);
    	//replyKeyboardMarkup.setKeyboard(keyboardRowLst);
    	return keyboardRow1;
    }
    
    private KeyboardRow getButtonLocation(String text) {
    	KeyboardRow keyboardRow1 = new KeyboardRow();
    	KeyboardButton keyboardButton1 = new KeyboardButton();
    	keyboardButton1.setText(text);
    	keyboardButton1.setRequestLocation(true);
    	keyboardRow1.add(keyboardButton1);
    	return keyboardRow1;
    }


	@Override
	public String getBotUsername() {
		return "technoloqieBot";
	}
	
	@Override
	public String getBotToken() {
		return "624237783:AAGhlzzyGLFBFNGlQ802Bg59HoyMf6PA0Tw";
	}

}
