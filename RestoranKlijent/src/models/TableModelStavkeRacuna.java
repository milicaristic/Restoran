/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Racun;
import domain.StavkaRacuna;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelStavkeRacuna extends AbstractTableModel {

    private ArrayList<StavkaRacuna> lista;
    private String[] kolone = {"Rb", "Proizvod", "Kolicina", "Cena"};
    int rb = 0;

    public TableModelStavkeRacuna() {
        lista = new ArrayList<>();
    }

    public TableModelStavkeRacuna(Racun r) {
        try {
            lista = ClientController.getInstance().getAllStavkaRacuna();
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavkeRacuna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        StavkaRacuna sr = lista.get(row);

        switch (column) {
            case 0:
                return sr.getRbStavke();
            case 1:
                return sr.getProizvod();
            case 2:
                return sr.getKolicina();
            case 3:
                return sr.getCenaStavke();

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaRacuna sr) {

        for (StavkaRacuna stavkaRacuna : lista) {
            if (sr.getProizvod().equals(stavkaRacuna.getProizvod()))  {
                stavkaRacuna.setCenaStavke(stavkaRacuna.getCenaStavke() + sr.getCenaStavke());
                stavkaRacuna.setKolicina(stavkaRacuna.getKolicina() + sr.getKolicina());
                fireTableDataChanged();
                return;
            }
        }

        sr.setRbStavke(++rb);
        lista.add(sr);
        fireTableDataChanged();

    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaRacuna stavkaRacuna : lista) {
            stavkaRacuna.setRbStavke(++rb);
        }

        fireTableDataChanged();
    }

    public ArrayList<StavkaRacuna> getLista() {
        return lista;
    }

    public StavkaRacuna getSelectedStavka(int row) {
        return lista.get(row);
    }

}
