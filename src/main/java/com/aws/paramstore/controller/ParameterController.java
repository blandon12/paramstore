package com.aws.paramstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParameterController {

//  @Value("${/config/demo/dynamodb}")
//  String dynamodb;
//
//  @Value("${/config/demo/s3bucket}")
//  String s3bucket;
//
//  @GetMapping("/health")
//  public String checkHealth() {
//    return "Parameter dynamodb is " + dynamodb + " s3 is " + s3bucket;
//  }
  @Autowired
  ConfigurableEnvironment env;

  @GetMapping("/parameters")
  public String getParameters() {
    for (PropertySource<?> propertySource : env.getPropertySources()) {
      System.out.println(propertySource.getName());
      if (propertySource.getName().equals("AWSParameterStorePropertySource")) {


        
        CompositePropertySource bootstrap = (CompositePropertySource) propertySource;
        for (PropertySource<?> nestedSource : bootstrap.getPropertySources()) {
          EnumerablePropertySource eps = (EnumerablePropertySource) nestedSource;
          System.out.println(eps.getName() + ":");
          for (String propName : eps.getPropertyNames()) {
            System.out.println("\t" + propName + " = " + eps.getProperty(propName));
          }
        }
      }
    }

    return "ok";
  }
}
