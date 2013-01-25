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

public class PackageElement implements ConfigurationElement {

  private String myName = null;
  private String myPart = null;
  private Boolean myRecursive = null;

  @Override
  public void appendXml(Project project, Element parentElement) {
    Element packageElement = ConfigurationHelper.appendElement(parentElement, "package");
    ConfigurationHelper.appendAttributeIfNotNull(packageElement, "name", this.getName());
    ConfigurationHelper.appendAttributeIfNotNull(packageElement, "part", this.getPart());
    ConfigurationHelper.appendAttributeIfNotNull(packageElement, "recursive", this.getRecursive());
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public String getName() {
    return this.myName;
  }
  public void setName(String name) {
    this.myName = name;
  }

  public String getPart() {
    return this.myPart;
  }
  public void setPart(String part) {
    this.myPart = part;
  }

  public Boolean getRecursive() {
    return this.myRecursive;
  }
  public void setRecursive(Boolean recursive) {
    this.myRecursive = recursive;
  }

}