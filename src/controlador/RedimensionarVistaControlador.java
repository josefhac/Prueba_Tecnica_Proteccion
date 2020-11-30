package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RedimensionarVistaControlador implements Initializable{
	 ObservableList<String> archivosList = FXCollections.observableArrayList(cargarArchivos());
	 public String  RUTA_CARPETA_IMAGENES = "C:/Users/Jose/Desktop/Imagenes_Prueba/";
	 public String rutaImagen;
	 public Image imagen;
	 public int anchoReferencia = 796;
	 public int altoReferencia = 1123;
	 public int anchoOriginal;
	 public int altoOriginal;
	 FileInputStream inputstream;
	 public String ORIENTACION_VERTICAL = "VERTICAL";
	 public String ORIENTACION_HORIZONTAL = "HORIZONTAL";
	 
	 
	@FXML
    private  ComboBox<String> comboSeleccionarImagen;
	
    @FXML
    private ImageView ViewVisorImagen;
    
    @FXML
    private Button btnRedimensionar;
    
    @FXML
    private TextField txtAncho;

    @FXML
    private TextField txtAlto;
    
    @FXML
    private TextField txtOrientacion;
    

    
    @FXML
    void eventoCombo(ActionEvent event) throws FileNotFoundException {
    	inputstream = new FileInputStream(RUTA_CARPETA_IMAGENES+ comboSeleccionarImagen.getValue());
        imagen = new Image(inputstream);
        anchoOriginal=(int)imagen.getWidth();
        altoOriginal=(int)imagen.getHeight();
        ViewVisorImagen.setFitWidth(anchoOriginal);
        ViewVisorImagen.setFitHeight(altoOriginal);
    	ViewVisorImagen.setPreserveRatio(true);
    	ViewVisorImagen.setImage(imagen);
    	txtAncho.setText(Integer.toString(anchoOriginal));
    	txtAlto.setText(Integer.toString(altoOriginal));
    	txtOrientacion.setText("");
    }
   
    @FXML
    void eventoRedimensionar(ActionEvent event) {
    	ViewVisorImagen.setFitWidth(anchoOriginal);
    	ViewVisorImagen.setFitHeight(altoOriginal);
    	txtAncho.setText(Integer.toString(anchoOriginal));
    	txtAlto.setText(Integer.toString(altoOriginal));
    	if(anchoOriginal>anchoReferencia) {
    		ViewVisorImagen.setFitWidth(anchoReferencia);
    		txtAncho.setText(Integer.toString(anchoReferencia));
    	}
    	
    	if(altoOriginal>altoReferencia) {
    		ViewVisorImagen.setFitHeight(altoReferencia);
    		txtAlto.setText(Integer.toString(altoReferencia));
    	}
    	
    	
    	ViewVisorImagen.setPreserveRatio(true);
    	ViewVisorImagen.setImage(imagen);
    	if(Integer.parseInt(txtAncho.getText())>Integer.parseInt(txtAlto.getText())) {
    		txtOrientacion.setText(ORIENTACION_HORIZONTAL);
    		
    	}else {
    		txtOrientacion.setText(ORIENTACION_VERTICAL);
    	}
 
    }

    

    
	public String[] cargarArchivos() {
		File ruta = new File("C:/Users/Jose/Desktop/Imagenes_Prueba");
		String[] nombresImagenes = ruta.list();
		return nombresImagenes;
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboSeleccionarImagen.setItems(archivosList);
		txtAncho.setText("0");
		txtAlto.setText("0");
	}

}
