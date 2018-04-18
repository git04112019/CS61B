package hw0;

public class Exercise_1a {
    public static void main(String[] args) {
        int star_num;
        int line = 1;
        while (line <= 5) {
            star_num = line;
            while (star_num > 0){
                System.out.print("*");
                star_num -= 1;
            }
            System.out.println();
            line += 1;
        }
    }
}
