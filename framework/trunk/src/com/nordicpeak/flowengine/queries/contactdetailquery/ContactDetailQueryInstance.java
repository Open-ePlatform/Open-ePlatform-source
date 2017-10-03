package com.nordicpeak.flowengine.queries.contactdetailquery;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import se.unlogic.hierarchy.core.beans.User;
import se.unlogic.hierarchy.core.interfaces.AttributeHandler;
import se.unlogic.hierarchy.core.interfaces.MutableAttributeHandler;
import se.unlogic.standardutils.dao.annotations.DAOManaged;
import se.unlogic.standardutils.dao.annotations.Key;
import se.unlogic.standardutils.dao.annotations.ManyToOne;
import se.unlogic.standardutils.dao.annotations.Table;
import se.unlogic.standardutils.reflection.ReflectionUtils;
import se.unlogic.standardutils.string.StringUtils;
import se.unlogic.standardutils.xml.XMLElement;
import se.unlogic.standardutils.xml.XMLUtils;

import com.nordicpeak.flowengine.beans.Contact;
import com.nordicpeak.flowengine.interfaces.ColumnExportableQueryInstance;
import com.nordicpeak.flowengine.interfaces.ContactQueryInstance;
import com.nordicpeak.flowengine.interfaces.QueryHandler;
import com.nordicpeak.flowengine.interfaces.StringValueQueryInstance;
import com.nordicpeak.flowengine.queries.basequery.BaseQueryInstance;

@Table(name = "contact_detail_query_instances")
@XMLElement
public class ContactDetailQueryInstance extends BaseQueryInstance implements StringValueQueryInstance, ColumnExportableQueryInstance, ContactQueryInstance {
	
	
	private static final long serialVersionUID = -7761759005604863873L;
	
	public static final Field QUERY_RELATION = ReflectionUtils.getField(ContactDetailQueryInstance.class, "query");
	
	@DAOManaged
	@Key
	@XMLElement
	private Integer queryInstanceID;
	
	@DAOManaged(columnName = "queryID")
	@ManyToOne
	@XMLElement
	private ContactDetailQuery query;
	
	@DAOManaged
	@XMLElement
	private String firstname;
	
	@DAOManaged
	@XMLElement
	private String lastname;
	
	@DAOManaged
	@XMLElement
	private String address;
	
	@DAOManaged
	@XMLElement
	private String zipCode;
	
	@DAOManaged
	@XMLElement
	private String postalAddress;
	
	@DAOManaged
	@XMLElement
	private String phone;
	
	@DAOManaged
	@XMLElement
	private String email;
	
	@DAOManaged
	@XMLElement
	private String mobilePhone;
	
	@DAOManaged
	@XMLElement
	private boolean contactBySMS;
	
	@DAOManaged
	@XMLElement
	private boolean persistUserProfile = true;
	
	@DAOManaged
	@XMLElement
	private String citizenID;
	
	@DAOManaged
	@XMLElement
	private String careOf;
	
	@DAOManaged
	@XMLElement
	private boolean officalAddress;
	
	@XMLElement
	private boolean isMutableUserWithAccess;
	
	public String getAddress() {
		
		return address;
	}
	
	public String getFirstname() {
		
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		
		this.firstname = firstname;
	}
	
	public String getLastname() {
		
		return lastname;
	}
	
	public void setLastname(String lastname) {
		
		this.lastname = lastname;
	}
	
	public void setAddress(String address) {
		
		this.address = address;
	}
	
	public String getZipCode() {
		
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		
		this.zipCode = zipCode;
	}
	
	public String getPostalAddress() {
		
		return postalAddress;
	}
	
	public void setPostalAddress(String postalAddress) {
		
		this.postalAddress = postalAddress;
	}
	
	public String getPhone() {
		
		return phone;
	}
	
	public void setPhone(String phone) {
		
		this.phone = phone;
	}
	
	public String getEmail() {
		
		return email;
	}
	
	public void setEmail(String email) {
		
		this.email = email;
	}
	
