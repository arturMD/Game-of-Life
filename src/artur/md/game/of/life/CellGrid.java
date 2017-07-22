package artur.md.game.of.life;

/**
 * Contains all cells located on torus.
 */

public class CellGrid {
    private Cell[][] grid;
    private int rows;
    private int columns;


    CellGrid() {
        rows = 0;
        columns = 0;
        grid = null;
    }

    /**
     * Contructs a CellGrid.
     *
     * @param rows number of rows
     * @param columns number of columnss
     */
    CellGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new Cell[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void setCell(int x, int y, boolean val) {
        grid[x][y].set(val);
    }

    public boolean isEnabled(int row, int cell) {
        return grid[row][cell].isEnabled();
    }


    /**
     * Computes next generation pattern
     */
    public void next() {
        Cell[][] buffer = new Cell[2][columns]; // Stores unchanged rows
        Cell[] bufferFirstRow = copyRow(grid[0]);
        buffer[0] = copyRow(grid[rows-1]);
        buffer[1] = copyRow(grid[0]);
        int sum;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                sum = buffer[0][ mod(j-1,columns) ].value();  //TODO change all % to mod for readability
                sum+=        buffer[0][ j % columns ].value();
                sum+=        buffer[0][ (j+1) % columns ].value();
                sum+=        buffer[1][ mod(j-1, columns) ].value();
                sum+=        buffer[1][ (j+1) % columns ].value();
                if( i == rows -1 ) {
                    sum += bufferFirstRow[ mod(j-1, columns) ].value();
                    sum +=        bufferFirstRow[ j % columns ].value();
                    sum +=        bufferFirstRow[ (j+1) % columns ].value();
                } else {
                    sum += grid[ (i+1) % rows ][ mod(j-1, columns) ].value();
                    sum+=        grid[ (i+1) % rows ][ j % columns ].value();
                    sum+=        grid[ (i+1) % rows ][ (j+1) % columns ].value();
                }
                grid[i][j].update(sum);

            }
            buffer[0] = copyRow(buffer[1]);
            buffer[1] = copyRow(grid[(i + 1) % columns]);
        }
    }


    /***
     * Modulo operator that returns values from 0 to b-1
     * @return non-negative modulo
     */

    private int mod(int a, int b) {
        int result = a % b;
        if(result < 0)
            result += b;
        return result;
    }

    private Cell[] copyRow(Cell[] r) {
        Cell[] copy = new Cell[columns];
        for(int i = 0; i < columns; i++) {
            copy[i] = new Cell(r[i]);
        }
        return copy;
    }


    public String toString() {
        StringBuilder output = new StringBuilder(rows * columns +columns);
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(grid[i][j].isEnabled()) {
                    output.append("*");
                } else {
                    output.append(".");
                }
            }
            output.append("\n");
        }
        return output.toString();
    }

}
