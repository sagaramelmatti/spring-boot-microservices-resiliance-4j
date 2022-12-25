package com.ledzer.product.common.utils;

public enum Topic {

	Item("Item", 101),
	Sales("Sales", 102);
	
	private final String name;

	private final int code;

	private Topic(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}


}
