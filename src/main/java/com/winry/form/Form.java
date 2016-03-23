package com.winry.form;

import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.lang.reflect.Field;

/**
 * Created by cong on 2016/3/22.
 *
 */
public abstract class Form {

	public RequestBody build() {
		Class<?> clazz = this.getClass();
		Field[] fields = clazz.getDeclaredFields();
		FormBody.Builder builder = new FormBody.Builder();
		for (Field field : fields) {
			String name = field.getName().toLowerCase();
			try {
				String value = field.get(this).toString();
				builder.add(name, value);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return builder.build();
	};
}
