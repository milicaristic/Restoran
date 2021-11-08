/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.cenovnik;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Cenovnik;
import java.sql.SQLException;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllCenovnik extends AbstractSO {

    private ArrayList<Cenovnik> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Cenovnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Cenovnik!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        ArrayList<AbstractDomainObject> cenovnici
                = (ArrayList<AbstractDomainObject>) DBBroker.getInstance().select(ado);
        lista = (ArrayList<Cenovnik>) (ArrayList<?>) cenovnici;
    }

    public ArrayList<Cenovnik> getLista() {
        return lista;
    }

}
