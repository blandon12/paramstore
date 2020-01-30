package com.aws.paramstore.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
