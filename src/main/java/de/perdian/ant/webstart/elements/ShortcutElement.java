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

public class ShortcutElement implements ConfigurationElement {

  private Boolean stateOnline = false;
  private Boolean stateInstall = false;
  private SimpleTextElement myDesktop = null;
  private MenuElement myMenu = null;

  @Override
  public void appendXml(Project project, Element parentElement) {
    Element shortcutElement = ConfigurationHelper.appendElement(parentElement, "shortcut");
    ConfigurationHelper.appendAttributeIfNotNull(shortcutElement, "online", this.getOnline());
    ConfigurationHelper.appendAttributeIfNotNull(shortcutElement, "install", this.getOnline());
    ConfigurationHelper.appendElement(project, shortcutElement, this.getDesktop());
    ConfigurationHelper.appendElement(project, shortcutElement, this.getMenu());
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public Boolean getOnline() {
    return this.stateOnline;
  }
  public void setOnline(Boolean online) {
    this.stateOnline = online;
  }

  public Boolean getInstall() {
    return this.stateInstall;
  }
  public void setInstall(Boolean install) {
    this.stateInstall = install;
  }

  public SimpleTextElement createDesktop() {
    if(this.myDesktop == null) {
      this.myDesktop = new SimpleTextElement("desktop");
    }
    return this.myDesktop;
  }
  public SimpleTextElement getDesktop() {
    return this.myDesktop;
  }

  public MenuElement createMenu() {
    if(this.myMenu == null) {
      this.myMenu = new MenuElement();
    }
    return this.myMenu;
  }
  public MenuElement getMenu() {
    return this.myMenu;
  }

}