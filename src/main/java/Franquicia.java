/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author Víctor Díaz
 */
public class Franquicia {
    
    private ArrayList<Tenda> tendas;
    private ArrayList<Cliente> clientes;
    
    //construtor por defecto
    public Franquicia() {
        this.tendas = new ArrayList<Tenda>();
        this.clientes = new ArrayList<Cliente>();
    }
    
    //construtor con parámetros
    public Franquicia(ArrayList<Tenda> pTendas, ArrayList<Cliente> pClientes) {
        this.tendas = new ArrayList<Tenda>(pTendas);
        this.clientes = new ArrayList<Cliente>(pClientes);
    }
    
    //setters
    public void setTendas(ArrayList<Tenda> parametro) {this.tendas = parametro;}
    public void setClientes(ArrayList<Cliente> parametro) {this.clientes = parametro;}
    
    //getters
    public ArrayList<Tenda> getTendas() {return tendas;}
    public ArrayList<Cliente> getClientes() {return clientes;}
    
}
