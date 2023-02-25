/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database.interfaces;

import model.Document;

/**
 *
 * @author parve
 */
public interface IDocumentDAO {
    
    void createDocument (Document document);
    void viewMyDocument(Document document);
    void findDocumentId(Document document);
    void modifyMyDocumentType(Document document);
    void modifyMyDocumentLocation(Document document);
}
