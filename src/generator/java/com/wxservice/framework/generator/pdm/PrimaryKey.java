package com.wxservice.framework.generator.pdm;
  
public class PrimaryKey {
	public Column ref = null;

	public String toString() {
		if (ref != null) {
			return ref.toString();
		} else {
			return "NO_EXIST_PRIMARKY_KEY";
		}
	}

	public Column getRef() {
		return ref;
	}

	public void setRef(Column ref) {
		this.ref = ref;
	}

	public boolean equals(Object arg0) {
		return ref.equals(arg0);
	}

	public String getCode() {
		return ref.getCode();
	}

	public String getCodeText() {
		return ref.getCodeText();
	}

	public String getComment() {
		return ref.getComment();
	}

	public String getCommentText() {
		return ref.getCommentText();
	}

	public String getCreationDate() {
		return ref.getCreationDate();
	}

	public String getCreator() {
		return ref.getCreator();
	}

	public String getDataType() {
		return ref.getDataType();
	}

	public String getDataTypeText() {
		return ref.getDataTypeText();
	}

	public String getDefaultValue() {
		return ref.getDefaultValue();
	}

	public String getDefaultValueText() {
		return ref.getDefaultValueText();
	}

	public String getId() {
		return ref.getId();
	}

	public String getLength() {
		return ref.getLength();
	}

	public String getLengthText() {
		return ref.getLengthText();
	}

	public String getMandatory() {
		return ref.getMandatory();
	}

	public String getModificationDate() {
		return ref.getModificationDate();
	}

	public String getModifier() {
		return ref.getModifier();
	}

	public String getName() {
		return ref.getName();
	}

	public String getNameText() {
		return ref.getNameText();
	}

	public String getObjectID() {
		return ref.getObjectID();
	}

	public String getPrecision() {
		return ref.getPrecision();
	}

	public String getPrecisionText() {
		return ref.getPrecisionText();
	}

	public Table getTable() {
		return ref.getTable();
	}

	public int hashCode() {
		return ref.hashCode();
	}

	public boolean noNull() {
		return ref.noNull();
	}

	public void setCode(String code) {
		ref.setCode(code);
	}

	public void setComment(String comment) {
		ref.setComment(comment);
	}

	public void setCreationDate(String creationDate) {
		ref.setCreationDate(creationDate);
	}

	public void setCreator(String creator) {
		ref.setCreator(creator);
	}

	public void setDataType(String dataType) {
		ref.setDataType(dataType);
	}

	public void setDefaultValue(String defaultValue) {
		ref.setDefaultValue(defaultValue);
	}

	public void setId(String id) {
		ref.setId(id);
	}

	public void setLength(String length) {
		ref.setLength(length);
	}

	public void setMandatory(String mandatory) {
		ref.setMandatory(mandatory);
	}

	public void setModificationDate(String modificationDate) {
		ref.setModificationDate(modificationDate);
	}

	public void setModifier(String modifier) {
		ref.setModifier(modifier);
	}

	public void setName(String name) {
		ref.setName(name);
	}

	public void setObjectID(String objectID) {
		ref.setObjectID(objectID);
	}

	public void setPrecision(String precision) {
		ref.setPrecision(precision);
	}

	public void setTable(Table table) {
		ref.setTable(table);
	}

}
