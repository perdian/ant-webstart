# Ant Webstart task

Ant task to generate a deployment structure for a webstart enabled application.

An example:

    <project xmlns:p="antlib:de.perdian.ant.webstart">

    <taskdef uri="antlib:de.perdian.ant.webstart" classpath="/path/to/webstart.jar" />
    <p:jnlp destfile="${build.target.directory}/webstart/test.jnlp">
      <information>
        <title>aTitle</title>
        <vendor>aVendor</vendor>
        <offlineallowed />
        <description>Short Description</description>
      </information>
      <resources>
        <path>
          <fileset dir="${build.target.directory}/webstart/libs/">
          </fileset>
        </path>
      </resources>
      <security>
        <allpermissions />
      </security>
      <applicationdesc mainclass="de.perdian.google.api.apps.impl.driveclient.DriveClientLauncher" />
    </p:jnlp>

All in all, the declaration of the target JNLP file in the Ant task ist almost
identical to the actual JNLP file, as it is defined in the specification
(http://docs.oracle.com/javase/7/docs/technotes/guides/javaws/developersguide/syntax.html).
The main exception being, that wherever slashes in the specification are
allowed, (for example within the <code>offline-allowed</code> element, there are
no slashed allowed for the ant task (which means the tag is to be added as
<code>offlineallowed</code>).

Additionally, to all the standard JNLP elements, the <code>resources</code>
elements supports adding a <code>path</code> child, as described in the
example above. The existence of such a child means, that the task will analyze
the contents of the path and will add a <code>jar</code> child element to the
resources parent element for each of the entries found in the path. This
allows for a dynamic way to add required resources, without actually having to
declare them over and over again. The path's to the resource files (usually
JARs) will be relative to the location of the JNLP file itself (as defined in
the <code>destfile</code> attribute of the main task element).