package com.minimajack.v8.metadata.external.fields;

import java.util.List;
import java.util.UUID;

import com.minimajack.v8.annotation.V8Class;
import com.minimajack.v8.metadata.external.TypeDescription;
import com.minimajack.v8.metadata.external.transformer.MetadataSection;

@V8Class
public class V8MetaTypesSection
    extends MetadataSection
{
    public UUID type;

    public List<TypeDescription> descr;

}
