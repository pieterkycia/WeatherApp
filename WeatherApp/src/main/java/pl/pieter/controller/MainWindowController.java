package pl.pieter.controller;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pl.pieter.utils.CountryCodes;
import pl.pieter.utils.FxmlUtils;
import pl.pieter.utils.Theme;
import pl.pieter.view.ViewManager;

import java.io.IOException;

public class MainWindowController extends BaseController {

    private static final int ALERTS_DATA_VBOX = 0;
    private static final int CURRENT_DATA_HBOX = 1;
    private static final int DAILY_DATA_VBOX = 2;
    private static final int HOURLY_DATA_VBOX = 3;
    private static final int DAY_DETAILS_DATA_VBOX = 4;

    @FXML
    private TextField cityNameTextField;

    @FXML
    private ComboBox<CountryCodes> countryComboBox;

    @FXML
    private ComboBox<Theme> themeComboBox;

    @FXML
    private VBox mainVBox;

    @FXML
    private VBox dataVBox;

    @FXML
    private HBox topBarHBox;

    @FXML
    private Button searchButton;

    @FXML
    private Button refreshButton;

    public MainWindowController(ViewManager viewManager, String fxmlPath) {
        super(viewManager, fxmlPath);
    }

    @FXML
    public void initialize() {
        setUpRefreshButton();
        setUpSearchButton();
        setUpTopBarHBox();
        setUpCountryComboBox();
        setUpThemeComboBox();
        mainVBox.getStyleClass().add("mainBackground");
        loadCssStyleSheets();

        cityNameTextField.setText("korytniki");
        countryComboBox.setValue(CountryCodes.PL);
        searchOnAction();
    }

    private void setUpTopBarHBox() {
        topBarHBox.getStyleClass().add("topBar");
    }

    @FXML
    public void refreshOnAction() {
        searchOnAction();
    }

    @FXML
    public void searchOnAction() {
        try {
            viewManager.getWeatherManager().createNewWeatherDataModelFx(cityNameTextField.getText(), countryComboBox.getValue().name());
            loadAllData();
        } catch (IOException e) {
            System.out.println("IOException");
            setEmptyWindow();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            setEmptyWindow();
        } catch (Exception e) {
            System.out.println("Exception");
            setEmptyWindow();
        }
    }

    private void loadCssStyleSheets() {
        mainVBox.getStylesheets().add(getClass().getResource("/pl/pieter/css/main.css").toExternalForm());
        mainVBox.getStylesheets().add(getClass().getResource("/pl/pieter/css/medium.css").toExternalForm());
    }

    private void loadAllData() {
        viewManager.loadAlertsDataWindow();
        viewManager.loadCurrentDataWindow();
        viewManager.loadDailyDataWindow();
        viewManager.loadHourlyDataWindow();
        viewManager.loadDayDetailsDataWindow();
    }

    public void setDataVBox(int index, BaseController controller) {
        dataVBox.getChildren().set(index, FxmlUtils.loadFxmlFile(controller));
    }

    private void clearDataVBox() {
        ((VBox) dataVBox.getChildren().get(ALERTS_DATA_VBOX)).getChildren().clear();
        ((HBox) dataVBox.getChildren().get(CURRENT_DATA_HBOX)).getChildren().clear();
        ((VBox) dataVBox.getChildren().get(DAILY_DATA_VBOX)).getChildren().clear();
        ((VBox) dataVBox.getChildren().get(HOURLY_DATA_VBOX)).getChildren().clear();
        ((VBox) dataVBox.getChildren().get(DAY_DETAILS_DATA_VBOX)).getChildren().clear();
    }

    private void setEmptyWindow() {
        clearDataVBox();
        viewManager.loadEmptyWindow();
    }

    private void setUpRefreshButton() {
        Text refreshButtonIcon = GlyphsDude.createIcon(FontAwesomeIcon.REFRESH, "30px");
        refreshButtonIcon.getStyleClass().add("topBarIcons");

        refreshButton.getStyleClass().add("topBarButtons");
        refreshButton.setGraphic(refreshButtonIcon);
        refreshButton.setAlignment(Pos.CENTER);
        refreshButton.setPrefHeight(36.8);
    }

    private void setUpSearchButton() {
        Text searchButtonIcon = GlyphsDude.createIcon(FontAwesomeIcon.SEARCH, "30px");
        searchButtonIcon.getStyleClass().add("topBarIcons");

        searchButton.getStyleClass().add("topBarButtons");
        searchButton.setGraphic(searchButtonIcon);
        searchButton.setAlignment(Pos.CENTER);
        searchButton.setPrefHeight(36.8);
    }

    private void setUpCountryComboBox() {
        for (int i = 0; i < CountryCodes.values().length; i++) {
            countryComboBox.getItems().add(CountryCodes.values()[i]);
        }
        countryComboBox.getStyleClass().add("text-input");
        countryComboBox.getStyleClass().add("countryComboBox");
    }

    private void setUpThemeComboBox() {
        for (int i = 0; i < Theme.values().length; i++) {
            themeComboBox.getItems().add(Theme.values()[i]);
        }
        themeComboBox.setValue(Theme.MEDIUM);
        themeComboBox.getStyleClass().add("text-input");
        themeComboBox.getStyleClass().add("themeComboBox");

        themeComboBox.valueProperty().addListener(new ChangeListener<Theme>() {
            @Override
            public void changed(ObservableValue<? extends Theme> observableValue, Theme oldValue, Theme newValue) {
                mainVBox.getStylesheets().set(1, newValue.getPath());
                viewManager.setThemeColor(newValue.toString());
                viewManager.loadDayDetailsDataWindow(viewManager.getDayDetailsId());
            }
        });
    }
}
