package testframe.framework.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import testframe.framework.common.http.ContentType;
import testframe.framework.common.http.HttpResFrame;
import testframe.framework.common.http.HttpResponseHeader;
import testframe.gui.model.PropertiesFileModel;

public abstract class ControllerFrame {
	public HttpResFrame html(String path) {
		try(FileInputStream fi = new FileInputStream(path)){
			File fileData = new File(path);
			long filesize = Files.size(fileData.toPath());
			byte[] readfile = new byte[(int)filesize];
			fi.read(readfile);
			String header = HttpResponseHeader.createResponseHeaderSuccess(ContentType.HTML,(int)filesize);
			header = header + "\r\n";
			String body = new String(readfile,"UTF-8");
			return new HttpResFrame(header, body, ContentType.HTML);
		} catch(IOException e) {
			String header = HttpResponseHeader.createResponseHeaderInternalServerError();
			return new HttpResFrame(header, "", ContentType.HTML);
		}
	} 
	
	public HttpResFrame css(String path) {
		try(FileInputStream fi = new FileInputStream(path)){
			File fileData = new File(path);
			long filesize = Files.size(fileData.toPath());
			byte[] readfile = new byte[(int)filesize];
			fi.read(readfile);
			String header = HttpResponseHeader.createResponseHeaderSuccess(ContentType.CSS,(int)filesize);
			header = header + "\r\n";
			String body = new String(readfile,"UTF-8");
			return new HttpResFrame(header, body, ContentType.CSS);
		} catch(IOException e) {
			String header = HttpResponseHeader.createResponseHeaderInternalServerError();
			return new HttpResFrame(header, "", ContentType.CSS);
		}
	}

	public HttpResFrame js(String path) {
		try(FileInputStream fi = new FileInputStream(path)){
			File fileData = new File(path);
			long filesize = Files.size(fileData.toPath());
			byte[] readfile = new byte[(int)filesize];
			fi.read(readfile);
			String header = HttpResponseHeader.createResponseHeaderSuccess(ContentType.JS,(int)filesize);
			header = header + "\r\n";
			String body = new String(readfile,"UTF-8");
			return new HttpResFrame(header, body, ContentType.JS);
		} catch(IOException e) {
			String header = HttpResponseHeader.createResponseHeaderInternalServerError();
			return new HttpResFrame(header, "", ContentType.JS);
		}
	}

	public HttpResFrame json(Object mess) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			String body = mapper.writeValueAsString(mess);
			String header = HttpResponseHeader.createResponseHeaderSuccess(ContentType.JSON);
			header = header + "\r\n";
			return new HttpResFrame(header, body, ContentType.JSON);
		} catch(Exception e) {
			String header = HttpResponseHeader.createResponseHeaderInternalServerError();
			return new HttpResFrame(header, "", ContentType.JSON);
		}
	}

	public HttpResFrame adminHtml(String path, List<PropertiesFileModel> propModelList) {
		try(FileInputStream fi = new FileInputStream(path)){
			File fileData = new File(path);
			long filesize = Files.size(fileData.toPath());
			byte[] readfile = new byte[(int)filesize];
			fi.read(readfile);
			String header = HttpResponseHeader.createResponseHeaderSuccess(ContentType.HTML,(int)filesize);
			header = header + "\r\n";
			String body = new String(readfile,"UTF-8");
			StringBuffer sb = new StringBuffer();
			for(PropertiesFileModel propModel : propModelList) {
				sb.append(propModel.createInputHTML());
			}
			body = body.replaceAll("<!--This is Change Prop File-->", sb.toString());
			return new HttpResFrame(header, body, ContentType.HTML);
		} catch(IOException e) {
			String header = HttpResponseHeader.createResponseHeaderInternalServerError();
			return new HttpResFrame(header, "", ContentType.HTML);
		}
	} 
}
