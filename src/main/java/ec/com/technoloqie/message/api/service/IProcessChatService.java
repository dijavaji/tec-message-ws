package ec.com.technoloqie.message.api.service;

import ec.com.technoloqie.message.api.model.ChatDto;

public interface IProcessChatService {
	
	ChatDto getResponseChat(ChatDto chat);

}
