package ec.com.technoloqie.message.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Audio;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Video;
import org.telegram.telegrambots.meta.api.objects.VideoNote;
import org.telegram.telegrambots.meta.api.objects.Voice;
import org.telegram.telegrambots.meta.api.objects.games.Animation;
import org.telegram.telegrambots.meta.api.objects.payments.Invoice;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import ec.com.technoloqie.message.api.commons.util.TelegramUtils;
import ec.com.technoloqie.message.api.dto.ChatDto;
import ec.com.technoloqie.message.api.service.IChatBotService;
import ec.com.technoloqie.message.api.service.IMessageService;

@Service
public class MessageServiceImpl  extends TelegramLongPollingBot implements IMessageService{

	Logger logger= LoggerFactory.getLogger(MessageServiceImpl.class);
	
	@Value("${ec.com.technoloqie.message.telegram.bot.token}")
	private String telegramBotToken;
	
	@Value("${ec.com.technoloqie.message.telegram.bot.username}")
	private String telegramBotName;
	
	private IChatBotService chatbotservice;
	
	private final Map<String, Boolean> esperandoPassword = new HashMap<>();
	private final Map<String, String> tokens = new HashMap<>();
	
	public MessageServiceImpl(IChatBotService chatbotservice) {
		this.chatbotservice = chatbotservice;
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		logger.info("actualizando--");
		
		if (update.hasMessage()) {
			Message msg = update.getMessage();
			// se obtiene el id de chat del usuario
			final Long chatId = update.getMessage().getChatId();
			// String baseUrl = getBaseUrl();
			// String userName = getBotUsername();
			// Si el mensaje contiene texto
			if (msg.hasText()) {
				processMessageText(chatId.toString(), msg.getText());
			}else if (msg.hasContact()) {
				processContact(chatId, msg.getContact());
			}else if (msg.hasVoice()) {
				processVoice(chatId.toString(), msg.getVoice());
			}else if(msg.hasLocation()){
				processLocation(chatId.toString(), msg.getLocation());
			}else if(msg.hasAudio()) {
				processAudio(chatId.toString(), msg.getAudio());
			}else if(msg.hasPhoto()) {
				processImage(chatId.toString(), msg.getPhoto());
			}else if(msg.hasDocument()) {
				processDocument(chatId.toString(), msg.getDocument());
			}else if(msg.hasSticker()) {
				processSticker(chatId.toString(), msg.getSticker());
			}else if(msg.hasAnimation()) {
				processAnimation(chatId.toString(), msg.getAnimation()); 
			}else if(msg.hasInvoice()) {
				processInvoice(chatId.toString(), msg.getInvoice());
			}else if(msg.hasVideo()) {
				processVideo(chatId.toString(), msg.getVideo());
			}else if(msg.hasVideoNote()) {
				processVideoNote(chatId.toString(), msg.getVideoNote());
			}
			
		}
	}
	
	/**
     * Manejo de mensajes de texto del usuario
     */
	private void processMessageText(String chatId, String messageTextReceived) {
		logger.info("excribieron en el bot. ".concat(messageTextReceived).concat(" ").concat(chatId) );
		// Si el usuario esta respondiendo con su pass
        if (esperandoPassword.getOrDefault(chatId, false)) {
            processLogin(chatId, messageTextReceived);
            esperandoPassword.put(chatId, false);	//TODO llamar a servicio redis fwk-security
            return;
        }

		switch (messageTextReceived) {
		case "/menu":
			sendMessageKeyboard(chatId, "👋 ¡Bienvenido! Usa el men\u00fa para interactuar con el bot.");
			break;
		case "Comprar productos":
			sendMsg(chatId, "🛒 Aqu\u00ed puedes comprar productos. (Funcionalidad en desarrollo).");
			break;
		case "/login":
			requestPassword(chatId);
			break;
		case "/start":
			//TODO tomar msj de la base
			sendMsg(chatId, "¡Hola!😉 soy tu asesor virtual. Solo puedo proporcionar informaci\u00f3n Por favor, visita nuestra p\u00e1gina oficial para m\u00e1s informaci\u00f3n: https://technoloqie.site o ponte en contacto con info@technoloqie.site ¿En qu\u00e9 puedo ayudarte?");
			break;
		case "Ayuda":
			sendMsg(chatId, "📌 Opciones disponibles:\n- Comprar productos\n- Login\n- Ayuda");
			break;
		default:
			sendChatBot(chatId,messageTextReceived);
			break;
		}
	}

