/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package user_interface;

import com.jakubwawak.track.connector.Connector;
import com.jakubwawak.track.connector.Snippet_Connector;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import maintenence.Parser;
import ui_components.snippetshare_window;

/**
 *Window for creating 
 * @author jakubwawak
 */
public class snippets_window extends javax.swing.JFrame {

    /**
     * Creates new form snippets_window
     */
    Connector connector;
    Snippet_Connector s_connector;
    int user_snippet_id;
    public snippets_window(Connector connector) throws UnirestException {
        initComponents();
        this.connector = connector;
        this.s_connector = new Snippet_Connector(this.connector);
        user_snippet_id = 0;
        this.setLocationRelativeTo(null);
        load_window();
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
     * Function for loading snippet list
     */
    void load_snippet_list() throws UnirestException{
        Parser parser = new Parser(s_connector.load_snippets(this));
        String [] snippets = {"Snippets"};
        DefaultTableModel dtm = new DefaultTableModel(snippets,0);
        List<String> snippets_data = parser.get_listlist("view");
        for(String object : snippets_data){
            dtm.addRow(new Object[]{object.split("\n")[0]});
        }
        table_snippets.setModel(dtm);
    }
    
    /**
     * Function for loading window content
     */
    void load_window() throws UnirestException{
        load_window_icon();
        Parser parser = new Parser(s_connector.load_snippets(this));
        String [] snippets = {"Snippets"};
        DefaultTableModel dtm = new DefaultTableModel(snippets,0);
        List<String> snippets_data = parser.get_listlist("view");
        for(String object : snippets_data){
            dtm.addRow(new Object[]{object.split("\n")[0]});
        }
        table_snippets.setModel(dtm);
        TableColumn column = table_snippets.getColumnModel().getColumn(0);
        
        column.setCellRenderer(new MyRenderer(Color.black,Color.yellow));
        // setting window components to start 
        label_title.setText("Title");
        field_snippet.setText("");
        label_owner.setText("owner:");
        label_updated.setText("update:");
        
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
        table_snippets = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        field_snippet = new javax.swing.JTextArea();
        label_title = new javax.swing.JLabel();
        label_owner = new javax.swing.JLabel();
        label_updated = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menu_createsnippet = new javax.swing.JMenuItem();
        menu_sharesnippet = new javax.swing.JMenuItem();
        menu_removesnippet = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Snippet manager");

        table_snippets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Snippets"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_snippets.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_snippetsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_snippets);
        if (table_snippets.getColumnModel().getColumnCount() > 0) {
            table_snippets.getColumnModel().getColumn(0).setResizable(false);
        }

        field_snippet.setColumns(20);
        field_snippet.setRows(5);
        jScrollPane2.setViewportView(field_snippet);

        label_title.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        label_title.setText("Title");

        label_owner.setText("owner:");

        label_updated.setText("updated:");

        jMenu1.setText("Actions");

        menu_createsnippet.setText("Create snippet");
        menu_createsnippet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_createsnippetActionPerformed(evt);
            }
        });
        jMenu1.add(menu_createsnippet);

        menu_sharesnippet.setText("Share snippet");
        menu_sharesnippet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_sharesnippetActionPerformed(evt);
            }
        });
        jMenu1.add(menu_sharesnippet);

        menu_removesnippet.setText("Remove snippet");
        menu_removesnippet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_removesnippetActionPerformed(evt);
            }
        });
        jMenu1.add(menu_removesnippet);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_owner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label_updated, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(label_title)
                        .addGap(5, 5, 5)
                        .addComponent(label_owner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label_updated))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void table_snippetsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_snippetsMouseClicked
        try{
            user_snippet_id = Integer.parseInt(table_snippets.getValueAt(table_snippets.getSelectedRow(), 0).toString().split(":")[0]);
            System.out.println("Selected user_snippet_id = "+user_snippet_id);
            
            Snippet_Connector sc = new Snippet_Connector(connector);
            Parser parser = new Parser(sc.get_snippet(user_snippet_id, this));
            if ( parser.get_int("flag") == 1 ){
                /**
                 * {"_glance":"1:test1\ntest2"
                    "user_snippet_id":1
                    "flag":1
                    "user_id":1
                    "user_snippet_title":"test1"
                    "user_snippet_time":"2021-11-30T14:02:09"
                    "user_snippet_content":"test2"}
                 */
                label_title.setText(parser.get_string("user_snippet_title"));
                field_snippet.setText(parser.get_string("user_snippet_content"));
                label_owner.setText("owner: (user_id) "+parser.get_int("user_id"));
                label_updated.setText(parser.get_string("user_snippet_time"));
            }
        }catch(Exception e){
            System.out.println("Error: "+e.toString());
        }
        
    }//GEN-LAST:event_table_snippetsMouseClicked

    private void menu_createsnippetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_createsnippetActionPerformed
        new snippetsadd_window(this,true,connector,0);
        try {
            load_snippet_list();
        } catch (UnirestException ex) {
            new message_window(this,true,"ERROR\n"+ex.toString(),"ERROR");
        }
    }//GEN-LAST:event_menu_createsnippetActionPerformed

    private void menu_removesnippetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_removesnippetActionPerformed
        if ( user_snippet_id != 0 ){
            Snippet_Connector sc = new Snippet_Connector(connector);
            Parser parser;
            try {
                parser = new Parser(sc.remove_snippets(user_snippet_id, this));
                if ( parser.get_int("flag") == 0){
                    new message_window(this,true,"Snippet removed.","");
                        try {
                            load_snippet_list();
                        } catch (UnirestException ex) {
                            new message_window(this,true,"ERROR\n"+ex.toString(),"ERROR");
                        }
            }
                else{
                    new message_window(this,true,"Error removing snippet","");
                }
            } catch (UnirestException ex) {
                new message_window(this,true,"Error removing snippet","");
            }
        }
    }//GEN-LAST:event_menu_removesnippetActionPerformed

    private void menu_sharesnippetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_sharesnippetActionPerformed
        if ( user_snippet_id != 0 ){
            System.out.println("Snippet selected user_snippet_id = "+user_snippet_id);
            new snippetshare_window(this,true,connector,user_snippet_id);
        }
    }//GEN-LAST:event_menu_sharesnippetActionPerformed

class MyRenderer extends DefaultTableCellRenderer {
   Color bg, fg;
   
   public MyRenderer(Color bg, Color fg) {
      super();
      this.bg = bg;
      this.fg = fg;
   }
   
   public Component getTableCellRendererComponent(JTable table, Object 
   value, boolean isSelected, boolean hasFocus, int row, int column) {
      Component cell = super.getTableCellRendererComponent(table, value, 
      isSelected, hasFocus, row, column);
      cell.setBackground(bg);
      cell.setForeground(fg);
      return cell;
   }
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea field_snippet;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label_owner;
    private javax.swing.JLabel label_title;
    private javax.swing.JLabel label_updated;
    private javax.swing.JMenuItem menu_createsnippet;
    private javax.swing.JMenuItem menu_removesnippet;
    private javax.swing.JMenuItem menu_sharesnippet;
    private javax.swing.JTable table_snippets;
    // End of variables declaration//GEN-END:variables
}
