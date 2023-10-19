package ec.com.technoloqie.message.api.service;

import ec.com.technoloqie.message.api.model.Intent;

public interface IIntentService {
	
	//Account createAccount(Account account);
	Intent getAccountId(Integer code);
	//Account updateAccount(Account account, int id);
	void deleteAccount(Integer code);
	//List<Intent> getListAccounts();
	Intent findOneByName(Integer code);

}
