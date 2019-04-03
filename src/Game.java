import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    //list of Strings to store the movies
    private List<String> movies = new ArrayList<>();
    //integer variable to keep count of the movies(lines in the text file)
    private int linesInFile = 0;
    //string variable to store the title of the random movie
    private String randomMovie = null;
    //int to keep track of how many numberOfGuessesLeft this player have
    private int numberOfGuessesLeft = 10;
    //is set to true when the user has won
    private boolean hasWon = false;
    //list of chars to store the chars of every word
    private List<String> charactersInMovieTitle = new ArrayList<>();
    private boolean isCorrectGuess = false;

    public void play() {
        readMoviesFromFile("movies.txt");
        generateRandomMovieTitle();
        System.out.println(randomMovie);
        hideMovieTitle();
        checkUserInput();

        if (hasWon) {
            System.out.println("        Congratulations! you won");
        } else {
            System.out.println("        you lost!");
            System.out.println("the correct movie title was: " + randomMovie);
        }

    }

    /**
     * read movies from a text file
     *
     * @param filePath of the file containing the movies
     * @return list of movies
     */
    private void readMoviesFromFile(String filePath) {
        Scanner scanner = null;
        File file = new File(filePath);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: file 'movies.txt' is not found!");
        }
        //read the movies from the file and add them to the list
        //convert all the movies to lowercase
        while (scanner.hasNextLine()) {
            movies.add(scanner.nextLine().toLowerCase());
            linesInFile++;
        }

    }

    /**
     * choose a movie randomly
     */
    private void generateRandomMovieTitle() {
        //set the randomMovie to  an item from the ArrayList by calling .get() on a random number
        try {
            randomMovie = movies.get((int) (Math.random() * linesInFile) + 1);
            //eliminates leading and trailing spaces of the movie name
            randomMovie = randomMovie.trim();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: random movie doesn't exist! index out of bounds exception");
        }

    }

    /**
     * replaces all the letters in a movie with "_"
     */
    private void hideMovieTitle() {
        for (int i = 0; i < randomMovie.length(); i++) {
            String character = randomMovie.charAt(i) != ' ' ? "-" : " ";
            charactersInMovieTitle.add(character);
            System.out.print(character);
        }
    }

    /**
     * takes in a user input and check if it's correct or wrong
     */
    private void checkUserInput() {
        while (numberOfGuessesLeft > 0 || !hasWon) {
            System.out.println();
            Scanner sc = new Scanner(System.in);
            //this will read only the first character in a string(in case the user entered more than one char)
            //and turn it to lowercase
            char userInput = Character.toLowerCase(sc.next().charAt(0));
            //iterate though each letter of the randomMovie
            for (int i = 0; i < randomMovie.length(); i++) {
                if (randomMovie.charAt(i) == userInput) {
                    charactersInMovieTitle.set(i, String.valueOf(userInput));
                    //System.out.println("your guess " + userInput + " is correct!");
                    //System.out.println("you still have " + numberOfGuessesLeft + " guesses left");
                } else {
                    //System.out.println("your guess " + userInput + "is wrong! try another letter...");
                    //numberOfGuessesLeft--;
                    //System.out.println("you still have " + numberOfGuessesLeft + " guesses left");
                }
            }
            //print the current state of the movie title including user's correct guesses and dashes
            for (String c : charactersInMovieTitle) {
                System.out.print(c);
            }

            if (!charactersInMovieTitle.contains("_")) {
                hasWon = true;
            }

        }
    }
}
