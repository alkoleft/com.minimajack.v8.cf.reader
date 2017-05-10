package com.minimajack.v8.metadata.configuration.sections.businessprocesses;

import com.minimajack.v8.annotation.V8Class;
import com.minimajack.v8.metadata.external.transformer.MetadataSection;

import java.util.List;
import java.util.UUID;

@V8Class
public class Tasks extends MetadataSection {

  public UUID type;
  public List<UUID> uuids;
}
