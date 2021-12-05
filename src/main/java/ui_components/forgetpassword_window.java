/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package ui_components;

import com.google.gson.JsonElement;
import com.jakubwawak.track.connector.Connector;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Image;
import java.awt.Toolkit;
import maintenence.Parser;
import user_interface.message_window;

/**
 *Window for reseting password
 * @author kubaw
 */
public class forgetpassword_window extends javax.swing.JDialog {

    /**
     * Creates new form resetpassword_window
     */
    Connector connector;
    public forgetpassword_window(javax.swing.JDialog parent, boolean modal,Connector connector) {
        super(parent, modal);
        this.connector = connector;
        initComponents();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Function for field validation
     * @return boolean
     */
    boolean validate_fields(){
        return field_email.getText().contains("@");
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
        field_email = new javax.swing.JTextField();
        button_reset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Password reset");

        jLabel1.setText("Email address:");

        button_reset.setText("Reset password");
        button_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_reset, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(field_email, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(field_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_reset)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_resetActionPerformed
        if ( validate_fields() ){
            try {
                JsonElement response = connector.user_resetpassword(field_email.getText());
                Parser parser = new Parser(response);
                if ( parser.get_int("user_id") == -7 ){
                    new message_window(this,true,"No account connected with this email address","");
                }
                else if ( parser.get_int("user_id") == -5){
                    new message_window(this,true,"Internall error","");
                }
                else{
                    new message_window(this,true,"Password reset sent to "+field_email.getText(),"");
                    dispose();
                }
            } catch (UnirestException ex) {
                new message_window(this,true,"Error \n"+ex.toString(),"");
            }
            
        }
        else{
            new message_window(this,true,"Email address is wrong","");
        }
    }//GEN-LAST:event_button_resetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_reset;
    private javax.swing.JTextField field_email;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
