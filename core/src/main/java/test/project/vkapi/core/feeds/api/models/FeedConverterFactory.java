package test.project.vkapi.core.feeds.api.models;


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class FeedConverterFactory extends Converter.Factory {
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type != FeedResponse.class) {
            return null;
        }
            return new ResponseConverter();
    }
}