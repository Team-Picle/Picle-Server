package gaedianz.org.Picle.global.config.swagger;

import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {
    private final static String TITLE = "Picle API Server";
    private final static String DESCRIPTION = "Picle API Docs";
    private final static String VERSION = "0.0.1";

    @Bean
    public OpenAPI openAPI() {
        Server serverLocal = createServer("http://localhost:8080", "for local usages");
        Server serverDev = createServer("https://picle.p-e.kr", "for dev usages");

        Info info = new Info()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION);

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer Token");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer Token", securityScheme))
                .security(Arrays.asList(securityRequirement))
                .servers(List.of(serverLocal, serverDev))
                .info(info);
    }

    private Server createServer(String url, String description) {
        Server server = new Server();
        server.setUrl(url);
        server.setDescription(description);

        return server;
    }
}
