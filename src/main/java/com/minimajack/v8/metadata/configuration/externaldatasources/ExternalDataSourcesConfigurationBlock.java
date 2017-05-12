package com.minimajack.v8.metadata.configuration.externaldatasources;

import com.minimajack.v8.annotation.V8Class;
import com.minimajack.v8.metadata.innerclass.V8InnerClass;

import java.util.List;

@V8Class
public class ExternalDataSourcesConfigurationBlock {

  public Integer version;

  public ExternalDataSourcesConfigurationBlockInfo info;

  public List<V8InnerClass> sections;
}