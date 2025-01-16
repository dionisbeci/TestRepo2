package bookstore;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private final BookFormPane pane = new BookFormPane();

    @Override
    public void start(Stage stage) {
        // Set the Submit button handler using a lambda expression
        pane.getSubmitBtn().setOnAction(event -> onBookSubmit(event));

        Scene scene = new Scene(pane, 500, 500);
        stage.setScene(scene);
        stage.setTitle("Bookstore");
        stage.show();
    }

    private void onBookSubmit(ActionEvent actionEvent) {
        try {
            // Get user input from the form
            String isbn = pane.getIsbnTF().getText().trim();
            String title = pane.getTitleTF().getText().trim();
            String description = pane.getDescriptionTA().getText().trim();
            float price = Float.parseFloat(pane.getPriceTF().getText().trim());
            Author author = pane.getAuthorComboBox().getValue();
            boolean isPaperback = pane.getRbPaperback().isSelected();

            // Create a new book with the provided input
            Book newBook = new Book(isbn, title, description, price, author, isPaperback);

            // Add selected genres to the book
            for (int i = 0; i < pane.getGenreCheckboxes().size(); i++) {
                if (pane.getGenreCheckboxes().get(i).isSelected()) {
                    newBook.addGenre(Genre.values()[i]);
                }
            }

            // Write the book to file and show feedback
            boolean success = FileHandler.writeBookToFile(newBook);
            if (success) {
                System.out.println("Book successfully saved: " + newBook);
            } else {
                System.out.println("Failed to save the book.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid price entered. Please provide a valid number.");
        } catch (Exception ex) {
            System.out.println("An error occurred: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
