import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI dogsOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Dogs Api")
                .description("dogs sample application").version("v0.0.1")
                .license(new License().name("Apache    2.0").url("http://localhost:8080/license")))
            .externalDocs(new ExternalDocumentation().description("dogs wiki documentation")
                .url("http://localhost:8080/dog/wiki"));
    }
}
