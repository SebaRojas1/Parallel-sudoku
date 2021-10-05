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

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The main class of the project
 */
@Slf4j
public final class Main {

    private static final int maxCores = Runtime.getRuntime().availableProcessors();
    /**
     * The main method of the class, this is where the application will run
     * @param args to use
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        //Class instance to read the board
        BoardReader file = new BoardReader();
        //Instance of the board class to have the board
        SudokuBoard board = file.readEntryBoard();

        //Iteration where the sudoku is solved with different amounts of threads
        for (int i = 1; i <= maxCores; i++) {

            //Board values reset, so it can be resolved again after running once
            board.setInitialValues();
            //Method to solve the sudoku
            solveSudoku(i, board);
        }
    }

    /**
     *
     * @param cantThreads Number of threads used
     * @param board The sudoku board used to be solved
     * @return True if a solution was found, false in other cases
     * @throws InterruptedException Task interruption
     */
    public static boolean solveSudoku(final int cantThreads, SudokuBoard board) throws InterruptedException {

        // The Executor of Threads
        final ExecutorService executor = Executors.newFixedThreadPool(cantThreads);

        log.info("Starting to solve with {} threads...", cantThreads);

        //Unresolved board print
        board.printBoard();

        //Solve runtime begins
        final StopWatch sw = StopWatch.createStarted();

        //The executor begins the tasks
        executor.submit(() -> {
            //Algorithm class instance
            Algorithm solver = new Algorithm();
            //Run solve
            solver.solve(board);
        });

        //Waiting for the executor
        executor.shutdown();

        //Maximum waiting time to be solved (minutes)
        int maxTime = 3;
        if(executor.awaitTermination(maxTime, TimeUnit.MINUTES)){
            //Time stops
            sw.stop();
            //Solved board print
            board.printBoard();
            log.info("The sudoku was solved with {} threads in a time of {} μs \n \n", cantThreads, sw.getTime(TimeUnit.MICROSECONDS));
            return true;
        }
        else{
            log.info("The sudoku was not solved");
            return false;
        }
    }

}
