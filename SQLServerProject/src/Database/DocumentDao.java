/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Database.interfaces.IDocumentDAO;
import javax.swing.JTable;
import model.Document;

/**
 *
 * @author parve
 */
public class DocumentDao extends Executioner implements IDocumentDAO {

    static int countForMyDoc = 1;

    @Override
    public void createDocument(Document document) {

        String query = "INSERT into Document (DocumentType,ClientId,DocumentLocation) Values"
                + " ('" + document.getDocumentType() + "','" + document.getClientId() + "','" + document.getDocumentLocation() + "' )";
        String successMessage = "Document Added!!";
        String failedMessage = "Document adding failed!!";

        executeInsertQuery(query, successMessage, failedMessage);
    }

    @Override
    public void viewMyDocument(Document document) {
        String query = "SELECT D.DocumentId,D.DocumentType,C.FirstName+' '+C.LastName AS 'Client Name',D.DocumentLocation From Document D INNER JOIN CLIENT C ON D.ClientId = C.ClientId"
                + " AND D.AdvocateId ='" + document.getAdvocateId() + "'";

        String successMessage = "Table Showed For My Documents.";
        String failure = "Failed";
        JTable table = document.getjTable();
        executeMyDocument(query, successMessage, failure, table, countForMyDoc);
        countForMyDoc = countForMyDoc + 1;
    }

    @Override
    public void findDocumentId(Document document) {
        String query = "Select DocumentId From Document";
        String successMessage = "ID Exists.";
        String failedMessage = "ID Doesn't Exist";
        String savedId = document.getDocumentId();
        executeFindDocId(query, successMessage, failedMessage, savedId);
    }

    public void modifyMyDocumentType(Document document) {

        String query = "UPDATE DOCUMENT SET DocumentType ='" + document.getDocumentType() + "' WHERE DocumentId = '" + document.getDocumentId() + "' AND AdvocateId = '"+document.getAdvocateId() + "' "; 
        String successMessage = "UPDATED.";
        String failedMessage = "Failed";
        executeModifyDocumentType(query,successMessage,failedMessage);
    }
    public  void modifyMyDocumentLocation(Document document)
    {
         String query = "UPDATE DOCUMENT SET DocumentLocation ='" + document.getDocumentLocation() + "' WHERE DocumentId = '" + document.getDocumentId() + "' AND AdvocateId = '"+document.getAdvocateId() + "' "; 
           String successMessage = "UPDATED.";
        String failedMessage = "Failed";
        executeModifyMyDocumentLocation(query,successMessage,failedMessage);
    }
}
/*
 
 */
