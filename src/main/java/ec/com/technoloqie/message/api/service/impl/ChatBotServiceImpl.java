package ec.com.technoloqie.message.api.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import ec.com.technoloqie.message.api.commons.MessagesConstants;
import ec.com.technoloqie.message.api.commons.exception.MessageException;
import ec.com.technoloqie.message.api.dto.ChatDto;
import ec.com.technoloqie.message.api.service.IChatBotService;
import ec.com.technoloqie.message.api.service.IChatWebClientService;

@Service
public class ChatBotServiceImpl implements IChatBotService{
	
	private IChatWebClientService chatWebClientService;
	
	public ChatBotServiceImpl(IChatWebClientService chatWebClientService) {
		this.chatWebClientService=chatWebClientService;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public ChatDto getChatMessage(ChatDto msg) throws MessageException {
		msg.setCreatedDate(new Date());
		msg.setCreatedBy(msg.getCreatedBy()!=null ? msg.getCreatedBy() : MessagesConstants.AUDIT_APP_CREATED_BY);
		return this.chatWebClientService.getResponseChat(msg);
	}
	
	

}
