package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.HexWorld;
import edu.princeton.cs.algs4.RunLength;
import javafx.geometry.Pos;

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

        long seed;

        if (input.toLowerCase().contains("n") && input.toLowerCase().contains("s")) {
            int start = input.toLowerCase().indexOf("n") + 1;
            int end = input.toLowerCase().indexOf("s");
            try {
                seed = Long.parseLong(input.substring(start, end));
            } catch(Exception e) {
                throw new RuntimeException("Seed has to be an integer but you input: \"" + input.substring(start, end) + "\"");
            }
        }

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }

    public static void drawMenu() {

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

    /**
     * Draws hallways in the world
     */
    public static void drawHallway(TETile[][] world, Random RANDOM) {
        Position p = new Position(RANDOM.nextInt(WIDTH), RANDOM.nextInt(HEIGHT));

        while (!(world[p.x][p.y].equals(Tileset.WALL)) || p.x < 3 || p.y < 3 || p.x > WIDTH - 3 || p.y > HEIGHT - 3) {
            p.x = RANDOM.nextInt(WIDTH - 1);
            p.y = RANDOM.nextInt(HEIGHT - 1);

            // Hallways cannot be on the side.
            if(p.x < 3 || p.y < 3 || p.x > WIDTH - 3 || p.y > HEIGHT - 3) {
                continue;
            }

            // Some walls on the corner and so on.
            if (!checkHallway(world, p)) {
                continue;
            }
        }

        int dir = setDirectionOfHallway(world, p);

        int count = 0;

        switch (dir) {
            case 1:
                while (p.y < HEIGHT - 2 && !checkConnected(world, p, 1)) {
                    world[p.x - 1][p.y] = Tileset.WALL;
                    world[p.x + 1][p.y] = Tileset.WALL;
                    world[p.x][p.y] = Tileset.FLOOR;
                    p.y += 1;
                    count++;
                    if (count >= 15) {
                        break;
                    }
                }
                world[p.x][p.y] = Tileset.WALL;
                world[p.x - 1][p.y] = Tileset.WALL;
                world[p.x + 1][p.y] = Tileset.WALL;
                break;
            case 2:
                while (p.x < WIDTH - 2 && !checkConnected(world, p, 2)) {
                    world[p.x][p.y + 1] = Tileset.WALL;
                    world[p.x][p.y - 1] = Tileset.WALL;
                    world[p.x][p.y] = Tileset.FLOOR;
                    p.x += 1;
                    count++;
                    if (count >= 15) {
                        break;
                    }
                }
                world[p.x][p.y] = Tileset.WALL;
                world[p.x][p.y + 1] = Tileset.WALL;
                world[p.x][p.y - 1] = Tileset.WALL;
                break;
            case 3:
                while (p.y > 1 && !checkConnected(world, p, 3)) {
                    world[p.x + 1][p.y] = Tileset.WALL;
                    world[p.x - 1][p.y] = Tileset.WALL;
                    world[p.x][p.y] = Tileset.FLOOR;
                    p.y -= 1;
                    count++;
                    if (count >= 15) {
                        break;
                    }

                }
                world[p.x][p.y] = Tileset.WALL;
                world[p.x - 1][p.y] = Tileset.WALL;
                world[p.x + 1][p.y] = Tileset.WALL;
                break;
            case 4:
                while (p.x > 1 && !checkConnected(world, p, 4)) {
                    world[p.x][p.y + 1] = Tileset.WALL;
                    world[p.x][p.y - 1] = Tileset.WALL;
                    world[p.x][p.y] = Tileset.FLOOR;
                    p.x -= 1;
                    count++;
                    if (count >= 15) {
                        break;
                    }
                }
                world[p.x][p.y] = Tileset.WALL;
                world[p.x][p.y + 1] = Tileset.WALL;
                world[p.x][p.y - 1] = Tileset.WALL;
                break;
        }
    }

    /**
     * Checks whether Position p is capable to construct a hallway. */
    private static boolean checkHallway(TETile[][] world, Position p) {
        // There must be at least one floor in four directions to construct one hallway.
        if (world[p.x + 1][p.y].equals(Tileset.FLOOR) || world[p.x - 1][p.y].equals(Tileset.FLOOR) ||
                world[p.x][p.y + 1].equals(Tileset.FLOOR) || world[p.x][p.y - 1].equals(Tileset.FLOOR)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Sets the direction of the hallway.
     * 1 means up
     * 2 means right
     * 3 means down
     * 4 means left
     * */
    private static int setDirectionOfHallway(TETile[][] world, Position p) {
        int dir = 0;
        if (world[p.x][p.y - 1].equals(Tileset.FLOOR)) {
            dir = 1;
        }
        if (world[p.x - 1][p.y].equals(Tileset.FLOOR)) {
            dir = 2;
        }
        if (world[p.x][p.y + 1].equals(Tileset.FLOOR)) {
            dir = 3;
        }
        if (world[p.x + 1][p.y].equals(Tileset.FLOOR)) {
            dir = 4;
        }
        return dir;
    }

    /**
     * Checks if the hallway has connected two rooms or not.
     */
    private static boolean checkConnected(TETile[][] world, Position p, int dir) {
        boolean connected = false;

        switch (dir) {
            case 1:
                if (world[p.x - 1][p.y].equals(Tileset.FLOOR) || world[p.x + 1][p.y].equals(Tileset.FLOOR) ||
                        world[p.x][p.y + 1].equals(Tileset.FLOOR)) {
                    connected = true;
                }
                break;
            case 2:
                if (world[p.x][p.y + 1].equals(Tileset.FLOOR) || world[p.x][p.y - 1].equals(Tileset.FLOOR) ||
                        world[p.x + 1][p.y].equals(Tileset.FLOOR)) {

                    connected = true;
                }
                break;
            case 3:
                if (world[p.x - 1][p.y].equals(Tileset.FLOOR) || world[p.x + 1][p.y].equals(Tileset.FLOOR) ||
                        world[p.x][p.y - 1].equals(Tileset.FLOOR)) {
                    connected = true;
                }
                break;
            case 4:
                if (world[p.x][p.y + 1].equals(Tileset.FLOOR) || world[p.x][p.y - 1].equals(Tileset.FLOOR) ||
                        world[p.x - 1][p.y].equals(Tileset.FLOOR)) {
                    connected = true;
                }
        }

        return connected;
    }

    public static void removeWall(TETile[][] world) {
        Position[] pShouldBeRemoved = new Position[500];
        int index = 0;

        for (int i = 2; i < WIDTH - 2; i++) {
            for (int j = 2; j < HEIGHT - 2; j++) {
                Position p = new Position(i,j);
                Position rightOfP = new Position(p.x + 1, p.y);
                Position lowerOfP = new Position(p.x, p.y - 1);
                Position leftOfP = new Position(p.x - 1, p.y);
                Position upperOfP = new Position(p.x, p.y + 1);
                if (wallShouldBeRemoved(world, p)) {
                    if ((wallShouldBeRemoved(world, upperOfP) && wallShouldBeRemoved(world, lowerOfP)) ||
                            (wallShouldBeRemoved(world, leftOfP) && wallShouldBeRemoved(world, rightOfP))) {
                        continue;
                    }
                    if ((isPeninsulaWall(world, upperOfP)) || isPeninsulaWall(world, rightOfP) ||
                            isPeninsulaWall(world, lowerOfP) || isPeninsulaWall(world, leftOfP)) {
                        continue;
                    }
//                    world[p.x][p.y] = Tileset.FLOOR;

                    pShouldBeRemoved[index] = p;
                    index++;

                }
            }
        }

        for (int i = 0; i < index; i++) {
            Position p = pShouldBeRemoved[i];
            world[p.x][p.y] = Tileset.FLOOR;
        }
    }

    public static boolean wallShouldBeRemoved(TETile[][] world, Position p) {
        if ((world[p.x + 1][p.y].equals(Tileset.FLOOR) && world[p.x - 1][p.y].equals(Tileset.FLOOR) &&
                world[p.x][p.y + 1].equals(Tileset.WALL) && world[p.x][p.y - 1].equals(Tileset.WALL)) ||
                (world[p.x][p.y + 1].equals(Tileset.FLOOR) && world[p.x][p.y - 1].equals(Tileset.FLOOR) &&
                world[p.x + 1][p.y].equals(Tileset.WALL) && world[p.x - 1][p.y].equals(Tileset.WALL))) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isPeninsulaWall(TETile[][] world, Position p) {
        if (!world[p.x][p.y].equals(Tileset.FLOOR)) {
            return false;
        }
        int count = 0;
        if (world[p.x][p.y + 1].equals(Tileset.FLOOR)) {
            count += 1;
        }
        if (world[p.x][p.y - 1].equals(Tileset.FLOOR)) {
            count += 1;
        }
        if (world[p.x + 1][p.y].equals(Tileset.FLOOR)) {
            count += 1;
        }
        if (world[p.x - 1][p.y].equals(Tileset.FLOOR)) {
            count += 1;
        }
        if (count == 3) {
            return true;
        }
        return false;
    }

    public static void drawLockedDoor(TETile[][] world, Random Random) {
        Position p = new Position(Random.nextInt(WIDTH - 1), Random.nextInt(HEIGHT - 1));
        boolean flag = false;

        //check if Position p can be set as a locked door
        while (!flag) {
            if (p.x - 1 < 0 || p.y - 1 < 0) {
                continue;
            }
            int numOfFloor = 0, numOfWall = 0, numOfBlank = 0;
            for (int i = p.x - 1; i <= p.x + 1; i++) {
                for (int j = p.y - 1; j <= p.y + 1; j++) {
                    if (world[i][j].equals(Tileset.FLOOR)){
                        numOfFloor += 1;
                    }
                    if (world[i][j].equals(Tileset.WALL)) {
                        numOfWall += 1;
                    }
                    numOfBlank += 1;
                }
            }
            if ((numOfFloor == 1 && numOfWall == 5) || (numOfFloor == 2 && numOfWall == 4) || (numOfFloor == 3 && numOfWall == 3)) {
                flag = true;
                world[p.x][p.y] = Tileset.LOCKED_DOOR;
            }
            else {
                p = new Position(Random.nextInt(WIDTH - 1), Random.nextInt(HEIGHT - 1));
            }
        }

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
