import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

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
                        String coordenada = j + "," + i;
                        if (tablero[i][j] == null){
                            color = colorMuro;
                            coordenada = "";
                        }

                        CasillaPanel cuadro = new CasillaPanel (coordenada);
                        cuadro.setBackground(color);
                        panel.add(cuadro);
                    }
                }
               add(panel);

               Font font = new Font("Arial", Font.BOLD, 20);
               setFont(font);

               pack();
                setLocationRelativeTo(null);
                setVisible(true);
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
                String coordenada = "";
                if (tablero[i][j] == null) {
                    color = colorMuro;
                    coordenada = "";
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
                        coordenada = i + "," + j;
                    }
                }

                CasillaPanel cuadro = new CasillaPanel(coordenada);
                cuadro.setBackground(color);
                panel.add(cuadro);
                
            }
        }
        add(panel);

        Font font = new Font("Arial", Font.BOLD, 20);
        setFont(font);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static class CasillaPanel extends JPanel {
        private String valor;

        public CasillaPanel(String valor) {
            this.valor = valor;
        }

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawString(String.valueOf(valor), getWidth() / 2, getHeight() / 2);
        }
    }

}
