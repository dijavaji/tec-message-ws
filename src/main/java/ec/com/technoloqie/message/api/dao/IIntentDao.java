package ec.com.technoloqie.message.api.dao;

import ec.com.technoloqie.message.api.model.Intent;

public interface IIntentDao {
	
	Intent findOneByQuery(String text);

}
