package com.wecare.coachMs.exception;

public class NoCoachPresentException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoCoachPresentException() {
		super();
	}

	public NoCoachPresentException(String errors) {
		super(errors);
	}
}