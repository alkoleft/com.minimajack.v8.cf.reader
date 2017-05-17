package com.minimajack.v8.metadata.inner.classes.attributes.accounting;

import com.minimajack.v8.annotation.V8Class;
import com.minimajack.v8.metadata.attributes.resource.accounting.AccountingResourceDescription;
import com.minimajack.v8.metadata.inner.classes.V8InnerClass;

import java.util.List;
import java.util.UUID;

@V8Class
public class AccountingResourceList extends V8InnerClass {

  public UUID type;

  public List<AccountingResourceDescription> descr;

}