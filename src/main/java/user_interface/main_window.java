/*
Jakub Wawak
kubawawak@gmail.com
all rights reseved
 */
package user_interface;

import com.jakubwawak.track.connector.Board_Connector;
import ui_components.passwordcheck_window;
import com.jakubwawak.track.connector.Connector;
import com.jakubwawak.track.connector.Issue_Connector;
import com.jakubwawak.track.connector.Project_Connector;
import com.jakubwawak.track.connector.Task_Connector;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.ListModel;
import maintenence.Parser;
import ui_components.archive_window;
import ui_components.boardview_window;
import ui_components.changepassword_window;
import ui_components.configuration_window;
import ui_components.createroom_window;
import ui_components.debug_window;
import ui_components.issuedetails_window;
import ui_components.newboard_window;
import ui_components.projectdetails_window;
import ui_components.roommanager_window;
import ui_components.setemail_window;
import ui_components.sharedprojects_window;
import ui_components.sharedtouser_window;
import ui_components.shareproject_window;
import ui_components.taskdetails_window;

/**
 *Main window for Track
 * @author Jakub Wawak
 */
public class main_window extends javax.swing.JFrame {

    /**
     * Creates new form main_window
     */
    Connector connector;
    int project_id;
    
    public main_window(Connector connector) throws UnirestException {
        initComponents();
        this.connector = connector;
        project_id = -1;
        this.setLocationRelativeTo(null);
        load_main_components();
        this.setTitle("Track Client "+connector.version);
        setVisible(true);
    }
    /**
     * Function for checking if any project is in app
     */
    void check_start(){
        ListModel<String> lm = list_projects.getModel();
        
        if ( lm.getElementAt(0).equals("Empty")){
            menu_task.setEnabled(false);
            menu_issue.setEnabled(false);
            menu_archive.setEnabled(false);
        }
        else{
            menu_task.setEnabled(true);
            menu_issue.setEnabled(true);
            menu_archive.setEnabled(true);
        }
            
    }
    /**
     * Function for loading main components
     */
    void load_main_components() throws UnirestException{
        button_projectsource.setText("My projects");
        menu_user_session.setText(connector.oauth.session_token);
        menu_user_session.setEnabled(false);
        menu_user_time.setText(connector.oauth.apr_login_time.toString());
        menu_user_time.setEnabled(false);
        menu_server_ip.setText("Connected to: "+connector.oauth.server_ip);
        menu_server_ip.setEnabled(false);
        label_build.setText(connector.bulid);
        load_projects_list();
        load_tasks_list(0);
        load_issues_list(0);
        load_boards_list();
        load_window_icon();
        check_start();
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
     * Function for loading project list
     */
    void load_projects_list() throws UnirestException{
        switch(button_projectsource.getText()){
            case "My projects":
            {
                Project_Connector pc = new Project_Connector(connector);
                Parser p = new Parser(pc.load_projects_glances(this));
                DefaultListModel dlm = new DefaultListModel();
                dlm.addAll(p.get_arraylist("view"));
                list_projects.setModel(dlm);
                break;
            }
            case "Member projects":
            {
                Project_Connector pc = new Project_Connector(connector);
                Parser p = new Parser(pc.load_projects_glances_member(this));
                DefaultListModel dlm = new DefaultListModel();
                dlm.addAll(p.get_arraylist("view"));
                list_projects.setModel(dlm);
                break;
            }
        }
    }
    
    /**
     * Function for loading tasks list
     * @throws UnirestException 
     */
    void load_tasks_list(int mode) throws UnirestException{
        if (mode != 0){
            label_tasks.setText("Tasks for project_id "+mode);
        }
        else{
            label_tasks.setText("Tasks");
        }
        Task_Connector tc = new Task_Connector(connector);
        Parser p = new Parser(tc.load_task_glances(mode, this));
        DefaultListModel dlm = new DefaultListModel();
        dlm.addAll(p.get_arraylist("view"));
        list_tasks.setModel(dlm);
    }
    /**
     * Function for loading issues list
     * @param mode 
     */
    void load_issues_list(int mode) throws UnirestException{
        if ( mode!= 0){
            label_issues.setText("Issues for project_id "+mode);
        }
        else{
            label_issues.setText("Issues");
        }
        Issue_Connector ic = new Issue_Connector(connector);
        Parser p = new Parser(ic.load_issues_glances(mode, this));
        DefaultListModel dlm = new DefaultListModel();
        dlm.addAll(p.get_arraylist("view"));
        list_issues.setModel(dlm);
    }
    
    /**
     * Function for loading board list
     * @throws UnirestException 
     */
    void load_boards_list() throws UnirestException{
        Board_Connector bc = new Board_Connector(connector);
        Parser parser = new Parser(bc.get_board_glances(this));
        DefaultListModel dlm = new DefaultListModel();
        dlm.addAll(parser.get_arraylist("view"));
        list_boards.setModel(dlm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        list_projects = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        list_tasks = new javax.swing.JList<>();
        label_tasks = new javax.swing.JLabel();
        button_loadall = new javax.swing.JButton();
        button_projectdetails = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        list_issues = new javax.swing.JList<>();
        label_issues = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        list_boards = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        button_boardview = new javax.swing.JButton();
        button_newboard = new javax.swing.JButton();
        button_snippets = new javax.swing.JButton();
        button_projectsource = new javax.swing.JButton();
        label_build = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_actions = new javax.swing.JMenu();
        menu_reload_window = new javax.swing.JMenuItem();
        menu_log_out = new javax.swing.JMenuItem();
        menu_about = new javax.swing.JMenuItem();
        menu_project = new javax.swing.JMenu();
        menu_new_project = new javax.swing.JMenuItem();
        menu_remove_project = new javax.swing.JMenuItem();
        menu_task = new javax.swing.JMenu();
        menu_createnewtask = new javax.swing.JMenuItem();
        menu_removetask = new javax.swing.JMenuItem();
        menu_taskdetails = new javax.swing.JMenuItem();
        menu_addtoboard = new javax.swing.JMenuItem();
        menu_issue = new javax.swing.JMenu();
        menu_newissue = new javax.swing.JMenuItem();
        menu_removeissue = new javax.swing.JMenuItem();
        menu_showdetails = new javax.swing.JMenuItem();
        menu_issueaddtoboard = new javax.swing.JMenuItem();
        menu_archive = new javax.swing.JMenu();
        menu_archivedtasks = new javax.swing.JMenuItem();
        menu_archivedissues = new javax.swing.JMenuItem();
        menu_shared = new javax.swing.JMenu();
        menu_shareprojects = new javax.swing.JMenuItem();
        menu_sharedprojects = new javax.swing.JMenuItem();
        menu_sharedtome = new javax.swing.JMenuItem();
        menu_board = new javax.swing.JMenu();
        menu_removeboard = new javax.swing.JMenuItem();
        menu_myprofile = new javax.swing.JMenu();
        menu_check_password = new javax.swing.JMenuItem();
        menu_change_password = new javax.swing.JMenuItem();
        menu_set_emailaddress = new javax.swing.JMenuItem();
        menu_user_configuration = new javax.swing.JMenuItem();
        menu_todo = new javax.swing.JMenu();
        menu_todo_showlist = new javax.swing.JMenuItem();
        menu_todo_quickadd = new javax.swing.JMenuItem();
        menu_rooms = new javax.swing.JMenu();
        menu_roommanager = new javax.swing.JMenuItem();
        menu_messages = new javax.swing.JMenuItem();
        menu_information = new javax.swing.JMenu();
        menu_user_session = new javax.swing.JMenuItem();
        menu_user_time = new javax.swing.JMenuItem();
        menu_server_ip = new javax.swing.JMenuItem();
        menu_reload_session = new javax.swing.JMenuItem();
        menu_debug = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Track Client");

        list_projects.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        list_projects.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                list_projectsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(list_projects);

        list_tasks.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(list_tasks);

        label_tasks.setText("Tasks");

        button_loadall.setText("Load all");
        button_loadall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_loadallActionPerformed(evt);
            }
        });