	public String getMobilePhone() {
		
		return mobilePhone;
	}
	
	public void setMobilePhone(String mobilePhone) {
		
		this.mobilePhone = mobilePhone;
	}
	
	public boolean isContactBySMS() {
		
		return contactBySMS;
	}
	
	public void setContactBySMS(boolean contactBySMS) {
		
		this.contactBySMS = contactBySMS;
	}
	
	public boolean isPersistUserProfile() {
		
		return persistUserProfile;
	}
	
	public void setPersistUserProfile(boolean persistUserProfile) {
		
		this.persistUserProfile = persistUserProfile;
	}
	
	public String getCareOf() {
		return careOf;
	}
	
	public void setCareOf(String careOf) {
		this.careOf = careOf;
	}
	
	public Integer getQueryInstanceID() {
		
		return queryInstanceID;
	}
	
	public void setQueryInstanceID(Integer queryInstanceID) {
		
		this.queryInstanceID = queryInstanceID;
	}
	
	@Override
	public ContactDetailQuery getQuery() {
		
		return query;
	}
	
	public void setQuery(ContactDetailQuery query) {
		
		this.query = query;
	}
	
	public boolean isMutableUserWithAccess() {
		
		return isMutableUserWithAccess;
	}
	
	public void setMutableUserWithAccess(boolean isMutableUserWithAccess) {
		
		this.isMutableUserWithAccess = isMutableUserWithAccess;
	}
	
	@Override
	public void reset(MutableAttributeHandler attributeHandler) {
		
		//Preserve fields in case they have been prepopulated
		
		super.reset(attributeHandler);
	}
	
	public void fullReset(MutableAttributeHandler attributeHandler) {
		
		citizenID = null;
		firstname = null;
		lastname = null;
		careOf = null;
		
		address = null;
		zipCode = null;
		postalAddress = null;
		
		email = null;
		phone = null;
		mobilePhone = null;
		
		contactBySMS = false;
		persistUserProfile = false;
		officalAddress = false;
		
		reset(attributeHandler);
	}
	
	@Override
	public String toString() {
		
		return "ContactDetailQueryInstance (queryInstanceID=" + queryInstanceID + ")";
	}
	
	public void initialize(User poster) {
		
		// We don't set firstname, lastname or citizenID here as they are always overridden by the posting user on view and population
		
		email = poster.getEmail();
		
		AttributeHandler attributeHandler = poster.getAttributeHandler();
		
		if (attributeHandler != null) {
			
			mobilePhone = attributeHandler.getString("mobilePhone");
			phone = attributeHandler.getString("phone");
			contactBySMS = attributeHandler.getPrimitiveBoolean("contactBySMS");
			
			address = attributeHandler.getString("address");
			zipCode = attributeHandler.getString("zipCode");
			postalAddress = attributeHandler.getString("postalAddress");
			
			if (query.usesOfficalAddress()) {
				
				updateOfficialAddress(poster);
			}
		}
	}
	
	public void updateOfficialAddress(User poster) {
		
		AttributeHandler attributeHandler = poster.getAttributeHandler();
		
		if (attributeHandler != null) {
			
			String officialCareOf = attributeHandler.getString("official.careOf");
			String officialAddress = attributeHandler.getString("official.address");
			String officialZipCode = attributeHandler.getString("official.zipCode");
			String officialPostalAddress = attributeHandler.getString("official.postalAddress");
			
			if (!StringUtils.isEmpty(officialAddress, officialZipCode, officialPostalAddress)) {
				
				careOf = officialCareOf;
				address = officialAddress;
				zipCode = officialZipCode;
				postalAddress = officialPostalAddress;
				officalAddress = true;
			}
		}
	}
	
