/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author PC
 */
public interface Operation {

    public static final int LOGIN = 0;

    public static final int ADD_KONOBAR = 1;
    public static final int DELETE_KONOBAR = 2;
    public static final int UPDATE_KONOBAR = 3;
    public static final int GET_ALL_KONOBAR = 4;

    public static final int GET_ALL_STO = 5;

    public static final int ADD_CENOVNIK = 6;
    public static final int UPDATE_CENOVNIK = 7;
    public static final int GET_ALL_CENOVNIK = 8;

    public static final int ADD_PROIZVOD = 9;
    public static final int UPDATE_PROIZVOD = 10;
    public static final int GET_ALL_PROIZVOD = 11;

    public static final int ADD_RACUN = 12;
    public static final int GET_ALL_RACUN = 13;
    
    public static final int ADD_STAVKA_RACUNA = 14;
    public static final int GET_ALL_STAVKA_RACUNA = 15;

}
