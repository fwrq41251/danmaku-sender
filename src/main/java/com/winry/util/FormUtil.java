package com.winry.util;

import com.winry.form.Form;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.lang.reflect.Field;

/**
 * Created by cong on 2016/3/22.
 */
public final class FormUtil {

    private FormUtil() {
    }

    public static RequestBody toFormBody(Form form) {
        Class<?> clazz = form.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder("?");
        FormBody.Builder builder= new FormBody.Builder();
        for (Field field : fields) {
            String name = field.getName().toLowerCase();
            try {
                String value = field.get(form).toString();
                builder.add(name, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return builder.build();
    }

}
