/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Víctor Díaz
 */
public class Empregado extends Persoa {
    
    //construtor por defecto
    public Empregado() {
        super();
    }
    
    //construtor con parámetros
    public Empregado(String pNome, String pApelidos) {
        super(pNome, pApelidos);
    }
    
    //setters
    public void setNome(String parametro) {super.setNome(parametro);}
    public void setApelidos(String parametro) {super.setApelidos(parametro);}
    
    //getters
    public String getNome() {return super.getNome();}
    public String getApeidos() {return super.getApelidos();}
        
}
