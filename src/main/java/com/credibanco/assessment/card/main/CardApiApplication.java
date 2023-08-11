package com.credibanco.assessment.card.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.credibanco.assessment.card"})
@EntityScan(basePackages = "com.credibanco.assessment.card.model")
@EnableJpaRepositories(basePackages = "com.credibanco.assessment.card.repository")
public class CardApiApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
    return application.sources(CardApiApplication.class);
  }

  public static void main(final String[] args) {
    SpringApplication.run(CardApiApplication.class, args);
  }
}
