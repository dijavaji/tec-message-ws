package ec.com.technoloqie.message.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import ec.com.technoloqie.message.api.dto.ChatDto;
import ec.com.technoloqie.message.api.service.IChatBotService;


@CrossOrigin(origins = {"/**"})
@Controller
public class MessageSocketController {
	
	Logger logger= LoggerFactory.getLogger(MessageSocketController.class);
	
	private IChatBotService chatbotservice;
	
	public MessageSocketController(IChatBotService chatbotservice) {
		this.chatbotservice = chatbotservice;
	}
	
	@MessageMapping("/chat.register")
	@SendTo("/topic/public")
	public ChatDto registerMessage(@Payload ChatDto message, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", message.getSenderId());
		return message;
	}
	
	@MessageMapping("/chat")
	@SendTo("/topic/messages")
	public ChatDto sendMessage(@Payload ChatDto chatmessage) {
		logger.info("mensaje por socket "+ chatmessage.getText());
		//sendmessage.setText("No reconozco tu pregunta o solicitud.");
		ChatDto chat = chatbotservice.getChatMessage(chatmessage);
		logger.info("respuesta del servicio"+ chat.getText());
		String text = null;
		if(chat.getText()!=null) {
			text = chat.getText();
		}else {
			text = "--error--";
		}
		chat.setResponse(text);
		return chat;
	}
}
