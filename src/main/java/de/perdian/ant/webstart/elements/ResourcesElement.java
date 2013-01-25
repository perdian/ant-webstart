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

import org.apache.tools.ant.Project;
import org.w3c.dom.Element;

/**
 * Collects general information to be written into the
 *
 * @author Christian Robert
 */

public class ResourcesElement implements ConfigurationElement {

  private String myOs = null;
  private String myArch = null;
  private String myLocale = null;
  private List<JavaElement> myJava = new ArrayList<JavaElement>();
  private List<JarElement> myJar = new ArrayList<JarElement>();
  private List<NativeLibElement> myNativelib = new ArrayList<NativeLibElement>();
  private List<ExtensionElement> myExtension = new ArrayList<ExtensionElement>();
  private List<PackageElement> myPackage = new ArrayList<PackageElement>();
  private List<PropertyElement> myProperty = new ArrayList<PropertyElement>();

  @Override
  public void appendXml(Project project, Element parentElement) {
    Element resourcesElement = ConfigurationHelper.appendElement(parentElement, "resources");
    ConfigurationHelper.appendAttributeIfNotNull(resourcesElement, "os", this.getOs());
    ConfigurationHelper.appendAttributeIfNotNull(resourcesElement, "arch", this.getArch());
    ConfigurationHelper.appendAttributeIfNotNull(resourcesElement, "locale", this.getLocale());
    ConfigurationHelper.appendElements(project, resourcesElement, this.getJava());
    ConfigurationHelper.appendElements(project, resourcesElement, this.getJar());
    ConfigurationHelper.appendElements(project, resourcesElement, this.getNativelib());
    ConfigurationHelper.appendElements(project, resourcesElement, this.getExtension());
    ConfigurationHelper.appendElements(project, resourcesElement, this.getPackage());
    ConfigurationHelper.appendElements(project, resourcesElement, this.getProperty());
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public String getOs() {
    return this.myOs;
  }
  public void setOs(String os) {
    this.myOs = os;
  }

  public String getArch() {
    return this.myArch;
  }
  public void setArch(String arch) {
    this.myArch = arch;
  }

  public String getLocale() {
    return this.myLocale;
  }
  public void setLocale(String locale) {
    this.myLocale = locale;
  }

  public JavaElement createJava() {
    JavaElement Java = new JavaElement();
    this.getJava().add(Java);
    return Java;
  }
  public List<JavaElement> getJava() {
    return this.myJava;
  }

  public JarElement createJar() {
    JarElement Jar = new JarElement();
    this.getJar().add(Jar);
    return Jar;
  }
  public List<JarElement> getJar() {
    return this.myJar;
  }

  public NativeLibElement createNativelib() {
    NativeLibElement Nativelib = new NativeLibElement();
    this.getNativelib().add(Nativelib);
    return Nativelib;
  }
  public List<NativeLibElement> getNativelib() {
    return this.myNativelib;
  }

  public ExtensionElement createExtension() {
    ExtensionElement Extension = new ExtensionElement();
    this.getExtension().add(Extension);
    return Extension;
  }
  public List<ExtensionElement> getExtension() {
    return this.myExtension;
  }

  public PackageElement createPackage() {
    PackageElement Package = new PackageElement();
    this.getPackage().add(Package);
    return Package;
  }
  public List<PackageElement> getPackage() {
    return this.myPackage;
  }

  public PropertyElement createProperty() {
    PropertyElement Property = new PropertyElement();
    this.getProperty().add(Property);
    return Property;
  }
  public List<PropertyElement> getProperty() {
    return this.myProperty;
  }

}