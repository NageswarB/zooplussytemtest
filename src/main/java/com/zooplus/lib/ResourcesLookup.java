package com.zooplus.lib;

import java.io.File;

public class ResourcesLookup {
	
	private static final String SEPERATOR = File.separator;
	static String workingDirectory = System.getProperty("user.dir");
	
	private static final String MAIN_RESOURCES_DIR =  workingDirectory + SEPERATOR + "src" + SEPERATOR 
			+ "main" + SEPERATOR + "resources";
	
	private static final String TEST_RESOURCES_DIR =  workingDirectory + SEPERATOR + "src" + SEPERATOR 
			+ "test" + SEPERATOR + "resources";
	
	public static String getMainResoucresDirPath() {
		return MAIN_RESOURCES_DIR;
	}
	
	public static String getTestResoucresDirPath() {
		return TEST_RESOURCES_DIR;
	}
	
}
