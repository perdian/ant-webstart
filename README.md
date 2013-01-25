# Ant Webstart task

Ant task to generate a deployment structure for a webstart enabled application.

An example:

    <p:jnlp destfile="/some/location/foo.jnlp">
      <p:information
        title="the application"
        vendor="the vendor"
        offline-allowed="true"
      />
    </p:webstart>

Let's take a look at the parameters:

## Element "jnlp" (root) ##

Attribute "destfile" *(required)*

> The target file into which the JNLP output

## Element "information" (required) ##

This required child element basically mirrors the XML structure from the JNLP
documentation and contains information about application itself.

Attribute "title" *(required)*

> The main title of your application

Attribute "vendor" *(required)*

> The vendor that created the application

Attribute "offline-allowed" *(optional, default = false)*

> Boolean value specifiying whether or not the application might be used offline.