package com.mycompany.facultymanagementsystemgui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FacultyManagementSystemGUI {

    Connection con;
    FacultyManagementSystemGUI(Connection con){
        this.con = con;
    }

    public boolean login(String username, String password) throws SQLException {
        Statement statement = this.con.createStatement();
        String q = String.format("select password from admin where username = '%s';", username);
        ResultSet resultSet = statement.executeQuery(q);

        if(resultSet.next()){

            if(resultSet.getString(1).equals(password)){
                return true;
            }
            else {
                JFrame popup = new JFrame("Invalid password");
                JLabel popupMsg = new JLabel("The password you entered is invalid.");
                popupMsg.setBounds(20,10,300,50);
                popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                popup.add(popupMsg);

                JButton button = new JButton("OK");
                button.setBounds(120,60,70,20);
                button.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                button.addActionListener(actionEvent2 -> popup.dispose());
                popup.add(button);

                popup.setLayout(null);
                popup.setSize(350, 150);
                popup.setVisible(true);
                return false;
            }
        }
        else {
            JFrame popup = new JFrame("Invalid username");
            JLabel popupMsg = new JLabel("The username you entered does not exist.");
            popupMsg.setBounds(20,10,500,50);
            popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            popup.add(popupMsg);

            JButton button = new JButton("OK");
            button.setBounds(170,60,70,20);
            button.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            button.addActionListener(actionEvent2 -> popup.dispose());
            popup.add(button);

            popup.setLayout(null);
            popup.setSize(450, 150);
            popup.setVisible(true);
            return false;
        }
    }

    static String username = "";
    static String password = "";

    JFrame menuFrame;
    JFrame loginFrame;

    public void viewFaculty() throws SQLException {
        menuFrame.setVisible(false);

        JFrame frame=new JFrame("Faculty Records");
        JPanel panel=new JPanel();

        Statement statement = this.con.createStatement();
        String q = "select * from facul";
        ResultSet resultSet = statement.executeQuery(q);

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        backButton.setBounds(450, 20, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            menuFrame.setVisible(true);
        });
        buttonPanel.add(backButton);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(new Color(254, 251, 246));
        panel.add(buttonPanel);

        while (resultSet.next()) {
            JPanel facultyCard = new JPanel();

            JLabel idLabel = new JLabel("ID");
            idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            JLabel idVal = new JLabel(String.valueOf(resultSet.getInt(1)));
            idVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            JLabel nameLabel = new JLabel("Name");
            nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            JLabel nameVal = new JLabel(resultSet.getString(2));
            nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            JLabel genderLabel = new JLabel("Gender");
            genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            JLabel genderVal = new JLabel(resultSet.getString(3));
            genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            JLabel phoneLabel = new JLabel("Phone Number");
            phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            JLabel phoneVal = new JLabel(resultSet.getString(4));
            phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            JLabel emailLabel = new JLabel("Email");
            emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            JLabel emailVal = new JLabel(resultSet.getString(5));
            emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            JLabel departmentLabel = new JLabel("Department");
            departmentLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            JLabel departmentVal = new JLabel(resultSet.getString(6));
            departmentVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            JLabel salaryLabel = new JLabel("Salary");
            salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            JLabel salaryVal = new JLabel(String.valueOf(resultSet.getDouble(7)));
            salaryVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

            facultyCard.add(idLabel);
            facultyCard.add(idVal);
            facultyCard.add(nameLabel);
            facultyCard.add(nameVal);
            facultyCard.add(genderLabel);
            facultyCard.add(genderVal);
            facultyCard.add(phoneLabel);
            facultyCard.add(phoneVal);
            facultyCard.add(emailLabel);
            facultyCard.add(emailVal);
            facultyCard.add(departmentLabel);
            facultyCard.add(departmentVal);
            facultyCard.add(salaryLabel);
            facultyCard.add(salaryVal);

            facultyCard.setSize(1000, 400);
            facultyCard.setBackground(new Color(166, 209, 230));
            facultyCard.setBorder(new EmptyBorder(0, 50, 0, 50));
            GridLayout cardLayout = new GridLayout(0, 2);
            cardLayout.setHgap(5);
            cardLayout.setVgap(10);
            facultyCard.setLayout(cardLayout);
            panel.add(facultyCard);
        }


        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(10);
        panel.setLayout(layout);
        panel.setBackground(new Color(254, 251, 246));
        panel.setBorder(new EmptyBorder(0, 0, 10, 0));

        JScrollPane scrollBar=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1100,750));
        frame.pack();
        frame.setVisible(true);

    }

    public void addFaculty() throws SQLException  {

        menuFrame.setVisible(false);

        JFrame frame=new JFrame("Faculty Records");
        JPanel panel=new JPanel();

        JLabel idLabel = new JLabel("Enter ID");
        idLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JTextField idVal = new JTextField("");
        idVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        idVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    JFrame popup = new JFrame("Invalid ID");
                    JLabel popupMsg = new JLabel("The ID you entered is invalid.");
                    popupMsg.setBounds(20,10,300,50);
                    popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                    popup.add(popupMsg);

                    JButton button = new JButton("OK");
                    button.setBounds(120,60,70,20);
                    button.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                    button.addActionListener(actionEvent2 -> popup.dispose());
                    popup.add(button);

                    popup.setLayout(null);
                    popup.setSize(350, 150);
                    popup.setVisible(true);

                }

            }
        });

        JLabel nameLabel = new JLabel("Enter Name");
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JTextField nameVal = new JTextField("");
        nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JLabel genderLabel = new JLabel("Enter gender");
        genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JComboBox<String> genderVal = new JComboBox<>(new String[]{"Male", "Female"});
        genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JLabel phoneLabel = new JLabel("Enter Phone Number");
        phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JTextField phoneVal = new JTextField("");
        phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JLabel emailLabel = new JLabel("Enter Email Address");
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JTextField emailVal = new JTextField("");
        emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JLabel departmentLabel = new JLabel("Enter Department");
        departmentLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JTextField departmentVal = new JTextField("");
        departmentVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JLabel salaryLabel = new JLabel("Enter Salary ($)");
        salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JTextField salaryVal = new JTextField("");
        salaryVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        salaryVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        panel.add(idLabel);
        panel.add(idVal);
        panel.add(nameLabel);
        panel.add(nameVal);
        panel.add(genderLabel);
        panel.add(genderVal);
        panel.add(phoneLabel);
        panel.add(phoneVal);
        panel.add(emailLabel);
        panel.add(emailVal);
        panel.add(departmentLabel);
        panel.add(departmentVal);
        panel.add(salaryLabel);
        panel.add(salaryVal);


        GridLayout cardLayout = new GridLayout(0, 2);
        cardLayout.setHgap(60);
        cardLayout.setVgap(40);
        panel.setLayout(cardLayout);

        panel.setSize(1000, 400);
        panel.setBackground(new Color(166, 209, 230));
        panel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        backButton.setBounds(450, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            menuFrame.setVisible(true);
        });
        panel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        submitButton.setBounds(450, 30, 200,50);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionListener -> {
            Statement statement = null;
            try {
                statement = this.con.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            int id = Integer.parseInt(idVal.getText());
            String name = nameVal.getText();
            String gender = (String) genderVal.getSelectedItem();
            String phoneNum = phoneVal.getText();
            String email = emailVal.getText();
            String department = departmentVal.getText();
            double salary = Double.parseDouble(salaryVal.getText());

            String q = String.format("insert into facul values (%d, '%s', '%s', '%s', '%s', '%s', %f);", id, name, gender,
                    phoneNum, email, department, salary);
            try {
                assert statement != null;
                statement.executeUpdate(q);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            menuFrame.setVisible(true);
            frame.dispose();

        });
        panel.add(submitButton);

        panel.setBackground(new Color(254, 251, 246));
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1100,750));
        frame.pack();
        frame.setVisible(true);

    }

    public void editFaculty() throws SQLException {
        menuFrame.setVisible(false);

        JFrame frame = new JFrame("Edit Faculty");

        JLabel label = new JLabel("Enter faculty id");
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label.setBounds(250,200,200,50);
        frame.add(label);

        JTextField idVal = new JTextField();
        idVal.setFont(new Font("Times New Roman", Font.BOLD, 20));
        idVal.setBounds(500,200,200,50);
        frame.add(idVal);
        idVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        backButton.setBounds(275, 400, 150,40);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            menuFrame.setVisible(true);
            frame.dispose();

        });
        frame.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        submitButton.setBounds(525, 400, 150,40);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionEvent -> {
            int id = Integer.parseInt(idVal.getText());
            String q = String.format("select * from facul where id = %d;", id);
            Statement statement;
            try {
                statement = this.con.createStatement();
                ResultSet resultSet = statement.executeQuery(q);
                if (resultSet.next()) {
                    System.out.println(resultSet.getString(2));
                    editFacultyHelper(id, frame);

                }
                else{
                    JFrame popup = new JFrame("Invalid ID");
                    JLabel popupMsg = new JLabel("The ID you entered is invalid.");
                    popupMsg.setBounds(20,10,300,50);
                    popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                    popup.add(popupMsg);

                    JButton button = new JButton("OK");
                    button.setBounds(120,60,70,20);
                    button.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                    button.addActionListener(actionEvent2 -> popup.dispose());
                    popup.add(button);

                    popup.setLayout(null);
                    popup.setSize(350, 150);
                    popup.setVisible(true);

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        frame.add(submitButton);

        frame.setLayout(null);
        frame.setSize(new Dimension(1100,750));
        frame.setVisible(true);
    }

    public void editFacultyHelper(int id, JFrame parentFrame) throws SQLException {
        JFrame frame = new JFrame("Edit Faculty");
        JPanel panel=new JPanel();

        Statement statement = null;
        try {
            statement = this.con.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String q = String.format("select * from facul where id = %d;",id);
        assert statement != null;
        ResultSet resultSet = statement.executeQuery(q);
        resultSet.next();
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JTextField nameVal = new JTextField(resultSet.getString(2));
        nameVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        String gender = resultSet.getString(3);
        JComboBox<String> genderVal = new JComboBox<>(new String[]{"Male", "Female"});
        genderVal.setSelectedIndex(gender.equals("Male") ? 0 : 1 );
        genderVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JTextField phoneVal = new JTextField(resultSet.getString(4));
        phoneVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JTextField emailVal = new JTextField(resultSet.getString(5));
        emailVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JLabel departmentLabel = new JLabel("Department");
        departmentLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JTextField departmentVal = new JTextField(resultSet.getString(6));
        departmentVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JLabel salaryLabel = new JLabel("Salary");
        salaryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        JTextField salaryVal = new JTextField(String.valueOf(resultSet.getDouble(7)));
        salaryVal.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        salaryVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        panel.add(nameLabel);
        panel.add(nameVal);
        panel.add(genderLabel);
        panel.add(genderVal);
        panel.add(phoneLabel);
        panel.add(phoneVal);
        panel.add(emailLabel);
        panel.add(emailVal);
        panel.add(departmentLabel);
        panel.add(departmentVal);
        panel.add(salaryLabel);
        panel.add(salaryVal);


        GridLayout cardLayout = new GridLayout(0, 2);
        cardLayout.setHgap(60);
        cardLayout.setVgap(40);
        panel.setLayout(cardLayout);

        panel.setSize(1000, 400);
        panel.setBackground(new Color(166, 209, 230));
        panel.setBorder(new EmptyBorder(20, 50, 20, 50));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        backButton.setBounds(450, 30, 200,50);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            frame.dispose();
            menuFrame.setVisible(true);
        });
        panel.add(backButton);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        submitButton.setBounds(450, 30, 200,50);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionListener -> {
            Statement statemnt = null;
            try {
                statemnt = this.con.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            String name = nameVal.getText();
            String genderValSelectedItem = (String) genderVal.getSelectedItem();
            String phoneNum = phoneVal.getText();
            String email = emailVal.getText();
            String department = departmentVal.getText();
            double salary = Double.parseDouble(salaryVal.getText());

            String query = String.format("update facul set name = '%s', gender = '%s', phoneNum = '%s', email = '%s', " +
                            "department = '%s', salary = %s where id = %d;", name, genderValSelectedItem,
                    phoneNum, email, department, salary, id);
            try {
                assert statemnt != null;
                statemnt.executeUpdate(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            menuFrame.setVisible(true);
            frame.dispose();

        });
        panel.add(submitButton);

        panel.setBackground(new Color(254, 251, 246));
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1100,750));
        frame.pack();
        frame.setVisible(true);
        parentFrame.dispose();
    }

    public void deleteFaculty(){
        menuFrame.setVisible(false);

        JFrame frame = new JFrame("Delete Faculty");

        JLabel label = new JLabel("Enter faculty id");
        label.setFont(new Font("Times New Roman", Font.BOLD, 20));
        label.setBounds(250,200,200,50);
        frame.add(label);

        JTextField idVal = new JTextField();
        idVal.setFont(new Font("Times New Roman", Font.BOLD, 20));
        idVal.setBounds(500,200,200,50);
        frame.add(idVal);
        idVal.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        backButton.setBounds(275, 400, 150,40);
        backButton.setFocusPainted(false);

        backButton.addActionListener(actionListener -> {
            menuFrame.setVisible(true);
            frame.dispose();

        });
        frame.add(backButton);

        JButton submitButton = new JButton("Delete");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        submitButton.setBounds(525, 400, 150,40);
        submitButton.setFocusPainted(false);

        submitButton.addActionListener(actionEvent -> {
            int id = Integer.parseInt(idVal.getText());
            String q = String.format("select * from facul where id = %d;", id);
            Statement statement;
            try {
                statement = this.con.createStatement();
                ResultSet resultSet = statement.executeQuery(q);

                if (resultSet.next()) {
                    System.out.println(resultSet.getString(2));

                    String deleteQuery = String.format("delete from facul where id = %d;", id);

                    int input = JOptionPane.showConfirmDialog(null, "CONFIRM YOU WANT TO DELETE?", "Delete Confirmation",
                            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);

                    // 0=Yes, 1=No, 2=Cancel
                    System.out.println(input);
                    if(input==0){
                    statement.executeUpdate(deleteQuery);}
                    menuFrame.setVisible(true);
                    frame.dispose();

                }
                else{
                    JFrame popup = new JFrame("Invalid ID");
                    JLabel popupMsg = new JLabel("The ID you entered is invalid.");
                    popupMsg.setBounds(20,10,300,50);
                    popupMsg.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                    popup.add(popupMsg);

                    JButton button = new JButton("OK");
                    button.setBounds(120,60,70,20);
                    button.setFont(new Font("Times New Roman", Font.PLAIN, 25));
                    button.addActionListener(actionEvent2 -> popup.dispose());
                    popup.add(button);

                    popup.setLayout(null);
                    popup.setSize(350, 150);
                    popup.setVisible(true);

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        frame.add(submitButton);

        frame.setLayout(null);
        frame.setSize(new Dimension(1100,750));
        frame.setVisible(true);
    }


    public void mainMenu(){
        menuFrame = new JFrame("Faculty Management System");

        JLabel welcomeLabel = new JLabel("Welcome to Faculty Management System");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        welcomeLabel.setBounds(185,100,800,50);
        menuFrame.add(welcomeLabel);

        JButton viewFactButton = new JButton("View all faculty");
        viewFactButton.setBounds(400, 200, 300, 40);
        viewFactButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        viewFactButton.setFocusPainted(false);
        viewFactButton.addActionListener(actionEvent -> {
            try {
                viewFaculty();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        menuFrame.add(viewFactButton);

        JButton addFactButton = new JButton("Add a faculty");
        addFactButton.setBounds(400, 270, 300, 40);
        addFactButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        addFactButton.setFocusPainted(false);
        addFactButton.addActionListener(actionEvent -> {
            try {
                addFaculty();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        menuFrame.add(addFactButton);

        JButton editfactButton = new JButton("Edit a faculty");
        editfactButton.setBounds(400, 340, 300, 40);
        editfactButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        editfactButton.setFocusPainted(false);
        editfactButton.addActionListener(actionEvent -> {
            try {
                editFaculty();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        menuFrame.add(editfactButton);

        JButton deleteFactButton = new JButton("Delete a faculty");
        deleteFactButton.setBounds(400, 410, 300, 40);
        deleteFactButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        deleteFactButton.setFocusPainted(false);
        deleteFactButton.addActionListener(deleteEvent -> deleteFaculty());
        menuFrame.add(deleteFactButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(400, 480, 300, 40);
        exitButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        exitButton.setFocusPainted(false);

        exitButton.addActionListener(actionEvent -> menuFrame.dispose());
        menuFrame.add(exitButton);


        menuFrame.setSize(1100,750);
        menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuFrame.setLayout(null);
        menuFrame.setVisible(true);

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbName = "faculty";
        String db_username = "root";
        String db_password = "root";
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/"+dbName, db_username, db_password);

        FacultyManagementSystemGUI fmsgui = new FacultyManagementSystemGUI(con);

        fmsgui.loginFrame = new JFrame("Login");

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(200,150,220,50);
        usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        fmsgui.loginFrame.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        usernameField.setBounds(450,150,420,50);
        fmsgui.loginFrame.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        passwordLabel.setBounds(200,250,220,50);
        fmsgui.loginFrame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        passwordField.setBounds(450,250,420,50);
        fmsgui.loginFrame.add(passwordField);

        JButton submitButton=new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        submitButton.setBounds(450,400,250, 50);

        submitButton.addActionListener(actionEvent -> {
            username = usernameField.getText();
            password = String.valueOf(passwordField.getPassword());

            try {
                boolean auth = fmsgui.login(username, password);
                if(auth){
                    fmsgui.loginFrame.dispose();
                    fmsgui.mainMenu();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        fmsgui.loginFrame.add(submitButton);

        fmsgui.loginFrame.setSize(1100,750);
        fmsgui.loginFrame.setLayout(null);
        fmsgui.loginFrame.setVisible(true);
        fmsgui.loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}