package com.jframe;

import com.resources.dao.DAOUsuario;
import com.resources.prog.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author Alicia Guerrero Marquez
 */
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        cabecera = new javax.swing.JLabel();
        botonRegistro = new javax.swing.JButton();
        labelNombreUsuario = new javax.swing.JLabel();
        textoNombreUsuario = new javax.swing.JTextField();
        labelContraseña = new javax.swing.JLabel();
        textoContraseña = new javax.swing.JPasswordField();
        botonLogin = new javax.swing.JButton();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JokerPoker - Login");
        setResizable(false);

        cabecera.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        cabecera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cabecera.setText("Login");

        botonRegistro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonRegistro.setToolTipText("");
        botonRegistro.setActionCommand("¿No esta registrado? Pulse aquí");
        botonRegistro.setLabel("¿No esta registrado? Pulse aquí");
        botonRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistroActionPerformed(evt);
            }
        });

        labelNombreUsuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelNombreUsuario.setText("Nombre de usuario:");

        textoNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoNombreUsuarioActionPerformed(evt);
            }
        });

        labelContraseña.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelContraseña.setText("Contraseña:");

        botonLogin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonLogin.setText("Entrar");
        botonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                .addGap(14, 14, 14))
            .addGroup(layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(botonRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelContraseña)
                            .addComponent(labelNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textoNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(textoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(botonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(cabecera)
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombreUsuario))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelContraseña)
                    .addComponent(textoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(botonLogin)
                .addGap(43, 43, 43)
                .addComponent(botonRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistroActionPerformed
        //abre el menú de registro
        Registro abrir = new Registro();
        abrir.setVisible(true);
        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_botonRegistroActionPerformed

    private void botonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLoginActionPerformed
        String login = textoNombreUsuario.getText().trim();
        String passwd = textoContraseña.getText().trim();
        if (login.isBlank()) { //Si el nombre está vacío 
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (passwd.isBlank()) { //Si la contraseña está vacía
            JOptionPane.showMessageDialog(this, "La contraseña no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            Usuario u = new DAOUsuario().getUsuario(login);
            if (u == null) {
                JOptionPane.showMessageDialog(this, "El usuario no existe", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (passwd.equals(u.getPasswd())) {//Si la contraseña introducida no es correcta 
                if (u.esAdmin()) { //Si el usuario es admin
                    MenuAdmin menu = new MenuAdmin(u);
                    this.setVisible(false);
                    menu.setVisible(true);
                    dispose();
                } else {
                    MenuUser menu = new MenuUser(u);
                    this.setVisible(false);
                    menu.setVisible(true);
                    dispose();
                }

            } else {

                JOptionPane.showMessageDialog(this, "La contraseña es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);

            }

        }

    }//GEN-LAST:event_botonLoginActionPerformed

    private void textoNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoNombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoNombreUsuarioActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonLogin;
    private javax.swing.JButton botonRegistro;
    private javax.swing.JLabel cabecera;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelContraseña;
    private javax.swing.JLabel labelNombreUsuario;
    private javax.swing.JPasswordField textoContraseña;
    private javax.swing.JTextField textoNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
