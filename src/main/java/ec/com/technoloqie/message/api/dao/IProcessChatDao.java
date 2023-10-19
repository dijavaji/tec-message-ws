package ec.com.technoloqie.message.api.dao;

import ec.com.technoloqie.message.api.model.ChatDto;

public interface IProcessChatDao {
	
	ChatDto getResponseChat(ChatDto chat);
}
