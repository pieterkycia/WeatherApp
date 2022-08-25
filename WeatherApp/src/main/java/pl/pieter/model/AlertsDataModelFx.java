package pl.pieter.model;

import pl.pieter.weather.library.AlertsData;

import java.util.List;

public class AlertsDataModelFx {

    private AlertsData alertsData;

    public AlertsDataModelFx(AlertsData alertsData) {
        this.alertsData = alertsData;
    }

    public boolean hasDescription(int index) {
        return !getDescription(index).contains("-1");
    }

    public String getDescription(int index) {
        return alertsData.getAlertDataList().get(index).getDescription();
    }

    public boolean hasEvent(int index) {
        return !getEvent(index).contains("-1");
    }

    public String getEvent(int index) {
        return alertsData.getAlertDataList().get(index).getEvent();
    }

    public List<String> getTags(int index) {
        return getAlertsData().getAlertDataList().get(index).getTags().getTagsList();
    }

    public AlertsData getAlertsData() {
        return alertsData;
    }
}
