/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Cenovnik;
import domain.Konobar;
import domain.Proizvod;
import domain.Racun;
import domain.StavkaRacuna;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;

/**
 *
 * @author PC
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request req = (Request) in.readObject();
                Response res = handleRequest(req);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request req) {
        Response res = new Response(null, null, ResponseStatus.Success);
        try {
            switch (req.getOperation()) {
                case Operation.ADD_KONOBAR:
                    ServerController.getInstance().addKonobar((Konobar) req.getData());
                    break;
                case Operation.ADD_CENOVNIK:
                    ServerController.getInstance().addCenovnik((Cenovnik) req.getData());
                    break;
                case Operation.ADD_PROIZVOD:
                    ServerController.getInstance().addProizvod((Proizvod) req.getData());
                    break;
                case Operation.ADD_RACUN:
                    ServerController.getInstance().addRacun((Racun) req.getData());
                    break;
                case Operation.ADD_STAVKA_RACUNA:
                    ServerController.getInstance().addStavkaRacuna((StavkaRacuna) req.getData());
                    break;
                case Operation.DELETE_KONOBAR:
                    ServerController.getInstance().deleteKonobar((Konobar) req.getData());
                    break;
                case Operation.UPDATE_KONOBAR:
                    ServerController.getInstance().updateKonobar((Konobar) req.getData());
                    break;
                case Operation.UPDATE_CENOVNIK:
                    ServerController.getInstance().updateCenovnik((Cenovnik) req.getData());
                    break;
                case Operation.UPDATE_PROIZVOD:
                    ServerController.getInstance().updateProizvod((Proizvod) req.getData());
                    break;
                case Operation.GET_ALL_KONOBAR:
                    res.setData(ServerController.getInstance().getAllKonobar());
                    break;
                case Operation.GET_ALL_CENOVNIK:
                    res.setData(ServerController.getInstance().getAllCenovnik());
                    break;
                case Operation.GET_ALL_PROIZVOD:
                    res.setData(ServerController.getInstance().getAllProizvod((Cenovnik)req.getData()));
                    break;
                case Operation.GET_ALL_RACUN:
                    res.setData(ServerController.getInstance().getAllRacun());
                    break;
                case Operation.GET_ALL_STAVKA_RACUNA:
                    res.setData(ServerController.getInstance().getAllStavkaRacuna());
                    break;
                case Operation.GET_ALL_STO:
                    res.setData(ServerController.getInstance().getAllSto());
                    break;
                case Operation.LOGIN:
                    ArrayList<Konobar> lista = ServerController.getInstance().getAllKonobar();
                    Konobar k = (Konobar) req.getData();
                    for (Konobar konobar : lista) {
                        if (konobar.getUsername().equals(k.getUsername())
                                && konobar.getPassword().equals(k.getPassword())) {
                            res.setData(konobar);
                        }
                    }
                    if (res.getData() == null) {
                        throw new Exception("Ne postoji konobar sa tim kredencijalima.");
                    } else {
                        break;
                    }
                default:
                    return null;
            }
        } catch (Exception e) {
            res.setError(e);
            res.setData(null);
            res.setResponseStatus(ResponseStatus.Error);
        }
        return res;
    }

}
