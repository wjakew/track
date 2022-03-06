/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package ui_components;

import com.jakubwawak.track.connector.Connector;
import com.jakubwawak.track.connector.Project_Connector;
import com.jakubwawak.track.connector.Room_Connector;
import com.mashape.unirest.http.exceptions.UnirestException;
import maintenence.Parser;
import user_interface.message_window;

/**
 *Window for adding member
 * @author jakubwawak
 */
public class addmember_window extends javax.swing.JDialog {

    
    Connector connector;
    int project_id;
    int mode;
    /**
     * Creates new form addmember_window
     * modes:
     * 1 - adds new member to the project
     * 2 - adds new member to the room
     */
    public addmember_window(java.awt.Frame parent, boolean modal,Connector connector,int project_id,int mode) {
        super(parent, modal);
        this.connector = connector;
        this.project_id = project_id;
        this.mode = mode;
        initComponents();
        if ( mode == 1 )
            combobox_role.setVisible(false);
        else
            combobox_role.setSelectedIndex(1);
        this.setLocationRelativeTo(parent);
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

        field_login = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        button_add = new javax.swing.JButton();
        combobox_role = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add member");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User login");

        button_add.setText("Add member");
        button_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_addActionPerformed(evt);
            }
        });

        combobox_role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1: admin", "2: user", "3: moderator", "4: spectator" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(field_login)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_add, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .addComponent(combobox_role, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(field_login, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combobox_role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_add)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_addActionPerformed
            String user_input = field_login.getText();
            if ( !user_input.equals("") ){
                try {
                    Parser parser = new Parser(connector.check_user_login(user_input, this));
                    if ( parser.get_int("user_id") > 0 ){
                        // add member project
                        if (mode == 1){
                            Project_Connector pc = new Project_Connector(connector);
                            Parser parser1 = new Parser(pc.add_project_members(this, project_id, parser.get_int("user_id")));
                            if ( parser1.get_int("flag") == 2 ){
                                new message_window(this,true,"Member added","");
                                dispose();
                            }
                            else{
                                new message_window(this,true,"Error adding member","ERROR");
                            }
                        }
                        // add room member
                        else if (mode == 2){
                            Room_Connector rc = new Room_Connector(connector);
                            Parser parser1 = new Parser(rc.add_room_member(project_id, parser.get_int("user_id"),
                                    combobox_role.getSelectedIndex()+1, this));
                            if ( parser1.get_int("flag") == 1 ){
                                new message_window(this,true,"Room member added","");
                                dispose();
                            }
                            else{
                                new message_window(this,true,"Failed to add member!\nCheck API log","");
                            }
                        }
                    }
                    else{
                        new message_window(this,true,"Wrong user login","");
                        field_login.setText("");
                    }
                } catch (UnirestException ex) {
                    new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
                    dispose();
                }
            }
            else{
                new message_window(this,true,"Error\n Wrong login","");
            }
        
    }//GEN-LAST:event_button_addActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_add;
    private javax.swing.JComboBox<String> combobox_role;
    private javax.swing.JTextField field_login;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
