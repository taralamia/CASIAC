/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database.interfaces;

import model.Client;

/**
 *
 * @author parve
 */
public interface IClientDAO {
    void createClient (Client client);
    void FindClient(Client client);
    int FindClientID(Client client);
    void showClient(Client client);
}
