package ec.com.technoloqie.message.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.technoloqie.message.api.model.Intent;

public interface IIntentDao extends JpaRepository<Intent, Integer>{
	
	Intent findOneByQuery(String text);

}
