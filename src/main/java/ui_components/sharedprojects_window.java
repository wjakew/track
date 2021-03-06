/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package ui_components;

import com.jakubwawak.track.connector.Connector;
import com.jakubwawak.track.connector.Share_Connector;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import maintenence.Parser;
import user_interface.message_window;

/**
 *Window for showing shared elements
 * @author kubaw
 */
public class sharedprojects_window extends javax.swing.JDialog {

    /**
     * Creates new form sharedprojects_window
     */
    Connector connector;
    public sharedprojects_window(java.awt.Frame parent, boolean modal,Connector connector) throws UnirestException {
        super(parent, modal);
        this.connector = connector;
        initComponents();
        this.setLocationRelativeTo(null);
        load_window();
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
     * Function for loading window components
     */
    void load_window() throws UnirestException{
        DefaultListModel dlm = new DefaultListModel();
        Share_Connector sh = new Share_Connector(connector);
        Parser parser = new Parser(sh.show_myshared_projects(this));
        addAll(dlm,parser.get_arraylist("view"));
        list_shares.setModel(dlm);
    }
    
    /**
     * Function for adding all data to the view
     * @param dlm
     * @param data 
     */
    void addAll(DefaultListModel dlm, ArrayList<String> data){
        for (String element : data){
            dlm.addElement(element);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        list_shares = new javax.swing.JList<>();
        button_cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("My shares");

        list_shares.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(list_shares);

        button_cancel.setText("Cancel sharing");
        button_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_cancelActionPerformed(evt);
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
                    .addComponent(button_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_cancel)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_cancelActionPerformed
        Share_Connector sh = new Share_Connector(connector);
        try{
            int project_id = Integer.parseInt(list_shares.getSelectedValue().toString().split(":")[0]);
            Parser parser = new Parser(sh.remove_share(project_id, this));
            if ( parser.get_int("flag") == 1){
                new message_window(this,true,"Shared removed","");
                load_window();
            }
            else{
                new message_window(this,true,"Share failed to remove. Check log","ERROR");
            }
        }catch(Exception e){}
    }//GEN-LAST:event_button_cancelActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_cancel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> list_shares;
    // End of variables declaration//GEN-END:variables
}
