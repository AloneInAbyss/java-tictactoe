package com.letscode.tictactoe;

public class Main {

    public static void main(String[] args) {
        char[] board = new char[9];
        clearBoard(board, Display.NO);
        displayBoard(board);
    }

    static void clearBoard(char[] board, Display display) {
        for (int i = 0; i < board.length; i++) {
            board[i] = (char) (i + 1 + '0');
        }
        if (display == Display.YES) System.out.printf("%nTabuleiro limpo!%n");
    }

    static void displayBoard(char[] board) {
        System.out.println();

        for (int i = 1; i <= board.length; i++) {
            if (i % 3 == 0) {
                System.out.println(board[i - 1]);
            }
            else {
                System.out.print(board[i - 1] + " ");
            }
        }
    }
}

enum Display {
    NO, YES
}

enum Player {
    X, O
}
