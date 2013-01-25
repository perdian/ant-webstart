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

import de.perdian.ant.webstart.elements.InformationElement;
import de.perdian.ant.webstart.elements.ResourcesElement;

/**
 * The main task from which the application will be generated
 *
 * @author Christian Robert
 */

public class JnlpTask extends Task {

  private File myDestfile = null;
  private String mySpec = "6.0+";
  private InformationElement myInformation = null;
  private ResourcesElement myResources = null;

  @Override
  public void execute() throws BuildException {
    if(this.getDestfile() == null) {
      throw new BuildException("Attribtue 'destfile' must be present");
    } else if(this.getInformation() == null) {
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
    jnlpElement.setAttribute("spec", this.getSpec());
    jnlpDocument.appendChild(jnlpElement);

    // Now append all children information
    this.getInformation().appendXml(jnlpDocument, jnlpElement);
    this.getResources().appendXml(jnlpDocument, jnlpElement);

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

  public InformationElement createInformation() {
    if(this.myInformation == null) {
      this.myInformation = new InformationElement();
    }
    return this.myInformation;
  }
  public InformationElement getInformation() {
    return this.myInformation;
  }

  public ResourcesElement createResources() {
    if(this.myResources == null) {
      this.myResources = new ResourcesElement();
    }
    return this.myResources;
  }
  public ResourcesElement getResources() {
    return this.myResources;
  }

}