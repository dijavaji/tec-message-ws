package ec.com.technoloqie.message.api.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Profile("!production")
@Configuration
public class SwaggerConfig {
	
	//private static final String SECURITY_SCHEME_NAME = "Bearer oAuth Token";
	
	@Bean
	public OpenAPI customOpenAPI(@Value("${spring.application.name}") String appName) {
		return new OpenAPI()
				/*.addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(
                        new Components()
                                .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                        new SecurityScheme()
                                                .name(SECURITY_SCHEME_NAME)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )*/
				.info(new Info().title("API REST Documentation for "+ appName +", by technoloqie" ).version("1.0")
						.description("Servicios REST de microservicios publicados en formato JSON"
								+ " para el modulo de " + appName)
						.termsOfService("https://www.technoloqie.website/terms-of-service")
						.license(new License().name("Apache License").url("https://www.apache.org/licenses/")));
	}
}