	@Override
	public Element toExportXML(Document doc, QueryHandler queryHandler) throws Exception {
		
		Element element = getBaseExportXML(doc);
		
		if (query.getFieldName() != ContactDetailQueryField.HIDDEN) {
			XMLUtils.appendNewCDATAElement(doc, element, "Firstname", firstname);
		}
		
		if (query.getFieldName() != ContactDetailQueryField.HIDDEN) {
			XMLUtils.appendNewCDATAElement(doc, element, "Lastname", lastname);
		}
		
		if (query.getFieldCareOf() != ContactDetailQueryField.HIDDEN) {
			XMLUtils.appendNewCDATAElement(doc, element, "CareOf", careOf);
		}
		
		if (query.getFieldAddress() != ContactDetailQueryField.HIDDEN) {
			XMLUtils.appendNewCDATAElement(doc, element, "Address", address);
		}
		
		if (query.getFieldAddress() != ContactDetailQueryField.HIDDEN) {
			XMLUtils.appendNewCDATAElement(doc, element, "ZipCode", zipCode);
		}
		
		if (query.getFieldAddress() != ContactDetailQueryField.HIDDEN) {
			XMLUtils.appendNewCDATAElement(doc, element, "PostalAddress", postalAddress);
		}
		
		if (query.getFieldPhone() != ContactDetailQueryField.HIDDEN) {
			XMLUtils.appendNewCDATAElement(doc, element, "Phone", phone);
		}
		
		if (query.getFieldEmail() != ContactDetailQueryField.HIDDEN) {
			XMLUtils.appendNewCDATAElement(doc, element, "Email", email);
		}
		
		if (query.getFieldMobilePhone() != ContactDetailQueryField.HIDDEN) {
			XMLUtils.appendNewCDATAElement(doc, element, "MobilePhone", mobilePhone);
		}
		
		if (query.getFieldCitizenID() != ContactDetailQueryField.HIDDEN) {
			XMLUtils.appendNewCDATAElement(doc, element, "SocialSecurityNumber", citizenID);
		}
		
		if (query.getFieldMobilePhone() != ContactDetailQueryField.HIDDEN && query.isAllowSMSNotification()) {
			XMLUtils.appendNewCDATAElement(doc, element, "ContactBySMS", contactBySMS);
		}
		
		return element;
	}
	
	@Override
	public String getStringValue() {
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(firstname + " " + lastname);
		
		if (address != null) {
			
			builder.append("\n" + address);
		}
		
		if (zipCode != null && postalAddress != null) {
			
			builder.append("\n" + zipCode + " " + postalAddress);
		}
		
		if (phone != null) {
			
			builder.append("\n" + phone);
		}
		
		if (mobilePhone != null) {
			
			builder.append("\n" + mobilePhone);
		}
		
		if (email != null) {
			
			builder.append("\n" + email);
		}
		
		return builder.toString();
	}
	
	public String getCitizenID() {
		
		return citizenID;
	}
	
	public void setCitizenID(String citizenID) {
		
		this.citizenID = citizenID;
	}
	
