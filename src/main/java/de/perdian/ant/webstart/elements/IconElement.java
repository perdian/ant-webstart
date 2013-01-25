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

import org.apache.tools.ant.types.EnumeratedAttribute;
import org.w3c.dom.Element;

import de.perdian.ant.webstart.JnlpTask;

public class IconElement implements ConfigurationElement {

  private String myHref = null;
  private IconKindAttribute myKind = null;
  private Integer myWidth = null;
  private Integer myHeight = null;
  private Integer myDepth = null;

  @Override
  public void appendXml(JnlpTask task, Element parentElement) {
    Element iconElement = ConfigurationHelper.appendElement(parentElement, "icon");
    ConfigurationHelper.appendAttributeIfNotNull(iconElement, "href", this.getHref());
    ConfigurationHelper.appendAttributeIfNotNull(iconElement, "kind", this.getKind());
    ConfigurationHelper.appendAttributeIfNotNull(iconElement, "width", this.getWidth());
    ConfigurationHelper.appendAttributeIfNotNull(iconElement, "height", this.getHeight());
    ConfigurationHelper.appendAttributeIfNotNull(iconElement, "depth", this.getDepth());
  }

  // ---------------------------------------------------------------------------
  // --- Inner classes ---------------------------------------------------------
  // ---------------------------------------------------------------------------

  public static class IconKindAttribute extends EnumeratedAttribute {
    @Override public String[] getValues() {
      return new String[] { "default", "splash", "shortcut" };
    }
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

  public IconKindAttribute getKind() {
    return this.myKind;
  }
  public void setKind(IconKindAttribute kind) {
    this.myKind = kind;
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

  public Integer getDepth() {
    return this.myDepth;
  }
  public void setDepth(Integer depth) {
    this.myDepth = depth;
  }

}