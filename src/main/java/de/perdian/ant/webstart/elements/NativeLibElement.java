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

public class NativeLibElement implements ConfigurationElement {

  private String myHref = null;
  private String myVersion = null;
  private Boolean myDownload = null;
  private Integer mySize = null;
  private String myPart = null;

  @Override
  public void appendXml(Project project, Element parentElement) {
    Element nativeLibElement = ConfigurationHelper.appendElement(parentElement, "native-lib");
    ConfigurationHelper.appendAttributeIfNotNull(nativeLibElement, "href", this.getHref());
    ConfigurationHelper.appendAttributeIfNotNull(nativeLibElement, "version", this.getVersion());
    ConfigurationHelper.appendAttributeIfNotNull(nativeLibElement, "download", this.getDownload());
    ConfigurationHelper.appendAttributeIfNotNull(nativeLibElement, "size", this.getSize());
    ConfigurationHelper.appendAttributeIfNotNull(nativeLibElement, "part", this.getPart());
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

  public Boolean getDownload() {
    return this.myDownload;
  }
  public void setDownload(Boolean download) {
    this.myDownload = download;
  }

  public Integer getSize() {
    return this.mySize;
  }
  public void setSize(Integer size) {
    this.mySize = size;
  }

  public String getPart() {
    return this.myPart;
  }
  public void setPart(String part) {
    this.myPart = part;
  }

}