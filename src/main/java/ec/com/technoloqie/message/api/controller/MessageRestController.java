/*
 * Copyright (c) 2012-2024 Technoloqie Inc. or its affiliates, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.com.technoloqie.message.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.com.technoloqie.message.api.commons.log.MessageLog;
import ec.com.technoloqie.message.api.dto.ChatDto;
import ec.com.technoloqie.message.api.service.IChatBotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * Controlador principal que expone el servicio a trav&eacute;s de HTTP/Rest para
 * las operaciones del recurso de mensajer&#237;a<br/>
 * <b>Class</b>: MessageRestController<br/>
 * <b>Company</b>:Technoloqie.<br/>
 *
 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>dvasquez</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>Jan 19, 2024 Creaci&oacute;n de Clase.</li>
 * </ul>
 * @version 0.1
 */
@CrossOrigin(origins = {"${ec.com.technoloqie.chatbot.app.url}"})
@RestController
@Tag(name = "Message API", description="Microservicio de mensajer&#237;a permite crear f&#225;cilmente programas que usan mensajes para una interfaz.")
@RequestMapping("${ec.com.technoloqie.message.api.prefix}/messages")
public class MessageRestController {
	
	private IChatBotService chatbotservice;
	@Value("${spring.application.name}")
	private String appName;
	
	public MessageRestController(IChatBotService chatbotservice){
		this.chatbotservice = chatbotservice;
	}
	
	@GetMapping
    public String getMessage() {
        return String.format("Now this finally works out. Welcome %s",appName);
    }
	
	@Operation(
			summary = "Utilizado cada vez que alguien env\u00eda un mensaje privado a tu bot",
			description = "Create a ChatDto object. The response is Response object with message, sucess and data.",
			tags = { "send"})
	@ApiResponses({
		@ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = ChatDto.class), mediaType = "application/json") }),
		@ApiResponse(responseCode = "401", description = "Un-Authorized user", content = {@Content(schema = @Schema(hidden = true))}),
		@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
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
			chatNew = chatbotservice.getChatMessage(chat);
		}catch(Exception e) {
			MessageLog.getLog().error("Error al momento de crear chat.");
			response.put("message", "Error al momento de crear chat");
			response.put("error", e.getMessage() +" : " + e.getMessage());
			response.put("success", Boolean.FALSE);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Mensaje enviado correcto");
		response.put("data", chatNew);
		response.put("success", Boolean.TRUE);
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}

}
