package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class TestGame {
    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        final int WIDTH = 80;
        final int HEIGHT = 30;
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Random r = new Random(2017);
        Game.Position p = new Game.Position(10, 10);
        for (int i = 0; i < 80; i++) {
            Game.drawRoom(world, p, r);
        }

        ter.renderFrame(world);
    }
}
