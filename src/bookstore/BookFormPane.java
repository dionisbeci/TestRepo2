package bookstore;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookFormPane extends GridPane{

	private static final TextField titleTF=new TextField();
	private static final Label titleLbl=new Label("Title");
	private static final TextField isbnTF=new TextField();
	private static final Label isbnLbl=new Label("ISBN 13");
	private static final TextField priceTF=new TextField();
	private static final Label priceLbl=new Label("Price");
	private static final Label versionLbl=new Label("Version");
	private static final RadioButton rbPaperback=new RadioButton("Paperback");
	private static final RadioButton rbEbook=new RadioButton("Ebook");
	private static final Label descriptiLbl=new Label("Description");
	private static final TextArea descriptionTa=new TextArea();
	private static final Label authorLbl=new Label("Select an author");
	private static final ComboBox<Author> authorComboBox=new ComboBox<Author>();
	private static final Label genreLbl=new Label("Genres");
	private static final ArrayList<CheckBox> genreCheckBoxes=new ArrayList<>();
	
	private static final Button btnSubmit=new Button("Submit");
	
	
	public BookFormPane()
	{
		setupView();
	}
	 public TextField getTitleTF() {
	        return titleTF;
	    }

	    public TextField getIsbnTF() {
	        return isbnTF;
	    }

	    public TextField getPriceTF() {
	        return priceTF;
	    }

	    public RadioButton getRbPaperback() {
	        return rbPaperback;
	    }

	    public RadioButton getRbEbook() {
	        return rbEbook;
	    }

	    public TextArea getDescriptionTA() {
	        return descriptionTa;
	    }

	    public ComboBox<Author> getAuthorComboBox() {
	        return authorComboBox;
	    }

	    public ArrayList<CheckBox> getGenreCheckboxes() {
	        return genreCheckBoxes;
	    }

	    public Button getSubmitBtn() {
	        return btnSubmit;
	    }
	 
	    public void setupView() {
	        this.setAlignment(Pos.CENTER);
	        this.setPadding(new Insets(10, 10, 10, 10));
	        this.setHgap(5);
	        this.setVgap(5);

	        ToggleGroup radioGroup = new ToggleGroup();
	        rbPaperback.setToggleGroup(radioGroup);
	        rbEbook.setToggleGroup(radioGroup);

	        HBox hbox = new HBox(10);
	        hbox.getChildren().addAll(rbPaperback, rbEbook);

	        descriptionTa.setPrefColumnCount(20);
	        descriptionTa.setPrefRowCount(5);
	        descriptionTa.setWrapText(true);

	        ArrayList<Author> authors = FileHandler.getAvailableAuthors();
	        authorComboBox.getItems().addAll(authors);

	        VBox paneforGenres = new VBox(10);
	        paneforGenres.setPadding(new Insets(4));

	        for (Genre g : Genre.values()) {
	            genreCheckBoxes.add(new CheckBox(g.toString()));
	        }
	        paneforGenres.getChildren().addAll(genreCheckBoxes);

	        // Add nodes to the GridPane
	        this.add(titleLbl, 0, 0);
	        this.add(titleTF, 1, 0);
	        this.add(isbnLbl, 0, 1);
	        this.add(isbnTF, 1, 1); // Add only once
	        this.add(priceLbl, 0, 2);
	        this.add(priceTF, 1, 2);
	        this.add(versionLbl, 0, 3);
	        this.add(hbox, 1, 3);
	        this.add(descriptiLbl, 0, 4);
	        this.add(descriptionTa, 1, 4);
	        this.add(authorLbl, 0, 5);
	        this.add(authorComboBox, 1, 5);
	        this.add(genreLbl, 0, 6);
	        this.add(paneforGenres, 1, 6);
	        this.add(btnSubmit, 1, 7);
	    }

	    
}