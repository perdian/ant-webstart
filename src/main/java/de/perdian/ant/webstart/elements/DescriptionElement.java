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

public class DescriptionElement implements ConfigurationElement {

  private DescriptionKindAttribute myKind = null;
  private String myText = null;

  @Override
  public void appendXml(JnlpTask task, Element parentElement) {
    Element descriptionElement = ConfigurationHelper.appendElement(parentElement, "description");
    ConfigurationHelper.appendAttributeIfNotNull(descriptionElement, "kind", this.getKind());
    if(this.getText() != null) {
      descriptionElement.appendChild(parentElement.getOwnerDocument().createTextNode(this.getText()));
    }
  }

  // ---------------------------------------------------------------------------
  // --- Inner classes ---------------------------------------------------------
  // ---------------------------------------------------------------------------

  public static class DescriptionKindAttribute extends EnumeratedAttribute {
    @Override public String[] getValues() {
      return new String[] { "one-line", "short", "tooltip" };
    }
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public DescriptionKindAttribute getKind() {
    return this.myKind;
  }
  public void setKind(DescriptionKindAttribute kind) {
    this.myKind = kind;
  }

  public void addText(String text) {
    this.setText(text);
  }
  public String getText() {
    return this.myText;
  }
  public void setText(String text) {
    this.myText = text;
  }

}