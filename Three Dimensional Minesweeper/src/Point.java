public class Point
{
    //I mean, what's the point anyways? *canned laughter*
    public int layer;
    public int row;
    public int column;
    public int value;
    public Point(int layer, int row, int column, int value)
    {
        this.layer = layer;
        this.row = row;
        this.column = column;
        this.value = value;
    }
    @Override
    public int hashCode()
    {
        return Integer.parseInt(this.layer+""+this.row+""+this.column);
    }
    @Override
    public boolean equals(Object p)
    {
        Point p1 = (Point) p;
        return this.layer == p1.layer && this.row == p1.row && this.column == p1.column;
    }
    @Override
    public String toString()
    {
        return "["+this.layer+"] ["+this.row+"] {"+this.column+"]\n";
    }
}
