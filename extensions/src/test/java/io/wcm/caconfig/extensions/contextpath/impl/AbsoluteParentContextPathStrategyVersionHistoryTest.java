/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2018 wcm.io
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
package io.wcm.caconfig.extensions.contextpath.impl;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
@ExtendWith(MockitoExtension.class)
class AbsoluteParentContextPathStrategyVersionHistoryTest extends AbsoluteParentContextPathStrategyTest {

  @Override
  @BeforeEach
  void setUp() throws Exception {
    level1 = context.create().page("/tmp/versionhistory/user1/version1/region1").adaptTo(Resource.class);
    level2 = context.create().page("/tmp/versionhistory/user1/version1/region1/site1").adaptTo(Resource.class);
    level3 = context.create().page("/tmp/versionhistory/user1/version1/region1/site1/en").adaptTo(Resource.class);
    level4 = context.create().page("/tmp/versionhistory/user1/version1/region1/site1/en/page1").adaptTo(Resource.class);
  }

}
