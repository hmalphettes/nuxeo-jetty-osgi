PLATFORM_VERSION = "5.3.1-SNAPSHOT"
CORE_VERSION = "1.6.1-SNAPSHOT"

NUXEO_ECM_PLATFORM = {
  :usermanager_api => "org.nuxeo.ecm.platform:nuxeo-platform-usermanager-api:jar:#{PLATFORM_VERSION}"
}

NUXEO_ECM_CORE = {
  :api => "org.nuxeo.ecm.core:nuxeo-core-api:jar:#{CORE_VERSION}",
  :schema => "org.nuxeo.ecm.core:nuxeo-core-schema:jar:#{CORE_VERSION}"
}

NUXEO_RUNTIME = "org.nuxeo.runtime:nuxeo-runtime:jar:#{CORE_VERSION}"
NUXEO_COMMON = "org.nuxeo.common:nuxeo-common:jar:#{CORE_VERSION}"

COMMON = {
  :collections => "commons-collections:commons-collections:jar:3.2.1"
}

APACHE = {
  :commons_logging => "commons-logging:commons-logging:jar:1.1",
  :common_lang => "commons-lang:commons-lang:jar:2.3"
}

HIBERNATE = "org.hibernate:hibernate:jar:3.2.0.ga"

OPEN_CSV = "net.sf.opencsv:opencsv:jar:1.7"
