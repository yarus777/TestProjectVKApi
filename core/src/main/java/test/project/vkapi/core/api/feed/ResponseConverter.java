package test.project.vkapi.core.api.feed;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class ResponseConverter<T> implements Converter<ResponseBody, T> {

    @Override
    public T convert(ResponseBody value) throws IOException {
        return null;
    }
}
