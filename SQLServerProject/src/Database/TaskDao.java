/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Database.interfaces.ITaskDAO;
import model.Task;

import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Advocate;


/**
 *
 * @author parve
 */
public class TaskDao extends Executioner implements ITaskDAO {
    
     static int countForShowTask = 1;
    @Override
    public void createAdvocate(Task task) {
        String query = "INSERT into Tasks (TaskDetails,Deadline,TaskPriority,LawyerId,TaskStatus,CreatedTime) Values"
                + " ('" + task.getTaskDetails() + "','" + task.getDeadline() + "','" + task.getTaskPriority() + "','" + task.getLawyerId()
                + "','" + task.getTaskStatus() + "','"+ task.getCreatedTime() + "' )";
        String successMessage = "Task added!!";
        String failedMessage = "Task adding failed";

       executeInsertQuery(query, successMessage, failedMessage);
    }
     @Override
      public   void showTasks(Task task)
        {
              String query = "SELECT TaskDetails ,Deadline,TaskPriority ,TaskStatus FROM TASKS Where LawyerId = '"+task.getLawyerId()+"' ORDER BY Deadline "; 
                     
              String successMessage ="showed";
          String failedMessage ="failed";
          JTable jtable = task.getTable();
         executeshowTasks(query,successMessage,failedMessage,jtable,countForShowTask);
        countForShowTask = countForShowTask + 1;
                     
        }
}
/*
 {
          String query = "SELECT TaskDetails ,Deadline,TaskPriority ,TaskStatus FROM TASKS Where LawyerId = '"+task.getLawyerId()+"' ORDER BY Deadline ";
          String successMessage ="showed";
          String failedMessage ="failed";
          JTable jtable = task.getTable();
          //executeshowTasks(query,successMessage,failedMessage,countForShowTask);
      }
*/
