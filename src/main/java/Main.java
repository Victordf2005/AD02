/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


/**
 *
 * @author Víctor Díaz
 */
public class Main {
        
    public static void main (String[] args) throws FileNotFoundException {
        
        //Clase que gardará todos os datos da franquicia
        Franquicia franquicia = new Franquicia();
        
        //cargar datos do arquivo JSON
        File arquivo = new File("datosFranquicia.json");
        
        if (arquivo.exists()) {
            
            //Cargamos os datos almacenados no arquivo json
            try {

                //Fluxo e buffer de entrada para o arquivo
                FileReader fluxoDatos = new FileReader(arquivo);
                BufferedReader entrada = new BufferedReader(fluxoDatos);

                //Lemos o arquivo liña a liña
                StringBuilder jsonBuilder = new StringBuilder();
                String linea;

                while ((linea = entrada.readLine()) != null) {
                    jsonBuilder.append(linea).append("\n");
                }

                //pechamos o arquivo
                entrada.close();

                //construimos o string con todas as liñas lidas
                String json = jsonBuilder.toString();

                //Pasamos o json á clase correspondente
                Gson gson = new Gson();
                franquicia = gson.fromJson(json, Franquicia.class);

            } catch (IOException erro) {}
            
        }        
    
        Scanner teclado = new Scanner(System.in);

        String opcionMenu="";

        while (opcionMenu.toUpperCase() != "S") {

            //Presentamos o menú de opcións e esperamos pola elección do usuario
            presentarMenu();
            opcionMenu = teclado.nextLine();

            switch (opcionMenu.toUpperCase()) {

                case "+T" : {
                    engadirTenda(franquicia);
                    gravarDatos(arquivo, franquicia);
                    break;
                }

                case "-T" : {
                    eliminarTenda(franquicia);
                    gravarDatos(arquivo, franquicia);
                    break;
                }

                case "+P" : {
                    engadirProduto(franquicia);
                    gravarDatos(arquivo, franquicia);
                    break;
                }

                case "-P" : {
                    eliminarProduto(franquicia);
                    gravarDatos(arquivo, franquicia);
                    break;
                }

                case "+E" : {
                    engadirEmpregado(franquicia);
                    gravarDatos(arquivo, franquicia);
                    break;
                }

                case "-E" : {
                    eliminarEmpregado(franquicia);
                    gravarDatos(arquivo, franquicia);
                    break;
                }

                case "VE" : {
                    verEmpregados(franquicia);
                    break;
                }

                case "+C" : {
                    engadirCliente(franquicia);
                    gravarDatos(arquivo, franquicia);
                    break;
                }

                case "-C" : {
                    eliminarCliente(franquicia);
                    gravarDatos(arquivo, franquicia);
                    break;
                }

                case "CS" : {
                    copiaSeguridade();
                    break;
                }

                case "EP" : {
                    titularesEP();
                    break;
                }

                case "S" : {
                    System.out.println("*** FIN DE EXECUCION ***");
                    break;
                }

                default : {
                    System.out.println("******* OPCIÓN NON PRESENTE NO MENU *******");
                }
            }
        }            
            
    }
    
    private static void presentarMenu() {
        
        //Presentamos as distintas opcións do menú principal.
        System.out.println("\n==================================================");
        System.out.println("                    VDF PCs");
        System.out.println("==================================================\n");
        System.out.println("----------------- MENU PRINCIPAL -----------------\n");
        System.out.println("TENDAS -> Engadir (+T)");
        System.out.println("TENDAS -> Eliminar (-T)\n");
        System.out.println("PRODUTOS -> Engadir a unha tenda (+P)");
        System.out.println("PRODUTOS -> Eliminar dunha tenda (-P)\n");
        System.out.println("EMPREGADOS -> Engadir a unha tenda (+E)");
        System.out.println("EMPREGADOS -> Eliminar dunha tenda (-E)");
        System.out.println("EMPREGADOS -> Ver empregados dunha tenda (VE)\n");
        System.out.println("CLIENTES -> Engadir (+C)");
        System.out.println("CLIENTES -> Eliminar (-C)\n");
        System.out.println("COPIA de seguridade (CS)");
        System.out.println("EL PAIS, ver titulares (EP)");
        System.out.println("SAIR (S)\n");
    }        
    
