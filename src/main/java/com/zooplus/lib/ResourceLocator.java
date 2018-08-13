package com.zooplus.lib;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public interface ResourceLocator {

	public abstract String getResourceName(String name);
	
	public abstract String getFullPathResourceName(String name) throws URISyntaxException;

	public abstract URL getAsUrl(String resource);

	public abstract InputStream getAsStream(String resource);

	public abstract File getAsFile(String resource) throws URISyntaxException;
	
	public abstract String getBaseFolder();

}