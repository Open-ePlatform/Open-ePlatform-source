
package com.nordicpeak.flowengine.integration.callback;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import se.unlogic.standardutils.string.StringUtils;

/**
 * <p>
 * Java class for Principal complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Principal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrincipalGroup", propOrder = { "name"
})
public class PrincipalGroup {

	@XmlElement(nillable = true)
	protected String name;

	/** Gets the value of the name property.
	 * 
	 * @return
	 * 				possible object is
	 *         {@link String } */
	public String getName() {
		return name;
	}

	/** Sets the value of the name property.
	 * 
	 * @param value
	 *          allowed object is
	 *          {@link String } */
	public void setName(String value) {
		this.name = value;
	}

	@Override
	public String toString() {
		
		if (name == null) {
			return "''";
		}
		
		return StringUtils.toLogFormat(name, 30);
	}

}
