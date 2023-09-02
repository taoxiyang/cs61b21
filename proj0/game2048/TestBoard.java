package game2048;

/**
 * @author qiushui
 * @Date 2023/9/1
 */
public class TestBoard {

    public static void main(String[] args) {
        int[][] before = new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
        };

        Board board = new Board(before,0);
        System.out.println(board.toString());

        Tile tile = board.tile(2,3);
        System.out.println(String.format("col -> %s, row -> %s, value -> %s", tile.col(), tile.row(), tile.value() ));

        int wcol = Side.WEST.col(2,3,board.size());
        int wrow = Side.WEST.row(2,3,board.size());

        tile = board.tile(wcol,wrow);
        System.out.println(String.format("WEST stand col -> %s, stand row -> %s, value -> %s",
                wcol,  wrow, tile.value() ));


        board.setViewingPerspective(Side.WEST);
        tile = board.tile(2,3);
        System.out.println(tile.value());





    }


}
