package ec.com.technoloqie.message.api.dao;

import ec.com.technoloqie.message.api.dto.ChatDto;

public interface IProcessChatDao {
	
	ChatDto getResponseChat(ChatDto chat);
}
