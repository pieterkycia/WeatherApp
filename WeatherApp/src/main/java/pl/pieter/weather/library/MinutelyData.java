package pl.pieter.weather.library;

import org.json.JSONArray;
import org.json.JSONObject;
import pl.pieter.utils.DefaultValue;

import java.util.ArrayList;
import java.util.List;

public class MinutelyData {

    private List<MinuteData> minuteDataList;

    public class MinuteData {
        private static final String JSON_DT = "dt";
        private static final String JSON_PRECIPITATION = "precipitation";

        private long dt;
        private float precipitation;

        public MinuteData(JSONObject jsonObject) {
            this.dt = jsonObject.optLong(MinuteData.JSON_DT, DefaultValue.LONG);
            this.precipitation = jsonObject.optFloat(MinuteData.JSON_PRECIPITATION, DefaultValue.FLOAT);
        }

        public long getDt() {
            return dt;
        }

        public float getPrecipitation() {
            return precipitation;
        }
    }

    public MinutelyData(JSONArray jsonArray) {
        this.minuteDataList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            MinuteData minuteData = new MinuteData(jsonArray.optJSONObject(i));
            this.minuteDataList.add(minuteData);
        }
    }

    public List<MinuteData> getMinuteDataList() {
        return minuteDataList;
    }
}
