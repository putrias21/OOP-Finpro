package com.stefano.mazesolver;

import java.util.List;

public class DepthFirst {
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};

    public static boolean searchPath(int[][] maze, int x, int y, List<Integer> path, List<Integer> traversal) {

        if(maze[y][x] == 0 || maze[y][x] == 9) {
            traversal.add(x);
            traversal.add(y);
        }

        if(maze[y][x] == 9) {
            path.add(x);
            path.add(y);
            return true;
        }

        if(maze[y][x] == 0) {
            maze[y][x] = 2; // mark starting node as visited

            // recursively visit all neighbors
            for(int i=0; i<4; ++i) {
                if(searchPath(maze, x + dx[i], y + dy[i], path, traversal)) {
                    path.add(x);
                    path.add(y);
                    return true;
                }
            }
        }

        return false;
    }
}
