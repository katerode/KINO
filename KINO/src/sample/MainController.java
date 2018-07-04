package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainController implements HierarchicalController<MainController> {

    public Pane srodek;

    private DataContainer dataContainer;

    public DataContainer getDataContainer() {
        return dataContainer;
    }

    public MainController() {
        dataContainer = new DataContainer();
    }

    private void loadIntoPane(String fxml) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            final BorderPane load = loader.load();
            srodek.getChildren().clear();
            srodek.getChildren().add(load);
            HierarchicalController<MainController> daneController = loader.getController();
            daneController.setParentController(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MainController getParentController() {
        return this;
    }

    @Override
    public void setParentController(MainController parent) {

    }

    public void sale(ActionEvent actionEvent) {
        loadIntoPane("Sale.fxml");
    }

    public void filmy(ActionEvent actionEvent) {
        loadIntoPane("Filmy.fxml");
    }

    public void seanse(ActionEvent actionEvent) {
        loadIntoPane("Seanse.fxml");
    }

    public void save(ActionEvent actionEvent) {
        dataContainer.zapisz();
    }

    public void load(ActionEvent actionEvent) {
        dataContainer.wczytaj();
    }
}
