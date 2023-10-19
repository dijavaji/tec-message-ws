package ec.com.technoloqie.message.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.com.technoloqie.message.api.commons.log.MessageLog;
import ec.com.technoloqie.message.api.model.ChatDto;
import ec.com.technoloqie.message.api.service.IProcessChatService;


//@CrossOrigin(origins = {"http://127.0.0.1:4200"})
//@RestController
//@RequestMapping("/api")
public class MessageRestController {
	
	@Autowired
	private IProcessChatService processService;
	
	//@PostMapping("/messages")
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createMessage(@Valid @RequestBody ChatDto chat, BindingResult result) {
		ChatDto chatNew = null;
		Map <String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream().map(err -> "El campo " + err.getField() +" "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			chatNew = processService.getResponseChat(chat);
		}catch(DataAccessException e) {
			MessageLog.getLog().error("Error al momento de crear chat.");
			response.put("mensaje", "Error al momento de crear chat");
			response.put("error", e.getMessage() +" : " + e.getMostSpecificCause());
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Mensaje enviado correcto");
		response.put("data", chatNew);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}

}
