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

import org.w3c.dom.Element;

import de.perdian.ant.webstart.JnlpTask;

public class ExtDownloadElement implements ConfigurationElement {

  private String myExtpart = null;
  private Boolean myDownload = null;
  private String myPart = null;

  @Override
  public void appendXml(JnlpTask task, Element parentElement) {
    Element extDownloadElement = ConfigurationHelper.appendElement(parentElement, "ext-download");
    ConfigurationHelper.appendAttributeIfNotNull(extDownloadElement, "ext-part", this.getExtpart());
    ConfigurationHelper.appendAttributeIfNotNull(extDownloadElement, "download", this.getDownload());
    ConfigurationHelper.appendAttributeIfNotNull(extDownloadElement, "part", this.getPart());
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public String getExtpart() {
    return this.myExtpart;
  }
  public void setExtpart(String extpart) {
    this.myExtpart = extpart;
  }

  public Boolean getDownload() {
    return this.myDownload;
  }
  public void setDownload(Boolean download) {
    this.myDownload = download;
  }

  public String getPart() {
    return this.myPart;
  }
  public void setPart(String part) {
    this.myPart = part;
  }

}