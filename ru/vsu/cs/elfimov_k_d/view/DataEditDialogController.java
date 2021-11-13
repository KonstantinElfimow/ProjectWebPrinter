package ru.vsu.cs.elfimov_k_d.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.vsu.cs.elfimov_k_d.model.Data;

/**
 * Окно для изменения информации о файле.
 */
 public class DataEditDialogController {
    @FXML
    private TextField identificationField;
    @FXML
    private TextField fileNameField;
    @FXML
    private TextField priorityField;
    @FXML
    private TextField numberOfPagesField;

    private Stage dialogStage;
    private Data data;
    private boolean okClicked = false;

    /**
     * Инициализирует класс-контроллер. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Устанавливает сцену для этого окна.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Задаёт данные файла, информацию о котором будем менять.
     *
     * @param data
     */
    public void setData(Data data) {
        this.data = data;

        identificationField.setText(data.getIdentification());
        fileNameField.setText(data.getFileName());
        priorityField.setText(data.getPriority());
        numberOfPagesField.setText(data.getNumberOfPages());
    }

    /**
     * Returns true, если пользователь кликнул OK, в другом случае false.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Вызывается, когда пользователь кликнул по кнопке OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            data.setIdentification(identificationField.getText());
            data.setFileName(fileNameField.getText());
            data.setPriority(priorityField.getText());
            data.setNumberOfPages(numberOfPagesField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }
    /**
     * Вызывается, когда пользователь кликнул по кнопке Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (identificationField.getText() == null || identificationField.getText().length() == 0) {
            errorMessage += "Невалидный идентификатор!\n";
        } else {
            try {
                Integer.parseInt(identificationField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Невалидный идентификатор (обязан быть числом)!\n";
            }
        }
        if (fileNameField.getText() == null || fileNameField.getText().length() == 0) {
            errorMessage += "Невалидное имя файла!\n";
        }
        if (priorityField.getText() == null || priorityField.getText().length() == 0) {
            errorMessage += "Невалидный приоритет!\n";
        } else {
            try {
                Integer.parseInt(priorityField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Невалидный приоритет (обязан быть числом)!\n";
            }
        }
        if (numberOfPagesField.getText() == null || numberOfPagesField.getText().length() == 0) {
            errorMessage += "Невалидное количество страниц!\n";
        } else {
            try {
                Integer.parseInt(numberOfPagesField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Невалидное количество страниц (обязан быть числом)!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Некорректные поля");
            alert.setHeaderText("Пожалуйста, введите корректные поля!");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}