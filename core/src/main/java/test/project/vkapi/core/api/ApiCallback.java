package test.project.vkapi.core.api;

public interface ApiCallback<T> {
    void onSuccess(T data);
    void onError(Throwable error);
}
