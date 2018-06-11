package test.project.vkapi.core.api.user;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class UsersResponse {
    @SerializedName("response")
    private List<UserResponse> response;

    public List<UserResponse> getResponse() {
        return response;
    }

    public void setResponse(List<UserResponse> response) {
        this.response = response;
    }
}

