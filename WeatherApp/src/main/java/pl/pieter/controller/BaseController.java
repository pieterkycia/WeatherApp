package pl.pieter.controller;

import pl.pieter.view.ViewManager;

public class BaseController {

    protected String fxmlPath;
    protected ViewManager viewManager;

    public BaseController(ViewManager viewManager, String fxmlPath) {
        this.viewManager = viewManager;
        this.fxmlPath = fxmlPath;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }
}
