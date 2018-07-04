package sample;

import java.io.Serializable;
import java.util.Date;

public class Seans implements Serializable {

    protected Film film;
    protected Sala sala;
    protected Date data;

    public Seans(Film film, Sala sala, Date data) {
        this.film = film;
        this.sala = sala;
        this.data = data;
    }

    public String getFilmNazwa() {
        return film.getNazwa();
    }

    public Film getFilm() {return film;}

    public void setFilm(Film film) {
        this.film = film;
    }

    public Sala getSala() {return sala;}

    public String getSalaNumer() {
        return sala.getNumer();
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
