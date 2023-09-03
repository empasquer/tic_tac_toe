import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        //whatever fucking something

        Scanner userInput = new Scanner(System.in);
        char player, computer;
        char[][] board = new char[3][3];
        boolean gameOver = false;
        boolean playerTurn = true;
        char currentPlayer;
        boolean checkWin = false;

        System.out.println("Let's play Tic-Tac-Toe.");
        System.out.println("Choose 'x' or 'o':");

        player = userInput.next().charAt(0);
        if (player == 'X' || player == 'x') {
            computer = 'o';
        } else {
            player = 'o'; // Default player choice is 'O'
            computer = 'x';
        }

        System.out.println("You have chosen: " + player);
        System.out.println("Let's start.");
        System.out.println("To place your sign on the Tic-Tac-Toe table, please insert row number and then column number.");
        System.out.println("There are 3 rows and 3 columns.");
        System.out.println("Example: For the first column and first row space, input 1 and 1.");
        System.out.println("\n\n");

        Random random = new Random();

        while (!gameOver) {
            currentPlayer = playerTurn ? player : computer;
            System.out.println("Current player is " + currentPlayer);

            if (currentPlayer == player) {
                // Get user input for the row and column
                System.out.println("Which row do you want to choose?");
                int row = userInput.nextInt() - 1;
                System.out.println("Which column do you want to choose?");
                int column = userInput.nextInt() - 1;

                if (isValidMove(board, row, column)) {
                    board[row][column] = currentPlayer;
                } else {
                    System.out.println("You can't play here, try again.");
                    continue;
                }
            } else {
                // Computer's random move
                int row, column;
                do {
                    row = random.nextInt(3);
                    column = random.nextInt(3);
                } while (!isValidMove(board, row, column));

                board[row][column] = currentPlayer;
                System.out.println("Computer chose row " + (row + 1) + " and column " + (column + 1));
            }

            // Print the updated board
            printBoard(board);

            // Check for a win after each move
            checkWin = checkWin(board, currentPlayer);

            if (checkWin) {
                gameOver = true;
                if (currentPlayer == player) {
                    System.out.println("Player " + currentPlayer + " wins!");
                } else {
                    System.out.println("Computer wins!");
                }
            } else if (isBoardFull(board)) {
                gameOver = true;
                System.out.println("It's a draw!");
            }

            playerTurn = !playerTurn;
        }

        userInput.close();
    }

    // Function to print the Tic-Tac-Toe board
    public static void printBoard(char[][] board) {
        System.out.println("   1   2   3");
        for (int i = 0; i < 3; i++) {
            System.out.print((char) ('1' + i) + "  ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  -----------");
            }
        }
    }

    // Function to check if a move is valid
    public static boolean isValidMove(char[][] board, int row, int column) {
        return row >= 0 && row < 3 && column >= 0 && column < 3 && board[row][column] == 0;
    }

    // Function to check if the game is won by a player
    public static boolean checkWin(char[][] board, char player) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Row win
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Column win
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Diagonal win
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Diagonal win
        }
        return false;
    }

    // Function to check if the board is full (a draw)
    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false; // There is an empty space, the board is not full
                }
            }
        }
        return true; // All spaces are filled, the game is a draw
    }

}