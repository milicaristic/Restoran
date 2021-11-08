/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Cenovnik;
import domain.Proizvod;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelProizvodi extends AbstractTableModel {

    private ArrayList<Proizvod> lista;
    private String[] kolone = {"Rb", "Naziv", "Cena", "Opis"};
    int rb = 0;

    public TableModelProizvodi() {
        lista = new ArrayList<>();
    }

    public TableModelProizvodi(Cenovnik c) {
        try {
            lista = ClientController.getInstance().getAllProizvod(c);
        } catch (Exception ex) {
            Logger.getLogger(TableModelProizvodi.class.getName()).log(Level.SEVERE, null, ex);
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
        Proizvod p = lista.get(row);

        switch (column) {
            case 0:
                return p.getRbProizvoda();
            case 1:
                return p.getNaziv();
            case 2:
                return p.getCena();
            case 3:
                return p.getOpis();

            default:
                return null;
        }
    }

    public Proizvod getSelectedProizvod(int row) {
        return lista.get(row);
    }

    public void dodajProizvod(Proizvod p) {
        p.setRbProizvoda(++rb);
        lista.add(p);
        fireTableDataChanged();
    }

    public void obrisiProizvod(int row) {
        lista.remove(row);
        
        rb = 0;
        for (Proizvod proizvod : lista) {
            proizvod.setRbProizvoda(++rb);
        }
        
        fireTableDataChanged();
    }

    public ArrayList<Proizvod> getLista() {
        return lista;
    }

    public void dodajProizvodZaIzmenu(Proizvod p) {
        rb = 0;
        for (Proizvod proizvod : lista) {
            ++rb;
        }
        p.setRbProizvoda(++rb);
        lista.add(p);
        fireTableDataChanged();
    }

    public boolean postojiOpis(String opis) {
        for (Proizvod proizvod : lista) {
            if(proizvod.getOpis().equals(opis))
                return true;
        }
        return false;
    }

    public boolean postojiNaziv(String naziv) {
        for (Proizvod proizvod : lista) {
            if(proizvod.getNaziv().equals(naziv))
                return true;
        }
        return false;
    }

}
