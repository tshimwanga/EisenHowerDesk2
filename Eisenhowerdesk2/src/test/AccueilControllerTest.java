package test;


import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllers.AccueilController;
import db.DataBase;
import db.TacheData;
import db.SousTacheData;


class AccueilControllerTest {

    private Connection connection;
    private AccueilController controller;

    @BeforeEach
    void setUp() {
        // Connexion à la base de données de test
        connection = DataBase.connectDb();
        assertNotNull(connection, "La connexion à la base de données a échoué");

        // Initialisation du contrôleur avec la connexion à la base de données
        controller = new AccueilController();
    }

    @AfterEach
    void tearDown() {
        // Fermeture de la connexion après chaque test
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    

    @Test
    void testSelectInBdIUTask() {
        // Exécuter la méthode à tester
        List<TacheData> tasks = controller.SelectInBdIUTask();

        // Vérifier le résultat
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        
    }
    
    @Test
    void testSelectInBdIBNUTask() {
        // Exécuter la méthode à tester
        List<TacheData> tasks = controller.SelectInBdIBNUTask();

        // Vérifier le résultat
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        
    }
    
    @Test
    void testSelectInBdNIBUTask() {
        // Exécuter la méthode à tester
        List<TacheData> tasks = controller.SelectInBdNIBUTask();

        // Vérifier le résultat
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        
    }
    
    @Test
    void testSelectInBdNIANUTask() {
        // Exécuter la méthode à tester
        List<TacheData> tasks = controller.SelectInBdNIANUTask();

        // Vérifier le résultat
        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        
    }
    @Test
    void testSelectInBdIUSubTask() {
    	int taskId = 1;
        // Exécuter la méthode à tester
        List<SousTacheData> sttasks = controller.SelectInBdIUSubTask(taskId);

        // Vérifier le résultat
        assertNotNull(sttasks);
        
        
    }
    
    @Test
    void testSelectInBdIBNUSubTask() {
    	int taskId = 1;
        // Exécuter la méthode à tester
        List<SousTacheData> sttasks = controller.SelectInBdIBNUSubTask(taskId);

        // Vérifier le résultat
        assertNotNull(sttasks);
        
        
    }
    @Test
    void testSelectInBdNIBUSubTask() {
    	int taskId = 1;
        // Exécuter la méthode à tester
        List<SousTacheData> sttasks = controller.SelectInBdNIBUSubTask(taskId);

        // Vérifier le résultat
        assertNotNull(sttasks);
        
        
    }
    @Test
    void testSelectInBdNIANUSubTask() {
    	int taskId = 1;
        // Exécuter la méthode à tester
        List<SousTacheData> sttasks = controller.SelectInBdNIANUSubTask(taskId);

        // Vérifier le résultat
        assertNotNull(sttasks);
        
        
    }
    
    

}

