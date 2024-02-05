package nafiz2209061;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {
    private static final char Empty_Box = ' ';
    private static final char player_one_symbol = 'X';
    private static final char player_two_symbol = '0';

    private final char[][] gameBoard = new char[3][3];
    private final Scanner input = new Scanner(System.in);

    private String playerOne;
    private String playerTwo;
    private String currentPlayer;
    private String whoWonTheGame;

    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.start();
    }

    public void start() {
        startingBoard();
        askPlayerName();

        while (gameIsNotOver()) {
            drawBoard();
            printPlayerTurn();
            askForManeuver();
        }

        gameIsOver();
    }

    private void startingBoard() {
        for (char[] chars : gameBoard) {
            Arrays.fill(chars, Empty_Box);
        }
    }

    private void askPlayerName() {
        System.out.println("Welcome to Tic Tac Toe Game");
        System.out.println("Input First Player Name");
        playerOne = input.nextLine();
        System.out.println("Input Second Player Name");
        playerTwo = input.nextLine();
        System.out.println("Who is playing first? Press\n1 for " + playerOne + "\n2 for " + playerTwo);
        int player = input.nextInt();
        currentPlayer = (player == 1) ? playerOne : playerTwo;
    }

    private boolean gameIsNotOver() {
        return !(boardIsComplete() || anyPlayerOwn());
    }

    private boolean boardIsComplete() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == Empty_Box) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean anyPlayerOwn() {
        char cross = ' ';
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] == gameBoard[i][1] &&
                    gameBoard[i][1] == gameBoard[i][2] &&
                    gameBoard[i][0] != Empty_Box) {
                cross = gameBoard[i][0];
            }
        }

        for (int j = 0; j < 3; j++) {
            if (gameBoard[0][j] == gameBoard[1][j] &&
                    gameBoard[1][j] == gameBoard[2][j] &&
                    gameBoard[0][j] != Empty_Box) {
                cross = gameBoard[0][j];
            }
        }

        if (gameBoard[0][0] == gameBoard[1][1] &&
                gameBoard[1][1] == gameBoard[2][2] &&
                gameBoard[0][0] != Empty_Box) {
            cross = gameBoard[0][0];
        }

        if (gameBoard[2][0] == gameBoard[1][1] &&
                gameBoard[1][1] == gameBoard[0][2] &&
                gameBoard[2][0] != Empty_Box) {
            cross = gameBoard[2][0];
        }

        if (cross == player_one_symbol) {
            whoWonTheGame = playerOne;
        } else if (cross == player_two_symbol) {
            whoWonTheGame = playerTwo;
        }

        return whoWonTheGame != null;
    }

    private void drawBoard() {
        System.out.println("|---|---|---|");
        for (char[] chars : gameBoard) {
            System.out.printf("| %c | %c | %c |%n", chars[0], chars[1], chars[2]);
            System.out.println("|---|---|---|");
        }
    }

    private void printPlayerTurn() {
        System.out.println(whoIsPlaying() + "'s turn");
    }

    private String whoIsPlaying() {
        return currentPlayer;
    }

    private void askForManeuver() {
        int row;
        int col;

        do {
            System.out.print("Enter a row number (0, 1, or 2): ");
            row = input.nextInt();
            System.out.print("Enter a column number (0, 1, or 2): ");
            col = input.nextInt();

        } while (!validateInput(row, col));

        if (whoIsPlaying().equals(playerOne)) {
            gameBoard[row][col] = player_one_symbol;
            currentPlayer = playerTwo;
        } else {
            gameBoard[row][col] = player_two_symbol;
            currentPlayer = playerOne;
        }
    }

    private boolean validateInput(int row, int col) {
        if (row < 0 || col < 0 || row > 2 || col > 2) {
            System.out.println("Out of Boundary of the board. Please try again");
            return false;
        } else if (gameBoard[row][col] != Empty_Box) {
            System.out.println("Another Player already chose this position, try again");
            return false;
        } else {
            return true;
        }
    }

    private void gameIsOver() {
        drawBoard();
        System.out.println("Game Over!");

        if (whoWonTheGame != null) {
            System.out.println("Congratulations " + whoWonTheGame + "!");
        } else {
            System.out.println("Match is a tie!");
        }
    }
}


