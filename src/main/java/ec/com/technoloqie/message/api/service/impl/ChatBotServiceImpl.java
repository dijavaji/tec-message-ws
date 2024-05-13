package ec.com.technoloqie.message.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import ec.com.technoloqie.message.api.commons.exception.MessageException;
import ec.com.technoloqie.message.api.commons.log.MessageLog;
import ec.com.technoloqie.message.api.dto.ChatDto;
import ec.com.technoloqie.message.api.dto.ResponseChatDto;
import ec.com.technoloqie.message.api.service.IChatBotService;

@Service
public class ChatBotServiceImpl implements IChatBotService{
	
	@Autowired
	//private RestTemplate restTemplate;
	private WebClient webClient;

	@SuppressWarnings("static-access")
	@Override
	public ChatDto getChatMessage(String message) throws MessageException {
		MessageLog.getLog().info("Starting web client "+ message);
		ChatDto chatDto = null;
		try {
			ChatDto msg = new ChatDto();
			msg.setText(message);
			ResponseChatDto responseOut = webClient.post().uri("http://127.0.0.1:8080/api/v1/messages/chat/")
					.contentType(MediaType.APPLICATION_JSON).bodyValue(msg).retrieve()
					.bodyToMono(ResponseChatDto.class).block();

			// tweetFlux.subscribe(tweet -> log.info(tweet.toString()));
			MessageLog.getLog().info("respuesta"+ responseOut.getData());
			//ObjectMapper objectMapper = new ObjectMapper();
			//String respData = objectMapper.writeValueAsString(responseOut.getData().toString());
			//Map respMap = objectMapper.readValue(respData, Map.class);
			//chatDto.setText(respMap.get("text").toString());
			//chatDto = objectMapper.readValue(responseOut.getData().toString(), ChatDto.class);
			//chatDto = objectMapper.reader().forType(ChatDto.class).readValue(respData);
			//Map<String, Object> map = objectMapper.readValue("", new TypeReference<Map<String,Object>>(){});
			chatDto = (ChatDto) responseOut.getData();
		}catch(Exception e) {
			MessageLog.getLog().info("Error al momento de preguntar al chatbot "+ e);
			throw new MessageException("Error al momento de preguntar al chatbot",e);
		}
	    MessageLog.getLog().info("Exiting NON-BLOCKING Controller!");
	    return chatDto;
	}
	
	

}
