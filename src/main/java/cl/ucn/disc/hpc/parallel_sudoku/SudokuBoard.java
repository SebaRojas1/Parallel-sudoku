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
     * Constructor of the board class
     * @param board The input sudoku board
     */
    public SudokuBoard(int[][] board, int[][] initialBoard, int N) {
        this.board = board;
        this.initialBoard = initialBoard;
        this.N = N;
    }

    public boolean checkRow(int row, int number) {
        for (int i = 0; i < N; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCol(int col, int number) {
        for (int i = 0; i < N; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBox(int row, int col, int number) {
        int boxRow = row - row % 3;
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

    public boolean checkMovement(int row, int col, int number) {
        if(checkRow(row, number) || checkCol(col, number) || checkBox(row, col, number)){
            return false;
        }
        return true;
    }

    public boolean checkFullBoard() {

        for (int i = 0; i < N; i++) {
            for (int p = 0; p < N; p++) {
                if (board[i][p] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] getBoard() {
        return this.board;
    }

    public void  setInitialValues() {
        for(int i = 0; i < N; i++){
            for(int p = 0; p < N; p++){
                board[i][p] = initialBoard[i][p];
            }
        }
    }

    public void printBoard(){
        System.out.println("");
        log.info("Solved sudoku board");
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

    public void setNumberByPos(int row, int col, int number) {
        this.board[row][col] = number;
    }

    public int getN() {
        return this.N;
    }
}