    //Método que grava os datos actualizados no arquivo json
    private static void gravarDatos(File arquivo, Franquicia franquicia) throws FileNotFoundException {
        
        //Pasamos a clase franquicia á Json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(franquicia);
        
        try {
            //Creamos o fluxo de saída
            FileWriter fluxoDatos = new FileWriter(arquivo);
            BufferedWriter saida = new BufferedWriter(fluxoDatos);
            //Gravamos
            saida.write(json);
            //pechamos o arquivo
            saida.close();
        } catch(IOException erro){}
        
    }
    
    //Método para pedir datos dunha nova tenda e engadila á franquicia
    private static void engadirTenda(Franquicia franquicia) {
    
        Scanner teclado = new Scanner(System.in);
        
        //Pedimos datos da tenda
        System.out.println("Teclea o nome da tenda.\n");        
        String tendaNome = teclado.nextLine();
        
        System.out.println("Teclea o nome da cidade na que se ubica.\n");
        String tendaCidade = teclado.nextLine();
        
        //Pedimos confirmación para engadila
        System.out.printf("Engadimos a tenda %s de %s á franquicia (S/N)? ", tendaNome, tendaCidade);
        
        if (teclado.nextLine().equalsIgnoreCase("S")) {
            //Engadimos a nova tenda
            franquicia.getTendas().add(new Tenda(tendaNome, tendaCidade, new ArrayList<Produto>(), new ArrayList<Empregado>()));
            
        } else {
            System.out.println(">>>>> A tenda non foi engadida.");
        }            
                
    }
    
    //Método para eliminar unha tenda da franquicia
    private static void eliminarTenda(Franquicia franquicia) {
            
        Scanner teclado = new Scanner(System.in);
        
        if (franquicia.getTendas().size() > 0) {
            //Persentamos listaxe de tendas abertas para que elixa a que se quere eliminar.
            System.out.println("Teclea o nº da tenda que desexas eliminar (tecleando un nº fóra da lista ou negativo non se eliminará). ");
            listarTendas(franquicia);

            int numTenda = Integer.parseInt(teclado.nextLine());

            //Comprobamos que tecleara o número dunha tenda existente
            if (numTenda >=0 && numTenda < franquicia.getTendas().size()) {

                //Preguntamos se realmente queremos eliminar esa tenda.
                System.out.printf("desexas eliminar a tenda %s de %s da franquicia (S/N)? ", franquicia.getTendas().get(numTenda).getNome(), franquicia.getTendas().get(numTenda).getCidade());

                if (teclado.nextLine().equalsIgnoreCase("S")) {
                    //Eliminamos a tenda
                    franquicia.getTendas().remove(numTenda);
                } else {
                    System.out.println(">>>>> A tenda non foi eliminada.");
                }

            } else {
                System.out.println(">>>>> Nº de tenda inexistente. A tenda non foi eliminada.");
            }
        } else {
            System.out.println(">>>>> Non hai tendas abertas.");
        }
            
    }
    
    //Método para pedir datos dun novo produto e engadilo a unha tenda
    private static void engadirProduto(Franquicia franquicia) {
    
        Scanner teclado = new Scanner(System.in);
        
        //Comprobamos se hai tendas abertas
        if (franquicia.getTendas().size() > 0) {
            
            //pedimos datos do produto
            System.out.println("Teclea o código identificador do produto ... ");
            int id = Integer.parseInt(teclado.nextLine());
            System.out.println("Teclea a descrición do produto ... ");
            String desc = teclado.nextLine();
            System.out.println("Teclea o prezo ... ");
            double prezo = Double.parseDouble(teclado.nextLine());
            System.out.println("Teclea a cantidade de produtos ... ");
            int cant = Integer.parseInt(teclado.nextLine());
            
            //Listamos as tendas abertas para que se elixa a que terá o novo produto
            System.out.println("Teclea o nº da tenda á que se asigna (tecleando un nº fóra da lista ou negativo non se engadirá o produto). ");
            listarTendas(franquicia);
            
            int numTenda = Integer.parseInt(teclado.nextLine());

            //Comprobamos que tecleara o número dunha tenda existente
            if (numTenda >=0 && numTenda < franquicia.getTendas().size()) {
                //Engadimos o novo produto
                franquicia.getTendas().get(numTenda).getProdutos().add(new Produto(id, desc, prezo, cant));
            } else {
                System.out.println(">>>>> Nº de tenda inexistente. O produto non foi engadido.");
            }
        } else {
            System.out.println(">>>>> Non hai tendas abertas ás que engadir o produto.");
        }
       
    }
    
