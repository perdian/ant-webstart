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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Collects general information to be written into the
 *
 * @author Christian Robert
 */

public class InformationElement extends ConfigurationElement {

  private String myTitle = null;
  private String myVendor = null;
  private boolean stateOfflineallowed = false;

  @Override
  public void appendXml(Document document, Element parentElement) {
    Element informationElement = document.createElement("information");
    informationElement.appendChild(ConfigurationElement.createElementWithContent(document, "title", this.getTitle()));
    informationElement.appendChild(ConfigurationElement.createElementWithContent(document, "vendor", this.getVendor()));
    if(this.isOfflineallowed()) {
      informationElement.appendChild(document.createElement("offline-allowed"));
    }
    parentElement.appendChild(informationElement);
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public String getTitle() {
    return this.myTitle;
  }
  public void setTitle(String title) {
    this.myTitle = title;
  }

  public String getVendor() {
    return this.myVendor;
  }
  public void setVendor(String vendor) {
    this.myVendor = vendor;
  }

  public boolean isOfflineallowed() {
    return this.stateOfflineallowed;
  }
  public void setOfflineallowed(boolean offlineallowed) {
    this.stateOfflineallowed = offlineallowed;
  }

}