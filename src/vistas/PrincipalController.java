/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vistas;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelos.Usuario;
import vistas.HistorialController;
import vistas.SignosController;

/**
 * FXML Controller class
 *
 * @author Samue
 */
public class PrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //DATOS DEL PACIENTE
    
    private Usuario usuario;

    @FXML
    private Pane botonSignos;
    @FXML
    private Pane botonEstadisticas;
    @FXML
    private Button botonMinimizar;
    @FXML
    private Button botonCerrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        botonEstadisticas.setOnMouseClicked(event -> {
            try {
                estadiscticas(event);
            } catch (IOException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        botonSignos.setOnMouseClicked(event -> {
            try {
                signos(event);
            } catch (IOException ex) {
                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    public void minimizarVentana(ActionEvent event) {
        ((Stage) botonMinimizar.getScene().getWindow()).setIconified(true);
    }

    @FXML
    public void cerrarVentana(ActionEvent event) throws IOException {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Sesión");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Quieres cerrar sesión?");

        if (alerta.showAndWait().get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("/vistas/InicioSesion.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    public void signos(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Signos.fxml"));
        Parent root = loader.load();
        SignosController controller = loader.getController();
        controller.setUsuario(usuario);

        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void estadiscticas(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Historial.fxml"));
        Parent root = loader.load();
        HistorialController controller = loader.getController();
        controller.setUsuario(usuario);
        
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        actualizarVistaConUsuario(); 
    }
    
    private void actualizarVistaConUsuario() {
        if (usuario != null) {
            // Aquí puedes acceder a usuario.getNombre() sin problema
            // Por ejemplo, actualizando un Label:
            System.out.println(usuario.getNombre());
        }
    }

}
