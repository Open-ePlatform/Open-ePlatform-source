package com.nordicpeak.flowengine.attachments;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import se.unlogic.hierarchy.core.beans.User;
import se.unlogic.standardutils.dao.annotations.DAOManaged;
import se.unlogic.standardutils.dao.annotations.Key;
import se.unlogic.standardutils.dao.annotations.OrderBy;
import se.unlogic.standardutils.dao.annotations.Table;
import se.unlogic.standardutils.enums.Order;
import se.unlogic.standardutils.io.BinarySizeFormater;
import se.unlogic.standardutils.string.StringUtils;
import se.unlogic.standardutils.xml.GeneratedElementable;
import se.unlogic.standardutils.xml.XMLElement;
import se.unlogic.standardutils.xml.XMLUtils;

@Table(name = "flowengine_flow_instance_attachments")
@XMLElement
public class Attachment extends GeneratedElementable implements Serializable {
	
	private static final long serialVersionUID = 3007617738831539142L;
	
	@DAOManaged(autoGenerated = true)
	@Key
	@XMLElement
	protected Integer attachmentID;
	
	@DAOManaged
	@XMLElement
	protected Integer flowInstanceID;
	
	@DAOManaged
	@XMLElement
	private String filename;
	
	@DAOManaged
	@XMLElement
	private Long size;
	
	@DAOManaged(dontUpdateIfNull = true)
	@XMLElement(name = "poster")
	protected User poster;
	
	@DAOManaged
	@OrderBy(order = Order.ASC)
	@XMLElement
	protected Timestamp added;
	
	@DAOManaged
	private transient Blob data;
	
	public Integer getFlowInstanceID() {
		return flowInstanceID;
	}
	
	public void setFlowInstanceID(Integer flowInstanceID) {
		this.flowInstanceID = flowInstanceID;
	}
	
	public Integer getAttachmentID() {
		
		return attachmentID;
	}
	
	public void setAttachmentID(Integer attachmentID) {
		
		this.attachmentID = attachmentID;
	}
	
	public User getPoster() {
		return poster;
	}
	
	public void setPoster(User poster) {
		this.poster = poster;
	}
	
	public String getFilename() {
		
		return filename;
	}
	
	public void setFilename(String filename) {
		
		this.filename = filename;
	}
	
	public Long getSize() {
		
		return size;
	}
	
	public void setSize(Long size) {
		
		this.size = size;
	}
	
	public Timestamp getAdded() {
		
		return added;
	}
	
	public void setAdded(Timestamp added) {
		
		this.added = added;
	}
	
	public Blob getData() {
		
		return data;
	}
	
	public void setData(Blob data) {
		
		this.data = data;
	}
	
	@Override
	public Element toXML(Document doc) {
		
		Element attachmentElement = super.toXML(doc);
		
		XMLUtils.appendNewElement(doc, attachmentElement, "FormatedSize", BinarySizeFormater.getFormatedSize(size));
		
		return attachmentElement;
	}
	
	@Override
	public String toString() {
		
		return StringUtils.toLogFormat(filename, 50) + " (ID: " + attachmentID + ")";
	}
}
