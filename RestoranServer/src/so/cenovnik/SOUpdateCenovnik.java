/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.cenovnik;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Cenovnik;
import domain.Proizvod;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOUpdateCenovnik extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Cenovnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Cenovnik!");
        }
        
        Cenovnik c = (Cenovnik) ado;
        
        if(c.getProizvodi().size() < 3){
            throw new Exception("Cenovnik mora da ima barem 3 proizvoda!");
        }
        
        if(c.getProizvodi().size() > 30){
            throw new Exception("Cenovnik mora da ima manje od 30 proizvoda!");
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        Cenovnik c = (Cenovnik) ado;
        
        // ovde uzimamo prvi element cenovnika, zasto?
        // zato sto ce ovo da izvrsi upit DELETE FROM PROIZVOD WHERE CENOVNIKID = (tvojID)
        // i odmah ce da obrise sve stare proizvode tog cenovnika
        DBBroker.getInstance().delete(c.getProizvodi().get(0));
        
        // sad ubacujemo nove proizvode koje smo dodali
        for (Proizvod proizvod : c.getProizvodi()) {
            DBBroker.getInstance().insert(proizvod);
        }
        
    }

}
