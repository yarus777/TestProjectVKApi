package test.project.vkapi.core.api.error;

import com.google.gson.annotations.SerializedName;

public abstract class BaseResponse {
    @SerializedName("error")
    private ErrorItem errorItem;

    public boolean isSuccessful() {
        return errorItem == null;
    }

    public ErrorItem getErrorItem() {
        return errorItem;
    }

    public void setErrorItem(ErrorItem errorItem) {
        this.errorItem = errorItem;
    }
}
