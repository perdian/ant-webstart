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
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.w3c.dom.Element;

public class JarElement implements ConfigurationElement {

  private String myHref = null;
  private String myVersion = null;
  private Boolean myMain = null;
  private Boolean myDownload = null;
  private Integer mySize = null;
  private String myPart = null;

  @Override
  public void appendXml(Project project, Element parentElement) {
    Element jarElement = ConfigurationHelper.appendElement(parentElement, "jar");
    ConfigurationHelper.appendAttributeIfNotNull(jarElement, "href", this.getHref());
    ConfigurationHelper.appendAttributeIfNotNull(jarElement, "version", this.getVersion());
    ConfigurationHelper.appendAttributeIfNotNull(jarElement, "main", this.getMain());
    ConfigurationHelper.appendAttributeIfNotNull(jarElement, "download", this.getDownload());
    ConfigurationHelper.appendAttributeIfNotNull(jarElement, "size", this.getSize());
    ConfigurationHelper.appendAttributeIfNotNull(jarElement, "part", this.getPart());
  }

  private Path createClasspath(Task task) {
    return new Path(task.getProject());
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

  public String getVersion() {
    return this.myVersion;
  }
  public void setVersion(String version) {
    this.myVersion = version;
  }

  public Boolean getMain() {
    return this.myMain;
  }
  public void setMain(Boolean main) {
    this.myMain = main;
  }

  public Boolean getDownload() {
    return this.myDownload;
  }
  public void setDownload(Boolean download) {
    this.myDownload = download;
  }

  public Integer getSize() {
    return this.mySize;
  }
  public void setSize(Integer size) {
    this.mySize = size;
  }

  public String getPart() {
    return this.myPart;
  }
  public void setPart(String part) {
    this.myPart = part;
  }

}