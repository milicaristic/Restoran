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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddCenovnik extends AbstractSO {

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
        
        ArrayList<Cenovnik> cenovnici = (ArrayList<Cenovnik>)(ArrayList<?>)DBBroker.getInstance().select(ado);
        
        for (Cenovnik cenovnik : cenovnici) {
            if(cenovnik.getTipCenovnika().equals(c.getTipCenovnika())){
                throw new Exception("Vec postoji cenovnik tipa " + c.getTipCenovnika() + " u restoranu!");
            }
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long cenovnikID = tableKeys.getLong(1);
        
        
        Cenovnik c = (Cenovnik) ado;
        c.setCenovnikID(cenovnikID);
        
       
        for (Proizvod proizvod : c.getProizvodi()) {
            proizvod.setCenovnik(c);
            DBBroker.getInstance().insert(proizvod);
        }
        
    }

}
