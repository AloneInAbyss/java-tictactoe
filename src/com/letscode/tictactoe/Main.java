package com.letscode.tictactoe;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Player player = drawPlayer();
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
                    clearConsole();
                    System.out.println("Conferindo...");
                    delay(3, 700);
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