	@Override
	public List<String> getColumnLabels(QueryHandler queryHandler) {
		
		ContactDetailQueryProviderModule queryProvider = queryHandler.getQueryProvider(getQueryInstanceDescriptor().getQueryDescriptor().getQueryTypeID(), ContactDetailQueryProviderModule.class);
		
		List<String> labels = new ArrayList<String>();
		
		if (query.getFieldName() != ContactDetailQueryField.HIDDEN) {
			
			labels.add(getQueryInstanceDescriptor().getQueryDescriptor().getName() + ": " + queryProvider.getExportFirstName());
			labels.add(getQueryInstanceDescriptor().getQueryDescriptor().getName() + ": " + queryProvider.getExportLastName());
		}
		
		if (query.getFieldCareOf() != ContactDetailQueryField.HIDDEN) {
			labels.add(getQueryInstanceDescriptor().getQueryDescriptor().getName() + ": " + queryProvider.getExportCareOf());
		}
		
		if (query.getFieldAddress() != ContactDetailQueryField.HIDDEN) {
			
			labels.add(getQueryInstanceDescriptor().getQueryDescriptor().getName() + ": " + queryProvider.getExportAddress());
			labels.add(getQueryInstanceDescriptor().getQueryDescriptor().getName() + ": " + queryProvider.getExportZipCode());
			labels.add(getQueryInstanceDescriptor().getQueryDescriptor().getName() + ": " + queryProvider.getExportPostalAddress());
		}
		
		if (query.getFieldPhone() != ContactDetailQueryField.HIDDEN) {
			labels.add(getQueryInstanceDescriptor().getQueryDescriptor().getName() + ": " + queryProvider.getExportPhone());
		}
		
		if (query.getFieldEmail() != ContactDetailQueryField.HIDDEN) {
			labels.add(getQueryInstanceDescriptor().getQueryDescriptor().getName() + ": " + queryProvider.getExportEmail());
		}
		
		if (query.getFieldMobilePhone() != ContactDetailQueryField.HIDDEN) {
			labels.add(getQueryInstanceDescriptor().getQueryDescriptor().getName() + ": " + queryProvider.getExportMobilePhone());
		}
		
		if (query.getFieldCitizenID() != ContactDetailQueryField.HIDDEN) {
			labels.add(getQueryInstanceDescriptor().getQueryDescriptor().getName() + ": " + queryProvider.getExportCitizenID());
		}
		
		return labels;
	}
	
	@Override
	public List<String> getColumnValues() {
		
		List<String> values = new ArrayList<String>();
		
		if (query.getFieldName() != ContactDetailQueryField.HIDDEN) {
			
			values.add(getFirstname());
			values.add(getLastname());
		}
		
		if (query.getFieldCareOf() != ContactDetailQueryField.HIDDEN) {
			values.add(getCareOf());
		}
		
		if (query.getFieldAddress() != ContactDetailQueryField.HIDDEN) {
			
			values.add(getAddress());
			values.add(getZipCode());
			values.add(getPostalAddress());
		}
		
		if (query.getFieldPhone() != ContactDetailQueryField.HIDDEN) {
			values.add(getPhone());
		}
		
		if (query.getFieldEmail() != ContactDetailQueryField.HIDDEN) {
			values.add(getEmail());
		}
		
		if (query.getFieldMobilePhone() != ContactDetailQueryField.HIDDEN) {
			values.add(getMobilePhone());
		}
		
		if (query.getFieldCitizenID() != ContactDetailQueryField.HIDDEN) {
			values.add(getCitizenID());
		}
		
		return values;
	}
	
	public boolean isOfficalAddress() {
		
		return officalAddress;
	}
	
	public void setOfficalAddress(boolean officalAddress) {
		
		this.officalAddress = officalAddress;
	}
	
	public boolean isPopulated() {
		
		if (StringUtils.isEmpty(firstname) && StringUtils.isEmpty(lastname) && StringUtils.isEmpty(address) && StringUtils.isEmpty(zipCode) && StringUtils.isEmpty(postalAddress) && StringUtils.isEmpty(phone) && StringUtils.isEmpty(email) && StringUtils.isEmpty(mobilePhone) && StringUtils.isEmpty(citizenID) && StringUtils.isEmpty(careOf)) {
			
			return false;
		}
		
		return true;
	}
	
	@Override
	public Contact getContact() {
		
		if (isPopulated()) {
			
			Contact contact = new Contact();
			
			contact.setFirstname(getFirstname());
			contact.setLastname(getLastname());
			contact.setEmail(getEmail());
			contact.setMobilePhone(getMobilePhone());
			contact.setPhone(getPhone());
			contact.setCareOf(getCareOf());
			contact.setAddress(getAddress());
			contact.setPostalAddress(getPostalAddress());
			contact.setZipCode(getZipCode());
			contact.setCitizenIdentifier(getCitizenID());
			
			contact.setContactBySMS(isContactBySMS());
			contact.setContactByEmail(true);
			
			return contact;
		}
		
		return null;
	}
	
	@Override
	public boolean updatedPoster() {
		return isPersistUserProfile();
	}
	
}