    //Método para eliminar un produto dunha tenda
    private static void eliminarProduto(Franquicia franquicia) {
            
        Scanner teclado = new Scanner(System.in);
        
        //Listamos as tendas abertas
        System.out.println("Teclea o nº da tenda da que desexas eliminar o produto (tecleando un nº fóra da lista ou negativo cancelamos o proceso). ");
        listarTendas(franquicia);

        int numTenda = Integer.parseInt(teclado.nextLine());

        //Comprobamos que tecleara o nº dunha tenda existente
        if (numTenda >=0 && numTenda < franquicia.getTendas().size()) {
            
            //Comprobamos que esa tenda teña produtos
            if (franquicia.getTendas().get(numTenda).getProdutos().size() > 0) {
                
                //Presentamos os produtos que hai nesa tenda e agardamos pola resposta do usuario                
                System.out.println("Teclea o nº do produto a eliminar (tecleando un nº fóra da lista ou negativo cancelamos o proceso). ");
                
                listarProdutos(franquicia, numTenda);
                
                int numProduto = Integer.parseInt(teclado.nextLine());
                int id = franquicia.getTendas().get(numTenda).getProdutos().get(numProduto).getId();
                String descricion = franquicia.getTendas().get(numTenda).getProdutos().get(numProduto).getDescricion();
                String tenda = franquicia.getTendas().get(numTenda).getNome();
                String cidade = franquicia.getTendas().get(numTenda).getCidade();
                
                //Preguntamos se realmente queremos eliminar ese produto.
                System.out.printf("Desexas eliminar o produto %d-%s da tenda %s de %s da franquicia (S/N)? ", id, descricion, tenda, cidade);

                if (teclado.nextLine().equalsIgnoreCase("S")) {
                    //Eliminamos a tenda
                    franquicia.getTendas().get(numTenda).getProdutos().remove(numProduto);
                } else {
                    System.out.println(">>>>> O produto non foi eliminado.");
                }
            } else {                
                System.out.println(">>>>> Non hai produtos nesta tenda.");
            }                
            
        } else {
            System.out.println(">>>>> Nº de tenda inexistente. Proceso cancelado.");
        }
    }
    
    //Método para engadir un empregado a unha tenda
    private static void engadirEmpregado(Franquicia franquicia) {
        
        Scanner teclado = new Scanner(System.in);
        
        //Comprobamos se hai tendas abertas
        if (franquicia.getTendas().size() > 0) {
            
            //pedimos datos do empregado
            System.out.println("Teclea o nome do empregado ... ");
            String nome = teclado.nextLine();
            System.out.println("Teclea os apelidos do empregado ... ");
            String apelidos = teclado.nextLine();

            //Presentamos as tendas existentes e agardamos pola resposta do usuario
            System.out.println("Teclea o nº da tenda á que se asigna (tecleando un nº fóra da lista ou negativo non se engadirá o empregado). ");
            listarTendas(franquicia);
            
            int numTenda = Integer.parseInt(teclado.nextLine());

            if (numTenda >=0 && numTenda < franquicia.getTendas().size()) {
                //Engadimos o empregado á tenda seleccionada
                franquicia.getTendas().get(numTenda).getEmpregados().add(new Empregado(nome, apelidos));
            } else {
                System.out.println(">>>>> Nº de tenda inexistente. O empregado non foi engadido.");
            }
        } else {
            System.out.println(">>>>> Non hai tendas abertas ás que engadir o empregado.");
        }
    }
    
