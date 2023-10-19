package ec.com.technoloqie.message.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.technoloqie.message.api.dao.IProcessChatDao;
import ec.com.technoloqie.message.api.model.ChatDto;
import ec.com.technoloqie.message.api.service.IProcessChatService;

@Service
public class ProcessChatServiceImpl implements IProcessChatService{
	
	@Autowired
	private IProcessChatDao processDao;

	/*@Override
	@Transactional
	public Account createAccount(Account account) {
		return this.accountDao.save(account);
	}*/

	@Override
	@Transactional(readOnly = true)
	public ChatDto getResponseChat(ChatDto chat) {
		return this.processDao.getResponseChat(chat);
	}
	
	

}
