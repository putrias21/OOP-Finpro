package com.stefano.mazesolver;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class BreadthFirst {
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, -1, 0, 1};

    public static boolean searchPath(int[][] maze, int x, int y, List<Integer> path, List<Integer> traversal) {
        Deque<Node> queue = new ArrayDeque<Node>();
        int[][] pre = new int[maze.length][maze[0].length];
        int[][] dis = new int[maze.length][maze[0].length];
        for(int i=0; i<dis.length; i++) {
            for(int j=0; j<dis[0].length; j++) {
                dis[i][j] = 100;
            }
        }

        //Enter the starting point, set the distance of the starting point to 0 and mark it as visited
        queue.add(new Node(x, y));
        dis[y][x] = 0;
        maze[y][x] = 2;
        traversal.add(x);
        traversal.add(y);
        Node temp;
        //Breadth first traverse all accessible points, and note down the shortest path and predecessor node of each point
        while(!queue.isEmpty()) {
            temp = queue.poll();
            //Try the four directions of each point
            for(int i=0; i<4; i++) {
                int tx = temp.x + dx[i];
                int ty = temp.y + dy[i];
                //If the point has not been visited, enqueue the point and mark it as visited
                if(maze[ty][tx] == 0 || maze[ty][tx] == 9) {
                    //Only one step at a time in the maze, so the distance is increased by one
                    traversal.add(tx);
                    traversal.add(ty);
                    dis[ty][tx] = dis[temp.y][temp.x] + 1;
                    pre[ty][tx] = i;
                    maze[ty][tx] = 2;
                    queue.add(new Node(tx, ty));
                }
            }
        }//The shortest path is stored in dis here, and the path is printed using the pre array below

        int a = maze[0].length - 2; //initialized at endX
        int b = maze.length - 2; //initialized at endY

        path.add(a);
        path.add(b);

        while(a != x || b != y) {
            int da = dx[pre[b][a]];
            int db = dy[pre[b][a]];
            a = a - da;
            b = b - db;
            path.add(a);
            path.add(b);
        }

        if(path.get(0) == maze[0].length - 2 && path.get(1) == maze.length - 2) {

            for(int i=0; i<traversal.size(); ++i) {
                System.out.print(traversal.get(i) + " ");
            }
            return true;
        }

        return false;
    }
}
