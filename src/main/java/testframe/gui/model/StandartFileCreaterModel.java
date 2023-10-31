package testframe.gui.model;

import java.util.HashMap;
import java.util.Map;

public class StandartFileCreaterModel implements FileCreaterModel {
	private final String requestData_;
	public StandartFileCreaterModel(String requestData) {
		requestData_ = requestData;
	}
	@Override
	public String getRequestData() {
		return requestData_;
	}
	
	@Override
	public Map<String, Object> getParamData() {
		return new HashMap<>();
	}
}
