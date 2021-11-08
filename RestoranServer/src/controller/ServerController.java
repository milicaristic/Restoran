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
import java.util.ArrayList;
import so.AbstractSO;
import so.cenovnik.SOAddCenovnik;
import so.cenovnik.SOGetAllCenovnik;
import so.cenovnik.SOUpdateCenovnik;
import so.konobar.SOAddKonobar;
import so.konobar.SODeleteKonobar;
import so.konobar.SOUpdateKonobar;
import so.konobar.SOGetAllKonobar;
import so.proizvod.SOAddProizvod;
import so.proizvod.SOGetAllProizvod;
import so.proizvod.SOUpdateProizvod;
import so.racun.SOAddRacun;
import so.racun.SOGetAllRacun;
import so.stavkaRacuna.SOAddStavkaRacuna;
import so.stavkaRacuna.SOGetAllStavkaRacuna;
import so.sto.SOGetAllSto;

/**
 *
 * @author PC
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public void addKonobar(Konobar konobar) throws Exception {
        AbstractSO aso = new SOAddKonobar();
        aso.templateExecute(konobar);
    }
    
    public void addCenovnik(Cenovnik cenovnik) throws Exception {
        AbstractSO aso = new SOAddCenovnik();
        aso.templateExecute(cenovnik);
    }
    
    public void addProizvod(Proizvod proizvod) throws Exception {
        AbstractSO aso = new SOAddProizvod();
        aso.templateExecute(proizvod);
    }
    
    public void addRacun(Racun racun) throws Exception {
        AbstractSO aso = new SOAddRacun();
        aso.templateExecute(racun);
    }
    
    public void addStavkaRacuna(StavkaRacuna stavkaRacuna) throws Exception {
        AbstractSO aso = new SOAddStavkaRacuna();
        aso.templateExecute(stavkaRacuna);
    }

    public void deleteKonobar(Konobar konobar) throws Exception {
        AbstractSO aso = new SODeleteKonobar();
        aso.templateExecute(konobar);
    }

    public void updateKonobar(Konobar konobar) throws Exception {
        AbstractSO aso = new SOUpdateKonobar();
        aso.templateExecute(konobar);
    }
    
    public void updateCenovnik(Cenovnik cenovnik) throws Exception {
        AbstractSO aso = new SOUpdateCenovnik();
        aso.templateExecute(cenovnik);
    }
    
    public void updateProizvod(Proizvod proizvod) throws Exception {
        AbstractSO aso = new SOUpdateProizvod();
        aso.templateExecute(proizvod);
    }

    public ArrayList<Konobar> getAllKonobar() throws Exception {
        SOGetAllKonobar so = new SOGetAllKonobar();
        so.templateExecute(new Konobar());
        return so.getLista();
    }
    
    public ArrayList<Cenovnik> getAllCenovnik() throws Exception {
        SOGetAllCenovnik so = new SOGetAllCenovnik();
        so.templateExecute(new Cenovnik());
        return so.getLista();
    }
    
    public ArrayList<Proizvod> getAllProizvod(Cenovnik c) throws Exception {
        SOGetAllProizvod so = new SOGetAllProizvod();
        
        Proizvod p = new Proizvod();
        p.setCenovnik(c);
        
        so.templateExecute(p);
        return so.getLista();
    }
    
    public ArrayList<Racun> getAllRacun() throws Exception {
        SOGetAllRacun so = new SOGetAllRacun();
        so.templateExecute(new Racun());
        return so.getLista();
    }
    
    public ArrayList<StavkaRacuna> getAllStavkaRacuna() throws Exception {
        SOGetAllStavkaRacuna so = new SOGetAllStavkaRacuna();
        so.templateExecute(new StavkaRacuna());
        return so.getLista();
    }
    
    public ArrayList<Sto> getAllSto() throws Exception {
        SOGetAllSto so = new SOGetAllSto();
        so.templateExecute(new Sto());
        return so.getLista();
    }

}
