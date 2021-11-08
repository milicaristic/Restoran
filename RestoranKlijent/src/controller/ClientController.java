/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Cenovnik;
import domain.Konobar;
import domain.Proizvod;
import domain.Racun;
import domain.StavkaRacuna;
import domain.Sto;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author PC
 */
public class ClientController {

    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Konobar login(Konobar konobar) throws Exception {
        return (Konobar) sendRequest(Operation.LOGIN, konobar);
    }

    public void addKonobar(Konobar konobar) throws Exception {
        sendRequest(Operation.ADD_KONOBAR, konobar);
    }
    
    public void addCenovnik(Cenovnik cenovnik) throws Exception {
        sendRequest(Operation.ADD_CENOVNIK, cenovnik);
    }
    
    public void addProizvod(Proizvod proizvod) throws Exception {
        sendRequest(Operation.ADD_PROIZVOD, proizvod);
    }
    
    public void addRacun(Racun racun) throws Exception {
        sendRequest(Operation.ADD_RACUN, racun);
    }
    
    public void addStavkaRacuna(StavkaRacuna stavkaRacuna) throws Exception {
        sendRequest(Operation.ADD_STAVKA_RACUNA, stavkaRacuna);
    }

    public void deleteKonobar(Konobar konobar) throws Exception {
        sendRequest(Operation.DELETE_KONOBAR, konobar);
    }

    public void updateKonobar(Konobar konobar) throws Exception {
        sendRequest(Operation.UPDATE_KONOBAR, konobar);
    }
    
    public void updateProizvod(Proizvod proizvod) throws Exception {
        sendRequest(Operation.UPDATE_PROIZVOD, proizvod);
    }
    
    public void updateCenovnik(Cenovnik cenovnik) throws Exception {
        sendRequest(Operation.UPDATE_CENOVNIK, cenovnik);
    }

    public ArrayList<Konobar> getAllKonobar() throws Exception {
        return (ArrayList<Konobar>) sendRequest(Operation.GET_ALL_KONOBAR, null);
    }
    
    public ArrayList<Cenovnik> getAllCenovnik() throws Exception {
        return (ArrayList<Cenovnik>) sendRequest(Operation.GET_ALL_CENOVNIK, null);
    }
    
    public ArrayList<Proizvod> getAllProizvod(Cenovnik c) throws Exception {
        return (ArrayList<Proizvod>) sendRequest(Operation.GET_ALL_PROIZVOD, c);
    }
    
    public ArrayList<Racun> getAllRacun() throws Exception {
        return (ArrayList<Racun>) sendRequest(Operation.GET_ALL_RACUN, null);
    }
    
    public ArrayList<StavkaRacuna> getAllStavkaRacuna() throws Exception {
        return (ArrayList<StavkaRacuna>) sendRequest(Operation.GET_ALL_STAVKA_RACUNA, null);
    }
    
    public ArrayList<Sto> getAllSto() throws Exception {
        return (ArrayList<Sto>) sendRequest(Operation.GET_ALL_STO, null);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request req = new Request(operation, data);
        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(req);
        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response res = (Response) in.readObject();
        if (res.getResponseStatus().equals(ResponseStatus.Error)) {
            throw res.getError();
        } else {
            return res.getData();
        }
    }
}
