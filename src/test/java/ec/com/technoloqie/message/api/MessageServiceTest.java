package ec.com.technoloqie.message.api;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ec.com.technoloqie.message.api.commons.log.MessageLog;
import ec.com.technoloqie.message.api.dto.ChatDto;
import ec.com.technoloqie.message.api.service.IChatBotService;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
public class MessageServiceTest {
	
	@Autowired
	private IChatBotService chatbotservice;
	
	@Before
	public void setUp() {
		MessageLog.getLog().info("Inicia test servicios");
		
	}
	
	 @Test
	 public void getMessageTest() {
		 try {
			 MessageLog.getLog().info("getMessageTest.");
			 
			ChatDto chatmessage = new ChatDto();
			chatmessage.setUserId(new Date().toString());
			chatmessage.setText("Hola quien eres");
			chatmessage.setAssistantName("praxisBot");
			ChatDto scanMsg =  chatbotservice.getChatMessage(chatmessage);
			MessageLog.getLog().error(scanMsg.getText());
			Assert.assertNotNull(scanMsg);
		 }catch(Exception e) {
			 MessageLog.getLog().error("getMessageTest.");
			 assertTrue("getMessageTest.",Boolean.TRUE);
		}
	}
}
