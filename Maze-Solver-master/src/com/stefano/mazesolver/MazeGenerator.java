package com.stefano.mazesolver;

public class MazeGenerator {

    private int rows, cols;
    private int startX = 1;
    private int startY = 1;
    private int endX, endY;
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, -1, 0, 1};
    private int[][] maze;

    public MazeGenerator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.endX = cols - 2;
        this.endY = rows - 2;
        maze = new int[rows][cols];
        for(int i=0; i<rows; ++i) {
            for(int j=0; j<cols; ++j) {
                maze[i][j] = 1;
            }
        }
        generateMaze(0, 0);
    }

    private boolean generateMaze(int cx, int cy) {
        if(maze[cy][cx] == 1) {
            maze[cy][cx] = 0;
        }

        if(cx == endX && cy == endY) {
            maze[cy][cx] = 9;
            return true;
        }

        for(int i=0; i<4; ++i) {
            if(generateMaze(cx + dx[i], cy + dy[i])) {
                return true;
            }
        }

        return false;


    }

    private enum DIR {
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        private final int bit;
        private final int dx;
        private final int dy;
        private DIR opposite;

        // use the static initializer to resolve forward references
        static {
            N.opposite = S;
            S.opposite = N;
            E.opposite = W;
            W.opposite = E;
        }

        DIR(int bit, int dx, int dy) {
            this.bit = bit;
            this.dx = dx;
            this.dy = dy;
        }
    };

    public int[][] getMaze() {
        return maze;
    }
}