        button_projectdetails.setText("Project Details");
        button_projectdetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_projectdetailsActionPerformed(evt);
            }
        });

        list_issues.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(list_issues);

        label_issues.setText("Issues");

        list_boards.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(list_boards);

        jLabel2.setText("My boards");

        button_boardview.setText("Board view");
        button_boardview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_boardviewActionPerformed(evt);
            }
        });

        button_newboard.setText("New...");
        button_newboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_newboardActionPerformed(evt);
            }
        });

        button_snippets.setText("Snippets");
        button_snippets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_snippetsActionPerformed(evt);
            }
        });

        button_projectsource.setText("My projects/Member projects");
        button_projectsource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_projectsourceActionPerformed(evt);
            }
        });

        label_build.setText("build");

        menu_actions.setText("Track");

        menu_reload_window.setText("Reload window");
        menu_reload_window.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_reload_windowActionPerformed(evt);
            }
        });
        menu_actions.add(menu_reload_window);

        menu_log_out.setText("Log out");
        menu_log_out.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_log_outActionPerformed(evt);
            }
        });
        menu_actions.add(menu_log_out);

        menu_about.setText("About");
        menu_about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_aboutActionPerformed(evt);
            }
        });
        menu_actions.add(menu_about);

        jMenuBar1.add(menu_actions);

        menu_project.setText("Project");

        menu_new_project.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menu_new_project.setText("New project");
        menu_new_project.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_new_projectActionPerformed(evt);
            }
        });
        menu_project.add(menu_new_project);

        menu_remove_project.setText("Remove project");
        menu_remove_project.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_remove_projectActionPerformed(evt);
            }
        });
        menu_project.add(menu_remove_project);

        jMenuBar1.add(menu_project);

        menu_task.setText("Task");

        menu_createnewtask.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menu_createnewtask.setText("Create new task");
        menu_createnewtask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_createnewtaskActionPerformed(evt);
            }
        });
        menu_task.add(menu_createnewtask);

        menu_removetask.setText("Remove task");
        menu_removetask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_removetaskActionPerformed(evt);
            }
        });
        menu_task.add(menu_removetask);

        menu_taskdetails.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menu_taskdetails.setText("Show details");
        menu_taskdetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_taskdetailsActionPerformed(evt);
            }
        });
        menu_task.add(menu_taskdetails);

        menu_addtoboard.setText("Add to board");
        menu_addtoboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_addtoboardActionPerformed(evt);
            }
        });
        menu_task.add(menu_addtoboard);

        jMenuBar1.add(menu_task);

        menu_issue.setText("Issue");

        menu_newissue.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menu_newissue.setText("Create new issue");
        menu_newissue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_newissueActionPerformed(evt);
            }
        });
        menu_issue.add(menu_newissue);

        menu_removeissue.setText("Remove issue");
        menu_removeissue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_removeissueActionPerformed(evt);
            }
        });
        menu_issue.add(menu_removeissue);

        menu_showdetails.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menu_showdetails.setText("Show details");
        menu_showdetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_showdetailsActionPerformed(evt);
            }
        });
        menu_issue.add(menu_showdetails);

        menu_issueaddtoboard.setText("Add to board");
        menu_issueaddtoboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_issueaddtoboardActionPerformed(evt);
            }
        });
        menu_issue.add(menu_issueaddtoboard);

        jMenuBar1.add(menu_issue);

        menu_archive.setText("Archive");

        menu_archivedtasks.setText("Archived tasks");
        menu_archivedtasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_archivedtasksActionPerformed(evt);
            }
        });
        menu_archive.add(menu_archivedtasks);

        menu_archivedissues.setText("Archived issues");
        menu_archivedissues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_archivedissuesActionPerformed(evt);
            }
        });
        menu_archive.add(menu_archivedissues);

        jMenuBar1.add(menu_archive);

        menu_shared.setText("Shared");

        menu_shareprojects.setText("Share project");
        menu_shareprojects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_shareprojectsActionPerformed(evt);
            }
        });
        menu_shared.add(menu_shareprojects);

        menu_sharedprojects.setText("My shared projects");
        menu_sharedprojects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_sharedprojectsActionPerformed(evt);
            }
        });
        menu_shared.add(menu_sharedprojects);

        menu_sharedtome.setText("Shared to me");
        menu_sharedtome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_sharedtomeActionPerformed(evt);
            }
        });
        menu_shared.add(menu_sharedtome);

        jMenuBar1.add(menu_shared);

        menu_board.setText("Board");

        menu_removeboard.setText("Remove board");
        menu_removeboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_removeboardActionPerformed(evt);
            }
        });
        menu_board.add(menu_removeboard);

        jMenuBar1.add(menu_board);

        menu_myprofile.setText("My Profile");

        menu_check_password.setText("Check password");
        menu_check_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_check_passwordActionPerformed(evt);
            }
        });
        menu_myprofile.add(menu_check_password);

        menu_change_password.setText("Change password");
        menu_change_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_change_passwordActionPerformed(evt);
            }
        });
        menu_myprofile.add(menu_change_password);

        menu_set_emailaddress.setText("Set email address");
        menu_set_emailaddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_set_emailaddressActionPerformed(evt);
            }
        });
        menu_myprofile.add(menu_set_emailaddress);

        menu_user_configuration.setText("User configuration");
        menu_user_configuration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_user_configurationActionPerformed(evt);
            }
        });
        menu_myprofile.add(menu_user_configuration);

        jMenuBar1.add(menu_myprofile);

        menu_todo.setText("ToDo");
        menu_todo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_todoMouseClicked(evt);
            }
        });

        menu_todo_showlist.setText("Show list");
        menu_todo_showlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_todo_showlistActionPerformed(evt);
            }
        });
        menu_todo.add(menu_todo_showlist);

        menu_todo_quickadd.setText("Quick add");
        menu_todo_quickadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_todo_quickaddActionPerformed(evt);
            }
        });
        menu_todo.add(menu_todo_quickadd);

        jMenuBar1.add(menu_todo);

        menu_rooms.setText("Rooms");

        menu_roommanager.setText("Room manager");
        menu_roommanager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_roommanagerActionPerformed(evt);
            }
        });
        menu_rooms.add(menu_roommanager);

        menu_messages.setText("Messages");
        menu_messages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_messagesActionPerformed(evt);
            }
        });
        menu_rooms.add(menu_messages);

        jMenuBar1.add(menu_rooms);

        menu_information.setText("Information");

        menu_user_session.setText("user_session");
        menu_information.add(menu_user_session);

        menu_user_time.setText("user_time");
        menu_information.add(menu_user_time);

        menu_server_ip.setText("server_ip");
        menu_information.add(menu_server_ip);

        menu_reload_session.setText("Reload session");
        menu_reload_session.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_reload_sessionActionPerformed(evt);
            }
        });
        menu_information.add(menu_reload_session);

        menu_debug.setText("Debug");
        menu_debug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_debugActionPerformed(evt);
            }
        });
        menu_information.add(menu_debug);

        jMenuBar1.add(menu_information);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_build, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(label_tasks)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label_issues)
                        .addGap(59, 59, 59)
                        .addComponent(button_loadall)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_newboard, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addComponent(button_projectdetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addComponent(button_boardview, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addComponent(button_snippets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_projectsource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_loadall)
                    .addComponent(label_issues)
                    .addComponent(button_projectsource)
                    .addComponent(label_tasks)
                    .addComponent(label_build))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(button_projectdetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_snippets)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(button_newboard))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_boardview, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_log_outActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_log_outActionPerformed
        new message_window(this,true,"User logged out","");
        dispose();
        new login_window(connector,0);
    }//GEN-LAST:event_menu_log_outActionPerformed

    private void menu_reload_sessionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_reload_sessionActionPerformed
        new login_window(this,true,connector,1);
        try {
            load_main_components();
        } catch (UnirestException ex) {
            new message_window(this,true,"Error \n"+ex.toString(),"ERROR");
        }
        try {
            load_main_components();
        } catch (UnirestException ex) {
            new message_window(this,true,"Error \n"+ex.toString(),"ERROR");
        }
    }//GEN-LAST:event_menu_reload_sessionActionPerformed

    private void menu_check_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_check_passwordActionPerformed
        new passwordcheck_window(this,true,connector);
    }//GEN-LAST:event_menu_check_passwordActionPerformed

    private void menu_new_projectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_new_projectActionPerformed
        new newproject_window(this,true,connector);
        try {
            load_main_components();
        } catch (UnirestException ex) {
            new message_window(this,true,"Error loading projects\n"+ex.toString(),"ERROR");
        }
    }//GEN-LAST:event_menu_new_projectActionPerformed

    private void menu_reload_windowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_reload_windowActionPerformed
        try {
            load_main_components();
        } catch (UnirestException ex) {
            new message_window(this,true,"Error loading projects\n"+ex.toString(),"ERROR");
        }
    }//GEN-LAST:event_menu_reload_windowActionPerformed

    private void list_projectsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list_projectsMouseClicked
        try{
            project_id = Integer.parseInt(list_projects.getSelectedValue().toString().split(":")[0]);
            System.out.println("project_id: "+project_id);
            load_tasks_list(project_id);
            load_issues_list(project_id);
        }catch(Exception e){}
    }//GEN-LAST:event_list_projectsMouseClicked

    private void menu_remove_projectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_remove_projectActionPerformed
        try{
            if ( project_id > 0){
                Project_Connector pc = new Project_Connector(connector);
                Parser p = new Parser(pc.remove_project(project_id, this));
                if ( p.get_int("flag") == 1 ){
                    new message_window(this,true,"Project project_id "+project_id+" removed.","");
                    load_main_components();
                }
                else{
                    new message_window(this,true,"Error deleting project","");
                }
            }
            else{
                System.out.println("project_id < 0");
            }
        }catch(Exception e){
            new message_window(this,true,"Project not set\n"+e.toString(),"");
        }
    }//GEN-LAST:event_menu_remove_projectActionPerformed

    private void menu_createnewtaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_createnewtaskActionPerformed
        try {
            new newtask_window(this,true,connector);
            load_tasks_list(0);
        } catch (UnirestException ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"");
        }
    }//GEN-LAST:event_menu_createnewtaskActionPerformed

    private void menu_debugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_debugActionPerformed
        new debug_window(this,true,connector);
    }//GEN-LAST:event_menu_debugActionPerformed

    private void menu_change_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_change_passwordActionPerformed
        new changepassword_window(this,true,connector);
    }//GEN-LAST:event_menu_change_passwordActionPerformed

    private void button_loadallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_loadallActionPerformed
        try {
            load_tasks_list(0);
            load_issues_list(0);
        } catch (UnirestException ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
        }
    }//GEN-LAST:event_button_loadallActionPerformed

    private void menu_removetaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_removetaskActionPerformed
        Task_Connector tc = new Task_Connector(connector);
        try{
            int task_id = Integer.parseInt(list_tasks.getSelectedValue().toString().split(":")[0]);
            Parser parser = new Parser(tc.remove_task(task_id, this));
            if ( parser.get_int("flag") == 1){
                new message_window(this,true,"Task removed","");
                load_tasks_list(0);
            }
            else{
                new message_window(this,true,"Failed to remove task","");
            }
        }catch(Exception e){
            new message_window(this,true,"No task selected to remove","");
        }
        
        
    }//GEN-LAST:event_menu_removetaskActionPerformed

    private void button_projectdetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_projectdetailsActionPerformed
        try{
            int project_id = Integer.parseInt(list_projects.getSelectedValue().split(":")[0]);
            new projectdetails_window(this,true,connector,project_id);
        }catch(Exception e){
            System.out.println("Error: "+e.toString());
        }
        
    }//GEN-LAST:event_button_projectdetailsActionPerformed

    private void menu_newissueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_newissueActionPerformed
        try {
            new newissue_window(this,true,connector);
            load_issues_list(0);
        } catch (UnirestException ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
        }
    }//GEN-LAST:event_menu_newissueActionPerformed

    private void menu_removeissueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_removeissueActionPerformed
        try{
            int issue_id = Integer.parseInt(list_issues.getSelectedValue().toString().split(":")[0]);
            Issue_Connector ic = new Issue_Connector(connector);
            Parser parser = new Parser(ic.issue_remove(issue_id, this));
            if ( parser.get_int("flag") == 1){
                new message_window(this,true,"Issue removed","");
                load_issues_list(0);
            }
            else{
                new message_window(this,true,"Issue remove error","");
            }
        }catch(Exception e){}
    }//GEN-LAST:event_menu_removeissueActionPerformed

    private void menu_taskdetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_taskdetailsActionPerformed
        try{
            int task_id = Integer.parseInt(list_tasks.getSelectedValue().toString().split(":")[0]);
            new taskdetails_window(this,true,connector,task_id);
            load_tasks_list(0);
        }catch(Exception e){}
    }//GEN-LAST:event_menu_taskdetailsActionPerformed

    private void menu_archivedtasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_archivedtasksActionPerformed
        try {
            new archive_window(this,true,connector,1);
            load_tasks_list(0);
            load_issues_list(0);
        } catch (UnirestException ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"");
        }
    }//GEN-LAST:event_menu_archivedtasksActionPerformed

    private void menu_showdetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_showdetailsActionPerformed
        try{
            int issue_id = Integer.parseInt(list_issues.getSelectedValue().toString().split(":")[0]);
            new issuedetails_window(this,true,connector,issue_id);
            load_issues_list(0);
            load_tasks_list(0);
        } catch (Exception ex) {}
    }//GEN-LAST:event_menu_showdetailsActionPerformed

    private void menu_archivedissuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_archivedissuesActionPerformed
        try{
            new archive_window(this,true,connector,2);
            load_tasks_list(0);
            load_issues_list(0);
        }catch(UnirestException ex){
            new message_window(this,true,"Error\n"+ex.toString(),"");
        }
    }//GEN-LAST:event_menu_archivedissuesActionPerformed

    private void button_newboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_newboardActionPerformed
        try {
            new newboard_window(this,true,connector);
            load_main_components();
        } catch (UnirestException ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"");
        }
    }//GEN-LAST:event_button_newboardActionPerformed

    private void menu_removeboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_removeboardActionPerformed
        try{
            int board_id = Integer.parseInt(list_boards.getSelectedValue().toString().split(":")[0]);
            Board_Connector bc = new Board_Connector(connector);
            Parser parser = new Parser(bc.board_remove(board_id, this));
            if ( parser.get_int("flag") == 1){
                new message_window(this,true,"Board removed","");
                load_main_components();
            }
            else{
                new message_window(this,true,"Error removing board","");
            }
        }catch(Exception e){
            new message_window(this,true,"Error\n"+e.toString(),"ERROR");
        }
    }//GEN-LAST:event_menu_removeboardActionPerformed

    private void menu_issueaddtoboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_issueaddtoboardActionPerformed
        int issue_id = -1;
        int board_id = -1;
        try{
            issue_id = Integer.parseInt(list_issues.getSelectedValue().toString().split(":")[0]);
        }catch(Exception e){
            new message_window(this,true,"No issue selected","");
        }
        try{
            board_id = Integer.parseInt(list_boards.getSelectedValue().toString().split(":")[0]);
        }catch(Exception e){
            new message_window(this,true,"No board selected","");
        }
        
        if (issue_id != -1 && board_id != -1){
            Board_Connector bc = new Board_Connector(connector);
            try {
                Parser parser = new Parser(bc.board_addelement_issue(board_id, issue_id, this));
                if ( parser.get_int("flag") == 1){
                    new message_window(this,true,"Issue "+issue_id+" added to board "+board_id,"");
                }
                else if ( parser.get_int("flag") == 2){
                    new message_window(this,true,"Issue already on board","");
                }
                else{
                    new message_window(this,true,"Error loading issue to database","");
                }
            } catch (UnirestException ex) {
                new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
            }
        }
    }//GEN-LAST:event_menu_issueaddtoboardActionPerformed

    private void button_boardviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_boardviewActionPerformed
        try{
            int board_id = Integer.parseInt(list_boards.getSelectedValue().toString().split(":")[0]);
            new boardview_window(this,true,connector,board_id);
            load_main_components();
        }catch(Exception e){}
    }//GEN-LAST:event_button_boardviewActionPerformed

    private void menu_addtoboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_addtoboardActionPerformed
        int task_id = -1;
        int board_id = -1;
        try{
            task_id = Integer.parseInt(list_tasks.getSelectedValue().toString().split(":")[0]);
        }catch(Exception e){
            new message_window(this,true,"No task selected","");
        }
        try{
            board_id = Integer.parseInt(list_boards.getSelectedValue().toString().split(":")[0]);
        }catch(Exception e){
            new message_window(this,true,"No board selected","");
        }
        
        if (task_id != -1 && board_id != -1){
            Board_Connector bc = new Board_Connector(connector);
            try {
                Parser parser = new Parser(bc.board_addelement_task(board_id, task_id, this));
                if ( parser.get_int("flag") == 1){
                    new message_window(this,true,"Task "+task_id+" added to board "+board_id,"");
                }
                else if ( parser.get_int("flag") == 2){
                    new message_window(this,true,"Task already on board","");
                }
                else{
                    new message_window(this,true,"Error loading task to database","");
                }
            } catch (UnirestException ex) {
                new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
            }
        }
    }//GEN-LAST:event_menu_addtoboardActionPerformed

    private void menu_shareprojectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_shareprojectsActionPerformed
        try {
            new shareproject_window(this,true,connector);
        } catch (UnirestException ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"ERROR"); 
        }
    }//GEN-LAST:event_menu_shareprojectsActionPerformed

    private void menu_sharedprojectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_sharedprojectsActionPerformed
        try {
            new sharedprojects_window(this,true,connector);
        } catch (UnirestException ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"ERROR"); 
        }
    }//GEN-LAST:event_menu_sharedprojectsActionPerformed

    private void menu_set_emailaddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_set_emailaddressActionPerformed
        new setemail_window(this,true,connector);
    }//GEN-LAST:event_menu_set_emailaddressActionPerformed

    private void menu_sharedtomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_sharedtomeActionPerformed
        try {
            new sharedtouser_window(this,true,connector);
        } catch (UnirestException ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"ERROR"); 
        }
    }//GEN-LAST:event_menu_sharedtomeActionPerformed

    private void menu_user_configurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_user_configurationActionPerformed
        new configuration_window(this,true,connector);
    }//GEN-LAST:event_menu_user_configurationActionPerformed

    private void button_snippetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_snippetsActionPerformed
        try {
            new snippets_window(connector);
        } catch (UnirestException ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
        }
    }//GEN-LAST:event_button_snippetsActionPerformed

    private void menu_todoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_todoMouseClicked

    }//GEN-LAST:event_menu_todoMouseClicked

    private void button_projectsourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_projectsourceActionPerformed
        if ( button_projectsource.getText().equals("My projects") ){
            button_projectsource.setText("Member projects");
            try {
                load_projects_list();
            } catch (UnirestException ex) {
                new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
            }
        }
        else{
            button_projectsource.setText("My projects");
            try {
                load_projects_list();
            } catch (UnirestException ex) {
                new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
            }
        }
    }//GEN-LAST:event_button_projectsourceActionPerformed

    private void menu_todo_showlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_todo_showlistActionPerformed
        try {
            new todo_window(connector,connector.oauth.user_id,this);
        } catch (UnirestException ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
        }
    }//GEN-LAST:event_menu_todo_showlistActionPerformed

    private void menu_todo_quickaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_todo_quickaddActionPerformed
        new todoadd_window(this,true,connector);
    }//GEN-LAST:event_menu_todo_quickaddActionPerformed

    private void menu_roommanagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_roommanagerActionPerformed
        try {
            new roommanager_window(connector);
        } catch (UnirestException ex) {
            new message_window(this,true,"Error\n"+ex.toString(),"ERROR");
        }
    }//GEN-LAST:event_menu_roommanagerActionPerformed

    private void menu_messagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_messagesActionPerformed
        new messages_window(connector);
    }//GEN-LAST:event_menu_messagesActionPerformed

    private void menu_aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_aboutActionPerformed
        new about_window(this,true);
    }//GEN-LAST:event_menu_aboutActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_boardview;
    private javax.swing.JButton button_loadall;
    private javax.swing.JButton button_newboard;
    private javax.swing.JButton button_projectdetails;
    private javax.swing.JButton button_projectsource;
    private javax.swing.JButton button_snippets;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel label_build;
    private javax.swing.JLabel label_issues;
    private javax.swing.JLabel label_tasks;
    private javax.swing.JList<String> list_boards;
    private javax.swing.JList<String> list_issues;
    private javax.swing.JList<String> list_projects;
    private javax.swing.JList<String> list_tasks;
    private javax.swing.JMenuItem menu_about;
    private javax.swing.JMenu menu_actions;
    private javax.swing.JMenuItem menu_addtoboard;
    private javax.swing.JMenu menu_archive;
    private javax.swing.JMenuItem menu_archivedissues;
    private javax.swing.JMenuItem menu_archivedtasks;
    private javax.swing.JMenu menu_board;
    private javax.swing.JMenuItem menu_change_password;
    private javax.swing.JMenuItem menu_check_password;
    private javax.swing.JMenuItem menu_createnewtask;
    private javax.swing.JMenuItem menu_debug;
    private javax.swing.JMenu menu_information;
    private javax.swing.JMenu menu_issue;
    private javax.swing.JMenuItem menu_issueaddtoboard;
    private javax.swing.JMenuItem menu_log_out;
    private javax.swing.JMenuItem menu_messages;
    private javax.swing.JMenu menu_myprofile;
    private javax.swing.JMenuItem menu_new_project;
    private javax.swing.JMenuItem menu_newissue;
    private javax.swing.JMenu menu_project;
    private javax.swing.JMenuItem menu_reload_session;
    private javax.swing.JMenuItem menu_reload_window;
    private javax.swing.JMenuItem menu_remove_project;
    private javax.swing.JMenuItem menu_removeboard;
    private javax.swing.JMenuItem menu_removeissue;
    private javax.swing.JMenuItem menu_removetask;
    private javax.swing.JMenuItem menu_roommanager;
    private javax.swing.JMenu menu_rooms;
    private javax.swing.JMenuItem menu_server_ip;
    private javax.swing.JMenuItem menu_set_emailaddress;
    private javax.swing.JMenu menu_shared;
    private javax.swing.JMenuItem menu_sharedprojects;
    private javax.swing.JMenuItem menu_sharedtome;
    private javax.swing.JMenuItem menu_shareprojects;
    private javax.swing.JMenuItem menu_showdetails;
    private javax.swing.JMenu menu_task;
    private javax.swing.JMenuItem menu_taskdetails;
    private javax.swing.JMenu menu_todo;
    private javax.swing.JMenuItem menu_todo_quickadd;
    private javax.swing.JMenuItem menu_todo_showlist;
    private javax.swing.JMenuItem menu_user_configuration;
    private javax.swing.JMenuItem menu_user_session;
    private javax.swing.JMenuItem menu_user_time;
    // End of variables declaration//GEN-END:variables
}
