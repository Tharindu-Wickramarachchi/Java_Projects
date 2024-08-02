// Algorithms – Coursework
// Name - W.P.D.Tharindu
// IIT - 20221669
// UoW - w1956204

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    // Class that represents the coordinates
    static class Coordinate {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Class to represent directions
    public static class Direction {
        int deltaX, deltaY;

        public Direction(int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
    }

    // Define directions and sliding directions as constants
    public static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static final Direction[] SLIDE_DIRECTIONS = {
            new Direction(0, -1),  // Up
            new Direction(-1, 0),  // Left
            new Direction(0, 1),   // Down
            new Direction(1, 0)    // Right
    };

    // Check if a coordinate is valid within the grid
    public static boolean isValidCoordinate(int x, int y, char[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        return x >= 0 && x < numRows && y >= 0 && y < numCols && grid[x][y] != '0';
    }

    // Slide along the wall in the given direction
    public static void slideAlongWall(char[][] grid, Coordinate current, Direction slideDirection) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        while (true) {
            int nextX = current.x + slideDirection.deltaX;
            int nextY = current.y + slideDirection.deltaY;

            // Check if next position is within bounds
            if (nextX >= 0 && nextX < numRows && nextY >= 0 && nextY < numCols) {
                // Check if next position is not a wall or a '0' obstacle
                if (grid[nextX][nextY] != '#' && grid[nextX][nextY] != '0') {
                    current.x = nextX;
                    current.y = nextY;
                } else {
                    break; // Hit a wall or '0' obstacle, stop sliding
                }
            } else {
                break; // Out of bounds, stop sliding
            }
        }
    }

    // Find the shortest path from source to destination
    public static List<Coordinate> shortestPath(char[][] grid, Coordinate source, Coordinate destination) {
        List<Coordinate> path = new ArrayList<>();
        int numRows = grid.length;
        int numCols = grid[0].length;

        boolean[][] visited = new boolean[numRows][numCols];
        Coordinate[][] parent = new Coordinate[numRows][numCols];

        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(source);
        visited[source.x][source.y] = true;

        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();

            if (current.x == destination.x && current.y == destination.y) {
                // Reconstruct the path
                while (current != null) {
                    path.add(current);
                    current = parent[current.x][current.y];
                }
                Collections.reverse(path);
                return path;
            }

            // Explore all possible neighbors
            for (int[] direction : DIRECTIONS) {
                int nextX = current.x + direction[0];
                int nextY = current.y + direction[1];

                if (isValidCoordinate(nextX, nextY, grid) && !visited[nextX][nextY]) {
                    queue.add(new Coordinate(nextX, nextY));
                    visited[nextX][nextY] = true;
                    parent[nextX][nextY] = current;

                    // Perform sliding along walls
                    for (Direction slideDirection : SLIDE_DIRECTIONS) {
                        slideAlongWall(grid, new Coordinate(nextX, nextY), slideDirection);
                    }
                }
            }
        }

        return null; // No path found
    }

    // Print the path from start to finish
    public static void printPath(List<Coordinate> path) {
        if (path == null) {
            System.out.println("No path exists from S to F.");
            return;
        }

        for (int i = 0; i < path.size(); i++) {
            Coordinate point = path.get(i);
            if (i == 0) {
                System.out.println((i + 1) + ". Start at (" + (point.y + 1) + ", " + (point.x + 1) + ")");
            } else if (i == path.size() - 1) {
                Coordinate prev = path.get(i - 1);
                String direction = getDirection(point, prev);
                System.out.println((i + 1) + ". Move " + direction + " to (" + (point.y + 1) + ", " + (point.x + 1) + ")");
                System.out.println((i + 2) + ". Done!");
            } else {
                Coordinate prev = path.get(i - 1);
                String direction = getDirection(point, prev);
                System.out.println((i + 1) + ". Move " + direction + " to (" + (point.y + 1) + ", " + (point.x + 1) + ")");
            }
        }
    }

    // Get the direction of movement from previous point to current point
    public static String getDirection(Coordinate current, Coordinate previous) {
        if (current.x == previous.x) {
            if (current.y > previous.y) {
                return "right";
            } else if (current.y < previous.y) {
                return "left";
            } else {
                return "stay";
            }
        } else {
            if (current.x > previous.x) {
                return "down";
            } else {
                return "up";
            }
        }
    }

    // Read the maze from a file
    public static char[][] readMazeFromFile(String fileName) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                lines.add(line);
            }
        }
        scanner.close();

        int rows = lines.size();
        int cols = lines.get(0).length();
        char[][] maze = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = lines.get(i).charAt(j);
            }
        }
        return maze;
    }

    // Find the start point 'S' in the maze
    public static Coordinate findStart(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'S') {
                    return new Coordinate(i, j);
                }
            }
        }
        return null; // Start not found
    }

    // Find the finish point 'F' in the maze
    public static Coordinate findFinish(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'F') {
                    return new Coordinate(i, j);
                }
            }
        }
        return null; // Finish not found
    }

    // Main method
    public static void main(String[] args) {
        try {
            long startTime = System.currentTimeMillis(); // Start timing

            char[][] grid = readMazeFromFile("puzzles/benchmark_series/puzzle_10.txt");
            Coordinate start = findStart(grid);
            Coordinate finish = findFinish(grid);

            List<Coordinate> path = shortestPath(grid, start, finish);
            printPath(path);

            long endTime = System.currentTimeMillis(); // End timing
            long totalTime = endTime - startTime;
            System.out.println("\nRuntime: " + totalTime + " milliseconds");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

}
