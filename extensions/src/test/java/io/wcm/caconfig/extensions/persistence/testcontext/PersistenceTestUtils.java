/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2017 wcm.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.wcm.caconfig.extensions.persistence.testcontext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.caconfig.management.ConfigurationManager;
import org.apache.sling.caconfig.spi.ConfigurationCollectionPersistData;
import org.apache.sling.caconfig.spi.ConfigurationPersistData;
import org.apache.sling.testing.mock.osgi.MapUtil;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.wcm.testing.mock.aem.junit5.AemContext;

/**
 * Helper methods for persisting configuration data.
 */
@SuppressWarnings("null")
@SuppressFBWarnings("NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE")
public final class PersistenceTestUtils {

  private PersistenceTestUtils() {
    // static methods only
  }

  public static void writeConfiguration(AemContext context, String contextPath, String configName, Map<String, Object> values) {
    ConfigurationManager configManager = context.getService(ConfigurationManager.class);
    Resource contextResource = context.resourceResolver().getResource(contextPath);
    configManager.persistConfiguration(contextResource, configName, new ConfigurationPersistData(values));
  }

  public static void writeConfiguration(AemContext context, String contextPath, String configName, Object... values) {
    writeConfiguration(context, contextPath, configName, MapUtil.toMap(values));
  }

  public static void writeConfigurationCollection(AemContext context, String contextPath, String configName, Collection<Map<String, Object>> values) {
    writeConfigurationCollection(context, contextPath, configName, values, null);
  }

  public static void writeConfigurationCollection(AemContext context, String contextPath, String configName, Collection<Map<String, Object>> values,
      Map<String, Object> collectionProperties) {
    ConfigurationManager configManager = context.getService(ConfigurationManager.class);
    Resource contextResource = context.resourceResolver().getResource(contextPath);
    List<ConfigurationPersistData> items = new ArrayList<>();
    int index = 0;
    for (Map<String, Object> map : values) {
      items.add(new ConfigurationPersistData(map).collectionItemName("item" + (index++)));
    }
    configManager.persistConfigurationCollection(contextResource, configName,
        new ConfigurationCollectionPersistData(items).properties(collectionProperties));
  }

}
