package com.aws.paramstore.controller;

import com.aws.paramstore.config.FdcParameterStoreEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParameterController {

  @Value("${/config/paramstore/rds}")
  String rds;

  @Value("${/config/demo/s3bucket}")
  String s3bucket;

  @Value("${parameter.store.rds}")
  String rdsKey;

  @Autowired
  FdcParameterStoreEnvironment fdcEnv;

  @GetMapping("/health")
  public String checkHealth() {
    return "Parameter rds is " + rds + " s3 is " + s3bucket;
  }

  @GetMapping("/parameters")
  public String getParameters(@RequestParam String key) {

    System.out.println(fdcEnv.getProperty(rdsKey));

    return fdcEnv.getProperty(key);
  }
}
