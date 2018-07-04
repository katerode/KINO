package sample;

import java.io.Serializable;
import java.sql.Time;

public class Film implements Serializable {

    private String nazwa;
    private String opis;
    private Integer czas;
    private Integer wiek;

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setCzas(Integer czas) {
        this.czas = czas;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setWiek(Integer wiek) {
        this.wiek = wiek;
    }

    public Integer getCzas() {
        return czas;
    }

    public Integer getWiek() {
        return wiek;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public Film(String nazwa, String opis, Integer czas, Integer wiek){
        this.nazwa=nazwa;
        this.opis=opis;
        this.czas=czas;
        this.wiek=wiek;
    }
}
