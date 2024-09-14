import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationPrevious {
    // TODO: Add any necessary instance variables.
    int[][] grid;

    /** Create N-by-N grid, with all sites initially blocked, 0 means blocked. */
    public PercolationPrevious(int N) {
        // TODO: Fill in this constructor.
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
            }
        }
    }

    /** Open the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        // TODO: Fill in this method.
        // if current position is full, open() does nothing
        // else if top, left, right is full, c.p. will open and full
        // else it opens
        if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
            throw new IndexOutOfBoundsException ("Can not open outside the grid!");
        }

        if (isFull(row, col)) {
            return;
        }

        if (isFull(row - 1, col)) {
            toFull(row, col);
        } else {
            grid[row][col] = 1;
        }
    }

    /** If row or col is out of gird, it is not open.
     *  If grid[row][col] == 1 or 2, it is open. */
    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0 || row >= grid.length || col < 0 || col >= grid.length) {
            return false;
        } else if (grid[row][col] > 0) {
            return true;
        } else {
            return false;
        }
    }

    /** If row or col is out of gird, it is not full, expect row < 0.
     *  If grid[row][col] == 2, it is full. */
    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        if (row < 0) {
            return true;
        } else if (row >= grid.length || col < 0 || col >= grid.length) {
            return false;
        } else if (grid[row][col] == 2) {
            return true;
        } else {
            return false;
        }
    }

    /** Iterates over grid, returns the number of opened sites. */
    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        int counts = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (isOpen(i, j)) {
                    counts ++;
                }
            }
        }
        return counts;
    }

    /** Returns true if the system percolates, that is, there is a full site in the last row. */
    public boolean percolates() {
        // TODO: Fill in this method.
        for (int j = 0; j < grid.length; j++) {
            if (isFull(grid.length - 1, j)) {
                return true;
            }
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

    /** Uses mutual recursion between toFull() and flowAround()
     *  Let liquid flows as far as it can whenever a new site is going to be full. */
    public void toFull(int row, int col) {
        grid[row][col] = 2;
        flowAround(row, col);
    }

    /** Liquid flows, it will fill the open sites adjacent its position. */
    public void flowAround(int row, int col) {
        if (row + 1 < grid.length && grid[row + 1][col] == 1) {
            toFull(row + 1, col);
        }
        if (col + 1 < grid.length && grid[row][col + 1] == 1) {
            toFull(row, col + 1);
        }
        if (col - 1 >= 0 && grid[row][col - 1] == 1) {
            toFull(row, col - 1);
        }
    }
}
