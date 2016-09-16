/*
 * Copyright (C) 2016 SamuelCoral
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jsnake;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 *
 * @author SamuelCoral
 */
public class AreaJuego extends JPanel implements KeyListener {
    
    // Constantes
    
    // Direcciones de las viboritas
    public static final int DERECHA = 0;
    public static final int ABAJO = 1;
    public static final int IZQUIERDA = 2;
    public static final int ARRIBA = 3;
    
    // Variables
    public final int numJugadores;
    private final FrmJuego juegoOrigen;
    private final java.util.Random rnd;
    private byte[] direcciones;
    private final boolean[] dirActualizada;
    private final Color[][] colores;
    private final int[][] teclas;
    private final java.util.List<java.util.LinkedList<Point>> viboritas;
    private final boolean[] yaPerdio;
    private final int[] puntos;
    private final Point[] posComiditas;
    private final Dimension tamCuadros;
    private final Dimension tamCampo;
    public boolean reproducirSoniditos;
    private Color colorFondo;
    private Color[] colorComidita;
    private boolean colorFotograma;
    private java.util.Timer temporizador;
    private AudioInputStream sonidoComidita;
    private AudioInputStream sonidoPerder;
    private JLabel lblPausa;
    public boolean juegoPausado;
    
    /**
     * 
     * Crea un panel en el que se desarrolla una partida del Snake e incializa el temporizador
     * y los elemntos del juego de acuerdo a los parámetros especificados.
     * @param juegoOrigen Formulario donde se agregará el juego, debe contener las etiquetas de las puntuaciones.
     * @param tamCuadros Tamaño de los cuadros (en pixeles).
     * @param tamCampo Tamaño del campo de juego (en cuadros).
     * @param numJugadores Número de jugadores que habrá en la partida (de 1 a 4).
     * @param teclas Teclas direccionales de cada jugador.
     * @param colores Colores de las viboritas.
     * @param numComiditas Número de comiditas simultaneas en el campo de juego.
     * @param longitudInicial Longitud inicial de las viboritas.
     * @param colorFondo Color de fondo del campo de juego.
     * @param colorComidita Color de las comiditas.
     * @param reproducirSoniditos Indica si se desean reproducir los sonidos del juego.
     * @param dificultad ms a esperar entre cada paso del juego.
     */
    public AreaJuego(
        FrmJuego juegoOrigen,
        Dimension tamCuadros,
        Dimension tamCampo,
        int numJugadores,
        int[][] teclas,
        Color[] colores,
        int numComiditas,
        int longitudInicial,
        Color colorFondo,
        Color colorComidita,
        boolean reproducirSoniditos,
        int dificultad
    ) {
        
        // Validacion de parámetros
        if(tamCuadros.width < 1 || tamCuadros.height < 1)
            throw new IllegalArgumentException("Los cuadros deben de ser mínimo de un pixel en cada dimensión.");
        if(tamCampo.width < 3 || tamCampo.height < 3)
            throw new IllegalArgumentException("El campo es demasiado pequeño.");
        if(numJugadores < 1 || numJugadores > 4)
            throw new IllegalArgumentException("El número de jugadores debe estar entre en un rango de 1 a 4.");
        if(numComiditas < 1)
            throw new IllegalArgumentException("Debe de haber por lo menos una comidita en el campo.");
        if(longitudInicial < 1)
            throw new IllegalArgumentException("Las viboritas deben de medir al menos un cuadro al empezar el juego.");
        if(dificultad < 1)
            throw new IllegalArgumentException("El tiempo de frecuencia entre pasos debe ser de al menos 1 ms.");
        
        if(longitudInicial >= tamCampo.width - 1 || (longitudInicial >= tamCampo.height - 1 && numJugadores > 2))
            throw new IllegalArgumentException("El campo es demasiado chico para que las viboritas quepan.");
        if(numComiditas > tamCampo.width * tamCampo.height - longitudInicial * numJugadores)
            throw new IllegalArgumentException("Hay demasiadas comiditas en el campo.");
        if(teclas.length < numJugadores)
            throw new IllegalArgumentException("No suficientes configuraciones de teclas para cada jugador.");
        if(colores.length < numJugadores)
            throw new IllegalArgumentException("No hay suficientes colores para todas las viboritas.");
        
        int c;
        for(c = 0; c < numJugadores; c++) if(teclas[c].length != 4)
            throw new IllegalArgumentException("La configuración de teclas número " + String.valueOf(c + 1) + " no especifica 4 teclas.");
        
        // Asignación de variables
        this.numJugadores = numJugadores;
        this.tamCuadros = tamCuadros;
        this.tamCampo = tamCampo;
        this.colorFondo = colorFondo;
        this.reproducirSoniditos = reproducirSoniditos;
        this.colorComidita = new Color[2];
        this.colorComidita[0] = colorComidita;
        this.colorComidita[1] = cambiarBrillo(colorComidita, colorContraste(colorComidita) == Color.WHITE ? 64 : -64);
        
        // Creación de la pila de coodenadas, el arreglo de direcciones, colores, configuraciones de teclas de las viboritas y banderas de ya perdió y direcciones actualizadas
        viboritas = new java.util.ArrayList<>();
        yaPerdio = new boolean[numJugadores];
        direcciones = new byte[numJugadores];
        dirActualizada = new boolean[numJugadores];
        puntos = new int[numJugadores];
        this.teclas = new int[numJugadores][];
        this.colores = new Color[numJugadores][];
        for(c = 0; c < numJugadores; c++) {
            
            viboritas.add(new java.util.LinkedList<Point>());
            this.teclas[c] = teclas[c].clone();
            yaPerdio[c] = false;
            dirActualizada[c] = true;
            puntos[c] = 0;
            juegoOrigen.puntuaciones[c].setText("Jugador " + String.valueOf(c + 1) + " - 0 comiditas");
            juegoOrigen.puntuaciones[c].setVisible(true);
            this.colores[c] = new Color[2];
            this.colores[c][0] = colores[c];
            this.colores[c][1] = colorContraste(colores[c]);
        }
        
        // Creación de las coordenadas y direcciones de inicio para cada viborita
        for(c = 0; c < longitudInicial; c++) viboritas.get(0).add(new Point(c, 0));
        direcciones[0] = DERECHA;
        if(numJugadores > 1) {
            for(c = 0; c < longitudInicial; c++) viboritas.get(1).add(new Point(tamCampo.width - 1 - c, tamCampo.height - 1));
            direcciones[1] = IZQUIERDA;
        }
        if(numJugadores > 2) {
            for(c = 0; c < longitudInicial; c++) viboritas.get(2).add(new Point(tamCampo.width - 1, c));
            direcciones[2] = ABAJO;
        }
        if(numJugadores > 3) {
            for(c = 0; c < longitudInicial; c++) viboritas.get(3).add(new Point(0, tamCampo.height - 1 - c));
            direcciones[3] = ARRIBA;
        }
        
        // Crear el generador de numeros aleatorios y damos como semilla la hora (para que sean más aleatorios :v)
        rnd = new java.util.Random(new java.util.Date().getTime());
        
        // Acomodar las comiditas en el campo
        posComiditas = new Point[numComiditas];
        for(c = 0; c < numComiditas; c++) acomodarComidita(c);
        
        // Temporizador, soniditos y etiqueta de pausa
        colorFotograma = false;
        juegoPausado = false;
        lblPausa = new JLabel("Pausado");
        lblPausa.setVisible(false);
        lblPausa.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblPausa.setForeground(colorContraste(colorFondo));
        lblPausa.setBackground(new Color(0, 0, 0, 0));
        
        try {
            
            sonidoComidita = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/recursos/bien.wav")));
            sonidoPerder = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/recursos/mal.wav")));
            sonidoComidita.mark(sonidoComidita.available());
            sonidoPerder.mark(sonidoPerder.available());
        }
        catch (UnsupportedAudioFileException | NullPointerException | IOException ex) {
            JOptionPane.showMessageDialog(juegoOrigen, ex.getMessage() + "\nNo se pudo cargar el sonidito de las puntuaciones.", "Error al inciar", JOptionPane.ERROR_MESSAGE);
        }
        temporizador = new java.util.Timer();
        temporizador.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                avanzarUnPaso();
            }
        }, dificultad, dificultad);
        
        // Ajustar el tamaño del Canvas, el KeyListener, el Form de origen y la etiqueta de pausa
        ajustarPanel();
        this.juegoOrigen = juegoOrigen;
    }
    
    /**
     * 
     * Devuelve un color blanco o negro para un color dado con la intención de crear un contraste entre ambos colores.
     * @param color Color a contrastar.
     * @return Blanco o negro que contraste con el color dado.
     */
    public static Color colorContraste(Color color) {
        
        double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
        return y >= 128 ? Color.BLACK : Color.WHITE;
    }
    
    /**
     * 
     * Cambia el brillo de un color especificando su incremento o decremento que
     * se aplicará a cada componente del color.
     * @param color Color a alterar.
     * @param incremento Incremento o decremento entre -255 y 255.
     * @return El mismo color con un brillo distinto.
     */
    public static Color cambiarBrillo(Color color, int incremento) {
        
        int nuevoRojo = color.getRed() + incremento;
        int nuevoVerde = color.getGreen() + incremento;
        int nuevoAzul = color.getBlue() + incremento;
        return new Color(
            nuevoRojo < 0 ? 0 : nuevoRojo > 255 ? 255 : nuevoRojo,
            nuevoVerde < 0 ? 0 : nuevoVerde > 255 ? 255 : nuevoVerde,
            nuevoAzul < 0 ? 0 : nuevoAzul > 255 ? 255 : nuevoAzul
        );
    }
    
    private void ajustarPanel() {
        
        setPreferredSize(new Dimension(tamCuadros.width * tamCampo.width, tamCuadros.height * tamCampo.height));
        setBackground(new Color(0, 0, 0, 0));
        addKeyListener(this);
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                juegoPausado = true;
                lblPausa.setVisible(true);
            }
        });
        setLayout(new GridBagLayout());
        add(lblPausa);
    }
    
    private void acomodarComidita(int comidita) {
        
        Point nuevoPunto;
        boolean repetir;
        do {
            
            repetir = false;
            nuevoPunto = new Point(rnd.nextInt(tamCampo.width), rnd.nextInt(tamCampo.height));
            for(Point puntoComidita : posComiditas) if(nuevoPunto.equals(puntoComidita)) repetir = true;
            for(java.util.LinkedList<Point> viborita : viboritas) for(Point puntoViborita : viborita) if(nuevoPunto.equals(puntoViborita)) repetir = true;
            
        } while(repetir);
        posComiditas[comidita] = nuevoPunto;
    }
    
    /**
     * 
     * Obtiene el color actual de las comiditas del campo de juego.
     * @return Color principal de las comiditas.
     */
    public Color obtenerColorComiditas() {
        return colorComidita[0];
    }
    
    /**
     * 
     * Cambia el color actual de las comiditas del campo de juego.
     * @param color Nuevo color principal de las comiditas.
     */
    public void ajustarColorComiditas(Color color) {
        
        colorComidita[0] = color;
        colorComidita[1] = cambiarBrillo(color, colorContraste(color) == Color.WHITE ? 64 : -64);
    }
    
    /**
     * 
     * Obtiene el color del fondo del campo de juego.
     * @return Color del fondo del campo de juego.
     */
    public Color obtenerColorFondo() {
        return colorFondo;
    }
    
    /**
     * 
     * Cambia el color del fondo del campo de juego.
     * @param color Nuevo color del campo de juego.
     */
    public void ajustarColorFondo(Color color) {
        
        colorFondo = color;
        lblPausa.setForeground(colorContraste(color));
    }
    
    /**
     * 
     * Obtiene el color de la viborita del juego indicada.
     * @param personaje Viborita a conocer color.
     * @return Color de la viborita indicada.
     */
    public Color obtenerColorViborita(int personaje) {
        
        if(personaje < 1 || personaje > viboritas.size())
            throw new IllegalArgumentException("El número de jugadores de este juego es de solo " + String.valueOf(viboritas.size()) + ".");
        return colores[personaje - 1][0];
    }
    
    /**
     * 
     * Ajusta el color de la viborita del juego indicada.
     * @param personaje Viborita a ajustar color.
     * @param color Nuevo color de la viborita.
     */
    public void ajustarColorViborita(int personaje, Color color) {
        
        if(personaje < 1 || personaje > viboritas.size())
            throw new IllegalArgumentException("El número de jugadores de este juego es de solo " + String.valueOf(viboritas.size()) + ".");
        colores[personaje - 1][0] = color;
        colores[personaje - 1][1] = colorContraste(color);
    }
    
    /**
     * 
     * Reproduce un sonido dado una sola vez.
     * @param sonidito Sonido a reproducir.
     */
    public static void reproducirSonidito(final AudioInputStream sonidito) {
        synchronized(sonidito) {
        
            new Thread() {
                @Override
                public void run() {
                    try {
                        Clip clip = AudioSystem.getClip();
                        clip.open(sonidito);
                        clip.start();
                        sonidito.reset();
                    } catch (LineUnavailableException | IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }.start();
        }
    }
    
    /**
     * 
     * Avanza un fotograma o un paso en el juego, Esto incluye:
     * <ul>
     * <li>Avanzar todos los jugadores un paso a la dirección a la que apunta.</li>
     * <li>Verificar si las viboritas chocan entre ellas, con sigo mismas o contra algún muro.</li>
     * <li>Verificar si alguna viborita recogió una comidita, si lo hizo aumentar su longitud,
     *     incrementar su puntuación y reacomodar la comidita.</li>
     * </ul>
     * 
     * Cuando todos los jugadores pierdan el juego se terminará, mostrará el jugador ganador
     * con su puntuación y mandará al formulario padre a destruir el juego.
     */
    public void avanzarUnPaso() {
        
        // Cambia el estado de parpadeo de los elementos decorativos del juego
        // como el color de las comiditas y el parpadeo del texto de pausa.
        colorFotograma = !colorFotograma;
        // Si el juego está pausado solamente hacer parpadear la etiqueta de pausa
        if(juegoPausado) {
            
            lblPausa.setVisible(colorFotograma);
            repaint();
            return;
        }
        
        Point posActual, nuevaPos = new Point();
        boolean juegoTerminado = true;
        for(int c = 0; c < viboritas.size(); c++) {
            
            if(yaPerdio[c]) continue;
            // Obtener la posición del frente de la viborita y decidir su siguiente posición
            // de acuerdo a la dirección en la que apunta.
            posActual = viboritas.get(c).getLast();
            switch(direcciones[c]) {
                
                case DERECHA: nuevaPos = new Point(posActual.x + 1, posActual.y); break;
                case ABAJO: nuevaPos = new Point(posActual.x, posActual.y + 1); break;
                case IZQUIERDA: nuevaPos = new Point(posActual.x - 1, posActual.y); break;
                case ARRIBA: nuevaPos = new Point(posActual.x, posActual.y - 1); break;
            }
            
            // Verificamos que la viborita no haya chocado con otras viboritas o con ella misma
            for(int x = 0; x < viboritas.size(); x++) {
                
                if(yaPerdio[x]) continue;
                for(int y = 0; y < viboritas.get(x).size(); y++) if(nuevaPos.equals(viboritas.get(x).get(y))) {
                        
                    if(y == viboritas.get(x).size() - 1) {
                        
                        yaPerdio[x] = true;
                        juegoOrigen.puntuaciones[x].setText(juegoOrigen.puntuaciones[x].getText() + " (ya perdió)");
                    }
                    
                    yaPerdio[c] = true;
                    juegoOrigen.puntuaciones[c].setText(juegoOrigen.puntuaciones[c].getText() + " (ya perdió)");
                    if(reproducirSoniditos) reproducirSonidito(sonidoPerder);
                    break;
                }
                
                if(yaPerdio[c]) break;
            }
            if(yaPerdio[c]) continue;
            
            // Verificamos que la viborita no haya chocado contra los bordes
            if(nuevaPos.x < 0 || nuevaPos.y < 0 || nuevaPos.x >= tamCampo.width || nuevaPos.y >= tamCampo.height) {
                
                yaPerdio[c] = true;
                juegoOrigen.puntuaciones[c].setText(juegoOrigen.puntuaciones[c].getText() + " (ya perdió)");
                viboritas.get(c).clear();
                if(reproducirSoniditos) reproducirSonidito(sonidoPerder);
                continue;
            }
            
            // Verificamos si la viborita recogió una comidita
            boolean crecerViborita = false;
            for(int x = 0; x < posComiditas.length; x++) {
                
                if(nuevaPos.x == posComiditas[x].x && nuevaPos.y == posComiditas[x].y) {
                    
                    puntos[c]++;
                    juegoOrigen.puntuaciones[c].setText("Jugador " + String.valueOf(c + 1) + " - " + String.valueOf(puntos[c]) + (puntos[c] > 1 ? " comiditas" : " comidita"));
                    acomodarComidita(x);
                    crecerViborita = true;
                    if(reproducirSoniditos) reproducirSonidito(sonidoComidita);
                }
            }
            
            dirActualizada[c] = true;
            viboritas.get(c).add(nuevaPos);
            if(!crecerViborita) viboritas.get(c).poll();
            juegoTerminado = false;
        }
        
        if(juegoTerminado) {
            
            temporizador.cancel();
            int ganador = 0;
            for(int c = 0; c < puntos.length; c++) if(puntos[c] > puntos[ganador]) ganador = c;
            javax.swing.JOptionPane.showMessageDialog(juegoOrigen,
                "Han perdido todos los jugadores.\n"
                + "Ha ganado el jugador " + String.valueOf(ganador + 1) + "\n"
                + "con una puntuación de " + String.valueOf(puntos[ganador]) + (puntos[ganador] == 1 ? " comidita." : " comiditas."),
                "Juego terminado.", javax.swing.JOptionPane.INFORMATION_MESSAGE
            );
            juegoOrigen.terminarJuego(false);
            return;
        }
        
        repaint();
    }
    
    public void terminarJuego() {
        
        temporizador.cancel();
    }
    
    /**
     * 
     * Manda a dibujar todos los elementos del juego en el siguiente orden:
     * <ul>
     * <li>Rellena el campo de juego con el color de fondo establecido.</li>
     * <li>Dibuja todas las viboritas con un contorno que contraste con el color de su cuerpo.</li>
     * <li>Dibuja sus ojos de acuerdo a la dirección en la que se dirigen con un color
     *     que contraste con el de su cuerpo.</li>
     * <li>Dibuja todas las comiditas.</li>
     * <li>Dibuja la etiqueta de pausa cuando sea el caso.</li>
     * </ul>
     * 
     * @param g Graficos a usar para dibujar.
     */
    @Override
    public void paint(Graphics g) {
        
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(colorFondo);
        g2d.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);
        
        Point posActual;
        for(int c = 0; c < viboritas.size(); c++) {
            
            if(yaPerdio[c]) continue;
            //for(Point puntoViborita : viboritas[c]) g2d.fillRect(puntoViborita.x * tamCuadros.width, puntoViborita.y * tamCuadros.height, tamCuadros.width, tamCuadros.height);
            for(int x = 0; x < viboritas.get(c).size(); x++) {
                
                Rectangle cuadroDibujar = new Rectangle(viboritas.get(c).get(x).x * tamCuadros.width, viboritas.get(c).get(x).y * tamCuadros.height, tamCuadros.width, tamCuadros.height);
                g2d.setColor(colores[c][0]);
                g2d.fill(cuadroDibujar);
                g2d.setColor(colores[c][1]);
                g2d.draw(cuadroDibujar);
            }
            
            posActual = viboritas.get(c).getLast();
            g2d.setColor(colores[c][1]);
            if(direcciones[c] == IZQUIERDA || direcciones[c] == ARRIBA)
                g2d.fillRect((int)((posActual.x + 1 / 5d ) * tamCuadros.width), (int)((posActual.y + 1 / 5d) * tamCuadros.height), tamCuadros.width / 5, tamCuadros.height / 5);
            if(direcciones[c] == DERECHA || direcciones[c] == ARRIBA)
                g2d.fillRect((int)((posActual.x + 3 / 5d ) * tamCuadros.width), (int)((posActual.y + 1 / 5d) * tamCuadros.height), tamCuadros.width / 5, tamCuadros.height / 5);
            if(direcciones[c] == IZQUIERDA || direcciones[c] == ABAJO)
                g2d.fillRect((int)((posActual.x + 1 / 5d ) * tamCuadros.width), (int)((posActual.y + 3 / 5d) * tamCuadros.height), tamCuadros.width / 5, tamCuadros.height / 5);
            if(direcciones[c] == DERECHA || direcciones[c] == ABAJO)
                g2d.fillRect((int)((posActual.x + 3 / 5d ) * tamCuadros.width), (int)((posActual.y + 3 / 5d) * tamCuadros.height), tamCuadros.width / 5, tamCuadros.height / 5);
        }
        for(Point puntoComidita : posComiditas) {
            
            g2d.setColor(colorComidita[colorFotograma && !juegoPausado ? 1 : 0]);
            g2d.fillRect(puntoComidita.x * tamCuadros.width, puntoComidita.y * tamCuadros.height, tamCuadros.width, tamCuadros.height);
        }
        
        if(juegoPausado) super.paint(g);
    }
    
    /**
     * 
     * Evento que se dispara cuando se presiona una tecla en el juego.
     * @param e Argumentos del evento.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        
        int teclaPulsada = e.getKeyCode();
        // Pausa o despausa el juego con la barra espaciadora
        if(teclaPulsada == KeyEvent.VK_SPACE) { juegoPausado = !juegoPausado; lblPausa.setVisible(juegoPausado); return; }
        // Termina el juego inmediatamente con la tecla escape
        if(teclaPulsada == KeyEvent.VK_ESCAPE) { juegoOrigen.terminarJuego(true); return; }
        if(juegoPausado) return;
        
        // Cambia la dirección de las viboritas a una direción válida
        // y solo si no se ha cambiado ya antes de dibujar el siguiente fotograma
        int c;
        for(c = 0; c < viboritas.size(); c++) {
            
            if(yaPerdio[c] || !dirActualizada[c]) continue;
            if(teclaPulsada == teclas[c][DERECHA] && direcciones[c] != IZQUIERDA && direcciones[c] != DERECHA) { direcciones[c] = DERECHA; dirActualizada[c] = false; }
            if(teclaPulsada == teclas[c][IZQUIERDA] && direcciones[c] != DERECHA && direcciones[c] != IZQUIERDA) { direcciones[c] = IZQUIERDA; dirActualizada[c] = false; }
            if(teclaPulsada == teclas[c][ABAJO] && direcciones[c] != ARRIBA && direcciones[c] != ABAJO) { direcciones[c] = ABAJO; dirActualizada[c] = false; }
            if(teclaPulsada == teclas[c][ARRIBA] && direcciones[c] != ABAJO && direcciones[c] != ARRIBA) { direcciones[c] = ARRIBA; dirActualizada[c] = false; }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) { }
    @Override
    public void keyReleased(KeyEvent e) { }
}
