/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vistas;

import java.io.IOException;
import java.net.URL;
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
            new Registro("13/08/2024", 72, 97),
            new Registro("14/08/2024", 68, 98),
            new Registro("15/08/2024", 75, 96),
            new Registro("16/08/2024", 80, 95),
            new Registro("17/08/2024", 92, 94),
            new Registro("18/08/2024", 70, 97),
            new Registro("19/08/2024", 65, 98)
        );

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
        // Cargar el archivo FXML de la nueva interfaz
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/Principal.fxml"));
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
