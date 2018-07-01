public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> stringDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            stringDeque.addLast(word.charAt(i));
        }
        return stringDeque;
    }
}
