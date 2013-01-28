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
package de.perdian.ant.webstart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.perdian.ant.webstart.elements.AppletDescElement;
import de.perdian.ant.webstart.elements.ApplicationDescElement;
import de.perdian.ant.webstart.elements.ComponentDescElement;
import de.perdian.ant.webstart.elements.ConfigurationHelper;
import de.perdian.ant.webstart.elements.InformationElement;
import de.perdian.ant.webstart.elements.InstallerDescElement;
import de.perdian.ant.webstart.elements.ResourcesElement;
import de.perdian.ant.webstart.elements.SecurityElement;
import de.perdian.ant.webstart.elements.UpdateElement;

/**
 * The main task from which the application will be generated
 *
 * @author Christian Robert
 */

public class JnlpTask extends Task {

  private File myDestfile = null;
  private String mySpec = "6.0+";
  private String myCodebase = null;
  private String myHref = null;
  private String myVersion = null;
  private List<InformationElement> myInformation = new ArrayList<InformationElement>();
  private SecurityElement mySecurity = null;
  private UpdateElement myUpdate = null;
  private List<ResourcesElement> myResources = new ArrayList<ResourcesElement>();
  private ApplicationDescElement myApplicationdesc = null;
  private AppletDescElement myAppletdesc = null;
  private ComponentDescElement myComponentdesc = null;
  private InstallerDescElement myInstallerdesc = null;

  @Override
  public void execute() throws BuildException {
    if(this.getDestfile() == null) {
      throw new BuildException("Attribtue 'destfile' must be present");
    } else if(this.getInformation() == null || this.getInformation().isEmpty()) {
      throw new BuildException("Child element 'information' must be present");
    } else if(this.getResources() == null) {
      throw new BuildException("Child element 'resources' must be present");
    } else {
      this.executeInternal();
    }
  }

  private void executeInternal() {
    try {
      if(!this.getDestfile().exists()) {

        this.log("Creating JNLP file at: " + this.getDestfile());
        if(!this.getDestfile().getParentFile().exists()) {
          this.getDestfile().getParentFile().mkdirs();
        }
        this.getDestfile().createNewFile();
      }

      // The content of the JNLP will be created as a DOM document first, then
      // we encode the document into the target file
      this.log("Writing JNLP file at: " + this.getDestfile().getAbsolutePath(), Project.MSG_INFO);
      try {
        Document jnlpDocument = this.createJnlpDom();
        StreamResult fileResult = new StreamResult(this.getDestfile());
        DOMSource domSource = new DOMSource(jnlpDocument);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(domSource, fileResult);
      } catch(Exception e) {
        this.log(e, Project.MSG_ERR);
        throw new BuildException("Cannot generate JNLP XML content", e);
      }

    } catch(IOException e) {
      throw new BuildException(e);
    }
  }

  private Document createJnlpDom() throws DOMException, ParserConfigurationException {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document jnlpDocument = documentBuilder.newDocument();
    this.appendJnlpContent(jnlpDocument);
    return jnlpDocument;
  }

  private void appendJnlpContent(Document jnlpDocument) throws DOMException {

    Element jnlpElement = jnlpDocument.createElement("jnlp");
    ConfigurationHelper.appendAttributeIfNotNull(jnlpElement, "spec", this.getSpec());
    ConfigurationHelper.appendAttributeIfNotNull(jnlpElement, "codebase", this.getCodebase());
    ConfigurationHelper.appendAttributeIfNotNull(jnlpElement, "href", this.getHref());
    ConfigurationHelper.appendAttributeIfNotNull(jnlpElement, "version", this.getVersion());
    jnlpDocument.appendChild(jnlpElement);

    // Now append all children information
    ConfigurationHelper.appendElements(this, jnlpElement, this.getInformation());
    ConfigurationHelper.appendElement(this, jnlpElement, this.getSecurity());
    ConfigurationHelper.appendElement(this, jnlpElement, this.getUpdate());
    ConfigurationHelper.appendElements(this, jnlpElement, this.getResources());
    ConfigurationHelper.appendElement(this, jnlpElement, this.getApplicationdesc());
    ConfigurationHelper.appendElement(this, jnlpElement, this.getAppletdesc());
    ConfigurationHelper.appendElement(this, jnlpElement, this.getComponentdesc());
    ConfigurationHelper.appendElement(this, jnlpElement, this.getInstallerdesc());

  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public File getDestfile() {
    return this.myDestfile;
  }
  public void setDestfile(File destfile) {
    this.myDestfile = destfile;
  }

  public String getSpec() {
    return this.mySpec;
  }
  public void setSpec(String spec) {
    this.mySpec = spec;
  }

  public String getCodebase() {
    return this.myCodebase;
  }
  public void setCodebase(String codebase) {
    this.myCodebase = codebase;
  }

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

  public InformationElement createInformation() {
    InformationElement information = new InformationElement();
    this.getInformation().add(information);
    return information;
  }
  public List<InformationElement> getInformation() {
    return this.myInformation;
  }

  public ResourcesElement createResources() {
    ResourcesElement resources = new ResourcesElement();
    this.getResources().add(resources);
    return resources;
  }
  public List<ResourcesElement> getResources() {
    return this.myResources;
  }

  public SecurityElement createSecurity() {
    if(this.mySecurity == null) {
      this.mySecurity = new SecurityElement();
    }
    return this.mySecurity;
  }
  public SecurityElement getSecurity() {
    return this.mySecurity;
  }

  public UpdateElement createUpdate() {
    if(this.myUpdate == null) {
      this.myUpdate = new UpdateElement();
    }
    return this.myUpdate;
  }
  public UpdateElement getUpdate() {
    return this.myUpdate;
  }

  public ApplicationDescElement createApplicationdesc() {
    if(this.myApplicationdesc == null) {
      this.myApplicationdesc = new ApplicationDescElement();
    }
    return this.myApplicationdesc;
  }
  public ApplicationDescElement getApplicationdesc() {
    return this.myApplicationdesc;
  }

  public AppletDescElement createAppletdesc() {
    if(this.myAppletdesc == null) {
      this.myAppletdesc = new AppletDescElement();
    }
    return this.myAppletdesc;
  }
  public AppletDescElement getAppletdesc() {
    return this.myAppletdesc;
  }

  public ComponentDescElement getComponentdesc() {
    return this.myComponentdesc;
  }
  public void setComponentdesc(ComponentDescElement componentdesc) {
    this.myComponentdesc = componentdesc;
  }

  public InstallerDescElement getInstallerdesc() {
    return this.myInstallerdesc;
  }
  public void setInstallerdesc(InstallerDescElement installerdesc) {
    this.myInstallerdesc = installerdesc;
  }

}