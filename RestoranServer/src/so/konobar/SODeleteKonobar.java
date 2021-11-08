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
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SODeleteKonobar extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Konobar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Konobar!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        DBBroker.getInstance().delete(ado);
    }

}
