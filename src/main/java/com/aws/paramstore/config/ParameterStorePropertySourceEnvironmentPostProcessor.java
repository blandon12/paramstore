package com.aws.paramstore.config;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class ParameterStorePropertySourceEnvironmentPostProcessor implements EnvironmentPostProcessor {
  @Override
  public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
    environment.getPropertySources()
            .addLast(new ParameterStorePropertySource("AWSParameterStorePropertySource",
                    AWSSimpleSystemsManagementClientBuilder.defaultClient()));
  }
}
