package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataContainer implements Serializable {

    private ObservableList<Film> filmy;
    private ObservableList<Sala> sale;
    private ObservableList<Seans> seanse;

    public ObservableList<Film> getFilmy() {
        return filmy;
    }

    public void setFilmy(List<Film> res) {
        this.filmy = FXCollections.observableArrayList(res);
    }

    public ObservableList<Sala> getSale() {
        return sale;
    }

    public void setSale(List<Sala> res) {
        this.sale = FXCollections.observableArrayList(res);
    }

    public ObservableList<Seans> getSeanse() {
        return seanse;
    }

    public void setSeanse(List<Seans> res) {
        this.seanse = FXCollections.observableArrayList(res);
    }

    public Film getFilm(String filmNazwa){
        for(Film film : filmy)
            if(film.getNazwa().equals(filmNazwa))
                return film;
        return null;
    }

    public Sala getSala(String salaNumer){
        for(Sala sala : sale)
            if(sala.getNumer().equals(salaNumer))
                return sala;
        return null;
    }

    public DataContainer() {
        sale = FXCollections.observableArrayList();
        filmy = FXCollections.observableArrayList();
        seanse = FXCollections.observableArrayList();
    }

    public void zapisz() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("filmy.obj"))) {
            oos.writeObject(new ArrayList<>(filmy));
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sale.obj"))) {
            oos.writeObject(new ArrayList<>(sale));
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("seanse.obj"))) {
            oos.writeObject(new ArrayList<>(seanse));
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void wczytaj() {
        ArrayList<Film> filmyList;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("filmy.obj"))) {
            filmyList = (ArrayList<Film>) ois.readObject();
            setFilmy(filmyList);
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Sala> saleList;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sale.obj"))) {
            saleList = (ArrayList<Sala>) ois.readObject();
            setSale(saleList);
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Seans> seanseList;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("seanse.obj"))) {
            seanseList = (ArrayList<Seans>) ois.readObject();
            setSeanse(seanseList);
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
