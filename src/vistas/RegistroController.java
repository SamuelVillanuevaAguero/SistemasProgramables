/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelos.Usuario;

/**
 * FXML Controller class
 *
 * @author Samue
 */
public class RegistroController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button botonMinimizar;
    @FXML
    private Button botonCerrar;
    @FXML
    private Button botonRegresar;
    @FXML
    private Label BotonRegresar2;
    @FXML
    private Label textoContraseña;
    @FXML
    private TextField entradaVerContraseña;
    @FXML
    private TextField entradaVerContraseña2;
    @FXML
    private ImageView botonVerContraseña;
    @FXML
    private ImageView botonVerContraseña2;

    //Entradas
    @FXML
    private TextField entradaNombre;
    @FXML
    private TextField entradaUsuario;
    @FXML
    private PasswordField entradaContraseña;
    @FXML
    private PasswordField entradaConfirmacion;
    @FXML
    private TextField entradaCorreo;
    @FXML
    private ComboBox entradaSexo;
    @FXML
    private Spinner<Integer> entradaEdad;
    @FXML
    private Spinner<Double> entradaPeso;

    @Override
    public void initialize(URL url, ResourceBundle rb) throws IndexOutOfBoundsException {
        // TODO
        entradaSexo.getItems().addAll("Masculino", "Femenino");
        entradaSexo.setValue("Masculino");

        SpinnerValueFactory<Integer> controlSpinnerEdad = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
        entradaEdad.setValueFactory(controlSpinnerEdad);
        entradaEdad.setEditable(true);

        SpinnerValueFactory<Double> controlSpinnerPeso = new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 0.1);
        entradaPeso.setValueFactory(controlSpinnerPeso);
        entradaPeso.setEditable(true);

        UnaryOperator<TextFormatter.Change> filter = null;
        filter = change -> {
            String newText = change.getControlNewText();

            // Verificar si la entrada es un número válido
            if (newText.matches("[0-9]*")) {
                return change; // Permitir solo números enteros
            } else if (newText.matches("[0-9]+\\.[0-9]*")) {
                // Si el texto contiene decimales, eliminar la parte decimal
                String integerPart = newText.split("\\.")[0];
                change.setText(integerPart); // Reemplaza con la parte entera
                change.setRange(0, newText.length()); // Ajusta el rango del cambio
                return change;
            }
            return null; // Rechaza cualquier otro cambio
        };

        // Crear y aplicar el TextFormatter al editor del Spinner
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        entradaEdad.getEditor().setTextFormatter(textFormatter);

        BotonRegresar2.setOnMouseClicked(event -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/vistas/InicioSesion.fxml"));
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(RegistroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        entradaContraseña.setOnKeyReleased(event -> {
            if (entradaContraseña.getText().equals(entradaConfirmacion.getText())) {
                textoContraseña.setText("");
            } else {
                textoContraseña.setText("No coinciden");
            }
        });

        entradaConfirmacion.setOnKeyReleased(event -> {
            if (entradaContraseña.getText().equals(entradaConfirmacion.getText())) {
                textoContraseña.setText("");
            } else {
                textoContraseña.setText("No coinciden");
            }
        });

        botonVerContraseña.setOnMousePressed(event -> {
            entradaVerContraseña.setText(entradaContraseña.getText());
            entradaContraseña.setVisible(false);
            entradaVerContraseña.setVisible(true);
        });

        botonVerContraseña.setOnMouseReleased(event -> {
            entradaContraseña.setVisible(true);
            entradaVerContraseña.setVisible(false);
        });

        botonVerContraseña2.setOnMousePressed(event -> {
            entradaVerContraseña2.setText(entradaConfirmacion.getText());
            entradaConfirmacion.setVisible(false);
            entradaVerContraseña2.setVisible(true);
        });

        botonVerContraseña2.setOnMouseReleased(event -> {
            entradaConfirmacion.setVisible(true);
            entradaVerContraseña2.setVisible(false);
        });
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
        alerta.setContentText("¿Quieres cerrar?");

        if (alerta.showAndWait().get() == ButtonType.OK) {
            ((Stage) botonCerrar.getScene().getWindow()).close();
        }

    }

    @FXML
    public void regresar(ActionEvent event) throws IOException {
        // Cargar el archivo FXML de la nueva interfaz
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/InicioSesion.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void registrar() {
        String nombre, alias, contraseña, correo, sexo;
        int edad;
        double peso;

        nombre = entradaNombre.getText();
        alias = entradaUsuario.getText();
        contraseña = entradaContraseña.getText();
        correo = entradaCorreo.getText();
        entradaEdad.increment(0);
        entradaPeso.increment(0);
        edad = entradaEdad.getValue();
        peso = entradaPeso.getValue();
        sexo = entradaSexo.getValue().toString();

        Usuario usuario = new Usuario(nombre, alias, contraseña, correo, edad, peso, sexo);

        if (contraseña.equals(entradaConfirmacion.getText()) && !usuario.isEmpty()) {
            System.out.println("");
        } else {

        }

    }
}
