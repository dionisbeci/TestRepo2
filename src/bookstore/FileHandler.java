package bookstore;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private static final File AUTHORS_FILE = new File("src/bookstore/authors.dat");
    private static final File BOOKS_FILE = new File("src/bookstore/books.dat");

    public static ArrayList<Author> getAvailableAuthors() {
        if (!AUTHORS_FILE.exists() || AUTHORS_FILE.length() == 0) {
            seedAuthorData();
            System.out.println("File size after seeding: " + AUTHORS_FILE.length());
        }

        ArrayList<Author> authors = new ArrayList<>();
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(AUTHORS_FILE))) {
            while (true) {
                Author author = (Author) reader.readObject();
                authors.add(author);
            }
        } catch (EOFException ex) {
            System.out.println("Reached the end of file");
        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

        return authors;
    }

    private static void seedAuthorData() {
        Author[] authors = new Author[]{
            new Author("Fyodor", "Dostoyevsky", Gender.MALE),
            new Author("Noam", "Chomsky", Gender.MALE),
            new Author("Emily", "Bronte", Gender.FEMALE),
            new Author("George", "Orwell", Gender.MALE)
        };

        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(AUTHORS_FILE))) {
            for (Author author : authors) {
                writer.writeObject(author);
            }
            System.out.println("Seeded authors data");
        } catch (IOException ex) {
            System.out.println("Error during writing authors: " + ex.getMessage());
        }
    }

    public static boolean writeBookToFile(Book book) {
        try (FileOutputStream outputStream = new FileOutputStream(BOOKS_FILE, true)) {
            ObjectOutputStream writer;
            if (BOOKS_FILE.length() > 0) {
                writer = new HeaderlessObjectOutputStream(outputStream);
            } else {
                writer = new ObjectOutputStream(outputStream);
            }
            writer.writeObject(book);
            writer.close();
            return true;
        } catch (IOException ex) {
            System.out.println("Error writing book to file: " + ex.getMessage());
            return false;
        }
    }
}
