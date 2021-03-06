package com.minimajack.v8.code.impl;

import com.minimajack.v8.code.ProjectTreeSearcher;
import com.minimajack.v8.metadata.V8MetaData;
import com.minimajack.v8.metadata.external.forms.FormDescription;
import com.minimajack.v8.metadata.external.qualifier.Qualifiers;
import com.minimajack.v8.metadata.external.qualifier.QualityTransformer;
import com.minimajack.v8.metadata.external.template.TemplateDescription;
import com.minimajack.v8.metadata.external.type.TypeValue;
import com.minimajack.v8.metadata.external.type.TypesTransformer;
import com.minimajack.v8.metadata.inner.classes.V8ClassObject;
import com.minimajack.v8.metadata.inner.classes.V8InnerClass;
import com.minimajack.v8.metadata.inner.classes.attributes.AttributesList;
import com.minimajack.v8.metadata.inner.classes.configuration.accounting.AccountingConfiguraionMetaData;
import com.minimajack.v8.metadata.inner.classes.configuration.accounting.AccountingRegisters;
import com.minimajack.v8.metadata.inner.classes.configuration.accounting.ChartsOfAccounts;
import com.minimajack.v8.metadata.inner.classes.configuration.businessprocesses.BusinessProcesses;
import com.minimajack.v8.metadata.inner.classes.configuration.businessprocesses.BusinessProcessesConfiguraionMetaData;
import com.minimajack.v8.metadata.inner.classes.configuration.businessprocesses.Tasks;
import com.minimajack.v8.metadata.inner.classes.configuration.calculation.CalculationConfiguraionMetaData;
import com.minimajack.v8.metadata.inner.classes.configuration.calculation.CalculationRegisters;
import com.minimajack.v8.metadata.inner.classes.configuration.calculation.ChartsOfCalculation;
import com.minimajack.v8.metadata.inner.classes.configuration.common.CommonAttributes;
import com.minimajack.v8.metadata.inner.classes.configuration.common.CommonConfiguraionMetaData;
import com.minimajack.v8.metadata.inner.classes.configuration.common.CommonModules;
import com.minimajack.v8.metadata.inner.classes.configuration.common.CommonPictures;
import com.minimajack.v8.metadata.inner.classes.configuration.common.CommonTemplates;
import com.minimajack.v8.metadata.inner.classes.configuration.common.DefinedTypes;
import com.minimajack.v8.metadata.inner.classes.configuration.common.EventSubscriptions;
import com.minimajack.v8.metadata.inner.classes.configuration.common.ExchangePlans;
import com.minimajack.v8.metadata.inner.classes.configuration.common.FilterCriteria;
import com.minimajack.v8.metadata.inner.classes.configuration.common.FunctionalOptionParams;
import com.minimajack.v8.metadata.inner.classes.configuration.common.FunctionalOptions;
import com.minimajack.v8.metadata.inner.classes.configuration.common.HttpServices;
import com.minimajack.v8.metadata.inner.classes.configuration.common.Interfaces;
import com.minimajack.v8.metadata.inner.classes.configuration.common.Languages;
import com.minimajack.v8.metadata.inner.classes.configuration.common.Roles;
import com.minimajack.v8.metadata.inner.classes.configuration.common.ScheduledJobs;
import com.minimajack.v8.metadata.inner.classes.configuration.common.SessionParams;
import com.minimajack.v8.metadata.inner.classes.configuration.common.SettingsStorages;
import com.minimajack.v8.metadata.inner.classes.configuration.common.StyleItems;
import com.minimajack.v8.metadata.inner.classes.configuration.common.Styles;
import com.minimajack.v8.metadata.inner.classes.configuration.common.Subsystems;
import com.minimajack.v8.metadata.inner.classes.configuration.common.WebServices;
import com.minimajack.v8.metadata.inner.classes.configuration.common.WsReferences;
import com.minimajack.v8.metadata.inner.classes.configuration.common.XdtoPackages;
import com.minimajack.v8.metadata.inner.classes.configuration.externaldatasources.ExternalDataSources;
import com.minimajack.v8.metadata.inner.classes.configuration.externaldatasources.ExternalDataSourcesConfiguraionMetaData;
import com.minimajack.v8.metadata.inner.classes.configuration.main.AccumulationRegisters;
import com.minimajack.v8.metadata.inner.classes.configuration.main.Catalogs;
import com.minimajack.v8.metadata.inner.classes.configuration.main.ChartsOfCharacteristicTypes;
import com.minimajack.v8.metadata.inner.classes.configuration.main.CommandGroups;
import com.minimajack.v8.metadata.inner.classes.configuration.main.CommonCommands;
import com.minimajack.v8.metadata.inner.classes.configuration.main.CommonForms;
import com.minimajack.v8.metadata.inner.classes.configuration.main.Constants;
import com.minimajack.v8.metadata.inner.classes.configuration.main.DataProcessors;
import com.minimajack.v8.metadata.inner.classes.configuration.main.DocumentJournals;
import com.minimajack.v8.metadata.inner.classes.configuration.main.DocumentNumerators;
import com.minimajack.v8.metadata.inner.classes.configuration.main.Documents;
import com.minimajack.v8.metadata.inner.classes.configuration.main.Enums;
import com.minimajack.v8.metadata.inner.classes.configuration.main.InformationRegisters;
import com.minimajack.v8.metadata.inner.classes.configuration.main.MainConfiguraionMetaData;
import com.minimajack.v8.metadata.inner.classes.configuration.main.Reports;
import com.minimajack.v8.metadata.inner.classes.configuration.main.Sequences;
import com.minimajack.v8.metadata.inner.classes.externaldataprocessor.ExternalDataProcessorMetaData;
import com.minimajack.v8.metadata.inner.classes.forms.FormList;
import com.minimajack.v8.metadata.inner.classes.tabular.TabularList;
import com.minimajack.v8.metadata.inner.classes.template.TemplateList;
import com.minimajack.v8.metadata.inner.classes.transformer.InnerClassTransformer;
import com.minimajack.v8.metadata.root.V8Root;
import com.minimajack.v8.metadata.style.item.font.FontDescription;
import com.minimajack.v8.metadata.style.item.font.FontTransformer;
import com.minimajack.v8.project.FileType;
import com.minimajack.v8.project.Project;
import com.minimajack.v8.project.ProjectTree;
import com.minimajack.v8.utility.V8Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class MetadataProcessor extends ProjectTreeSearcher {

  private final Path path;

  final Logger logger = LoggerFactory.getLogger(MetadataProcessor.class);

  private static final String METADATA_FILE = "metadata.mdo";

  private static final String TEMPLATES_PATH = "Templates";

  private static final String FORM_PATH = "Forms";

  private static final String LANGUAGES_PATH = "Languages";

  private static final String SUBSYSTEM_PATH = "Subsystems";

  private static final String SESSION_PARAMS_PATH = "SessionParams";

  private static final String COMMON_MODULES_PATH = "CommonModules";

  private static final String ROLES_PATH = "Roles";

  private static final String COMMON_ATTRIBUTES_PATH = "CommonAttributes";

  private static final String EXCHANGE_PLANS_PATH = "ExchangePlans";

  private static final String FILTER_CRITERIA_PATH = "FilterCriteria";

  private static final String EVENT_SUBSCRIPTIONS_PATH = "EventSubscriptions";

  private static final String SCHEDULED_JOBS_PATH = "ScheduledJobs";

  private static final String FUNCTIONAL_OPTIONS_PATH = "FunctionalOptions";

  private static final String FUNCTIONAL_OPTIONS_PARAMS_PATH = "FunctionalOptionParams";

  private static final String DEFINED_TYPES_PATH = "DefinedTypes";

  private static final String SETTINGS_STORAGES_PATH = "SettingsStorages";

  private static final String COMMON_TEMPLATES_PATH = "CommonTemplates";

  private static final String COMMON_PICTURES_PATH = "CommonPictures";

  private static final String XDTO_PACKAGES_PATH = "XDTOPackages";

  private static final String WEB_SERVICES_PATH = "WebServices";

  private static final String HTTP_SERVICES_PATH = "HTTPServices";

  private static final String WS_REFERENCES_PATH = "WSReferences";

  private static final String STYLE_ITEMS_PATH = "StyleItems";

  private static final String INTERFACES_PATH = "Interfaces";

  private static final String STYLES_PATH = "Styles";

  private static final String CONSTANTS_PATH = "Constants";

  private static final String DOCUMENTS_PATH = "Documents";

  private static final String COMMON_FORMS_PATH = "CommonForms";

  private static final String INFORMATION_REGISTERS_PATH = "InformationRegisters";

  private static final String COMMAND_GROUPS_PATH = "CommandGroups";

  private static final String COMMON_COMMANDS_PATH = "CommonCommands";

  private static final String DOCUMENT_NUMERATORS_PATH = "DocumentNumerators";

  private static final String DOCUMENT_JOURNALS_PATH = "DocumentJournals";

  private static final String REPORTS_PATH = "Reports";

  private static final String CHART_OF_CHARACTERITIC_TYPES_PATH = "ChartsOfCharacteristicTypes";

  private static final String ACCUMULATION_REGISTERS_PATH = "AccumulationRegisters";

  private static final String SEQUENCES_PATH = "Sequences";

  private static final String DATA_PROCESSORS_PATH = "DataProcessors";

  private static final String CATALOGS_PATH = "Catalogs";

  private static final String ENUMS_PATH = "Enums";

  private static final String CHART_OF_ACCOUNTS_PATH = "ChartsOfAccounts";

  private static final String ACCOUNTING_REGISTERS_PATH = "AccountingRegisters";

  private static final String CHART_OF_CALCULATION_PATH = "ChartsOfCalculation";

  private static final String CALCULATION_REGISTERS_PATH = "CalculationRegisters";

  private static final String TASKS_PATH = "Tasks";

  private static final String BUSINESS_PROCESSES_PATH = "BusinessProcesses";

  private static final String EXTERNAL_DATA_SOURCES_PATH = "ExternalDataSources";

  public MetadataProcessor(final Path path) {
    super(path);
    this.path = path;
  }

  {
    new V8Reader();
    V8Reader.init();
    V8Reader.registerTransformer(V8InnerClass.class, new InnerClassTransformer());
    V8Reader.registerTransformer(Qualifiers.class, new QualityTransformer());
    V8Reader.registerTransformer(TypeValue.class, new TypesTransformer());
    V8Reader.registerTransformer(FontDescription.class, new FontTransformer());

  }

  @Override
  public ProjectTree process(final ProjectTree tree) {
    final ByteBuffer rootBuffer = getFileBuffer(tree, "root");
    if (rootBuffer != null) {
      final V8Root root = V8Reader.read(V8Root.class, getFileBuffer(tree, "root"));
      final V8MetaData md =
          V8Reader.read(V8MetaData.class, getFileBuffer(tree, root.guid.toString()));
      for (final V8ClassObject v8Metadata : md.mdd) {
        if (v8Metadata.clazz instanceof ExternalDataProcessorMetaData) {
          processExternalDataProcessor(tree, (ExternalDataProcessorMetaData) v8Metadata.clazz);
        } else if (v8Metadata.clazz instanceof CommonConfiguraionMetaData) {
          processCommonConfigurationMetaData(tree, (CommonConfiguraionMetaData) v8Metadata.clazz);
        } else if (v8Metadata.clazz instanceof MainConfiguraionMetaData) {
          processMainConfigurationMetaData(tree, (MainConfiguraionMetaData) v8Metadata.clazz);
        } else if (v8Metadata.clazz instanceof AccountingConfiguraionMetaData) {
          processAccountingConfiguraionMetaData(tree, (AccountingConfiguraionMetaData) v8Metadata.clazz);
        } else if (v8Metadata.clazz instanceof CalculationConfiguraionMetaData) {
          processCalculationConfiguraionMetaData(tree, (CalculationConfiguraionMetaData) v8Metadata.clazz);
        } else if (v8Metadata.clazz instanceof BusinessProcessesConfiguraionMetaData) {
          processBusinessProcessesConfiguraionMetaData(tree, (BusinessProcessesConfiguraionMetaData) v8Metadata.clazz);
        } else if (v8Metadata.clazz instanceof ExternalDataSourcesConfiguraionMetaData) {
          processExternalDataSourcesConfiguraionMetaData(tree, (ExternalDataSourcesConfiguraionMetaData) v8Metadata.clazz);
        }
      }
    } else {
      this.logger.warn("Can't find root file {}", this.path);
    }
    return tree;
  }

  private void processExternalDataSourcesConfiguraionMetaData(final ProjectTree tree,
      final ExternalDataSourcesConfiguraionMetaData v8MetaData) {
    for (final V8ClassObject section : v8MetaData.innerType.sections) {
      if (section.clazz instanceof ExternalDataSources) {
        processExternalDataSources(tree, (ExternalDataSources) section.clazz);
      } else {
        throw new RuntimeException("NOT IMPLEMENTED " + section.getClass());
      }
    }
  }

  private void processExternalDataSources(final ProjectTree tree,
      final ExternalDataSources v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + EXTERNAL_DATA_SOURCES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());
    }
  }

  private void processBusinessProcessesConfiguraionMetaData(final ProjectTree tree,
      final BusinessProcessesConfiguraionMetaData v8MetaData) {
    for (final V8ClassObject section : v8MetaData.innerType.sections) {
      if (section.clazz instanceof Tasks) {
        processTasks(tree, (Tasks) section.clazz);
      } else if (section.clazz instanceof BusinessProcesses) {
        processBusinessProcesses(tree, (BusinessProcesses) section.clazz);
      } else {
        throw new RuntimeException("NOT IMPLEMENTED " + section.getClass());
      }
    }
  }

  private void processBusinessProcesses(final ProjectTree tree,
      final BusinessProcesses v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + BUSINESS_PROCESSES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());
    }
  }

  private void processTasks(final ProjectTree tree, final Tasks v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + TASKS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());
    }
  }

  private void processCalculationConfiguraionMetaData(final ProjectTree tree,
      final CalculationConfiguraionMetaData v8MetaData) {
    for (final V8ClassObject section : v8MetaData.innerType.sections) {
      if (section.clazz instanceof ChartsOfCalculation) {
        processChartsOfCalculation(tree, (ChartsOfCalculation) section.clazz);
      } else if (section.clazz instanceof CalculationRegisters) {
        processCalculationRegisters(tree, (CalculationRegisters) section.clazz);
      } else {
        throw new RuntimeException("NOT IMPLEMENTED " + section.getClass());
      }
    }
  }

  private void processCalculationRegisters(final ProjectTree tree,
      final CalculationRegisters v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + CALCULATION_REGISTERS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());
    }
  }

  private void processChartsOfCalculation(final ProjectTree tree,
      final ChartsOfCalculation v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + CHART_OF_CALCULATION_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());
    }
  }

  private void processAccountingConfiguraionMetaData(final ProjectTree tree,
      final AccountingConfiguraionMetaData v8MetaData) {
    for (final V8ClassObject section : v8MetaData.innerType.sections) {
      if (section.clazz instanceof ChartsOfAccounts) {
        processChartsOfAccounts(tree, (ChartsOfAccounts) section.clazz);
      } else if (section.clazz instanceof AccountingRegisters) {
        processAccountingRegisters(tree, (AccountingRegisters) section.clazz);
      } else {
        throw new RuntimeException("NOT IMPLEMENTED " + section.getClass());
      }
    }
  }

  private void processAccountingRegisters(final ProjectTree tree,
      final AccountingRegisters v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + ACCOUNTING_REGISTERS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processChartsOfAccounts(final ProjectTree tree, final ChartsOfAccounts v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + CHART_OF_ACCOUNTS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processMainConfigurationMetaData(final ProjectTree tree,
      final MainConfiguraionMetaData v8MetaData) {
    for (final V8ClassObject section : v8MetaData.innerType.mcbi.sections) {
      if (section.clazz instanceof Constants) {
        processConstants(tree, (Constants) section.clazz);
      } else if (section.clazz instanceof Documents) {
        processDocuments(tree, (Documents) section.clazz);
      } else if (section.clazz instanceof CommonForms) {
        processCommonForms(tree, (CommonForms) section.clazz);
      } else if (section.clazz instanceof InformationRegisters) {
        processInformationRegisters(tree, (InformationRegisters) section.clazz);
      } else if (section.clazz instanceof CommandGroups) {
        processCommandGroups(tree, (CommandGroups) section.clazz);
      } else if (section.clazz instanceof CommonCommands) {
        processCommonCommands(tree, (CommonCommands) section.clazz);
      } else if (section.clazz instanceof DocumentNumerators) {
        processDocumentNumerators(tree, (DocumentNumerators) section.clazz);
      } else if (section.clazz instanceof DocumentJournals) {
        processDocumentJournals(tree, (DocumentJournals) section.clazz);
      } else if (section.clazz instanceof Reports) {
        processReports(tree, (Reports) section.clazz);
      } else if (section.clazz instanceof ChartsOfCharacteristicTypes) {
        processChartsOfCharacteristicTypes(tree, (ChartsOfCharacteristicTypes) section.clazz);
      } else if (section.clazz instanceof AccumulationRegisters) {
        processAccumulationRegisters(tree, (AccumulationRegisters) section.clazz);
      } else if (section.clazz instanceof Sequences) {
        processSequences(tree, (Sequences) section.clazz);
      } else if (section.clazz instanceof DataProcessors) {
        processDataProcessors(tree, (DataProcessors) section.clazz);
      } else if (section.clazz instanceof Catalogs) {
        processCatalogs(tree, (Catalogs) section.clazz);
      } else if (section.clazz instanceof Enums) {
        processEnums(tree, (Enums) section.clazz);
      } else {
        throw new RuntimeException("NOT IMPLEMENTED" + section.getClass());
      }
    }
  }

  private void processEnums(final ProjectTree tree, final Enums v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + ENUMS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processCatalogs(final ProjectTree tree, final Catalogs v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + CATALOGS_PATH
              + File.separator;
      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processDataProcessors(final ProjectTree tree, final DataProcessors v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + DATA_PROCESSORS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processSequences(final ProjectTree tree, final Sequences v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + SEQUENCES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processAccumulationRegisters(final ProjectTree tree,
      final AccumulationRegisters v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + ACCUMULATION_REGISTERS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processChartsOfCharacteristicTypes(final ProjectTree tree,
      final ChartsOfCharacteristicTypes v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + CHART_OF_CHARACTERITIC_TYPES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processReports(final ProjectTree tree, final Reports v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + REPORTS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());
      moveLinkedContainerToFolder(tree, path2.toString() + ".0", destinationDir
          + path2.toString()
          + ".0");
      moveToFolder(tree, path2.toString() + ".1", destinationDir + path2.toString() + ".1");

    }
  }

  private void processDocumentJournals(final ProjectTree tree, final DocumentJournals v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + DOCUMENT_JOURNALS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processDocumentNumerators(final ProjectTree tree,
      final DocumentNumerators v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + DOCUMENT_NUMERATORS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processCommonCommands(final ProjectTree tree, final CommonCommands v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + COMMON_COMMANDS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());
      moveLinkedContainerToFolder(tree, path2.toString() + ".2", destinationDir
          + path2.toString()
          + ".2");

    }
  }

  private void processCommandGroups(final ProjectTree tree, final CommandGroups v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + COMMAND_GROUPS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processInformationRegisters(final ProjectTree tree,
      final InformationRegisters v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + INFORMATION_REGISTERS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processCommonForms(final ProjectTree tree, final CommonForms v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + COMMON_FORMS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processDocuments(final ProjectTree tree, final Documents v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + DOCUMENTS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processConstants(final ProjectTree tree, final Constants v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + CONSTANTS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processCommonConfigurationMetaData(final ProjectTree tree,
      final CommonConfiguraionMetaData v8MetaData) {
    for (final V8ClassObject section : v8MetaData.innerType.sections) {
      if (section.clazz instanceof Languages) {
        processLanguages(tree, (Languages) section.clazz);
      } else if (section.clazz instanceof Subsystems) {
        processSubsystems(tree, (Subsystems) section.clazz);
      } else if (section.clazz instanceof CommonModules) {
        processCommonModules(tree, (CommonModules) section.clazz);
      } else if (section.clazz instanceof SessionParams) {
        processSessionParams(tree, (SessionParams) section.clazz);
      } else if (section.clazz instanceof Roles) {
        processRoles(tree, (Roles) section.clazz);
      } else if (section.clazz instanceof CommonAttributes) {
        processCommonAttributes(tree, (CommonAttributes) section.clazz);
      } else if (section.clazz instanceof ExchangePlans) {
        processExchangePlans(tree, (ExchangePlans) section.clazz);
      } else if (section.clazz instanceof FilterCriteria) {
        processFilterCriteria(tree, (FilterCriteria) section.clazz);
      } else if (section.clazz instanceof EventSubscriptions) {
        processEventSubscriptions(tree, (EventSubscriptions) section.clazz);
      } else if (section.clazz instanceof ScheduledJobs) {
        processScheduledJobs(tree, (ScheduledJobs) section.clazz);
      } else if (section.clazz instanceof FunctionalOptions) {
        processFunctionalOptions(tree, (FunctionalOptions) section.clazz);
      } else if (section.clazz instanceof FunctionalOptionParams) {
        processFuctionalOptionParams(tree, (FunctionalOptionParams) section.clazz);
      } else if (section.clazz instanceof DefinedTypes) {
        processDefinedTypes(tree, (DefinedTypes) section.clazz);
      } else if (section.clazz instanceof SettingsStorages) {
        processSettingsStorages(tree, (SettingsStorages) section.clazz);
      } else if (section.clazz instanceof CommonTemplates) {
        processCommonTemplates(tree, (CommonTemplates) section.clazz);
      } else if (section.clazz instanceof CommonPictures) {
        processCommonPictures(tree, (CommonPictures) section.clazz);
      } else if (section.clazz instanceof XdtoPackages) {
        processXdtoPackages(tree, (XdtoPackages) section.clazz);
      } else if (section.clazz instanceof WebServices) {
        processWebServices(tree, (WebServices) section.clazz);
      } else if (section.clazz instanceof HttpServices) {
        processHttpServices(tree, (HttpServices) section.clazz);
      } else if (section.clazz instanceof WsReferences) {
        processWsReferences(tree, (WsReferences) section.clazz);
      } else if (section.clazz instanceof StyleItems) {
        processStyleItems(tree, (StyleItems) section.clazz);
      } else if (section.clazz instanceof Interfaces) {
        processInterfaces(tree, (Interfaces) section.clazz);
      } else if (section.clazz instanceof Styles) {
        processStyles(tree, (Styles) section.clazz);
      } else {
        throw new RuntimeException("Undefined section" + section.getClass());
      }
    }
  }

  private void processStyles(final ProjectTree tree, final Styles v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + STYLES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processInterfaces(final ProjectTree tree, final Interfaces v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + INTERFACES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());
      moveToFolder(tree, path2.toString() + ".0", destinationDir + path2.toString() + ".0");

    }
  }

  private void processStyleItems(final ProjectTree tree, final StyleItems v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + STYLE_ITEMS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processWsReferences(final ProjectTree tree, final WsReferences v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + WS_REFERENCES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());
      moveLinkedContainerToFolder(tree, path2.toString() + ".0", destinationDir
          + path2.toString()
          + ".0");

    }
  }

  private void processHttpServices(final ProjectTree tree, final HttpServices v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + HTTP_SERVICES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processWebServices(final ProjectTree tree, final WebServices v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + WEB_SERVICES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processXdtoPackages(final ProjectTree tree, final XdtoPackages v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + XDTO_PACKAGES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processCommonPictures(final ProjectTree tree, final CommonPictures v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + COMMON_PICTURES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());
      moveToFolder(tree, path2.toString() + ".0", destinationDir + path2.toString() + ".0");

    }
  }

  private void processCommonTemplates(final ProjectTree tree, final CommonTemplates v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + COMMON_TEMPLATES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());
      moveToFolder(tree, path2.toString() + ".0", destinationDir + path2.toString() + ".0");

    }
  }

  private void processSettingsStorages(final ProjectTree tree, final SettingsStorages v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + SETTINGS_STORAGES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processDefinedTypes(final ProjectTree tree, final DefinedTypes v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + DEFINED_TYPES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processFuctionalOptionParams(final ProjectTree tree,
      final FunctionalOptionParams v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + FUNCTIONAL_OPTIONS_PARAMS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processFunctionalOptions(final ProjectTree tree,
      final FunctionalOptions v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + FUNCTIONAL_OPTIONS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processScheduledJobs(final ProjectTree tree, final ScheduledJobs v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + SCHEDULED_JOBS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processEventSubscriptions(final ProjectTree tree,
      final EventSubscriptions v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + EVENT_SUBSCRIPTIONS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processFilterCriteria(final ProjectTree tree, final FilterCriteria v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + FILTER_CRITERIA_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processExchangePlans(final ProjectTree tree, final ExchangePlans v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + EXCHANGE_PLANS_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processCommonAttributes(final ProjectTree tree, final CommonAttributes v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + COMMON_ATTRIBUTES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());

    }
  }

  private void processRoles(final ProjectTree tree, final Roles v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + ROLES_PATH
              + File.separator;

      moveToFolder(tree, path2.toString(), destinationDir + path2.toString());
      moveToFolder(tree, path2.toString() + ".0", destinationDir + path2.toString() + ".0");

    }
  }

  private void processSessionParams(final ProjectTree tree, final SessionParams v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + SESSION_PARAMS_PATH
              + File.separator
              + path2.toString();

      moveToFolder(tree, path2.toString(), destinationDir);

    }
  }

  private void processLanguages(final ProjectTree tree, final Languages v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + LANGUAGES_PATH
              + File.separator
              + path2.toString();

      moveToFolder(tree, path2.toString(), destinationDir);

    }
  }

  private void processCommonModules(final ProjectTree tree, final CommonModules v8MetaData) {
    for (final UUID module : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + COMMON_MODULES_PATH
              + File.separator
              + module.toString();

      moveToFolder(tree, module.toString(), destinationDir);
      moveLinkedContainerToFolder(tree, module.toString() + ".0", destinationDir + ".0");

    }
  }

  private void processSubsystems(final ProjectTree tree, final Subsystems v8MetaData) {
    for (final UUID path2 : v8MetaData.uuids) {
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + SUBSYSTEM_PATH
              + File.separator
              + path2.toString();

      moveToFolder(tree, path2.toString(), destinationDir);

    }
  }

  private void processExternalDataProcessor(final ProjectTree tree,
      final ExternalDataProcessorMetaData v8MetaData) {
    for (final V8ClassObject section : v8MetaData.innerType.sections) {
      if (section.clazz instanceof FormList) {
        this.logger.debug("FormSections size: {}", ((FormList) section.clazz).forms.size());
        processForms(tree, (FormList) section.clazz);

      } else if (section.clazz instanceof TabularList) {
        this.logger
            .debug("TabularSections size: {}", ((TabularList) section.clazz).tabularSections
                .size());
      } else if (section.clazz instanceof TemplateList) {
        this.logger.debug("TemplateSection size: {}", ((TemplateList) section.clazz).templates
            .size());
        processTemplates(tree, (TemplateList) section.clazz);
      } else if (section.clazz instanceof AttributesList) {
        this.logger.debug("Attributes size: {}", ((AttributesList) section.clazz).descr.size());
      } else {
        this.logger.warn("Not implement section {}", section.getClass());
      }
    }
  }

  private void processTemplates(final ProjectTree tree, final TemplateList templateSection) {
    for (final UUID template : templateSection.templates) {
      final String templateUuid = template.toString();
      final TemplateDescription description = getTemplateDescription(tree, templateUuid);
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + TEMPLATES_PATH
              + File.separator
              + description.templateInnerDescription.msn.name
              + File.separator;
      moveToFolder(tree, templateUuid, destinationDir + METADATA_FILE);
      moveLinkedContainerToFolder(tree, templateUuid + ".0", destinationDir);
      moveLinkedContainerToFolder(tree, templateUuid + ".1", destinationDir);
    }
  }

  private void processForms(final ProjectTree tree, final FormList formSection) {
    for (final UUID form : formSection.forms) {
      final String formUuid = form.toString();
      final FormDescription description = getFormDescription(tree, formUuid);
      final String destinationDir =
          this.path.toString()
              + File.separator
              + Project.SRC_PATH
              + File.separator
              + FORM_PATH
              + File.separator
              + description.formInnerDescription.md.md.ffmd.name
              + File.separator;

      moveToFolder(tree, formUuid, destinationDir + METADATA_FILE);
      moveLinkedContainerToFolder(tree, formUuid + ".0", destinationDir);
      moveLinkedContainerToFolder(tree, formUuid + ".1", destinationDir);
    }
  }

  private TemplateDescription getTemplateDescription(final ProjectTree tree,
      final String template) {
    TemplateDescription description = null;
    try {
      description =
          V8Reader.read(TemplateDescription.class, getFileBuffer(tree, template.toString()));
    } catch (final Exception e) {
      this.logger.warn("Error while parsing template {}", template);
    }

    return description;
  }

  private FormDescription getFormDescription(final ProjectTree tree, final String form) {
    FormDescription description = null;
    try {
      description = V8Reader.read(FormDescription.class, getFileBuffer(tree, form.toString()));
    } catch (final Exception e) {
      this.logger.warn("Error while parsing form {}", form, e);
    }

    return description;
  }

  private void moveLinkedContainerToFolder(final ProjectTree tree, final String name,
      final String dest) {
    final ProjectTree pt = findFileByName(tree, name);
    if ((pt != null) && (pt.type != FileType.ERROR)) {
      if (pt.type.equals(FileType.CONTAINER)) {
        final Path p = pt.getRawPath();
        final String destination = this.path.relativize(Paths.get(dest)).toString();
        for (final ProjectTree child : pt.child) {
          final Path simplename = p.relativize(child.getRawPath());
          final Path abolute =
              Paths.get(this.path.toString()
                  + File.separator
                  + destination
                  + File.separator
                  + simplename.toString());

          moveToFolder(child, child.name, abolute.toString());
        }
        Paths.get(this.path.toAbsolutePath() + File.separator + pt.getRawPath().toString())
            .toFile().delete();
        pt.setPath(destination);
      } else if (pt.type.equals(FileType.FILE)) {
        moveToFolder(tree, name, dest + File.separator + pt.getName());
      }
    }
  }

  private void moveToFolder(final ProjectTree tree, final String name, final String dest) {
    final ProjectTree pt = findFileByName(tree, name);
    if (pt != null) {
      final Path p = Paths.get(this.path.toString() + File.separator + pt.getPath());
      final File file = p.toFile();
      final File destName = new File(dest);
      destName.getParentFile().mkdirs();
      if (destName.exists() && !destName.delete()) {
        throw new RuntimeException("Can't delete previous file:" + dest);
      }
      if (file.renameTo(destName)) {
        pt.setPath(this.path.relativize(destName.toPath().toAbsolutePath()).toString());
      } else {
        this.logger.warn("Can't move {} to {} ", p, dest);
      }
    }
  }
}
