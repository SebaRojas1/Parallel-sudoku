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

import java.io.*;

public class BoardReader {


    public BoardReader() {

    }

    /**
     * This method receives the file containing the sudoku board and the size
     * @return true if the file information is correct, false in other cases
     */
    public SudokuBoard readEntryBoard() throws IOException {
        FileReader fReader = new FileReader("entry_board.txt");
        BufferedReader bReader = new BufferedReader(fReader);
        int N = Integer.parseInt(bReader.readLine());
        int[][] sudokuBoard = new int[N][N];
        int[][] initialSudokuBoard = new int[N][N];
        for(int i = 0; i < N; i++) {
            String line = bReader.readLine();
            String[] rowNumbers = line.split(" ");
            for(int p = 0; p < N; p++) {
                sudokuBoard[i][p] = Integer.parseInt(rowNumbers[p]);
                initialSudokuBoard[i][p] = Integer.parseInt(rowNumbers[p]);
            }
        }
        SudokuBoard newBoard = new SudokuBoard(sudokuBoard, initialSudokuBoard, N);
        return newBoard;
    }
}