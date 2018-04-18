package hw0;

public class Exercise_2 {
    public static int max(int[] m) {
        int index = 0;
        int curr;
        int max = 0;
        while (index < m.length) {
            curr = m[index];
            if (curr > max) {
                max = curr;
            }
            index += 1;
        }
        return max;
    }
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.print(max(numbers));
    }
}
