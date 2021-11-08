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
public class SOAddKonobar extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Konobar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Konobar!");
        }
        
        Konobar k = (Konobar) ado;
        
        ArrayList<Konobar> konobari = (ArrayList<Konobar>)(ArrayList<?>)DBBroker.getInstance().select(ado);
        
        for (Konobar konobar : konobari) {
            if(konobar.getUsername().equals(k.getUsername()))
                throw new Exception("Vec postoji konobar sa tim korisnickim imenom!");
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().insert(ado);
    }

}
