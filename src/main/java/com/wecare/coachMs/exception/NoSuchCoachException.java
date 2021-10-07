package com.wecare.coachMs.exception;

public class NoSuchCoachException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoSuchCoachException() {
		super();
	}

	public NoSuchCoachException(String errors) {
		super(errors);
	}
}
