package controllers;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import db.DataBase;
import db.TacheData;
import db.SousTacheData;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;




public class AccueilController implements Initializable {
	
	//Composants of main table
	 @FXML
	private AnchorPane accueilmain;
	//Coté gauche
	@FXML
    private Button close;
	
	@FXML
    private Button minimize;
	
	@FXML
    private Button homebtn;
	
	@FXML
    private Button deletedbtn_main;

	@FXML
    private Button archivedbtn_main;
	
	@FXML
    private Button morebtn;
	
	@FXML
    private HBox generalview;
	
	@FXML
	private Canvas IU;
	
	@FXML
	private Button addnewtaskbtnIU;

	@FXML
	private Canvas IBNU;
	
	@FXML
    private Button addnewtaskbtnIBNU;
	
	@FXML
	private Canvas NIBU;
 
	@FXML
	private Button addnewtaskbtnNIBU;
	
	@FXML
	private Canvas NIANU;
	
	@FXML
    private Button addnewtaskbtnNIANU;
	
	//Coté droit
	
	//Composants de la table deleted
    @FXML
    private AnchorPane Deletetab;
    
    @FXML
    private TableView<TacheData> tableauofdeled;
    
    @FXML
    private TableColumn<TacheData, String> taskcoldel; 
    
    @FXML
    private TableColumn<TacheData, String> descripcoldel;

    @FXML
    private TableColumn<TacheData, String> deadlinecoldel; 
    
    @FXML
    private TableColumn<TacheData, String> timecoldel; 
    
    @FXML
    private TableColumn<TacheData, String> donecoldel; 
    
    @FXML
    private Button archivedbtndel;
    
    @FXML
    private Button defdeleted;
    
    @FXML
    private MenuButton recoverchoicedel;
    
    @FXML
    private MenuItem choiceiudel;
    
    @FXML
    private MenuItem choiceibnudel;
    
    @FXML
    private MenuItem choicenibudel;
    
    @FXML
    private MenuItem choicenianudel;
    
    @FXML
    private TextField TaskDelfield;
    
    @FXML
    private TextField DescripDelfield;
    
    @FXML
    private DatePicker DeadDelfield;
    
    @FXML
    private TextField TimeDelfield;
    
    @FXML
    private ComboBox<String> DoneDelfield;

    //Composants de la fenetre archived
    
    @FXML
    private AnchorPane archivedtab;
    
    @FXML
    private TableView<TacheData> tableauarchived;
    
    @FXML
    private TableColumn<TacheData, String> taskarchi; 
    
    @FXML
    private TableColumn<TacheData, String> descriparchi;

    @FXML
    private TableColumn<TacheData, String> deadarchi; 
    
    @FXML
    private TableColumn<TacheData, String> timearchi; 
    
    @FXML
    private TableColumn<TacheData, String> donearchi;
    
    @FXML
    private MenuButton recoverchoicearchi;
    
    @FXML
    private MenuItem choiceiuarchi;
    
    @FXML
    private MenuItem choiceibnuarchi;
    
    @FXML
    private MenuItem choicenibuarchi;
    
    @FXML
    private MenuItem choicenianuarchi;
    
    @FXML
    private Button deletedbtnarchi;
    
    @FXML
    private TextField TaskArchivedfield;
    
    @FXML
    private TextField DescripArchivedfield;
    
    @FXML
    private DatePicker DeadArchivedfield;
    
    @FXML
    private TextField TimeArchivedfield;
    
    @FXML
    private ComboBox<String> DoneArchivedfield;
	//composant de la fenetre help
  
    @FXML
    private AnchorPane helptab;
    
//composants de la fenetre viewtaskIU
    
    @FXML
    private AnchorPane viewtaskofiu;
    
    @FXML
    private TextField taskmodifIU;
    
    @FXML
    private TextField descripmodifIU;
    
    @FXML
    private DatePicker deadlinemodifIU;
    
    @FXML
    private TextField timemodifIU;
    
    @FXML
    private ComboBox<String> donemodifIU;
    
    @FXML
    private Button modifOKiu;
    
    @FXML
    private Button addsubiubtn;
    
    @FXML
    private Button archivedtaskbtniu;
    
    @FXML
    private Button deletedtaskbtniu;
    
    @FXML
    private MenuButton changefolderiubtn;
    
    @FXML
    private MenuItem choiceibnuIU;
    
    @FXML
    private MenuItem choicenibuIU;
    
    @FXML
    private MenuItem choicenianuIU;
   
    @FXML
    private Button backiu;
    
    @FXML
    private Slider rateimpmodifIU;
    
    @FXML
    private Slider rateurgmodifIU;
    
    @FXML
    private Canvas stIU;
    
//composants de la fenetre viewtaskIBNU
    
    @FXML
    private AnchorPane viewtaskofibnu;
    
    @FXML
    private TextField taskmodifIBNU;
    
    @FXML
    private TextField descripmodifIBNU;
    
    @FXML
    private DatePicker deadlinemodifIBNU;
    
    @FXML
    private TextField timemodifIBNU;
    
    @FXML
    private ComboBox<String> donemodifIBNU;
    
    @FXML
    private Button modifOKibnu;
    
    @FXML
    private Button addsubibnubtn;
    
    @FXML
    private Button archivedtaskbtnibnu;
    
    @FXML
    private Button deletedtaskbtnibnu;
    
    @FXML
    private MenuButton changefolderibnubtn;
    
    @FXML
    private MenuItem choiceiuIBNU;
    
    @FXML
    private MenuItem choicenibuIBNU;
    
    @FXML
    private MenuItem choicenianuIBNU;
    
    @FXML
    private Button backibnu;
    
    @FXML
    private Canvas stIBNU;
    
    @FXML
    private Slider rateimpmodifIBNU;
    
    @FXML
    private Slider rateurgmodifIBNU;
    
    
//composants de la fenetre viewtaskNIBU
    
    @FXML
    private AnchorPane viewtaskofnibu;
    
    @FXML
    private Button addsubnibubtn;
    
    @FXML
    private TextField taskmodifNIBU;
    
    @FXML
    private TextField descripmodifNIBU;
    
    @FXML
    private DatePicker deadlinemodifNIBU;
    
    @FXML
    private TextField timemodifNIBU;
    
    @FXML
    private ComboBox<String> donemodifNIBU;
    
    @FXML
    private Button modifOKnibu;
    
    @FXML
    private Button archivedtaskbtnnibu;
    
    @FXML
    private Button deletedtaskbtnnibu;
    
    @FXML
    private MenuButton changefoldernibubtn;
    
    @FXML
    private MenuItem choiceiuNIBU;
    
    @FXML
    private MenuItem choiceibnuNIBU;
    
    @FXML
    private MenuItem choicenianuNIBU;
    
    @FXML
    private Button backnibu;
    
    @FXML
    private Canvas stNIBU;
    
    @FXML
    private Slider rateimpmodifNIBU;
    
    @FXML
    private Slider rateurgmodifNIBU;
    
//composants de la fenetre viewtasNIANU
    
    @FXML
    private AnchorPane viewtaskofnianu;
    
    @FXML
    private Button addsubnianubtn;
    
    @FXML
    private TextField taskmodifNIANU;
    
    @FXML
    private TextField descripmodifNIANU;
    
    @FXML
    private DatePicker deadlinemodifNIANU;
    
    @FXML
    private TextField timemodifNIANU;
    
    @FXML
    private ComboBox<String> donemodifNIANU;
    
    @FXML
    private Button modifOKnianu;
    
    @FXML
    private Button archivedtaskbtnnianu;
    
    @FXML
    private Button deletedtaskbtnnianu;
    
    @FXML
    private MenuButton changefoldernianubtn;
    
    @FXML
    private MenuItem choiceiuNIANU;
    
    @FXML
    private MenuItem choiceibnuNIANU;
    
    @FXML
    private MenuItem choicenibuNIANU;
    
    @FXML
    private Button backnianu;
   
    @FXML
    private Canvas stNIANU;
    
    @FXML
    private Slider rateimpmodifNIANU;
    
    @FXML
    private Slider rateurgmodifNIANU;
    
    //composant de vue st IU
    @FXML
    private AnchorPane viewsubtaskofiu;
    @FXML
    private TextField viewsubtaskfieldiu;
    
    @FXML
    private TextField viewdescripfieldiu;
    
    @FXML
    private DatePicker viewdeadlinefieldiu;
    
    @FXML
    private TextField viewtimefieldiu;
    
    @FXML
    private ComboBox<String> viewdonefieldiu;
    
    @FXML
    private Slider viewrateimpfieldiu;
    
    @FXML
    private Slider viewrateurgfieldiu;
    
    @FXML
    private Button deletesubtiu;
    
    @FXML
    private Button backstIU;
    
    @FXML
    private Button updateviewstiu;
    
  //composant de vue st IBNU
    @FXML
    private AnchorPane viewsubtaskofibnu;
    
    @FXML
    private TextField viewsubtaskfieldibnu;
    
    @FXML
    private TextField viewdescripfieldibnu;
    
    @FXML
    private DatePicker viewdeadlinefieldibnu;
    
    @FXML
    private TextField viewtimefieldibnu;
    
    @FXML
    private ComboBox<String> viewdonefieldibnu;
    
    @FXML
    private Button deletestibnu;
    
    @FXML
    private Button updateviewstibnu;
    
    @FXML
    private Slider viewrateimpfieldibnu;
    
    @FXML
    private Slider viewrateurgfieldibnu;
    
    @FXML
    private Button backstIBNU;
    
  //composant de vue st NIBU
    @FXML
    private AnchorPane viewsubtaskofnibu;
    
    @FXML
    private TextField viewsubtaskfieldnibu;
    
    @FXML
    private TextField viewdescripfieldnibu;
    
    @FXML
    private DatePicker viewdeadlinefieldnibu;
    
    @FXML
    private TextField viewtimefieldnibu;
    
    @FXML
    private Button backstNIBU;
    
    @FXML
    private ComboBox<String> viewdonefieldnibu;
    
    @FXML
    private Button deletestnibu;
    
    @FXML
    private Slider viewrateimpfieldnibu;
    
    @FXML
    private Slider viewrateurgfieldnibu;
    
    @FXML
    private Button updatestnibu;
    
  //composant de vue st NIANU
    @FXML
    private AnchorPane viewsubtaskofnianu;
    
    @FXML
    private TextField viewsubtaskfieldnianu;
    
    @FXML
    private TextField viewdescripfieldnianu;
    
    @FXML
    private DatePicker viewdeadlinefieldnianu;
    
    @FXML
    private Button backstNIANU;
    
    @FXML
    private TextField viewtimefieldnianu;
    
    @FXML
    private ComboBox<String> viewdonefieldnianu;
    
    @FXML
    private Button deletestnianu;

    @FXML
    private Button updatestnianu;
    
    @FXML
    private Slider viewrateimpfieldnianu;
    
    @FXML
    private Slider viewrateurgfieldnianu;
 


    /*	Idée d'amélioration de l'app
     * ------------------------------
     * Mettre l'option de création d'un compte et de déconnection
    

     */
    
    
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    
  //Quand je clique sur ajouter une tache 
    
    public void addTaskWindowIU() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/AddTask.fxml"));
            assert loader != null : "FXML Loader a échoué"; // Assertion pour FXML Loader

            Parent root = loader.load();
            assert root != null : "Echec de load root"; // Assertion pour loaded root
            AddTaskController addTaskController = loader.getController();
            // Appel de la méthode du contrôleur pour modifier la visibilité des éléments
            addTaskController.configureElementsVisibility(true, false, false, false);
            Stage deleteStage = new Stage();
            deleteStage.setScene(new Scene(root));
            deleteStage.initModality(Modality.WINDOW_MODAL);
            deleteStage.initOwner(addnewtaskbtnIU.getScene().getWindow()); 

            // Show the task interface
            deleteStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addTaskWindowIBNU() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/AddTask.fxml"));
            assert loader != null : "FXML Loader a échoué"; // Assertion pour FXML Loader

            Parent root = loader.load();
            assert root != null : "Echec de load root"; // Assertion pour loaded root
            AddTaskController addTaskController = loader.getController();
            // Appel de la méthode du contrôleur pour modifier la visibilité des éléments
            addTaskController.configureElementsVisibility(false, true, false, false);
            Stage deleteStage = new Stage();
            deleteStage.setScene(new Scene(root));
            deleteStage.initModality(Modality.WINDOW_MODAL);
            deleteStage.initOwner(addnewtaskbtnIBNU.getScene().getWindow()); 

            // Show the task interface
            deleteStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addTaskWindowNIBU() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/AddTask.fxml"));
            assert loader != null : "FXML Loader a échoué"; // Assertion pour FXML Loader

            Parent root = loader.load();
            assert root != null : "Echec de load root"; // Assertion pour loaded root
            AddTaskController addTaskController = loader.getController();
            // Appel de la méthode du contrôleur pour modifier la visibilité des éléments
            addTaskController.configureElementsVisibility(false, false, true, false);
            Stage deleteStage = new Stage();
            deleteStage.setScene(new Scene(root));
            deleteStage.initModality(Modality.WINDOW_MODAL);
            deleteStage.initOwner(addnewtaskbtnNIBU.getScene().getWindow()); 

            // Show the task interface
            deleteStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addTaskWindowNIANU() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/AddTask.fxml"));
            assert loader != null : "FXML Loader a échoué"; // Assertion pour FXML Loader

            Parent root = loader.load();
            assert root != null : "Echec de load root"; // Assertion pour loaded root
            AddTaskController addTaskController = loader.getController();
            // Appel de la méthode du contrôleur pour modifier la visibilité des éléments
            addTaskController.configureElementsVisibility(false, false, false, true);
            Stage deleteStage = new Stage();
            deleteStage.setScene(new Scene(root));
            deleteStage.initModality(Modality.WINDOW_MODAL);
            deleteStage.initOwner(addnewtaskbtnNIANU.getScene().getWindow()); 

            // Show the task interface
            deleteStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
  //  
    
