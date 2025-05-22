package ec.com.technoloqie.message.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import ec.com.technoloqie.message.api.commons.exception.MessageException;
import ec.com.technoloqie.message.api.commons.log.MessageLog;
import ec.com.technoloqie.message.api.dto.ChatDto;
import ec.com.technoloqie.message.api.dto.ResponseChatDto;
import ec.com.technoloqie.message.api.service.IChatWebClientService;
import reactor.core.publisher.Mono;

@Service
public class ChatWebClientServiceImpl implements IChatWebClientService{
	
	@Value("${ec.com.technoloqie.chatbot.api.url}")
	private String chatbotApi;
	
	@Autowired
	private WebClient webClient;
	//private RestTemplate restTemplate;

	@Override
	public ChatDto getResponseChat(ChatDto chat) throws MessageException {
		MessageLog.getLog().info("Starting web client. "+ chat.getText());
		ChatDto chatDto = null;
		try {
			ResponseChatDto responseOut = webClient.post().uri(chatbotApi + "/chat")
					.contentType(MediaType.APPLICATION_JSON).bodyValue(chat).retrieve()
					.onStatus(HttpStatus.NOT_FOUND::equals,
						    response -> {
						    				//ResponseChatDto errResponse = response.bodyToMono(ResponseChatDto.class).block();
						    				String errMsg = "El servidor no pudo encontrar el contenido solicitado.";
						    				//response.bodyToMono(ResponseChatDto.class).map(m -> {  MessageLog.getLog().info(m.getError().getDetail()); return m;});
						    				//MessageLog.getLog().info();
						    				//response.bodyToMono(String.class).block()
						    				//response.bodyToMono(String.class).map(MessageException::new);
						    				return  Mono.error(new MessageException(errMsg));}
						      /*HttpStatus::isError,
						      response ->{ MessageLog.getLog().info("response"+ response);  return Mono.error(new MessageException("Maybe not an error?"));}
						       switch (response.rawStatusCode()) {
						          case 400 -> Mono.error(new BadRequestException("bad request made"));
						          case 401, 403 -> Mono.error(new Exception("auth error"));
						          case 404 -> Mono.error(new Exception("Maybe not an error?"));
						          case 500 -> Mono.error(new Exception("server error"));
						          default -> Mono.error(new Exception("something went wrong"));
						        }*/
						      
						    )
					.bodyToMono(ResponseChatDto.class).block();

			// tweetFlux.subscribe(tweet -> log.info(tweet.toString()));
			MessageLog.getLog().info("respuesta "+ responseOut.getData().getStatus());
			//ObjectMapper objectMapper = new ObjectMapper();
			//String respData = objectMapper.writeValueAsString(responseOut.getData().toString());
			//Map respMap = objectMapper.readValue(respData, Map.class);
			//chatDto.setText(respMap.get("text").toString());
			//chatDto = objectMapper.readValue(responseOut.getData().toString(), ChatDto.class);
			//chatDto = objectMapper.reader().forType(ChatDto.class).readValue(respData);
			//Map<String, Object> map = objectMapper.readValue("", new TypeReference<Map<String,Object>>(){});
			chatDto = responseOut.getData();
		} catch(Exception e) {
			MessageLog.getLog().error("Error al momento de preguntar al chatbot ", e);
			throw new MessageException("Error al momento de preguntar al chatbot",e);
		}
	    MessageLog.getLog().info("Exiting NON-BLOCKING Controller!");
	    return chatDto;
	}

}
