package com.winry.util;

public class Tuple_2 {

	private final Object[] array = new Object[2];

	public Tuple_2(Object e1, Object e2) {
		super();
		this.array[0] = e1;
		this.array[1] = e2;
	}

	@SuppressWarnings("unchecked")
	public <T> T _1() {
		return (T) this.array[0];
	}

	@SuppressWarnings("unchecked")
	public <F> F _2() {
		return (F) this.array[1];
	}

}
