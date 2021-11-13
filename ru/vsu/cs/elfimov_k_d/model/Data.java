package ru.vsu.cs.elfimov_k_d.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Objects;


/**
 * Класс-модель для данных файла (Data).
 *
 * Property позволяет нам получать автоматические уведомления при любых изменениях переменных,
 * таких как identification или любых других.
 * Это позволяет поддерживать синхронность представления и данных.
 */
public class Data {
    private final StringProperty identification;
    private final StringProperty fileName;
    private final StringProperty numberOfPages;
    private final StringProperty priority;

    /**
     * Конструктор по умолчанию.
     */
    public Data() {
        this(null, null, null, null);
    }

    /**
     * Конструктор с некоторыми начальными данными.
     *
     * @param identification - число
     * @param fileName - строка
     * @param priority - число
     * @param numberOfPages - число
     */
    public Data(String identification, String fileName, String priority, String numberOfPages) {
        this.identification = new SimpleStringProperty(identification);
        this.fileName = new SimpleStringProperty(fileName);
        this.priority = new SimpleStringProperty(priority);
        this.numberOfPages = new SimpleStringProperty(numberOfPages);
    }

    public void setPriority(String priority) {
        this.priority.set(priority);
    }
    public void setNumberOfPages(String numberPages) {
        this.numberOfPages.set(numberPages);
    }
    public void setFileName(String nameFile) {
        this.fileName.set(nameFile);
    }
    public void setIdentification(String identification) {
        this.identification.set(identification);
    }

    public String getFileName() {
        return fileName.get();
    }
    public String getNumberOfPages() {
        return numberOfPages.get();
    }
    public String getPriority() {
        return priority.get();
    }
    public String getIdentification() {
        return identification.get();
    }

    public StringProperty identificationProperty() {
        return identification;
    }
    public StringProperty fileNameProperty() {
        return fileName;
    }
    public StringProperty priorityProperty() {
        return priority;
    }
    public StringProperty numberOfPagesProperty() {
        return numberOfPages;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identification, fileName, priority, numberOfPages);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Data data = (Data) o;
        return identification != data.identification && fileName.equals(data.fileName)
                && priority == data.priority && numberOfPages == data.numberOfPages;
    }
    @Override
    public String toString() {
        return identification + "; " + fileName + "; " + priority + " " + numberOfPages;
    }
}
