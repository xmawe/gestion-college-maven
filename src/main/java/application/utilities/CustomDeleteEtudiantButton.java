package application.utilities;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import application.model.Etudiant;

public class CustomDeleteEtudiantButton
        implements Callback<TableColumn<Etudiant, String>, TableCell<Etudiant, String>> {
    private final ButtonClickHandler<Etudiant> buttonClickHandler;

    public CustomDeleteEtudiantButton(ButtonClickHandler<Etudiant> buttonClickHandler) {
        this.buttonClickHandler = buttonClickHandler;
    }

    @Override
    public TableCell<Etudiant, String> call(TableColumn<Etudiant, String> param) {
        return new TableCell<>() {
            private final Button button = new Button();

            {
                Image icon = new Image(getClass().getResourceAsStream("/images/icons/trash-03.png"));
                ImageView imageView = new ImageView(icon);
                imageView.setFitWidth(16); // Set the width of the image
                imageView.setFitHeight(16); // Set the height of the image

                // Set the image as the graphic for the button
                button.setGraphic(imageView);
                button.setStyle(
                        "-fx-background-color: #E5C4C2; -fx-cursor: Hand; -fx-padding: 5px");
                button.setOnAction(event -> {
                    // Handle button click here
                    Etudiant rowData = getTableRow().getItem();
                    System.out.println("Button clicked. Data for row: " + rowData);

                    // Call the button click handler function
                    buttonClickHandler.handleButtonClick(rowData);
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        };
    }
}
