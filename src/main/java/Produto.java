/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Víctor Díaz
 */
public class Produto {
    
    private int id;
    private String descricion;
    private double prezo;
    private int cantidade;
    
    
    //Construtor por defecto
    public Produto(){
        this.id =0;
        this.descricion = new String("");
        this.prezo = 0.0;
        this.cantidade = 0;
    }
    
    //Contrutor con parámetros
    public Produto(int pId, String pDescricion, double pPrezo, int pCantidade){
        this.id = pId;
        this.descricion = new String(pDescricion);
        this.prezo = pPrezo;
        this.cantidade = pCantidade;
    }
    
    //setters
    public void setId(int parametro) {this.id = parametro;}    
    public void setDescricion(String parametro) {this.descricion = parametro;}    
    public void setPrezo(float parametro) {this.prezo = parametro;}    
    public void setCantidade(int parametro) {this.cantidade = parametro;}
    
    //getters
    public int getId() {return this.id;}    
    public String getDescricion() {return this.descricion;}    
    public double getPrezo() {return this.prezo;}    
    public int getCantidade() {return this.cantidade;}
        
}
