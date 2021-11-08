/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.stavkaRacuna;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaRacuna;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddStavkaRacuna extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof StavkaRacuna)) {
            throw new Exception("Prosledjeni objekat nije instanca klase StavkaRacuna!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().insert(ado);
    }

}
