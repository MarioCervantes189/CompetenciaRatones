public class Tablero {
    private Casilla[][] tablero;
    private int filas;
    private int columnas;
    private int porcentajeMuros;
    private Casilla raton1;
    private Casilla raton2;
    private Casilla queso;
    

    public Tablero (int filas, int columnas, int porcentajeMuros) {
        this.filas = filas;
        this.columnas = columnas;
        this.tablero = new Casilla[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = new Casilla(i, j);
            }
        }
        generarMuros(porcentajeMuros);   
    }

    public void generarMuros(int porcentajeMuros) {
        int muros = (int) (filas * columnas * porcentajeMuros / 100);
        int murosGenerados = 0;
        while (murosGenerados < muros) {
            int x = (int) (Math.random() * filas);
            int y = (int) (Math.random() * columnas);
            if (tablero[x][y].getEstado().equals("NOVISITADO")) {
                tablero[x][y].setEstado(Estado.MURO);
                murosGenerados++;
            }
        }
    }

    public void setRaton1(int x, int y) {
        raton1 = tablero[x][y];
        raton1.setEstado(Estado.INICIO);
    }

    public void setRaton2(int x, int y) {
        raton2 = tablero[x][y];
        raton2.setEstado(Estado.INICIO);
    }

    public void setQueso(int x, int y) {
        queso = tablero[x][y];
        queso.setEsMeta(true);
    }

    public void resetTablero(){
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j].setEstado(Estado.NOVISITADO);
                tablero[i][j].setPadre(null);
                tablero[i][j].setDistanciaFinal(0);
            }
        }

        tablero[raton1.getX()][raton1.getY()].setEstado(Estado.INICIO);
        tablero[raton1.getX()][raton1.getY()].setDistanciaFinal(0);

        tablero[raton2.getX()][raton2.getY()].setEstado(Estado.INICIO);
        tablero[raton2.getX()][raton2.getY()].setDistanciaFinal(0);
    }

    public boolean esMeta(Casilla casilla) {
        return casilla.getX() == queso.getX() && casilla.getY() == queso.getY();
    }

    public boolean esRaton1(Casilla casilla) {
        return casilla.getX() == raton1.getX() && casilla.getY() == raton1.getY();
    }

    public boolean esRaton2(Casilla casilla) {
        return casilla.getX() == raton2.getX() && casilla.getY() == raton2.getY();
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Casilla getCasilla(int x, int y) {
        return tablero[x][y];
    }

    public Casilla getRaton1() {
        return raton1;
    }

    public Casilla getRaton2() {
        return raton2;
    }

    public Casilla getQueso() {
        return queso;
    }

    public Casilla[][] getTablero() {
        return tablero;
    }

    public double[][] getDistanciasFinalesDeMatriz(){
        double [][] distanciasFinales = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if(tablero[i][j].getEstado().equals("MURO")){
                    distanciasFinales[i][j] = -1;
            }else{
                distanciasFinales[i][j] = tablero[i][j].getDistanciaFinal();
                }
            }   
        }
        return distanciasFinales;
    }
}
