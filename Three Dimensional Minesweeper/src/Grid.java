//TODO grid spaces: -1 = mine, -2 = viewed, -3 = unassigned, -4 = flagged (102 mines)
import java.util.ArrayList;
import java.util.Random;
import java.util.HashSet;
public class Grid
{
    public static HashSet  <Point> revealedPoints = new HashSet<>();
    public static int[][][] gridArray;  //= new int[8][8][8];
    public Grid(int layer, int row, int column)
    {
        gridArray = new int[8][8][8];
        emptyGridGenerator();
        mineMaker(layer, row, column);
        minePeeperSweeper();
        printGrid();
        System.out.println(gridArray[layer][row][column] + "<---");
        System.out.println(gridArray[layer-1][row][column]);
    }
    private void emptyGridGenerator()
    {
        for (int i = 0; i<8; i++)
        {
            for (int j = 0; j<8; j++)
            {
                for (int k = 0; k<8; k++)
                {
                    gridArray[i][j][k] = -3;
                }
            }
        }
    }
    public void printGrid()
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                for (int k = 0; k < 8; k++)
                {
                    if (gridArray[i][j][k] >= 0) System.out.print(" ");
                    System.out.print(gridArray[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }
    }
    public void mineMaker(int x, int y, int z)
    {
        Random isMine = new Random();
        for (int i = 0; i < 102; i++)
        {
            int mineX = isMine.nextInt(8);
            int mineY = isMine.nextInt(8);
            int mineZ = isMine.nextInt(8);
            System.out.println(x);
            System.out.println(y);
            System.out.println(z);

        if (mineX == x && mineY == y && mineZ == z ||
                (mineX-1 == x && mineY == y && mineZ == z) ||
                (mineX+1 == x && mineY == y && mineZ == z) ||
                (mineX == x && mineY-1 == y && mineZ == z) ||
                (mineX == x && mineY+1 == y && mineZ == z) ||
                (mineX == x && mineY == y && mineZ-1 == z) ||
                (mineX == x && mineY == y && mineZ+1 == z))
            {
                i--;
                continue;
            }
            gridArray[mineX][mineY][mineZ] = -1;
        System.out.println("asdfghjkl;");
        }
    }
    public int minePeeper(int x, int y, int z)
            //drake ahh function name
            //figuring out amnt of mines next to every space
    {
        int mineCount = 0;
        //mine counpf >:)
        if (x < 7 && gridArray[x+1][y][z] == -1) mineCount++;
        if (x > 0 && gridArray[x-1][y][z] == -1) mineCount++;
        if (y < 7 && gridArray[x][y+1][z] == -1) mineCount++;
        if (y > 0 && gridArray[x][y-1][z] == -1) mineCount++;
        if (z < 7 && gridArray[x][y][z+1] == -1) mineCount++;
        if (z > 0 && gridArray[x][y][z-1] == -1) mineCount++;
        gridArray[x][y][z] = mineCount;
        return mineCount;
    }
    public void minePeeperSweeper()
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                for (int k = 0; k < 8; k++)
                {
                    if (gridArray[i][j][k] != -1) minePeeper(i,j,k);
                }
            }
        }
    }
    public static void savedPoints()
    {

    }
    {
}
}
