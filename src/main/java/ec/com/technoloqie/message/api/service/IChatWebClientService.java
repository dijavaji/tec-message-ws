package ec.com.technoloqie.message.api.service;

import ec.com.technoloqie.message.api.commons.exception.MessageException;
import ec.com.technoloqie.message.api.dto.ChatDto;

public interface IChatWebClientService {
	
	ChatDto getResponseChat(ChatDto chat) throws MessageException;;

}
