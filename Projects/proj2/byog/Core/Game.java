package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.HexWorld;

import java.util.Random;


public class Game {


    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;


    protected static class Position{
        int x;
        int y;

        Position(int posX, int posY) {
            x = posX;
            y = posY;
        }
    }

//    /**
//     * Gets a Random according to the seed that player sets.
//     * */
//    private static Random getRandom(long seed) {
//        Random RANDOM = new Random(seed);
//        return RANDOM;
//    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }

    /**
     * Draws rooms in the world
     * @param world the world to draw on
     * @param p the bottom left coordinate of the room
     * @param RANDOM the pseudorandom instance to produce next int
     */
    public static void drawRoom(TETile[][] world, Position p, Random RANDOM) {
        int roomWidth = 0;
        int roomHeight = 0;
        while (roomWidth < 3 || roomHeight < 3){
            roomWidth = RANDOM.nextInt(10) * (int) Math.pow(-1, RANDOM.nextInt());
            roomHeight = RANDOM.nextInt(10) * (int) Math.pow(-1, RANDOM.nextInt());
        }

        if (checkRoom(world, p, roomWidth, roomHeight)) {

            // p is always the bottom left coordinate of the room
            if(roomWidth < 0) {
                p.x = p.x + roomWidth;
                roomWidth = Math.abs(roomWidth);
            }
            if(roomHeight < 0) {
                p.y = p.y + roomHeight;
                roomHeight = Math.abs(roomHeight);
            }

            // Draws wall
            for (int i = p.x; i < p.x + roomWidth; i++){
                world[i][p.y] = Tileset.WALL;
                world[i][p.y + roomHeight - 1] = Tileset.WALL;
            }
            for (int j = p.y; j < p.y + roomHeight; j++) {
                world[p.x][j] = Tileset.WALL;
                world[p.x + roomWidth - 1][j] = Tileset.WALL;
            }

            // Draws floor
            for (int i = p.x + 1; i < p.x + roomWidth - 1; i++) {
                for (int j = p. y + 1; j < p.y + roomHeight - 1; j++) {
                    world[i][j] = Tileset.FLOOR;
                }
            }

        }
        p.x = RANDOM.nextInt(WIDTH);
        p.y = RANDOM.nextInt(HEIGHT);
    }

    /**
     * Checks whether the next room can be drawn from Position p
     * @param world the world to draw on
     * @param p the bottom left coordinate of the next room
     * @param roomWidth the width of the room to draw
     * @param roomHeight the height of the room to draw
     */
    public static boolean checkRoom(TETile[][] world, Position p, int roomWidth, int roomHeight) {
        boolean flag = true;

        // Checks space
        if (p.x + roomWidth < 0 || p.x + roomWidth > WIDTH || p.y + roomHeight < 0 || p.y + roomHeight > HEIGHT) {
            flag = false;
        }

        // Checks overlapping
        if (flag) {
            OUTER:
            for (int i = Math.min(p.x, p.x + roomWidth); i < Math.max(p.x, p.x + roomWidth); i++) {
                for (int j = Math.min(p.y, p.y + roomHeight); j < Math.max(p.y, p.y + roomHeight); j++) {
                    if (!(world[i][j].equals(Tileset.NOTHING))) {
                        flag = false;
                        break OUTER;
                    }
                }
            }
        }
        return flag;
    }

//    public static void main(String[] args) {
//        TERenderer ter = new TERenderer();
//        ter.initialize(WIDTH, HEIGHT);
//
//        TETile[][] world = new TETile[WIDTH][HEIGHT];
//        for (int x = 0; x < WIDTH; x++) {
//            for (int y = 0; y < HEIGHT; y++) {
//                world[x][y] = Tileset.NOTHING;
//            }
//        }
//
//        Random r = new Random(2017);
//        Position p = new Position(10, 10);
//        for (int i = 0; i < 80; i++) {
//            drawRoom(world, p, r);
//        }
//
//        ter.renderFrame(world);
//    }

}
