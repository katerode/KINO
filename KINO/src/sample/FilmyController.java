package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FilmyController implements HierarchicalController<MainController> {


    public TableView<Film> tablica;
    public TextField nazwa;
    public TextField opis;
    public TextField czas;
    public TextField wiek;
    public Button add;
    public Button delete;
    public Button back;
    public Button synchro;
    public Button edit;
    private MainController parentController;

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
        tablica.getItems().addAll(parentController.getDataContainer().getFilmy());
    }

    public MainController getParentController() {
        return parentController;
    }

    public void dodaj(ActionEvent actionEvent) {
        Film f = new Film(nazwa.getText(),opis.getText(),Integer.parseInt(czas.getText()),Integer.parseInt(wiek.getText()));
        tablica.getItems().add(f);
    }

    public void usun(ActionEvent actionEvent) {
        tablica.getItems().remove(tablica.getSelectionModel().getSelectedItem());
    }

    public void usunZmiany(ActionEvent actionEvent) {
        tablica.getItems().clear();
        tablica.getItems().addAll(parentController.getDataContainer().getFilmy());
    }

    public void initialize() {

        tablica.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection!=null&&newSelection != oldSelection) {
                nazwa.setText(newSelection.getNazwa());
                opis.setText(newSelection.getOpis());
                czas.setText(newSelection.getCzas().toString());
                wiek.setText(newSelection.getWiek().toString());
            }
        });

        for (TableColumn<Film, ?> filmTableColumn : tablica.getColumns()) {
            filmTableColumn.setCellValueFactory(new PropertyValueFactory<>(filmTableColumn.getId()));
        }
    }

    public void synchro(ActionEvent actionEvent) {
        parentController.getDataContainer().setFilmy(tablica.getItems());
    }

    public void edycja(ActionEvent actionEvent) {
        Film film = tablica.getSelectionModel().getSelectedItem();
        film.setNazwa(nazwa.getText());
        film.setOpis(opis.getText());
        film.setCzas(Integer.parseInt(czas.getText()));
        film.setWiek(Integer.parseInt(wiek.getText()));
        tablica.refresh();
    }
}
