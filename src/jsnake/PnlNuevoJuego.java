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
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * 
 * Clase de un panel que contiene los controles para personalizar una nueva partida e iniciarla.
 * @author SamuelCoral
 */
public class PnlNuevoJuego extends javax.swing.JPanel {

    /**
     * Creates new form PnlNuevoJuego
     * @param juegoOrigen Formulario que crea este panel de controles.
     */
    public PnlNuevoJuego(FrmJuego juegoOrigen) {
        
        initComponents();
        this.juegoOrigen = juegoOrigen;
        pnlColorViboritas = new JPanel[4];
        for(int c = 0; c < 4; c++) {
            
            pnlColorViboritas[c] = new JPanel();
            pnlColorViboritas[c].setBackground(coloresDefaultViboritas[c]);
            pnlColorViboritas[c].setName(String.valueOf(c));
            pnlColorViboritas[c].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    cambiarColorViborita(Integer.parseInt(e.getComponent().getName()));
                }
            });
        }
        tabColoresViboritas.addTab("1", pnlColorViboritas[0]);
    }
    
    FrmJuego juegoOrigen;
    JPanel[] pnlColorViboritas;
    Color[] coloresDefaultViboritas = new Color[] { Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE };
    
    /**
     * 
     * Cambia el color del panel que correspode al color de la viborita indicada.
     * @param viborita Índice de la viborita cuyo color correspondre al color del panel.
     * @return Color seleccionado o null si cerró el cuadro de diálogo.
     */
    public Color cambiarColorViborita(int viborita) {
        
        Color colorSeleccionado = JColorChooser.showDialog(juegoOrigen, "Elija un color para la viborita " + String.valueOf(viborita + 1), pnlColorViboritas[viborita].getBackground());
        if(colorSeleccionado == null) return null;
        pnlColorViboritas[viborita].setBackground(colorSeleccionado);
        return colorSeleccionado;
    }
    
    /**
     * 
     * Cambia el color del panel que indicará el color de fondo de la partida.
     * @return Color seleccionado o null si cerró el cuadro de diálogo.
     */
    public Color cambiarColorFondo() {
        
        Color colorSeleccionado = JColorChooser.showDialog(juegoOrigen, "Elija un color para el fondo del campo de juego.", pnlColorFondo.getBackground());
        if(colorSeleccionado == null) return null;
        pnlColorFondo.setBackground(colorSeleccionado);
        return colorSeleccionado;
    }
    
    /**
     * 
     * Cambia el color del panel que indicará el color principal de las comiditas de la partida.
     * @return Color seleccionado o null si cerró el cuadro de diálogo.
     */
    public Color cambiarColorComiditas() {
        
        Color colorSeleccionado = JColorChooser.showDialog(juegoOrigen, "Elija un color para las comiditas.", pnlColorComiditas.getBackground());
        if(colorSeleccionado == null) return null;
        pnlColorComiditas.setBackground(colorSeleccionado);
        return colorSeleccionado;
    }
    
    /**
     * 
     * Aplica el tema Look And Feel "Metal" si está disponible, de lo contrario
     * aplicará el tema por defecto.
     */
    public void aplicarTemaPorDefecto() {
        
        String temaSeleccionar = null;
        for(LookAndFeelInfo tema : UIManager.getInstalledLookAndFeels()) {
            cmbTema.addItem(tema.getName());
            if ("Metal".equals(tema.getName())) temaSeleccionar = tema.getName();
        }
        if(temaSeleccionar != null) cmbTema.setSelectedItem(temaSeleccionar);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chkDificultad = new javax.swing.ButtonGroup();
        lblNuevoJuego = new javax.swing.JLabel();
        btnEmpezar = new javax.swing.JButton();
        pnlDimensionesCampo = new javax.swing.JPanel();
        lblTamCuadros = new javax.swing.JLabel();
        numAnchoCuadros = new javax.swing.JSpinner();
        numAltoCuadros = new javax.swing.JSpinner();
        lblAnchoCuadros = new javax.swing.JLabel();
        lblAltoCuadros = new javax.swing.JLabel();
        lblTamCampo = new javax.swing.JLabel();
        numAnchoCampo = new javax.swing.JSpinner();
        numAltoCampo = new javax.swing.JSpinner();
        lblAnchoCampo = new javax.swing.JLabel();
        lblAltoCampo = new javax.swing.JLabel();
        pnlOpcionesJugadores = new javax.swing.JPanel();
        numJugadores = new javax.swing.JSpinner();
        lblNumJugadores = new javax.swing.JLabel();
        tabColoresViboritas = new javax.swing.JTabbedPane();
        pnlOpcionesJuego = new javax.swing.JPanel();
        numComiditas = new javax.swing.JSpinner();
        numLongitudInicial = new javax.swing.JSpinner();
        lblNumComiditas = new javax.swing.JLabel();
        lblLongitudInicial = new javax.swing.JLabel();
        tabColoresElemetos = new javax.swing.JTabbedPane();
        pnlColorFondo = new javax.swing.JPanel();
        pnlColorComiditas = new javax.swing.JPanel();
        chkSonidos = new javax.swing.JCheckBox();
        pnlDificultad = new javax.swing.JPanel();
        numDificultad = new javax.swing.JSpinner();
        rdbMuyFacil = new javax.swing.JRadioButton();
        rdbFacil = new javax.swing.JRadioButton();
        rdbMedio = new javax.swing.JRadioButton();
        rdbDificil = new javax.swing.JRadioButton();
        rdbMuydificil = new javax.swing.JRadioButton();
        rdbPersonalizado = new javax.swing.JRadioButton();
        cmbTema = new javax.swing.JComboBox<>();
        lblTema = new javax.swing.JLabel();

        lblNuevoJuego.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNuevoJuego.setText("Nuevo Juego");

        btnEmpezar.setText("Jugar");
        btnEmpezar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpezarActionPerformed(evt);
            }
        });

        pnlDimensionesCampo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dimensiones del campo de juego", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11))); // NOI18N

        lblTamCuadros.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTamCuadros.setForeground(new java.awt.Color(102, 102, 102));
        lblTamCuadros.setText("Tamaño de los cuadros (pixeles)");

        numAnchoCuadros.setModel(new javax.swing.SpinnerNumberModel(20, 5, 60, 1));

        numAltoCuadros.setModel(new javax.swing.SpinnerNumberModel(20, 5, 60, 1));

        lblAnchoCuadros.setText("Ancho");

        lblAltoCuadros.setText("Alto");

        lblTamCampo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTamCampo.setForeground(new java.awt.Color(102, 102, 102));
        lblTamCampo.setText("Tamaño del campo (cuadros)");

        numAnchoCampo.setModel(new javax.swing.SpinnerNumberModel(30, 3, 100, 1));

        numAltoCampo.setModel(new javax.swing.SpinnerNumberModel(30, 3, 100, 1));

        lblAnchoCampo.setText("Ancho");

        lblAltoCampo.setText("Alto");

        javax.swing.GroupLayout pnlDimensionesCampoLayout = new javax.swing.GroupLayout(pnlDimensionesCampo);
        pnlDimensionesCampo.setLayout(pnlDimensionesCampoLayout);
        pnlDimensionesCampoLayout.setHorizontalGroup(
            pnlDimensionesCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDimensionesCampoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDimensionesCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTamCampo)
                    .addGroup(pnlDimensionesCampoLayout.createSequentialGroup()
                        .addGroup(pnlDimensionesCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAnchoCuadros)
                            .addComponent(lblAltoCuadros))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDimensionesCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numAltoCuadros)
                            .addComponent(numAnchoCuadros)))
                    .addGroup(pnlDimensionesCampoLayout.createSequentialGroup()
                        .addGroup(pnlDimensionesCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAnchoCampo)
                            .addComponent(lblAltoCampo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDimensionesCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numAltoCampo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(numAnchoCampo)))
                    .addComponent(lblTamCuadros))
                .addContainerGap())
        );
        pnlDimensionesCampoLayout.setVerticalGroup(
            pnlDimensionesCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDimensionesCampoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTamCuadros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDimensionesCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numAnchoCuadros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAnchoCuadros))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDimensionesCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numAltoCuadros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAltoCuadros))
                .addGap(18, 18, 18)
                .addComponent(lblTamCampo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDimensionesCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numAnchoCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAnchoCampo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDimensionesCampoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numAltoCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAltoCampo))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlOpcionesJugadores.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones de jugadores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11))); // NOI18N

        numJugadores.setModel(new javax.swing.SpinnerNumberModel(1, 1, 4, 1));
        numJugadores.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                numJugadoresStateChanged(evt);
            }
        });

        lblNumJugadores.setText("Jugadores");

        tabColoresViboritas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Color", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        javax.swing.GroupLayout pnlOpcionesJugadoresLayout = new javax.swing.GroupLayout(pnlOpcionesJugadores);
        pnlOpcionesJugadores.setLayout(pnlOpcionesJugadoresLayout);
        pnlOpcionesJugadoresLayout.setHorizontalGroup(
            pnlOpcionesJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesJugadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOpcionesJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabColoresViboritas)
                    .addGroup(pnlOpcionesJugadoresLayout.createSequentialGroup()
                        .addComponent(lblNumJugadores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numJugadores, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlOpcionesJugadoresLayout.setVerticalGroup(
            pnlOpcionesJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesJugadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOpcionesJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumJugadores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabColoresViboritas, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlOpcionesJuego.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones del juego", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11))); // NOI18N

        numComiditas.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));

        numLongitudInicial.setModel(new javax.swing.SpinnerNumberModel(10, 1, 50, 1));

        lblNumComiditas.setText("Comiditas");

        lblLongitudInicial.setText("Longitud inicial");

        tabColoresElemetos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Colores de los elementos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        pnlColorFondo.setBackground(java.awt.Color.gray);
        pnlColorFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlColorFondoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlColorFondoLayout = new javax.swing.GroupLayout(pnlColorFondo);
        pnlColorFondo.setLayout(pnlColorFondoLayout);
        pnlColorFondoLayout.setHorizontalGroup(
            pnlColorFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );
        pnlColorFondoLayout.setVerticalGroup(
            pnlColorFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        tabColoresElemetos.addTab("Fondo", pnlColorFondo);

        pnlColorComiditas.setBackground(java.awt.Color.yellow);
        pnlColorComiditas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlColorComiditasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlColorComiditasLayout = new javax.swing.GroupLayout(pnlColorComiditas);
        pnlColorComiditas.setLayout(pnlColorComiditasLayout);
        pnlColorComiditasLayout.setHorizontalGroup(
            pnlColorComiditasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );
        pnlColorComiditasLayout.setVerticalGroup(
            pnlColorComiditasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );

        tabColoresElemetos.addTab("Comiditas", pnlColorComiditas);

        chkSonidos.setSelected(true);
        chkSonidos.setText("Reproducir sonidos");

        pnlDificultad.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dificultad (ms entre pasos)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11), new java.awt.Color(102, 102, 102))); // NOI18N

        numDificultad.setModel(new javax.swing.SpinnerNumberModel(100, 20, 1000, 1));
        numDificultad.setEnabled(false);

        chkDificultad.add(rdbMuyFacil);
        rdbMuyFacil.setText("Muy fácil");
        rdbMuyFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMuyFacilActionPerformed(evt);
            }
        });

        chkDificultad.add(rdbFacil);
        rdbFacil.setText("Fácil");
        rdbFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbFacilActionPerformed(evt);
            }
        });

        chkDificultad.add(rdbMedio);
        rdbMedio.setSelected(true);
        rdbMedio.setText("Medio");
        rdbMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMedioActionPerformed(evt);
            }
        });

        chkDificultad.add(rdbDificil);
        rdbDificil.setText("Difícil");
        rdbDificil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbDificilActionPerformed(evt);
            }
        });

        chkDificultad.add(rdbMuydificil);
        rdbMuydificil.setText("Muy difícil");
        rdbMuydificil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbMuydificilActionPerformed(evt);
            }
        });

        chkDificultad.add(rdbPersonalizado);
        rdbPersonalizado.setText("Personalizado");
        rdbPersonalizado.setToolTipText("");
        rdbPersonalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbPersonalizadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDificultadLayout = new javax.swing.GroupLayout(pnlDificultad);
        pnlDificultad.setLayout(pnlDificultadLayout);
        pnlDificultadLayout.setHorizontalGroup(
            pnlDificultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDificultadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDificultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numDificultad)
                    .addGroup(pnlDificultadLayout.createSequentialGroup()
                        .addGroup(pnlDificultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbMuyFacil)
                            .addComponent(rdbFacil)
                            .addComponent(rdbMedio))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDificultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbPersonalizado)
                            .addComponent(rdbMuydificil)
                            .addComponent(rdbDificil))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDificultadLayout.setVerticalGroup(
            pnlDificultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDificultadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDificultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbMuyFacil)
                    .addComponent(rdbDificil))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDificultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbFacil)
                    .addComponent(rdbMuydificil))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDificultadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbMedio)
                    .addComponent(rdbPersonalizado))
                .addGap(8, 8, 8)
                .addComponent(numDificultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlOpcionesJuegoLayout = new javax.swing.GroupLayout(pnlOpcionesJuego);
        pnlOpcionesJuego.setLayout(pnlOpcionesJuegoLayout);
        pnlOpcionesJuegoLayout.setHorizontalGroup(
            pnlOpcionesJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesJuegoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOpcionesJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabColoresElemetos)
                    .addGroup(pnlOpcionesJuegoLayout.createSequentialGroup()
                        .addGroup(pnlOpcionesJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLongitudInicial)
                            .addComponent(lblNumComiditas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlOpcionesJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numComiditas)
                            .addComponent(numLongitudInicial)))
                    .addComponent(pnlDificultad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlOpcionesJuegoLayout.createSequentialGroup()
                        .addComponent(chkSonidos)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlOpcionesJuegoLayout.setVerticalGroup(
            pnlOpcionesJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesJuegoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOpcionesJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numComiditas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumComiditas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOpcionesJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numLongitudInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLongitudInicial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabColoresElemetos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkSonidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDificultad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cmbTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTemaActionPerformed(evt);
            }
        });

        lblTema.setText("Tema");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEmpezar)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNuevoJuego)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pnlDimensionesCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pnlOpcionesJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlOpcionesJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTema)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 91, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNuevoJuego)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlDimensionesCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlOpcionesJugadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlOpcionesJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTema))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btnEmpezar)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEmpezarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpezarActionPerformed

        try {
            
            juegoOrigen.juegoActual = new AreaJuego(
                juegoOrigen,
                new Dimension((int)numAnchoCuadros.getValue(), (int)numAltoCuadros.getValue()),
                new Dimension((int)numAnchoCampo.getValue(), (int)numAltoCampo.getValue()),
                (int)numJugadores.getValue(),
                new int[][] {
                    new int[] { KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_W },
                    new int[] { KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_UP },
                    new int[] { KeyEvent.VK_L, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_I },
                    new int[] { KeyEvent.VK_NUMPAD6, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD8},
                },
                new Color[] {
                    pnlColorViboritas[0].getBackground(),
                    pnlColorViboritas[1].getBackground(),
                    pnlColorViboritas[2].getBackground(),
                    pnlColorViboritas[3].getBackground()
                },
                (int)numComiditas.getValue(),
                (int)numLongitudInicial.getValue(),
                pnlColorFondo.getBackground(),
                pnlColorComiditas.getBackground(),
                chkSonidos.isSelected(),
                (int)numDificultad.getValue()
            );
            juegoOrigen.empezarJuego();
        }
        catch(IllegalArgumentException e) {
            
            javax.swing.JOptionPane.showMessageDialog(this, e.getMessage(), "Error al crear la partida.", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEmpezarActionPerformed

    private void pnlColorFondoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlColorFondoMouseClicked
        cambiarColorFondo();
    }//GEN-LAST:event_pnlColorFondoMouseClicked

    private void pnlColorComiditasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlColorComiditasMouseClicked
        cambiarColorComiditas();
    }//GEN-LAST:event_pnlColorComiditasMouseClicked

    private void rdbMuyFacilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMuyFacilActionPerformed
        numDificultad.setValue(350);
        numDificultad.setEnabled(false);
    }//GEN-LAST:event_rdbMuyFacilActionPerformed

    private void rdbFacilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbFacilActionPerformed
        numDificultad.setValue(200);
        numDificultad.setEnabled(false);
    }//GEN-LAST:event_rdbFacilActionPerformed

    private void rdbMedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMedioActionPerformed
        numDificultad.setValue(100);
        numDificultad.setEnabled(false);
    }//GEN-LAST:event_rdbMedioActionPerformed

    private void rdbDificilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbDificilActionPerformed
        numDificultad.setValue(50);
        numDificultad.setEnabled(false);
    }//GEN-LAST:event_rdbDificilActionPerformed

    private void rdbMuydificilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbMuydificilActionPerformed
        numDificultad.setValue(35);
        numDificultad.setEnabled(false);
    }//GEN-LAST:event_rdbMuydificilActionPerformed

    private void rdbPersonalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbPersonalizadoActionPerformed
        numDificultad.setEnabled(true);
    }//GEN-LAST:event_rdbPersonalizadoActionPerformed

    private void numJugadoresStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_numJugadoresStateChanged
        
        if(tabColoresViboritas.getTabCount() == (int)numJugadores.getValue()) return;
        int tabSelec = tabColoresViboritas.getSelectedIndex();
        tabColoresViboritas.removeAll();
        for(int c = 0; c < (int)numJugadores.getValue(); c++) tabColoresViboritas.addTab(String.valueOf(c + 1), pnlColorViboritas[c]);
        tabColoresViboritas.setSelectedIndex(tabSelec < tabColoresViboritas.getTabCount() ? tabSelec : tabColoresViboritas.getTabCount() - 1);
    }//GEN-LAST:event_numJugadoresStateChanged

    private void cmbTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTemaActionPerformed
        
        try {
            UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[cmbTema.getSelectedIndex()].getClassName());
            SwingUtilities.updateComponentTreeUI(juegoOrigen);
            juegoOrigen.pack();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo cambiar el Tema de la aplicación.", "Error al cambiar el tema", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_cmbTemaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEmpezar;
    private javax.swing.ButtonGroup chkDificultad;
    public javax.swing.JCheckBox chkSonidos;
    private javax.swing.JComboBox<String> cmbTema;
    private javax.swing.JLabel lblAltoCampo;
    private javax.swing.JLabel lblAltoCuadros;
    private javax.swing.JLabel lblAnchoCampo;
    private javax.swing.JLabel lblAnchoCuadros;
    private javax.swing.JLabel lblLongitudInicial;
    private javax.swing.JLabel lblNuevoJuego;
    private javax.swing.JLabel lblNumComiditas;
    private javax.swing.JLabel lblNumJugadores;
    private javax.swing.JLabel lblTamCampo;
    private javax.swing.JLabel lblTamCuadros;
    private javax.swing.JLabel lblTema;
    private javax.swing.JSpinner numAltoCampo;
    private javax.swing.JSpinner numAltoCuadros;
    private javax.swing.JSpinner numAnchoCampo;
    private javax.swing.JSpinner numAnchoCuadros;
    private javax.swing.JSpinner numComiditas;
    private javax.swing.JSpinner numDificultad;
    private javax.swing.JSpinner numJugadores;
    private javax.swing.JSpinner numLongitudInicial;
    private javax.swing.JPanel pnlColorComiditas;
    private javax.swing.JPanel pnlColorFondo;
    private javax.swing.JPanel pnlDificultad;
    private javax.swing.JPanel pnlDimensionesCampo;
    private javax.swing.JPanel pnlOpcionesJuego;
    private javax.swing.JPanel pnlOpcionesJugadores;
    private javax.swing.JRadioButton rdbDificil;
    private javax.swing.JRadioButton rdbFacil;
    private javax.swing.JRadioButton rdbMedio;
    private javax.swing.JRadioButton rdbMuyFacil;
    private javax.swing.JRadioButton rdbMuydificil;
    private javax.swing.JRadioButton rdbPersonalizado;
    private javax.swing.JTabbedPane tabColoresElemetos;
    private javax.swing.JTabbedPane tabColoresViboritas;
    // End of variables declaration//GEN-END:variables
}
