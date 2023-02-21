/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import DatabaseCredentials.DatabaseCredentials;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author parve
 */
public class Executioner {

    private Connection connection;

    DatabaseCredentials dbc = new DatabaseCredentials();
    String databaseUrl = dbc.getDatabaseUrl();
    String AdminPassword = "123456";
    PreparedStatement prepareStatement = null;
    ResultSet result = null;
    Statement statement = null;
    DefaultTableModel tblModel;

    void executeInsertQuery(String query, String successMessage, String failureMessage) {
        try {
            connection = DriverManager.getConnection(databaseUrl, "sa", AdminPassword);
            statement = connection.createStatement();
            statement.executeUpdate(query);

            JOptionPane.showMessageDialog(null, successMessage,
                    "Success", JOptionPane.OK_OPTION);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, failureMessage,
                    "Failure1!!", JOptionPane.WARNING_MESSAGE);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Connection closing failed",
                        "Failure2!!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    void executeFindQuery(String query, String successMessage, String failureMessage, String userInput) {
        boolean flag = false;
        try {
            connection = DriverManager.getConnection(databaseUrl, "sa", AdminPassword);
            prepareStatement = connection.prepareStatement(query);
            result = prepareStatement.executeQuery();
            while (result.next()) {
                int retrievedID = result.getInt("ClientId");
                String ID = Integer.toString(retrievedID);
                if (ID.equals(userInput)) {
                    //  System.out.println(retrievedID);
                    //  System.out.println(ID);
                    flag = true;
                    break;
                }

            }

        } catch (SQLException ex) {

        }
        if (flag != true) {
            JOptionPane.showMessageDialog(null, failureMessage,
                    "Failure!!", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, successMessage,
                    "DONE!!", JOptionPane.OK_OPTION);
        }
    }

    int executeFindClientID(String query, String successMessage, String failureMessage, String userInput) {
        boolean flag = false;
        int savedID = -1;
        try {
            connection = DriverManager.getConnection(query);
            PreparedStatement prepareStatement = connection.prepareStatement(query);

            prepareStatement.setString(1, userInput);
            result = prepareStatement.executeQuery();
            if (result.next()) {
                savedID = result.getInt("ClientId");
            }

        } catch (SQLException ex) {
        }
        return savedID;
    }

