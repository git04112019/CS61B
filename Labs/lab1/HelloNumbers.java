/*
* @Author: caizhe
* @Date:   2018-04-16 14:09:57
* @Last Modified by:   caizhe
* @Last Modified time: 2018-04-16 14:16:00
*/
public class HelloNumbers{
	public static void main(String[] args){
		int x = 0;
		int sum = 0;
		while (x < 10){
			System.out.print(sum +  " ");
			x += 1;
			sum += x;
		}
		System.out.println();
	}
}