	private void processVoice(String chatId, Voice voice) {
		// TODO Auto-generated method stub
		sendMsg(chatId, "🎵 Recib\u00ed tu msj de voz. ".concat(voice.getFileSize().toString()).concat(" ¡Gracias!") );
	}

	private void processLocation(String chatId, Location location) {
		// TODO Auto-generated method stub
		logger.info("compartio ubicacion. ".concat(location.getProximityAlertRadius().toString()) );
		sendMsg(chatId,"Tu ubicaci\u00f3n " + location.getLatitude() +", " +location.getLatitude());
	}

	private void processAudio(String chatId, Audio audio) {
		// TODO Auto-generated method stub
		sendMsg(chatId, "🎵 Recib\u00ed tu audio.".concat(audio.getDuration().toString()).concat(" ¡Gracias!") );
	}

	private void processContact(Long chatId, Contact contact) {
		logger.info("compartio contacto. {}", contact.getFirstName());
		String nombre = contact.getFirstName() + (contact.getLastName() != null ? " " + contact.getLastName() : "");
		String telefono = contact.getPhoneNumber();
		String mensaje = "📞 Contacto recibido:\n" + "👤 Nombre: " + nombre + "\n" + "📱 Tel\u00e9fono: " + telefono;

		sendMsg(chatId.toString(), mensaje);

	}
	
	private void processImage(String chatId, List<PhotoSize> list) {
		// TODO Auto-generated method stub
		sendMsg(chatId, "🤖 Recib\u00ed tu imagen.".concat(" "+ list.size()).concat("¡Gracias!") );
	}
	
	private void processDocument(String chatId, Document document) {
		// TODO Auto-generated method stub
		sendMsg(chatId, "📄 Recib\u00ed tu archivo.".concat(" "+ document.getMimeType()).concat(" ¡Gracias!") );
	}
	
	private void processSticker(String chatId, Sticker sticker) {
		// TODO Auto-generated method stub
		sendMsg(chatId, "🔖 Recib\u00ed tu sticker.".concat(" "+ sticker.getEmoji()).concat(" ¡Gracias!") );
	}
	
	private void processAnimation(String chatId, Animation animation) {
		// TODO Auto-generated method stub
		sendMsg(chatId, "🤖 Recib\u00ed tu animacion.".concat(" "+ animation.getFileName()).concat(" ¡Gracias!") );
	}
	
	private void processVideoNote(String chatId, VideoNote videoNote) {
		// TODO Auto-generated method stub
		sendMsg(chatId, "🤖 Recib\u00ed tu videio.".concat(" "+ videoNote.getFileUniqueId()).concat(" ¡Gracias!") );
	}

	private void processVideo(String chatId, Video video) {
		// TODO Auto-generated method stub
		sendMsg(chatId, "🤖 Recib\u00ed tu video.".concat(" "+ video.getFileName()).concat(" ¡Gracias!") );
	}

	private void processInvoice(String chatId, Invoice invoice) {
		// TODO Auto-generated method stub
		sendMsg(chatId, "🤖 Recib\u00ed tu pago.".concat(" "+ invoice.getTotalAmount()).concat(" ¡Gracias!") );
	}
	
