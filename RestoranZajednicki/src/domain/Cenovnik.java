/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Cenovnik extends AbstractDomainObject implements Serializable {
    
    private Long cenovnikID;
    private String tipCenovnika;
    private boolean hrana;
    private ArrayList<Proizvod> proizvodi;

    public Cenovnik() {
    }

    public Cenovnik(Long cenovnikID, String tipCenovnika, boolean hrana, ArrayList<Proizvod> proizvodi) {
        this.cenovnikID = cenovnikID;
        this.tipCenovnika = tipCenovnika;
        this.hrana = hrana;
        this.proizvodi = proizvodi;
    }

    @Override
    public String toString() {
        return tipCenovnika;
    }

    @Override
    public String nazivTabele() {
        return " cenovnik ";
    }

    @Override
    public String alijas() {
        return " c ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Cenovnik c = new Cenovnik(rs.getLong("CenovnikID"),
                    rs.getString("TipCenovnika"), rs.getBoolean("Hrana"), null);

            lista.add(c);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (TipCenovnika, Hrana) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " CenovnikID = " + cenovnikID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + tipCenovnika + "', " + (hrana ? 1 : 0);
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getCenovnikID() {
        return cenovnikID;
    }

    public void setCenovnikID(Long cenovnikID) {
        this.cenovnikID = cenovnikID;
    }

    public String getTipCenovnika() {
        return tipCenovnika;
    }

    public void setTipCenovnika(String tipCenovnika) {
        this.tipCenovnika = tipCenovnika;
    }

    public ArrayList<Proizvod> getProizvodi() {
        return proizvodi;
    }

    public void setProizvodi(ArrayList<Proizvod> proizvodi) {
        this.proizvodi = proizvodi;
    }

    public boolean isHrana() {
        return hrana;
    }

    public void setHrana(boolean hrana) {
        this.hrana = hrana;
    }
}
