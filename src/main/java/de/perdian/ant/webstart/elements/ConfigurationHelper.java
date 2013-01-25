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

import java.util.Collection;

import org.apache.tools.ant.Project;
import org.w3c.dom.Element;

public class ConfigurationHelper {

  public static void appendElement(Project project, Element parentElement, ConfigurationElement configurationElement) {
    if(configurationElement != null) {
      configurationElement.appendXml(project, parentElement);
    }
  }

  public static Element appendElement(Element parentElement, String childElementName) {
    return ConfigurationHelper.appendElement(parentElement, childElementName, null);
  }

  public static Element appendElement(Element parentElement, String childElementName, Object childElementContent) {
    Element childElement = parentElement.getOwnerDocument().createElement(childElementName);
    if(childElementContent != null) {
      childElement.appendChild(parentElement.getOwnerDocument().createTextNode(childElementContent.toString()));
    }
    parentElement.appendChild(childElement);
    return childElement;
  }

  public static Element appendElementIfNotNull(Element parentElement, String childElementName, Object childElementContent) {
    if(childElementContent == null) {
      return null;
    } else {
      return ConfigurationHelper.appendElement(parentElement, childElementName, childElementContent);
    }
  }

  public static void appendElements(Project project, Element parentElement, Collection<? extends ConfigurationElement> elements) {
    if(elements != null && !elements.isEmpty()) {
      for(ConfigurationElement element : elements) {
        element.appendXml(project, parentElement);
      }
    }
  }

  public static void appendAttributeIfNotNull(Element parentElement, String attributeName, Object attributeValue) {
    if(attributeValue != null) {
      parentElement.setAttribute(attributeName, attributeValue.toString());
    }
  }

}