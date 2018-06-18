/*
* @Author: caizhe
* @Date:   2018-06-18 10:46:40
* @Last Modified by:   caizhe
* @Last Modified time: 2018-06-18 11:16:46
*/
public class ArrayDisc03 {
    public static int[] insert(int[] arr, int item, int position) {
        int[] result = new int[arr.length + 1];
        position = Math.min(arr.length, position);
        for (int i = 0; i < position; i++) {
            result[i] = arr[i];
        } 
        result[position] = item;
        for (int i = position; i < arr.length; i++) {
            result[i + 1] = arr[i];
        }
        return result;
    }

    public static void reverse(int[] arr) {
        int temp;
        for(int i = 0; i < arr.length / 2; i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    public static int[] replicate(int[] arr) {
        int total = 0;
        for (int item : arr) {
            total = total + item;
        }
        int[] result = new int[total];
        int i = 0;
        for (int item : arr) {
            for (int j = 0; j < item; j++) {
                result[i] = item;
                i++;
            }
        }
        return result;
    } 

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        for (int item : replicate(a)) {
           System.out.println(item);
        }

    }
}