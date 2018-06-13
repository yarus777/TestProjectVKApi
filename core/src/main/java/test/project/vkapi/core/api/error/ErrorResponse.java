package test.project.vkapi.core.api.error;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("error")
    private ErrorItem errorItem;

    public ErrorItem getErrorItem() {
        return errorItem;
    }

    public void setErrorItem(ErrorItem errorItem) {
        this.errorItem = errorItem;
    }
}
