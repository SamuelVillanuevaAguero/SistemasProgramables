/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vistas;

import controladores.UsuarioControlador;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelos.Usuario;

/**
 * FXML Controller class
 *
 * @author Samue
 */
public class InicioSesionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button botonCerrar;
    @FXML
    private Button botonMinimizar;
    @FXML
    private ImageView botonVerPass;
    @FXML
    private TextField entradaUsuario;
    @FXML
    private PasswordField entradaContrasena;
    @FXML
    private TextField entradaAuxiliar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        entradaAuxiliar.setVisible(false);

        botonVerPass.setOnMousePressed(event -> {
            verContraseñaMousePressed(event);
        });

        botonVerPass.setOnMouseReleased(event -> {
            verContraseñaMouseReleased(event);
        });

    }

    @FXML
    public void verContraseñaMousePressed(MouseEvent event) {
        entradaAuxiliar.setText(entradaContrasena.getText());
        entradaContrasena.setVisible(false);
        entradaAuxiliar.setVisible(true);
    }

    @FXML
    public void verContraseñaMouseReleased(MouseEvent event) {
        entradaContrasena.setVisible(true);
        entradaAuxiliar.setVisible(false);
    }

    @FXML
    public void minimizarVentana(ActionEvent event) {
        ((Stage) botonMinimizar.getScene().getWindow()).setIconified(true);
    }

    @FXML
    public void cerrarVentana(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Confirmación");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Quieres cerrar la aplicación?");

        if (alerta.showAndWait().get() == ButtonType.OK) {
            ((Stage) botonCerrar.getScene().getWindow()).close();
        }

    }

    @FXML
    public void iniciarSesion(ActionEvent event) throws IOException {
        String usuario = entradaUsuario.getText();
        String contraseña = entradaContrasena.getText();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Cuidado");
            alerta.setHeaderText(null);
            alerta.setContentText("Llena todos los campos");
            alerta.showAndWait();
            return;
        }

        if (UsuarioControlador.iniciarSesion(usuario, contraseña)) {
            Usuario u = UsuarioControlador.getUsuario(usuario, contraseña);

// Carga el archivo FXML primero
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Principal.fxml"));
            Parent root = loader.load();  // Aquí se carga la vista

// Ahora puedes obtener el controlador
            PrincipalController controller = loader.getController();
            controller.setUsuario(u);

// Cambia de escena
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("inicio de sesión");
            alerta.setHeaderText("Usuario y/o contraseña incorrectas");
            alerta.showAndWait();
        }

    }

    @FXML
    private void registrar(ActionEvent event) throws IOException {
        // Cargar el archivo FXML de la nueva interfaz
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/Registro.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
