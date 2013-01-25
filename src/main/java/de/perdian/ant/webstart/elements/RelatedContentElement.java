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

public class RelatedContentElement implements ConfigurationElement {

  private String myHref = null;
  private String myTitle = null;
  private List<DescriptionElement> myDescription = new ArrayList<DescriptionElement>();
  private List<IconElement> myIcon = new ArrayList<IconElement>();

  @Override
  public void appendXml(JnlpTask task, Element parentElement) {
    Element relatedContentElement = ConfigurationHelper.appendElement(parentElement, "related-content");
    ConfigurationHelper.appendAttributeIfNotNull(relatedContentElement, "title", this.getTitle());
    ConfigurationHelper.appendAttributeIfNotNull(relatedContentElement, "href", this.getHref());
    ConfigurationHelper.appendElements(task, relatedContentElement, this.getDescription());
    ConfigurationHelper.appendElements(task, relatedContentElement, this.getIcon());
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

  public String getTitle() {
    return this.myTitle;
  }
  public void setTitle(String title) {
    this.myTitle = title;
  }

  public DescriptionElement createDescription() {
    DescriptionElement DescriptionElement = new DescriptionElement();
    this.getDescription().add(DescriptionElement);
    return DescriptionElement;
  }
  public List<DescriptionElement> getDescription() {
    return this.myDescription;
  }

  public IconElement createIcon() {
    IconElement iconElement = new IconElement();
    this.getIcon().add(iconElement);
    return iconElement;
  }
  public List<IconElement> getIcon() {
    return this.myIcon;
  }

}