package testframe.framework.recvier.handle.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import testframe.framework.common.http.HttpResFrame;
import testframe.framework.common.http.HttpResponseHeader;
import testframe.framework.common.log.Logger;
import testframe.framework.common.properties.PropFileReader;
import testframe.framework.controllercache.ControllerCache;
import testframe.framework.recvier.handle.exception.HttpCreateResponseException;
import testframe.framework.router.RouterContainer;
import testframe.framework.router.RouterFileReader;

public class HttpHandleUtil {
	private static int recvBufSize = 2048;
	private static String serverName;
	
	public static int getRecvBufize() {
		return recvBufSize;
	}

	public static String httpRequestAnalizeAndCreateResponse(String httpRequest) throws HttpCreateResponseException {
		String retStr = "";
		String[] httpRequestLine = httpRequest.split("\r\n");
		String httpRequestHeader = httpRequestLine[0];
		String[] httpRequestHeaderLine = httpRequestHeader.split(" ");
		if(httpRequestHeaderLine.length < 2) {
			throw new HttpCreateResponseException("CreateResponsError");
		}
		String requestMethod = httpRequestHeaderLine[0].trim();
		String requestData = httpRequestHeaderLine[1].trim();
		
		String excludeHeaderHttpRequest = httpRequest.substring(httpRequestHeader.length());
		Map<String,String> requestParameterMap = new HashMap<>();
		requestParameterMapConverter(httpRequestLine,requestParameterMap);
		
		Map<String,Object> routesrFileMapAllList = RouterFileReader.getInstance().getRouterFileMap();
		Map<String,String> routerFileMap = (Map<String,String>)routesrFileMapAllList.get(requestMethod);
		String actionClassPath = routerFileMap.get(requestData);
		if(actionClassPath == null || actionClassPath.trim().equals("")) {
		Logger.warn(Thread.currentThread().getName(), "this web api is not define : " + requestData);
			return HttpResponseHeader.createResponseHeaderBad();
		}
		if(ControllerCache.getInstance().existData(actionClassPath)) {
			Logger.info(Thread.currentThread().getName(), actionClassPath + " is From Cashe");
			HttpResFrame httpResFrame = ControllerCache.getInstance().getData(actionClassPath);
			return httpResFrame.createResponse();
		}
		if(requestMethod.toUpperCase().equals("POST")) {
			//Web API
			int dataLength = 0;
			dataLength = requestParameterMap.get("CONTENT-LENGTH") != null ? Integer.parseInt(requestParameterMap.get("CONTENT-LENGTH")) : 0;
			if(dataLength == 0) {
				try { 
					HttpResFrame httpResFrame = actionCalssCallHandler(actionClassPath);
					return httpResFrame.createResponse();
				} catch(Exception e) {
					e.printStackTrace();
					return HttpResponseHeader.createResponseHeaderInternalServerError(e.getMessage());
				}
			} else {
				String requestPrameterData = excludeHeaderHttpRequest.trim().substring(excludeHeaderHttpRequest.trim().length() - dataLength);
				Map<String,Object> reqMap = null;
				try {
					requestPrameterData = URLDecoder.decode(requestPrameterData, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					requestPrameterData = "";
				}
				if(requestPrameterData.trim().equals("")) {
					reqMap = new HashMap<>();
				} else {
					reqMap = createReqParamMap(requestPrameterData);
				}
				try { 
					HttpResFrame httpResFrame = actionCalssCallHandler(actionClassPath, reqMap);
					return httpResFrame.createResponse();
				} catch(Exception e) {
					e.printStackTrace();
					return HttpResponseHeader.createResponseHeaderInternalServerError(e.getMessage());
				}
			}
			
		} else if(requestMethod.toUpperCase().equals("GET")) {
			try {
				HttpResFrame httpResFrame = actionCalssCallHandler(actionClassPath);
				if(!ControllerCache.getInstance().existData(actionClassPath)) {
					Logger.info(Thread.currentThread().getName(), actionClassPath + " join Cashe");
					ControllerCache.getInstance().addCacheData(actionClassPath, httpResFrame);
				}
				return httpResFrame.createResponse();
			} catch(Exception e) {
				e.printStackTrace();
				return HttpResponseHeader.createResponseHeaderInternalServerError(e.getMessage());
			}
		}
		return retStr;
	}
	
	public static String getServerName() {
		if(serverName == null) {
			serverName = (String)PropFileReader.getProperties().get("settingPort");
		}
		return serverName;
	}

	private static void requestParameterMapConverter(String[] requestParameterLine ,Map<String,String> requestparameterMap) {
		for(String requestParamter : requestParameterLine) {
			if(requestParamter.matches("\\s*(\\w|-)+\\s*:\\s*(\\w|-)+\\s*")) {
				String[] tmpLine = requestParamter.split(":");
				requestparameterMap.put(tmpLine[0].toUpperCase().trim(), tmpLine[1].toUpperCase().trim());
			}
		}
	}

	private static HttpResFrame actionCalssCallHandler(String actionClassPath, Object... requestParameterData) throws Exception {
		return RouterContainer.<HttpResFrame>callFunction(actionClassPath, requestParameterData);
	}

	private static Map<String, Object> createReqParamMap(String reqParamRawData) {
		if(reqParamRawData.matches("^\\{.*\\}")) {
			return createReqParamMapForJsonParam(reqParamRawData);
		} else {
			return createReqParamMapForSubmitParam(reqParamRawData);
		}
	}

	private static Map<String, Object> createReqParamMapForSubmitParam(String reqParamRawData) {
		return new HashMap<String,Object>();
	}

	private static Map<String, Object> createReqParamMapForJsonParam(String reqParamRawData) {
		Map<String, Object> retMap = new HashMap<String,Object>();
		reqParamRawData = reqParamRawData.replaceAll("\\{|\\}", "");
		String[] reqParamRawDataLine = reqParamRawData.split(",");
		try {
			for(String r : reqParamRawDataLine) {
				String key = r.split(":")[0].trim();
				String value = r.split(":")[1].trim();
				retMap.put(delimterStringQuataionType(key),delimterStringQuataionType(value));
			}
		} catch(Exception e) {
			retMap = new HashMap<String, Object>();
		}
		return retMap;
	}

	private static String delimterStringQuataionType(String data) {
		if(data.matches("^\".*\"$")) {
			data = data.replaceAll("^\"|\"$", "");
		}
		return data;
	}
}
