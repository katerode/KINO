package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SaleController implements HierarchicalController<MainController> {
    public TextField numer;
    public TextField miejsca;
    public TextField rodzaj;
    public Button add;
    public Button delete;
    public Button back;
    public TableView<Sala> tablica;
    public Button synchro;
    public Button edit;

    private MainController parentController;

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
        tablica.getItems().addAll(parentController.getDataContainer().getSale());
    }

    public MainController getParentController() {
        return parentController;
    }

    public void dodaj(ActionEvent actionEvent) {
        Sala s = new Sala(numer.getText(),Integer.parseInt(miejsca.getText()),rodzaj.getText());
        tablica.getItems().add(s);
    }

    public void usun(ActionEvent actionEvent) {
        tablica.getItems().remove(tablica.getSelectionModel().getSelectedItem());
    }

    public void usunZmiany(ActionEvent actionEvent) {
        tablica.getItems().clear();
        tablica.getItems().addAll(parentController.getDataContainer().getSale());
    }

    public void initialize() {

        tablica.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection!=null&&newSelection != oldSelection) {
                numer.setText(newSelection.getNumer());
                miejsca.setText(newSelection.getMiejsca().toString());
                rodzaj.setText(newSelection.getRodzaj());
            }
    });

        for (TableColumn<Sala, ?> salaTableColumn : tablica.getColumns()) {
            salaTableColumn.setCellValueFactory(new PropertyValueFactory<>(salaTableColumn.getId()));
        }
    }

    public void synchro(ActionEvent actionEvent) {
        parentController.getDataContainer().setSale(tablica.getItems());
    }

    public void edycja(ActionEvent actionEvent) {
        Sala sala = tablica.getSelectionModel().getSelectedItem();
        sala.setNumer(numer.getText());
        sala.setMiejsca(Integer.parseInt(miejsca.getText()));
        sala.setRodzaj(rodzaj.getText());
        tablica.refresh();
    }
}
