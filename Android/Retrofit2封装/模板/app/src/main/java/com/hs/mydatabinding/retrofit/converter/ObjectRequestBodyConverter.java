/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hs.mydatabinding.retrofit.converter;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Description: Object request 解析
 */
final class ObjectRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE   = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
    private static final Charset CHARSET_NAME = Charset.forName("UTF-8");

    ObjectRequestBodyConverter() {
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Field fields[] = value.getClass().getDeclaredFields();
        StringBuilder builder  = new StringBuilder();
        try {
            Field.setAccessible(fields, true);
            for (Field field : fields) {
                SerializedName annotation = field.getAnnotation(SerializedName.class);
                String name;
                if (null != annotation) {
                    name = annotation.value();
                } else {
                    name = field.getName();
                }
                if (field.get(value) != null) {
                    builder.append(name);
                    builder.append("=");
                    builder.append(field.get(value));
                    builder.append("&");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int    index = builder.length() - 1;
        byte[] bytes = builder.deleteCharAt(index).toString().getBytes(CHARSET_NAME);
        return RequestBody.create(MEDIA_TYPE, bytes);
    }
}
