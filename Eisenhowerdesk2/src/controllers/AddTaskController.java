package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import db.DataBase;
import javafx.scene.control.DateCell;
import javafx.util.Callback;




public class AddTaskController implements Initializable  {
	
    //Composant of IU
    @FXML
    private AnchorPane addtaskiu;
    
    @FXML
    private TextField taskiu;
    
    @FXML
    private TextField descriptioniu;
    
    @FXML
    private DatePicker dateiu;
    
    @FXML
    private TextField timeiu;
    
    @FXML
    private Slider rateimpiu;
    
    @FXML
    private Slider rateurgiu;
    
    @FXML
    private Button addtaskbtniu;
    
    @FXML
    private Button addcanceliu;
//Composant of IBnu
    @FXML
    private AnchorPane addtaskibnu;
    
    @FXML
    private TextField taskibnu;
    
    @FXML
    private TextField descriptionibnu;
    
    @FXML
    private DatePicker dateibnu;
    
    @FXML
    private TextField timeibnu;
    
    @FXML
    private Slider rateimpibnu;
    
    @FXML
    private Slider rateurgibnu;
    
    @FXML
    private Button addtaskbtnibnu;
    
    @FXML
    private Button addcancelibnu;
  //Composant of NIBU
    @FXML
    private AnchorPane addtasknibu;
    
    @FXML
    private TextField tasknibu;
    
    @FXML
    private TextField descriptionnibu;
    
    @FXML
    private DatePicker datenibu;
    
    @FXML
    private TextField timenibu;
    
    @FXML
    private Slider rateimpnibu;
    
    @FXML
    private Slider rateurgnibu;
    
    @FXML
    private Button addtaskbtnnibu;
    
    @FXML
    private Button addcancelnibu;

  //Composant of NIANU
    @FXML
    private AnchorPane addtasknianu;
    
    @FXML
    private TextField tasknianu;
    
    @FXML
    private TextField descriptionnianu;
    
    @FXML
    private DatePicker datenianu;
    
    @FXML
    private TextField timenianu;
    
    @FXML
    private Slider rateimpnianu;
    
    @FXML
    private Slider rateurgnianu;
    
    @FXML
    private Button addcancelnianu;
    
    @FXML
    private Button addtaskbtnnianu;

    
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
       
