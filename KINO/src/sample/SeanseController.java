package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SeanseController implements HierarchicalController<MainController> {
    public TableView<Seans> tablica;
    public TextField data;
    public Button add;
    public Button delete;
    public Button back;
    public Button synchro;
    public ComboBox filmNazwa;
    public ComboBox salaNumer;
    public Button edit;
    private MainController parentController;
    private final DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy, HH:mm");

    public void setParentController(MainController parentController) {
        this.parentController = parentController;
        tablica.getItems().addAll(parentController.getDataContainer().getSeanse());

        ObservableList<String> options = FXCollections.observableArrayList();
        for (Film film : parentController.getDataContainer().getFilmy())
            options.add(film.getNazwa());
        filmNazwa.getItems().addAll(options);

        options.clear();
        for (Sala film : parentController.getDataContainer().getSale())
            options.add(film.getNumer());
        salaNumer.getItems().addAll(options);
    }

    public MainController getParentController() {
        return parentController;
    }

    public void dodaj(ActionEvent actionEvent){

        try {
            Seans s = new Seans(
                    parentController.getDataContainer().getFilm(filmNazwa.getValue().toString()),
                    parentController.getDataContainer().getSala(salaNumer.getValue().toString()),
                    formatter.parse(data.getText())
                    );
            tablica.getItems().add(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void usun(ActionEvent actionEvent) {
        tablica.getItems().remove(tablica.getSelectionModel().getSelectedItem());
    }

    public void usunZmiany(ActionEvent actionEvent) {
        tablica.getItems().clear();
        tablica.getItems().addAll(parentController.getDataContainer().getSeanse());
    }

    public void initialize() {
        data.setText("dd-MM-yyyy, HH:mm");

        tablica.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection!=null&&newSelection != oldSelection) {
                filmNazwa.setValue(newSelection.getFilmNazwa());
                salaNumer.setValue(newSelection.getSalaNumer());
                data.setText(formatter.format(newSelection.getData()));
            }
        });

        for (TableColumn<Seans, ?> seansTableColumn : tablica.getColumns()) {
            seansTableColumn.setCellValueFactory(new PropertyValueFactory<>(seansTableColumn.getId()));
        }
    }

    public void synchro(ActionEvent actionEvent) {
        parentController.getDataContainer().setSeanse(tablica.getItems());
    }

    public void edycja(ActionEvent actionEvent) {
        Seans seans = tablica.getSelectionModel().getSelectedItem();
        seans.setFilm(parentController.getDataContainer().getFilm(filmNazwa.getValue().toString()));
        seans.setSala(parentController.getDataContainer().getSala(salaNumer.getValue().toString()));
        try {
            seans.setData(formatter.parse(data.getText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tablica.refresh();
    }
}
