/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vistas;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import modelos.Usuario;

/**
 * FXML Controller class
 *
 * @author Samue
 */
public class SignosController implements Initializable {

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
    private Button botonMinimizar;
    @FXML
    private Button botonCerrar;
    @FXML
    private Button botonRegresar;
    
    //Grafica del ritmo cardiaco
    @FXML
    private Canvas heartbeatCanvas;
    private double x = 0;
    private double y = 150;         // Línea base (punto de referencia)
    private double speed = 5;       // Velocidad de desplazamiento de la línea
    private int patternIndex = 0;
    
     private final double[] pattern = {
        100, 0, 0, 0, 0, 0, 0, -50, 0, 0, 0, 0, 0, 0, 0
    };
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Configurar el contexto gráfico
                
        GraphicsContext gc = heartbeatCanvas.getGraphicsContext2D();
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);
        
        // Crear la animación de línea
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e -> drawHeartbeat(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
     private void drawHeartbeat(GraphicsContext gc) {
        // Limpiar y reiniciar cuando se llegue al borde derecho del canvas
        if (x > heartbeatCanvas.getWidth()) {
            x = 0;
            gc.clearRect(0, 0, heartbeatCanvas.getWidth(), heartbeatCanvas.getHeight());
        }

        // Calcular la posición Y según el patrón de ECG
        double newY = y + pattern[patternIndex];
        
        // Dibujar línea entre el último punto y el nuevo
        gc.strokeLine(x, y + pattern[patternIndex == 0 ? pattern.length - 1 : patternIndex - 1], x + speed, newY);
        
        // Avanzar en x y en el patrón
        x += speed;
        patternIndex = (patternIndex + 1) % pattern.length;
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