    //Método para eliminar un empregado dunha tenda
    private static void eliminarEmpregado(Franquicia franquicia) {
            
        Scanner teclado = new Scanner(System.in);
        
        //Presentamos as tendas existentes para que o usuario a seleccione
        System.out.println("Teclea o nº da tenda da que desexas eliminar o empregado (tecleando un nº fóra da lista ou negativo cancelamos o proceso). ");
        listarTendas(franquicia);

        int numTenda = Integer.parseInt(teclado.nextLine());

        //Comprobamos que tecleara o número dunha tenda da listaxe
        if (numTenda >=0 && numTenda < franquicia.getTendas().size()) {
            
            //Comprobamos que esa tenda teña empregados
            if (franquicia.getTendas().get(numTenda).getEmpregados().size() > 0) {
                
                //Preguntamos polo empregado a eliminar                
                System.out.println("Teclea o nº do empregado a eliminar (tecleando un nº fóra da lista ou negativo cancelamos o proceso). ");
                listarEmpregados(franquicia, numTenda);
                
                int numEmpregado = Integer.parseInt(teclado.nextLine());
                String nome = franquicia.getTendas().get(numTenda).getEmpregados().get(numEmpregado).getNome();
                String apelidos = franquicia.getTendas().get(numTenda).getEmpregados().get(numEmpregado).getApelidos();
                String tenda = franquicia.getTendas().get(numTenda).getNome();
                String cidade = franquicia.getTendas().get(numTenda).getCidade();
                
                //Preguntamos se realmente queremos eliminar ese empregado.
                System.out.printf("Desexas eliminar o empregado %s %s da tenda %s de %s da franquicia (S/N)? ", nome, apelidos, tenda, cidade);

                if (teclado.nextLine().equalsIgnoreCase("S")) {
                    //Eliminamos a tenda
                    franquicia.getTendas().get(numTenda).getEmpregados().remove(numEmpregado);
                } else {
                    System.out.println(">>>>> O empregado non foi engadido.");
                }
            } else {                
                System.out.println(">>>>> Non hai empregados nesta tenda.");
            }
            
        } else {
            System.out.println(">>>>> Nº de tenda inexistentes. Proceso cancelado.");
        }
    }
    
    //Método para listar os empregados dunha tenda
    private static void verEmpregados(Franquicia franquicia) {
    
        Scanner teclado = new Scanner(System.in);
        
        //presentamos a listaxe de tendas existentes para que o usuario seleccione da que quere ver empregados
        System.out.println("Teclea o nº da tenda da que desexas ver os empregados (tecleando un nº fóra da lista ou negativo cancelamos o proceso). ");
        listarTendas(franquicia);

        int numTenda = Integer.parseInt(teclado.nextLine());

        //Comprobamos que exista a tenda tecleada
        if (numTenda >=0 && numTenda < franquicia.getTendas().size()) {
            
            //Comprobamos que esa tenda teña empregados
            if (franquicia.getTendas().get(numTenda).getEmpregados().size() > 0) {
                listarEmpregados(franquicia, numTenda);
            } else {                
                System.out.println(">>>>> Non hai empregados nesta tenda.");
            }
            
        } else {
            System.out.println(">>>>> Nº de tenda inexistente. Proceso cancelado.");
        }
    }
    
    //Método para engadir un novo cliente da franquicia
    private static void engadirCliente(Franquicia franquicia) {
            
        Scanner teclado = new Scanner(System.in);
                
        //pedimos datos do cliente
        System.out.println("Teclea o nome do cliente ... ");
        String nome = teclado.nextLine();
        System.out.println("Teclea os apelidos do cliente ... ");
        String apelidos = teclado.nextLine();
        System.out.println("Teclea o enderezo de email do cliente ... ");
        String email = teclado.nextLine();

        //Engadimos o novo cliente.
        franquicia.getClientes().add(new Cliente(nome, apelidos, email));
        
    }
    
    //Método para eliminar un cliente da franquicia
    private static void eliminarCliente(Franquicia franquicia) {
        
        Scanner teclado = new Scanner(System.in);
        
        //Comprobamos que teñamos clientes
        if (franquicia.getClientes().size() > 0) {
            
            //Presentamos a listaxe de clientes para que o usuario seleccione cal daremos de baixa
            System.out.println("Teclea o nº de cliente que desexas eliminar (tecleando un nº fóra da lista ou negativo non se eliminará). ");
            listarClientes(franquicia);

            int numCliente = Integer.parseInt(teclado.nextLine());

            //Comprobamos que tecleara o nº dun cliente existente
            if (numCliente >=0 && numCliente < franquicia.getClientes().size()) {

                //Preguntamos se realmente queremos eliminar ese cliente.
                System.out.printf("desexas eliminar o cliente %s da franquicia (S/N)? ", franquicia.getClientes().get(numCliente).getNome() + " " + franquicia.getClientes().get(numCliente).getApelidos());

                if (teclado.nextLine().equalsIgnoreCase("S")) {
                    //Eliminamos a tenda
                    franquicia.getClientes().remove(numCliente);
                } else {
                    System.out.println(">>>>> O cliente non foi eliminado.");
                }

            } else {
                System.out.println(">>>>> Nº de cliente inexistente. O cliente non foi eliminado.");
            }
        } else {
            System.out.println(">>>>> Non hai clientes.");
        }
        
    }
    
