package com.satisdrms.kvds.test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ValueDTO {
	@XmlElement
	String value;

}
