package vistas;

import com.mysql.fabric.Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
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

public class SignosController implements Initializable {

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
    private Label textoRitmo; // Este campo se tiene que actualizar a ejemplo: "72 BPM"  
    @FXML
    private Label textoOxigenacion; // Este campo se tiene que actualizar a ejemplo: "79 %"  

    @FXML
    private Button botonMinimizar;
    @FXML
    private Button botonCerrar;
    @FXML
    private Button botonRegresar;

    // Gráfica del ritmo cardiaco 
    Socket socket;
    BufferedReader in;
    @FXML
    private Canvas heartbeatCanvas;
    private int ritmoValue = 60;

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

        // Iniciar el hilo de recepción de datos
        new Thread(this::startDataReceiver).start();
    }

    private void drawHeartbeat(GraphicsContext gc) {
        // Limpar el area del electro
        gc.clearRect(0, 0, heartbeatCanvas.getWidth(), heartbeatCanvas.getHeight());

        //Centrar la linea jeje
        double midY = heartbeatCanvas.getHeight() / 2;
        double scaleY = 40;

        //Dibujo del electrocardiograma o cómo se escriba la verdad no estudié medicina
        
    }

    private void startDataReceiver() {
        while (true) { // Bucle infinito para intentar siempre recibir datos

            try {
                socket = new Socket("192.168.29.208", 12345);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String mensaje = in.readLine();
                String[] data = mensaje.split(";");
                if (data.length == 2) {
                    String ritmo = data[0].split(":")[1];
                    String oxigenacion = data[1].split(":")[1];

                    // Actualizar la interfaz de usuario  
                    Platform.runLater(() -> {
                        textoRitmo.setText(ritmo + " BPM");
                        textoOxigenacion.setText(oxigenacion + " %");
                        ritmoValue = Integer.parseInt(ritmo);
                        
                        
                    });
                }

            } catch (IOException e) {
                System.err.println("Error al conectar o recibir datos del servidor: " + e.getMessage());
            }
        }
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
