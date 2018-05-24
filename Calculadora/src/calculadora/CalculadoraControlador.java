package calculadora;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author Cándido Orantes López
 */
public class CalculadoraControlador implements Initializable {
	
	@FXML
	private TextField num1;
	@FXML
	private TextField num2;
	
	private double calculo;
	private double n1;
	private double n2;
	private String operacion;
	
	public void mostrarErrores (String titulo, String cabecera, String mensaje) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Numero erroneo");
				alert.setHeaderText("Error al convertir la cadena de caracteres");
				alert.setContentText("Por favor introduzca un numero correcto");
				
				alert.showAndWait();
	}
	
	public void convertirNumero () {
		String num1 = this.num1.getText();
		String num2 = this.num2.getText();
		if (num1.isEmpty())
			n1 = 0;
		else
			try {
				n1 = Integer.parseInt(num1);
			}
			catch (NumberFormatException ex){
				mostrarErrores ("Número erroneo","Fallo al convertir","Por favor, introduzca un número correctamenete");
			}
			
		if (num2.isEmpty())
			n2 = 0;
		else
			try {
				n2 = Integer.parseInt(num2);
			}
			catch (NumberFormatException ex){
				mostrarErrores ("Número erroneo","Fallo al convertir","Por favor, introduzca un número correctamenete");
			}
	}
	
	public CalculadoraControlador () {
		calculo=0;
		n1=0;
		n2=0;
		operacion="";
	}
	
	@FXML
	private void sumar () {
		operacion= "sumar";
	}
	
	@FXML
	private void restar () {
		operacion= "restar";
	}
	
	@FXML
	private void multiplicar () {
		operacion= "multiplicar";
	}
	
	@FXML
	private void dividir () {
		operacion= "dividir";
	}
	
	@FXML
	private void calcular(ActionEvent event) {
		convertirNumero();
		switch (operacion) {
			case "sumar":
				calculo = n1 + n2;
				break;
			case "restar":
				calculo = n1 - n2;
				break;
			case "multiplicar":
				calculo = n1 * n2;
				break;
			case "dividir":
				calculo = n1 / n2;
				break;
		}
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("");
			alert.setHeaderText("");
			alert.setContentText("El resultado es: " + calculo);	
			alert.showAndWait();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	
}