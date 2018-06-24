public class HorribleSteve {
    public static void main(String [] args) {
        int i = 0;
        // refractored the code
        int j = 0;
        while(i < 500){
            if (!Flik.isSameNumber(i , j)){
                break; // break exits the for loop!
                // breaks when i = j = 128, don't know why.
            }
            i = i + 1;
            j = j + 1;
        }
        System.out.println("i is " + i);
    }
}
