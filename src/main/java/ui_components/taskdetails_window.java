/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package ui_components;

import com.jakubwawak.track.connector.Connector;
import com.jakubwawak.track.connector.Task_Connector;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import maintenence.Parser;
import user_interface.message_window;

/**
 *Window for showing task details
 * @author kubaw
 */
public class taskdetails_window extends javax.swing.JDialog {

    /**
     * Creates new form taskdetails_window
     */
    Connector connector;
    int task_id;
    public taskdetails_window(java.awt.Frame parent, boolean modal,Connector connector,int task_id) throws UnirestException {
        super(parent, modal);
        this.connector = connector;
        this.task_id = task_id;
        initComponents();
        this.setLocationRelativeTo(null);
        load_window();
        setVisible(true);
    }
    
    /**
     * Function for loading window components
     * @throws UnirestException 
     */
    void load_window() throws UnirestException{
        Task_Connector tc = new Task_Connector(connector);
        Parser parser = new Parser(tc.get_task(task_id, this));
        Parser owner = new Parser(connector.get_user(parser.get_int("user_id"), this));
        label_taskname.setText(parser.get_string("task_name"));
        this.setTitle("Task - "+parser.get_string("task_name")+" - details");
        label_owner.setText(owner.get_string("user_login"));
        label_state.setText(parser.get_string("task_state"));
        field_desc.setText(parser.get_string("task_desc"));
        label_priority.setText("Priority: "+parser.get_int("task_priority"));
        field_desc.setEditable(false);
        label_project.setText("ProjectID connected: "+parser.get_int("project_id"));
        if ( label_state.getText().equals("UNDONE")){
            button_settaskdone.setEnabled(true);
            label_state.setForeground(Color.red);
        }
        else{
            label_state.setForeground(Color.green);
            button_settaskdone.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_taskname = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        field_desc = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        label_owner = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        label_state = new javax.swing.JLabel();
        button_settaskdone = new javax.swing.JButton();
        button_showhistory = new javax.swing.JButton();
        label_priority = new javax.swing.JLabel();
        label_project = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        label_taskname.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        label_taskname.setText("TASK NAME");

        field_desc.setColumns(20);
        field_desc.setRows(5);
        jScrollPane1.setViewportView(field_desc);

        jLabel1.setText("Description");

        jLabel3.setText("Owner: ");

        label_owner.setText("jLabel4");

        jLabel4.setText("State:");

        label_state.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        label_state.setText("STATE");

        button_settaskdone.setText("Set task done");
        button_settaskdone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_settaskdoneActionPerformed(evt);
            }
        });

        button_showhistory.setText("Show task history");
        button_showhistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_showhistoryActionPerformed(evt);
            }
        });

        label_priority.setText("Priority: X");

        label_project.setText("Project connected:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_taskname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_settaskdone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(button_showhistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(label_owner, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label_project))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(label_state, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(label_priority)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_taskname, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(label_owner))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(label_state, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_priority))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_project)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_settaskdone)
                        .addGap(18, 18, 18)
                        .addComponent(button_showhistory)
                        .addGap(33, 33, 33))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_settaskdoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_settaskdoneActionPerformed
        Task_Connector tc = new Task_Connector(connector);
        try {
            Parser parser = new Parser(tc.set_task_done(task_id, this));
            if ( parser.get_int("flag") == 1){
                new message_window(this,true,"Task set to done","");
                load_window();
            }
        } catch (UnirestException ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
        }
    }//GEN-LAST:event_button_settaskdoneActionPerformed

    private void button_showhistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_showhistoryActionPerformed
        Task_Connector tc = new Task_Connector(connector);
        try {
            Parser parser = new Parser(tc.get_history(task_id, this));
            new history_window(this,true,"Task history",parser.get_arraylist("view"));
        } catch (Exception ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
        }
        
    }//GEN-LAST:event_button_showhistoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_settaskdone;
    private javax.swing.JButton button_showhistory;
    private javax.swing.JTextArea field_desc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_owner;
    private javax.swing.JLabel label_priority;
    private javax.swing.JLabel label_project;
    private javax.swing.JLabel label_state;
    private javax.swing.JLabel label_taskname;
    // End of variables declaration//GEN-END:variables
}
