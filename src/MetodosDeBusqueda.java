import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;

public class MetodosDeBusqueda extends Thread{
    private Tablero tablero;
    private Stack<Casilla> F;
    private Casilla inicio;
    private String nombreCasilla;
    private boolean encontrado = false;
    private Scanner sc = new Scanner(System.in);
    private Stack<Casilla> OS = new Stack<Casilla>();
    private Casilla EA;
    private boolean solucion = false;

public MetodosDeBusqueda(){

}

public void insertarDatos(Tablero tablero, Stack<Casilla> F, Casilla inicio, String nombreCasilla){
    this.tablero = tablero;
    this.F = F;
    this.inicio = inicio;
    this.nombreCasilla = nombreCasilla;
}

@Override
public void run(){
    Stack<Casilla> Fila = new Stack<Casilla>();
    Fila.add(this.inicio);
    Estrella(Fila,this.inicio,this.nombreCasilla);
    if (this.encontrado){
        System.out.println("Gano el raton: " + nombreCasilla);
        imprimeTableroEstrella();
    }else{
        System.out.println("Perdio el raton: " + nombreCasilla);
    }
}

public void Estrella(Stack<Casilla> F, Casilla inicio, String nombre){
    if (this.tablero.getQueso().getEstado().equals(Estado.VISITADO.toString())){
        return;
    }
    else if(F.isEmpty()){
        return;
    }else{
        this.EA = F.pop();
        if(GoalTest(EA)){
            this.encontrado = true;
            return;
    }else{
        this.OS = Expand(EA);
        this.OS = EvaluateHeuristic(OS, inicio);
        F = insert (F, OS);
        F = sort(F);
        Estrella(F, inicio, nombre);
    }
}
}

public void imprimeTableroEstrella(){
    TableroFrame tableroFrame = new TableroFrame(this.tablero.getTablero());
    if (encontrado){
        this.retroceder(this.tablero.getQueso());
    }
    tableroFrame.initComponents();
    tableroFrame.setVisible(true);
    Scanner sc = new Scanner(System.in);
    sc.nextLine();
    tableroFrame.setVisible(false);
}

private Stack<Casilla> EvaluateHeuristic(Stack<Casilla> OS, Casilla inicio){
    for (int i = 0; i < OS.size(); i++){
        Casilla casillaEvaluada = OS.get(i);
        casillaEvaluada.setDistanciaFinal((getDistanciaLineal(inicio, casillaEvaluada) + getDistanciaRecorrida(casillaEvaluada, this.tablero.getQueso())));
        OS.set(i, casillaEvaluada);
    }
    return OS;
}

private Stack<Casilla> sort(Stack<Casilla> OS) {

    ArrayList<Casilla> Ordenar = new ArrayList<Casilla> ();
    while(!OS.isEmpty()){
        Ordenar.add(OS.pop());
    }

    // Ordenar el ArrayList utilizando un Comparator
    Collections.sort(Ordenar, new Comparator<Casilla>() {
        @Override
        public int compare(Casilla casilla2, Casilla casilla1) {
            // Comparar las distancias de menor a mayor
            return Double.compare(casilla1.getDistanciaFinal(), casilla2.getDistanciaFinal());
        }
    });

    for(Casilla casilla : Ordenar){
        if(casilla.getEstado().equals(Estado.NOVISITADO.toString())){
            OS.push(casilla);
        }
        
    }
 
    return OS;
}

//aqui va mi metodo original
        
private Stack<Casilla> insert(Stack<Casilla> F, Stack<Casilla> OS){
    while (!OS.isEmpty()){
        Casilla actual = OS.pop();
        F.add(0, actual);
    }
    return F;
}

private Stack<Casilla> Expand(Casilla EA){
    Stack<Casilla> OS = new Stack<Casilla>();
    for (int i = -1; i<= 1; i+= 2){
        try{
            OS.add(this.tablero.getCasilla(EA.getX() + i, EA.getY()));
        }catch(ArrayIndexOutOfBoundsException e){
        }
    }
    for (int i = -1; i<= 1; i+= 2){
        try{
            OS.add(this.tablero.getCasilla(EA.getX(), EA.getY() + i));
        }catch(ArrayIndexOutOfBoundsException e){
        }
    }
    OS.removeAll(Collections.singleton(null));

    for (int i = 0; i < OS.size(); i++){
        if(OS.get(i).getEstado().equals(Estado.NOVISITADO.toString())){
            OS.get(i).setPadre(EA);
        }
    }
    return OS;

}

private boolean GoalTest(Casilla EA){
    EA.setEstado(Estado.VISITADO);
    EA.setDistanciaFinal((int)0);
    return this.tablero.esMeta(EA);
}

private double getDistanciaLineal(Casilla casillaActual, Casilla casillaFinal){
    return Math.sqrt(Math.pow(casillaActual.getX() - casillaFinal.getX(), 2) + Math.pow(casillaActual.getY() - casillaFinal.getY(), 2));
}

private double getDistanciaRecorrida(Casilla casillaActual, Casilla casillaFinal){
    return Math.abs(casillaActual.getX() - casillaFinal.getX()) + Math.abs(casillaActual.getY() - casillaFinal.getY());
}

public void retroceder(Casilla actual){
    if(actual.getPadre() == null){
        System.out.println("No hay camino");
        return;
    }else{
        actual.setEstado(Estado.CAMINO);
        actual = actual.getPadre();
        if(this.tablero.esRaton1(actual) || this.tablero.esRaton2(actual)){
            actual.setEstado(Estado.INICIO);
            System.out.println("Se encontro el camino");
            return;
        }else{
            retroceder(actual);
        }

    }
}

public void setSolucion (boolean solucion){
    this.solucion = solucion;
}

public boolean fueEncontrado(){
    return this.encontrado;
}

}
