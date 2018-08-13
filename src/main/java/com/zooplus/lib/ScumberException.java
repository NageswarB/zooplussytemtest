package com.zooplus.lib;

public class ScumberException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ScumberException() {
		super();
	}

	public ScumberException(String message, Throwable cause) {
		super(message, cause);
	}

	public ScumberException(String message) {
		super(message);
	}

	public ScumberException(Throwable cause) {
		super(cause);
	}
	
}
