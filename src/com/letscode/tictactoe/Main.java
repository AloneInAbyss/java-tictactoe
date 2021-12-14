package com.letscode.tictactoe;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Player player = drawPlayer();
        Player computer;

        if (player == Player.X) computer = Player.O;
        else computer = Player.X;

        char[] board = new char[9];
        Scanner scanner = new Scanner(System.in);
        String response;

        clearBoard(board, Display.NO);
        displayBoard(board);

        System.out.println();
        System.out.println("Deseja jogar? Pressione [Enter] ou digite \"Sair\" para finalizar.");
        response = scanner.nextLine();

        if (response.equals("Sair")) {
            System.exit(0);
        }

        while (!response.equals("Sair")) {
            clearConsole();
            displayBoard(board);

            System.out.println();
            System.out.println("Você é o jogador " + player + ".");
            System.out.println("Digite o número da posição onde deseja jogar.");
            response = scanner.nextLine();

            int responseNumber = Integer.parseInt(response);
            if (responseNumber >= 1 && responseNumber <= 9) {
                if (board[responseNumber - 1] == 'X' || board[responseNumber - 1] == 'O') {
                    clearConsole();
                    System.out.println("Jogada inválida...");
                    System.out.println("Tente novamente!");
                    delay(3, 750);
                    continue;
                }
                else {
                    board = makeMove(board, responseNumber, player);
                    String result = checkIfItsOver(board);
                    if (!result.equals("")) {
                        clearConsole();
                        displayBoard(board);

                        System.out.println("Vencedor: " + result);
                        break;
                    }

                    clearConsole();
                    System.out.println("É a vez do computador...");
                    delay(3, 700);

                    int move = calculateMove(board, player);
                    board = makeMove(board, move + 1, computer);
                    result = checkIfItsOver(board);
                    if (!result.equals("")) {
                        clearConsole();
                        displayBoard(board);

                        System.out.println("Vencedor: " + result);
                        break;
                    }

                }
            }
        }

        System.exit(0);
    }

    static Player drawPlayer() {
        Random rand = new Random();
        int n = rand.nextInt(2);

        if (n == 0) return Player.X;
        else return Player.O;
    }

    static char[] makeMove(char[] board, int position, Player player) {
        char symbol;

        if (player == Player.X) symbol = 'X';
        else symbol = 'O';

        board[position - 1] = symbol;
        return board;
    }

    static String checkIfItsOver(char[] board) {
        for (int i = 0; i < 9; i += 3) {
            if (board[i] == board[1 + i] && board[i] == board[2 + i]) {
                return String.valueOf(board[i]);
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i] == board[3 + i] && board[i] == board[6 + i]) {
                return String.valueOf(board[i]);
            }
        }

        if (board[0] == board[4] && board[0] == board[8]) {
            return String.valueOf(board[0]);
        }

        if (board[2] == board[4] && board[2] == board[6]) {
            return String.valueOf(board[2]);
        }

        return "";
    }

    static int calculateMove(char[] board, Player player) {
        char playerSymbol, computerSymbol;

        if (player == Player.X) {
            playerSymbol = 'X';
            computerSymbol = 'O';
        }
        else {
            playerSymbol = 'O';
            computerSymbol = 'X';
        }

        for (int i = 0; i < 9; i += 3) {
            if (board[i] == playerSymbol && board[1 + i] == playerSymbol && board[2 + i] != computerSymbol) {
                return 2 + i;
            }
            if (board[i] == playerSymbol && board[2 + i] == playerSymbol && board[1 + i] != computerSymbol) {
                return 1 + i;
            }
            if (board[1 + i] == playerSymbol && board[2 + i] == playerSymbol && board[i] != computerSymbol) {
                return i;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[i] == playerSymbol && board[3 + i] == playerSymbol && board[6 + i] != computerSymbol) {
                return 6 + i;
            }
            if (board[i] == playerSymbol && board[6 + i] == playerSymbol && board[3 + i] != computerSymbol) {
                return 3 + i;
            }
            if (board[3 + i] == playerSymbol && board[6 + i] == playerSymbol && board[i] != computerSymbol) {
                return i;
            }
        }

        if (board[0] == playerSymbol && board[4] == playerSymbol && board[8] != computerSymbol) {
            return 8;
        }
        if (board[0] == playerSymbol && board[8] == playerSymbol && board[4] != computerSymbol) {
            return 4;
        }
        if (board[4] == playerSymbol && board[8] == playerSymbol && board[0] != computerSymbol) {
            return 0;
        }

        if (board[2] == playerSymbol && board[4] == playerSymbol && board[6] != computerSymbol) {
            return 6;
        }
        if (board[2] == playerSymbol && board[6] == playerSymbol && board[4] != computerSymbol) {
            return 4;
        }
        if (board[4] == playerSymbol && board[6] == playerSymbol && board[2] != computerSymbol) {
            return 2;
        }

        for(int i = 0; i < 9; i++) {
            if (board[i] != computerSymbol && board[i] != playerSymbol) return i;
        }

        return 0;
    }

    static void clearBoard(char[] board, Display display) {
        for (int i = 0; i < board.length; i++) {
            board[i] = (char) (i + 1 + '0');
        }
        if (display == Display.YES) System.out.printf("%nTabuleiro limpo!%n");
    }

    static void displayBoard(char[] board) {
        System.out.println();
        System.out.println("-------  BEM-VINDO AO JOGO DA VELHA  -------");
        System.out.println();

        for (int i = 1; i <= board.length; i++) {
            if (i % 3 == 1) {
                System.out.print("                    ");
                System.out.print(board[i - 1] + " ");
            }
            else if (i % 3 == 0) {
                System.out.println(board[i - 1]);
            }
            else {
                System.out.print(board[i - 1] + " ");
            }
        }

        System.out.println();
        System.out.println("--------------------------------------------");
    }

    static void clearConsole() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    static void delay(int amount, int time) throws InterruptedException {
        for (int i = amount; i >= 1; i--) {
            System.out.println();
            System.out.println(i + "...");
            TimeUnit.MILLISECONDS.sleep(time);
        }
    }

}

enum Display {
    NO, YES
}

enum Player {
    X, O
}
