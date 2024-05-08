package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import db.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.DateCell;
import javafx.util.Callback;
import java.time.LocalDate;


public class AddSubTaskController implements Initializable  {
	//Composant st IU
	
	@FXML
    private AnchorPane addsubtaskiu;
	
	@FXML
	private TextField subtaskiu;
	
	@FXML
    private TextField subdescriptioniu;
	
	@FXML
    private DatePicker subdateiu;
	
	@FXML
	private TextField subtimeiu;
	
	@FXML
    private Slider subrateimpiu;
	
	@FXML
    private Slider subrateurgiu;
	
	@FXML
    private Button subaddtaskbtniu;
	
	@FXML
	private Button subaddcanceliu;
	
	//Composant st IBNU
	
	 @FXML
	 private AnchorPane addsubtaskibnu;
	 
	 @FXML
	 private TextField subtaskibnu;
	 
	 @FXML
	 private TextField subdescriptionibnu;
	 
	 @FXML
	 private DatePicker subdateibnu;
	 
	 @FXML
	 private TextField subtimeibnu;
	 
	 @FXML
	 private Slider subrateimpibnu;
	 
	 @FXML
	 private Slider subrateurgibnu;
	 
	 @FXML
	 private Button subaddtaskbtnibnu;
	 
	 @FXML
	    private Button subaddcancelibnu;
	//Composant st NIBU
	 
	 @FXML
	 private AnchorPane addsubtasknibu;
	 
	 @FXML
	 private TextField subtasknibu;
	 
	 @FXML
	 private TextField subdescriptionnibu;
	 
	 @FXML
	 private DatePicker subdatenibu;
	 
	 @FXML
	 private TextField subtimenibu;
	 
	 @FXML
	 private Slider subrateimpnibu;
	 
	 @FXML
	 private Slider subrateurgnibu;
	 
	 @FXML
	 private Button subaddtaskbtnnibu;
	 
	 @FXML
	 private Button subaddcancelnibu;
	 
	//Composant st NIANU

    @FXML
    private AnchorPane addsubtasknianu;
    
    @FXML
    private TextField subtasknianu;
    
    @FXML
    private TextField subdescriptionnianu;
    
    @FXML
    private DatePicker subdatenianu;
    
    @FXML
    private TextField subtimenianu;
    
    @FXML
    private Slider subrateimpnianu;
    
    @FXML
    private Slider subrateurgnianu;
    
    @FXML
    private Button subaddtaskbtnnianu;

    @FXML
    private Button subaddcancelnianu;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private AccueilController accueilController;

    public void init(AccueilController accueilController) {
        this.accueilController = accueilController;
    }
    // Méthode publique pour modifier la visibilité des éléments
    public void configureElementsVisibility(boolean iu, boolean ibnu, boolean nibu, boolean nianu) {
    	addsubtaskiu.setVisible(iu);
        addsubtaskibnu.setVisible(ibnu);
        addsubtasknibu.setVisible(nibu);
        addsubtasknianu.setVisible(nianu);
    }

    //Ajouter une sous tache IU
    
