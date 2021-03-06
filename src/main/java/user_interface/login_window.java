/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package user_interface;

import ui_components.forgetpassword_window;
import com.google.gson.JsonElement;
import com.jakubwawak.track.connector.Connector;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.security.NoSuchAlgorithmException;
import javax.swing.ImageIcon;
import maintenence.Parser;
import ui_components.twofactorauth_window;

/**
 *Window for loggin user
 * @author kubaw
 */
public class login_window extends javax.swing.JDialog {

    /**
     * Creates new form login_window
     */
    Connector connector;
    /**
     * mode = 0 - first run - start main window
     * mode = 1 - session extension
     */
    int mode;
    
    public login_window(java.awt.Frame parent, boolean modal,Connector connector,int mode){
        super(parent, modal);
        this.connector = connector;
        this.mode = mode;
        this.setTitle("Track Client "+this.connector.version+"/"+this.connector.bulid);
        initComponents();
        if(mode == 1){
            this.setTitle("Login window - Session expired");
        }

        this.setLocationRelativeTo(null);
        load_window_icon();
        setVisible(true);
    }
    
    public login_window(Connector connector,int mode) {
        this.connector = connector;
        this.mode = mode;
        this.setTitle("Track Client "+this.connector.version+"/"+this.connector.bulid);
        initComponents();
        this.setLocationRelativeTo(null);   
        load_window_icon();
        setVisible(true);
    }
    
    public login_window(javax.swing.JDialog parent, boolean modal,Connector connector,int mode) {
        super(parent, modal);
        this.connector = connector;
        this.mode = mode;
        this.setTitle("Track Client "+this.connector.version+"/"+this.connector.bulid);
        initComponents();
        this.setLocationRelativeTo(null);
        load_window_icon();
        setVisible(true);
    }
    
    /**
     * Function for loading window icon
     */
    void load_window_icon(){
        try{
            load_image();
            ImageIcon img = new ImageIcon("track_icon.png");
            this.setIconImage(img.getImage());
            label_serverinfo.setText(connector.api_information);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Function for loading image
     */
    void load_image(){
        try{
            ImageIcon imageIcon = new ImageIcon("track_icon.png");
            Image image = imageIcon.getImage();
            Image newimg = image.getScaledInstance(140, 140,  java.awt.Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newimg);
            label_icon.setIcon(imageIcon);
        }catch(Exception e){
            System.out.println("Failed to load track icon ("+e.toString()+")");
            label_icon.setText("Icon error");
        }
    }
    
    /**
     * Function for field validation
     * @return 
     */
    boolean validate_fields(){
        return !field_login.getText().equals("") && !field_password.getText().equals("");
    }
    
    /**
     * Function for login
     */
    void login(){
        if ( validate_fields() ){
            try {
                JsonElement response = connector.user_login(field_login.getText(),field_password.getText());
                if (response != null){
                    Parser parser = new Parser(response);
                    if ( parser.get_flag() == 1){
                        // user without 2fa auth
                        if(parser.get_int("user_id") == -11){
                            new message_window(this,true,"App token not found.","APPTOKEN_ERROR");
                        }
                        else if (parser.get_int("user_id") == -5){
                            new message_window(this,true,"Wrong password or login. Authorization failed.","AUTH_ERROR");
                        }
                        else if (parser.get_int("user_id") == -6){
                            new message_window(this,true,"Database error","DATABASE_ERROR");
                        }
                        else{
                            //login successfull
                            if (mode == 0){
                                new message_window(this,true,"Welcome back "+parser.get_string("user_name")+"!","welcome!");
                                // loading user_configuration
                                connector.get_user_configuration(this);
                                new main_window(connector);
                                dispose();
                            }
                            else{
                                dispose();
                            }
                        }
                    }
                    else if (parser.get_int("flag") == -69){
                        //2fa authorization
                        new twofactorauth_window(this,true,connector);
                        dispose();
                    }
                    else{
                        new message_window(this,true,"API returned unknown code ("+parser.get_int("flag")+"). Check API log","ERROR");
                    }
                }
                else{
                    new message_window(this,true,"API connection faled.\nResponse is null.","ERROR");
                    System.exit(0);
                }
            } catch (UnirestException ex) {
                new message_window(this,true,"ERROR\n"+ex.toString(),"ERROR");
            } catch (NoSuchAlgorithmException ex) {
                new message_window(this,true,"ERROR\n"+ex.toString(),"ERROR");
            }
        }
        else{
            new message_window(this,true,"Wrong login or password","ERROR");
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        field_login = new javax.swing.JTextField();
        button_login = new javax.swing.JButton();
        field_password = new javax.swing.JPasswordField();
        button_register = new javax.swing.JButton();
        button_reset = new javax.swing.JButton();
        label_serverinfo = new javax.swing.JLabel();
        label_icon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Track Client");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Login:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Password:");

        field_login.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        button_login.setText("Login");
        button_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_loginActionPerformed(evt);
            }
        });

        field_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                field_passwordKeyPressed(evt);
            }
        });

        button_register.setText("Register");
        button_register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_registerActionPerformed(evt);
            }
        });

        button_reset.setText("Reset password");
        button_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_resetActionPerformed(evt);
            }
        });

        label_serverinfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_serverinfo.setText("vX.X.X/XXXXXXXXXX/XXXXXXXXXXX");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_serverinfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button_login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_register, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_password, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(field_login, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label_icon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_icon, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_serverinfo)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_login, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_password, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_login)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_register)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button_reset)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_loginActionPerformed
       login(); 
       
    }//GEN-LAST:event_button_loginActionPerformed

    private void button_registerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_registerActionPerformed
        new register_window(this,true,connector);
    }//GEN-LAST:event_button_registerActionPerformed

    private void field_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_field_passwordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            login();
        }
    }//GEN-LAST:event_field_passwordKeyPressed

    private void button_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_resetActionPerformed
        new forgetpassword_window(this,true,connector);
    }//GEN-LAST:event_button_resetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_login;
    private javax.swing.JButton button_register;
    private javax.swing.JButton button_reset;
    private javax.swing.JTextField field_login;
    private javax.swing.JPasswordField field_password;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel label_icon;
    private javax.swing.JLabel label_serverinfo;
    // End of variables declaration//GEN-END:variables
}
