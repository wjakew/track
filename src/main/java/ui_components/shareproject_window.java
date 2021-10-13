/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package ui_components;

import com.jakubwawak.track.connector.Connector;
import com.jakubwawak.track.connector.Project_Connector;
import com.jakubwawak.track.connector.Share_Connector;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import maintenence.Parser;
import user_interface.message_window;

/**
 *Window for sharing projects
 * @author kubaw
 */
public class shareproject_window extends javax.swing.JDialog {

    /** Creates new form shareproject_window */
    Connector connector;
    public shareproject_window(java.awt.Frame parent, boolean modal,Connector connector) throws UnirestException {
        super(parent, modal);
        initComponents();
        this.connector = connector;
        load_window();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Function for loading window components
     */
    void load_window() throws UnirestException{
        Project_Connector pc = new Project_Connector(connector);
        DefaultListModel dlm = new DefaultListModel();
        Parser parser = new Parser(pc.load_projects_glances(this));
        dlm.addAll(parser.get_arraylist("view"));
        list_projects.setModel(dlm);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        list_projects = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        field_user = new javax.swing.JTextField();
        button_check = new javax.swing.JButton();
        button_share = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Share project");

        list_projects.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(list_projects);

        jLabel1.setText("Choose project to share:");

        jLabel2.setText("User login:");

        button_check.setText("Check");
        button_check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_checkActionPerformed(evt);
            }
        });

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
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_user, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_check, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(button_share, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(field_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_check))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(button_share)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_checkActionPerformed
        String user_login = field_user.getText();
        if ( !user_login.equals("") ){
            try {
                Parser parser = new Parser(connector.check_user_login(user_login, this));
                if ( parser.get_int("user_id") > 0 ){
                    new message_window(this,true,"User found","");
                }
                else{
                    new message_window(this,true,"User not found","");
                }
            } catch (UnirestException ex) {
                new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
            }
        }else{
            new message_window(this,true,"User login is empty","");
        }
    }//GEN-LAST:event_button_checkActionPerformed

    private void button_shareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_shareActionPerformed
        Share_Connector sh = new Share_Connector(connector);
        try{
            int project_id = Integer.parseInt(list_projects.getSelectedValue().toString().split(":")[0]);
            if ( !field_user.getText().isEmpty() ){
                Parser parser = new Parser(sh.share_project(project_id, field_user.getText(), this));
                if ( parser.get_int("flag") == 1){
                    new message_window(this,true,"Project shared to "+field_user.getText(),"");
                    dispose();
                }
                else{
                    if ( parser.get_int("user_id") == -7 ){
                        new message_window(this,true,"No user with login: "+field_user.getText(),"");
                    }
                    else{
                        new message_window(this,true,"Error while sharing project. Check log","");
                    }
                }
            }
        } catch (Exception ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
        }
    }//GEN-LAST:event_button_shareActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_check;
    private javax.swing.JButton button_share;
    private javax.swing.JTextField field_user;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> list_projects;
    // End of variables declaration//GEN-END:variables

}