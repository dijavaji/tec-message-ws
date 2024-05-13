package ec.com.technoloqie.message.api.dto;

import ec.com.technoloqie.message.api.dto.ResponseFormatOut.Error;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseChatDto {
	
		private Boolean success;

		private String message;

		private ChatDto data;

		private Error error;

		private String traceid;
}

