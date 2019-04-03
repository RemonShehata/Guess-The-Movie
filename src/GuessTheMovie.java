/**
 * Guess the movie game(pretty much like hangman but with movies)!
 * The rules are simple, the computer randomly picks a movie title, and shows you how many letters it's made up of.
 * Your goal is to try to figure out the movie by guessing one letter at a time.
 * If a letter is indeed in the title the computer will reveal its correct position in the word,
 * if not, you lose a point. If you lose 10 points, game over!
 * BUT the more correct letters you guess the more obvious the movie becomes
 * and at a certain point you should be able to figure it out.
 * The program will randomly pick a movie title from a text file that contains a large list of movies.
 */

public class GuessTheMovie {

    public static void main(String[] args) {

        System.out.println("Welcome to Guess the movie game!");
        System.out.println("Try to guess the letters in  movie title");
        System.out.println("Enter one letter at a time, if the letter is correct it will be revealed");
        System.out.println("For each wrong letter you enter you lose 1 point");
        System.out.println("You have 10 Points left");

        Game game = new Game();
        game.play();

    }
}

