package ec.com.technoloqie.message.api.service;

import ec.com.technoloqie.message.api.dto.ChatDto;

public interface IProcessChatService {
	
	ChatDto getResponseChat(ChatDto chat);

}
