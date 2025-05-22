package com.jframe;

import com.resources.dao.DAOUsuario;
import com.resources.prog.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author Alicia Guerrero Marquez
 */
public class Registro extends javax.swing.JFrame {

    public Registro() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        botonLogin = new javax.swing.JButton();
        labelNombreUsuario = new javax.swing.JLabel();
        textoNombreUsuario = new javax.swing.JTextField();
        labelContraseña = new javax.swing.JLabel();
        textoContraseña = new javax.swing.JPasswordField();
        labelNombreUsuarioName = new javax.swing.JLabel();
        botonAceptar = new javax.swing.JButton();
        textoNombreUsuarioName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JokerPoker - Registro");
        setResizable(false);

        botonLogin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonLogin.setText("Si ya tiene una cuenta, pulse aquí");
        botonLogin.setToolTipText("");
        botonLogin.setActionCommand("¿No esta registrado? Pulse aquí");
        botonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLoginActionPerformed(evt);
            }
        });

        labelNombreUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelNombreUsuario.setText("Nombre de usuario:");

        labelContraseña.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelContraseña.setText("Contraseña:");

        labelNombreUsuarioName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelNombreUsuarioName.setText("Nombre:");

        botonAceptar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Registro");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 163, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(216, 216, 216))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(356, 356, 356))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(textoNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelNombreUsuarioName)
                                    .addComponent(labelContraseña))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoContraseña, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoNombreUsuarioName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(196, 196, 196))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombreUsuario))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNombreUsuarioName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombreUsuarioName))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelContraseña)
                    .addComponent(textoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(botonAceptar)
                .addGap(18, 18, 18)
                .addComponent(botonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLoginActionPerformed
        //abre el menú de login
        Login abrir = new Login();
        abrir.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_botonLoginActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        String login = textoNombreUsuario.getText().trim();
        String nombre = textoNombreUsuarioName.getText().trim();
        String passwd = textoContraseña.getText().trim();

        if (nombre.isBlank() || nombre.length() > 24) { //si el nombre del usuario está vacío o es demasiado largo
            JOptionPane.showMessageDialog(this, "Debe introducir un nombre válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (passwd.isBlank() || passwd.length() > 24) { //si la contraseña está vacía o es demasiado larga
            JOptionPane.showMessageDialog(this, "Debe introducir una contraseña válida.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (login.isBlank() || login.length() > 24) { //si el nombre de usuario está vacío o es demasiado largo
            JOptionPane.showMessageDialog(this, "Debe introducir un nombre de usuario válido", "Error", JOptionPane.ERROR_MESSAGE);

        } else {

            Usuario u = new DAOUsuario().getUsuario(login);
            if (u == null) { //si el nombre de usuario introducido no existe

                u = new Usuario(login, passwd, nombre, Usuario.getInitialBalance(), false);
                new DAOUsuario().insertUsuario(u);
                Login abrir = new Login();
                abrir.setVisible(true);
                this.setVisible(false);
                dispose();

            } else { //si el nombre de usuario introducido ya existe

                JOptionPane.showMessageDialog(this, "El nombre de usuario introducido ya existe.", "Error", JOptionPane.ERROR_MESSAGE);

            }

        }


    }//GEN-LAST:event_botonAceptarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonLogin;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelContraseña;
    private javax.swing.JLabel labelNombreUsuario;
    private javax.swing.JLabel labelNombreUsuarioName;
    private javax.swing.JPasswordField textoContraseña;
    private javax.swing.JTextField textoNombreUsuario;
    private javax.swing.JTextField textoNombreUsuarioName;
    // End of variables declaration//GEN-END:variables
}
