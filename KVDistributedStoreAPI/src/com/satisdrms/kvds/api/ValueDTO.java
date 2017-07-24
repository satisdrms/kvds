package com.satisdrms.kvds.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ValueDTO {
	@XmlElement
	String value;

}
