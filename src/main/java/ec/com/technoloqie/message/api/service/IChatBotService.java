package ec.com.technoloqie.message.api.service;

import ec.com.technoloqie.message.api.commons.exception.MessageException;
import ec.com.technoloqie.message.api.dto.ChatDto;

public interface IChatBotService {

	ChatDto getChatMessage(ChatDto message) throws MessageException;
	
}
