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

public class SecurityElement implements ConfigurationElement {

  private SimpleTextElement myAllpermissions = null;
  private SimpleTextElement myJ2eeapplicationclientpermissions = null;

  @Override
  public void appendXml(Project project, Element parentElement) {
    Element securityElement = ConfigurationHelper.appendElement(parentElement, "security");
    ConfigurationHelper.appendElement(project, securityElement, this.getAllpermissions());
    ConfigurationHelper.appendElement(project, securityElement, this.getJ2eeapplicationclientpermissions());
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public SimpleTextElement createAllpermissions() {
    if(this.myAllpermissions == null) {
      this.myAllpermissions = new SimpleTextElement("all-permissions");
    }
    return this.myAllpermissions;
  }
  public SimpleTextElement getAllpermissions() {
    return this.myAllpermissions;
  }

  public SimpleTextElement createJ2eeapplicationclientpermissions() {
    if(this.myJ2eeapplicationclientpermissions == null) {
      this.myJ2eeapplicationclientpermissions = new SimpleTextElement("j2ee-application-client-permissions");
    }
    return this.myJ2eeapplicationclientpermissions;
  }
  public SimpleTextElement getJ2eeapplicationclientpermissions() {
    return this.myJ2eeapplicationclientpermissions;
  }

}