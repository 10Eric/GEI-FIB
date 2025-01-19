package edu.upc.prop.clusterxx;

import java.util.ArrayList;

public class Distribucio {
   //Atributos de la clase prestatgeria
    private String nombre;
    private int numero_prestatges;
    private boolean usar_cambios_fijos;
    //sera false cuando queremos ordenar todos por igual,
    // sino se quedaran fijos los productosColocats que tienen el bool fijo en true.

    //Relaciones de la clase Prestatgeria
    private ArrayList<ProducteColocat> productesColocats;
    private EstrategiaCalculo estrategiaCalculo;

    //Constructora
    public Distribucio(String nom){
        this.nombre = nom;
        this.numero_prestatges = 0;
        this.productesColocats =  new ArrayList<>();
        this.usar_cambios_fijos = false;
        this.estrategiaCalculo = new CalculBackTracking();
    }

    public Distribucio(String nom, int numero_prestatge){
        this.nombre = nom;
        this.numero_prestatges = numero_prestatge;
        this.productesColocats =  new ArrayList<>();
        this.usar_cambios_fijos = false;
        this.estrategiaCalculo = new CalculBackTracking();
    }

    //Constructora por defecto
    public Distribucio(){
        this.numero_prestatges = 0;
        this.productesColocats =  new ArrayList<>();
        this.usar_cambios_fijos = false;
        this.estrategiaCalculo = new CalculBackTracking();

    }

    public EstrategiaCalculo getEstrategiaCalculo() {
        return estrategiaCalculo;
    }

    //devuelve el array de productes colocats que tiene asociado, si se ordenó con anterioridad,
    //este array ya esta ordeando y listo para imprimir en el controlador
    public ArrayList<ProducteColocat> getProductesColocats() {
        return productesColocats;
    }

    //Añade productoColocado con la posición introducida

    public void afegeix_producte_colocat(ProducteColocat producteColocat){
                productesColocats.add(producteColocat);
                numero_prestatges++;
    }

            //Devuelve el producto de la posicion = pos esto SOLO FUNCIONA SI ESTA ORDENADA LA LISTA
    public Producte obtenirProductePos(int pos) {
        //De forma alternativa se puede hacer una busqueda por_todo el array en el caso que no sea el mismo array
        //el que ordenamos que el que guardamos los productos colocats
        if (pos <= numero_prestatges && pos >= 0){
            return productesColocats.get(pos-1).getProducte();
        }
        else return null;
    }


    public int getNumero_prestatges(){
        return numero_prestatges;
    }

    //aqui ya podria ser un objeto de entrada no un string
    public void elimina_producte(String producte){
        for (int i = 0 ; i < this.productesColocats.size(); i++){
            if (this.productesColocats.get(i).getnom().equals(producte)){
                this.productesColocats.remove(i);
            }
        }
    }

    public void intercambiar_productes(int seleccion1, int seleccion2){
        if (seleccion1 > 0 && seleccion1 <= productesColocats.size() && seleccion2 > 0 && seleccion2 <= productesColocats.size()) {
            ProducteColocat p1 = productesColocats.get(seleccion1);
            ProducteColocat p2 = productesColocats.get(seleccion2);

            int tempPos = p1.getPos();
            int tempAltura = p1.getAltura();
            p1.setPos(p2.getPos());
            p1.setAltura(p2.getAltura());
            p2.setPos(tempPos);
            p2.setAltura(tempAltura);
            System.out.println("Productos intercambiados.");
        } else {
            System.out.println("Selección no válida.");
        }
    }

    //en la capa de dominio debemos crear el objeto ProducteColocat y pasarselo para enlazarlo (navegabilidad)
    public void afegeix_producte(ProducteColocat producteColocat){
        productesColocats.add(producteColocat);
    }

    public void setCambiosManualesRealizados(boolean cambios_fijos) {
        this.usar_cambios_fijos = cambios_fijos;
    }

    public void setProductesColocats(ArrayList<ProducteColocat> productesColocats) {
        this.productesColocats = productesColocats;
    }

    public boolean isCambiosManualesRealizados() {
        return usar_cambios_fijos;
    }


//SI ESTA ORDENADA YA SERIA EL GRAFO Y NOS SERVIRIA PARA IMPRIMIR LA DISTRO, sino solo devuelve los productos desordeandos

    public void calcula_distribucio(){

    }

    //para canviar el algoritmo que se usa le enviamos desde el controlador de prestatgeria una clase de tipo
        //- para calcular backtracking del tipo new CalculBackTracking();, del TSP enviamos new EstrategiaTSP(); y si es para el test de junit de integracion del sistema
        //usamos la estrategia one to one que coloca el producto en la posicion en la que viene (no funciona solo para test) new EstrategiaOneToOne()
    public void canvia_estrategia_calculo(EstrategiaCalculo estrategia){
        this.estrategiaCalculo = estrategia;
    }
}