	 /**
     * Procesa el login con la pass enviada de manera privada.
     */
	private void processLogin(String chatId, String messageTextReceived) {
		//TODO agregar servicios fwk-security
		/*User usuario = userService.findByChatId(chatId);
        if (usuario == null) {
        	sendMsg(chatId, "❌ Usuario no registrado.");
            return;
        }

        // Validar la pass 
        if (!usuario.getPassword().equals(password)) {
        	sendMsg(chatId, "❌ Contraseña incorrecta.");
            return;
        }*/

        // Generar JWT con roles
        //String token = jwtUtil.generateToken(chatId, usuario.getRoles());
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbkBwYnguY29tIiwiaWF0IjoxNzQxNDgxNzcyLCJpc3MiOiJ0ZWNobm9sb3FpZS53ZWJzaXRlIiwiYXVkIjoidXJuOiB0ZWNobm9sb3FpZS53ZWJzaXRlLmFwaS52MSIsImV4cCI6MTc0NDA3Mzc3Miwicm9sZXMiOjEsImVtYWlsIjoiYWRtaW5AcGJ4LmNvbSIsInVzZXJuYW1lIjoiYWRtaW4ifQ.RGcLUqpxiXc9zxkDf_NvATY3A5A8ocbcb472mdWNNAS5ffPknJ4lW76ZWfv0UYXn";
        tokens.put(chatId, token);
        sendMsg(chatId, "🔑 Login exitoso. Tu sesi\u00f3n est\u00e1 activa.");
		
	}

	private void sendChatBot(String chatId, String text) {
		ChatDto messageDto = new ChatDto();
		messageDto.setText(text);
		messageDto.setSenderId(chatId);
		messageDto.setAssistantName(getBotUsername());
		ChatDto chat = chatbotservice.getChatMessage(messageDto);
		logger.info("respuesta del servicio. ".concat(chat.getText()));
		// sendmessage.setText(chat.getText());
		sendMsg(chatId, chat.getText());
	}
	
	/**
     * Method for creating a message and sending it.
     * @param chatId chat id
     * @param s The String that you want to send as a message.
     */
    private synchronized void sendMsg(String chatId, String s) {
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
    
    
    /**
     * Solicita la contrasena al usuario de manera segura con ForceReply.
     */
    private void requestPassword(String chatId) {
        SendMessage mensaje = new SendMessage();
        mensaje.setChatId(String.valueOf(chatId));
        mensaje.setText("🔒 Por favor, responde a este mensaje con tu contrase\u00f1a.");
        
        // Habilitamos ForceReply para que la respuesta sea privada
        ForceReplyKeyboard forceReply = new ForceReplyKeyboard();
        forceReply.setSelective(true);
        mensaje.setReplyMarkup(forceReply);

        try {
            execute(mensaje);
            esperandoPassword.put(chatId, true); // Marcamos al usuario como "esperando pass"
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Envia un mensaje con un teclado personalizado
     */
    private void sendMessageKeyboard(String chatId, String texto) {
        SendMessage mensaje = new SendMessage();
        mensaje.setChatId(chatId);
        mensaje.setText(texto);

        // Create a keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true); // Ajusta el tamano del teclado al contenido
        replyKeyboardMarkup.setOneTimeKeyboard(false); // El teclado permanece visible
        replyKeyboardMarkup.setSelective(true);
        
        // Create a list of keyboard rows
        List<KeyboardRow> keyboard = new ArrayList<>();
        // Agregar las filas al teclado
        keyboard.add(TelegramUtils.getInstance().createButton("Comprar productos"));
        
        KeyboardRow keyboardFirstRow = new KeyboardRow();
		keyboardFirstRow.add(TelegramUtils.getInstance().getButtonContact("Compartir contacto"));
		keyboardFirstRow.add(TelegramUtils.getInstance().getButtonLocation("Compartir ubicaci\u00f3n"));
        keyboard.add(keyboardFirstRow);
        //fila con "Login" y "Ayuda"
        keyboard.add(TelegramUtils.getInstance().createRowButtons("/login","Ayuda"));
        
        replyKeyboardMarkup.setKeyboard(keyboard);
        mensaje.setReplyMarkup(replyKeyboardMarkup);

        try {
            execute(mensaje);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    

	@Override
	public String getBotUsername() {
		return telegramBotName;
	}
	
	@Override
	public String getBotToken() {
		return telegramBotToken;
	}

}
