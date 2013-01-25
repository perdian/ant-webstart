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

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Ant.Reference;
import org.apache.tools.ant.types.Path;
import org.w3c.dom.Element;

import de.perdian.ant.webstart.JnlpTask;

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
  private Path myPath = null;
  private Reference myPathref = null;

  @Override
  public void appendXml(JnlpTask task, Element parentElement) {

    Element resourcesElement = ConfigurationHelper.appendElement(parentElement, "resources");
    ConfigurationHelper.appendAttributeIfNotNull(resourcesElement, "os", this.getOs());
    ConfigurationHelper.appendAttributeIfNotNull(resourcesElement, "arch", this.getArch());
    ConfigurationHelper.appendAttributeIfNotNull(resourcesElement, "locale", this.getLocale());
    ConfigurationHelper.appendElements(task, resourcesElement, this.getJava());
    ConfigurationHelper.appendElements(task, resourcesElement, this.getJar());
    ConfigurationHelper.appendElements(task, resourcesElement, this.getNativelib());
    ConfigurationHelper.appendElements(task, resourcesElement, this.getExtension());
    ConfigurationHelper.appendElements(task, resourcesElement, this.getPackage());
    ConfigurationHelper.appendElements(task, resourcesElement, this.getProperty());

    // Now compute the dynamic path
    Path dynamicPath = new Path(task.getProject());
    if(this.getPath() != null) {
      dynamicPath.append(this.getPath());
    }
    if(this.getPathref() != null) {
      dynamicPath.setRefid(this.getPathref());
    }

    // Since all entries within the JNLP should be relative to it's location,
    // we compute the locations of the files found in the dynamic path against
    // the URI of the destination file of the JNLP XML document
    URI pathBaseUri = task.getDestfile().getParentFile().toURI();
    for(String dynamicPathEntry : dynamicPath.list()) {
      File resourceFile = new File(dynamicPathEntry);
      if(resourceFile.isFile() && resourceFile.exists()) {
        URI resourceUriAbsolute = resourceFile.toURI();
        URI resourceUriRelative = pathBaseUri.relativize(resourceUriAbsolute);
        if(resourceUriRelative.isAbsolute()) {
          task.log("Computed resource entry is not relative! >> " + resourceUriRelative, Project.MSG_WARN);
        }
        JarElement jarElement = new JarElement();
        jarElement.setHref(resourceUriRelative.toString());
        jarElement.setSize((int)resourceFile.length());
        jarElement.appendXml(task, resourcesElement);
      }
    }

  }

  // ---------------------------------------------------------------------------
  // --- Path computation ------------------------------------------------------
  // ---------------------------------------------------------------------------

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

  public Path getPath() {
    return this.myPath;
  }
  public Path createPath() {
    if(this.myPath == null) {
      this.myPath = new Path(null);
    }
    return this.myPath;
  }

  public Reference createPathref() {
    if(this.myPathref == null) {
      this.myPathref = new Reference();
    }
    return this.myPathref;
  }
  public Reference getPathref() {
    return this.myPathref;
  }

}