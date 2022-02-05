/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package user_interface;

import com.jakubwawak.track.connector.Connector;
import com.jakubwawak.track.connector.Issue_Connector;
import com.jakubwawak.track.connector.Project_Connector;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import maintenence.Parser;

/**
 *
 * @author kubaw
 */
public class newissue_window extends javax.swing.JDialog {

    /**
     * Creates new form newissue_window
     */
    Connector connector;
    int project_id;
    public newissue_window(java.awt.Frame parent, boolean modal,Connector connector) throws UnirestException {
        super(parent, modal);
        this.connector = connector;
        project_id = -1;
        initComponents();
        load_projects();
        this.setLocationRelativeTo(null);
        load_window_icon();
        setVisible(true);
    }
    
    public newissue_window(javax.swing.JDialog parent, boolean modal,Connector connector,int project_id) throws UnirestException {
        super(parent, modal);
        this.connector = connector;
        this.project_id = project_id;
        initComponents();
        load_projects();
        this.setLocationRelativeTo(null);
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
     * Function for loading projects
     */
    void load_projects() throws UnirestException{
        DefaultComboBoxModel dcm = new DefaultComboBoxModel();
        Project_Connector pc = new Project_Connector(connector);
        Parser parser = new Parser(pc.load_projects_glances(this));
        ArrayList<String> data = parser.get_arraylist("view");
        dcm.addAll(parser.get_arraylist("view"));
        combobox_projects.setModel(dcm);
        combobox_projects.setSelectedIndex(0);
        int index = -1;
        
        if ( project_id != -1 ){
            for(String element : data){
                if(element.contains(Integer.toString(project_id))){
                    index = data.indexOf(index);
                    break;
                }
            }
        }
        
        if ( index != -1 ){
            dcm.removeAllElements();
            dcm.addElement(Integer.toString(project_id)+":");
            combobox_projects.setEnabled(false);
        }
    }
    
    /**
     * Function for validating fields
     * @return boolean
     */
    boolean validate_fields(){
        return !field_name.getText().equals("") && !field_desc.getText().equals("");
    }
    
    /**
     * Function for validating field with date
     * @return Integer
     * return codes:
     * 1 - field contains yyyy-MM-dd HH:mm
     * 2 - field contains correct date
     * -1 - field is containing non parasable string
     */
    int validate_date(){
        if ( field_date.getText().equals("yyyy-MM-dd HH:mm")){
            return 1;
        }
        else{
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(field_date.getText(), formatter);
                return 2;
            }catch(Exception e){
                return -1;
            }
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

        field_name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        field_desc = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        combobox_projects = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        combobox_priority = new javax.swing.JComboBox<>();
        field_date = new javax.swing.JTextField();
        button_create = new javax.swing.JButton();
        button_reset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New issue");

        jLabel1.setText("Title:");

        field_desc.setColumns(20);
        field_desc.setRows(5);
        jScrollPane1.setViewportView(field_desc);

        jLabel2.setText("For project:");

        combobox_projects.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Description");

        jLabel4.setText("Priority");

        combobox_priority.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        field_date.setText("yyyy-MM-dd HH:mm");

        button_create.setText("Create issue");
        button_create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_createActionPerformed(evt);
            }
        });

        button_reset.setText("Reset");
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
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combobox_projects, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(field_name)))))
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combobox_priority, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(field_date, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                    .addComponent(button_create, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_reset)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(combobox_projects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(combobox_priority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(field_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_reset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_create)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_createActionPerformed
        if ( validate_fields()){
            int project_id = Integer.parseInt(combobox_projects.getSelectedItem().toString().split(":")[0]);
            String issue_name = field_name.getText();
            String issue_desc = field_desc.getText();
            int priority = Integer.parseInt(combobox_priority.getSelectedItem().toString());
            Issue_Connector ic = new Issue_Connector(connector);
            try {
                switch(validate_date()){
                    case 1:
                        Parser parse = new Parser(ic.issue_set(issue_name, issue_desc, priority, project_id, "blank", this));
                        if ( parse.get_int("flag") == 1 ){
                            new message_window(this,true,"Issue "+parse.get_string("issue_name")+" added","");
                            dispose();
                        }
                        break;
                    case 2:
                        Parser parse2 = new Parser(ic.issue_set(issue_name, issue_desc, priority, project_id, field_date.getText(), this));
                        new message_window(this,true,"Issue "+parse2.get_string("issue_name")+"added","");
                        dispose();
                        break;
                    default:
                        new message_window(this,true,"Error parsing date","");
                        break;
                }
            } catch (UnirestException ex) {
                new message_window(this,true,"Error\n"+ex.toString(),"");
            }
        }
        else{
            new message_window(this,true,"Error loading fields. Check it","");
        }
        
    }//GEN-LAST:event_button_createActionPerformed

    private void button_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_resetActionPerformed
        field_date.setText("yyyy-MM-dd HH:mm");
    }//GEN-LAST:event_button_resetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_create;
    private javax.swing.JButton button_reset;
    private javax.swing.JComboBox<String> combobox_priority;
    private javax.swing.JComboBox<String> combobox_projects;
    private javax.swing.JTextField field_date;
    private javax.swing.JTextArea field_desc;
    private javax.swing.JTextField field_name;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
