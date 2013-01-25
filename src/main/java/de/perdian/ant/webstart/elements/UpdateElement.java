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

import org.apache.tools.ant.types.EnumeratedAttribute;
import org.w3c.dom.Element;

import de.perdian.ant.webstart.JnlpTask;

public class UpdateElement implements ConfigurationElement {

  private CheckAttribute myCheck = null;
  private PolicyAttribute myPolicy = null;

  @Override
  public void appendXml(JnlpTask task, Element parentElement) {
    Element updateElement = ConfigurationHelper.appendElement(parentElement, "update");
    ConfigurationHelper.appendAttributeIfNotNull(updateElement, "check", this.getCheck());
    ConfigurationHelper.appendAttributeIfNotNull(updateElement, "policy", this.getPolicy());
  }

  // ---------------------------------------------------------------------------
  // --- Inner classes ---------------------------------------------------------
  // ---------------------------------------------------------------------------

  public static class CheckAttribute extends EnumeratedAttribute {
    @Override public String[] getValues() {
      return new String[] { "always", "timeout", "attribute" };
    }
  }

  public static class PolicyAttribute extends EnumeratedAttribute {
    @Override public String[] getValues() {
      return new String[] { "always", "prompt-update", "prompt-run" };
    }
  }

  // ---------------------------------------------------------------------------
  // --- Property access methods -----------------------------------------------
  // ---------------------------------------------------------------------------

  public CheckAttribute getCheck() {
    return this.myCheck;
  }
  public void setCheck(CheckAttribute check) {
    this.myCheck = check;
  }

  public PolicyAttribute getPolicy() {
    return this.myPolicy;
  }
  public void setPolicy(PolicyAttribute policy) {
    this.myPolicy = policy;
  }

}