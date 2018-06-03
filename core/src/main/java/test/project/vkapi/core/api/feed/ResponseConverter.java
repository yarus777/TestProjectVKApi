package test.project.vkapi.core.api.feed;

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
            for(int i=0; i<items.length(); i++) {
                FeedItem feedItem = parseItem(items.getJSONObject(i));
                if(feedItem != null) {
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
        if(!type.equals("post")) {
            return null;
        }
        FeedItem item = new FeedItem();
        item.setType(type);
        item.setText(json.getString("text"));
        return item;
    }
}
