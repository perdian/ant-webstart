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

public class SimpleTextElement implements ConfigurationElement {

  private String myElementName = null;
  private String myText = null;

  public SimpleTextElement(String elementName) {
    this.setElementName(elementName);
  }

  @Override
  public void appendXml(JnlpTask task, Element parentElement) {
    String trimmedText = this.getText() == null ? null : this.getText().trim();
    if(trimmedText != null && trimmedText.length() > 0) {
      Element childElement = ConfigurationHelper.appendElement(parentElement, this.getElementName());
      childElement.appendChild(parentElement.getOwnerDocument().createTextNode(trimmedText));
    }
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public String getElementName() {
    return this.myElementName;
  }
  public void setElementName(String elementName) {
    this.myElementName = elementName;
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