    //Método para listar tendas da franquicia
    private static void listarTendas(Franquicia franquicia) {
    
        ArrayList<Tenda> tendas = new ArrayList<Tenda>(franquicia.getTendas());
        
        System.out.println("\n===== Listaxe de tendas abertas ====\n");
        
        for (int i=0; i<tendas.size(); i++) {
            System.out.println(i + " -> " + tendas.get(i).getNome() + " (" + tendas.get(i).getCidade() +")");
        }
        
    }
    
    //Método para listar produtos dumha tenda
    private static void listarProdutos (Franquicia franquicia, int tenda) {
    
        ArrayList<Produto> produtos = new ArrayList<Produto>(franquicia.getTendas().get(tenda).getProdutos());
        
        System.out.println("\n===== Listaxe de produtos da tenda " + franquicia.getTendas().get(tenda).getNome() + " (" +franquicia.getTendas().get(tenda).getCidade() + ") =====");
        
        for (int i=0; i<produtos.size(); i++) {
            System.out.println(i + " -> id: " + produtos.get(i).getId() + ", descrición: " + produtos.get(i).getDescricion());            
        }
    
    }
    
    //Método para listar empregados dunha tenda
    private static void listarEmpregados (Franquicia franquicia, int tenda) {
    
        ArrayList<Empregado> empregados = new ArrayList<Empregado>(franquicia.getTendas().get(tenda).getEmpregados());
        
        System.out.println("\n===== Listaxe de empregados da tenda " + franquicia.getTendas().get(tenda).getNome() + " (" +franquicia.getTendas().get(tenda).getCidade() + ") =====");
        
        for (int i=0; i<empregados.size(); i++) {
            System.out.println(i + " -> " + empregados.get(i).getNome() + " " + empregados.get(i).getApelidos());
        }
    }

    //Método para listar clientes da franquicia
    private static void listarClientes (Franquicia franquicia) {
    
        ArrayList<Cliente> clientes = new ArrayList<Cliente>(franquicia.getClientes());
        
        System.out.println("\n===== Listaxe de clientes =====");
        
        for (int i=0; i<clientes.size(); i++) {
            System.out.println(i + " -> " + clientes.get(i).getNome() + " " + clientes.get(i).getApelidos());
        }
    }
    
    //Método que realiza unha copia de seguridade do arquivo de datos json
    private static void copiaSeguridade() {
    
        try {
            //Declaramos os ficheiros E/S
            File arquivoOrixinal = new File("datosFranquicia.json");
            File arquivoCopia = new File("datosFranquicia_json.backup");
            
            //Fluxos de E/S
            FileInputStream fluxoEntrada = new FileInputStream(arquivoOrixinal);
            FileOutputStream fluxoSaida = new FileOutputStream(arquivoCopia);
            
            //Lemos byte a byte e gravamos na copia
            int datoByte;
            
            while ((datoByte = fluxoEntrada.read()) != -1) {
                fluxoSaida.write(datoByte);
            }
            
            //pechamos os arquivos
            fluxoEntrada.close();
            fluxoSaida.close();
            
            System.out.println(">>>>> Copia de seguridade realizada.");
        }
        catch (IOException erro) {
            System.out.println(">>>>> Fallo no proceso de copia de seguridade.");
        }
    }
    
    //Método para amosar en pantalla os titulares do arquivo RSS on-line de El Pais
    private static void titularesEP() {
    
        XMLReader procesadorXML = null;
        
        try {
            
            System.out.println("Buscando titulares do periódico El País ... agarde, por favor.\n");
            
            //Creamos o parseador
            procesadorXML = XMLReaderFactory.createXMLReader();
            TitularesPaisXML titularesPaisXML = new TitularesPaisXML();
            procesadorXML.setContentHandler(titularesPaisXML);
            
            //indicamos a ruta do arquivo
            InputSource arquivo = new InputSource("http://ep00.epimg.net/rss/elpais/portada.xml");
            procesadorXML.parse(arquivo);
            
            //imprimimos os datos
            ArrayList<TitularPais> titulares = titularesPaisXML.getTitulares();
            for (int i=0; i<titulares.size(); i++) {
                System.out.println(titulares.get(i).getTitular());
            }
        
        } catch (SAXException erro) {
            System.out.println("Ocorreu un erro ao ler o arquivo XML");
        } catch (IOException erro) {
            System.out.println("Ocorreu un erro ao ler o arquivo XML");
        }
            
    }
    
}
