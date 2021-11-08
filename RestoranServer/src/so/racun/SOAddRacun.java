/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.racun;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Racun;
import domain.StavkaRacuna;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddRacun extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws SQLException {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long racunID = tableKeys.getLong(1);
        
        
        Racun r = (Racun) ado;
        r.setRacunID(racunID);
        
        for (StavkaRacuna stavkaRacuna : r.getStavkeRacuna()) {
            stavkaRacuna.setRacun(r);
            DBBroker.getInstance().insert(stavkaRacuna);
        }
    }

}
