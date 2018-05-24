package view;

import Util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import controller.MainApp;
import model.Person;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 *
 * @author Cándido Orantes López
 */
public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Referencia a la aplicación principal.
    private MainApp mainApp;

    /**
     * El constructor.
     * El constructor se llama antes del método initialize ().
     */
    public PersonOverviewController() {
    }

    /**
     * Inicializa la clase de controlador. Este método se llama automáticamente
     * después de que el archivo fxml haya sido cargado.
     */
    @FXML
    private void initialize() {
        // Inicializa la tabla de personas con las dos columnas.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		
		// Borrar detalles de la persona.
		showPersonDetails(null);

		// Escuche los cambios de selección y muestre los detalles de la persona cuando haya cambiado.
		personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Es llamado por la aplicación principal para dar una referencia a sí mismo.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Agregar datos de listas observables a la tabla
        personTable.setItems(mainApp.getPersonData());
    }
	
	/**
	* Rellena todos los campos de texto para mostrar detalles sobre la persona.
	* Si la persona especificada es nula, se borran todos los campos de texto.
	* 
	* @param person the person or null
	*/
	private void showPersonDetails(Person person) {
		if (person != null) {
			// Llena las etiquetas con información del objeto de la persona.
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
			cityLabel.setText(person.getCity());
			birthdayLabel.setText(DateUtil.format(person.getBirthday()));
			
		} else {
			// La persona es nula, elimina todo el texto.
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}
	
	/**
	* Se llama cuando el usuario hace clic en el botón Eliminar.
	*/
	@FXML
	private void handleDeletePerson() {
		int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			personTable.getItems().remove(selectedIndex);
		} else {
			// Nada seleccionado.
			Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("No Selection");
				alert.setHeaderText(null);
				alert.setContentText("Please select a person in the table.");
				alert.showAndWait();
		}
	}
	
	/**
	 * Se llama cuando el usuario hace clic en el nuevo botón. 
	 * Abre un cuadro de diálogo para editar los detalles de una nueva persona.
	 */
	@FXML
	private void handleNewPerson() {
		Person tempPerson = new Person();
		boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
		if (okClicked) {
			mainApp.getPersonData().add(tempPerson);
		}
	}

	/**
	 * Se invoca cuando el usuario hace clic en el botón Editar. 
	 * Abre un cuadro de diálogo para editar los detalles de la persona seleccionada.
	 */
	@FXML
	private void handleEditPerson() {
		Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
		if (selectedPerson != null) {
			boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
			if (okClicked) {
				showPersonDetails(selectedPerson);
			}

		} else {
			// Nada seleccionado.
			Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("No Selection");
				alert.setHeaderText(null);
				alert.setContentText("Please select a person in the table.");
				alert.showAndWait();
		}
	}
}