/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vistas;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelos.Registro;
import modelos.Usuario;

/**
 * FXML Controller class
 *
 * @author Samue
 */
public class HistorialController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Usuario usuario;
    @FXML
    private Label textoNombre;
    @FXML
    private Label textoEdad;
    @FXML
    private Label TextoPeso;
    @FXML
    private Label TextoSexo;

    @FXML
    private Button botonCerrar;
    @FXML
    private Button botonMinimizar;
    @FXML
    private Button botonRegresar;

    //TABLA
    @FXML
    private TableView<Registro> tableView;
    @FXML
    private TableColumn<Registro, String> fechaColumn;
    @FXML
    private TableColumn<Registro, Integer> ritmoCardiacoColumn;
    @FXML
    private TableColumn<Registro, Integer> oxigenacionColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Enlazar las columnas con las propiedades del modelo
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        fechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ritmoCardiacoColumn.setCellValueFactory(new PropertyValueFactory<>("ritmoCardiaco"));
        oxigenacionColumn.setCellValueFactory(new PropertyValueFactory<>("nivelOxigenacion"));

        // Crear una lista de datos de ejemplo (puedes obtenerlos de una base de datos o de otro origen)
        ObservableList<Registro> data = FXCollections.observableArrayList(
                new Registro(Calendar.getInstance().get(Calendar.YEAR) + "/" + Calendar.getInstance().get(Calendar.MONTH) + "/"
                        + Calendar.getInstance().get(Calendar.DATE), (int) (58 + Math.random() * (70 - 58)), (int) (95 + Math.random() * (97 - 95))));

        // Añadir los datos al TableView
        tableView.setItems(data);

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
    public void regresar(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/Principal.fxml"));
        Parent root = loader.load();  // Aquí se carga la vista

// Ahora puedes obtener el controlador
        PrincipalController controller = loader.getController();
        controller.setUsuario(usuario);

// Cambia de escena
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
            textoNombre.setText(usuario.getNombre());
            TextoPeso.setText(usuario.getPeso() + " Kg");
            textoEdad.setText(usuario.getEdad() + " años");
            TextoSexo.setText(usuario.getSexo());
        }
    }

}