  public void addSubTaskIU(ActionEvent event) {
    	
        String sql = "INSERT INTO soustache (name_st, description_st, deadline_st, time_st, done_st, rateimp_st, rateurg_st, id_t) VALUES (?, ?, ?, ?, 'No', ?, ?, ?)";

        connect = DataBase.connectDb();
        try {
            
            Alert alert;
            if (subtaskiu.getText().isEmpty() || subdateiu.getValue() == null || subtimeiu.getText().isEmpty()) {
            	accueilController.AlertMessage();
            } else {
                String check = "SELECT name_st FROM soustache WHERE name_st = ? AND id_t= ? ";
                prepare = connect.prepareStatement(check);
                prepare.setString(1, subtaskiu.getText());
                prepare.setInt(2, accueilController.TakeIDTaskIU());
                
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("SubTask: " + subtaskiu.getText() + " already exists!");
                    alert.showAndWait();
                } else {
                	// Convertir les valeurs Double en int pour rateimpiu et rateurgiu
                    int subrateimpiuValue = (int) subrateimpiu.getValue();
                    int subrateurgiuValue = (int) subrateurgiu.getValue();
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, subtaskiu.getText());
                    prepare.setString(2, subdescriptioniu.getText());
                    prepare.setString(3, subdateiu.getValue().toString());
                    prepare.setString(4, subtimeiu.getText());
                    prepare.setInt(5, subrateimpiuValue);
                    prepare.setInt(6, subrateurgiuValue);
                    prepare.setInt(7, accueilController.TakeIDTaskIU());
                    prepare.executeUpdate();

                    accueilController.AlertMessagePositive("added");
                    
                    Scene scene = ((Node) event.getSource()).getScene();
                    
                    // Fermer la fenêtre actuelle (AddSubTask)
                    Stage stage = (Stage) scene.getWindow();
                    stage.close();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/Accueil.fxml"));
                    Parent root = loader.load();

                    // Nouvelle scène
                    stage.setScene(new Scene(root));
                    stage.show();
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  public void addSubTaskIBNU(ActionEvent event) {
  	
      String sql = "INSERT INTO soustache (name_st, description_st, deadline_st, time_st, done_st, rateimp_st, rateurg_st, id_t) VALUES (?, ?, ?, ?, 'No', ?, ?, ?)";

      connect = DataBase.connectDb();
      try {
          
          Alert alert;
          if (subtaskibnu.getText().isEmpty() || subdateibnu.getValue() == null || subtimeibnu.getText().isEmpty()) {
        	  accueilController.AlertMessage();
          } else {
              String check = "SELECT name_st FROM soustache WHERE name_st = ? AND id_t= ? ";
              prepare = connect.prepareStatement(check);
              prepare.setString(1, subtaskibnu.getText());
              prepare.setInt(2, accueilController.TakeIDTaskIBNU());
              
              result = prepare.executeQuery();

              if (result.next()) {
                  alert = new Alert(AlertType.ERROR);
                  alert.setTitle("Error Message");
                  alert.setHeaderText(null);
                  alert.setContentText("SubTask: " + subtaskibnu.getText() + " already exists!");
                  alert.showAndWait();
              } else {
              	// Convertir les valeurs Double en int pour rateimpiu et rateurgiu
                  int subrateimpibnuValue = (int) subrateimpibnu.getValue();
                  int subrateurgibnuValue = (int) subrateurgibnu.getValue();
                  prepare = connect.prepareStatement(sql);
                  prepare.setString(1, subtaskibnu.getText());
                  prepare.setString(2, subdescriptionibnu.getText());
                  prepare.setString(3, subdateibnu.getValue().toString());
                  prepare.setString(4, subtimeibnu.getText());
                  prepare.setInt(5, subrateimpibnuValue);
                  prepare.setInt(6, subrateurgibnuValue);
                  prepare.setInt(7, accueilController.TakeIDTaskIBNU());
                  prepare.executeUpdate();

                  accueilController.AlertMessagePositive("added");
                  
                  Scene scene = ((Node) event.getSource()).getScene();

                  // Fermer la fenêtre actuelle (AddSubTask)
                  Stage stage = (Stage) scene.getWindow();
                  stage.close();

                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/Accueil.fxml"));
                  Parent root = loader.load();
                  // Nouvelle scène
                  stage.setScene(new Scene(root));
                  stage.show();
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  public void addSubTaskNIBU(ActionEvent event) {
  	
      String sql = "INSERT INTO soustache (name_st, description_st, deadline_st, time_st, done_st, rateimp_st, rateurg_st, id_t) VALUES (?, ?, ?, ?, 'No', ?, ?, ?)";

      connect = DataBase.connectDb();
      try {
          
          Alert alert;
          if (subtasknibu.getText().isEmpty() || subdatenibu.getValue() == null || subtimenibu.getText().isEmpty()) {
        	  accueilController.AlertMessage();
          } else {
              String check = "SELECT name_st FROM soustache WHERE name_st = ? AND id_t= ? ";
              prepare = connect.prepareStatement(check);
              prepare.setString(1, subtasknibu.getText());
              prepare.setInt(2, accueilController.TakeIDTaskNIBU());
              
              result = prepare.executeQuery();

              if (result.next()) {
                  alert = new Alert(AlertType.ERROR);
                  alert.setTitle("Error Message");
                  alert.setHeaderText(null);
                  alert.setContentText("SubTask: " + subtasknibu.getText() + " already exists!");
                  alert.showAndWait();
              } else {
              	// Convertir les valeurs Double en int pour rateimpiu et rateurgiu
                  int subrateimpnibuValue = (int) subrateimpnibu.getValue();
                  int subrateurgnibuValue = (int) subrateurgnibu.getValue();
                  prepare = connect.prepareStatement(sql);
                  prepare.setString(1, subtasknibu.getText());
                  prepare.setString(2, subdescriptionnibu.getText());
                  prepare.setString(3, subdatenibu.getValue().toString());
                  prepare.setString(4, subtimenibu.getText());
                  prepare.setInt(5, subrateimpnibuValue);
                  prepare.setInt(6, subrateurgnibuValue);
                  prepare.setInt(7, accueilController.TakeIDTaskNIBU());
                  prepare.executeUpdate();

                  accueilController.AlertMessagePositive("added");
                  
                  Scene scene = ((Node) event.getSource()).getScene();

                  // Fermer la fenêtre actuelle (AddSubTask)
                  Stage stage = (Stage) scene.getWindow();
                  stage.close();

                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/Accueil.fxml"));
                  Parent root = loader.load();

                  // Nouvelle scène
                  stage.setScene(new Scene(root));
                  stage.show();
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
  public void addSubTaskNIANU(ActionEvent event) {
  	
      String sql = "INSERT INTO soustache (name_st, description_st, deadline_st, time_st, done_st, rateimp_st, rateurg_st, id_t) VALUES (?, ?, ?, ?, 'No', ?, ?, ?)";

      connect = DataBase.connectDb();
      try {
          
          Alert alert;
          if (subtasknianu.getText().isEmpty() || subdatenianu.getValue() == null || subtimenianu.getText().isEmpty()) {
              alert = new Alert(AlertType.ERROR);
              alert.setTitle("Error Message");
              alert.setHeaderText(null);
              alert.setContentText("Please fill all * fields");
              alert.showAndWait();
          } else {
              String check = "SELECT name_st FROM soustache WHERE name_st = ? AND id_t= ? ";
              prepare = connect.prepareStatement(check);
              prepare.setString(1, subtasknianu.getText());
              prepare.setInt(2, accueilController.TakeIDTaskNIANU());
              
              result = prepare.executeQuery();

              if (result.next()) {
            	  accueilController.AlertMessage();
              } else {
              	// Convertir les valeurs Double en int pour rateimpiu et rateurgiu
                  int subrateimpnianuValue = (int) subrateimpnianu.getValue();
                  int subrateurgnianuValue = (int) subrateurgnianu.getValue();
                  prepare = connect.prepareStatement(sql);
                  prepare.setString(1, subtasknianu.getText());
                  prepare.setString(2, subdescriptionnianu.getText());
                  prepare.setString(3, subdatenianu.getValue().toString());
                  prepare.setString(4, subtimenianu.getText());
                  prepare.setInt(5, subrateimpnianuValue);
                  prepare.setInt(6, subrateurgnianuValue);
                  prepare.setInt(7, accueilController.TakeIDTaskNIANU());
                  prepare.executeUpdate();

                  accueilController.AlertMessagePositive("added");
                  
                  Scene scene = ((Node) event.getSource()).getScene();

                  // Fermer la fenêtre actuelle (AddSubTask)
                  Stage stage = (Stage) scene.getWindow();
                  stage.close();

                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/Accueil.fxml"));
                  Parent root = loader.load();

                  // Nouvelle scène
                  stage.setScene(new Scene(root));
                  stage.show();
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  
  //Abandon
  public void subcancelIU() {
    	Stage stage = (Stage) subaddcanceliu.getScene().getWindow();
        stage.close();
    }
  public void subcancelIBNU() {
  	Stage stage = (Stage) subaddcancelibnu.getScene().getWindow();
      stage.close();
  }
  public void subcancelNIBU() {
  	Stage stage = (Stage) subaddcancelnibu.getScene().getWindow();
      stage.close();
  }
  public void subcancelNIANU() {
  	Stage stage = (Stage) subaddcancelnianu.getScene().getWindow();
      stage.close();
  }
  
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	//Pour afficher les dates préceédentes comme indisponible
        subdateiu.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                     // Récupérer la date de la tâche parente depuis l'accueilController
                        LocalDate parentTaskDeadline = accueilController.TakeDeadlineIU();

                        // Vérifier si la date est avant la date actuelle ou après la date de la tâche parente
                        if (item.isBefore(LocalDate.now()) || item.isAfter(parentTaskDeadline)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); 
                        }
                    }
                };
            }
        });
    
    
  
    subdateibnu.setDayCellFactory(new Callback<DatePicker, DateCell>() {
        
        public DateCell call(DatePicker param) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    LocalDate parentTaskDeadline = accueilController.TakeDeadlineIBNU();

                    // Vérifier si la date est avant la date actuelle ou après la date de la tâche parente
                    if (item.isBefore(LocalDate.now()) || item.isAfter(parentTaskDeadline)) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;"); 
                    }
                }
            };
        }
    });
    
    
    subdatenibu.setDayCellFactory(new Callback<DatePicker, DateCell>() {
        
        public DateCell call(DatePicker param) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    LocalDate parentTaskDeadline = accueilController.TakeDeadlineNIBU();

                    // Vérifier si la date est avant la date actuelle ou après la date de la tâche parente
                    if (item.isBefore(LocalDate.now()) || item.isAfter(parentTaskDeadline)) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;"); 
                    }
                }
            };
        }
    });
   
    
subdatenianu.setDayCellFactory(new Callback<DatePicker, DateCell>() {
        
        public DateCell call(DatePicker param) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    LocalDate parentTaskDeadline = accueilController.TakeDeadlineNIANU();

                    // Vérifier si la date est avant la date actuelle ou après la date de la tâche parente
                    if (item.isBefore(LocalDate.now()) || item.isAfter(parentTaskDeadline)) {
                        setDisable(true);
                        setStyle("-fx-background-color: #ffc0cb;"); 
                    }
                }
            };
        }
    });}
    



}
