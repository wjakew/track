/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package ui_components;

import com.jakubwawak.track.connector.Connector;
import com.jakubwawak.track.connector.Room_Connector;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import maintenence.Parser;
import user_interface.message_window;

/**
 *
 * @author jakubwawak
 */
public class roominvite_window extends javax.swing.JDialog {

    Connector connector;
    
    /**
     * Creates new form roominvite_window
     */
    public roominvite_window(java.awt.Frame parent, boolean modal,Connector connector) {
        super(parent, modal);
        this.connector = connector;
        initComponents();
        this.setLocationRelativeTo(parent);
        load_window_icon();
        setVisible(true);
    }
    
    /**
     * Function for loading window icon
     */
    void load_window_icon(){
        try{
            ImageIcon img = new ImageIcon("track_icon.png");
            this.setIconImage(img.getImage());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Function for validating user input in fields
     * @return Boolean
     */
    boolean field_validator(){
        return !field_roomcode.getText().equals("") && !field_password.getText().equals("");
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
        jLabel2 = new javax.swing.JLabel();
        field_roomcode = new javax.swing.JTextField();
        field_password = new javax.swing.JTextField();
        button_join = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Room invite");

        jLabel1.setText("Room code:");

        jLabel2.setText("Room password:");

        button_join.setText("Join");
        button_join.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_joinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_join, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(14, 14, 14)
                        .addComponent(field_password, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(43, 43, 43)
                        .addComponent(field_roomcode)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(field_roomcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(field_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(button_join)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_joinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_joinActionPerformed
        if ( field_validator() ){
            Room_Connector rc = new Room_Connector(connector);
            try {
                Parser parser = new Parser(rc.invite_room(field_roomcode.getText(),field_password.getText(),this));
                if ( parser.get_int("flag") == 1 ){
                    new message_window(this,true,"Room added","");
                }
                else{
                    new message_window(this,true,"Cannot add room","");
                }
            } catch (UnirestException ex) {
                new message_window(this,true,"Failed to add room ("+ex.toString()+")","");
            }
        }
        else{
            new message_window(this,true,"Wrong input","");
        }
    }//GEN-LAST:event_button_joinActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_join;
    private javax.swing.JTextField field_password;
    private javax.swing.JTextField field_roomcode;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
