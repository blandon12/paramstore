package com.aws.paramstore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

@Component
public class FdcParameterStoreEnvironment {

  private ConfigurableEnvironment env;

  @Value("${parameter.store.prefix}")
  private String prefix;

  public FdcParameterStoreEnvironment(ConfigurableEnvironment env) {
    this.env = env;
  }

  public String getProperty(String key) {
    if (!key.startsWith(prefix)) {
      return null;
    }

    try {
      return env.getProperty(key);
    } catch (Exception e) {
      return null;
    }
  }
}
