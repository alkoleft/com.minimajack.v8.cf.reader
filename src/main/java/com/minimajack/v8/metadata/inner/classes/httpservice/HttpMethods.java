package com.minimajack.v8.metadata.inner.classes.httpservice;

import com.minimajack.v8.annotation.V8Class;
import com.minimajack.v8.metadata.httpservice.HttpMethodMetaData;
import com.minimajack.v8.metadata.inner.classes.V8InnerClass;

import java.util.List;
import java.util.UUID;

@V8Class
public class HttpMethods extends V8InnerClass {

  public UUID type;
  public List<HttpMethodMetaData> items;
}
