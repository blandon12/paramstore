package com.aws.paramstore.config;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.ParameterNotFoundException;
import org.springframework.core.env.PropertySource;

public class ParameterStorePropertySource extends PropertySource<AWSSimpleSystemsManagement> {
  public ParameterStorePropertySource(String name, AWSSimpleSystemsManagement source) {
    super(name, source);
  }

  @Override
  public Object getProperty(String propertyName) {

    System.out.println("#####" + propertyName);

    if (name.startsWith("/") && propertyName.startsWith("/config/paramstore")) {
      try {
        return source.getParameter(new GetParameterRequest().withName(propertyName)
                .withWithDecryption(true))
                .getParameter()
                .getValue();
      } catch (ParameterNotFoundException e) {
      }
    }
    return null;
  }
}
