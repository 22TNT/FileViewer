package com.example.fileviewer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;


import java.io.*;

public class HelloController {
    @FXML
    private TextField text1;

    private ObservableList<CustomFile> lineList = FXCollections.observableArrayList();
    /*we have to use an ObservableCollection to fill a TableView*/

    private String getExtensionFromFileName(String filename)
    {
        return filename.substring(filename.lastIndexOf(".")+1);
    }

    @FXML
    public TableView<CustomFile> table;

    @FXML
    public TableColumn<CustomFile, Integer> columnSize;

    @FXML
    public TableColumn<CustomFile, String> columnExtension;

   @FXML
    public TableColumn<CustomFile, Object> columnContent;

    @FXML
    private void onClearButtonClick()
    {
        /*OnClick method for clearButton*/
        table.getItems().clear();
    }

    @FXML
    protected void onLoadButtonClick() {
        /*OnClick method for loadButton*/
        FileChooser fc = new FileChooser();
        fc.setTitle("Open the file");
        File file = fc.showOpenDialog(text1.getScene().getWindow());
        ImageView imageView = new ImageView(new Image(file.getAbsolutePath()));
        String extension = getExtensionFromFileName(file.getName());
        if (extension.equalsIgnoreCase("png")
                || extension.equalsIgnoreCase("jpg")
                ||extension.equalsIgnoreCase("bmp"))
        {
            lineList.add(new CustomFile<ImageView>(imageView, extension, ((int) file.length())));
        }
        else
        {
            String cnt = "";
            try (BufferedReader br = new BufferedReader(new FileReader(file)))
            {
                int left = 0;
                for (int chr; (chr = br.read()) != -1; )
                {
                    cnt += (char)chr;
                    if (cnt.length()-left >= 200)
                    {
                        cnt+= "\n";
                        left+=200;
                    }
                }
                lineList.add(new CustomFile<String>(cnt, extension, (int) file.length()));
            } catch (FileNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error!");
                alert.setContentText("No such file");
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        columnContent.setCellValueFactory(new PropertyValueFactory<CustomFile, Object>("content"));
        columnExtension.setCellValueFactory(new PropertyValueFactory<CustomFile, String>("extension"));
        columnSize.setCellValueFactory(new PropertyValueFactory<CustomFile, Integer>("size"));


        table.setItems(lineList);
        /*magic code straight from StackOverflow to make it fill the TableView*/
    }

}
