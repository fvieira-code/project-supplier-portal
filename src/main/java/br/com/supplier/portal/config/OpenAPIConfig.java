package br.com.supplier.portal.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

  @Value("${project-java-backend.openapi.dev-url}")
  private String devUrl;

  /*@Value("${agendarc.openapi.prod-url}")
  private String prodUrl;*/

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("Servidor de URL em Ambiente de Desenvolvimento");

    /*Server prodServer = new Server();
    prodServer.setUrl(prodUrl);
    prodServer.setDescription("Servidor de URL em Ambiente de Produção");*/

    Contact contact = new Contact();
    contact.setEmail("agendarc@gmail.com");
    contact.setName("Agenda de Espaço - ATI-TO");
    contact.setUrl("https://www.to.gov.br/cge/agencia-de-tecnologia-da-informacao-ati");

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info = new Info()
        .title("API Agendamento de Espaço - ATI-TO")
        .version("1.0")
        .contact(contact)
        .description("API de endpoints da Agenda de Espaços - ATI-TO")
            .termsOfService("https://www.to.gov.br/cge/agencia-de-tecnologia-da-informacao-ati/terms")
        .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(devServer)); //, prodServer));
  }
}