    void executeTable(String query, String successMessage, String failedMessage, JTable jt, int count) {

        tblModel = (DefaultTableModel) jt.getModel();
        jt.setModel(tblModel);
        if (count > 1) {
            int i = jt.getRowCount();

            for (int j = 0; j < i; j++) {
                tblModel.removeRow(0);
            }
        }

        try {
            connection = DriverManager.getConnection(databaseUrl, "sa", "123456");
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                String SID = result.getString("AdvocateId");
                String FirstName = result.getString("FirstName");
                String LastName = result.getString("LastName");
                String Email = result.getString("Email");
                String phoneNumber = result.getString("PhoneNumber");
                String BirthDate = result.getString("DateOfBirth");
                String Gender = result.getString("Gender");
                String address = result.getString("Address_");
                String tbData[] = {SID, FirstName, LastName, Email, phoneNumber, BirthDate, Gender, address};

                tblModel.addRow(tbData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    void executeCaseTable(String query, String successMessage, String failedMessage, JTable jtable, int count) {

        //  Model.setRowCount(0);
        DefaultTableModel model1 = (DefaultTableModel) jtable.getModel();
        jtable.setModel(model1);
        if (count > 1) {
            int i = jtable.getRowCount();
            for (int j = 0; j < i; j++) {
                model1.removeRow(0);
            }
        }
        try {
            connection = DriverManager.getConnection(databaseUrl, "sa", "123456");
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                String CaseId = result.getString("CaseId");
                String ClientName = result.getString("Client Name");
                String Category = result.getString("Category");
                String CaseLocation = result.getString("CaseLocation");
                String OpeningDate = result.getString("OpeningDate");
                String caseWorkerName = result.getString("Case Worker");
                String caseIntroducer = result.getString("Case Introducer");
                String amount = result.getString("Amount");
                String tableData[] = {CaseId, ClientName, Category, CaseLocation, OpeningDate, caseWorkerName, caseIntroducer, amount};
                model1.addRow(tableData);
//System.out.println("id "+CaseId+" client name "+ClientName+" Category"+Category+" loc"+CaseLocation+" openin date"+OpeningDate);
//System.out.println("case worker "+caseWorkerName+" case intro "+caseIntroducer+" amount "+amount);
            }

            //connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    void executeFindAdvocate(String query, String successMessage, String failedMessage, String userInput) {
        boolean flag = false;
        try {
            connection = DriverManager.getConnection(databaseUrl, "sa", AdminPassword);
            prepareStatement = connection.prepareStatement(query);
            result = prepareStatement.executeQuery();
            while (result.next()) {
                int retrievedID = result.getInt("AdvocateId");

                String ID = Integer.toString(retrievedID);
                if (ID.equals(userInput)) {
                    flag = true;
                    break;
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (flag != true) {
            JOptionPane.showMessageDialog(null, failedMessage,
                    "Failed to Find Advocate ID!!", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, successMessage,
                    "DONE!!", JOptionPane.OK_OPTION);
        }
    }

    void executeCaseTableForMyFolder(String query, String successMessage, String failedMessage, JTable jtable, int count) {

        DefaultTableModel model1 = (DefaultTableModel) jtable.getModel();
        jtable.setModel(model1);
        if (count > 1) {
            int i = jtable.getRowCount();
            for (int j = 0; j < i; j++) {
                model1.removeRow(0);
            }
        }

        try {
            connection = DriverManager.getConnection(databaseUrl, "sa", "123456");
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                String CaseId = result.getString("CaseId");
                String ClientName = result.getString("Client Name");
                String Category = result.getString("Category");
                String caseIntroducer = result.getString("Case Introducer");
                String CaseLocation = result.getString("CaseLocation");
                String OpeningDate = result.getString("OpeningDate");
                String amount = result.getString("Amount");
                String tableData[] = {CaseId, ClientName, Category, caseIntroducer, CaseLocation, OpeningDate, amount};
                model1.addRow(tableData);
//System.out.println("id "+CaseId+" client name "+ClientName+" Category"+Category+" loc"+CaseLocation+" openin date"+OpeningDate);
//System.out.println("case worker "+caseWorkerName+" case intro "+caseIntroducer+" amount "+amount);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    void executeCaseTableForMyFolderCategory(String query, String successMessage, String failedMessage, JTable jtable, int count) {

        DefaultTableModel model1 = (DefaultTableModel) jtable.getModel();
        jtable.setModel(model1);
        if (count > 1) {
            int i = jtable.getRowCount();
            for (int j = 0; j < i; j++) {
                model1.removeRow(0);
            }
        }

        try {
            connection = DriverManager.getConnection(databaseUrl, "sa", "123456");
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                String CaseId = result.getString("CaseId");
                String ClientName = result.getString("Client Name");
                String Category = result.getString("Category");
                String caseIntroducer = result.getString("Case Introducer");
                String caseWorker = result.getString("Case Worker");

                String CaseLocation = result.getString("CaseLocation");
                String OpeningDate = result.getString("OpeningDate");
                String amount = result.getString("Amount");
                String tableData[] = {CaseId, ClientName, Category, caseIntroducer, caseWorker, CaseLocation, OpeningDate, amount};
                model1.addRow(tableData);
//System.out.println("id "+CaseId+" client name "+ClientName+" Category"+Category+" loc"+CaseLocation+" openin date"+OpeningDate);
//System.out.println("case worker "+caseWorkerName+" case intro "+caseIntroducer+" amount "+amount);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    void executeCaseTableForMyFolderLocation(String query, String successMessage, String failedMessage, JTable jtable, int count) {

        DefaultTableModel model1 = (DefaultTableModel) jtable.getModel();
        jtable.setModel(model1);
        if (count > 1) {
            int i = jtable.getRowCount();
            for (int j = 0; j < i; j++) {
                model1.removeRow(0);
            }
        }

        try {
            connection = DriverManager.getConnection(databaseUrl, "sa", "123456");
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                String CaseId = result.getString("CaseId");
                String ClientName = result.getString("Client Name");
                String Category = result.getString("Category");
                String caseIntroducer = result.getString("Case Introducer");
                String caseWorker = result.getString("Case Worker");

                String CaseLocation = result.getString("CaseLocation");
                String OpeningDate = result.getString("OpeningDate");
                String amount = result.getString("Amount");
                String tableData[] = {CaseId, ClientName, Category, caseIntroducer, caseWorker, CaseLocation, OpeningDate, amount};
                model1.addRow(tableData);
//System.out.println("id "+CaseId+" client name "+ClientName+" Category"+Category+" loc"+CaseLocation+" openin date"+OpeningDate);
//System.out.println("case worker "+caseWorkerName+" case intro "+caseIntroducer+" amount "+amount);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
