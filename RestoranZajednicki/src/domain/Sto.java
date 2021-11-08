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
public class Sto extends AbstractDomainObject implements Serializable {
    
    private Long stoID;
    private boolean zaJelo;
    private boolean dozvoljenoPusenje;

    public Sto() {
    }

    public Sto(Long stoID, boolean zaJelo, boolean dozvoljenoPusenje) {
        this.stoID = stoID;
        this.zaJelo = zaJelo;
        this.dozvoljenoPusenje = dozvoljenoPusenje;
    }

    @Override
    public String toString() {
        if(zaJelo && !dozvoljenoPusenje)
            return stoID + " [Jelo: DA, Pusenje: NE] ";
        if(!zaJelo && dozvoljenoPusenje)
            return stoID + " [Jelo: NE, Pusenje: DA] ";
        if(zaJelo && dozvoljenoPusenje)
            return stoID + " [Jelo: DA, Pusenje: DA] ";
        if(!zaJelo && !dozvoljenoPusenje)
            return stoID + " [Jelo: NE, Pusenje: NE] ";
        return "";
    }
    
    
    @Override
    public String nazivTabele() {
        return " sto ";
    }

    @Override
    public String alijas() {
        return " s ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Sto s = new Sto(rs.getLong("StoID"),
                    rs.getBoolean("ZaJelo"), rs.getBoolean("DozvoljenoPusenje"));

            lista.add(s);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " StoID = " + stoID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String getByID() {
        return "";
    }

    public Long getStoID() {
        return stoID;
    }

    public void setStoID(Long stoID) {
        this.stoID = stoID;
    }

    public boolean isZaJelo() {
        return zaJelo;
    }

    public void setZaJelo(boolean zaJelo) {
        this.zaJelo = zaJelo;
    }

    public boolean isDozvoljenoPusenje() {
        return dozvoljenoPusenje;
    }

    public void setDozvoljenoPusenje(boolean dozvoljenoPusenje) {
        this.dozvoljenoPusenje = dozvoljenoPusenje;
    }
}
