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

public class ExtensionElement implements ConfigurationElement {

  private String myHref = null;
  private String myVersion = null;
  private String myName = null;
  private List<ExtDownloadElement> myExtdownload = new ArrayList<ExtDownloadElement>();

  @Override
  public void appendXml(JnlpTask task, Element parentElement) {
    Element extensionElement = ConfigurationHelper.appendElement(parentElement, "extension");
    ConfigurationHelper.appendAttributeIfNotNull(extensionElement, "href", this.getHref());
    ConfigurationHelper.appendAttributeIfNotNull(extensionElement, "version", this.getVersion());
    ConfigurationHelper.appendAttributeIfNotNull(extensionElement, "name", this.getName());
    ConfigurationHelper.appendElements(task, extensionElement, this.getExtdownload());
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public String getHref() {
    return this.myHref;
  }
  public void setHref(String href) {
    this.myHref = href;
  }

  public String getVersion() {
    return this.myVersion;
  }
  public void setVersion(String version) {
    this.myVersion = version;
  }

  public String getName() {
    return this.myName;
  }
  public void setName(String name) {
    this.myName = name;
  }

  public ExtDownloadElement createExtdownload() {
    ExtDownloadElement extdownload = new ExtDownloadElement();
    this.getExtdownload().add(extdownload);
    return extdownload;
  }
  public List<ExtDownloadElement> getExtdownload() {
    return this.myExtdownload;
  }

}