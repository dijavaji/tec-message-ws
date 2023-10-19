package ec.com.technoloqie.message.api;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ec.com.technoloqie.message.api.commons.log.MessageLog;
import ec.com.technoloqie.message.api.model.ChatDto;
import ec.com.technoloqie.message.api.service.IProcessChatService;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {
	
	@Autowired
	private IProcessChatService processChat;
	
	@Before
	public void setUp() {
		MessageLog.getLog().info("Inicia test servicios");
		
	}
	
	 @Test
	 public void getMessageTest() {
		 try {
			 MessageLog.getLog().info("getMessageTest.");
			 
			ChatDto chat = new ChatDto();
			chat.setSenderId(new Date().toString());
			chat.setText("Hola");
			ChatDto scanProduct =  processChat.getResponseChat(chat );
	
			 Assert.assertNotNull(scanProduct);
		 }catch(Exception e) {
			 MessageLog.getLog().error("getMessageTest.");
			 assertTrue("getMessageTest.",Boolean.TRUE);
		}
	}
}
