package test.project.vkapi.core.api.feed;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class ResponseConverter implements Converter<ResponseBody, FeedResponse> {
    @Override
    public FeedResponse convert(ResponseBody value) throws IOException {
        FeedResponse response = new FeedResponse();
        FeedList list = new FeedList();
        response.setFeedList(list);
        List<FeedItem> itemsRes = new ArrayList<>();
        list.setItems(itemsRes);
        try {
            JSONObject root = new JSONObject(value.string());
            JSONArray items = root.getJSONObject("response").getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                FeedItem feedItem = parseItem(items.getJSONObject(i));
                if (feedItem != null) {
                    itemsRes.add(feedItem);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    private FeedItem parseItem(JSONObject json) throws JSONException {
        String type = json.getString("type");
        Gson gson = new Gson();
        if (!type.equals("post")) {
            return null;
        }
        FeedItem item = new FeedItem();
        item.setType(type);
        item.setText(json.getString("text"));
        item.setLikes(gson.fromJson(json.getString("likes"), LikeItem.class));
        item.setComments(gson.fromJson(json.getString("comments"), CommentItem.class));

        if (json.has("attachments")) {
            JSONArray attachments = json.getJSONArray("attachments");
            List<AttachmentItem> attachmentsList = new ArrayList<>();
            for (int i = 0; i < attachments.length(); i++) {
                AttachmentItem attachmentItem = parseAttachmentItem(attachments.getJSONObject(i));
                if (attachmentItem != null) {
                    attachmentsList.add(attachmentItem);
                }
                item.setAttachments(attachmentsList);
            }
        }

        return item;
    }

    private AttachmentItem parseAttachmentItem(JSONObject json) throws JSONException {
        String type = json.getString("type");
        Gson gson = new Gson();
        AttachmentItem item = new AttachmentItem();
        switch (type) {
            case "photo": {
                item = gson.fromJson(json.getString("photo"), PhotoItem.class);
                break;
            }
            case "video": {
                item = gson.fromJson(json.getString("video"), VideoItem.class);
                break;
            }
            case "audio": {
                item = gson.fromJson(json.getString("audio"), AudioItem.class);
                break;
            }
            case "link": {
                item = gson.fromJson(json.getString("link"), LinkItem.class);
                break;
            }
        }

        return item;
    }
}
