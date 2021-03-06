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

import org.w3c.dom.Element;

import de.perdian.ant.webstart.JnlpTask;

public class AppletDescElement implements ConfigurationElement {

  private String myDocumentbase = null;
  private String myName = null;
  private String myMainclass = null;
  private Integer myWidth = null;
  private Integer myHeight = null;
  private List<ParamElement> myParam = new ArrayList<ParamElement>();

  @Override
  public void appendXml(JnlpTask task, Element parentElement) {
    Element appletDescElement = ConfigurationHelper.appendElement(parentElement, "applet-desc");
    ConfigurationHelper.appendAttributeIfNotNull(appletDescElement, "documentBase", this.getDocumentbase());
    ConfigurationHelper.appendAttributeIfNotNull(appletDescElement, "name", this.getName());
    ConfigurationHelper.appendAttributeIfNotNull(appletDescElement, "main-class", this.getMainclass());
    ConfigurationHelper.appendAttributeIfNotNull(appletDescElement, "width", this.getWidth());
    ConfigurationHelper.appendAttributeIfNotNull(appletDescElement, "height", this.getHeight());
    ConfigurationHelper.appendElements(task, appletDescElement, this.getParam());
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public String getDocumentbase() {
    return this.myDocumentbase;
  }
  public void setDocumentbase(String documentbase) {
    this.myDocumentbase = documentbase;
  }

  public String getName() {
    return this.myName;
  }
  public void setName(String name) {
    this.myName = name;
  }

  public String getMainclass() {
    return this.myMainclass;
  }
  public void setMainclass(String mainclass) {
    this.myMainclass = mainclass;
  }

  public Integer getWidth() {
    return this.myWidth;
  }
  public void setWidth(Integer width) {
    this.myWidth = width;
  }

  public Integer getHeight() {
    return this.myHeight;
  }
  public void setHeight(Integer height) {
    this.myHeight = height;
  }

  public ParamElement createParam() {
    ParamElement param = new ParamElement();
    this.getParam().add(param);
    return param;
  }
  public List<ParamElement> getParam() {
    return this.myParam;
  }

}