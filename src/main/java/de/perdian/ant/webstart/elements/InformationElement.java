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

public class InformationElement implements ConfigurationElement {

  private String myOs = null;
  private String myArch = null;
  private String myPlatform = null;
  private String myLocale = null;
  private SimpleTextElement myTitle = null;
  private SimpleTextElement myVendor = null;
  private HomepageElement myHomepage = null;
  private List<DescriptionElement> myDescription = new ArrayList<DescriptionElement>();
  private List<IconElement> myIcon = new ArrayList<IconElement>();
  private OfflineAllowedElement myOfflineallowed = null;
  private AssociationElement myAssociation = null;
  private ShortcutElement myShortcut = null;
  private RelatedContentElement myRelatedContent = null;

  @Override
  public void appendXml(Project project, Element parentElement) {

    Element informationElement = ConfigurationHelper.appendElement(parentElement, "information");
    ConfigurationHelper.appendAttributeIfNotNull(informationElement, "os", this.getOs());
    ConfigurationHelper.appendAttributeIfNotNull(informationElement, "arch", this.getArch());
    ConfigurationHelper.appendAttributeIfNotNull(informationElement, "platform", this.getPlatform());
    ConfigurationHelper.appendAttributeIfNotNull(informationElement, "locale", this.getLocale());

    ConfigurationHelper.appendElement(project, informationElement, this.getTitle());
    ConfigurationHelper.appendElement(project, informationElement, this.getVendor());
    ConfigurationHelper.appendElement(project, informationElement, this.getHomepage());
    ConfigurationHelper.appendElements(project, informationElement, this.getDescription());
    ConfigurationHelper.appendElements(project, informationElement, this.getIcon());
    ConfigurationHelper.appendElement(project, informationElement, this.getOfflineallowed());
    ConfigurationHelper.appendElement(project, informationElement, this.getAssociation());
    ConfigurationHelper.appendElement(project, informationElement, this.getShortcut());
    ConfigurationHelper.appendElement(project, informationElement, this.getRelatedContent());

  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public SimpleTextElement createTitle() {
    if(this.myTitle == null) {
      this.myTitle = new SimpleTextElement("title");
    }
    return this.myTitle;
  }
  public SimpleTextElement getTitle() {
    return this.myTitle;
  }

  public SimpleTextElement createVendor() {
    if(this.myVendor == null) {
      this.myVendor = new SimpleTextElement("vendor");
    }
    return this.myVendor;
  }
  public SimpleTextElement getVendor() {
    return this.myVendor;
  }

  public OfflineAllowedElement createOfflineallowed() {
    if(this.myOfflineallowed == null) {
      this.myOfflineallowed = new OfflineAllowedElement();
    }
    return this.myOfflineallowed;
  }
  public OfflineAllowedElement getOfflineallowed() {
    return this.myOfflineallowed;
  }

  public AssociationElement createAssociation() {
    if(this.myAssociation == null) {
      this.myAssociation = new AssociationElement();
    }
    return this.myAssociation;
  }
  public AssociationElement getAssociation() {
    return this.myAssociation;
  }

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

  public String getPlatform() {
    return this.myPlatform;
  }
  public void setPlatform(String platform) {
    this.myPlatform = platform;
  }

  public String getLocale() {
    return this.myLocale;
  }
  public void setLocale(String locale) {
    this.myLocale = locale;
  }

  public HomepageElement getHomepage() {
    return this.myHomepage;
  }
  public void setHomepage(HomepageElement homepage) {
    this.myHomepage = homepage;
  }

  public DescriptionElement createDescription() {
    DescriptionElement DescriptionElement = new DescriptionElement();
    this.getDescription().add(DescriptionElement);
    return DescriptionElement;
  }
  public List<DescriptionElement> getDescription() {
    return this.myDescription;
  }

  public IconElement createIcon() {
    IconElement iconElement = new IconElement();
    this.getIcon().add(iconElement);
    return iconElement;
  }
  public List<IconElement> getIcon() {
    return this.myIcon;
  }

  public ShortcutElement createShortcut() {
    if(this.myShortcut == null) {
      this.myShortcut = new ShortcutElement();
    }
    return this.myShortcut;
  }
  public ShortcutElement getShortcut() {
    return this.myShortcut;
  }

  public RelatedContentElement createRelatedContent() {
    if(this.myRelatedContent == null) {
      this.myRelatedContent = new RelatedContentElement();
    }
    return this.myRelatedContent;
  }
  public RelatedContentElement getRelatedContent() {
    return this.myRelatedContent;
  }

}