package ru.vsu.cs.elfimov_k_d.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.vsu.cs.elfimov_k_d.model.Data;
import ru.vsu.cs.elfimov_k_d.MainApp;

public class DataOverviewController {
    @FXML
    private TableView<Data> dataTable;
    @FXML
    private TableColumn<Data, String> identification;
    @FXML
    private TableColumn<Data, String> fileName;
    @FXML
    private TableColumn<Data, String> priority;
    @FXML
    private TableColumn<Data, String> numberOfPages;

    @FXML
    private Label identificationLabel;
    @FXML
    private Label fileNameLabel;
    @FXML
    private Label priorityLabel;
    @FXML
    private Label numberOfPagesLabel;

    // Ссылка на главное приложение.
    private MainApp mainApp;

    /**
     * Конструктор.
     * Конструктор вызывается раньше метода initialize().
     */
    public DataOverviewController() {
    }

    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы данных о файле с четырьмя столбцами.
        identification.setCellValueFactory(cellData -> cellData.getValue().identificationProperty());
        fileName.setCellValueFactory(cellData -> cellData.getValue().fileNameProperty());
        priority.setCellValueFactory(cellData -> cellData.getValue().priorityProperty());
        numberOfPages.setCellValueFactory(cellData -> cellData.getValue().numberOfPagesProperty());
        // Очистка дополнительной информации о файле.
        showDetails(null);

        // Слушаем изменения выбора, и при изменении отображаем
        // дополнительную информацию о файле
        dataTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetails(newValue));
    }
    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        dataTable.setItems(mainApp.getFileData());
    }

    /**
     * Заполняет все текстовые поля, отображая подробности о файле.
     * Если указанный data = null, то все текстовые поля очищаются.
     *
     * @param data — данные типа Data или null
     */
    private void showDetails(Data data) {
        if (data != null) {
            identificationLabel.setText(String.valueOf(data.getIdentification()));
            fileNameLabel.setText(data.getFileName());
            priorityLabel.setText(String.valueOf(data.getPriority()));
            numberOfPagesLabel.setText(String.valueOf(data.getNumberOfPages()));
        } else {
            // Если data = null, то убираем весь текст.
            identificationLabel.setText("");
            fileNameLabel.setText("");
            priorityLabel.setText("");
            numberOfPagesLabel.setText("");
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопке New...
     * Открывает диалоговое окно с дополнительной информацией нового кортежа.
     */
    @FXML
    private void handleNewData() {
        Data tempPerson = new Data();
        boolean okClicked = mainApp.showDataEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getFileData().add(tempPerson);
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit...
     * Открывает диалоговое окно для изменения выбранного картежа.
     */
    @FXML
    private void handleEditData() {
        Data selectedPerson = dataTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showDataEditDialog(selectedPerson);
            if (okClicked) {
                showDetails(selectedPerson);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Никакие данные не были выбраны");
            alert.setContentText("Пожалуйста, выберете данные в таблице!");

            alert.showAndWait();
        }
    }
    /**
     * Вызывается, когда администратор кликает по кнопке удаления.
     */
    @FXML
    private void handleDeleteRow() {
        int selectedIndex = dataTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            dataTable.getItems().remove(selectedIndex);
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Никакие данные не были выбраны");
            alert.setContentText("Пожалуйста, выберете данные в таблице!");

            alert.showAndWait();
        }
    }
}
