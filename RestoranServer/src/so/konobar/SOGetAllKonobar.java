/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.konobar;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Konobar;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllKonobar extends AbstractSO {

    private ArrayList<Konobar> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Konobar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Konobar!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> konobari
                = (ArrayList<AbstractDomainObject>) DBBroker.getInstance().select(ado);
        lista = (ArrayList<Konobar>) (ArrayList<?>) konobari;
    }

    public ArrayList<Konobar> getLista() {
        return lista;
    }

}
