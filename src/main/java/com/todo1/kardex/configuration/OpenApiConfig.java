package com.todo1.kardex.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info =
        @Info(
            title = "kardex-api",
            version = "v1",
            description = "API for kardex app",
            contact =
                @Contact(
                    name = "Edisson Andrés Garcia",
                    email = "eagarciah707@gmail.com",
                    url = "https://www.linkedin.com/in/edisson-andres-garcia-herrera-63a91517b"),
            license =
                @License(
                    name = "Apache 2.0",
                    url = "http://www.apache.org/licenses/LICENSE-2.0.html")),
    servers = {@Server(url = "http://localhost:8080/todo1-kardex/v1")})
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer")
public class OpenApiConfig {}