    // recupère toutes les taches dans la bd
    public ObservableList<TacheData> SelectInBdIUTask() {

        ObservableList<TacheData> listDataIU = FXCollections.observableArrayList();

        // on selectionne dans la bd la liste des taches IU

        String sql = "SELECT * FROM tache WHERE folder = 'Important and Urgent'";

        
        
        connect = DataBase.connectDb();

        try {
            // Utilisation des assertions pour vérifier que la préparation de la requête SQL ne génère pas d'erreur
            assert connect != null : "La connexion à la base de données n'a pas été établie.";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            TacheData TacheD;

            while (result.next()) {
                TacheD = new TacheData(
                        result.getString("name"),
                        result.getString("description"),
                        result.getDate("deadline"),
                        result.getString("time"),
                        result.getString("done"),
                        result.getInt("rateimp"),
                        result.getInt("rateurg"),
                        result.getString("folder"));
                listDataIU.add(TacheD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDataIU;
    }
    
    public ObservableList<TacheData> SelectInBdIBNUTask() {

        ObservableList<TacheData> listDataIBNU = FXCollections.observableArrayList();

        // on selectionne dans la bd la liste des taches IU

        String sql = "SELECT * FROM tache WHERE folder = 'Important but not Urgent'";

        

        connect = DataBase.connectDb();

        try {
            // Utilisation des assertions pour vérifier que la préparation de la requête SQL ne génère pas d'erreur
            assert connect != null : "La connexion à la base de données n'a pas été établie.";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            TacheData TacheD;

            while (result.next()) {
                TacheD = new TacheData(
                        result.getString("name"),
                        result.getString("description"),
                        result.getDate("deadline"),
                        result.getString("time"),
                        result.getString("done"),
                        result.getInt("rateimp"),
                        result.getInt("rateurg"),
                        result.getString("folder"));
                listDataIBNU.add(TacheD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDataIBNU;
    }
    
    public ObservableList<TacheData> SelectInBdNIBUTask() {

        ObservableList<TacheData> listDataNIBU = FXCollections.observableArrayList();

        // on selectionne dans la bd la liste des taches IU

        String sql = "SELECT * FROM tache WHERE folder = 'Not Important but Urgent'";

        

        connect = DataBase.connectDb();

        try {
            // Utilisation des assertions pour vérifier que la préparation de la requête SQL ne génère pas d'erreur
            assert connect != null : "La connexion à la base de données n'a pas été établie.";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            TacheData TacheD;

            while (result.next()) {
                TacheD = new TacheData(
                        result.getString("name"),
                        result.getString("description"),
                        result.getDate("deadline"),
                        result.getString("time"),
                        result.getString("done"),
                        result.getInt("rateimp"),
                        result.getInt("rateurg"),
                        result.getString("folder"));
                listDataNIBU.add(TacheD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDataNIBU;
    }
    
    public ObservableList<TacheData> SelectInBdNIANUTask() {

        ObservableList<TacheData> listDataNIANU = FXCollections.observableArrayList();

        // on selectionne dans la bd la liste des taches IU

        String sql = "SELECT * FROM tache WHERE folder = 'Not Important and not Urgent'";

      

        connect = DataBase.connectDb();

        try {
            // Utilisation des assertions pour vérifier que la préparation de la requête SQL ne génère pas d'erreur
            assert connect != null : "La connexion à la base de données n'a pas été établie.";
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            TacheData TacheD;

            while (result.next()) {
                TacheD = new TacheData(
                        result.getString("name"),
                        result.getString("description"),
                        result.getDate("deadline"),
                        result.getString("time"),
                        result.getString("done"),
                        result.getInt("rateimp"),
                        result.getInt("rateurg"),
                        result.getString("folder"));
                listDataNIANU.add(TacheD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDataNIANU;
    }

    public ObservableList<TacheData> SelectInBdDeletedTask(){
      	
      	
      	ObservableList<TacheData> listDataDelete = FXCollections.observableArrayList();
      	
      	// on selectionne dans la bd la liste des taches pour Deleted
      	
      	String sql = "SELECT * FROM tache WHERE folder = 'Deleted'"; 
      			
      	connect = DataBase.connectDb();
      	
      	try {
      		prepare = connect.prepareStatement(sql);
              result = prepare.executeQuery();
              
              TacheData TacheD;
              
              while (result.next()) {
              	TacheD = new TacheData(
                          result.getString("name"),
                          result.getString("description"),
                          result.getDate("deadline"),
                          result.getString("time"),
                          result.getString("done"),
                          result.getInt("rateimp"),
                          result.getInt("rateurg"),
                          result.getString("folder"));
              	listDataDelete.add(TacheD);}
              
      	}catch (Exception e) {e.printStackTrace();}
      	return listDataDelete;
      			
      			
      }
    
    public ObservableList<TacheData> SelectInBdArchivedTask(){
    	
    	
    	ObservableList<TacheData> listDataArchive = FXCollections.observableArrayList();
    	
    	// on selectionne dans la bd la liste des taches pour Deleted
    	
    	String sql = "SELECT * FROM tache WHERE folder = 'Archived'"; 
    			
    	connect = DataBase.connectDb();
    	
    	try {
    		prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            TacheData TacheD;
            
            while (result.next()) {
            	TacheD = new TacheData(
                        result.getString("name"),
                        result.getString("description"),
                        result.getDate("deadline"),
                        result.getString("time"),
                        result.getString("done"),
                        result.getInt("rateimp"),
                        result.getInt("rateurg"),
                        result.getString("folder"));
            	listDataArchive.add(TacheD);}
            
    	}catch (Exception e) {e.printStackTrace();}
    	return listDataArchive;
    			
    			
    }

//Afficher les taches prise dans la fonction précédentes 
	
	
	
 // Liste pour stocker les coordonnées des tâches affichées
    List<Point2D> taskCoordinates = new ArrayList<>();

    public void ShowIUTask() {
        GraphicsContext gc = IU.getGraphicsContext2D();
        assert gc != null : "GraphicsContext ne doit pas être null";
        ObservableList<TacheData> IUTaskList = SelectInBdIUTask();
        assert IUTaskList != null : "IUTaskList ne doit pas être null";

        // Iterate through tasks and handle potential overlaps
        for (TacheData task : IUTaskList) {
            String nomTache = task.getName();
            double urgency = task.getRateurg();
            double importance = task.getRateimp();

            // calcul les cordonnées initiaux selon l'importance et l'urgence
            double x = 10 + (urgency / 10.0) * (IU.getWidth() - 20);
            double y = IU.getHeight() - 10 - (importance / 10.0) * (IU.getHeight() - 20);

            // verifie une supperposition des taches
            boolean overlapFound = true;
            int adjustmentCount = 0;
            while (overlapFound && adjustmentCount < 10) { // Limiter les iterations 
                Point2D potentialCoordinates = new Point2D(x, y);
                overlapFound = taskCoordinates.stream().anyMatch(existingCoordinate ->
                        existingCoordinate.distance(potentialCoordinates) < 10); // ajuster

                if (overlapFound) {
                    // Adjuster les coordinates 
                    x += 10;
                    y += 10;
                    adjustmentCount++;
                }
            }

            // stocker quand il y a pas de supperposition
            if (!overlapFound) {
                taskCoordinates.add(new Point2D(x, y));
            } else {
                
                System.out.println("Warning: Unable to resolve overlap for task: " + nomTache);
            }

            // Dessiner la tâche
            if (nomTache != null) {
            	String nTask = nomTache.length() > 4 ? nomTache.substring(0, 4) : nomTache;

                // Constrain task point to canvas boundaries
                double pointMaxX = IU.getWidth() - 5;  // Adjust for point width
                double pointMaxY = IU.getHeight() - 15; // Adjust for point height and text offset
                x = Math.min(x, pointMaxX);
                y = Math.min(y, pointMaxY);

                // Constrain text to canvas boundaries
                double textMaxX = x + 10 + gc.getFont().getSize() * nTask.length();
                double textMaxY = IU.getHeight() - gc.getFont().getSize();
                if (textMaxX > IU.getWidth()) {
                    // Adjust x if text would go beyond canvas width
                    x -= textMaxX - IU.getWidth();
                }
                if (textMaxY > IU.getHeight()) {
                    // Truncate text if it would go beyond canvas height with a visual indicator
                    nTask = nTask.substring(0, (int) (IU.getHeight() / gc.getFont().getSize() - 1)) + "...";
                }
                if (task.getDeadline() != null) {
                    LocalDate deadline = task.getDeadline().toLocalDate();
                    assert deadline != null : "Deadline should not be null";
                    LocalDate currentDate = LocalDate.now();
                    long daysUntilDeadline = ChronoUnit.DAYS.between(currentDate, deadline);
                    assert daysUntilDeadline >= 0 : "Days until deadline should be positive";

                    if (daysUntilDeadline == 1 || daysUntilDeadline == 0) {
                        gc.setFill(Color.RED);
                    } else if (daysUntilDeadline < 0) {
                        gc.setFill(Color.GRAY);
                    } else {
                        gc.setFill(Color.BLACK);
                    }
                } else {
                    // Tache sans deadline, afficher en noir par défaut
                    gc.setFill(Color.BLACK);
                }

                gc.fillOval(x, y, 5, 5);
                gc.fillText(nTask, x + 10, y + 5);
            } else {
                // Tache sans nom, afficher une indication par défaut
                gc.fillText("Tache sans nom", x + 10, y + 5);
            }
        }
    }

	
	List<Point2D> taskCoordinatesibnu = new ArrayList<>();
	 

    public void ShowIBNUTask() {
        GraphicsContext gc = IBNU.getGraphicsContext2D();
        assert gc != null : "GraphicsContext ne doit pas être null";
        ObservableList<TacheData> IBNUTaskList = SelectInBdIBNUTask();
        assert IBNUTaskList != null : "IUTaskList ne doit pas être null";

        // Iterate through tasks and handle potential overlaps
        for (TacheData task : IBNUTaskList) {
            String nomTache = task.getName();
            double urgency = task.getRateurg();
            double importance = task.getRateimp();
             
            urgency = 10.0 - urgency;
            // calcul les cordonnées initiaux selon l'importance et l'urgence
            double x = IBNU.getWidth() - 10 - (urgency / 10.0) * (IBNU.getWidth() - 20);
            double y = IBNU.getHeight() - 10 - (importance / 10.0) * (IBNU.getHeight() - 20);

            // verifie une supperposition des taches
            boolean overlapFound = true;
            int adjustmentCount = 0;
            while (overlapFound && adjustmentCount < 10) { // Limiter les iterations 
                Point2D potentialCoordinates = new Point2D(x, y);
                overlapFound = taskCoordinatesibnu.stream().anyMatch(existingCoordinate ->
                        existingCoordinate.distance(potentialCoordinates) < 10); // ajuster

                if (overlapFound) {
                    // Adjuster les coordinates 
                    x += 10;
                    y += 10;
                    adjustmentCount++;
                }
            }

            // stocker quand il y a pas de supperposition
            if (!overlapFound) {
                taskCoordinatesibnu.add(new Point2D(x, y));
            } else {
                
                System.out.println("Warning: Unable to resolve overlap for task: " + nomTache);
            }

            // Dessiner la tâche
            if (nomTache != null) {
            	String nTask = nomTache.length() > 4 ? nomTache.substring(0, 4) : nomTache;

                // Constrain task point to canvas boundaries
                double pointMaxX = IBNU.getWidth() - 5;  // Adjust for point width
                double pointMaxY = IBNU.getHeight() - 15; // Adjust for point height and text offset
                x = Math.min(x, pointMaxX);
                y = Math.min(y, pointMaxY);

                // Constrain text to canvas boundaries
                double textMaxX = x + 10 + gc.getFont().getSize() * nTask.length();
                double textMaxY = IBNU.getHeight() - gc.getFont().getSize();
                if (textMaxX > IBNU.getWidth()) {
                    // Adjust x if text would go beyond canvas width
                    x -= textMaxX - IBNU.getWidth();
                }
                if (textMaxY > IBNU.getHeight()) {
                    // Truncate text if it would go beyond canvas height with a visual indicator
                    nTask = nTask.substring(0, (int) (IBNU.getHeight() / gc.getFont().getSize() - 1)) + "...";
                }
                if (task.getDeadline() != null) {
                    LocalDate deadline = task.getDeadline().toLocalDate();
                    assert deadline != null : "Deadline should not be null";
                    LocalDate currentDate = LocalDate.now();
                    long daysUntilDeadline = ChronoUnit.DAYS.between(currentDate, deadline);
                    assert daysUntilDeadline >= 0 : "Days until deadline should be positive";

                    if (daysUntilDeadline == 1 || daysUntilDeadline == 0) {
                        gc.setFill(Color.RED);
                    } else if (daysUntilDeadline < 0) {
                        gc.setFill(Color.GRAY);
                    } else {
                        gc.setFill(Color.BLACK);
                    }
                } else {
                    // Tache sans deadline, afficher en noir par défaut
                    gc.setFill(Color.BLACK);
                }

                gc.fillOval(x, y, 5, 5);
                gc.fillText(nTask, x + 10, y + 5);
            } else {
                // Tache sans nom, afficher une indication par défaut
                gc.fillText("Tache sans nom", x + 10, y + 5);
            }
        }
    }

	
	
List<Point2D> taskCoordinatesnibu = new ArrayList<>();
	
	public void ShowNIBUTask() {
		 GraphicsContext gc = NIBU.getGraphicsContext2D();
	        assert gc != null : "GraphicsContext ne doit pas être null";
	        ObservableList<TacheData> NIBUTaskList = SelectInBdNIBUTask();
	        assert NIBUTaskList != null : "IUTaskList ne doit pas être null";

	        // Iterate through tasks and handle potential overlaps
	        for (TacheData task : NIBUTaskList) {
	            String nomTache = task.getName();
	            double urgency = task.getRateurg();
	            double importance = task.getRateimp();
	            
	            importance = 10.0 - importance;
	            // calcul les cordonnées initiaux selon l'importance et l'urgence
	            double x = 10 + (urgency / 10.0) * (NIBU.getWidth() - 20);
	            double y = 10 + (importance / 10.0) * (NIBU.getHeight() - 20);

	            // verifie une supperposition des taches
	            boolean overlapFound = true;
	            int adjustmentCount = 0;
	            while (overlapFound && adjustmentCount < 10) { // Limiter les iterations 
	                Point2D potentialCoordinates = new Point2D(x, y);
	                overlapFound = taskCoordinatesnibu.stream().anyMatch(existingCoordinate ->
	                        existingCoordinate.distance(potentialCoordinates) < 10); // ajuster

	                if (overlapFound) {
	                    // Adjuster les coordinates 
	                    x += 5;
	                    y += 5;
	                    adjustmentCount++;
	                }
	            }

	            // stocker quand il y a pas de supperposition
	            if (!overlapFound) {
	                taskCoordinatesnibu.add(new Point2D(x, y));
	            } else {
	                
	                System.out.println("Warning: Unable to resolve overlap for task: " + nomTache);
	            }

	            // Dessiner la tâche
	            if (nomTache != null) {
	            	String nTask = nomTache.length() > 4 ? nomTache.substring(0, 4) : nomTache;

	                // Constrain task point to canvas boundaries
	                double pointMaxX = NIBU.getWidth() - 5;  // Adjust for point width
	                double pointMaxY = NIBU.getHeight() - 15; // Adjust for point height and text offset
	                x = Math.min(x, pointMaxX);
	                y = Math.min(y, pointMaxY);

	                // Constrain text to canvas boundaries
	                double textMaxX = x + 10 + gc.getFont().getSize() * nTask.length();
	                double textMaxY = NIBU.getHeight() - gc.getFont().getSize();
	                if (textMaxX > NIBU.getWidth()) {
	                    // Adjust x if text would go beyond canvas width
	                    x -= textMaxX - NIBU.getWidth();
	                }
	                if (textMaxY > NIBU.getHeight()) {
	                    // Truncate text if it would go beyond canvas height with a visual indicator
	                    nTask = nTask.substring(0, (int) (NIBU.getHeight() / gc.getFont().getSize() - 1)) + "...";
	                }
	                if (task.getDeadline() != null) {
	                    LocalDate deadline = task.getDeadline().toLocalDate();
	                    assert deadline != null : "Deadline should not be null";
	                    LocalDate currentDate = LocalDate.now();
	                    long daysUntilDeadline = ChronoUnit.DAYS.between(currentDate, deadline);
	                    assert daysUntilDeadline >= 0 : "Days until deadline should be positive";

	                    if (daysUntilDeadline == 1 || daysUntilDeadline == 0) {
	                        gc.setFill(Color.RED);
	                    } else if (daysUntilDeadline < 0) {
	                        gc.setFill(Color.GRAY);
	                    } else {
	                        gc.setFill(Color.BLACK);
	                    }
	                } else {
	                    // Tache sans deadline, afficher en noir par défaut
	                    gc.setFill(Color.BLACK);
	                }

	                gc.fillOval(x, y, 5, 5);
	                gc.fillText(nTask, x + 10, y + 5);
	            } else {
	                // Tache sans nom, afficher une indication par défaut
	                gc.fillText("Tache sans nom", x + 10, y + 5);
	            }
	        }
	    }

		
List<Point2D> taskCoordinatesnianu = new ArrayList<>();
	
	public void ShowNIANUTask() {
		 GraphicsContext gc = NIANU.getGraphicsContext2D();
	        assert gc != null : "GraphicsContext ne doit pas être null";
	        ObservableList<TacheData> NIANUTaskList = SelectInBdNIANUTask();
	        assert NIANUTaskList != null : "IUTaskList ne doit pas être null";

	        // Iterate through tasks and handle potential overlaps
	        for (TacheData task : NIANUTaskList) {
	            String nomTache = task.getName();
	            double urgency = task.getRateurg();
	            double importance = task.getRateimp();
	            urgency = 10.0 - urgency;
	            importance = 10.0 - importance;
	            // calcul les cordonnées initiaux selon l'importance et l'urgence
	            double x = NIANU.getWidth() - 10 - (urgency / 10.0) * (NIANU.getWidth() - 20);
	            double y = 10 + (importance / 10.0) * (NIANU.getHeight() - 20);

	            // verifie une supperposition des taches
	            boolean overlapFound = true;
	            int adjustmentCount = 0;
	            while (overlapFound && adjustmentCount < 10) { // Limiter les iterations 
	                Point2D potentialCoordinates = new Point2D(x, y);
	                overlapFound = taskCoordinatesnianu.stream().anyMatch(existingCoordinate ->
	                        existingCoordinate.distance(potentialCoordinates) < 10); // ajuster

	                if (overlapFound) {
	                    // Adjuster les coordinates 
	                    x += 5;
	                    y += 5;
	                    adjustmentCount++;
	                }
	            }

	            // stocker quand il y a pas de supperposition
	            if (!overlapFound) {
	                taskCoordinatesnianu.add(new Point2D(x, y));
	            } else {
	                
	                System.out.println("Warning: Unable to resolve overlap for task: " + nomTache);
	            }

	            // Dessiner la tâche
	            if (nomTache != null) {
	            	String nTask = nomTache.length() > 4 ? nomTache.substring(0, 4) : nomTache;

	                // Constrain task point to canvas boundaries
	                double pointMaxX = NIANU.getWidth() - 5;  // Adjust for point width
	                double pointMaxY = NIANU.getHeight() - 15; // Adjust for point height and text offset
	                x = Math.min(x, pointMaxX);
	                y = Math.min(y, pointMaxY);

	                // Constrain text to canvas boundaries
	                double textMaxX = x + 10 + gc.getFont().getSize() * nTask.length();
	                double textMaxY = NIANU.getHeight() - gc.getFont().getSize();
	                if (textMaxX > NIANU.getWidth()) {
	                    // Adjust x if text would go beyond canvas width
	                    x -= textMaxX - NIANU.getWidth();
	                }
	                if (textMaxY > NIANU.getHeight()) {
	                    // Truncate text if it would go beyond canvas height with a visual indicator
	                    nTask = nTask.substring(0, (int) (NIANU.getHeight() / gc.getFont().getSize() - 1)) + "...";
	                }
	                if (task.getDeadline() != null) {
	                    LocalDate deadline = task.getDeadline().toLocalDate();
	                    assert deadline != null : "Deadline should not be null";
	                    LocalDate currentDate = LocalDate.now();
	                    long daysUntilDeadline = ChronoUnit.DAYS.between(currentDate, deadline);
	                    assert daysUntilDeadline >= 0 : "Days until deadline should be positive";

	                    if (daysUntilDeadline == 1 || daysUntilDeadline == 0) {
	                        gc.setFill(Color.RED);
	                    } else if (daysUntilDeadline < 0) {
	                        gc.setFill(Color.GRAY);
	                    } else {
	                        gc.setFill(Color.BLACK);
	                    }
	                } else {
	                    // Tache sans deadline, afficher en noir par défaut
	                    gc.setFill(Color.BLACK);
	                }

	                gc.fillOval(x, y, 5, 5);
	                gc.fillText(nTask, x + 10, y + 5);
	            } else {
	                // Tache sans nom, afficher une indication par défaut
	                gc.fillText("Tache sans nom", x + 10, y + 5);
	            }
	        }
	    }

		
	
	private ObservableList<TacheData> DeleteTacheList;

	public void ShowDeletedTask() {
	
	DeleteTacheList = SelectInBdDeletedTask();
	taskcoldel.setCellValueFactory(new PropertyValueFactory<>("name"));
	descripcoldel.setCellValueFactory(new PropertyValueFactory<>("description"));
	deadlinecoldel.setCellValueFactory(new PropertyValueFactory<>("deadline"));
	timecoldel.setCellValueFactory(new PropertyValueFactory<>("time"));
	donecoldel.setCellValueFactory(new PropertyValueFactory<>("done"));

	tableauofdeled.setItems(DeleteTacheList);
}
	
	
	 private ObservableList<TacheData> ArchiveTaskList;
	    
     public void ShowArchivedTask() {
     	
     	ArchiveTaskList = SelectInBdArchivedTask();
     	taskarchi.setCellValueFactory(new PropertyValueFactory<>("name"));
     	descriparchi.setCellValueFactory(new PropertyValueFactory<>("description"));
     	deadarchi.setCellValueFactory(new PropertyValueFactory<>("deadline"));
     	timearchi.setCellValueFactory(new PropertyValueFactory<>("time"));
     	donearchi.setCellValueFactory(new PropertyValueFactory<>("done"));

     	tableauarchived.setItems(ArchiveTaskList);
     }
       
	//Voir la tache 
	public void ViewtasksIU(){
	   	  generalview.setVisible(false);

	         Deletetab.setVisible(false);
	         archivedtab.setVisible(false);
	         helptab.setVisible(false);
	     
	         viewtaskofiu.setVisible(true);
	         viewtaskofibnu.setVisible(false);
	         viewtaskofnibu.setVisible(false);
	         viewtaskofnianu.setVisible(false);
	         viewsubtaskofiu.setVisible(false);
	         viewsubtaskofibnu.setVisible(false);
	         viewsubtaskofnibu.setVisible(false);
	         viewsubtaskofnianu.setVisible(false);
	         
	     }
	public void ViewtasksIBNU(){
	   	  generalview.setVisible(false);

	         Deletetab.setVisible(false);
	         archivedtab.setVisible(false);
	         helptab.setVisible(false);
	     
	         viewtaskofiu.setVisible(false);
	         viewtaskofibnu.setVisible(true);
	         viewtaskofnibu.setVisible(false);
	         viewtaskofnianu.setVisible(false);
	         viewsubtaskofiu.setVisible(false);
	         viewsubtaskofibnu.setVisible(false);
	         viewsubtaskofnibu.setVisible(false);
	         viewsubtaskofnianu.setVisible(false);
	        
	     }
	public void ViewtasksNIBU(){
	   	  generalview.setVisible(false);

	         Deletetab.setVisible(false);
	         archivedtab.setVisible(false);
	         helptab.setVisible(false);
	     
	         viewtaskofiu.setVisible(false);
	         viewtaskofibnu.setVisible(false);
	         viewtaskofnibu.setVisible(true);
	         viewtaskofnianu.setVisible(false);
	         viewsubtaskofiu.setVisible(false);
	         viewsubtaskofibnu.setVisible(false);
	         viewsubtaskofnibu.setVisible(false);
	         viewsubtaskofnianu.setVisible(false);
	         
	     }
	public void ViewtasksNIANU(){
	   	  generalview.setVisible(false);

	         Deletetab.setVisible(false);
	         archivedtab.setVisible(false);
	         helptab.setVisible(false);
	     
	         viewtaskofiu.setVisible(false);
	         viewtaskofibnu.setVisible(false);
	         viewtaskofnibu.setVisible(false);
	         viewtaskofnianu.setVisible(true);
	         viewsubtaskofiu.setVisible(false);
	         viewsubtaskofibnu.setVisible(false);
	         viewsubtaskofnibu.setVisible(false);
	         viewsubtaskofnianu.setVisible(false);
	         
	     }
	
	
	//Cliquer sur une tache
	public void SelectIUTask() {
	    IU.setOnMouseClicked(event -> {
	        double mouseX = event.getX();
	        double mouseY = event.getY();

	        ObservableList<TacheData> tasks = SelectInBdIUTask();

	        assert tasks != null : "La liste des tâches ne doit pas être nulle";

	        if (tasks.isEmpty()) {
	            return; // Aucune tâche à traiter
	        }

	        // Vérifier si les coordonnées du clic correspondent à une tâche
	        for (TacheData task : tasks) {
	            int index = tasks.indexOf(task);
	            assert index >= 0 && index < taskCoordinates.size() : "Indice de tâche invalide";

	            double xTache = taskCoordinates.get(index).getX();
	            double yTache = taskCoordinates.get(index).getY();
	            double tolerance = 10.0;

	            // Vérifier si les coordonnées du clic sont près des coordonnées de la tâche
	            if (Math.abs(mouseX - xTache) < tolerance && Math.abs(mouseY - yTache) < tolerance) {
	                // Action à effectuer si le clic est près du nom de la tâche
	            	 ViewtasksIU();
	                //Affiche les infos sur les champs respectifs
	                taskmodifIU.setText(task.getName());
	                taskmodifIU.setDisable(true);
	                descripmodifIU.setText(task.getDescription());
	                deadlinemodifIU.setValue(task.getDeadline().toLocalDate());
	                timemodifIU.setText(task.getTime()); 
	                rateimpmodifIU.setValue(task.getRateimp());	                
	                rateurgmodifIU.setValue(task.getRateurg());
	                donemodifIU.setValue(task.getDone());
	                ShowIUstTask() ;
	   	         SelectIUstTask();

	                break; // Sortir de la boucle après avoir trouvé une tâche à proximité du clic
	            }
	        }
	    });
	}
	
	public void SelectIBNUTask() {
		    IBNU.setOnMouseClicked(event -> {
		        double mouseX = event.getX();
		        double mouseY = event.getY();

		        ObservableList<TacheData> tasks = SelectInBdIBNUTask();

		        assert tasks != null : "La liste des tâches ne doit pas être nulle";

		        if (tasks.isEmpty()) {
		            return; // Aucune tâche à traiter
		        }

		        // Vérifier si les coordonnées du clic correspondent à une tâche
		        for (TacheData task : tasks) {
		            int index = tasks.indexOf(task);
		            assert index >= 0 && index < taskCoordinates.size() : "Indice de tâche invalide";

		            double xTache = taskCoordinatesibnu.get(index).getX();
		            double yTache = taskCoordinatesibnu.get(index).getY();
		            double tolerance = 10.0;

		            // Vérifier si les coordonnées du clic sont près des coordonnées de la tâche
		            if (Math.abs(mouseX - xTache) < tolerance && Math.abs(mouseY - yTache) < tolerance) {
		                // Action à effectuer si le clic est près du nom de la tâche
		            	 ViewtasksIBNU();
		                //Affiche les infos sur les champs respectifs
		                taskmodifIBNU.setText(task.getName());
		                taskmodifIBNU.setDisable(true);
		                descripmodifIBNU.setText(task.getDescription());
		                deadlinemodifIBNU.setValue(task.getDeadline().toLocalDate());
		                timemodifIBNU.setText(task.getTime()); 
		                rateimpmodifIBNU.setValue(task.getRateimp());
		                rateurgmodifIBNU.setValue(task.getRateurg());
		                donemodifIBNU.setValue(task.getDone());
		                ShowIBNUstTask() ;
		   	         SelectIBNUstTask();
		                break; // Sortir de la boucle après avoir trouvé une tâche à proximité du clic
		            }
		        }
		    });
		}

	public void SelectNIBUTask() {
		    NIBU.setOnMouseClicked(event -> {
		        double mouseX = event.getX();
		        double mouseY = event.getY();

		        ObservableList<TacheData> tasks = SelectInBdNIBUTask();

		        assert tasks != null : "La liste des tâches ne doit pas être nulle";

		        if (tasks.isEmpty()) {
		            return; // Aucune tâche à traiter
		        }

		        // Vérifier si les coordonnées du clic correspondent à une tâche
		        for (TacheData task : tasks) {
		            int index = tasks.indexOf(task);
		            assert index >= 0 && index < taskCoordinatesnibu.size() : "Indice de tâche invalide";

		            double xTache = taskCoordinatesnibu.get(index).getX();
		            double yTache = taskCoordinatesnibu.get(index).getY();
		            double tolerance = 10.0;

		            // Vérifier si les coordonnées du clic sont près des coordonnées de la tâche
		            if (Math.abs(mouseX - xTache) < tolerance && Math.abs(mouseY - yTache) < tolerance) {
		                // Action à effectuer si le clic est près du nom de la tâche
		            	 ViewtasksNIBU();
		                //Affiche les infos sur les champs respectifs
		                taskmodifNIBU.setText(task.getName());
		                taskmodifNIBU.setDisable(true);
		                descripmodifNIBU.setText(task.getDescription());
		                deadlinemodifNIBU.setValue(task.getDeadline().toLocalDate());
		                timemodifNIBU.setText(task.getTime()); 
		                rateimpmodifNIBU.setValue(task.getRateimp());
		                rateurgmodifNIBU.setValue(task.getRateurg());
		                donemodifNIBU.setValue(task.getDone());
		                ShowNIBUstTask() ;
		   	         SelectNIBUstTask();
		                break; // Sortir de la boucle après avoir trouvé une tâche à proximité du clic
		            }
		        }
		    });
		}
		
	public void SelectNIANUTask() {
		    NIANU.setOnMouseClicked(event -> {
		        double mouseX = event.getX();
		        double mouseY = event.getY();

		        ObservableList<TacheData> tasks = SelectInBdNIANUTask();

		        assert tasks != null : "La liste des tâches ne doit pas être nulle";

		        if (tasks.isEmpty()) {
		            return; // Aucune tâche à traiter
		        }

		        // Vérifier si les coordonnées du clic correspondent à une tâche
		        for (TacheData task : tasks) {
		            int index = tasks.indexOf(task);
		            assert index >= 0 && index < taskCoordinatesnianu.size() : "Indice de tâche invalide";

		            double xTache = taskCoordinatesnianu.get(index).getX();
		            double yTache = taskCoordinatesnianu.get(index).getY();
		            double tolerance = 10.0;

		            // Vérifier si les coordonnées du clic sont près des coordonnées de la tâche
		            if (Math.abs(mouseX - xTache) < tolerance && Math.abs(mouseY - yTache) < tolerance) {
		                // Action à effectuer si le clic est près du nom de la tâche
		            	 ViewtasksNIANU();
		                //Affiche les infos sur les champs respectifs
		                taskmodifNIANU.setText(task.getName());
		                taskmodifNIANU.setDisable(true);
		                descripmodifNIANU.setText(task.getDescription());
		                deadlinemodifNIANU.setValue(task.getDeadline().toLocalDate());
		                timemodifNIANU.setText(task.getTime()); 
		                rateimpmodifNIANU.setValue(task.getRateimp());
		                rateurgmodifNIANU.setValue(task.getRateurg());
		                donemodifNIANU.setValue(task.getDone());
		                ShowNIANUstTask() ;
		   	         SelectNIANUstTask();
		                break; // Sortir de la boucle après avoir trouvé une tâche à proximité du clic
		            }
		        }
		    });
		}


//effacer les champs
	public void ClearTaskFieldIU() {
		taskmodifIU.setText("");
		descripmodifIU.setText("");
        deadlinemodifIU.setValue(null);
        rateimpmodifIU.setValue(0);
        rateurgmodifIU.setValue(0);
        timemodifIU.setText("");
        donemodifIU.getSelectionModel().clearSelection();
   }
	
	public void ClearTaskFieldIBNU() {
		taskmodifIBNU.setText("");
		descripmodifIBNU.setText("");
        deadlinemodifIBNU.setValue(null);
        rateimpmodifIBNU.setValue(0);
        rateurgmodifIBNU.setValue(0);
        timemodifIBNU.setText("");
        donemodifIBNU.getSelectionModel().clearSelection();
   }
	
	public void ClearTaskFieldNIBU() {
		taskmodifNIBU.setText("");
		descripmodifNIBU.setText("");
        deadlinemodifNIBU.setValue(null);
        rateimpmodifNIBU.setValue(0);
        rateurgmodifNIBU.setValue(0);
        timemodifNIBU.setText("");
        donemodifIU.getSelectionModel().clearSelection();
   }
	
	public void ClearTaskFieldNIANU() {
		taskmodifNIANU.setText("");
		descripmodifNIANU.setText("");
        deadlinemodifNIANU.setValue(null);
        rateimpmodifNIANU.setValue(0);
        rateurgmodifNIANU.setValue(0);
        timemodifNIANU.setText("");
        donemodifNIANU.getSelectionModel().clearSelection();
   }
	
 
  //Mettre à jour les données
	public void UpdateTaskIU() {
	    connect = DataBase.connectDb();

	    try {
	    	
	        if (taskmodifIU.getText().isEmpty() || deadlinemodifIU.getValue() == null
	                || donemodifIU.getSelectionModel().isEmpty()|| timemodifIU.getText().isEmpty()) {
	        	AlertMessage();
	        } else {
	            assert donemodifIU.getSelectionModel().getSelectedItem() != null : "No selected item in done combobox";

	            if (donemodifIU.getSelectionModel().getSelectedItem().equals("Yes")) {
	            	

	                // Sélectionner l'ID de la tâche à partir de son nom
	                String selectTaskIdQuery = "SELECT id_t FROM tache WHERE name = ? AND folder = 'Important and Urgent'";
	                PreparedStatement selectTaskIdStatement = connect.prepareStatement(selectTaskIdQuery);
	                selectTaskIdStatement.setString(1, taskmodifIU.getText());
	                ResultSet taskIdResult = selectTaskIdStatement.executeQuery();
	            	
	                if (taskIdResult.next()) {
	                    int taskId = taskIdResult.getInt("id_t");

	                    // Vérifier l'état des sous-tâches
	                    String checkSubTaskQuery = "SELECT done_st FROM soustache WHERE id_t = ?";
	                    PreparedStatement checkSubTaskStatement = connect.prepareStatement(checkSubTaskQuery);
	                    checkSubTaskStatement.setInt(1, taskId);
	                    ResultSet subTaskResult = checkSubTaskStatement.executeQuery();

	                    boolean allSubTasksDone = true;

	                    while (subTaskResult.next()) {
	                        String subTaskDone = subTaskResult.getString("done_st");
	                        if (!subTaskDone.equals("Yes")) {
	                            allSubTasksDone = false;
	                            break;
	                        }
	                    }

	                    if (!allSubTasksDone) {
	                        Alert alert = new Alert(AlertType.ERROR);
	                        alert.setTitle("Error Message");
	                        alert.setHeaderText(null);
	                        alert.setContentText("Complete undone subtasks before complete the task.");
	                        alert.showAndWait();
	                    } else {
	                        // Toutes les sous-tâches sont terminées, confirmation de l'archivage de la tâche
	                        Alert alert = new Alert(AlertType.CONFIRMATION);
	                        alert.setTitle("Confirmation Message");
	                        alert.setHeaderText(null);
	                        alert.setContentText("As you have done " + taskmodifIU.getText() + ", do you want to archive it?");
	                        Optional<ButtonType> option = alert.showAndWait();

	                        if (option.isPresent() && option.get() == ButtonType.OK) {
	                            ArchivedTaskIU(); // Archiver la tâche
	                        }
	                }
	            }} else {
	                int rateimpmodifIUValue = (int) rateimpmodifIU.getValue();
	                int rateurgmodifIUValue = (int) rateurgmodifIU.getValue();
	                //assert rateimpmodifIUValue >= 0 && rateurgmodifIUValue >= 0 : "Invalid rate values";

	                // Requête de mise à jour
	                String updateSql = "UPDATE tache SET description = ?, deadline = ?, time = ?, rateimp = ?, rateurg = ?, done = ? WHERE name = ? AND folder= 'Important and Urgent'";
	                prepare = connect.prepareStatement(updateSql);
	                prepare.setString(1, descripmodifIU.getText());
	                prepare.setDate(2, java.sql.Date.valueOf(deadlinemodifIU.getValue())); // Convertir LocalDate en java.sql.Date
	                prepare.setString(3, timemodifIU.getText());
	                prepare.setInt(4, rateimpmodifIUValue);
	                prepare.setInt(5, rateurgmodifIUValue);
	                prepare.setString(6, donemodifIU.getSelectionModel().getSelectedItem());
	                prepare.setString(7, taskmodifIU.getText());

	                // Exécution de la requête de mise à jour
	                prepare.executeUpdate();
	                AlertMessagePositive("Updated");
	                PrintGeneralView();
	                ClearTaskFieldIU();
	            }
	        

	    }} catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void UpdateTaskIBNU() {
	    connect = DataBase.connectDb();

	    try {
	    	

	        if (taskmodifIBNU.getText().isEmpty() || deadlinemodifIBNU.getValue() == null
	                || donemodifIBNU.getSelectionModel().isEmpty()|| timemodifIBNU.getText().isEmpty()) {
	        	AlertMessage();
	        } else {
	            assert donemodifIBNU.getSelectionModel().getSelectedItem() != null : "No selected item in done combobox";

	            if (donemodifIBNU.getSelectionModel().getSelectedItem().equals("Yes")) {
	            	String selectTaskIdQuery = "SELECT id_t FROM tache WHERE name = ? AND folder = 'Important but not Urgent'";
	                PreparedStatement selectTaskIdStatement = connect.prepareStatement(selectTaskIdQuery);
	                selectTaskIdStatement.setString(1, taskmodifIBNU.getText());
	                ResultSet taskIdResult = selectTaskIdStatement.executeQuery();
	            	
	                if (taskIdResult.next()) {
	                    int taskId = taskIdResult.getInt("id_t");

	                    // Vérifier l'état des sous-tâches
	                    String checkSubTaskQuery = "SELECT done_st FROM soustache WHERE id_t = ?";
	                    PreparedStatement checkSubTaskStatement = connect.prepareStatement(checkSubTaskQuery);
	                    checkSubTaskStatement.setInt(1, taskId);
	                    ResultSet subTaskResult = checkSubTaskStatement.executeQuery();

	                    boolean allSubTasksDone = true;

	                    while (subTaskResult.next()) {
	                        String subTaskDone = subTaskResult.getString("done_st");
	                        if (!subTaskDone.equals("Yes")) {
	                            allSubTasksDone = false;
	                            break;
	                        }
	                    }

	                    if (!allSubTasksDone) {
	                        Alert alert = new Alert(AlertType.ERROR);
	                        alert.setTitle("Error Message");
	                        alert.setHeaderText(null);
	                        alert.setContentText("Complete undone subtasks before complete the task.");
	                        alert.showAndWait();
	                    } else {
	                        // Toutes les sous-tâches sont terminées, confirmation de l'archivage de la tâche
	                        Alert alert = new Alert(AlertType.CONFIRMATION);
	                        alert.setTitle("Confirmation Message");
	                        alert.setHeaderText(null);
	                        alert.setContentText("As you have done " + taskmodifIBNU.getText() + ", do you want to archive it?");
	                        Optional<ButtonType> option = alert.showAndWait();

	                        if (option.isPresent() && option.get() == ButtonType.OK) {
	                            ArchivedTaskIU(); // Archiver la tâche
	                        }
	                }
	            }} else {
	                int rateimpmodifIBNUValue = (int) rateimpmodifIBNU.getValue();
	                int rateurgmodifIBNUValue = (int) rateurgmodifIBNU.getValue();
	                assert rateimpmodifIBNUValue >= 0 && rateurgmodifIBNUValue >= 0 : "Invalid rate values";

	                // Requête de mise à jour
	                String updateSql = "UPDATE tache SET description = ?, deadline = ?, time = ?, rateimp = ?, rateurg = ?, done = ? WHERE name = ? AND folder= 'Important but not Urgent'";
	                prepare = connect.prepareStatement(updateSql);
	                prepare.setString(1, descripmodifIBNU.getText());
	                prepare.setDate(2, java.sql.Date.valueOf(deadlinemodifIBNU.getValue())); // Convertir LocalDate en java.sql.Date
	                prepare.setString(3, timemodifIBNU.getText());
	                prepare.setInt(4, rateimpmodifIBNUValue);
	                prepare.setInt(5, rateurgmodifIBNUValue);
	                prepare.setString(6, donemodifIBNU.getSelectionModel().getSelectedItem());
	                prepare.setString(7, taskmodifIBNU.getText());

	                // Exécution de la requête de mise à jour
	                prepare.executeUpdate();
	                AlertMessagePositive("Updateed");
	                PrintGeneralView();
	                
	                ShowIBNUTask();
	                ClearTaskFieldIBNU();
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void UpdateTaskNIBU() {
	    connect = DataBase.connectDb();

	    try {
	    	

	        if (taskmodifNIBU.getText().isEmpty() || deadlinemodifNIBU.getValue() == null
	                || donemodifNIBU.getSelectionModel().isEmpty()|| timemodifNIBU.getText().isEmpty()) {
	        	AlertMessage();
	        } else {
	            assert donemodifNIBU.getSelectionModel().getSelectedItem() != null : "No selected item in done combobox";

	            if (donemodifNIBU.getSelectionModel().getSelectedItem().equals("Yes")) {
	            	String selectTaskIdQuery = "SELECT id_t FROM tache WHERE name = ? AND folder = 'Not Important but Urgent'";
	                PreparedStatement selectTaskIdStatement = connect.prepareStatement(selectTaskIdQuery);
	                selectTaskIdStatement.setString(1, taskmodifNIBU.getText());
	                ResultSet taskIdResult = selectTaskIdStatement.executeQuery();
	            	
	                if (taskIdResult.next()) {
	                    int taskId = taskIdResult.getInt("id_t");

	                    // Vérifier l'état des sous-tâches
	                    String checkSubTaskQuery = "SELECT done_st FROM soustache WHERE id_t = ?";
	                    PreparedStatement checkSubTaskStatement = connect.prepareStatement(checkSubTaskQuery);
	                    checkSubTaskStatement.setInt(1, taskId);
	                    ResultSet subTaskResult = checkSubTaskStatement.executeQuery();

	                    boolean allSubTasksDone = true;

	                    while (subTaskResult.next()) {
	                        String subTaskDone = subTaskResult.getString("done_st");
	                        if (!subTaskDone.equals("Yes")) {
	                            allSubTasksDone = false;
	                            break;
	                        }
	                    }

	                    if (!allSubTasksDone) {
	                        Alert alert = new Alert(AlertType.ERROR);
	                        alert.setTitle("Error Message");
	                        alert.setHeaderText(null);
	                        alert.setContentText("Complete undone subtasks before complete the task.");
	                        alert.showAndWait();
	                    } else {
	                        // Toutes les sous-tâches sont terminées, confirmation de l'archivage de la tâche
	                        Alert alert = new Alert(AlertType.CONFIRMATION);
	                        alert.setTitle("Confirmation Message");
	                        alert.setHeaderText(null);
	                        alert.setContentText("As you have done " + taskmodifNIBU.getText() + ", do you want to archive it?");
	                        Optional<ButtonType> option = alert.showAndWait();

	                        if (option.isPresent() && option.get() == ButtonType.OK) {
	                            ArchivedTaskIU(); // Archiver la tâche
	                        }
	                }
	            }} else {
	                int rateimpmodifNIBUValue = (int) rateimpmodifNIBU.getValue();
	                int rateurgmodifNIBUValue = (int) rateurgmodifNIBU.getValue();
	                assert rateimpmodifNIBUValue >= 0 && rateurgmodifNIBUValue >= 0 : "Invalid rate values";

	                // Requête de mise à jour
	                String updateSql = "UPDATE tache SET description = ?, deadline = ?, time = ?, rateimp = ?, rateurg = ?, done = ? WHERE name = ? AND folder='Not Important but Urgent'";
	                prepare = connect.prepareStatement(updateSql);
	                prepare.setString(1, descripmodifNIBU.getText());
	                prepare.setDate(2, java.sql.Date.valueOf(deadlinemodifNIBU.getValue())); // Convertir LocalDate en java.sql.Date
	                prepare.setString(3, timemodifNIBU.getText());
	                prepare.setInt(4, rateimpmodifNIBUValue);
	                prepare.setInt(5, rateurgmodifNIBUValue);
	                prepare.setString(6, donemodifNIBU.getSelectionModel().getSelectedItem());
	                prepare.setString(7, taskmodifNIBU.getText());

	                // Exécution de la requête de mise à jour
	                prepare.executeUpdate();
	                AlertMessagePositive("Updated");
	                PrintGeneralView();
	                ShowNIBUTask();
	                ClearTaskFieldNIBU();
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void UpdateTaskNIANU() {
	    connect = DataBase.connectDb();

	    try {
	    	
	        if (taskmodifNIANU.getText().isEmpty() || deadlinemodifNIANU.getValue() == null
	                || donemodifNIANU.getSelectionModel().isEmpty()|| timemodifNIANU.getText().isEmpty()) {
	        	AlertMessage();
	        } else {
	            assert donemodifNIANU.getSelectionModel().getSelectedItem() != null : "No selected item in done combobox";

	            if (donemodifNIANU.getSelectionModel().getSelectedItem().equals("Yes")) {
	            	String selectTaskIdQuery = "SELECT id_t FROM tache WHERE name = ? AND folder = 'Not Important and not Urgent'";
	                PreparedStatement selectTaskIdStatement = connect.prepareStatement(selectTaskIdQuery);
	                selectTaskIdStatement.setString(1, taskmodifNIANU.getText());
	                ResultSet taskIdResult = selectTaskIdStatement.executeQuery();
	            	
	                if (taskIdResult.next()) {
	                    int taskId = taskIdResult.getInt("id_t");

	                    // Vérifier l'état des sous-tâches
	                    String checkSubTaskQuery = "SELECT done_st FROM soustache WHERE id_t = ?";
	                    PreparedStatement checkSubTaskStatement = connect.prepareStatement(checkSubTaskQuery);
	                    checkSubTaskStatement.setInt(1, taskId);
	                    ResultSet subTaskResult = checkSubTaskStatement.executeQuery();

	                    boolean allSubTasksDone = true;

	                    while (subTaskResult.next()) {
	                        String subTaskDone = subTaskResult.getString("done_st");
	                        if (!subTaskDone.equals("Yes")) {
	                            allSubTasksDone = false;
	                            break;
	                        }
	                    }

	                    if (!allSubTasksDone) {
	                        Alert alert = new Alert(AlertType.ERROR);
	                        alert.setTitle("Error Message");
	                        alert.setHeaderText(null);
	                        alert.setContentText("Complete undone subtasks before complete the task.");
	                        alert.showAndWait();
	                    } else {
	                        // Toutes les sous-tâches sont terminées, confirmation de l'archivage de la tâche
	                        Alert alert = new Alert(AlertType.CONFIRMATION);
	                        alert.setTitle("Confirmation Message");
	                        alert.setHeaderText(null);
	                        alert.setContentText("As you have done " + taskmodifNIANU.getText() + ", do you want to archive it?");
	                        Optional<ButtonType> option = alert.showAndWait();

	                        if (option.isPresent() && option.get() == ButtonType.OK) {
	                            ArchivedTaskIU(); // Archiver la tâche
	                        }
	                }
	            }} else {
	                int rateimpmodifNIANUValue = (int) rateimpmodifNIANU.getValue();
	                int rateurgmodifNIANUValue = (int) rateurgmodifIU.getValue();
	                assert rateimpmodifNIANUValue >= 0 && rateurgmodifNIANUValue >= 0 : "Invalid rate values";

	                // Requête de mise à jour
	                String updateSql = "UPDATE tache SET description = ?, deadline = ?, time = ?, rateimp = ?, rateurg = ?, done = ? WHERE name = ? AND folder='Not Important and not Urgent'";
	                prepare = connect.prepareStatement(updateSql);
	                prepare.setString(1, descripmodifNIANU.getText());
	                prepare.setDate(2, java.sql.Date.valueOf(deadlinemodifNIANU.getValue())); // Convertir LocalDate en java.sql.Date
	                prepare.setString(3, timemodifNIANU.getText());
	                prepare.setInt(4, rateimpmodifNIANUValue);
	                prepare.setInt(5, rateurgmodifNIANUValue);
	                prepare.setString(6, donemodifNIANU.getSelectionModel().getSelectedItem());
	                prepare.setString(7, taskmodifNIANU.getText());

	                // Exécution de la requête de mise à jour
	                prepare.executeUpdate();
	                AlertMessagePositive("Updated");
	                PrintGeneralView();
	                ShowNIANUTask();
	                ClearTaskFieldNIANU();
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
 
	//supprimer une tache
    
    public void DeleteTaskIU() {
        String sql = "UPDATE tache SET folder= 'Deleted' WHERE name = ? AND deadline = ?";
        connect = DataBase.connectDb();
        try {
        	assert connect != null : "Database connection null";
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete task: " + taskmodifIU.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
            	
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, taskmodifIU.getText());
                prepare.setDate(2, java.sql.Date.valueOf(deadlinemodifIU.getValue())); 
                
                prepare.executeUpdate();

                AlertMessagePositive("Deleted");
                
                

                
             // nettoie le canvas
                GraphicsContext gc = IU.getGraphicsContext2D();
        	    gc.clearRect(0, 0, IU.getWidth(), IU.getHeight());           
                // Masquer les onglets et autres éléments UI non pertinents
                PrintGeneralView(); 
                ShowIUTask();
                // Actualiser la liste des tâches
                ShowDeletedTask();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    
    public void DeleteTaskIBNU() {
    	String sql = "UPDATE tache SET folder= 'Deleted' WHERE name = ? AND deadline = ?";        connect = DataBase.connectDb();
        try {
        	assert connect != null : "Database connection null";
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete task: " + taskmodifIBNU.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
            	
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, taskmodifIBNU.getText());
                
                prepare.setString(2, deadlinemodifIBNU.getValue().toString());
               
                prepare.executeUpdate();

                AlertMessagePositive("Deleted");
                
                
                
             // nettoie le canvas
                GraphicsContext gc = IBNU.getGraphicsContext2D();
        	    gc.clearRect(0, 0, IBNU.getWidth(), IBNU.getHeight());           
                // Masquer les onglets et autres éléments UI non pertinents
                PrintGeneralView(); 
                ShowIBNUTask();
                // Actualiser la liste des tâches
                ShowDeletedTask();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    
    public void DeleteTaskNIBU() {
    	String sql = "UPDATE tache SET folder= 'Deleted' WHERE name = ? AND deadline = ?";
        connect = DataBase.connectDb();
        try {
        	assert connect != null : "Database connection null";
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete task: " + taskmodifNIBU.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
            	
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, taskmodifNIBU.getText());
                prepare.setString(2, deadlinemodifNIBU.getValue().toString());
                
                prepare.executeUpdate();

                AlertMessagePositive("Deleted");
                
             // nettoie le canvas
                GraphicsContext gc = NIBU.getGraphicsContext2D();
        	    gc.clearRect(0, 0, NIBU.getWidth(), NIBU.getHeight());           
                // Masquer les onglets et autres éléments UI non pertinents
                PrintGeneralView();             
                // Actualiser la liste des tâches
                ShowDeletedTask();
                ShowNIBUTask();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    
    public void DeleteTaskNIANU() {
    	String sql = "UPDATE tache SET folder= 'Deleted' WHERE name = ? AND deadline = ?";        connect = DataBase.connectDb();
        try {
        	assert connect != null : "Database connection null";
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete task: " + taskmodifNIANU.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
            	
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, taskmodifNIANU.getText());
                prepare.setString(2, deadlinemodifNIANU.getValue().toString());
                prepare.executeUpdate();

                AlertMessagePositive("Deleted");
         
             // nettoie le canvas
                GraphicsContext gc = NIANU.getGraphicsContext2D();
        	    gc.clearRect(0, 0, NIANU.getWidth(), NIANU.getHeight());           
                // Masquer les onglets et autres éléments UI non pertinents
                PrintGeneralView();             
                // Actualiser la liste des tâches
                ShowDeletedTask();
                ShowNIANUTask();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    
    
    //Archiver une tache
    

      public void ArchivedTaskIU() {
    	  String sql = "UPDATE tache SET folder= 'Archived' WHERE name = ? AND deadline = ?";
          connect = DataBase.connectDb();
          try {
        	  assert connect != null : "Database connection is null";
          	Alert alert;
              alert = new Alert(AlertType.CONFIRMATION);
              alert.setTitle("Confirmation Message");
              alert.setHeaderText(null);
              alert.setContentText("Are you sure you want to archive task " + taskmodifIU.getText() + "?");
              Optional<ButtonType> option = alert.showAndWait();
                          
              if (option.isPresent() && option.get() == ButtonType.OK) {
                  
                  prepare = connect.prepareStatement(sql);
                  prepare.setString(1, taskmodifIU.getText());
                  prepare.setDate(2, java.sql.Date.valueOf(deadlinemodifIU.getValue())); 
                  prepare.executeUpdate();

                  AlertMessagePositive("Archived");

                  // nettoie le canvas
                  GraphicsContext gc = IU.getGraphicsContext2D();
          	      gc.clearRect(0, 0, IU.getWidth(), IU.getHeight());
          	    
                  // Masquer les onglets et autres éléments UI non pertinents
                  PrintGeneralView();
                  ShowIUTask();       
                  // Actualiser la liste des tâches
                  ShowArchivedTask();
              }
          } catch (Exception e) {
              e.printStackTrace();
          
          }
      }
      
      public void ArchivedTaskIBNU() {
    	  String sql = "UPDATE tache SET folder= 'Archived' WHERE name = ? AND deadline = ?";
            connect = DataBase.connectDb();
            try {
          	  assert connect != null : "Database connection is null";
            	Alert alert;
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to archive task " + taskmodifIBNU.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                            
                if (option.isPresent() && option.get() == ButtonType.OK) {
                	prepare = connect.prepareStatement(sql);
                    prepare.setString(1, taskmodifIBNU.getText());
                    
                    prepare.setString(2, deadlinemodifIBNU.getValue().toString());
                   
                    prepare.executeUpdate();

                    AlertMessagePositive("Archived");

                   // nettoie le canvas
                    GraphicsContext gc = IBNU.getGraphicsContext2D();
            	      gc.clearRect(0, 0, IBNU.getWidth(), IBNU.getHeight());
            	    
                    // Masquer les onglets et autres éléments UI non pertinents
                    PrintGeneralView();
                    ShowIBNUTask();       
                    // Actualiser la liste des tâches
                    ShowArchivedTask();
                }
            } catch (Exception e) {
                e.printStackTrace();
            
            }
        }
        
      public void ArchivedTaskNIBU() {
    	  String sql = "UPDATE tache SET folder= 'Archived' WHERE name = ? AND deadline = ?";
            connect = DataBase.connectDb();
            try {
          	  assert connect != null : "Database connection is null";
            	Alert alert;
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to archive task " + taskmodifNIBU.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                            
                if (option.isPresent() && option.get() == ButtonType.OK) {
                	prepare = connect.prepareStatement(sql);
                    prepare.setString(1, taskmodifNIBU.getText());
                    prepare.setString(2, deadlinemodifNIBU.getValue().toString());
                    
                    prepare.executeUpdate();

                    AlertMessagePositive("Archived");
                    // nettoie le canvas
                    GraphicsContext gc = NIBU.getGraphicsContext2D();
            	      gc.clearRect(0, 0, NIBU.getWidth(), NIBU.getHeight());
            	    
                    // Masquer les onglets et autres éléments UI non pertinents
                    PrintGeneralView();
                    ShowNIBUTask();       
                    // Actualiser la liste des tâches
                    ShowArchivedTask();
                }
            } catch (Exception e) {
                e.printStackTrace();
            
            }
        }
        
      public void ArchivedTaskNIANU() {
    	  String sql = "UPDATE tache SET folder= 'Archived' WHERE name = ? AND deadline = ?";
            connect = DataBase.connectDb();
            try {
          	  assert connect != null : "Database connection is null";
            	Alert alert;
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to archive task " + taskmodifNIANU.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                            
                if (option.isPresent() && option.get() == ButtonType.OK) {
                	 prepare = connect.prepareStatement(sql);
                     prepare.setString(1, taskmodifNIANU.getText());
                     prepare.setString(2, deadlinemodifNIANU.getValue().toString());
                     prepare.executeUpdate();

                    AlertMessagePositive("Archived");

                    // nettoie le canvas
                    GraphicsContext gc = NIANU.getGraphicsContext2D();
            	      gc.clearRect(0, 0, NIANU.getWidth(), NIANU.getHeight());
            	    
                    // Masquer les onglets et autres éléments UI non pertinents
                    PrintGeneralView();
                    ShowNIANUTask();       
                    // Actualiser la liste des tâches
                    ShowArchivedTask();
                }
            } catch (Exception e) {
                e.printStackTrace();
            
            }
        }
        
      
      //Cette partie concerne les soustaches
      
      //Ajouter une soustache
      
      public void addSubTaskWindowIU() {
    	    try {
    	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/AddSubTask.fxml"));
    	        Parent root = loader.load();

    	        assert root != null : "Root node of AddSubTask.fxml is null";

    	        AddSubTaskController addSubTaskController = loader.getController();
    	        addSubTaskController.init(this);
    	        addSubTaskController.configureElementsVisibility(true, false, false, false);
    	        Stage deleteStage = new Stage();
    	        Scene scene = new Scene(root);
    	        assert scene != null : "Scene is null";
    	        deleteStage.setScene(scene);

    	        deleteStage.initModality(Modality.WINDOW_MODAL);
    	        assert addsubiubtn != null : "addsubiubtn is null";
    	        assert addsubiubtn.getScene() != null : "Scene of addsubiubtn is null";
    	        deleteStage.initOwner(addsubiubtn.getScene().getWindow()); // Set main window as owner

    	        // Show the task interface
    	        deleteStage.showAndWait();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	}
      
      public void addSubTaskWindowIBNU() {
  	    try {
  	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/AddSubTask.fxml"));
  	        Parent root = loader.load();

  	        assert root != null : "Root node of AddSubTask.fxml is null";

  	        AddSubTaskController addSubTaskController = loader.getController();
  	        addSubTaskController.init(this);
  	        addSubTaskController.configureElementsVisibility(false, true, false, false);
  	        Stage deleteStage = new Stage();
  	        Scene scene = new Scene(root);
  	        assert scene != null : "Scene is null";
  	        deleteStage.setScene(scene);

  	        deleteStage.initModality(Modality.WINDOW_MODAL);
  	        assert addsubibnubtn != null : "addsubiubtn is null";
  	        assert addsubibnubtn.getScene() != null : "Scene of addsubiubtn is null";
  	        deleteStage.initOwner(addsubibnubtn.getScene().getWindow()); // Set main window as owner

  	        // Show the task interface
  	        deleteStage.showAndWait();
  	    } catch (Exception e) {
  	        e.printStackTrace();
  	    }
  	}
      
      public void addSubTaskWindowNIBU() {
  	    try {
  	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/AddSubTask.fxml"));
  	        Parent root = loader.load();

  	        assert root != null : "Root node of AddSubTask.fxml is null";

  	        AddSubTaskController addSubTaskController = loader.getController();
  	        addSubTaskController.init(this);
  	        addSubTaskController.configureElementsVisibility(false, false, true, false);
  	        Stage deleteStage = new Stage();
  	        Scene scene = new Scene(root);
  	        assert scene != null : "Scene is null";
  	        deleteStage.setScene(scene);

  	        deleteStage.initModality(Modality.WINDOW_MODAL);
  	        assert addsubnibubtn != null : "addsubiubtn is null";
  	        assert addsubnibubtn.getScene() != null : "Scene of addsubiubtn is null";
  	        deleteStage.initOwner(addsubnibubtn.getScene().getWindow()); // Set main window as owner

  	        // Show the task interface
  	        deleteStage.showAndWait();
  	    } catch (Exception e) {
  	        e.printStackTrace();
  	    }
  	}
      
      public void addSubTaskWindowNIANU() {
  	    try {
  	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/AddSubTask.fxml"));
  	        Parent root = loader.load();

  	        assert root != null : "Root node of AddSubTask.fxml is null";

  	        AddSubTaskController addSubTaskController = loader.getController();
  	        addSubTaskController.init(this);
  	        addSubTaskController.configureElementsVisibility(false, false, false, true);
  	        Stage deleteStage = new Stage();
  	        Scene scene = new Scene(root);
  	        assert scene != null : "Scene is null";
  	        deleteStage.setScene(scene);

  	        deleteStage.initModality(Modality.WINDOW_MODAL);
  	        assert addsubnianubtn != null : "addsubiubtn is null";
  	        assert addsubnianubtn.getScene() != null : "Scene of addsubiubtn is null";
  	        deleteStage.initOwner(addsubnianubtn.getScene().getWindow()); // Set main window as owner

  	        // Show the task interface
  	        deleteStage.showAndWait();
  	    } catch (Exception e) {
  	        e.printStackTrace();
  	    }
  	}
      
      //Recupérer l'id d'une tache pour rajouter une sous tache
      
      public int TakeIDTaskIU() {
    	    String selectSql = "SELECT id_t FROM tache WHERE name = ? AND folder = 'Important and Urgent'";
    	    int taskID = 0;

    	    try (Connection connection = DataBase.connectDb()) {
    	        assert connection != null : "La connexion à la base de données a échoué.";

    	        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
    	            assert preparedStatement != null : "La préparation de la requête a échoué.";

    	            preparedStatement.setString(1, taskmodifIU.getText());

    	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
    	                if (resultSet.next()) {
    	                    taskID = resultSet.getInt("id_t");
    	                   
    	                } else {
    	                    System.out.println("Aucune tâche trouvée avec ce nom et ce dossier.");
    	                }
    	            }
    	        }
    	    } catch (SQLException e) {
    	        // Gérer l'erreur SQL
    	        e.printStackTrace(); // Ou afficher un message d'erreur
    	    }

    	    return taskID;
    	    
    	}

      public int TakeIDTaskIBNU() {
  	    String selectSql = "SELECT id_t FROM tache WHERE name = ? AND folder = 'Important but not Urgent'";
  	    int taskID = 0;

  	    try (Connection connection = DataBase.connectDb()) {
  	        assert connection != null : "La connexion à la base de données a échoué.";

  	        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
  	            assert preparedStatement != null : "La préparation de la requête a échoué.";

  	            preparedStatement.setString(1, taskmodifIBNU.getText());

  	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
  	                if (resultSet.next()) {
  	                    taskID = resultSet.getInt("id_t");
  	                } else {
  	                    System.out.println("Aucune tâche trouvée avec ce nom et ce dossier.");
  	                }
  	            }
  	        }
  	    } catch (SQLException e) {
  	        // Gérer l'erreur SQL
  	        e.printStackTrace(); // Ou afficher un message d'erreur
  	    }

  	    return taskID;
  	}
      
      public int TakeIDTaskNIBU() {
  	    String selectSql = "SELECT id_t FROM tache WHERE name = ? AND folder = 'Not Important but Urgent'";
  	    int taskID = 0;

  	    try (Connection connection = DataBase.connectDb()) {
  	        assert connection != null : "La connexion à la base de données a échoué.";

  	        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
  	            assert preparedStatement != null : "La préparation de la requête a échoué.";

  	            preparedStatement.setString(1, taskmodifNIBU.getText());

  	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
  	                if (resultSet.next()) {
  	                    taskID = resultSet.getInt("id_t");
  	                } else {
  	                    System.out.println("Aucune tâche trouvée avec ce nom et ce dossier.");
  	                }
  	            }
  	        }
  	    } catch (SQLException e) {
  	        // Gérer l'erreur SQL
  	        e.printStackTrace(); // Ou afficher un message d'erreur
  	    }

  	    return taskID;
  	}
      
      public int TakeIDTaskNIANU() {
  	    String selectSql = "SELECT id_t FROM tache WHERE name = ? AND folder = 'Not Important and not Urgent'";
  	    int taskID = 0;

  	    try (Connection connection = DataBase.connectDb()) {
  	        assert connection != null : "La connexion à la base de données a échoué.";

  	        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
  	            assert preparedStatement != null : "La préparation de la requête a échoué.";

  	            preparedStatement.setString(1, taskmodifNIANU.getText());

  	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
  	                if (resultSet.next()) {
  	                    taskID = resultSet.getInt("id_t");
  	                } else {
  	                    System.out.println("Aucune tâche trouvée avec ce nom et ce dossier.");
  	                }
  	            }
  	        }
  	    } catch (SQLException e) {
  	        // Gérer l'erreur SQL
  	        e.printStackTrace(); // Ou afficher un message d'erreur
  	    }

  	    return taskID;
  	}

   // Cette méthode retourne la date sélectionnée dans le DatePicker
      public LocalDate TakeDeadlineIU() {
          return deadlinemodifIU.getValue();
      }
      public LocalDate TakeDeadlineIBNU() {
          return deadlinemodifIBNU.getValue();
      }
      public LocalDate TakeDeadlineNIBU() {
          return deadlinemodifNIBU.getValue();
      }
      public LocalDate TakeDeadlineNIANU() {
          return deadlinemodifNIANU.getValue();
      }
      
      
      //Recuperer les sous taches d'une tache
      public ObservableList<SousTacheData> SelectInBdIUSubTask(int taskId){
    	    ObservableList<SousTacheData> listDatastIU = FXCollections.observableArrayList();
    	    
    	    // on selectionne dans la bd la liste des taches IU
    	    
    	    String sql = "SELECT * FROM soustache WHERE id_t = ?"; // Utilisation d'un paramètre
    	    
    	    connect = DataBase.connectDb();
    	    
    	    try {
    	        assert connect != null : "La connexion à la base de données est nulle";
    	        
    	        prepare = connect.prepareStatement(sql);
    	        prepare.setInt(1, taskId); // Paramétrage de la valeur de l'identifiant de la tâche
    	        result = prepare.executeQuery();
    	        
    	        SousTacheData SousTacheD;
    	        
    	        while (result.next()) {
    	            SousTacheD = new SousTacheData(
    	                result.getString("name_st"),
    	                result.getString("description_st"),
    	                result.getDate("deadline_st"),
    	                result.getString("time_st"),
    	                result.getString("done_st"),
    	                result.getInt("rateimp_st"),
    	                result.getInt("rateurg_st"),
    	                result.getInt("id_t"));
    	            assert SousTacheD != null : "La tâche sous-tâche créée est nulle";
    	            listDatastIU.add(SousTacheD);
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    
    	    return listDatastIU;
    	}

      public ObservableList<SousTacheData> SelectInBdIBNUSubTask(int taskId){
  	    ObservableList<SousTacheData> listDatastIBNU = FXCollections.observableArrayList();
  	    
  	    // on selectionne dans la bd la liste des taches IU
  	    
  	    String sql = "SELECT * FROM soustache WHERE id_t = ?"; // Utilisation d'un paramètre
  	    
  	    connect = DataBase.connectDb();
  	    
  	    try {
  	        assert connect != null : "La connexion à la base de données est nulle";
  	        
  	        prepare = connect.prepareStatement(sql);
  	        prepare.setInt(1, taskId); // Paramétrage de la valeur de l'identifiant de la tâche
  	        result = prepare.executeQuery();
  	        
  	        SousTacheData SousTacheD;
  	        
  	        while (result.next()) {
  	            SousTacheD = new SousTacheData(
  	                result.getString("name_st"),
  	                result.getString("description_st"),
  	                result.getDate("deadline_st"),
  	                result.getString("time_st"),
  	                result.getString("done_st"),
  	                result.getInt("rateimp_st"),
  	                result.getInt("rateurg_st"),
  	                result.getInt("id_t"));
  	            assert SousTacheD != null : "La tâche sous-tâche créée est nulle";
  	            listDatastIBNU.add(SousTacheD);
  	        }
  	    } catch (Exception e) {
  	        e.printStackTrace();
  	    }
  	    
  	    return listDatastIBNU;
  	}

      public ObservableList<SousTacheData> SelectInBdNIBUSubTask(int taskId){
  	    ObservableList<SousTacheData> listDatastNIBU = FXCollections.observableArrayList();
  	    
  	    // on selectionne dans la bd la liste des taches IU
  	    
  	    String sql = "SELECT * FROM soustache WHERE id_t = ?"; // Utilisation d'un paramètre
  	    
  	    connect = DataBase.connectDb();
  	    
  	    try {
  	        assert connect != null : "La connexion à la base de données est nulle";
  	        
  	        prepare = connect.prepareStatement(sql);
  	        prepare.setInt(1, taskId); // Paramétrage de la valeur de l'identifiant de la tâche
  	        result = prepare.executeQuery();
  	        
  	        SousTacheData SousTacheD;
  	        
  	        while (result.next()) {
  	            SousTacheD = new SousTacheData(
  	                result.getString("name_st"),
  	                result.getString("description_st"),
  	                result.getDate("deadline_st"),
  	                result.getString("time_st"),
  	                result.getString("done_st"),
  	                result.getInt("rateimp_st"),
  	                result.getInt("rateurg_st"),
  	                result.getInt("id_t"));
  	            assert SousTacheD != null : "La tâche sous-tâche créée est nulle";
  	            listDatastNIBU.add(SousTacheD);
  	        }
  	    } catch (Exception e) {
  	        e.printStackTrace();
  	    }
  	    
  	    return listDatastNIBU;
  	}

      public ObservableList<SousTacheData> SelectInBdNIANUSubTask(int taskId){
  	    ObservableList<SousTacheData> listDatastNIANU = FXCollections.observableArrayList();
  	    
  	    // on selectionne dans la bd la liste des taches IU
  	    
  	    String sql = "SELECT * FROM soustache WHERE id_t = ?"; // Utilisation d'un paramètre
  	    
  	    connect = DataBase.connectDb();
  	    
  	    try {
  	        assert connect != null : "La connexion à la base de données est nulle";
  	        
  	        prepare = connect.prepareStatement(sql);
  	        prepare.setInt(1, taskId); // Paramétrage de la valeur de l'identifiant de la tâche
  	        result = prepare.executeQuery();
  	        
  	        SousTacheData SousTacheD;
  	        
  	        while (result.next()) {
  	            SousTacheD = new SousTacheData(
  	                result.getString("name_st"),
  	                result.getString("description_st"),
  	                result.getDate("deadline_st"),
  	                result.getString("time_st"),
  	                result.getString("done_st"),
  	                result.getInt("rateimp_st"),
  	                result.getInt("rateurg_st"),
  	                result.getInt("id_t"));
  	            assert SousTacheD != null : "La tâche sous-tâche créée est nulle";
  	          listDatastNIANU.add(SousTacheD);
  	        }
  	    } catch (Exception e) {
  	        e.printStackTrace();
  	    }
  	    
  	    return listDatastNIANU;
  	}

     List<Point2D> sttaskCoordinates = new ArrayList<>();
  	
     public void ShowIUstTask() {
         assert sttaskCoordinates != null : "sttaskCoordinates should not be null";
         assert stIU != null : "stIU should not be null";

         GraphicsContext gc = stIU.getGraphicsContext2D();
         ObservableList<SousTacheData> IUTaskList = SelectInBdIUSubTask(TakeIDTaskIU());
         double x = 10;
         double y = 10;

         for (SousTacheData sttask : IUTaskList) {
             assert sttask != null : "sttask should not be null";
             String nomstTache = sttask.getName();
          // Stocker les coordonnées de la tâche
             sttaskCoordinates.add(new Point2D(x + 10, y + 5));
            
             if (nomstTache != null) {
		      
		      if (sttask.getDeadline() != null) {
		        LocalDate deadline = sttask.getDeadline().toLocalDate();
		        assert deadline != null : "Deadline should not be null";

		        LocalDate currentDate = LocalDate.now();
		        long daysUntilDeadline = ChronoUnit.DAYS.between(currentDate, deadline);
		        assert daysUntilDeadline >= 0 : "Days until deadline should be positive";
		        
		        if (daysUntilDeadline == 1 ||daysUntilDeadline == 0) {
		          gc.setFill(Color.RED);
		        } else if (daysUntilDeadline < 0) {
		          gc.setFill(Color.GRAY);
		        } else {
		          gc.setFill(Color.BLACK);
		        }
		      } else {
		        // Tache sans deadline, afficher en noir par défaut
		        gc.setFill(Color.BLACK);
		      }
		      gc.fillOval(x, y, 5, 5);
		      gc.fillText(nomstTache, x + 10, y + 5);
		    } else {
		      // Tache sans nom, afficher une indication par défaut
		      gc.fillText("Tache sans nom", x + 10, y + 5);
		    }

		    // Décalage vertical pour la prochaine tâche
		    x += 20;
		    y += 20;
		    
		  }
		}

     List<Point2D> sttaskCoordinatesibnu = new ArrayList<>();
   	
     public void ShowIBNUstTask() {
         assert sttaskCoordinatesibnu != null : "sttaskCoordinates should not be null";
         assert stIBNU != null : "stIU should not be null";

         GraphicsContext gc = stIBNU.getGraphicsContext2D();
         ObservableList<SousTacheData> IBNUTaskList = SelectInBdIBNUSubTask(TakeIDTaskIBNU());
         double x = 10;
         double y = 10;

         for (SousTacheData sttask : IBNUTaskList) {
             assert sttask != null : "sttask ne doit pas être null";
             String nomstTache = sttask.getName();
          // Stocker les coordonnées de la tâche
             sttaskCoordinatesibnu.add(new Point2D(x + 10, y + 5));
             if (nomstTache != null) {
   		      
   		      if (sttask.getDeadline() != null) {
   		        LocalDate deadline = sttask.getDeadline().toLocalDate();
   		        assert deadline != null : "Deadline should not be null";

   		        LocalDate currentDate = LocalDate.now();
   		        long daysUntilDeadline = ChronoUnit.DAYS.between(currentDate, deadline);
   		        assert daysUntilDeadline >= 0 : "Days until deadline should be positive";
   		        
   		        if (daysUntilDeadline == 1 ||daysUntilDeadline == 0) {
   		          gc.setFill(Color.RED);
   		        } else if (daysUntilDeadline < 0) {
   		          gc.setFill(Color.GRAY);
   		        } else {
   		          gc.setFill(Color.BLACK);
   		        }
   		      } else {
   		        // Tache sans deadline, afficher en noir par défaut
   		        gc.setFill(Color.BLACK);
   		      }
   		      gc.fillOval(x, y, 5, 5);
   		      gc.fillText(nomstTache, x + 10, y + 5);
   		    } else {
   		      // Tache sans nom, afficher une indication par défaut
   		      gc.fillText("Tache sans nom", x + 10, y + 5);
   		    }

   		    // Décalage vertical pour la prochaine tâche
   		    x += 20;
   		    y += 20;
   		    
   		  }
   		}


     List<Point2D> sttaskCoordinatesnibu = new ArrayList<>();
   	
     public void ShowNIBUstTask() {
         assert sttaskCoordinatesnibu != null : "sttaskCoordinates should not be null";
         assert stNIBU != null : "stIU should not be null";

         GraphicsContext gc = stNIBU.getGraphicsContext2D();
         ObservableList<SousTacheData> NIBUTaskList = SelectInBdNIBUSubTask(TakeIDTaskNIBU());
         double x = 10;
         double y = 10;

         for (SousTacheData sttask : NIBUTaskList) {
             assert sttask != null : "sttask should not be null";
             String nomstTache = sttask.getName();
          // Stocker les coordonnées de la tâche
             sttaskCoordinatesnibu.add(new Point2D(x + 10, y + 5));
             if (nomstTache != null) {
   		      
   		      if (sttask.getDeadline() != null) {
   		        LocalDate deadline = sttask.getDeadline().toLocalDate();
   		        assert deadline != null : "Deadline should not be null";

   		        LocalDate currentDate = LocalDate.now();
   		        long daysUntilDeadline = ChronoUnit.DAYS.between(currentDate, deadline);
   		        assert daysUntilDeadline >= 0 : "Days until deadline should be positive";
   		        
   		        if (daysUntilDeadline == 1 ||daysUntilDeadline == 0) {
   		          gc.setFill(Color.RED);
   		        } else if (daysUntilDeadline < 0) {
   		          gc.setFill(Color.GRAY);
   		        } else {
   		          gc.setFill(Color.BLACK);
   		        }
   		      } else {
   		        // Tache sans deadline, afficher en noir par défaut
   		        gc.setFill(Color.BLACK);
   		      }
   		      gc.fillOval(x, y, 5, 5);
   		      gc.fillText(nomstTache, x + 10, y + 5);
   		    } else {
   		      // Tache sans nom, afficher une indication par défaut
   		      gc.fillText("Tache sans nom", x + 10, y + 5);
   		    }

   		    // Décalage vertical pour la prochaine tâche
   		    x += 20;
   		    y += 20;
   		    
   		  }
   		}


  	
     List<Point2D> sttaskCoordinatesnianu = new ArrayList<>();
   	
     public void ShowNIANUstTask() {
         assert sttaskCoordinatesnianu != null : "sttaskCoordinates should not be null";
         assert stNIANU != null : "stIU should not be null";

         GraphicsContext gc = stNIANU.getGraphicsContext2D();
         ObservableList<SousTacheData> NIANUTaskList = SelectInBdNIANUSubTask(TakeIDTaskNIANU());
         double x = 10;
         double y = 10;

         for (SousTacheData sttask : NIANUTaskList) {
             assert sttask != null : "sttask should not be null";
             String nomstTache = sttask.getName();
          // Stocker les coordonnées de la tâche
             sttaskCoordinatesnianu.add(new Point2D(x + 10, y + 5));
             if (nomstTache != null) {
   		      
   		      if (sttask.getDeadline() != null) {
   		        LocalDate deadline = sttask.getDeadline().toLocalDate();
   		        assert deadline != null : "Deadline should not be null";

   		        LocalDate currentDate = LocalDate.now();
   		        long daysUntilDeadline = ChronoUnit.DAYS.between(currentDate, deadline);
   		        assert daysUntilDeadline >= 0 : "Days until deadline should be positive";
   		        
   		        if (daysUntilDeadline == 1 ||daysUntilDeadline == 0) {
   		          gc.setFill(Color.RED);
   		        } else if (daysUntilDeadline < 0) {
   		          gc.setFill(Color.GRAY);
   		        } else {
   		          gc.setFill(Color.BLACK);
   		        }
   		      } else {
   		        // Tache sans deadline, afficher en noir par défaut
   		        gc.setFill(Color.BLACK);
   		      }
   		      gc.fillOval(x, y, 5, 5);
   		      gc.fillText(nomstTache, x + 10, y + 5);
   		    } else {
   		      // Tache sans nom, afficher une indication par défaut
   		      gc.fillText("Tache sans nom", x + 10, y + 5);
   		    }

   		    // Décalage vertical pour la prochaine tâche
   		    x += 20;
   		    y += 20;
   		    
   		  }
   		}


  	//selectionner une sous tache
     public void SelectIUstTask() {
    	    stIU.setOnMouseClicked(event -> {
    	        double mouseX = event.getX();
    	        double mouseY = event.getY();

    	        ObservableList<SousTacheData> sttasks = SelectInBdIUSubTask(TakeIDTaskIU());
    	        
    	        assert !sttasks.isEmpty() : "La liste des sous-tâches ne doit pas être vide";

    	        // Vérifier si les coordonnées du clic correspondent à une tâche
    	        for (SousTacheData sttask : sttasks) {
    	            int index = sttasks.indexOf(sttask);
    	            assert index >= 0 && index < sttaskCoordinates.size() : "L'indice de la sous-tâche est invalide";
    	            
    	            double xstTache = sttaskCoordinates.get(index).getX();
    	            double ystTache = sttaskCoordinates.get(index).getY();
    	            double tolerance = 10.0;

    	            // Vérifier si les coordonnées du clic sont près des coordonnées de la tâche
    	            if (Math.abs(mouseX - xstTache) < tolerance && Math.abs(mouseY - ystTache) < tolerance) {
    	                // Action à effectuer si le clic est près du nom de la tâche
    	                generalview.setVisible(false);
    	                Deletetab.setVisible(false);
    	                archivedtab.setVisible(false);
    	                helptab.setVisible(false);
    	                viewtaskofiu.setVisible(false);
    	                viewtaskofibnu.setVisible(false);
    	                viewtaskofnibu.setVisible(false);
    	                viewtaskofnianu.setVisible(false);
    	                viewsubtaskofiu.setVisible(true);
    	                viewsubtaskofibnu.setVisible(false);
    	                viewsubtaskofnibu.setVisible(false);
    	                viewsubtaskofnianu.setVisible(false);
    	                //Affiche les infos sur les champs respectifs
    	                viewsubtaskfieldiu.setText(sttask.getName());
    	                viewsubtaskfieldiu.setDisable(true);
    	                viewdescripfieldiu.setText(sttask.getDescription());
    	                viewdeadlinefieldiu.setValue(sttask.getDeadline().toLocalDate());
    	                viewtimefieldiu.setText(sttask.getTime()); 
    	                viewdonefieldiu.setValue(sttask.getDone());
    	                viewrateimpfieldiu.setValue(sttask.getRateimp());
    	                viewrateurgfieldiu.setValue(sttask.getRateurg());
    	                GraphicsContext gc = stIU.getGraphicsContext2D();
    	                gc.clearRect(0, 0, stIU.getWidth(), stIU.getHeight());  
    	                
    	                
    	                break; // Sortir de la boucle après avoir trouvé une tâche à proximité du clic
    	            }
    	        }
    	    });
    	}

     public void SelectIBNUstTask() {
 	    stIBNU.setOnMouseClicked(event -> {
 	        double mouseX = event.getX();
 	        double mouseY = event.getY();

 	        ObservableList<SousTacheData> sttasks = SelectInBdIBNUSubTask(TakeIDTaskIBNU());
 	        
 	        assert !sttasks.isEmpty() : "La liste des sous-tâches ne doit pas être vide";

 	        // Vérifier si les coordonnées du clic correspondent à une tâche
 	        for (SousTacheData sttask : sttasks) {
 	            int index = sttasks.indexOf(sttask);
 	            assert index >= 0 && index < sttaskCoordinatesibnu.size() : "L'indice de la sous-tâche est invalide";
 	            
 	            double xstTache = sttaskCoordinatesibnu.get(index).getX();
 	            double ystTache = sttaskCoordinatesibnu.get(index).getY();
 	            double tolerance = 10.0;

 	            // Vérifier si les coordonnées du clic sont près des coordonnées de la tâche
 	            if (Math.abs(mouseX - xstTache) < tolerance && Math.abs(mouseY - ystTache) < tolerance) {
 	                // Action à effectuer si le clic est près du nom de la tâche
 	                generalview.setVisible(false);
 	                Deletetab.setVisible(false);
 	                archivedtab.setVisible(false);
 	                helptab.setVisible(false);
 	                viewtaskofiu.setVisible(false);
 	                viewtaskofibnu.setVisible(false);
 	                viewtaskofnibu.setVisible(false);
 	                viewtaskofnianu.setVisible(false);
 	                viewsubtaskofiu.setVisible(false);
 	                viewsubtaskofibnu.setVisible(true);
 	                viewsubtaskofnibu.setVisible(false);
 	                viewsubtaskofnianu.setVisible(false);
 	                //Affiche les infos sur les champs respectifs
 	                viewsubtaskfieldibnu.setText(sttask.getName());
 	                viewsubtaskfieldibnu.setDisable(true);
 	                viewdescripfieldibnu.setText(sttask.getDescription());
 	                viewdeadlinefieldibnu.setValue(sttask.getDeadline().toLocalDate());
 	                viewtimefieldibnu.setText(sttask.getTime()); 
 	                viewdonefieldibnu.setValue(sttask.getDone());
 	                viewrateimpfieldibnu.setValue(sttask.getRateimp());
 	                viewrateurgfieldibnu.setValue(sttask.getRateurg());
 	                GraphicsContext gc = stIBNU.getGraphicsContext2D();
 	                gc.clearRect(0, 0, stIBNU.getWidth(), stIBNU.getHeight());  
 	                
 	                
 	                break; // Sortir de la boucle après avoir trouvé une tâche à proximité du clic
 	            }
 	        }
 	    });
 	}

     public void SelectNIBUstTask() {
 	    stNIBU.setOnMouseClicked(event -> {
 	        double mouseX = event.getX();
 	        double mouseY = event.getY();

 	        ObservableList<SousTacheData> sttasks = SelectInBdNIBUSubTask(TakeIDTaskNIBU());
 	        
 	        assert !sttasks.isEmpty() : "La liste des sous-tâches ne doit pas être vide";

 	        // Vérifier si les coordonnées du clic correspondent à une tâche
 	        for (SousTacheData sttask : sttasks) {
 	            int index = sttasks.indexOf(sttask);
 	            assert index >= 0 && index < sttaskCoordinatesnibu.size() : "L'indice de la sous-tâche est invalide";
 	            
 	            double xstTache = sttaskCoordinatesnibu.get(index).getX();
 	            double ystTache = sttaskCoordinatesnibu.get(index).getY();
 	            double tolerance = 10.0;

 	            // Vérifier si les coordonnées du clic sont près des coordonnées de la tâche
 	            if (Math.abs(mouseX - xstTache) < tolerance && Math.abs(mouseY - ystTache) < tolerance) {
 	                // Action à effectuer si le clic est près du nom de la tâche
 	                generalview.setVisible(false);
 	                Deletetab.setVisible(false);
 	                archivedtab.setVisible(false);
 	                helptab.setVisible(false);
 	                viewtaskofiu.setVisible(false);
 	                viewtaskofibnu.setVisible(false);
 	                viewtaskofnibu.setVisible(false);
 	                viewtaskofnianu.setVisible(false);
 	                viewsubtaskofiu.setVisible(false);
 	                viewsubtaskofibnu.setVisible(false);
 	                viewsubtaskofnibu.setVisible(true);
 	                viewsubtaskofnianu.setVisible(false);
 	                //Affiche les infos sur les champs respectifs
 	                viewsubtaskfieldnibu.setText(sttask.getName());
 	                viewsubtaskfieldnibu.setDisable(true);
 	                viewdescripfieldnibu.setText(sttask.getDescription());
 	                viewdeadlinefieldnibu.setValue(sttask.getDeadline().toLocalDate());
 	                viewtimefieldnibu.setText(sttask.getTime()); 
 	                viewdonefieldnibu.setValue(sttask.getDone());
 	                viewrateimpfieldnibu.setValue(sttask.getRateimp());
 	                viewrateurgfieldnibu.setValue(sttask.getRateurg());
 	                GraphicsContext gc = stNIBU.getGraphicsContext2D();
 	                gc.clearRect(0, 0, stNIBU.getWidth(), stNIBU.getHeight());  
 	                
 	                
 	                break; // Sortir de la boucle après avoir trouvé une tâche à proximité du clic
 	            }
 	        }
 	    });
 	}

     public void SelectNIANUstTask() {
 	    stNIANU.setOnMouseClicked(event -> {
 	        double mouseX = event.getX();
 	        double mouseY = event.getY();

 	        ObservableList<SousTacheData> sttasks = SelectInBdNIANUSubTask(TakeIDTaskNIANU());
 	        
 	        assert !sttasks.isEmpty() : "La liste des sous-tâches ne doit pas être vide";

 	        // Vérifier si les coordonnées du clic correspondent à une tâche
 	        for (SousTacheData sttask : sttasks) {
 	            int index = sttasks.indexOf(sttask);
 	            assert index >= 0 && index < sttaskCoordinatesnianu.size() : "L'indice de la sous-tâche est invalide";
 	            
 	            double xstTache = sttaskCoordinatesnianu.get(index).getX();
 	            double ystTache = sttaskCoordinatesnianu.get(index).getY();
 	            double tolerance = 10.0;

 	            // Vérifier si les coordonnées du clic sont près des coordonnées de la tâche
 	            if (Math.abs(mouseX - xstTache) < tolerance && Math.abs(mouseY - ystTache) < tolerance) {
 	                // Action à effectuer si le clic est près du nom de la tâche
 	                generalview.setVisible(false);
 	                Deletetab.setVisible(false);
 	                archivedtab.setVisible(false);
 	                helptab.setVisible(false);
 	                viewtaskofiu.setVisible(false);
 	                viewtaskofibnu.setVisible(false);
 	                viewtaskofnibu.setVisible(false);
 	                viewtaskofnianu.setVisible(false);
 	                viewsubtaskofiu.setVisible(false);
 	                viewsubtaskofibnu.setVisible(false);
 	                viewsubtaskofnibu.setVisible(false);
 	                viewsubtaskofnianu.setVisible(true);
 	                //Affiche les infos sur les champs respectifs
 	                viewsubtaskfieldnianu.setText(sttask.getName());
 	                viewsubtaskfieldnianu.setDisable(true);
 	                viewdescripfieldnianu.setText(sttask.getDescription());
 	                viewdeadlinefieldnianu.setValue(sttask.getDeadline().toLocalDate());
 	                viewtimefieldnianu.setText(sttask.getTime()); 
 	                viewdonefieldnianu.setValue(sttask.getDone());
 	                viewrateimpfieldnianu.setValue(sttask.getRateimp());
 	                viewrateurgfieldnianu.setValue(sttask.getRateurg());
 	                GraphicsContext gc = stNIANU.getGraphicsContext2D();
 	                gc.clearRect(0, 0, stNIANU.getWidth(), stNIANU.getHeight());  
 	                
 	                
 	                break; // Sortir de la boucle après avoir trouvé une tâche à proximité du clic
 	            }
 	        }
 	    });
 	}

  	
  //effacer les champs
  	public void ClearstTaskFieldIU() {
  		viewsubtaskfieldiu.setText("");
  		viewdescripfieldiu.setText("");
  		viewdeadlinefieldiu.setValue(null);
  		viewrateimpfieldiu.setValue(0);
  		viewrateurgfieldiu.setValue(0);
  		viewtimefieldiu.setText("");
  		viewdonefieldiu.getSelectionModel().clearSelection();
     }
  	public void ClearstTaskFieldIBNU() {
  		viewsubtaskfieldibnu.setText("");
  		viewdescripfieldibnu.setText("");
  		viewdeadlinefieldibnu.setValue(null);
  		viewrateimpfieldibnu.setValue(0);
  		viewrateurgfieldibnu.setValue(0);
  		viewtimefieldibnu.setText("");
  		viewdonefieldibnu.getSelectionModel().clearSelection();
     }
  	public void ClearstTaskFieldNIBU() {
  		viewsubtaskfieldnibu.setText("");
  		viewdescripfieldnibu.setText("");
  		viewdeadlinefieldnibu.setValue(null);
  		viewrateimpfieldnibu.setValue(0);
  		viewrateurgfieldnibu.setValue(0);
  		viewtimefieldnibu.setText("");
  		viewdonefieldnibu.getSelectionModel().clearSelection();
     }
  	public void ClearstTaskFieldNIANU() {
  		viewsubtaskfieldnianu.setText("");
  		viewdescripfieldnianu.setText("");
  		viewdeadlinefieldnianu.setValue(null);
  		viewrateimpfieldnianu.setValue(0);
  		viewrateurgfieldnianu.setValue(0);
  		viewtimefieldnianu.setText("");
  		viewdonefieldnianu.getSelectionModel().clearSelection();
     }
  	  	
  	//Update st
  	
  	public void UpdatestTaskIU() {
    	connect = DataBase.connectDb();
  
            try {
               
                if (viewsubtaskfieldiu.getText().isEmpty() || viewdeadlinefieldiu.getValue() == null
                        || viewdonefieldiu.getSelectionModel().isEmpty()|| viewtimefieldiu.getText().isEmpty()) {
                	AlertMessage();}
                else {
                	if (viewdonefieldiu.getSelectionModel().getSelectedItem().equals("Yes")) {
                		
                		
                		
                		Alert alertt;
                        alertt = new Alert(AlertType.CONFIRMATION);
                        alertt.setTitle("Confirmation Message");
                        alertt.setHeaderText(null);
                        alertt.setContentText("As you have done " + viewsubtaskfieldiu.getText() + " do you want to delete it ?");
                        Optional<ButtonType> option = alertt.showAndWait();
                                    
                        if (option.isPresent() && option.get() == ButtonType.OK) {
                        	DeletestTaskIU(); 
                        }
                	} else {
                		int subrateimpmodifIUValue = (int) viewrateimpfieldiu.getValue();
                        int subrateurgmodifIUValue = (int) viewrateurgfieldiu.getValue();
                		    // Requête de mise à jour
                		    String updateSql = "UPDATE soustache SET description_st = ?, deadline_st = ?, time_st = ?, rateimp_st = ?, rateurg_st = ?, done_st = ? WHERE name_st = ?";
                		    prepare = connect.prepareStatement(updateSql);
                		    prepare.setString(1, viewdescripfieldiu.getText());
                		    prepare.setDate(2, java.sql.Date.valueOf(viewdeadlinefieldiu.getValue())); // Convertir LocalDate en java.sql.Date
                		    prepare.setString(3, viewtimefieldiu.getText());
                		    prepare.setInt(4, subrateimpmodifIUValue);
                            prepare.setInt(5, subrateurgmodifIUValue);
                		    prepare.setString(6, viewdonefieldiu.getSelectionModel().getSelectedItem());
                		    prepare.setString(7, viewsubtaskfieldiu.getText());

                		    // Exécution de la requête de mise à jour
                		    prepare.executeUpdate();
                		    AlertMessagePositive("Updated");
                            ViewtasksIU();
                            ShowIUstTask() ;
           	   	         SelectIUstTask();
                        	ClearstTaskFieldIU();
                		} }

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
  	public void UpdatestTaskIBNU() {
    	connect = DataBase.connectDb();
  
            try {
               
                if (viewsubtaskfieldibnu.getText().isEmpty() || viewdeadlinefieldibnu.getValue() == null
                        || viewdonefieldibnu.getSelectionModel().isEmpty()|| viewtimefieldibnu.getText().isEmpty()) {
                	AlertMessage();}
                else {
                	if (viewdonefieldibnu.getSelectionModel().getSelectedItem().equals("Yes")) {
                		Alert alertt;
                        alertt = new Alert(AlertType.CONFIRMATION);
                        alertt.setTitle("Confirmation Message");
                        alertt.setHeaderText(null);
                        alertt.setContentText("As you have done " + viewsubtaskfieldibnu.getText() + " do you want to delete it ?");
                        Optional<ButtonType> option = alertt.showAndWait();
                                    
                        if (option.isPresent() && option.get() == ButtonType.OK) {
                        	DeletestTaskIBNU(); 
                        }
                	} else {
                		int subrateimpmodifIBNUValue = (int) viewrateimpfieldibnu.getValue();
                        int subrateurgmodifIBNUValue = (int) viewrateurgfieldibnu.getValue();
                		    // Requête de mise à jour
                		    String updateSql = "UPDATE soustache SET description_st = ?, deadline_st = ?, time_st = ?, rateimp_st = ?, rateurg_st = ?, done_st = ? WHERE name_st = ?";
                		    prepare = connect.prepareStatement(updateSql);
                		    prepare.setString(1, viewdescripfieldibnu.getText());
                		    prepare.setDate(2, java.sql.Date.valueOf(viewdeadlinefieldibnu.getValue())); // Convertir LocalDate en java.sql.Date
                		    prepare.setString(3, viewtimefieldibnu.getText());
                		    prepare.setInt(4, subrateimpmodifIBNUValue);
                            prepare.setInt(5, subrateurgmodifIBNUValue);
                		    prepare.setString(6, viewdonefieldibnu.getSelectionModel().getSelectedItem());
                		    prepare.setString(7, viewsubtaskfieldibnu.getText());

                		    // Exécution de la requête de mise à jour
                		    prepare.executeUpdate();
                		    AlertMessagePositive("Updated");
                            ViewtasksIBNU();
                            ShowIBNUstTask() ;
           	   	         SelectIBNUstTask();
                        	ClearstTaskFieldIBNU();
                		} }

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
  	public void UpdatestTaskNIBU() {
    	connect = DataBase.connectDb();
  
            try {
               
                if (viewsubtaskfieldnibu.getText().isEmpty() || viewdeadlinefieldnibu.getValue() == null
                        || viewdonefieldnibu.getSelectionModel().isEmpty()|| viewtimefieldnibu.getText().isEmpty()) {
                	AlertMessage();}
                else {
                	if (viewdonefieldnibu.getSelectionModel().getSelectedItem().equals("Yes")) {
                		Alert alertt;
                        alertt = new Alert(AlertType.CONFIRMATION);
                        alertt.setTitle("Confirmation Message");
                        alertt.setHeaderText(null);
                        alertt.setContentText("As you have done " + viewsubtaskfieldnibu.getText() + " do you want to delete it ?");
                        Optional<ButtonType> option = alertt.showAndWait();
                                    
                        if (option.isPresent() && option.get() == ButtonType.OK) {
                        	DeletestTaskNIBU(); 
                        }
                	} else {
                		int subrateimpmodifNIBUValue = (int) viewrateimpfieldnibu.getValue();
                        int subrateurgmodifNIBUValue = (int) viewrateurgfieldnibu.getValue();
                		    // Requête de mise à jour
                		    String updateSql = "UPDATE soustache SET description_st = ?, deadline_st = ?, time_st = ?, rateimp_st = ?, rateurg_st = ?, done_st = ? WHERE name_st = ?";
                		    prepare = connect.prepareStatement(updateSql);
                		    prepare.setString(1, viewdescripfieldnibu.getText());
                		    prepare.setDate(2, java.sql.Date.valueOf(viewdeadlinefieldnibu.getValue())); // Convertir LocalDate en java.sql.Date
                		    prepare.setString(3, viewtimefieldnibu.getText());
                		    prepare.setInt(4, subrateimpmodifNIBUValue);
                            prepare.setInt(5, subrateurgmodifNIBUValue);
                		    prepare.setString(6, viewdonefieldnibu.getSelectionModel().getSelectedItem());
                		    prepare.setString(7, viewsubtaskfieldnibu.getText());

                		    // Exécution de la requête de mise à jour
                		    prepare.executeUpdate();
                		    AlertMessagePositive("Updated");
                            ViewtasksNIBU();
                            ShowNIBUstTask() ;
              	   	         SelectNIBUstTask();
                            ClearstTaskFieldNIBU();
                		} }

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
  	public void UpdatestTaskNIANU() {
    	connect = DataBase.connectDb();
  
            try {
               
                if (viewsubtaskfieldnianu.getText().isEmpty() || viewdeadlinefieldnianu.getValue() == null
                        || viewdonefieldnianu.getSelectionModel().isEmpty()|| viewtimefieldnianu.getText().isEmpty()) {
                	AlertMessage();}
                else {
                	if (viewdonefieldnianu.getSelectionModel().getSelectedItem().equals("Yes")) {
                		Alert alertt;
                        alertt = new Alert(AlertType.CONFIRMATION);
                        alertt.setTitle("Confirmation Message");
                        alertt.setHeaderText(null);
                        alertt.setContentText("As you have done " + viewsubtaskfieldnianu.getText() + " do you want to delete it ?");
                        Optional<ButtonType> option = alertt.showAndWait();
                                    
                        if (option.isPresent() && option.get() == ButtonType.OK) {
                        	DeletestTaskNIANU(); 
                        }
                	} else {
                		int subrateimpmodifNIANUValue = (int) viewrateimpfieldnianu.getValue();
                        int subrateurgmodifNIANUValue = (int) viewrateurgfieldnianu.getValue();
                		    // Requête de mise à jour
                		    String updateSql = "UPDATE soustache SET description_st = ?, deadline_st = ?, time_st = ?, rateimp_st = ?, rateurg_st = ?, done_st = ? WHERE name_st = ?";
                		    prepare = connect.prepareStatement(updateSql);
                		    prepare.setString(1, viewdescripfieldnianu.getText());
                		    prepare.setDate(2, java.sql.Date.valueOf(viewdeadlinefieldnianu.getValue())); // Convertir LocalDate en java.sql.Date
                		    prepare.setString(3, viewtimefieldnianu.getText());
                		    prepare.setInt(4, subrateimpmodifNIANUValue);
                            prepare.setInt(5, subrateurgmodifNIANUValue);
                		    prepare.setString(6, viewdonefieldnianu.getSelectionModel().getSelectedItem());
                		    prepare.setString(7, viewsubtaskfieldnianu.getText());

                		    // Exécution de la requête de mise à jour
                		    prepare.executeUpdate();
                		    AlertMessagePositive("Updated");
                            ViewtasksNIANU(); 
                            ShowNIANUstTask() ;
              	   	         SelectNIANUstTask();
                        	ClearstTaskFieldNIANU();
                		} }

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
  	
  	//Delete st
  	
  	public void DeletestTaskIU() {
  	   
          
          String sql = "DELETE FROM soustache WHERE id_t = ? AND name_st = ?";  
          connect = DataBase.connectDb();
          
          
          try {
          	Alert alert;
              alert = new Alert(AlertType.CONFIRMATION);
              alert.setTitle("Confirmation Message");
              alert.setHeaderText(null);
              alert.setContentText("Are you sure you want definitelly delete subtask: " + viewsubtaskfieldiu.getText() + "?");
              Optional<ButtonType> option = alert.showAndWait();
              
                          
              if (option.isPresent() && option.get() == ButtonType.OK) {
                  prepare = connect.prepareStatement(sql);
                  prepare.setInt(1, TakeIDTaskIU());
                  prepare.setString(2, viewsubtaskfieldiu.getText());
                  prepare.executeUpdate();

                  AlertMessagePositive("Deleted");
                 
                          
                  // Masquer les onglets et autres éléments UI non pertinents
                 
                  ViewtasksIU();       
                
                  ShowIUstTask() ;
                  
    	   	         SelectIUstTask();
    	   	         
              	ClearstTaskFieldIU();
               
               
                 
              }
          } catch (Exception e) {
              e.printStackTrace();
          
          }
      }
 
  	public void DeletestTaskIBNU() {
   	   
        
        String sql = "DELETE FROM soustache WHERE id_t = ? AND name_st = ?";  
        connect = DataBase.connectDb();
        
        
        try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want definitelly delete subtask: " + viewsubtaskfieldibnu.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
            
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, TakeIDTaskIBNU());
                prepare.setString(2, viewsubtaskfieldibnu.getText());
                prepare.executeUpdate();

                AlertMessagePositive("Deleted");
             
                        
                // Masquer les onglets et autres éléments UI non pertinents
               
                ViewtasksIBNU();       
               
                ShowIBNUstTask() ;
  	   	         SelectIBNUstTask();
            	ClearstTaskFieldIBNU();
             
             
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }

  	public void DeletestTaskNIBU() {
   	   
        
        String sql = "DELETE FROM soustache WHERE id_t = ? AND name_st = ?";  
        connect = DataBase.connectDb();
        
        
        try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want definitelly delete subtask: " + viewsubtaskfieldnibu.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
            
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, TakeIDTaskNIBU());
                prepare.setString(2, viewsubtaskfieldnibu.getText());
                prepare.executeUpdate();

                AlertMessagePositive("Deleted");
                
                // Masquer les onglets et autres éléments UI non pertinents
                
                ViewtasksNIBU();       
                
                ShowNIBUstTask() ;
  	   	         SelectNIBUstTask();
            	ClearstTaskFieldNIBU();
             
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }

  	public void DeletestTaskNIANU() {
   	   
        
        String sql = "DELETE FROM soustache WHERE id_t = ? AND name_st = ?";  
        connect = DataBase.connectDb();
        
        
        try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want definitelly delete subtask: " + viewsubtaskfieldnianu.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
            
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1, TakeIDTaskNIANU());
                prepare.setString(2, viewsubtaskfieldnianu.getText());
                prepare.executeUpdate();

                AlertMessagePositive("Deleted");
               
                        
                // Masquer les onglets et autres éléments UI non pertinents
               
                ViewtasksNIANU();       
                
                ShowNIANUstTask() ;
  	   	         SelectNIANUstTask();
            	ClearstTaskFieldNIANU();
             
             
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }

  	public void DefDeleteTask() {
  	   //récupère l'élément sélectionné dans un TableView et le stocke dans TacheD.
      	
      	TacheData TacheD = tableauofdeled.getSelectionModel().getSelectedItem();
      	int num = tableauofdeled.getSelectionModel().getSelectedIndex();

      	//si aucun élément n'est séléctionné
      	
          if ((num - 1) < -1) {
              return;}
          
          String sql = "DELETE FROM tache WHERE folder = 'Deleted'AND name = ?";  
          connect = DataBase.connectDb();
          TaskDelfield.setText(TacheD.getName()); 
          
          try {
          	Alert alert;
              alert = new Alert(AlertType.CONFIRMATION);
              alert.setTitle("Confirmation Message");
              alert.setHeaderText(null);
              alert.setContentText("Are you sure you want definitelly DELETE task: " + TaskDelfield.getText() + "?");
              Optional<ButtonType> option = alert.showAndWait();
              
                          
              if (option.isPresent() && option.get() == ButtonType.OK) {
                  prepare = connect.prepareStatement(sql);
                  prepare.setString(1, TaskDelfield.getText());
                  prepare.executeUpdate();

                  AlertMessagePositive("Deleted");
                          
                  // Masquer les onglets et autres éléments UI non pertinents
                 
                  Deletetab.setVisible(true);
                  generalview.setVisible(false);
                  archivedtab.setVisible(false);
                  helptab.setVisible(false);
                 viewtaskofiu.setVisible(false);
                  viewtaskofibnu.setVisible(false);
                  viewtaskofnibu.setVisible(false);
                  viewtaskofnianu.setVisible(false);
                  viewsubtaskofiu.setVisible(false);
                  viewsubtaskofibnu.setVisible(false);
                  viewsubtaskofnibu.setVisible(false);
                  viewsubtaskofnianu.setVisible(false);
                          
                  // Actualiser la liste des tâches
                  ShowDeletedTask();
                  TaskDelfield.setText("");
                 
              }
          } catch (Exception e) {
              e.printStackTrace();
          
          }
      }
          
  	 //archiver une tache supprimée
    public void FromDeletedToArchived() {
  	  String sql = "UPDATE tache SET folder= 'Archived' WHERE name = ? AND deadline = ?";
        connect = DataBase.connectDb();
        TacheData TacheD = tableauofdeled.getSelectionModel().getSelectedItem();
    	int num = tableauofdeled.getSelectionModel().getSelectedIndex();

    	//si aucun élément n'est séléctionné
    	
        if ((num - 1) < -1) {
            return;}
    
        TaskDelfield.setText(TacheD.getName());
        DescripDelfield.setText(TacheD.getDescription());
        DeadDelfield.setValue(TacheD.getDeadline().toLocalDate());
        TimeDelfield.setText(TacheD.getTime());
        DoneDelfield.setValue(TacheD.getDone());
        try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to archive task: " + TaskDelfield.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, TaskDelfield.getText());
                prepare.setString(2, DeadDelfield.getValue().toString());

                
                prepare.executeUpdate();

                AlertMessagePositive("Archived");

               
                        
                // Masquer les onglets et autres éléments UI non pertinents
                generalview.setVisible(false);
                Deletetab.setVisible(true);
                archivedtab.setVisible(false);
                helptab.setVisible(false);
                viewtaskofiu.setVisible(false);
                viewtaskofibnu.setVisible(false);
                viewtaskofnibu.setVisible(false);
                viewtaskofnianu.setVisible(false);
                viewsubtaskofiu.setVisible(false);
                viewsubtaskofibnu.setVisible(false);
                viewsubtaskofnibu.setVisible(false);
                viewsubtaskofnianu.setVisible(false);
                        
                // Actualiser la liste des tâches
                ShowArchivedTask();
                ShowDeletedTask();
                
                TaskDelfield.setText("");
                DescripDelfield.setText("");
                DeadDelfield.setValue(null);
                TimeDelfield.setText("");
                DoneDelfield.getSelectionModel().clearSelection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
  	
    //supprimer une tache archivée
    public void FromArchivedToDeleted() {
    	  String sql = "UPDATE tache SET folder= 'Deleted' WHERE name = ? AND deadline = ?";
        connect = DataBase.connectDb();
        TacheData TacheD = tableauarchived.getSelectionModel().getSelectedItem();
    	int num = tableauarchived.getSelectionModel().getSelectedIndex();

    	//si aucun élément n'est séléctionné
    	
        if ((num - 1) < -1) {
            return;}
    
        TaskArchivedfield.setText(TacheD.getName());
        DescripArchivedfield.setText(TacheD.getDescription());
        DeadArchivedfield.setValue(TacheD.getDeadline().toLocalDate());
        TimeArchivedfield.setText(TacheD.getTime());
        DoneArchivedfield.setValue(TacheD.getDone());
        try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete task: " + TaskArchivedfield.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, TaskArchivedfield.getText());
                prepare.setString(2, DeadArchivedfield.getValue().toString());
                
                prepare.executeUpdate();

                AlertMessagePositive("Deleted");

               
                        
                // Masquer les onglets et autres éléments UI non pertinents
                generalview.setVisible(false);
                Deletetab.setVisible(false);
                archivedtab.setVisible(true);
                helptab.setVisible(false);
               viewtaskofiu.setVisible(false);
                viewtaskofibnu.setVisible(false);
                viewtaskofnibu.setVisible(false);
                viewtaskofnianu.setVisible(false);
                viewsubtaskofiu.setVisible(false);
                viewsubtaskofibnu.setVisible(false);
                viewsubtaskofnibu.setVisible(false);
                viewsubtaskofnianu.setVisible(false);
                        
                // Actualiser la liste des tâches
                ShowArchivedTask();
                ShowDeletedTask();
                TaskArchivedfield.setText("");
                DescripArchivedfield.setText("");
                DeadArchivedfield.setValue(null);
                TimeArchivedfield.setText("");
                DoneArchivedfield.getSelectionModel().clearSelection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    
    }
  	
    //Pour remettre une tache supprimée
    public void RecovertaskDeleted(String folder) {
    
    	//Quand je clique sur le bouton recover, je choisis un dossier, et on me met un message de confirmafation et ensuite le dossier et mis dans le dossier de base
    	
    	String sqlrecover = "UPDATE tache SET folder= ? WHERE name = ? AND deadline = ?";
        connect = DataBase.connectDb();
    	TacheData TacheD = tableauofdeled.getSelectionModel().getSelectedItem();
    	int num = tableauofdeled.getSelectionModel().getSelectedIndex();

    	//si aucun élément n'est séléctionné
    	
        if ((num - 1) < -1) {
            return;}
    
        TaskDelfield.setText(TacheD.getName());
        DescripDelfield.setText(TacheD.getDescription());
        DeadDelfield.setValue(TacheD.getDeadline().toLocalDate());
        TimeDelfield.setText(TacheD.getTime());
       
    	
        try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to recover task: " + TaskDelfield.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
            
            	prepare = connect.prepareStatement(sqlrecover);
            	prepare.setString(1, folder);
                prepare.setString(2, TaskDelfield.getText());
                prepare.setString(3, DeadDelfield.getValue().toString());
             
                prepare.executeUpdate();
                
                
                AlertMessagePositive("Recovered");
                GraphicsContext gc = IU.getGraphicsContext2D();
        	      gc.clearRect(0, 0, IU.getWidth(), IU.getHeight());
        	      
        	    GraphicsContext gcc = IBNU.getGraphicsContext2D();
    	      gcc.clearRect(0, 0, IBNU.getWidth(), IBNU.getHeight());
    	      
    	    GraphicsContext gccc = NIBU.getGraphicsContext2D();
  	      gccc.clearRect(0, 0, NIBU.getWidth(), NIBU.getHeight());
  	      
  	      GraphicsContext gcccc = NIANU.getGraphicsContext2D();
  	      gcccc.clearRect(0, 0, NIANU.getWidth(), NIANU.getHeight());
                        
                // Masquer les onglets et autres éléments UI non pertinents
  	  
                Deletetab.setVisible(true);
                archivedtab.setVisible(false);
                helptab.setVisible(false);
                viewtaskofiu.setVisible(false);
                viewtaskofibnu.setVisible(false);
                viewtaskofnibu.setVisible(false);
                viewtaskofnianu.setVisible(false);
                viewsubtaskofiu.setVisible(false);
                viewsubtaskofibnu.setVisible(false);
                viewsubtaskofnibu.setVisible(false);
                viewsubtaskofnianu.setVisible(false);
                        
                // Actualiser la liste des tâches
                
                ShowDeletedTask();
                ShowIUTask();
                ShowIBNUTask();
                ShowNIBUTask();
                ShowNIANUTask();
                
                TaskDelfield.setText("");
                DescripDelfield.setText("");
                DeadDelfield.setValue(null);
                TimeDelfield.setText("");
                DoneDelfield.getSelectionModel().clearSelection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    
  //Pour remettre une tache archivée
    public void RecovertaskArchived(String folder) {
    
    	//Quand je clique sur le bouton recover, je choisis un dossier, et on me met un message de confirmafation et ensuite le dossier et mis dans le dossier de base
    	
    	String sqlrecover = "UPDATE tache SET folder= ? WHERE name = ? AND deadline = ?";
        connect = DataBase.connectDb();
    	TacheData TacheD = tableauarchived.getSelectionModel().getSelectedItem();
    	int num = tableauarchived.getSelectionModel().getSelectedIndex();

    	//si aucun élément n'est séléctionné
    	
        if ((num - 1) < -1) {
            return;}
    
        TaskArchivedfield.setText(TacheD.getName());
        DescripArchivedfield.setText(TacheD.getDescription());
        DeadArchivedfield.setValue(TacheD.getDeadline().toLocalDate());
        TimeArchivedfield.setText(TacheD.getTime());
        DoneArchivedfield.setValue(TacheD.getDone());
    	
        try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to recover task: " + TaskArchivedfield.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
            	
            	
            	prepare = connect.prepareStatement(sqlrecover);
            	prepare.setString(1, folder);
                prepare.setString(2, TaskArchivedfield.getText());
                prepare.setString(3, DeadArchivedfield.getValue().toString());
             
                prepare.executeUpdate();
                
                prepare.executeUpdate();
                
                AlertMessagePositive("Recovered");
                // Masquer les onglets et autres éléments UI non pertinents
             
                Deletetab.setVisible(false);
                archivedtab.setVisible(true);
                helptab.setVisible(false);
               viewtaskofiu.setVisible(false);
                viewtaskofibnu.setVisible(false);
                viewtaskofnibu.setVisible(false);
                viewtaskofnianu.setVisible(false);
                viewsubtaskofiu.setVisible(false);
                viewsubtaskofibnu.setVisible(false);
                viewsubtaskofnibu.setVisible(false);
                viewsubtaskofnianu.setVisible(false);
                        
                // Actualiser la liste des tâches
                GraphicsContext gc = IU.getGraphicsContext2D();
        	      gc.clearRect(0, 0, IU.getWidth(), IU.getHeight());
        	      
        	    GraphicsContext gcc = IBNU.getGraphicsContext2D();
    	      gcc.clearRect(0, 0, IBNU.getWidth(), IBNU.getHeight());
    	      
    	    GraphicsContext gccc = NIBU.getGraphicsContext2D();
  	      gccc.clearRect(0, 0, NIBU.getWidth(), NIBU.getHeight());
  	      
  	      GraphicsContext gcccc = NIANU.getGraphicsContext2D();
  	      gcccc.clearRect(0, 0, NIANU.getWidth(), NIANU.getHeight());
                ShowArchivedTask();
                ShowIUTask();
                ShowIBNUTask();
                ShowNIBUTask();
                ShowNIANUTask();
                TaskArchivedfield.setText("");
                DescripArchivedfield.setText("");
                DeadArchivedfield.setValue(null);
                TimeArchivedfield.setText("");
                DoneArchivedfield.getSelectionModel().clearSelection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    
   
 
    //Changer la tache de dossier
    public void ChangeFolderIU(String folder) {
    	String sql = "UPDATE tache SET folder= ? WHERE name = ? AND deadline = ?";
    
    	connect = DataBase.connectDb();
    
    	try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to change folder task ?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
            	
            	
            	prepare = connect.prepareStatement(sql);
            
            	prepare.setString(1, folder);
            	prepare.setString(2, taskmodifIU.getText());
             	prepare.setString(3, deadlinemodifIU.getValue().toString());
        		prepare.executeUpdate();
        	
             
                
                AlertMessagePositive("Changed");
                GraphicsContext gc = IU.getGraphicsContext2D();
      	      gc.clearRect(0, 0, IU.getWidth(), IU.getHeight());
      	      
      	    GraphicsContext gcc = IBNU.getGraphicsContext2D();
  	      gcc.clearRect(0, 0, IBNU.getWidth(), IBNU.getHeight());
  	      
  	    GraphicsContext gccc = NIBU.getGraphicsContext2D();
	      gccc.clearRect(0, 0, NIBU.getWidth(), NIBU.getHeight());
	      
	      GraphicsContext gcccc = NIANU.getGraphicsContext2D();
	      gcccc.clearRect(0, 0, NIANU.getWidth(), NIANU.getHeight());
	      
                PrintGeneralView();
                ShowIUTask();
                ShowIBNUTask();
                ShowNIBUTask();
                ShowNIANUTask();
                
             }
         } catch (Exception e) {
             e.printStackTrace();
         
         }
     }
    
    public void ChangeFolderIBNU(String folder) {
    	String sql = "UPDATE tache SET folder= ? WHERE name = ? AND deadline = ?";
    
    	connect = DataBase.connectDb();
    
    	try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to change folder task ?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
            	
            	
            	prepare = connect.prepareStatement(sql);
             	prepare.setString(1, folder);
            	prepare.setString(2, taskmodifIBNU.getText());
            	prepare.setString(3, deadlinemodifIBNU.getValue().toString());
           
        		prepare.executeUpdate();
        	
                AlertMessagePositive("Changed");
                GraphicsContext gc = IU.getGraphicsContext2D();
      	      gc.clearRect(0, 0, IU.getWidth(), IU.getHeight());
      	      
      	    GraphicsContext gcc = IBNU.getGraphicsContext2D();
  	      gcc.clearRect(0, 0, IBNU.getWidth(), IBNU.getHeight());
  	      
  	    GraphicsContext gccc = NIBU.getGraphicsContext2D();
	      gccc.clearRect(0, 0, NIBU.getWidth(), NIBU.getHeight());
	      
	      GraphicsContext gcccc = NIANU.getGraphicsContext2D();
	      gcccc.clearRect(0, 0, NIANU.getWidth(), NIANU.getHeight());
	      
                PrintGeneralView();
                ShowIUTask();
                ShowIBNUTask();
                ShowNIBUTask();
                ShowNIANUTask();
             }
         } catch (Exception e) {
             e.printStackTrace();
         
         }
     }
    
    public void ChangeFolderNIBU(String folder) {
    	String sql = "UPDATE tache SET folder= ? WHERE name = ? AND deadline = ?";
    
    	connect = DataBase.connectDb();
    
    	try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to change folder task ?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
            	
            	
            	prepare = connect.prepareStatement(sql);
            	prepare.setString(1, folder);
            	prepare.setString(2, taskmodifNIBU.getText());
            	prepare.setString(3, deadlinemodifNIBU.getValue().toString());
            	
            
        		prepare.executeUpdate();
        	
                AlertMessagePositive("Changed");
                GraphicsContext gc = IU.getGraphicsContext2D();
      	      gc.clearRect(0, 0, IU.getWidth(), IU.getHeight());
      	      
      	    GraphicsContext gcc = IBNU.getGraphicsContext2D();
  	      gcc.clearRect(0, 0, IBNU.getWidth(), IBNU.getHeight());
  	      
  	    GraphicsContext gccc = NIBU.getGraphicsContext2D();
	      gccc.clearRect(0, 0, NIBU.getWidth(), NIBU.getHeight());
	      
	      GraphicsContext gcccc = NIANU.getGraphicsContext2D();
	      gcccc.clearRect(0, 0, NIANU.getWidth(), NIANU.getHeight());
	      
                PrintGeneralView();
                ShowIUTask();
                ShowIBNUTask();
                ShowNIBUTask();
                ShowNIANUTask();
             }
         } catch (Exception e) {
             e.printStackTrace();
         
         }
     }
    
    public void ChangeFolderNIANU(String folder) {
    	String sql = "UPDATE tache SET folder= ? WHERE name = ? AND deadline = ?";
    
    	connect = DataBase.connectDb();
    
    	try {
        	Alert alert;
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to change folder task ?");
            Optional<ButtonType> option = alert.showAndWait();
                        
            if (option.isPresent() && option.get() == ButtonType.OK) {
            	
            	
            	prepare = connect.prepareStatement(sql);
            	prepare.setString(1, folder);
            	prepare.setString(2, taskmodifNIANU.getText());
            	prepare.setString(3, deadlinemodifNIANU.getValue().toString());
            	
        		prepare.executeUpdate();
        	
                AlertMessagePositive("Changed");
                GraphicsContext gc = IU.getGraphicsContext2D();
      	      gc.clearRect(0, 0, IU.getWidth(), IU.getHeight());
      	      
      	    GraphicsContext gcc = IBNU.getGraphicsContext2D();
  	      gcc.clearRect(0, 0, IBNU.getWidth(), IBNU.getHeight());
  	      
  	    GraphicsContext gccc = NIBU.getGraphicsContext2D();
	      gccc.clearRect(0, 0, NIBU.getWidth(), NIBU.getHeight());
	      
	      GraphicsContext gcccc = NIANU.getGraphicsContext2D();
	      gcccc.clearRect(0, 0, NIANU.getWidth(), NIANU.getHeight());
	      
                PrintGeneralView();
                ShowIUTask();
                ShowIBNUTask();
                ShowNIBUTask();
                ShowNIANUTask();
             }
         } catch (Exception e) {
             e.printStackTrace();
         
         }
     }
 //Cette partie contient les fonctions générales       
 //___________________________________________________________________________________________   
    
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
      public void AlertMessage() {
      	Alert alert;
      	alert = new Alert(AlertType.ERROR);
          alert.setTitle("Error Message");
          alert.setHeaderText(null);
          alert.setContentText("Please fill * fields");
          alert.showAndWait();
      }
      public void AlertMessagePositive(String Message) {
      	Alert alert;
      	alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Information Message");
          alert.setHeaderText(null);
          alert.setContentText("Successfully " + Message);
          alert.showAndWait();
      }
    //Permet d'afficher la fenetre principle
      public void PrintGeneralView(){
    	  generalview.setVisible(true);

          Deletetab.setVisible(false);
          archivedtab.setVisible(false);
          helptab.setVisible(false);
      
          viewtaskofiu.setVisible(false);
          viewtaskofibnu.setVisible(false);
          viewtaskofnibu.setVisible(false);
          viewtaskofnianu.setVisible(false);
          viewsubtaskofiu.setVisible(false);
          viewsubtaskofibnu.setVisible(false);
          viewsubtaskofnibu.setVisible(false);
          viewsubtaskofnianu.setVisible(false);
         
      }
        
   //ajouter des valeurs aux combo box
      
	private String[] listDone = {"Yes", "No"};
    
    public void DoneList() {
    	
        List<String> listD= new ArrayList<>();

        for (String data : listDone) {
        	listD.add(data);
        }

        
        donemodifIU.getItems().addAll(listD);
        donemodifIBNU.getItems().addAll(listD);
        donemodifNIBU.getItems().addAll(listD);
        donemodifNIANU.getItems().addAll(listD);
        viewdonefieldiu.getItems().addAll(listD);
        viewdonefieldibnu.getItems().addAll(listD);
        viewdonefieldnibu.getItems().addAll(listD);
        viewdonefieldnianu.getItems().addAll(listD);
        }
        

    public void switchWindow(ActionEvent event) {
    	
    	if (event.getSource() == deletedbtn_main) {
    		generalview.setVisible(false);
    		Deletetab.setVisible(true);
        	archivedtab.setVisible(false);
        	helptab.setVisible(false);
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	

        	deletedbtn_main.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	
        	archivedbtn_main.setStyle("-fx-background-color:transparent");
        	morebtn.setStyle("-fx-background-color:transparent");
        	homebtn.setStyle("-fx-background-color:transparent");
        	
        	//passer à la fentre archived
        }else if (event.getSource() == archivedbtn_main) {
        	generalview.setVisible(false);
        	Deletetab.setVisible(false);
        	archivedtab.setVisible(true);
        	helptab.setVisible(false);
        	
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	
        	
        	archivedbtn_main.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	
        	deletedbtn_main.setStyle("-fx-background-color:transparent");
        	morebtn.setStyle("-fx-background-color:transparent");
        	homebtn.setStyle("-fx-background-color:transparent");

        	//passer à la fentre more
        	
        }else if (event.getSource() == morebtn) {
        	generalview.setVisible(false);
        	
        	Deletetab.setVisible(false);
        	archivedtab.setVisible(false);
        	helptab.setVisible(true);
        	
        	viewtaskofiu.setVisible(false);
        	viewtaskofibnu.setVisible(false);
        	viewtaskofnibu.setVisible(false);
        	viewtaskofnianu.setVisible(false);
        	viewsubtaskofiu.setVisible(false);
        	viewsubtaskofibnu.setVisible(false);
        	viewsubtaskofnibu.setVisible(false);
        	viewsubtaskofnianu.setVisible(false);
        	
        	
        	morebtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	homebtn.setStyle("-fx-background-color:transparent");
        	deletedbtn_main.setStyle("-fx-background-color:transparent");
        	archivedbtn_main.setStyle("-fx-background-color:transparent");

        }else if (event.getSource() == backiu || (event.getSource() == backibnu) || (event.getSource() == backnibu) ||(event.getSource() == backnianu) || (event.getSource() == homebtn)){
        	PrintGeneralView();
        	homebtn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
        	morebtn.setStyle("-fx-background-color:transparent");
        	
        	deletedbtn_main.setStyle("-fx-background-color:transparent");
        	archivedbtn_main.setStyle("-fx-background-color:transparent");
        	ClearTaskFieldIU();
        	ClearTaskFieldIBNU();
        	ClearTaskFieldNIBU();
        	ClearTaskFieldNIANU();
        	// nettoie le canvas
            GraphicsContext gc = stIU.getGraphicsContext2D();
    	    gc.clearRect(0, 0, stIU.getWidth(), stIU.getHeight());
    	    GraphicsContext gcc = stIBNU.getGraphicsContext2D();
    	    gcc.clearRect(0, 0, stIBNU.getWidth(), stIBNU.getHeight());
    	    GraphicsContext gccc = stNIBU.getGraphicsContext2D();
    	    gccc.clearRect(0, 0, stNIBU.getWidth(), stNIBU.getHeight());
    	    GraphicsContext gcccc = stNIANU.getGraphicsContext2D();
    	    gcccc.clearRect(0, 0, stNIANU.getWidth(), stNIANU.getHeight());
    	    
            
        	}else if (event.getSource() == backstIU) {
        		ViewtasksIU();
        		ShowIUstTask() ;
   	         SelectIUstTask();
            	ClearstTaskFieldIU();
        	}else if (event.getSource() == backstIBNU) {
        		ViewtasksIBNU();
        		ShowIBNUstTask() ;
   	         SelectIBNUstTask();
            	ClearstTaskFieldIBNU();}
        	else if (event.getSource() == backstNIBU) {
        		ViewtasksNIBU();
        		ShowNIBUstTask() ;
   	         SelectNIBUstTask();
            	ClearstTaskFieldNIBU();}
        	else if (event.getSource() == backstNIANU) {
        		ViewtasksNIANU();
        		ShowNIANUstTask() ;
   	         SelectNIANUstTask();
            	ClearstTaskFieldNIANU();}
    	}
    
    //fermer la fenetre accueil
    
    public void close() {
    	System.exit(0);
    }
    
  //réduire la fenetre accueil
    
    public void minimize() {
    	Stage stage= (Stage)accueilmain.getScene().getWindow();
    	stage.setIconified(true);
    }
   
 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ShowIUTask();
		ShowIBNUTask();
		ShowNIBUTask();
		ShowNIANUTask();
		ShowDeletedTask();
		ShowArchivedTask();
		
		SelectIUTask();
		SelectIBNUTask();
		SelectNIBUTask();
		SelectNIANUTask();
		
		
		
		choiceiudel.setOnAction(event -> RecovertaskDeleted("Important and Urgent"));
	    choiceibnudel.setOnAction(event -> RecovertaskDeleted("Important but not Urgent"));
	    choicenibudel.setOnAction(event -> RecovertaskDeleted("Not Important but Urgent"));
	    choicenianudel.setOnAction(event -> RecovertaskDeleted("Not Important and not Urgent"));
	    
	    choiceiuarchi.setOnAction(event -> RecovertaskArchived("Important and Urgent"));
	    choiceibnuarchi.setOnAction(event -> RecovertaskArchived("Important but not Urgent"));
	    choicenibuarchi.setOnAction(event -> RecovertaskArchived("Not Important but Urgent"));
	    choicenianuarchi.setOnAction(event -> RecovertaskArchived("Not Important and not Urgent"));
	    
	    choiceibnuIU.setOnAction(event -> ChangeFolderIU("Important but not Urgent"));
	    choicenibuIU.setOnAction(event -> ChangeFolderIU("Not Important but Urgent"));
	    choicenianuIU.setOnAction(event -> ChangeFolderIU("Not Important and not Urgent"));
	    
	    choiceiuIBNU.setOnAction(event -> ChangeFolderIBNU("Important and Urgent"));
	    choicenibuIBNU.setOnAction(event -> ChangeFolderIBNU("Not Important but Urgent"));
	    choicenianuIBNU.setOnAction(event -> ChangeFolderIBNU("Not Important and not Urgent"));
	    
	    choiceiuNIBU.setOnAction(event -> ChangeFolderNIBU("Important and Urgent"));
	    choiceibnuNIBU.setOnAction(event -> ChangeFolderNIBU("Important but not Urgent"));
	    choicenianuNIBU.setOnAction(event -> ChangeFolderNIBU("Not Important and not Urgent"));
	    
	    choiceiuNIANU.setOnAction(event -> ChangeFolderNIANU("Important and Urgent"));
	    choiceibnuNIANU.setOnAction(event -> ChangeFolderNIANU("Important but not Urgent"));
	    choicenibuNIANU.setOnAction(event -> ChangeFolderNIANU("Not Important but Urgent"));
	   
	   
		 //Pour afficher les dates préceédentes comme indisponible
		deadlinemodifIU.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); 
                        }
                    }
                };
            }
        });
		deadlinemodifIBNU.setDayCellFactory(new Callback<DatePicker, DateCell>() {
        
        public DateCell call(DatePicker param) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item.isBefore(LocalDate.now())) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;"); 
                    }
                }
            };
        }
    });
		deadlinemodifNIBU.setDayCellFactory(new Callback<DatePicker, DateCell>() {
        
        public DateCell call(DatePicker param) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item.isBefore(LocalDate.now())) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;"); 
                    }
                }
            };
        }
    });
 
		deadlinemodifNIANU.setDayCellFactory(new Callback<DatePicker, DateCell>() {
        
        public DateCell call(DatePicker param) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item.isBefore(LocalDate.now())) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;"); 
                    }
                }
            };
        }
    });
		
		viewdeadlinefieldiu.setDayCellFactory(new Callback<DatePicker, DateCell>() {
	        
	        public DateCell call(DatePicker param) {
	            return new DateCell() {
	                @Override
	                public void updateItem(LocalDate item, boolean empty) {
	                    super.updateItem(item, empty);
	                    

	                    // Vérifier si la date est avant la date actuelle ou après la date de la tâche parente
	                    if (item.isBefore(LocalDate.now()) || item.isAfter(TakeDeadlineIU())) {
	                        setDisable(true);
	                        setStyle("-fx-background-color: #ffc0cb;"); 
	                    }
	                }
	            };
	        }
	    });
		
		
		viewdeadlinefieldibnu.setDayCellFactory(new Callback<DatePicker, DateCell>() {
	        
	        public DateCell call(DatePicker param) {
	            return new DateCell() {
	                @Override
	                public void updateItem(LocalDate item, boolean empty) {
	                    super.updateItem(item, empty);
	                    if (item.isBefore(LocalDate.now()) || item.isAfter(TakeDeadlineIBNU())) {
	                        setDisable(true);
	                        setStyle("-fx-background-color: #ffc0cb;"); 
	                    }
	                }
	            };
	        }
	    });
		
		
		viewdeadlinefieldnibu.setDayCellFactory(new Callback<DatePicker, DateCell>() {
	        
	        public DateCell call(DatePicker param) {
	            return new DateCell() {
	                @Override
	                public void updateItem(LocalDate item, boolean empty) {
	                    super.updateItem(item, empty);
	                    if (item.isBefore(LocalDate.now()) || item.isAfter(TakeDeadlineNIBU())) {
	                        setDisable(true);
	                        setStyle("-fx-background-color: #ffc0cb;"); 
	                    }
	                }
	            };
	        }
	    });
		
		
		viewdeadlinefieldnianu.setDayCellFactory(new Callback<DatePicker, DateCell>() {
	        
	        public DateCell call(DatePicker param) {
	            return new DateCell() {
	                @Override
	                public void updateItem(LocalDate item, boolean empty) {
	                    super.updateItem(item, empty);
	                    if (item.isBefore(LocalDate.now()) || item.isAfter(TakeDeadlineNIANU())) {
	                        setDisable(true);
	                        setStyle("-fx-background-color: #ffc0cb;"); 
	                    }
	                }
	            };
	        }
	    });
	
	
	}
  	
	
	}





