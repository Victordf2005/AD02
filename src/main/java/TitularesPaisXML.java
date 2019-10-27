/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
        
/**
 *
 * @author Víctor Díaz
 */
public class TitularesPaisXML extends DefaultHandler {
    
    private ArrayList<TitularPais> titularesPais;
    
    private TitularPais titularAux;
    
    private String cadeaTexto;
    
    public TitularesPaisXML() {
        super();
    }
    
    boolean sonTitulares = false;
    
    @Override
    public void startDocument() throws SAXException {
        this.titularesPais = new ArrayList<TitularPais>();
    }
    
    @Override
    public void endDocument() throws SAXException {}
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes atributos) throws SAXException {
            
        if (localName == "title" && sonTitulares) {
            this.titularAux = new TitularPais();            
        }
        
        else if (localName == "item") {
            sonTitulares = true;
        }
        
    }
    
    public void endElement(String uri, String localName, String qName) throws SAXException {
            
        if (localName == "title" && sonTitulares) {
            this.titularAux.setTitular(cadeaTexto);
        }
        
        else if (localName == "item") {
            this.titularesPais.add(titularAux);
        }
    }
    
    public void characters (char[] ch, int comezo, int largo) throws SAXException {        
        this.cadeaTexto = new String(ch, comezo, largo);
    
    }
    
    public ArrayList<TitularPais> getTitulares() {return titularesPais;}
}

