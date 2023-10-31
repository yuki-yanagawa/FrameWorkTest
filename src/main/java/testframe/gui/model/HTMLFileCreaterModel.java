package testframe.gui.model;

import java.util.HashMap;
import java.util.Map;

public class HTMLFileCreaterModel implements FileCreaterModel {
	private final String requestData_;
	private final Map<String, Object> paramData_; 
	public HTMLFileCreaterModel(String requestData, Map<String, Object> paramData) {
		requestData_ = requestData;
		paramData_ = new HashMap<>(paramData);
	}
	@Override
	public String getRequestData() {
		return requestData_;
	}

	@Override
	public Map<String, Object> getParamData() {
		return paramData_;
	}
	
}
