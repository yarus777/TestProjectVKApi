package test.project.vkapi.core.feeds.api.models;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import test.project.vkapi.core.api.error.BaseResponse;
import test.project.vkapi.core.api.error.ErrorItem;
import test.project.vkapi.core.feeds.api.models.attachments.AudioItem;
import test.project.vkapi.core.feeds.api.models.attachments.LinkItem;
import test.project.vkapi.core.feeds.api.models.attachments.PhotoItem;
import test.project.vkapi.core.feeds.api.models.attachments.VideoItem;

public class ResponseConverter implements Converter<ResponseBody, FeedResponse> {
    private final Gson gson;

    public ResponseConverter() {
        gson = new Gson();
    }

    @Override
    public FeedResponse convert(ResponseBody value) throws IOException {
        try {
            JSONObject root = new JSONObject(value.string());
            if(root.has("error")) {
                return parseErrorResponse(value.string());
            } else {
                return parseResponse(root.getJSONObject("response"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private FeedResponse parseErrorResponse(String error) {
        return gson.fromJson(error, FeedResponse.class);
    }

    private FeedResponse parseResponse(JSONObject root) throws JSONException {
        FeedResponse response = new FeedResponse();
        FeedList list = new FeedList();
        response.setFeedList(list);
        List<FeedItem> itemsRes = new ArrayList<>();
        list.setItems(itemsRes);

        HashMap<String, PostInfoSource> postInfoItems = new HashMap<>();
        list.setInfoItems(postInfoItems);
        JSONArray items = root.getJSONArray("items");
        JSONArray groups = root.getJSONArray("groups");
        JSONArray profiles = root.getJSONArray("profiles");
        for (int i = 0; i < items.length(); i++) {
            FeedItem feedItem = parseItem(items.getJSONObject(i));
            if (feedItem != null) {
                itemsRes.add(feedItem);
            }
        }
        for (int j = 0; j < groups.length(); j++) {
            GroupItem groupItem = gson.fromJson(String.valueOf(groups.getJSONObject(j)), GroupItem.class);
            if (groupItem != null) {
                postInfoItems.put(groupItem.getId(), groupItem);
            }
        }

        for (int z = 0; z < profiles.length(); z++) {
            ProfileItem profileItem = gson.fromJson(String.valueOf(profiles.getJSONObject(z)), ProfileItem.class);
            if (profileItem != null) {
                postInfoItems.put(profileItem.getId(), profileItem);
            }
        }
        return response;
    }

    private FeedItem parseItem(JSONObject json) throws JSONException {
        String type = json.getString("type");
        if (!type.equals("post") || json.has("copy_history")) {
            return null;
        }
        FeedItem item = new FeedItem();
        item.setType(type);
        item.setSourceId(json.getString("source_id"));
        item.setPostId(json.getString("post_id"));
        item.setText(json.getString("text"));
        item.setLikes(gson.fromJson(json.getString("likes"), LikeItem.class));
        item.setComments(gson.fromJson(json.getString("comments"), CommentItem.class));


        if (json.has("attachments")) {
            JSONArray attachments = json.getJSONArray("attachments");
            for (int i = 0; i < attachments.length(); i++) {
                parseAttachmentItem(attachments.getJSONObject(i), item);
            }
        }

        return item;
    }

    private void parseAttachmentItem(JSONObject json, FeedItem feedItem) throws JSONException {
        String type = json.getString("type");
        switch (type) {
            case "photo": {
                PhotoItem attachment = gson.fromJson(json.getString("photo"), PhotoItem.class);
                feedItem.addPhotoAttachment(attachment);
                break;
            }
            case "video": {
                VideoItem attachment = gson.fromJson(json.getString("video"), VideoItem.class);
                feedItem.addVideoAttachment(attachment);
                break;
            }
            case "audio": {
                AudioItem attachment = gson.fromJson(json.getString("audio"), AudioItem.class);
                feedItem.addAudioAttachment(attachment);
                break;
            }
            case "link": {
                LinkItem attachment = gson.fromJson(json.getString("link"), LinkItem.class);
                feedItem.addLinkAttachment(attachment);
                break;
            }
            default:
                break;
        }
    }
}
