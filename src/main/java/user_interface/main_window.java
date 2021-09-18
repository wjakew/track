/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package user_interface;

import com.jakubwawak.track.connector.Connector;

/**
 *
 * @author Jakub Wawak
 */
public class main_window extends javax.swing.JFrame {

    /**
     * Creates new form main_window
     */
    Connector connector;
    
    public main_window(Connector connector) {
        initComponents();
        this.connector = connector;
        this.setLocationRelativeTo(null);
        load_main_components();
        setVisible(true);
    }
    
    /**
     * Function for loading main components
     */
    void load_main_components(){
        menu_user_session.setText(connector.oauth.session_token);
        menu_user_session.setEnabled(false);
        menu_user_time.setText(connector.oauth.apr_login_time.toString());
        menu_user_time.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menu_log_out = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menu_user_session = new javax.swing.JMenuItem();
        menu_user_time = new javax.swing.JMenuItem();
        menu_reload_session = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Track Client");

        jMenu1.setText("Actions");

        menu_log_out.setText("Log out");
        menu_log_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_log_outActionPerformed(evt);
            }
        });
        jMenu1.add(menu_log_out);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Information");

        menu_user_session.setText("user_session");
        jMenu2.add(menu_user_session);

        menu_user_time.setText("user_time");
        jMenu2.add(menu_user_time);

        menu_reload_session.setText("Reload session");
        menu_reload_session.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_reload_sessionActionPerformed(evt);
            }
        });
        jMenu2.add(menu_reload_session);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 875, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_log_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_log_outActionPerformed
        new message_window(this,true,"User logged out","");
        dispose();
    }//GEN-LAST:event_menu_log_outActionPerformed

    private void menu_reload_sessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_reload_sessionActionPerformed
        new login_window(this,true,connector,1);
        load_main_components();
    }//GEN-LAST:event_menu_reload_sessionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menu_log_out;
    private javax.swing.JMenuItem menu_reload_session;
    private javax.swing.JMenuItem menu_user_session;
    private javax.swing.JMenuItem menu_user_time;
    // End of variables declaration//GEN-END:variables
}