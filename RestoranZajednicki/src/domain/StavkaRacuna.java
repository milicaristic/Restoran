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
public class StavkaRacuna extends AbstractDomainObject implements Serializable {

    private Racun racun;
    private int rbStavke;
    private int kolicina;
    private double cenaStavke;
    private Cenovnik cenovnik;
    private Proizvod proizvod;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Racun racun, int rbStavke, int kolicina, double cenaStavke, Cenovnik cenovnik, Proizvod proizvod) {
        this.racun = racun;
        this.rbStavke = rbStavke;
        this.kolicina = kolicina;
        this.cenaStavke = cenaStavke;
        this.cenovnik = cenovnik;
        this.proizvod = proizvod;
    }

    @Override
    public String nazivTabele() {
        return " stavkaRacuna ";
    }

    @Override
    public String alijas() {
        return " sr ";
    }

    @Override
    public String join() {
        return " JOIN racun r ON (r.racunID = sr.racunID) "
                + "JOIN sto s ON (s.stoid =  r.stoid) "
                + "JOIN konobar k ON (k.konobarid = r.konobarid) "
                + "JOIN proizvod p ON (p.rbproizvoda = sr.rbproizvoda AND p.cenovnikid = sr.cenovnikid) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Konobar k = new Konobar(rs.getLong("KonobarID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    "", "");

            Sto s = new Sto(rs.getLong("StoID"),
                    rs.getBoolean("ZaJelo"), rs.getBoolean("DozvoljenoPusenje"));

            Cenovnik c = new Cenovnik(rs.getLong("CenovnikID"),
                    rs.getString("TipCenovnika"), rs.getBoolean("Hrana"), null);

            Proizvod p = new Proizvod(c, rs.getInt("RbProizvoda"), rs.getString("Naziv"),
                    rs.getDouble("Cena"), rs.getString("Opis"));
            
            Racun r = new Racun(rs.getLong("RacunID"),
                    rs.getTimestamp("DatumVreme"), rs.getDouble("UkupnaCena"),
                    s, k, null);

            StavkaRacuna sr = new StavkaRacuna(r,
                    rs.getInt("RbStavke"), rs.getInt("Kolicina"), rs.getDouble("CenaStavke"),
                    c, p);

            lista.add(sr);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (RacunID, RbStavke, Kolicina, CenaStavke, CenovnikID, RbProizvoda) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " RacunID = " + racun.getRacunID();
    }

    @Override
    public String vrednostiZaInsert() {
        return racun.getRacunID() + ", " + rbStavke + ", "
                + kolicina + ", " + cenaStavke + ", " + cenovnik.getCenovnikID()
                + ", " + proizvod.getRbProizvoda();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCenaStavke() {
        return cenaStavke;
    }

    public void setCenaStavke(double cenaStavke) {
        this.cenaStavke = cenaStavke;
    }

    public Cenovnik getCenovnik() {
        return cenovnik;
    }

    public void setCenovnik(Cenovnik cenovnik) {
        this.cenovnik = cenovnik;
    }

    public Proizvod getProizvod() {
        return proizvod;
    }

    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }
}
