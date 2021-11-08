/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Cenovnik;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelCenovnici extends AbstractTableModel implements Runnable {

    private ArrayList<Cenovnik> lista;
    private String[] kolone = {"ID", "Tip cenovnika"};
    private String parametar = "";

    public TableModelCenovnici() {
        try {
            lista = ClientController.getInstance().getAllCenovnik();
        } catch (Exception ex) {
            Logger.getLogger(TableModelCenovnici.class.getName()).log(Level.SEVERE, null, ex);
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
        Cenovnik c = lista.get(row);

        switch (column) {
            case 0:
                return c.getCenovnikID();
            case 1:
                return c.getTipCenovnika();

            default:
                return null;
        }
    }

    public Cenovnik getSelectedCenovnik(int row) {
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
            Logger.getLogger(TableModelCenovnici.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllCenovnik();
            if (!parametar.equals("")) {
                ArrayList<Cenovnik> novaLista = new ArrayList<>();
                for (Cenovnik c : lista) {
                    if (c.getTipCenovnika().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(c);
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
