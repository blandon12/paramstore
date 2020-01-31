package com.aws.paramstore.controller;

import com.coveo.configuration.parameterstore.ParameterStorePropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
public class ParameterController {

  @Value("${/config/demo/dynamodb}")
  String dynamodb;

  @Value("${/config/demo/s3bucket}")
  String s3bucket;

  @GetMapping("/health")
  public String checkHealth() {
    return "Parameter dynamodb is " + dynamodb + " s3 is " + s3bucket;
  }

  @Autowired
  ConfigurableEnvironment env;

  @GetMapping("/parameters")
  public String getParameters() {

    String param = env.getProperty("/config/demo/dynamodb");
    System.out.println(param);

    for (PropertySource<?> propertySource : env.getPropertySources()) {
      if (propertySource.getName().equals("AWSParameterStorePropertySource")) {

        ParameterStorePropertySource source = (ParameterStorePropertySource) propertySource;

        System.out.println(propertySource.getName());
        System.out.println(source.getProperty("/config/paramstore/rds"));
        System.out.println(source.getProperty("/config"));
      }
    }

    return param;
  }
}
