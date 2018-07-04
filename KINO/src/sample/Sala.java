package sample;

import java.io.Serializable;

public class Sala implements Serializable {

    protected String numer;
    protected Integer miejsca;
    protected String rodzaj;

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public Integer getMiejsca() {
        return miejsca;
    }

    public void setMiejsca(Integer miejsca) {
        this.miejsca = miejsca;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public Sala(String numer, Integer miejsca, String rodzaj) {
        this.numer = numer;
        this.miejsca = miejsca;
        this.rodzaj = rodzaj;
    }
}
