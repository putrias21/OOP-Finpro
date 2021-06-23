package com.stefano.mazesolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MazeSolverGUI extends JFrame {
    private JPanel mainPanel;
    private JLabel labelSize;
    private JTextField sizeInput;
    private JLabel labelMethod;
    private JRadioButton dfs;
    private JRadioButton bfs;
    private JButton btnRun;
    private JPanel mazePanel;
    private JButton btnRepeat;
    private JPanel mazeGUI;

    public static int mazeSize;
    private int [][] maze = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,1,0,1,0,1,0,0,0,0,0,1},
            {1,0,1,0,0,0,1,0,1,1,1,0,1},
            {1,0,0,0,1,1,1,0,0,0,0,0,1},
            {1,0,1,0,0,0,0,0,1,1,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,0,0,1},
            {1,0,1,0,1,0,0,0,1,1,1,0,1},
            {1,0,1,0,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,1,9,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    private List<Integer> traversal = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public MazeSolverGUI(String title) {
        super(title);
        ButtonGroup group = new ButtonGroup();
        group.add(dfs);
        group.add(bfs);
        mazePanel.setVisible(false);
        btnRepeat.setVisible(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(mainPanel);
        this.pack();
        btnRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mazeSize = Integer.parseInt(sizeInput.getText());
                    if(mazePanel.isVisible()) {
                        resetMaze();
                    } else {
                        mazeGUI = new MazeGUI(maze, path, traversal, path.size() - 2, 1);
                        mazePanel.setVisible(true);
                        btnRepeat.setVisible(true);
                    }
                    if(dfs.isSelected()) {
                        DepthFirst.searchPath(maze, 1, 1, path, traversal);
                    } else if(bfs.isSelected()) {
                        BreadthFirst.searchPath(maze, 1, 1, path, traversal);
                    }
                    setExtendedState(JFrame.MAXIMIZED_BOTH);
                    mazePanel.add(mazeGUI, BorderLayout.CENTER);
                    mazePanel.revalidate();
                    mazePanel.repaint();
                    revalidate();
                    repaint();
                } catch(Error err) {
                    err.printStackTrace();
                }
            }
        });
        btnRepeat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    resetMaze();
                    if(dfs.isSelected()) {
                        DepthFirst.searchPath(maze, 1, 1, path, traversal);
                    } else if(bfs.isSelected()) {
                        BreadthFirst.searchPath(maze, 1, 1, path, traversal);
                    }
                    setExtendedState(JFrame.MAXIMIZED_BOTH);
                    mazePanel.add(mazeGUI, BorderLayout.CENTER);
                    mazePanel.setVisible(true);
                    revalidate();
                    repaint();
                } catch(Error err) {
                    err.printStackTrace();
                }
            }
        });
    }

    private void resetMaze() {
        path = new ArrayList<>();
        traversal = new ArrayList<>();
        mazePanel.remove(mazeGUI);
        mazePanel.revalidate();
        mazePanel.repaint();
        for(int i=0; i<maze.length; ++i) {
            for(int j=0; j<maze[0].length; ++j) {
                if(maze[i][j] == 2) {
                    maze[i][j] = 0;
                }
            }
        }
        mazeGUI = new MazeGUI(maze, path, traversal, path.size() - 2, 1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new MazeSolverGUI("Maze Solver");
                frame.setVisible(true);
            }
        });
    }
}
