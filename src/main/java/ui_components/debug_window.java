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

/**
 *Window for sending response
 * @author kubaw
 */
public class debug_window extends javax.swing.JDialog {

    /**
     * Creates new form debug_window
     */
    Connector connector;
    public debug_window(java.awt.Frame parent, boolean modal,Connector connector) {
        super(parent, modal);
        this.connector = connector;
        initComponents();
        this.setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("track_icon.ico");
        this.setIconImage(icon);
        load_window();
        setVisible(true);
    }
    
    /**
     * Function for loading window
     */
    void load_window(){
        button_currentsession.setText(connector.oauth.session_token);
        button_apptoken.setText(connector.oauth.app_token);
    }
    
    /**
     * Function for parsing responses
     * @param response
     * @return String 
     */
    String parse_response(String response){
        String parsed = "";
        for(String line : response.split(",")){
            parsed = parsed + line + "\n";
        }
        return parsed;
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
        jLabel3 = new javax.swing.JLabel();
        field_query = new javax.swing.JTextField();
        button_commit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        field_response = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        button_clear = new javax.swing.JButton();
        button_currentsession = new javax.swing.JButton();
        button_apptoken = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Debug");

        jLabel1.setText("Current session token:");

        jLabel2.setText("Current app token:");

        jLabel3.setText("Raw query:");

        button_commit.setText("Commit");
        button_commit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_commitActionPerformed(evt);
            }
        });

        field_response.setColumns(20);
        field_response.setRows(5);
        jScrollPane1.setViewportView(field_response);

        jLabel4.setText("Response");

        button_clear.setText("Clear");
        button_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_clearActionPerformed(evt);
            }
        });

        button_currentsession.setText("jButton1");
        button_currentsession.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_currentsessionActionPerformed(evt);
            }
        });

        button_apptoken.setText("jButton1");
        button_apptoken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_apptokenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_commit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_query))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_currentsession, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_apptoken, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_clear)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(button_currentsession)
                    .addComponent(button_apptoken))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(field_query, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_commit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button_clear)
                    .addComponent(jLabel4))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_commitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_commitActionPerformed
        try {
            field_query.setText(field_query.getText().replaceAll(" ", "%20"));
            JsonElement response = connector.commit(field_query.getText(), this);
            if ( response == null ){
                field_response.setText("response == null");
            }
            else{
                field_response.setText(parse_response(response.toString()));
            }
        } catch (UnirestException ex) {
            field_response.setText(ex.toString());
        }
        
    }//GEN-LAST:event_button_commitActionPerformed

    private void button_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_clearActionPerformed
        field_query.setText("");
        field_response.setText("");
    }//GEN-LAST:event_button_clearActionPerformed

    private void button_currentsessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_currentsessionActionPerformed
        field_query.setText(field_query.getText()+button_currentsession.getText());
        field_query.requestFocus();
    }//GEN-LAST:event_button_currentsessionActionPerformed

    private void button_apptokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_apptokenActionPerformed
        field_query.setText(field_query.getText()+button_apptoken.getText());
        field_query.requestFocus();
    }//GEN-LAST:event_button_apptokenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_apptoken;
    private javax.swing.JButton button_clear;
    private javax.swing.JButton button_commit;
    private javax.swing.JButton button_currentsession;
    private javax.swing.JTextField field_query;
    private javax.swing.JTextArea field_response;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}