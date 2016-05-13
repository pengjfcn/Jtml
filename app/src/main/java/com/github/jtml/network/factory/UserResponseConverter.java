package com.github.jtml.network.factory;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by pengjf on 2016/5/12.
 */
public class UserResponseConverter<T> implements Converter<ResponseBody, T>
{
    private Type type;
    Gson gson = new Gson();

    public UserResponseConverter(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        String result = responseBody.string();
        T users = gson.fromJson(result, type);
        return users;
    }
}
