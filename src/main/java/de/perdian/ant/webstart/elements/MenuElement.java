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

public class MenuElement implements ConfigurationElement {

  private String mySubmenu = null;

  @Override
  public void appendXml(JnlpTask task, Element parentElement) {
    Element menuElement = ConfigurationHelper.appendElement(parentElement, "menu");
    ConfigurationHelper.appendAttributeIfNotNull(menuElement, "sub-menu", this.getSubmenu());
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public String getSubmenu() {
    return this.mySubmenu;
  }
  public void setSubmenu(String submenu) {
    this.mySubmenu = submenu;
  }

}