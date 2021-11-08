/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author PC
 */
public class Racun extends AbstractDomainObject implements Serializable {

    private Long racunID;
    private Date datumVreme;
    private double ukupnaCena;
    private Sto sto;
    private Konobar konobar;
    private ArrayList<StavkaRacuna> stavkeRacuna;

    public Racun() {
    }

    public Racun(Long racunID, Date datumVreme, double ukupnaCena, Sto sto, Konobar konobar, ArrayList<StavkaRacuna> stavkeRacuna) {
        this.racunID = racunID;
        this.datumVreme = datumVreme;
        this.ukupnaCena = ukupnaCena;
        this.sto = sto;
        this.konobar = konobar;
        this.stavkeRacuna = stavkeRacuna;
    }

    @Override
    public String nazivTabele() {
        return " racun ";
    }

    @Override
    public String alijas() {
        return " r ";
    }

    @Override
    public String join() {
        return " JOIN sto s ON (s.stoid =  r.stoid) "
                + "JOIN konobar k ON (k.konobarid = r.konobarid) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Konobar k= new Konobar(rs.getLong("KonobarID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    "", "");
            
            Sto s = new Sto(rs.getLong("StoID"),
                    rs.getBoolean("ZaJelo"), rs.getBoolean("DozvoljenoPusenje"));
            
            Racun r = new Racun(rs.getLong("RacunID"),
                    rs.getTimestamp("DatumVreme"), rs.getDouble("UkupnaCena"),
                    s, k, null);

            lista.add(r);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (DatumVreme, UkupnaCena, StoID, KonobarID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " RacunID = " + racunID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVreme.getTime()) + "', " + ukupnaCena + ", "
                + sto.getStoID() + ", " + konobar.getKonobarID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getRacunID() {
        return racunID;
    }

    public void setRacunID(Long racunID) {
        this.racunID = racunID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public Sto getSto() {
        return sto;
    }

    public void setSto(Sto sto) {
        this.sto = sto;
    }

    public Konobar getKonobar() {
        return konobar;
    }

    public void setKonobar(Konobar konobar) {
        this.konobar = konobar;
    }

    public ArrayList<StavkaRacuna> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna;
    }
}