 // Méthode publique pour modifier la visibilité des éléments
    public void configureElementsVisibility(boolean iu, boolean ibnu, boolean nibu, boolean nianu) {
        addtaskiu.setVisible(iu);
        addtaskibnu.setVisible(ibnu);
        addtasknibu.setVisible(nibu);
        addtasknianu.setVisible(nianu);
    }

    
  public void addTaskIU(ActionEvent event) {
        String sql = "INSERT INTO tache (name, description, deadline, time, done, rateimp, rateurg, folder) VALUES (?, ?, ?, ?, 'No', ?, ?, 'Important and Urgent')";

        connect = DataBase.connectDb();

        try {
            Alert alert;
            if (taskiu.getText().isEmpty() || dateiu.getValue() == null || timeiu.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all * fields");
                alert.showAndWait();
            } else {
                String check = "SELECT name FROM tache WHERE name = '" + taskiu.getText() + "' and folder='Important and Urgent'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Task: " + taskiu.getText() + " already exists!");
                    alert.showAndWait();
                } else {
                    // Convertir les valeurs Double en int pour rateimpiu et rateurgiu
                    int rateimpiuValue = (int) rateimpiu.getValue();
                    int rateurgiuValue = (int) rateurgiu.getValue();
                    
                    // Préparer la requête avec les valeurs correctes
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, taskiu.getText());
                    prepare.setString(2, descriptioniu.getText());
                    prepare.setString(3, dateiu.getValue().toString());
                    prepare.setString(4, timeiu.getText());
                    prepare.setInt(5, rateimpiuValue);
                    prepare.setInt(6, rateurgiuValue);
                    
                    // Exécuter la mise à jour de la base de données
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added!");
                    alert.showAndWait();

                    Scene scene = ((Node) event.getSource()).getScene();

                    // Fermer la fenêtre actuelle (AddTask)
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


  public void addTaskIBNU(ActionEvent event) {
      String sql = "INSERT INTO tache (name, description, deadline, time, done, rateimp, rateurg, folder) VALUES (?, ?, ?, ?, 'No', ?, ?, 'Important but not Urgent')";

      connect = DataBase.connectDb();

      try {
          Alert alert;
          if (taskibnu.getText().isEmpty() || dateibnu.getValue() == null || timeibnu.getText().isEmpty()) {
              alert = new Alert(AlertType.ERROR);
              alert.setTitle("Error Message");
              alert.setHeaderText(null);
              alert.setContentText("Please fill all * fields");
              alert.showAndWait();
          } else {
              String check = "SELECT name FROM tache WHERE name = '" + taskibnu.getText() + "' and folder='Important but not Urgent'";

              statement = connect.createStatement();
              result = statement.executeQuery(check);

              if (result.next()) {
                  alert = new Alert(AlertType.ERROR);
                  alert.setTitle("Error Message");
                  alert.setHeaderText(null);
                  alert.setContentText("Task: " + taskibnu.getText() + " already exists!");
                  alert.showAndWait();
              } else {
                  // Convertir les valeurs Double en int pour rateimpiu et rateurgiu
                  int rateimpibnuValue = (int) rateimpibnu.getValue();
                  int rateurgibnuValue = (int) rateurgibnu.getValue();
                  
                  // Préparer la requête avec les valeurs correctes
                  prepare = connect.prepareStatement(sql);
                  prepare.setString(1, taskibnu.getText());
                  prepare.setString(2, descriptionibnu.getText());
                  prepare.setString(3, dateibnu.getValue().toString());
                  prepare.setString(4, timeibnu.getText());
                  prepare.setInt(5, rateimpibnuValue);
                  prepare.setInt(6, rateurgibnuValue);
                  
                  // Exécuter la mise à jour de la base de données
                  prepare.executeUpdate();

                  alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Message");
                  alert.setHeaderText(null);
                  alert.setContentText("Successfully added!");
                  alert.showAndWait();

                  Scene scene = ((Node) event.getSource()).getScene();

                  // Fermer la fenêtre actuelle (AddTask)
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

  public void addTaskNIBU(ActionEvent event) {
      String sql = "INSERT INTO tache (name, description, deadline, time, done, rateimp, rateurg, folder) VALUES (?, ?, ?, ?, 'No', ?, ?, 'Not Important but Urgent')";

      connect = DataBase.connectDb();

      try {
          Alert alert;
          if (tasknibu.getText().isEmpty() || datenibu.getValue() == null || timenibu.getText().isEmpty()) {
              alert = new Alert(AlertType.ERROR);
              alert.setTitle("Error Message");
              alert.setHeaderText(null);
              alert.setContentText("Please fill all * fields");
              alert.showAndWait();
          } else {
              String check = "SELECT name FROM tache WHERE name = '" + tasknibu.getText() + "' and folder='Not Important but Urgent'";

              statement = connect.createStatement();
              result = statement.executeQuery(check);

              if (result.next()) {
                  alert = new Alert(AlertType.ERROR);
                  alert.setTitle("Error Message");
                  alert.setHeaderText(null);
                  alert.setContentText("Task: " + tasknibu.getText() + " already exists!");
                  alert.showAndWait();
              } else {
                  // Convertir les valeurs Double en int pour rateimpiu et rateurgiu
                  int rateimpnibuValue = (int) rateimpnibu.getValue();
                  int rateurgnibuValue = (int) rateurgnibu.getValue();
                  
                  // Préparer la requête avec les valeurs correctes
                  prepare = connect.prepareStatement(sql);
                  prepare.setString(1, tasknibu.getText());
                  prepare.setString(2, descriptionnibu.getText());
                  prepare.setString(3, datenibu.getValue().toString());
                  prepare.setString(4, timenibu.getText());
                  prepare.setInt(5, rateimpnibuValue);
                  prepare.setInt(6, rateurgnibuValue);
                  
                  // Exécuter la mise à jour de la base de données
                  prepare.executeUpdate();

                  alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Message");
                  alert.setHeaderText(null);
                  alert.setContentText("Successfully added!");
                  alert.showAndWait();

                  Scene scene = ((Node) event.getSource()).getScene();

                  // Fermer la fenêtre actuelle (AddTask)
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

  
  public void addTaskNIANU(ActionEvent event) {
      String sql = "INSERT INTO tache (name, description, deadline, time, done, rateimp, rateurg, folder) VALUES (?, ?, ?, ?, 'No', ?, ?, 'Not Important and not Urgent')";

      connect = DataBase.connectDb();

      try {
          Alert alert;
          if (tasknianu.getText().isEmpty() || datenianu.getValue() == null || timenianu.getText().isEmpty()) {
              alert = new Alert(AlertType.ERROR);
              alert.setTitle("Error Message");
              alert.setHeaderText(null);
              alert.setContentText("Please fill all * fields");
              alert.showAndWait();
          } else {
              String check = "SELECT name FROM tache WHERE name = '" + tasknianu.getText() + "' and folder='Important and Urgent'";

              statement = connect.createStatement();
              result = statement.executeQuery(check);

              if (result.next()) {
                  alert = new Alert(AlertType.ERROR);
                  alert.setTitle("Error Message");
                  alert.setHeaderText(null);
                  alert.setContentText("Task: " + tasknianu.getText() + " already exists!");
                  alert.showAndWait();
              } else {
                  // Convertir les valeurs Double en int pour rateimpiu et rateurgiu
                  int rateimpnianuValue = (int) rateimpnianu.getValue();
                  int rateurgnianuValue = (int) rateurgnianu.getValue();
                  
                  // Préparer la requête avec les valeurs correctes
                  prepare = connect.prepareStatement(sql);
                  prepare.setString(1, tasknianu.getText());
                  prepare.setString(2, descriptionnianu.getText());
                  prepare.setString(3, datenianu.getValue().toString());
                  prepare.setString(4, timenianu.getText());
                  prepare.setInt(5, rateimpnianuValue);
                  prepare.setInt(6, rateurgnianuValue);
                  
                  // Exécuter la mise à jour de la base de données
                  prepare.executeUpdate();

                  alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Information Message");
                  alert.setHeaderText(null);
                  alert.setContentText("Successfully added!");
                  alert.showAndWait();

                  Scene scene = ((Node) event.getSource()).getScene();

                  // Fermer la fenêtre actuelle (AddTask)
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


   //Abandonner l'ajout 	
    public void cancelIU() {
    	Stage stage = (Stage) addcanceliu.getScene().getWindow();
        stage.close();
    }

    
    public void cancelIBNU() {
    	Stage stage = (Stage) addcancelibnu.getScene().getWindow();
        stage.close();
    }
    
    public void cancelNIBU() {
    	Stage stage = (Stage) addcancelnibu.getScene().getWindow();
        stage.close();
    }
    
    public void cancelNIANU() {
    	Stage stage = (Stage) addcancelnianu.getScene().getWindow();
        stage.close();
    }
   
    
   //
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //Pour afficher les dates préceédentes comme indisponible
        dateiu.setDayCellFactory(new Callback<DatePicker, DateCell>() {
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
    
    
  
    dateibnu.setDayCellFactory(new Callback<DatePicker, DateCell>() {
        
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
    
    
    datenibu.setDayCellFactory(new Callback<DatePicker, DateCell>() {
        
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
    
    
    
    
    
datenianu.setDayCellFactory(new Callback<DatePicker, DateCell>() {
        
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
    });}
    



}






