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
import javax.swing.*;

/**
 * 
 * Clase de un panel que contiene todos los elementos del juego.
 * @author SamuelCoral
 */
public class FrmJuego extends JFrame {
    
    /**
     * 
     * Crea una instancia de este formulario.
     */
    public FrmJuego() {
        inicializarForm();
    }
    
    // Páneles y componentes útiles para el juego
    public AreaJuego juegoActual;
    public PnlNuevoJuego pnlNuevoJuego;
    public PnlPuntuaciones pnlPuntuaciones;
    public JLabel[] puntuaciones;
    
    // Controles
    private JMenuBar barraMenu;
    private JMenu mnuArchivo;
    public JMenuItem mnuNuevoJuego;
    private JMenuItem mnuSalir;
    private JMenu mnuPartida;
    private JMenu mnuColores;
    private JMenuItem mnuColorFondo;
    private JMenuItem mnuColorComiditas;
    private JMenu mnuColoresViboritas;
    public JMenuItem[] mnuColorViboritas;
    private JCheckBoxMenuItem mnuSonidos;
    private JMenu mnuAyuda;
    private JMenuItem mnuControles;
    private JMenuItem mnuAcercaDe;
    
    private void inicializarForm() {
        
        // Creación de la barra de menú
        barraMenu = new JMenuBar();
        
        mnuArchivo = new JMenu("Juego");
        mnuNuevoJuego = new JMenuItem("Nuevo juego");
        mnuSalir = new JMenuItem("Salir");
        mnuNuevoJuego.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        mnuNuevoJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(
                        rootPane,
                        "¿Seguro que desea terminar la partida en curso?",
                        "Terminar partida",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE
                    ) == JOptionPane.OK_OPTION
                ) terminarJuego();
            }
        });
        mnuSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        mnuSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnuArchivo.add(mnuNuevoJuego);
        mnuArchivo.add(mnuSalir);
        
        mnuPartida = new JMenu("Partida");
        mnuColores = new JMenu("Colores del juego");
        mnuColorFondo = new JMenuItem("Fondo");
        mnuColorComiditas = new JMenuItem("Comiditas");
        mnuColorFondo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color nuevoColor = pnlNuevoJuego.cambiarColorFondo();
                if(nuevoColor != null) juegoActual.ajustarColorFondo(nuevoColor);
            }
        });
        mnuColorComiditas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color nuevoColor = pnlNuevoJuego.cambiarColorFondo();
                if(nuevoColor != null) juegoActual.ajustarColorComiditas(nuevoColor);
            }
        });
        mnuColores.add(mnuColorFondo);
        mnuColores.add(mnuColorComiditas);
        mnuColoresViboritas = new JMenu("Colores de las viboritas");
        mnuColorViboritas = new JMenuItem[4];
        for(int c = 0; c < mnuColorViboritas.length; c++) {
            
            mnuColorViboritas[c] = new JMenuItem("Jugador " + String.valueOf(c + 1));
            mnuColorViboritas[c].setName(String.valueOf(c + 1));
            mnuColorViboritas[c].setVisible(false);
            mnuColorViboritas[c].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int viborita = Integer.parseInt(((Component)e.getSource()).getName());
                    Color nuevoColor = pnlNuevoJuego.cambiarColorViborita(viborita - 1);
                    if(nuevoColor != null) juegoActual.ajustarColorViborita(viborita, nuevoColor);
                }
            });
            mnuColoresViboritas.add(mnuColorViboritas[c]);
        }
        mnuSonidos = new JCheckBoxMenuItem("Sonidos");
        mnuSonidos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        mnuSonidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                juegoActual.reproducirSoniditos = mnuSonidos.isSelected();
                pnlNuevoJuego.chkSonidos.setSelected(mnuSonidos.isSelected());
            }
        });
        mnuPartida.add(mnuColores);
        mnuPartida.add(mnuColoresViboritas);
        mnuPartida.add(mnuSonidos);
        
        mnuAyuda = new JMenu("Ayuda");
        mnuControles = new JMenuItem("Controles");
        mnuControles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAyudaControles();
            }
        });
        mnuAcercaDe = new JMenuItem("Acerca de...");
        mnuAcercaDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                javax.swing.JOptionPane.showMessageDialog(
                    rootPane,
                    "JSnake v1.0\n"
                    + "Juego tradicional del Snake personalizable.\n"
                    + "Por SamuelCoral.",
                    "Acerca de Juego del Snake",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
                );
            }
        });
        mnuAyuda.add(mnuControles);
        mnuAyuda.add(mnuAcercaDe);
        
        barraMenu.add(mnuArchivo);
        barraMenu.add(mnuPartida);
        barraMenu.add(mnuAyuda);
        setJMenuBar(barraMenu);
        
        // Agregar páneles y elementos del juego del juego
        pnlNuevoJuego = new PnlNuevoJuego(this);
        pnlPuntuaciones = new PnlPuntuaciones();
        add(pnlNuevoJuego, BorderLayout.CENTER);
        add(pnlPuntuaciones, BorderLayout.EAST);
        mnuNuevoJuego.setVisible(false);
        mnuPartida.setVisible(false);
        pnlPuntuaciones.setVisible(false);
        puntuaciones = new javax.swing.JLabel[] {
            pnlPuntuaciones.lblPuntuacion1, pnlPuntuaciones.lblPuntuacion2, pnlPuntuaciones.lblPuntuacion3, pnlPuntuaciones.lblPuntuacion4
        };
        for(JLabel puntuacion : puntuaciones) puntuacion.setVisible(false);
        
        // Estática y funcionamiento del formulario
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("JSnake v1.0");
        setResizable(false);
        pnlNuevoJuego.aplicarTemaPorDefecto();
        centrarForm(this);
    }
    
    /**
     * 
     * Centra el formulario dado en la pantalla.
     * @param form Formulario a centrar.
     */
    public static void centrarForm(JFrame form) {
        
        Dimension areaPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        form.setLocation((areaPantalla.width - form.getSize().width) / 2, (areaPantalla.height - form.getSize().height) / 2);
    }
    
    private void mostrarAyudaControles() {
        
        setVisible(false);
        new FrmInfoControles(this).setVisible(true);
    }
    
    /**
     * 
     * Realiza las siguientes operaciones para empezar el juego:
     * <ul>
     * <li>Oculta el panel de configuración de nuevo juego.</li>
     * <li>Muestra el panel de puntuaciones y el menú de reinciar juego.</li>
     * <li>Agrega el panel del juego y redimensiona el panel de puntuaciones
     *     para que sea del mismo alto que el panel del juego.</li>
     * <li>Redimensiona todo el formulario y lo centra en la pantalla.</li>
     * <li>Enfoca el juego para que reaccione a los eventos del teclado.</li>
     * </ul>
     */
    public void empezarJuego() {
        
        pnlNuevoJuego.setVisible(false);
        pnlPuntuaciones.setVisible(true);
        mnuNuevoJuego.setVisible(true);
        mnuPartida.setVisible(true);
        mnuSonidos.setSelected(pnlNuevoJuego.chkSonidos.isSelected());
        add(juegoActual, BorderLayout.WEST);
        pnlPuntuaciones.setPreferredSize(new Dimension(pnlPuntuaciones.getPreferredSize().width, juegoActual.getPreferredSize().height));
        pack();
        centrarForm(this);
        juegoActual.requestFocusInWindow();
    }
    
    /**
     * 
     * Realiza las siguientes operaciones para terminar el juego:
     * <ul>
     * <li>Esconde todas las etiquetas de puntuación de los jugadores y el panel de puntuaciones</li>
     * <li>Esconde el menú de reiniciar el juego.</li>
     * <li>Muestra el panel de configuración de nuevo juego.</li>
     * <li>Quita el panel del juego que acaba de terminar y lo destruye.</li>
     * <li>Redimensiona todo el formulario y lo centra en la pantalla.</li>
     * </ul>
     */
    public void terminarJuego() {
        
        juegoActual.temporizador.cancel();
        for(JLabel puntuacion : puntuaciones) puntuacion.setVisible(false);
        for(JMenuItem colores : mnuColorViboritas) colores.setVisible(false);
        pnlPuntuaciones.setVisible(false);
        pnlNuevoJuego.setVisible(true);
        mnuNuevoJuego.setVisible(false);
        mnuPartida.setVisible(false);
        remove(juegoActual);
        juegoActual = null;
        pack();
        centrarForm(this);
    }
    
    /**
     * 
     * Punto de entrada para el juego.
     * @param ar Argumentos del programa (no usados aún).
     */
    public static void main(String[] ar) {
        
        // Crea y muestra el form
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmJuego().setVisible(true);
            }
        });
    }
}
