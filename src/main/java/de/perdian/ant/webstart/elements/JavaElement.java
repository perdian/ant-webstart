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

public class JavaElement extends ConfigurationElement {

  private String myVersion = null;
  private String myHref = null;
  private String myJavavmargs = null;
  private String myInitialheapsize = null;
  private String myMaxheapsize = null;

  @Override
  public void appendXml(Document document, Element parentElement) {
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public String getVersion() {
    return this.myVersion;
  }
  public void setVersion(String version) {
    this.myVersion = version;
  }

  public String getHref() {
    return this.myHref;
  }
  public void setHref(String href) {
    this.myHref = href;
  }

  public String getJavavmargs() {
    return this.myJavavmargs;
  }
  public void setJavavmargs(String javavmargs) {
    this.myJavavmargs = javavmargs;
  }

  public String getInitialheapsize() {
    return this.myInitialheapsize;
  }
  public void setInitialheapsize(String initialheapsize) {
    this.myInitialheapsize = initialheapsize;
  }

  public String getMaxheapsize() {
    return this.myMaxheapsize;
  }
  public void setMaxheapsize(String maxheapsize) {
    this.myMaxheapsize = maxheapsize;
  }

}