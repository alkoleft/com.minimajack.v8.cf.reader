package com.minimajack.v8.metadata.definedtypes;

import com.minimajack.v8.annotation.V8Class;
import com.minimajack.v8.metadata.external.PatternDescription;
import com.minimajack.v8.metadata.external.common.V8MetaShortName;

import java.util.UUID;

@V8Class
public class DefinedTypesBlock {

  public Integer version;
  public UUID unk1;
  public UUID unk2;
  public V8MetaShortName v8mn;
  public PatternDescription pd;
}
