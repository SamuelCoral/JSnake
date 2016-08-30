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

/**
 * 
 * Clase de un formulario que muestra la información de los controles del juego.
 * @author SamuelCoral
 */
public class FrmInfoControles extends javax.swing.JFrame {

    /**
     * Creates new form FrmInfoControles
     * @param frmOrigen Formulario que crea este formulario de ayuda.
     */
    public FrmInfoControles(javax.swing.JFrame frmOrigen) {
        initComponents();
        this.frmOrigen = frmOrigen;
        inicializarForm();
    }
    
    private void inicializarForm() {
        FrmJuego.centrarForm(this);
    }
    
    private javax.swing.JFrame frmOrigen;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlControlesJugadores = new javax.swing.JScrollPane();
        tblControlesJugadores = new javax.swing.JTable();
        lblControlesJugadores = new javax.swing.JLabel();
        lblNota = new javax.swing.JLabel();
        lblNota2 = new javax.swing.JLabel();
        lblPausa = new javax.swing.JLabel();
        lblTeclaPausa = new javax.swing.JLabel();
        lblTerminarPartida = new javax.swing.JLabel();
        lblTeclaTerminarPartida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Información de los controles");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tblControlesJugadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Jugador 1", "W", "A", "S", "D"},
                {"Jugador 2", "Arriba", "Izquierda", "Abajo", "Derecha"},
                {"Jugador 3", "I", "J", "K", "L"},
                {"Jugador 4", "8", "4", "5", "6"}
            },
            new String [] {
                "", "Arriba", "Izquierda", "Abajo", "Derecha"
            }
        ));
        pnlControlesJugadores.setViewportView(tblControlesJugadores);

        lblControlesJugadores.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblControlesJugadores.setText("Mover las viboritas");

        lblNota.setText("NOTA: Cuadro de diálogo informativo solamente.");

        lblNota2.setText("Aún no se pueden configurar los controles :(");

        lblPausa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPausa.setText("Pausar el juego");

        lblTeclaPausa.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblTeclaPausa.setText("Barra espaciadora");

        lblTerminarPartida.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblTerminarPartida.setText("Terminar la partida");

        lblTeclaTerminarPartida.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblTeclaTerminarPartida.setText("Tecla escape");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblControlesJugadores)
                    .addComponent(lblNota)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTerminarPartida)
                            .addComponent(lblPausa))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTeclaPausa)
                            .addComponent(lblTeclaTerminarPartida)))
                    .addComponent(lblNota2)
                    .addComponent(pnlControlesJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNota)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNota2)
                .addGap(18, 18, 18)
                .addComponent(lblControlesJugadores)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlControlesJugadores, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPausa)
                    .addComponent(lblTeclaPausa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTerminarPartida)
                    .addComponent(lblTeclaTerminarPartida))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        frmOrigen.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblControlesJugadores;
    private javax.swing.JLabel lblNota;
    private javax.swing.JLabel lblNota2;
    private javax.swing.JLabel lblPausa;
    private javax.swing.JLabel lblTeclaPausa;
    private javax.swing.JLabel lblTeclaTerminarPartida;
    private javax.swing.JLabel lblTerminarPartida;
    private javax.swing.JScrollPane pnlControlesJugadores;
    private javax.swing.JTable tblControlesJugadores;
    // End of variables declaration//GEN-END:variables
}