/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package ui_components;

import com.jakubwawak.track.connector.Connector;
import com.jakubwawak.track.connector.Snippet_Connector;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.logging.Level;
import java.util.logging.Logger;
import maintenence.Parser;
import user_interface.message_window;

/**
 *Window for sharing snippets
 * @author jakubwawak
 */
public class snippetshare_window extends javax.swing.JDialog {

    /**
     * Creates new form snippetshare_window
     */
    Connector connector;
    int user_snippet_id;
    public snippetshare_window(java.awt.Frame parent, boolean modal,Connector connector,int user_snippet_id) {
        super(parent, modal);
        this.connector = connector;
        this.user_snippet_id = user_snippet_id;
        initComponents();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        field_login = new javax.swing.JTextField();
        button_share = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("User login:");

        button_share.setText("Share");
        button_share.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_shareActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_share, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_login, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_login, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(10, 10, 10)
                .addComponent(button_share)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_shareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_shareActionPerformed
        String user_login = field_login.getText();
        if ( !user_login.equals("") ){
            try {
                Parser parser = new Parser(connector.check_user_login(user_login, this));
                if ( parser.get_int("user_id") > 0 ){
                    Snippet_Connector sc = new Snippet_Connector(connector);
                    Parser parser_share = new Parser(sc.share_snippet(user_snippet_id,parser.get_int("user_id"),this));
                    if( parser_share.get_int("flag") == 1 ){
                        new message_window(this,true,"Snippet shared!","");
                        dispose();
                    }
                    else{
                        new message_window(this,true,"Error sharing snippet","");
                        dispose();
                    }
                }
                else{
                    new message_window(this,true,"Wrong login. Cannot find login in database","Login error");
                }
            } catch (UnirestException ex) {
                new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
            }
        }else{
            new message_window(this,true,"Wrong login","Login error");
        }
    }//GEN-LAST:event_button_shareActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_share;
    private javax.swing.JTextField field_login;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
