package ec.com.technoloqie.message.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TecMessageWsApplication {
	
	@Bean
	WebClient webClient() {
		return WebClient.builder().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(TecMessageWsApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doAfterStartup() {
		log.info("The Message Application has started");
	}

}
