/**
 * Copyright 2021 Sebastián Rojas Rodriguez sebastian.rojas04@alumnos.ucn.cl
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package cl.ucn.disc.hpc.parallel_sudoku;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * Board object class
 */
@Slf4j
public class SudokuBoard {

    //Sudoku board NxN
    private int[][] board;
    //Initial sudoku board NxN
    private int[][] initialBoard;
    //Size of the board
    private final int N;

    /**
     * Constructor
     * @param board The input sudoku board
     */
    public SudokuBoard(int[][] board, int[][] initialBoard, int N) {
        this.board = board;
        this.initialBoard = initialBoard;
        this.N = N;
    }

    /**
     * Method used to check if the number is already in the row
     * @param row Number of the row
     * @param number Number to check
     * @return True if the number is found in the row, false in other cases
     */
    public boolean checkRow(int row, int number) {
        for (int i = 0; i < N; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method used to check if the number is already in the column
     * @param col Number of the column
     * @param number Number to check
     * @return True if the number is found in the column, false in other cases
     */
    public boolean checkCol(int col, int number) {
        for (int i = 0; i < N; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method used to check if the number is already in the box
     * @param row Number of the row
     * @param col Number of the column
     * @param number Number to check
     * @return True if the number is in the box, false in other cases
     */
    public boolean checkBox(int row, int col, int number) {
        //The first row of the box is obtained
        int boxRow = row - row % 3;
        //The first column of the box is obtained
        int boxCol = col - col % 3;

        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int p = boxCol; p < boxCol + 3; p++) {
                if (board[i][p] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Method used to check that the movement you are trying to make is valid
     * @param row Number of the row
     * @param col Number of the column
     * @param number Number to check
     * @return True if the movement is valid, false if the movement is not valid
     */
    public boolean checkMovement(int row, int col, int number) {
        if(checkRow(row, number) || checkCol(col, number) || checkBox(row, col, number)){
            return false;
        }
        return true;
    }

    /**
     * Get the board used to be solved
     * @return The board
     */
    public int[][] getBoard() {
        return this.board;
    }

    /**
     * Method used to restore the initial values of the board
     */
    public void  setInitialValues() {
        for(int i = 0; i < N; i++){
            for(int p = 0; p < N; p++){
                board[i][p] = initialBoard[i][p];
            }
        }
    }

    /**
     * Method used to print the board
     */
    public void printBoard(){
        System.out.println("");
        for(int i = 0; i < N; i++) {
            for (int p = 0; p < N; p++) {
                System.out.print(board[i][p]);
                if((p+1)%3 == 0 && p!=0) {
                    System.out.print("     ");
                }
            }
            System.out.println(" ");
            if((i+1)%3 == 0) {
                System.out.println(" ");
            }
        }
    }

    /**
     * Method used to place a number on a grid
     * @param row Number of the row
     * @param col Number of the column
     * @param number Number to check
     */
    public void setNumberByPos(int row, int col, int number) {
        this.board[row][col] = number;
    }

    /**
     * Get the size
     * @return The size
     */
    public int getN() {
        return this.N;
    }
}