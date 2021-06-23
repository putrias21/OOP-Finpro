package com.stefano.mazesolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int mazeSize;
    private static int [][] maze =
            { {1,1,1,1,1,1,1,1,1,1,1,1,1},
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
    private static final List<Integer> path = new ArrayList<Integer>();

    private static void createWindow() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Maze Solver");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(final JFrame frame){
        JPanel mainPanel = new JPanel();
        LayoutManager mainLayout = new BorderLayout();
        mainPanel.setLayout(mainLayout);
        JPanel topPanel = new JPanel();
        LayoutManager topLayout = new FlowLayout();
        topPanel.setLayout(topLayout);

        JButton generateMazeButton = new JButton("Generate Maze");
        JButton solveMazeButton = new JButton("Solve Maze");
        solveMazeButton.setVisible(false);
        final JLabel instructions = new JLabel();


        topPanel.add(generateMazeButton);
        topPanel.add(solveMazeButton);
        mainPanel.add(topPanel, BorderLayout.EAST);
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

        generateMazeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = (String)JOptionPane.showInputDialog(
                        frame,
                        "Enter maze size",
                        "Maze Solver",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "Red"
                );
                if(result != null && result.length() > 0){
                    mazeSize = Integer.parseInt(result);
                    solveMazeButton.setVisible(true);
                    DepthFirst.searchPath(maze, 1, 1, path);
                    mainPanel.add(new MazeGUI(maze, path), BorderLayout.CENTER);
                }
            }
        });
    }

    public static void main(String[] args) {
        createWindow();
    }
}
