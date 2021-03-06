package org.jazzframework.response.mustache;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.google.inject.Inject;
import com.sun.jersey.api.view.Viewable;

public class MustacheUtil {
	
	private final MustacheViewProcessor viewProcessor;
	
	@Inject
	public MustacheUtil(MustacheViewProcessor viewProcessor) {
		this.viewProcessor = viewProcessor; 
	}
	
	public String compile(String path, Object data) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		if (path == null) {
			return null;
		}
		String resolvedPath = viewProcessor.resolve(path);
		Viewable viewable = new Viewable(path, data);
		try {
			viewProcessor.writeTo(resolvedPath, viewable, out);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return out.toString();
	}
}
