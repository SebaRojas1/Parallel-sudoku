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

/**
 * The class of the sudoku solution in parallel
 */
public class Algorithm {

    //The sudoku board
    private SudokuBoard board;

    /**
     * Constructor
     * @param board The board to be used
     */
    public Algorithm(SudokuBoard board) {
        this.board = board;
    }

    /**
     * The method used to solve the sudoku puzzle in parallel
     * @return
     */
    public boolean solve(){
        for (int row = 0; row < board.getN(); row++) {
            for (int col = 0; col < board.getN(); col++) {
                if(board.getBoard()[row][col] == 0) {
                    for(int number = 1; number < 10; number++) {
                        if(board.checkMovement(row, col, number)) {
                            board.setNumberByPos(row, col, number);
                            if(solve()) {
                                return true;
                            }
                            else {
                                board.setNumberByPos(row, col, 0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

}
