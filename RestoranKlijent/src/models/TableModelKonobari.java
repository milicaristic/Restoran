/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Konobar;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelKonobari extends AbstractTableModel implements Runnable {

    private ArrayList<Konobar> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Username"};
    private String parametar = "";

    public TableModelKonobari() {
        try {
            lista = ClientController.getInstance().getAllKonobar();
        } catch (Exception ex) {
            Logger.getLogger(TableModelKonobari.class.getName()).log(Level.SEVERE, null, ex);
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
        Konobar k = lista.get(row);

        switch (column) {
            case 0:
                return k.getKonobarID();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getUsername();

            default:
                return null;
        }
    }

    public Konobar getSelectedKonobar(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(5000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelKonobari.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllKonobar();
            if (!parametar.equals("")) {
                ArrayList<Konobar> novaLista = new ArrayList<>();
                for (Konobar k : lista) {
                    if (k.getIme().toLowerCase().contains(parametar.toLowerCase())
                            || k.getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(k);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
