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
package snakegame;

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
    private JMenuItem mnuNuevoJuego;
    private JMenuItem mnuSalir;
    private JMenu mnuAyuda;
    private JMenuItem mnuControles;
    private JMenuItem mnuAcercaDe;
    
    private void inicializarForm() {
        
        // Creación de la barra de menú
        barraMenu = new JMenuBar();
        
        mnuArchivo = new JMenu("Juego");
        mnuNuevoJuego = new JMenuItem("Nuevo juego");
        mnuSalir = new JMenuItem("Salir");
        mnuNuevoJuego.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                terminarJuego();
            }
        });
        mnuSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnuArchivo.add(mnuNuevoJuego);
        mnuArchivo.add(mnuSalir);
        
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
                mostrarAcercaDe();
            }
        });
        mnuAyuda.add(mnuControles);
        mnuAyuda.add(mnuAcercaDe);
        
        barraMenu.add(mnuArchivo);
        barraMenu.add(mnuAyuda);
        setJMenuBar(barraMenu);
        
        // Agregar páneles y elementos del juego del juego
        pnlNuevoJuego = new PnlNuevoJuego(this);
        pnlPuntuaciones = new PnlPuntuaciones();
        add(pnlNuevoJuego, BorderLayout.CENTER);
        add(pnlPuntuaciones, BorderLayout.EAST);
        mnuNuevoJuego.setVisible(false);
        pnlPuntuaciones.setVisible(false);
        puntuaciones = new javax.swing.JLabel[] {
            pnlPuntuaciones.lblPuntuacion1, pnlPuntuaciones.lblPuntuacion2, pnlPuntuaciones.lblPuntuacion3, pnlPuntuaciones.lblPuntuacion4
        };
        for(JLabel puntuacion : puntuaciones) puntuacion.setVisible(false);
        
        // Estática y funcionamiento del formulario
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("JSnake v1.0");
        setResizable(false);
        pack();
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
    
    private void mostrarAcercaDe() {
        javax.swing.JOptionPane.showMessageDialog(
            this,
            "JSnake v1.0\n"
            + "Juego tradicional del Snake personalizable.\n"
            + "Por SamuelCoral.",
            "Acerca de Juego del Snake",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    /**
     * 
     * Realiza las siguientes operaciones para empezar el juego:
     * - Oculta el panel de configuración de nuevo juego.
     * - Muestra el panel de puntuaciones y el menú de reinciar juego.
     * - Agrega el panel del juego y redimensiona el panel de puntuaciones
     *   para que sea del mismo alto que el panel del juego.
     * - Redimensiona todo el formulario y lo centra en la pantalla
     * - Enfoca el juego para que reaccione a los eventos del teclado.
     */
    public void empezarJuego() {
        
        pnlNuevoJuego.setVisible(false);
        pnlPuntuaciones.setVisible(true);
        mnuNuevoJuego.setVisible(true);
        add(juegoActual, BorderLayout.WEST);
        pnlPuntuaciones.setPreferredSize(new Dimension(pnlPuntuaciones.getPreferredSize().width, juegoActual.getPreferredSize().height));
        pack();
        centrarForm(this);
        juegoActual.requestFocusInWindow();
    }
    
    /**
     * 
     * Realiza las siguientes operaciones para terminar el juego:
     * - Esconde todas las etiquetas de puntuación de los jugadores y el panel de puntuaciones
     * - Esconde el menú de reiniciar el juego.
     * - Muestra el panel de configuración de nuevo juego.
     * - Quita el panel del juego que acaba de terminar y lo destruye.
     * - Redimensiona todo el formulario y lo centra en la pantalla.
     */
    public void terminarJuego() {
        
        for(JLabel puntuacion : puntuaciones) puntuacion.setVisible(false);
        pnlPuntuaciones.setVisible(false);
        pnlNuevoJuego.setVisible(true);
        mnuNuevoJuego.setVisible(false);
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
        
        // Para el tema "look and feel"
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        // Crea y muestra el form
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmJuego().setVisible(true);
            }
        });
    }
}
