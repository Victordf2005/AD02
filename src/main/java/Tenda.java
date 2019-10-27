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
public class Tenda {
    
    private String nome;
    private String cidade;
    private ArrayList<Produto> produtos;
    private ArrayList<Empregado> empregados;
    
    //construtor por defecto
    public Tenda(){
        this.nome="";
        this.cidade="";
        this.produtos = new ArrayList<Produto>();
        this.empregados = new ArrayList<Empregado>();
    }
    
    //construtor con parámetros
    public Tenda(String pNome, String pCidade, ArrayList<Produto> pProdutos, ArrayList<Empregado> pEmpregados) {
        this.nome = pNome;
        this.cidade = pCidade;
        this.produtos = new ArrayList<Produto>(pProdutos);
        this.empregados = new ArrayList<Empregado>(pEmpregados);
    }
    
    //setters
    public void setNome(String parametro) {this.nome = parametro;}
    public void setCidade(String parametro)  {this.cidade = parametro;}
    public void setProdutos(ArrayList<Produto> parametro) {this.produtos = parametro;}
    public void setEmpregados(ArrayList<Empregado> parametro) {this.empregados = parametro;}
    
    //getters
    public String getNome() {return this.nome;}
    public String getCidade() {return this.cidade;}
    public ArrayList<Produto> getProdutos() {return this.produtos;}
    public ArrayList<Empregado> getEmpregados() {return this.empregados;}
    
}
