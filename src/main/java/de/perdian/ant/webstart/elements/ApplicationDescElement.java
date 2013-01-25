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

import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.Project;
import org.w3c.dom.Element;

public class ApplicationDescElement implements ConfigurationElement {

  private String myMainclass = null;
  private List<ArgumentElement> myArgument = new ArrayList<ArgumentElement>();

  @Override
  public void appendXml(Project project, Element parentElement) {
    Element applicationDescElement = ConfigurationHelper.appendElement(parentElement, "application-desc");
    ConfigurationHelper.appendAttributeIfNotNull(applicationDescElement, "main-class", this.getMainclass());
    ConfigurationHelper.appendElements(project, applicationDescElement, this.getArgument());
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public String getMainclass() {
    return this.myMainclass;
  }
  public void setMainclass(String mainclass) {
    this.myMainclass = mainclass;
  }

  public ArgumentElement createArgument() {
    ArgumentElement argument = new ArgumentElement();
    this.getArgument().add(argument);
    return argument;
  }
  public List<ArgumentElement> getArgument() {
    return this.myArgument;
  }

}