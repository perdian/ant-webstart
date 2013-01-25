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

public abstract class ConfigurationElement {

  public abstract void appendXml(Document document, Element parentElement);

  public static Element createElementWithContent(Document owningDocument, String childElementName, CharSequence childElementContent) {
    Element childElement = owningDocument.createElement(childElementName);
    if(childElementContent != null) {
      childElement.appendChild(owningDocument.createTextNode(childElementContent.toString()));
    }
    return childElement;
  }

}