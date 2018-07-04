public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> stringDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            stringDeque.addLast(word.charAt(i));
        }
        return stringDeque;
    }

    public boolean isPalindrome(String word) {
        if (word.length() <= 1) {
            return true;
        }
        Deque d1 = wordToDeque(word);
        String reverseWord = new StringBuilder(word).reverse().toString();
        Deque d2 = wordToDeque(reverseWord);
        for (int i = 0; i < d1.size(); i++) {
            if (!d1.get(i).equals(d2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindromeRecursive(String word) {
        if (word.length() <= 1) {
            return true;
        }
        else {
            Deque d1 = wordToDeque(word);
            String reverseWord = new StringBuilder(word).reverse().toString();
            Deque d2 = wordToDeque(reverseWord);
            return isPalindromeRecursiveHelper(d1, d2);
        }
    }

    private boolean isPalindromeRecursiveHelper(Deque d1, Deque d2) {
        if (d1.isEmpty()){
            return true;
        }
        else {
            if (d1.removeFirst().equals(d2.removeFirst())) {
                return isPalindromeRecursiveHelper(d1, d2);
            }
            else {
                return false;
            }
        }

    }
}
