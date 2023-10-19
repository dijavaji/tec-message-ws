package ec.com.technoloqie.message.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import ec.com.technoloqie.message.api.commons.exception.MessageException;
import ec.com.technoloqie.message.api.commons.log.MessageLog;
import ec.com.technoloqie.message.api.model.ChatDto;
import ec.com.technoloqie.message.api.service.IChatBotService;

@Service
public class ChatBotServiceImpl implements IChatBotService{
	
	@Autowired
	//private RestTemplate restTemplate;
	private WebClient webClient;

	@Override
	public ChatDto getChatMessage(String message) throws MessageException {
		MessageLog.getLog().info("Starting web client "+ message);
		ChatDto chatDto = null;
		try {
			ChatDto msg = new ChatDto();
			msg.setText(message);
			chatDto = webClient.post()
				      .uri("http://127.0.0.1:8080/api/messages/send/")
				      .contentType(MediaType.APPLICATION_JSON)
				      .bodyValue(msg)
				      .retrieve()
				      .bodyToMono(ChatDto.class).block();;

				    //tweetFlux.subscribe(tweet -> log.info(tweet.toString()));
		}catch(Exception e) {
			MessageLog.getLog().info("Error al momento de preguntar al chatbot "+ e);
			throw new MessageException("Error al momento de preguntar al chatbot",e);
		}
	    MessageLog.getLog().info("Exiting NON-BLOCKING Controller!");
	    return chatDto;
	}
	
	

}
