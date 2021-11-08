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
public class Proizvod extends AbstractDomainObject implements Serializable {

    private Cenovnik cenovnik;
    private int rbProizvoda;
    private String naziv;
    private double cena;
    private String opis;

    public Proizvod() {
    }

    public Proizvod(Cenovnik cenovnik, int rbProizvoda, String naziv, double cena, String opis) {
        this.cenovnik = cenovnik;
        this.rbProizvoda = rbProizvoda;
        this.naziv = naziv;
        this.cena = cena;
        this.opis = opis;
    }

    @Override
    public String toString() {
        return naziv + " (" + cena + "din)";
    }

    @Override
    public String nazivTabele() {
        return " proizvod ";
    }

    @Override
    public String alijas() {
        return " p ";
    }

    @Override
    public String join() {
        return " JOIN cenovnik c ON (p.cenovnikid =  c.cenovnikid) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Cenovnik c = new Cenovnik(rs.getLong("CenovnikID"),
                    rs.getString("TipCenovnika"), rs.getBoolean("Hrana"), null);
            
            Proizvod p = new Proizvod(c, rs.getInt("RbProizvoda"), rs.getString("Naziv"),
                    rs.getDouble("Cena"), rs.getString("Opis"));

            lista.add(p);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (CenovnikID, RbProizvoda, Naziv, Cena, Opis) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " CenovnikID = " + cenovnik.getCenovnikID();
    }

    @Override
    public String vrednostiZaInsert() {
        return cenovnik.getCenovnikID() + ", " + rbProizvoda + ", "
                + "'" + naziv + "', " + cena + ", '" + opis + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Cena = '" + cena + "', Opis = '" + opis + "' ";
    }

    @Override
    public String getByID() {
        return " WHERE C.CENOVNIKID = " + cenovnik.getCenovnikID();
    }

    public Cenovnik getCenovnik() {
        return cenovnik;
    }

    public void setCenovnik(Cenovnik cenovnik) {
        this.cenovnik = cenovnik;
    }

    public int getRbProizvoda() {
        return rbProizvoda;
    }

    public void setRbProizvoda(int rbProizvoda) {
        this.rbProizvoda = rbProizvoda;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
