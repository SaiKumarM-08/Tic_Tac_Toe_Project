package com.game;

import java.util.Random;
import java.util.Scanner;

class TicTacToe
{
    //Declaring game board
    static char[][] board;

    //initializing 3x3 board
    public TicTacToe()
    {
        board = new char[3][3];
        initBoard();
    }

    //converting default values too empty
    public void initBoard()
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                board[i][j] = ' ';
            }
        }
    }

    //Display board on console
    public void dispBoard()
    {
        System.out.println("-------------");
        for(int i = 0; i < board.length; i++)
        {
            System.out.print("| ");
            for(int j = 0; j < board[i].length; j++)
            {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    //placing mark using co-ordinates
    public static void placeMark(int row, int col, char mark)
    {
        if(row >= 0 && row <= 2 &&
            col >= 0 && col <=2)
        {
            board[row][col] = mark;
        }
        else
        {
            System.out.println("Invalid position");
        }
    }

    //checking for is any row matches
    public boolean checkRowWin()
    {
        for(int i = 0; i <= 2; i++)
        {
            if(board[i][0] != ' ' && board[i][0] == board[i][1] &&
                board[i][1] == board[i][2])
            {
                return true;
            }
        }
        return false;
    }

    //checking for is any column matches
    public boolean checkColWin()
    {
        for(int j = 0; j <= 2; j++)
        {
            if(board[0][j] != ' ' && board[0][j] == board[1][j] &&
                    board[1][j] == board[2][j])
            {
                return true;
            }
        }
        return false;
    }

    //checking for is any diagonal matches
    public boolean checkCrossWin()
    {
        if(board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] ||
                board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0])
        {
            return true;
        }
        return false;
    }

    //checking is game draw or not
    public boolean checkDraw()
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[i][j] == ' ')
                {
                    return false;
                }
            }
        }
        return true;
    }
}

abstract class PlayerMode
{
    String name;
    char mark;

    abstract void makeMove();

    public boolean isValidMove(int row, int col)
    {
        if(row >= 0 && row <= 2 &&
                col >= 0 && col <=2 && TicTacToe.board[row][col] == ' ')
        {
            return true;
        }
        return false;
    }
}

class HumanPlayer extends PlayerMode
{
    public HumanPlayer(String name, char mark)
    {
        this.name = name;
        this.mark = mark;
    }

    @Override
    public void makeMove()
    {
        Scanner sc = new Scanner(System.in);
        int row;
        int col;

        do{
            row = sc.nextInt();
            col = sc.nextInt();
        } while(!isValidMove(row, col));

        TicTacToe.placeMark(row, col, mark);
    }


}

class AIplayer extends PlayerMode
{
    public AIplayer(String name, char mark)
    {
        this.name = name;
        this.mark = mark;
    }

    @Override
    public void makeMove()
    {
        Random r = new Random();
        int row;
        int col;

        do{
            row = r.nextInt(3);
            col = r.nextInt(3);
        } while(!isValidMove(row, col));

        TicTacToe.placeMark(row, col, mark);

    }
}


public class LaunchGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicTacToe ticTacToe = new TicTacToe();

        System.out.println("select play mode\n 1.Multiplayer Mode\n 2.AI Mode");
        int playMode = sc.nextInt();

        if(playMode == 1)
        {
            System.out.println("---Multiplayer---");
            System.out.print("Enter player 1 name: ");
            String player1 = sc.next();
            System.out.print("Enter player 2 name: ");
            String player2 = sc.next();

            ticTacToe.dispBoard();

            HumanPlayer p1 = new HumanPlayer(player1, 'X');
            HumanPlayer p2 = new HumanPlayer(player2, 'O');

            PlayerMode currPlayer;
            currPlayer = p1;

            while(true)
            {
                System.out.println(currPlayer.name + " turn");
                currPlayer.makeMove();
                ticTacToe.dispBoard();

                if(ticTacToe.checkRowWin() || ticTacToe.checkColWin() || ticTacToe.checkCrossWin())
                {
                    System.out.println(currPlayer.name + " has won the game");
                    break;
                }
                else if(ticTacToe.checkDraw())
                {
                    System.out.println("Game draw!");
                    break;
                }
                else
                {
                    if(currPlayer == p1)
                        currPlayer = p2;
                    else
                        currPlayer =p1;
                }
            }

        }
        else
        {
            System.out.println("---AI mode---");
            System.out.print("Enter player name: ");
            String player = sc.next();

            ticTacToe.dispBoard();

            HumanPlayer p1 = new HumanPlayer(player, 'X');
            AIplayer p2 = new AIplayer("Ai", 'O');

            PlayerMode currPlayer;
            currPlayer = p1;

            while(true)
            {
                System.out.println(currPlayer.name + " turn");
                currPlayer.makeMove();
                ticTacToe.dispBoard();

                if(ticTacToe.checkRowWin() || ticTacToe.checkColWin() || ticTacToe.checkCrossWin())
                {
                    System.out.println(currPlayer.name + " has won the game");
                    break;
                }
                else if(ticTacToe.checkDraw())
                {
                    System.out.println("Game draw!");
                    break;
                }
                else
                {
                    if(currPlayer == p1)
                        currPlayer = p2;
                    else
                        currPlayer =p1;
                }
            }
        }
    }
}
