/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Víctor Díaz
 */
public class TitularPais {
    
    String titular;
    
    //Construtor por defecto
    public void TitularPais() {
        this.titular="";
    }
    
    //Construtor con parámetros
    public void TitularPais(String parametro) {
        this.titular = parametro;
    }
    
    //setter
    public void setTitular(String parametro) {this.titular = parametro;}
    
    //getter
    public String getTitular() {return this.titular;}
    
}
