package az.edu.ada.wm2.studentservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Tələbə Xidməti API-si",
                version = "v1",
                description = "Tələbələrin idarə edilməsi və ad üzrə axtarışı üçün API.",
                contact = @Contact(name = "WM2 Backend Course"),
                license = @License(name = "Tədris məqsədli istifadə")
        ),
        servers = {
                @Server(url = "http://localhost:9090", description = "Lokal server")
        }
)
public class OpenApiConfig {
}