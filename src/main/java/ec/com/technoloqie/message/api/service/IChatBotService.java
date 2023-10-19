package ec.com.technoloqie.message.api.service;

import ec.com.technoloqie.message.api.commons.exception.MessageException;
import ec.com.technoloqie.message.api.model.ChatDto;

public interface IChatBotService {

	ChatDto getChatMessage(String message) throws MessageException;
	
}
