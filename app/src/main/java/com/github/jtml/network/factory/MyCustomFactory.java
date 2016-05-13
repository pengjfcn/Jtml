package com.github.jtml.network.factory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by pengjf on 2016/5/12.
 */
public class MyCustomFactory extends Converter.Factory
{
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit)
    {
        //根据type判断是否是自己能处理的类型，不能的话，return null ,交给后面的Converter.Factory
        return new UserResponseConverter(type);
    }

}