import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class TableroFrame extends JFrame {
    private int filas = 8;
    private int columnas = 8;
    private Casilla[][] tablero;

    public TableroFrame(Casilla[][] tablero) {
        this.tablero = tablero;
        this.filas = tablero.length;
        this.columnas = tablero[0].length;
    }

    public void TableroInicial(){
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setTitle("Laberinto");

                JPanel panel = new JPanel();
                panel.setLayout(new java.awt.GridLayout(filas, columnas));

                Color colorMuro = Color.BLACK;

                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        Color color = Color.WHITE;
                        if (tablero[i][j].getEstado().equals("MURO")) {
                            color = colorMuro;
                        }
                       
                    }
                }
    }

    public void initComponents(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Laberinto");

        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.GridLayout(filas, columnas));

        Color colorMuro = Color.BLACK;
        Color colorVisitado = Color.GRAY;
        Color colorCamino = Color.GREEN;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Color color = Color.WHITE;
                if (tablero[i][j] == null || tablero[i][j].getEstado().equals("MURO")) {
                    color = colorMuro;
                }else{
                    switch (tablero[i][j].getEstado()) {
                        case "VISITADO":
                            color = colorVisitado;
                            break;
                        case "CAMINO":
                            color = colorCamino;
                            break;
                        case "INICIO":
                            color = Color.BLUE;
                            break;
                        case "MURO":
                            color = colorMuro;
                            break;
                        default:
                            break;
                    }
                    if (tablero[i][j].esMeta()) {
                        color = Color.RED;
                    }
                }
                
            }
        }
    }

}
