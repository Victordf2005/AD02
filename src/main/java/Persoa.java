/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Víctor Díaz
 */
public class Persoa {
    
    private String nome;
    private String apelidos;
    
    //Construtor por defecto
    public Persoa(){
        this.nome="";
        this.apelidos="";
    }
    
    //Construtor con parámetros
    public Persoa(String pNome, String pApelidos) {
        this.nome = pNome;
        this.apelidos = pApelidos;
    }
    
    //setters
    public void setNome(String parametro) {this.nome = parametro;}
    public void setApelidos(String parametro) {this.apelidos = parametro;}
    
    //getters
    public String getNome() {return this.nome;}
    public String getApelidos() {return this.apelidos;}
    
}
