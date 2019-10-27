/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Víctor Díaz
 */
public class Cliente extends Persoa {
    
    private String email;
    
    //construtor por defecto
    public Cliente() {
        super();
        this.email="";
    }
    
    //construtor con parámetros
    public Cliente(String pNome, String pApelidos, String pEmail) {
        super(pNome, pApelidos);
        this.email = pEmail;
    }
    
    //setters
    public void setNome(String parametro) {super.setNome(parametro);}
    public void setApelidos(String parametro) {super.setApelidos(parametro);}
    public void setEmail(String parametro) {this.email = parametro;}
    
    //getters
    public String getNome() {return super.getNome();}
    public String getApelidos() {return super.getApelidos();}
    public String getEmail() {return this.email;}
        
}
