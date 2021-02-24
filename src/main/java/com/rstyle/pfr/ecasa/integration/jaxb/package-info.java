@XmlSchema(
        namespace = "http://rstyle.com/2014/mcaLoader",
        elementFormDefault = XmlNsForm.QUALIFIED,

        xmlns = {
                @XmlNs(prefix = "", namespaceURI="http://rstyle.com/2014/mcaLoader")
        }
)
package com.rstyle.pfr.ecasa.integration.jaxb;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;