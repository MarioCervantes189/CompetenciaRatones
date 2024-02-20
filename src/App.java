import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        Tablero tablero = tableroMatriz();

        if(tablero.isTresCasillasLibres()){
            tablero.setRaton1(0, 0);
            tablero.setRaton2(tablero.getFilas()-1, tablero.getColumnas()-1);
            tablero.setQueso((tablero.getFilas()-1)/2, (tablero.getColumnas()-1)/2);
        }else{
            System.out.println("No hay tres casillas libres");
            return;
        }

        TableroFrame frame = new TableroFrame(tablero.getTablero());
        frame.initComponents();

        System.out.println(tablero);
        frame.setVisible(false);

        iniciarBusqueda(tablero);

    }

    public static Tablero tableroMatriz(){
        int f = 0;
        int c = 0;
        int m = 0;

        while (true){
            try{
                System.out.println("Ingrese el numero de filas");
                f = Integer.parseInt(System.console().readLine());
                System.out.println("Ingrese el numero de columnas");
                c = Integer.parseInt(System.console().readLine());
                System.out.println("Ingrese el porcentaje de muros");
                m = Integer.parseInt(System.console().readLine());
                if (m< 1 || m > 100){
                    System.out.println("El porcentaje debe ser un numero entre 1 y 100");
            }else if((f*c) < 3){
                System.out.println("El tablero debe tener al menos 3 casillas");
        }else{
            break;
        }
    }catch(NumberFormatException e){
        System.out.println("Ingrese un numero valido");
    }
}
return new Tablero(f, c, m);
}

public static void iniciarBusqueda(Tablero tablero){
    Stack<Casilla> F = new Stack<Casilla>();
    Stack<Casilla> F2 = new Stack<Casilla>();

    TableroFrame frame = new TableroFrame(tablero.getTablero());
    frame.initComponents();

    F.push(tablero.getRaton1());
    F2.push(tablero.getRaton2());

    MetodosDeBusqueda busqueda1 = new MetodosDeBusqueda();
    MetodosDeBusqueda busqueda2 = new MetodosDeBusqueda();

    busqueda1.insertarDatos(tablero, F, tablero.getRaton1(), "Raton 1");
    busqueda2.insertarDatos(tablero, F2, tablero.getRaton2(), "Raton 2");

    busqueda1.start();
    busqueda2.start();

    F.clear();
    F2.clear();
}

}
