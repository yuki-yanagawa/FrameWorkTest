package testframe.gui.model;

public class PropertiesFileModel {
	private Object key_;
	private Object value_;
	
	public PropertiesFileModel(Object key, Object value) {
		key_ = key;
		value_ = value;
	}
	
	public String createInputHTML() {
		return "<label>" + key_.toString() + " : </label><input type='text' value='" + value_.toString() + "'></br>";
	}
}
