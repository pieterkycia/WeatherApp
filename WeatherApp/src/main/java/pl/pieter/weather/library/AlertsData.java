package pl.pieter.weather.library;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.pieter.utils.DefaultValue;
import pl.pieter.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class AlertsData {

    private List<AlertData> alertDataList;

    public class AlertData {
        private static final String JSON_SENDER_NAME = "sender_name";
        private static final String JSON_EVENT = "event";
        private static final String JSON_START = "start";
        private static final String JSON_END = "end";
        private static final String JSON_DESCRIPTION = "description";
        private static final String JSON_TAGS = "tags";

        private String senderName;
        private String event;
        private long start;
        private long end;
        private String description;
        private Tags tags;

        public class Tags {
            private List<String> tagsList;

            public Tags(JSONArray jsonArray) {
                this.tagsList = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    this.tagsList.add(jsonArray.optString(i));
                }
            }

            public List<String> getTagsList() {
                return tagsList;
            }
        }

        public AlertData(JSONObject jsonObject) {
            this.senderName = jsonObject.optString(AlertData.JSON_SENDER_NAME, DefaultValue.STRING);
            this.event = jsonObject.optString(AlertData.JSON_EVENT, DefaultValue.STRING);
            this.start = jsonObject.optLong(AlertData.JSON_START, DefaultValue.LONG);
            this.end = jsonObject.optLong(AlertData.JSON_END, DefaultValue.LONG);
            this.description = jsonObject.optString(AlertData.JSON_DESCRIPTION, DefaultValue.STRING);
            this.tags = new Tags(JsonUtils.checkJsonArray(jsonObject.optJSONArray(AlertData.JSON_TAGS), DefaultValue.JSON_ARRAY));
        }

        public String getSenderName() {
            return senderName;
        }

        public String getEvent() {
            return event;
        }

        public long getStart() {
            return start;
        }

        public long getEnd() {
            return end;
        }

        public String getDescription() {
            return description;
        }

        public Tags getTags() {
            return tags;
        }
    }


    public AlertsData(JSONArray jsonArray) {
        this.alertDataList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            AlertData alertData = new AlertData(jsonArray.optJSONObject(i));
            this.alertDataList.add(alertData);
        }
    }

    public List<AlertData> getAlertDataList() {
        return alertDataList;
    }
}
