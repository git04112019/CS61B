package hw0;

public class Exercise_1b {
    public static void drawTriangle(int N) {
        int star_num;
        int line = 1;
        while (line <= N) {
            star_num = line;
            while (star_num > 0) {
                System.out.print("*");
                star_num -= 1;
            }
            System.out.println();
            line += 1;
        }
    }
    public static void main(String[] args) {
        drawTriangle(10);
    }
}
