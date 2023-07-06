import javax.swing.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class AyushHospitalFinderForm extends JFrame {

    private JTextField hospitalIdTextField;
    private JTextField hospitalNameTextField;
    private JTextField hospitalCityTextField;
    private JTextField hospitalContactTextField;
    private JTextField hospitalOpenTimeTextField;
    private JTextField hospitalCloseTimeTextField;
    private JTextField ratingTextField;
    private JPanel mainPanel,mainPanel1,mainPanel2;
    private JLabel imageLabel;
    private JPanel insertPanel,updatePanel, patientPanel, hospitalPanel, deletePanel, appointmentPanel,doctorPanel;
    private JTable patientTable;
    public AyushHospitalFinderForm() {
        setTitle("Ayush Hospital Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("D:\\DBMS\\unnamed.png\\");
        imageLabel.setIcon(imageIcon);

        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());

        JButton adminButton = new JButton("Admin");
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminMenu();
            }
        });

        JButton patientButton = new JButton("Patient");
        patientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                patientMenu();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(adminButton);
        buttonPanel.add(patientButton);

        mainPanel.add(buttonPanel, BorderLayout.PAGE_START);
        mainPanel.add(imageLabel, BorderLayout.CENTER);
        
        pack();
        setVisible(true);
    }


    private void adminMenu() {
    	JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(mainPanel, message, "Admin Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();

            boolean isAuthenticated = authenticateAdmin(username, password);
            if (isAuthenticated) {
            	setTitle("Ayush Hopsital Finder");
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setSize(600, 600);
                setLocationRelativeTo(null);

                imageLabel = new JLabel();
                ImageIcon imageIcon = new ImageIcon("D:\\DBMS\\unnamed.png\\");
                imageLabel.setIcon(imageIcon);
                
                mainPanel1 = new JPanel();
                setContentPane(mainPanel1);
                mainPanel1.setLayout(new BorderLayout());

                JMenuBar menuBar1 = new JMenuBar();
                setJMenuBar(menuBar1);

                JMenu optionsMenu = new JMenu("Admin Options");
                menuBar1.add(optionsMenu);

                JMenuItem hospitalMenuItem = new JMenuItem("EditHospital");
                optionsMenu.add(hospitalMenuItem);
                hospitalMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        HospitalForm();
                    }
                });

                JMenuItem doctorMenuItem = new JMenuItem("EditDoctor");
                optionsMenu.add(doctorMenuItem);
                doctorMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	DoctorForm();
                    }
                });
                
                JMenuItem viewDoctor =new JMenuItem("View Doctors");
                optionsMenu.add(viewDoctor);
                viewDoctor.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		DoctorList();
   
                	}
                });
                
                JMenuItem viewHospital =new JMenuItem("View Hospitals");
                optionsMenu.add(viewHospital);
                viewHospital.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		HospitalList();
   
                	}
                });
                JMenuItem backMenuItem = new JMenuItem("Back");
                optionsMenu.add(backMenuItem);
                backMenuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new AyushHospitalFinderForm().setVisible(true);
                        setVisible(false);}
                });
                
                mainPanel1.add(imageLabel, BorderLayout.CENTER);
                pack();
                setVisible(true);
            }
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Invalid username or password. Please try again.", "Authentication Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    private boolean authenticateAdmin(String username, char[] password) {
        
        String storedUsername = "admin";
        String storedPassword = "admin";
        return username.equals(storedUsername) && new String(password).equals(storedPassword);
    }

    private void patientMenu() {
    	setTitle("Ayush Hospital Finder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(600, 600);
        setLocationRelativeTo(null);
        
        imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("D:\\DBMS\\unnamed.png\\");
        imageLabel.setIcon(imageIcon);

        mainPanel2 = new JPanel();
        setContentPane(mainPanel2);
        mainPanel2.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu optionsMenu = new JMenu("Explore");
        menuBar.add(optionsMenu);

        JMenuItem patientFMenuItem = new JMenuItem("Patient Form");
        optionsMenu.add(patientFMenuItem);
        patientFMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                patientForm();
            }
        });

        JMenuItem appointMenuItem = new JMenuItem("Appointment");
        optionsMenu.add(appointMenuItem);
        appointMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                appointmentForm();
            }
        });
        
        JMenuItem patientappointmentItem = new JMenuItem("My Appointments");
        optionsMenu.add(patientappointmentItem);
        patientappointmentItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewappointment();
            }
        });
         
        JMenuItem backMenuItem = new JMenuItem("Back");
        optionsMenu.add(backMenuItem);
        backMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AyushHospitalFinderForm().setVisible(true);
                setVisible(false);
            }
        });
        mainPanel2.add(imageLabel, BorderLayout.CENTER);
        pack();
        setVisible(true);
        // Add patient-specific actions here
    }
    private JTextField patientIdTextField;
    private JTextField patientFNameTextField;
    private JTextField patientLNameTextField;
    private JTextField patientBGTextField;
    private JTextField patientGenderTextField;
    private JTextField patientPhoneTextField;
    private void patientForm() {
        JLabel patientIdLabel = new JLabel("Patient ID:");
        patientIdTextField = new JTextField(10);

        JLabel patientFNameLabel = new JLabel("FirstName:");
        patientFNameTextField = new JTextField(20);

        JLabel patientLNameLabel = new JLabel("LastName:");
        patientLNameTextField = new JTextField(20);

        JLabel patientBGLabel = new JLabel("BloodGroup:");
        patientBGTextField = new JTextField(15);

        JLabel patientGenderLabel = new JLabel("Gender:");
        patientGenderTextField = new JTextField(15);

        JLabel patientPhoneLabel = new JLabel("PhoneNo:");
        patientPhoneTextField = new JTextField(15);

        // Create buttons
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertPatient();
            }
        });

        JButton modifyButton = new JButton("Modify");
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePatient();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePatient();
            }
        });
        
        JButton viewButton = new JButton("View");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewPatientDetails();
            }
        });

        // Create panel for form components
        patientPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(6, 6, 6, 6);

        patientPanel.add(patientIdLabel, gbc);
        gbc.gridx++;
        patientPanel.add(patientIdTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        patientPanel.add(patientFNameLabel, gbc);
        gbc.gridx++;
        patientPanel.add(patientFNameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        patientPanel.add(patientLNameLabel, gbc);
        gbc.gridx++;
        patientPanel.add(patientLNameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        patientPanel.add(patientBGLabel, gbc);
        gbc.gridx++;
        patientPanel.add(patientBGTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        patientPanel.add(patientGenderLabel, gbc);
        gbc.gridx++;
        patientPanel.add(patientGenderTextField, gbc);
        // ...

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        patientPanel.add(patientPhoneLabel, gbc);
        gbc.gridx++;
        patientPanel.add(patientPhoneTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1; // Empty label for spacing

        gbc.gridx++;
        patientPanel.add(submitButton, gbc);

        gbc.gridx++;
        gbc.insets = new Insets(0, 10, 0, 5); // Adjusted insets for the Modify button
        patientPanel.add(modifyButton, gbc);

        gbc.gridx++;
        gbc.insets = new Insets(0, 5, 0, 5); // Adjusted insets for the Delete button
        patientPanel.add(deleteButton, gbc);

        gbc.gridx++;
        gbc.insets = new Insets(0, 5, 0, 0); // Adjusted insets for the View button
        patientPanel.add(viewButton, gbc);
        
        add(patientPanel, BorderLayout.CENTER);
        setSize(600, 600);
        setContentPane(patientPanel);
        setLocationRelativeTo(null);
    }
    private DefaultTableModel tableModel;
    private void insertPatient()
    {
    	tableModel = new DefaultTableModel();
        patientTable = new JTable(tableModel);
    	int patientId = Integer.parseInt(patientIdTextField.getText());
        String patientFName = patientFNameTextField.getText();
        String patientLName = patientLNameTextField.getText();
        String patientBG = patientBGTextField.getText();
        String patientGender = patientGenderTextField.getText();
        long patientPhone = Long.parseLong(patientPhoneTextField.getText());
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            String insertQuery = "INSERT INTO users (USID, FName, LName, Blood_Group,Gender,Phone) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setString(2, patientFName);
            preparedStatement.setString(3, patientLName);
            preparedStatement.setString(4, patientBG);
            preparedStatement.setString(5, patientGender);
            preparedStatement.setLong(6, patientPhone);
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Patient inserted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearPFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to insert Patient", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updatePatient() {
        int patientId = Integer.parseInt(patientIdTextField.getText());
        String patientFName = patientFNameTextField.getText();
        String patientLName = patientLNameTextField.getText();
        String patientBG = patientBGTextField.getText();
        String patientGender = patientGenderTextField.getText();
        String phoneText = patientPhoneTextField.getText();
        long patientPhone = 0;
        if (!phoneText.isEmpty()) {
            try {
                patientPhone = Long.parseLong(phoneText);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            StringBuilder updateQuery = new StringBuilder("UPDATE users SET ");
            List<Object> params = new ArrayList<>();
            
            if (!patientFName.isEmpty()) {
                updateQuery.append("FName = ?, ");
                params.add(patientFName);
            }
            if (!patientLName.isEmpty()) {
                updateQuery.append("LName = ?, ");
                params.add(patientLName);
            }
            if (!patientBG.isEmpty()) {
                updateQuery.append("Blood_Group = ?, ");
                params.add(patientBG);
            }
            if (!patientGender.isEmpty()) {
                updateQuery.append("Gender = ?, ");
                params.add(patientGender);
            }
            if (patientPhone != 0) {
                updateQuery.append("Phone = ?, ");
                params.add(patientPhone);
            }
            
            // Remove the trailing comma and space
            updateQuery.setLength(updateQuery.length() - 2);
            
            updateQuery.append(" WHERE USID = ?");
            params.add(patientId);
            
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery.toString());
            
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
            
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Patient updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearPFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update Patient", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePatient()
    {
    	int patientId = Integer.parseInt(patientIdTextField.getText());

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            String deleteQuery = "DELETE FROM users WHERE USID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, patientId);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Patient deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearPFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete patient", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewPatientDetails() {
        int patientId = Integer.parseInt(patientIdTextField.getText());

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            String selectQuery = "SELECT * FROM users WHERE usid = ?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();

            // Create the table model
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Patient ID");
            tableModel.addColumn("First Name");
            tableModel.addColumn("Last Name");
            tableModel.addColumn("Blood Group");
            tableModel.addColumn("Gender");
            tableModel.addColumn("Phone");

            // Populate the table model with data
            while (resultSet.next()) {
                int id = resultSet.getInt("usid");
                String firstName = resultSet.getString("FName");
                String lastName = resultSet.getString("LName");
                String bloodGroup = resultSet.getString("blood_Group");
                String gender = resultSet.getString("gender");
                String phoneNo = resultSet.getString("phone");

                Object[] rowData = {id, firstName, lastName, bloodGroup, gender, phoneNo};
                tableModel.addRow(rowData);
            }

            // Create the table with the table model
            patientTable = new JTable(tableModel);

            // Create a scroll pane and add the table to it
            JScrollPane scrollPane = new JScrollPane(patientTable);

            // Create a panel and add the scroll pane to it
            JPanel panel = new JPanel();
            panel.add(scrollPane);

            // Create a frame and add the panel to it
            JFrame frame = new JFrame("Patient Details");
            frame.getContentPane().add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearPFormFields()
    {
    	patientIdTextField.setText("");
        patientFNameTextField.setText("");
        patientLNameTextField.setText("");
        patientBGTextField.setText("");
        patientGenderTextField.setText("");
        patientPhoneTextField.setText("");
    }
    private JTextField visitIdTextField;
    private JTextField usidTextField;
    private JTextField vhidTextField;
    private JTextField vdidTextField;
    private JTextField visitdateTextField;
    private JTextField visittimeTextField;
    
    private void appointmentForm() {
        setSize(600, 600);
        JLabel visitIdLabel = new JLabel("Visit ID:");
        visitIdTextField = new JTextField(10);

        JLabel usidLabel = new JLabel("Patient ID:");
        usidTextField = new JTextField(20);

        JLabel vhidLabel = new JLabel("Hospital ID:");
        vhidTextField = new JTextField(20);

        JButton locateButton = new JButton("Locate");
        locateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewHospitals();
            }
        });

        JLabel vdidLabel = new JLabel("Doctor ID:");
        vdidTextField = new JTextField(15);

        JButton viewButton = new JButton("View");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewdoctors();
            }
        });

        JLabel visitdateLabel = new JLabel("Visit Date:");
        visitdateTextField = new JTextField(15);

        JLabel visittimeLabel = new JLabel("Visit Time:");
        visittimeTextField = new JTextField(15);
        
        // Create buttons
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookappointment();
            }
        });

        JButton modifyButton = new JButton("Modify");
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateappointment();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteappointment();
            }
        });

        // Create panel for form components
        appointmentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 8, 8, 8);

        appointmentPanel.add(visitIdLabel, gbc);
        gbc.gridx++;
        appointmentPanel.add(visitIdTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        appointmentPanel.add(usidLabel, gbc);
        gbc.gridx++;
        appointmentPanel.add(usidTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        appointmentPanel.add(vhidLabel, gbc);
        gbc.gridx++;
        appointmentPanel.add(vhidTextField, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        appointmentPanel.add(locateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        appointmentPanel.add(vdidLabel, gbc);
        gbc.gridx++;
        appointmentPanel.add(vdidTextField, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        appointmentPanel.add(viewButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        appointmentPanel.add(visitdateLabel, gbc);
        gbc.gridx++;
        appointmentPanel.add(visitdateTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        appointmentPanel.add(visittimeLabel, gbc);
        gbc.gridx++;
        appointmentPanel.add(visittimeTextField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        appointmentPanel.add(submitButton, gbc);

        gbc.gridx++;
        gbc.insets = new Insets(8, 8, 8, 8); // Adjusted insets for the Modify button
        appointmentPanel.add(modifyButton, gbc);

        gbc.gridx++;
        appointmentPanel.add(deleteButton, gbc);

        //add(appointmentPanel, BorderLayout.CENTER);
        setContentPane(appointmentPanel);
        setLocationRelativeTo(null);
        
    }


    private void viewHospitals()
    {
    	JTextField locationField = new JTextField();
        Object[] message = {
                "", locationField};
        int option = JOptionPane.showConfirmDialog(mainPanel, message, "Locator", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION)
        {
        	String location=locationField.getText();
        	try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
                String selectQuery = "SELECT HID,HBranch,Opening_Time,Closing_Time,Rating FROM hospitals where HBranch= ?";
                PreparedStatement statement = connection.prepareStatement(selectQuery);
                statement.setString(1,location);
                ResultSet resultSet = statement.executeQuery();
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("Hospital ID");
                tableModel.addColumn("Hospital Branch");
                tableModel.addColumn("Opening_Time");
                tableModel.addColumn("Closing_Time");
                tableModel.addColumn("Rating");

                // Populate the table model with data
                while (resultSet.next()) {
                    int hid = resultSet.getInt("hid");
                    String hbranch = resultSet.getString("hbranch");
                    String opentime = resultSet.getString("opening_time");
                    String closetime= resultSet.getString("closing_time");
                    int rating = resultSet.getInt("rating");

                    Object[] rowData = {hid,hbranch,opentime,closetime,rating};
                    tableModel.addRow(rowData);
                }

                // Create the table with the table model
                JTable table = new JTable(tableModel);

                // Create a scroll pane and add the table to it
                JScrollPane scrollPane = new JScrollPane(table);

                // Create a panel and add the scroll pane to it
                JPanel panel = new JPanel();
                panel.add(scrollPane);

                // Create a frame and add the panel to it
                JFrame frame = new JFrame("Nearby Ayush Hospitals");
//                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.getContentPane().add(panel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
        	}
        	catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Failed to connect to the database");
            }
        }
        
    }
    private void viewdoctors()
    {
    	JTextField viewField = new JTextField();
        Object[] message = {
                "", viewField};
        int option = JOptionPane.showConfirmDialog(mainPanel, message, "DocInfo", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION)
        {
        	String view=viewField.getText();
        	try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
                String selectQuery = "SELECT doctors.did,doctors.dname,doctors.hid,doctors.sid,specialization.sname FROM doctors inner join specialization ON doctors.sid=specialization.sid where specialization.sname= ?" ;
                PreparedStatement statement = connection.prepareStatement(selectQuery);
                statement.setString(1,view);
                ResultSet resultSet = statement.executeQuery();
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("Doctor ID");
                tableModel.addColumn("Doctor Name");
                tableModel.addColumn("Hospital ID");
                tableModel.addColumn("Specialization");

                // Populate the table model with data
                while (resultSet.next()) {
                    int did = resultSet.getInt("did");
                    String dname = resultSet.getString("dname");
                    int hid = resultSet.getInt("hid");
                    String sname= resultSet.getString("sname");

                    Object[] rowData = {did,dname,hid,sname};
                    tableModel.addRow(rowData);
                }

                // Create the table with the table model
                JTable table = new JTable(tableModel);

                // Create a scroll pane and add the table to it
                JScrollPane scrollPane = new JScrollPane(table);

                // Create a panel and add the scroll pane to it
                doctorPanel = new JPanel();
                doctorPanel.add(scrollPane);

                // Create a frame and add the panel to it
                JFrame frame = new JFrame("Doctors Info");
                //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.getContentPane().add(doctorPanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
        	}
        	catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Failed to connect to the database");
            }
        }
        
    }
    private void bookappointment() {
        int visitId = Integer.parseInt(visitIdTextField.getText());
        int userId = Integer.parseInt(usidTextField.getText());
        int doctorId = Integer.parseInt(vdidTextField.getText());
        int hospitalId = Integer.parseInt(vhidTextField.getText());
        String visitDate = visitdateTextField.getText();
        String visitTime = visittimeTextField.getText();

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            String insertQuery = "INSERT INTO visits (vid, usid, did, hid, visit_date, visit_time) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, visitId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, doctorId);
            preparedStatement.setInt(4, hospitalId);
            preparedStatement.setString(5, visitDate);
            preparedStatement.setString(6, visitTime);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Appointment booked successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearAFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to book appointment", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateappointment() {
        int visitid = Integer.parseInt(visitIdTextField.getText());

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            StringBuilder updateQuery = new StringBuilder("UPDATE visits SET ");

            List<Object> params = new ArrayList<>();

            if (usidTextField.getText().trim().length() > 0) {
                int usid = Integer.parseInt(usidTextField.getText());
                updateQuery.append("USID = ?, ");
                params.add(usid);
            }

            if (vhidTextField.getText().trim().length() > 0) {
                int vhid = Integer.parseInt(vhidTextField.getText());
                updateQuery.append("HID = ?, ");
                params.add(vhid);
            }

            if (vdidTextField.getText().trim().length() > 0) {
                int vdid = Integer.parseInt(vdidTextField.getText());
                updateQuery.append("DID = ?, ");
                params.add(vdid);
            }

            if (visitdateTextField.getText().trim().length() > 0) {
                String visitdate = visitdateTextField.getText();
                updateQuery.append("VISIT_DATE = ?, ");
                params.add(visitdate);
            }

            if (visittimeTextField.getText().trim().length() > 0) {
                String visittime = visittimeTextField.getText();
                updateQuery.append("VISIT_TIME = ?, ");
                params.add(visittime);
            }

            // Remove the trailing comma and space from the query
            updateQuery.delete(updateQuery.length() - 2, updateQuery.length());

            updateQuery.append(" WHERE VID = ?");
            params.add(visitid);

            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery.toString());

            // Set the parameter values
            for (int i = 0; i < params.size(); i++) {
                Object paramValue = params.get(i);
                preparedStatement.setObject(i + 1, paramValue);
            }

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Appointment updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearAFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update appointment", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Invalid input format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteappointment()
    {
    	int visitId = Integer.parseInt(visitIdTextField.getText());

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            String deleteQuery = "DELETE FROM visits WHERE VID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, visitId);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Appointment deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearAFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete appointment", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearAFormFields()
    {
    	visitIdTextField.setText("");
    	usidTextField.setText("");
        vhidTextField.setText("");
        vdidTextField.setText("");
        visitdateTextField.setText("");
        visittimeTextField.setText("");
    }
    private void viewappointment()
    {
    	JTextField viewField = new JTextField();
        Object[] message = {
                "", viewField};
        int option = JOptionPane.showConfirmDialog(mainPanel, message, "AppointmentInfo", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION)
        {
        	int no=Integer.parseInt(viewField.getText());    	try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            String selectQuery = "SELECT * FROM visits where usid=?";
            PreparedStatement statement = connection.prepareStatement(selectQuery);
            statement.setInt(1,no);
            ResultSet resultSet = statement.executeQuery();

            // Create the table model with column names
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Visit ID");
            tableModel.addColumn("Patient ID");
            tableModel.addColumn("Hospital ID");
            tableModel.addColumn("Doctor ID");
            tableModel.addColumn("Visit Date");
            tableModel.addColumn("Visit Time");

            // Populate the table model with data
            while (resultSet.next()) {
                int vid = resultSet.getInt("vid");
                int usid = resultSet.getInt("usid");
                int hid = resultSet.getInt("hid");
                int did = resultSet.getInt("did");
                String visitdate = resultSet.getString("visit_date");
                String visittime = resultSet.getString("visit_time");

                Object[] rowData = {vid,usid,hid,did,visitdate,visittime};
                tableModel.addRow(rowData);
            }

            // Create the table with the table model
            JTable table = new JTable(tableModel);

            // Create a scroll pane and add the table to it
            JScrollPane scrollPane = new JScrollPane(table);

            // Create a panel and add the scroll pane to it
            insertPanel = new JPanel();
            insertPanel.add(scrollPane);

            // Create a frame and add the panel to it
            JFrame frame = new JFrame("Appointment Data");
            //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().add(insertPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed to connect to the database");
        }
        }
        else
        {
        	JOptionPane.showMessageDialog(mainPanel, "Enter Patient ID.", "Invalid", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void HospitalForm() {
        // Create form components
    	
    	
        JLabel hospitalIdLabel = new JLabel("Hospital ID:");
        hospitalIdTextField = new JTextField(10);

        JLabel hospitalNameLabel = new JLabel("Hospital Name:");
        hospitalNameTextField = new JTextField(20);

        JLabel hospitalCityLabel = new JLabel("Hospital City:");
        hospitalCityTextField = new JTextField(20);

        JLabel hospitalContactLabel = new JLabel("Hospital Contact:");
        hospitalContactTextField = new JTextField(15);

        JLabel hospitalOpeningTimeLabel = new JLabel("Hospital OpenTime:");
        hospitalOpenTimeTextField = new JTextField(15);

        JLabel hospitalClosingTimeLabel = new JLabel("Hospital CloseTime:");
        hospitalCloseTimeTextField = new JTextField(15);

        JLabel hospitalRatingLabel = new JLabel("Rating:");
        ratingTextField = new JTextField(10);

        // Create buttons
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertHospital();
            }
        });

        JButton modifyButton = new JButton("Modify");
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateHospital();
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	deleteHospital();
            }
        });
        
        /*JButton viewButton = new JButton("View");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HospitalList();
            }
        });*/
        
        // Create panel for form components
        hospitalPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(7, 7, 7, 7);

        hospitalPanel.add(hospitalIdLabel, gbc);
        gbc.gridx++;
        hospitalPanel.add(hospitalIdTextField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        hospitalPanel.add(hospitalNameLabel, gbc);
        gbc.gridx++;
        hospitalPanel.add(hospitalNameTextField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        hospitalPanel.add(hospitalCityLabel, gbc);
        gbc.gridx++;
        hospitalPanel.add(hospitalCityTextField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        hospitalPanel.add(hospitalContactLabel, gbc);
        gbc.gridx++;
        hospitalPanel.add(hospitalContactTextField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        hospitalPanel.add(hospitalOpeningTimeLabel, gbc);
        gbc.gridx++;
        hospitalPanel.add(hospitalOpenTimeTextField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        hospitalPanel.add(hospitalClosingTimeLabel, gbc);
        gbc.gridx++;
        hospitalPanel.add(hospitalCloseTimeTextField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        hospitalPanel.add(hospitalRatingLabel, gbc);
        gbc.gridx++;
        hospitalPanel.add(ratingTextField, gbc);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        /*buttonPanel.add(viewButton);*/
        
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        hospitalPanel.add(buttonPanel, gbc);

        add(hospitalPanel, BorderLayout.CENTER);
        setContentPane(hospitalPanel);
        setLocationRelativeTo(null);
    }


    private void insertHospital() {
    	int hospitalId = Integer.parseInt(hospitalIdTextField.getText());
        String hospitalName = hospitalNameTextField.getText();
        String hospitalCity = hospitalCityTextField.getText();
        long hospitalContact = Long.parseLong(hospitalContactTextField.getText());
        String hospitalOpen = hospitalOpenTimeTextField.getText();
        String hospitalClose = hospitalCloseTimeTextField.getText();
        int rating = Integer.parseInt(ratingTextField.getText());
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            String insertQuery = "INSERT INTO Hospitals (HID, HName, HBranch, HPhone,Opening_Time,Closing_Time,Rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, hospitalId);
            preparedStatement.setString(2, hospitalName);
            preparedStatement.setString(3, hospitalCity);
            preparedStatement.setLong(4, hospitalContact);
            preparedStatement.setString(5, hospitalOpen);
            preparedStatement.setString(6, hospitalClose);
            preparedStatement.setInt(7, rating);
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Hospital inserted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to insert hospital", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }

    private void updateHospital() {
        int hospitalId = Integer.parseInt(hospitalIdTextField.getText());

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            StringBuilder updateQuery = new StringBuilder("UPDATE Hospitals SET ");

            List<Object> params = new ArrayList<>();

            if (hospitalNameTextField.getText().trim().length() > 0) {
                String hospitalName = hospitalNameTextField.getText();
                updateQuery.append("HName = ?, ");
                params.add(hospitalName);
            }

            if (hospitalCityTextField.getText().trim().length() > 0) {
                String hospitalCity = hospitalCityTextField.getText();
                updateQuery.append("HBranch = ?, ");
                params.add(hospitalCity);
            }

            if (hospitalContactTextField.getText().trim().length() > 0) {
                long hospitalContact = Long.parseLong(hospitalContactTextField.getText());
                updateQuery.append("HPhone = ?, ");
                params.add(hospitalContact);
            }

            if (hospitalOpenTimeTextField.getText().trim().length() > 0) {
                String hospitalOpen = hospitalOpenTimeTextField.getText();
                updateQuery.append("Opening_Time = ?, ");
                params.add(hospitalOpen);
            }

            if (hospitalCloseTimeTextField.getText().trim().length() > 0) {
                String hospitalClose = hospitalCloseTimeTextField.getText();
                updateQuery.append("Closing_Time = ?, ");
                params.add(hospitalClose);
            }

            if (ratingTextField.getText().trim().length() > 0) {
                int rating = Integer.parseInt(ratingTextField.getText());
                updateQuery.append("Rating = ?, ");
                params.add(rating);
            }

            // Remove the trailing comma and space from the query
            updateQuery.delete(updateQuery.length() - 2, updateQuery.length());

            updateQuery.append(" WHERE HID = ?");
            params.add(hospitalId);

            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery.toString());

            // Set the parameter values
            for (int i = 0; i < params.size(); i++) {
                Object paramValue = params.get(i);
                preparedStatement.setObject(i + 1, paramValue);
            }

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Hospital updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update hospital", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Invalid input format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void deleteHospital() {
        int hospitalId = Integer.parseInt(hospitalIdTextField.getText());

        SwingWorker<Integer, Void> worker = new SwingWorker<Integer, Void>() {
            @Override
            protected Integer doInBackground() throws Exception {
                try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
                	JOptionPane.showMessageDialog(AyushHospitalFinderForm.this, "Hospital deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    String deleteQuery = "DELETE FROM Hospitals WHERE HID = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
                    preparedStatement.setInt(1, hospitalId);

                    return preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return -1;
                }
            }

            @Override
            protected void done() {
                try {
                    int rowsDeleted = get();
                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(AyushHospitalFinderForm.this, "Hospital deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        clearFormFields();
                    } else {
                        JOptionPane.showMessageDialog(AyushHospitalFinderForm.this, "Failed to delete hospital", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(AyushHospitalFinderForm.this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        worker.execute();
    }

    private void clearFormFields() {
        hospitalIdTextField.setText("");
        hospitalNameTextField.setText("");
        hospitalCityTextField.setText("");
        hospitalContactTextField.setText("");
        hospitalOpenTimeTextField.setText("");
        hospitalCloseTimeTextField.setText("");
        ratingTextField.setText("");
    }
    private JTextField didTextField;
    private JTextField dnameTextField;
    private JTextField sidTextField;
    private JTextField hidTextField;

    private void DoctorForm() {
    	
        setTitle("Doctor Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel didLabel = new JLabel("Doctor ID:");
        didTextField = new JTextField(10);
        JLabel dnameLabel = new JLabel("Doctor Name:");
        dnameTextField = new JTextField(10);
        JLabel sidLabel = new JLabel("Specialization ID:");
        sidTextField = new JTextField(10);
        JLabel hidLabel = new JLabel("Hospital ID:");
        hidTextField = new JTextField(10);

        mainPanel.add(didLabel, gbc);
        gbc.gridx++;
        mainPanel.add(didTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(dnameLabel, gbc);
        gbc.gridx++;
        mainPanel.add(dnameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(sidLabel, gbc);
        gbc.gridx++;
        mainPanel.add(sidTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        mainPanel.add(hidLabel, gbc);
        gbc.gridx++;
        mainPanel.add(hidTextField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        mainPanel.add(new JLabel(), gbc); // Empty label for spacing

        JButton submitButton = new JButton("Submit");
        gbc.gridy++;
        gbc.gridwidth = 1;
        mainPanel.add(submitButton, gbc);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertDoctor();
            }
        });

        JButton modifyButton = new JButton("Modify");
        gbc.gridx++;
        mainPanel.add(modifyButton, gbc);
        modifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDoctor();
            }
        });

        JButton deleteButton = new JButton("Delete");
        gbc.gridx++;
        mainPanel.add(deleteButton, gbc);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteDoctor();
            }
        });
        
        /*JButton viewButton = new JButton("View");
        gbc.gridx++;
        mainPanel.add(viewButton, gbc);
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DoctorList();
            }
        });*/


        setContentPane(mainPanel);
        setVisible(true);
    }

    private void insertDoctor()
    {
    	int doctorId = Integer.parseInt(didTextField.getText());
        String doctorName = dnameTextField.getText();
        int specializationId = Integer.parseInt(sidTextField.getText());
        int hospId = Integer.parseInt(hidTextField.getText());
    	try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            String insertQuery = "INSERT INTO doctors (did, dname, sid, hid) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, doctorName);
            preparedStatement.setInt(3, specializationId);
            preparedStatement.setInt(4, hospId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Doctor data inserted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearDFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to insert doctor data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void updateDoctor() {
        int doctorId = Integer.parseInt(didTextField.getText());

        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            StringBuilder updateQuery = new StringBuilder("UPDATE doctors SET ");

            List<Object> params = new ArrayList<>();

            if (dnameTextField.getText().trim().length() > 0) {
                String doctorName = dnameTextField.getText();
                updateQuery.append("dname = ?, ");
                params.add(doctorName);
            }

            if (sidTextField.getText().trim().length() > 0) {
                int specializationId = Integer.parseInt(sidTextField.getText());
                updateQuery.append("sid = ?, ");
                params.add(specializationId);
            }

            if (hidTextField.getText().trim().length() > 0) {
                int hospId = Integer.parseInt(hidTextField.getText());
                updateQuery.append("hid = ?, ");
                params.add(hospId);
            }

            // Remove the trailing comma and space from the query
            updateQuery.delete(updateQuery.length() - 2, updateQuery.length());

            updateQuery.append(" WHERE did = ?");
            params.add(doctorId);

            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery.toString());

            // Set the parameter values
            for (int i = 0; i < params.size(); i++) {
                Object paramValue = params.get(i);
                preparedStatement.setObject(i + 1, paramValue);
            }

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Doctor data updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearDFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update doctor data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Invalid input format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteDoctor()
    {
    	int doctorId = Integer.parseInt(didTextField.getText());
    	try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            String deleteQuery = "DELETE FROM doctors WHERE did = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, doctorId);

            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, "Doctor deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearDFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete Doctor", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to connect to the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearDFormFields()
    {
    	didTextField.setText("");;
        dnameTextField.setText("");
        sidTextField.setText("");
        hidTextField.setText("");
    }
    
    private void HospitalList()
    {
    	try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            String selectQuery = "SELECT * FROM hospitals";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Create the table model with column names
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Hospital ID");
            tableModel.addColumn("Hospital Name");
            tableModel.addColumn("Location");
            tableModel.addColumn("Contact");
            tableModel.addColumn("OpeningTime");
            tableModel.addColumn("ClosingTime");
            tableModel.addColumn("Rating");

            // Populate the table model with data
            while (resultSet.next()) {
                int hid = resultSet.getInt("hid");
                String hname = resultSet.getString("hname");
                String location = resultSet.getString("hbranch");
                long contact=resultSet.getLong("hphone");
                String OpenTime = resultSet.getString("opening_time");
                String CloseTime = resultSet.getString("closing_time");
                int rating=resultSet.getInt("rating");

                Object[] rowData = {hid, hname, location,contact,OpenTime,CloseTime,rating};
                tableModel.addRow(rowData);
            }

            // Create the table with the table model
            JTable table = new JTable(tableModel);

            // Create a scroll pane and add the table to it
            JScrollPane scrollPane = new JScrollPane(table);

            // Create a panel and add the scroll pane to it
            JPanel panel = new JPanel();
            panel.add(scrollPane);

            // Create a frame and add the panel to it
            JFrame frame = new JFrame("Hospital Data");
            //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed to connect to the database");
        }
    }
    
    private void DoctorList()
    {
    	try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "aniruth", "vasavi")) {
            String selectQuery = "SELECT * FROM doctors";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Create the table model with column names
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Doctor ID");
            tableModel.addColumn("Doctor Name");
            tableModel.addColumn("Specialization ID");
            tableModel.addColumn("Hospital ID");

            // Populate the table model with data
            while (resultSet.next()) {
                int did = resultSet.getInt("did");
                String dname = resultSet.getString("dname");
                int specialization = resultSet.getInt("sid");
                int hid = resultSet.getInt("hid");

                Object[] rowData = {did, dname, specialization, hid};
                tableModel.addRow(rowData);
            }

            // Create the table with the table model
            JTable table = new JTable(tableModel);

            // Create a scroll pane and add the table to it
            JScrollPane scrollPane = new JScrollPane(table);

            // Create a panel and add the scroll pane to it
            JPanel panel = new JPanel();
            panel.add(scrollPane);

            // Create a frame and add the panel to it
            JFrame frame = new JFrame("Doctor Data");
            //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Failed to connect to the database");
        }
    }
    private void removePreviousPanel()
    {
    	if(insertPanel==null)
    	{
    		getContentPane().remove(insertPanel);
    	}
    	if(updatePanel==null)
    	{
    		getContentPane().remove(updatePanel);
    	}
    	if(patientPanel==null)
    	{
    		getContentPane().remove(patientPanel);
    	}
    	if(hospitalPanel==null)
    	{
    		getContentPane().remove(hospitalPanel);
    	}
    	if(deletePanel==null)
    	{
    		getContentPane().remove(deletePanel);
    	}
    	if(appointmentPanel==null)
    	{
    		getContentPane().remove(appointmentPanel);
    	}
    	if(mainPanel==null)
    	{
    		getContentPane().remove(mainPanel);
    	}
    	if(mainPanel1==null)
    	{
    		getContentPane().remove(mainPanel1);
    	}
    	if(mainPanel2==null)
    	{
    		getContentPane().remove(mainPanel2);
    	}
    	if(doctorPanel==null)
    	{
    		getContentPane().remove(doctorPanel);
    	}
    	
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AyushHospitalFinderForm().setVisible(true);
            }
        });
    }
}
