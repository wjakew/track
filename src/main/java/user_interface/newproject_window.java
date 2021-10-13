/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package user_interface;

import com.google.gson.JsonElement;
import com.jakubwawak.track.connector.Connector;
import com.jakubwawak.track.connector.Project_Connector;
import com.mashape.unirest.http.exceptions.UnirestException;
import maintenence.Parser;

/**
 *
 * @author kubaw
 */
public class newproject_window extends javax.swing.JDialog {

    /**
     * Creates new form newproject_window
     */
    Connector connector;
    String data;
    public newproject_window(java.awt.Frame parent, boolean modal,Connector connector) {
        super(parent, modal);
        this.connector = connector;
        data = "active";
        initComponents();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Function for field validation
     * @return boolean
     */
    boolean validate_fields(){
        return !field_name.getText().equals("") && !field_desc.getText().equals("");
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
        field_name = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        field_desc = new javax.swing.JTextArea();
        button_create = new javax.swing.JButton();
        field_state = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New project");

        jLabel1.setText("Name:");

        jLabel2.setText("Description");

        field_desc.setColumns(20);
        field_desc.setRows(5);
        jScrollPane1.setViewportView(field_desc);

        button_create.setText("Create project");
        button_create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_createActionPerformed(evt);
            }
        });

        field_state.setText("active");

        jLabel3.setText("yyyy-MM-dd HH:mm / other state as text");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_name))
                            .addComponent(button_create, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(field_state, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(field_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(field_state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_create)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_createActionPerformed
        String name = field_name.getText();
        name = name.replaceAll(" ", "%20");
        String desc = field_desc.getText();
        desc = desc.replaceAll(" ", "%20");
        Project_Connector pc = new Project_Connector(connector);
        data = field_state.getText();
        try {
            data = data.replace(" ", "%20");
            JsonElement je = pc.load_project(name, desc, data,this);
            Parser parser = new Parser(je);
            if ( parser.get_int("flag") == 1){
                new message_window(this,true,"Project added","");
                dispose();
            }
            else if ( parser.get_int("flag") == -99){
                new login_window(this,true,connector,1);
            }
        } catch (UnirestException ex) {
            new message_window(this,true,"Error \n"+ex.toString(),"ERROR");
        }
        
    }//GEN-LAST:event_button_createActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_create;
    private javax.swing.JTextArea field_desc;
    private javax.swing.JTextField field_name;
    private javax.swing.JTextField field_state;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}