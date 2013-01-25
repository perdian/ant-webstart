/*
 * Copyright 2013 Christian Robert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.perdian.ant.webstart.elements;

import org.apache.tools.ant.Project;
import org.w3c.dom.Element;

public class AssociationElement implements ConfigurationElement {

  private String myMimetype = null;
  private String myExtensions = null;

  @Override
  public void appendXml(Project project, Element parentElement) {
    Element associationElement = ConfigurationHelper.appendElement(parentElement, "association");
    ConfigurationHelper.appendAttributeIfNotNull(associationElement, "mime-type", this.getMimetype());
    ConfigurationHelper.appendAttributeIfNotNull(associationElement, "extensions", this.getExtensions());
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public String getMimetype() {
    return this.myMimetype;
  }
  public void setMimetype(String mimetype) {
    this.myMimetype = mimetype;
  }

  public String getExtensions() {
    return this.myExtensions;
  }
  public void setExtensions(String extensions) {
    this.myExtensions = extensions;
  }

}