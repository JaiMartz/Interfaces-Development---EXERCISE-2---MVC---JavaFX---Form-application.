/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea02_di;

import Alumno.alumno;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Jairo
 */

public class vistaController implements Initializable {
    
    //Creamos los elementos
    @FXML public Button btnGuardar;
    @FXML public TextField txfDNI;
    @FXML public TextField txfModulo;
    @FXML public TextField txfNota;
    @FXML public TextField txfRecuperacion;
    
    //Añadimos la tabla
    @FXML public TableView<alumno> tablaAlumnos; 
    @FXML public TableColumn clDNI;
    @FXML public TableColumn clModulo;
    @FXML public TableColumn clNota;
    @FXML public TableColumn clRecuperacion;
    
    private final ObservableList<alumno>listaAlumnos = FXCollections.observableArrayList();
    
    
    /**
     * Method that clean the form fields.
     * Metodo que limpia los campos del formulario.
     */
    @FXML
    private void limpiarCampos(){
    
        txfDNI.setText("");
        txfModulo.setText("");
        txfRecuperacion.setText("");
        txfNota.setText("");
    }

    /**
     * Method that allow us to save the information in the table.
     * 
     * @param event 
     */
 
    @FXML
    private void accionGuardarNotas(ActionEvent event) {
        String dniRegExp = "\\d{8}[A-HJ-NP-TV-Z]";
        int parseNota = Integer.parseInt(txfNota.getText());
        int parseRecu = Integer.parseInt(txfRecuperacion.getText());
        
        String titError = "Error";
        String msgErrorInvalido = "El valor introducido no es válido, inténtelo de nuevo.\n"
                + "\n-Recuerde que la nota no puede ser mayor que 10"
                + "\n-Recuerde que la nota no puede ser menor que 0."
                + "\n-Recuerde que la nota de recuperacion no puede ser mayor que 5."
                + "\n-Recuerde que la nota de recuperacion no puede ser menor que 0."
                + "\n-Recuerde que DNI debe tener 8 digitos y 1 letra." ;
        String msgRecuError = "Si el alumno ha aprobado, no puede tener valor de recuperación";
        String msgDniError = "El DNI introducido no es valido, intentelo de nuevo.";
        
        if((parseNota<0) || (parseNota>10) || (parseRecu<0) ||(parseRecu>5)){
            JOptionPane.showConfirmDialog(null, msgErrorInvalido, titError, JOptionPane.ERROR_MESSAGE);
        }else if(!Pattern.matches(dniRegExp, txfDNI.getText())){
            JOptionPane.showConfirmDialog(null, msgDniError, titError, JOptionPane.ERROR_MESSAGE);
        }else if((parseNota>= 5)&&(parseRecu >= 5)){
            JOptionPane.showConfirmDialog(null, msgRecuError, titError, JOptionPane.ERROR_MESSAGE);
        }else{
            if(parseNota >= 5){
            parseRecu = 0;
            }
            alumno Alumno = new alumno(txfDNI.getText() ,txfModulo.getText(), Integer.parseInt(txfNota.getText()), parseRecu);
            listaAlumnos.add(Alumno);
            limpiarCampos();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clDNI.setCellValueFactory(new PropertyValueFactory<alumno, String>("DNI"));
        clModulo.setCellValueFactory(new PropertyValueFactory<alumno, String>("Modulo"));
        clNota.setCellValueFactory(new PropertyValueFactory<alumno, Integer>("Nota"));
        clRecuperacion.setCellValueFactory(new PropertyValueFactory<alumno, String>("Recuperacion"));

        tablaAlumnos.setItems(listaAlumnos);
    }    
    
}
