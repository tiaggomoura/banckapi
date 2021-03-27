package com.bankapi.entities;

public interface GenericEntity<T, VO> {

	void update(VO source);

	Long getId();

	T createNewInstance(T t, VO source);

}