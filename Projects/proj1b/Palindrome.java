public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> stringDeque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            stringDeque.addLast(word.charAt(i));
        }
        return stringDeque;
    }

    /** Returns true if one word is a Palindrome. */
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

    /** Recursive solution to isPalindrome. */
    public boolean isPalindromeRecursive(String word) {
        if (word.length() <= 1) {
            return true;
        }
        else {
            Deque d1 = wordToDeque(word);
            String reverseWord = new StringBuilder(word).reverse().toString();
            Deque d2 = wordToDeque(reverseWord);
            // Only need to check half of the word.
            int checkLength = word.length() / 2;
            return isPalindromeRecursiveHelper(d1, d2, checkLength);
        }
    }

    private boolean isPalindromeRecursiveHelper(Deque d1, Deque d2, int checkLength) {
        if (checkLength <= 0){
            return true;
        }
        else {
            if (d1.removeFirst().equals(d2.removeFirst())) {
                return isPalindromeRecursiveHelper(d1, d2, checkLength - 1);
            }
            else {
                return false;
            }
        }
    }

    /** Returns true if one word is a Palindrome according to CharacterComparator cc, false otherwise. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        }
        else {
            Deque d1 = wordToDeque(word);
            String reverseWord = new StringBuilder(word).reverse().toString();
            Deque d2 = wordToDeque(reverseWord);
            // Only need to check half of the word.
            int checkLength = word.length() / 2;
            return isPalindromeHelper(d1, d2, cc, checkLength);
        }
    }

    private boolean isPalindromeHelper(Deque d1, Deque d2, CharacterComparator cc, int checkLength) {
        if (checkLength <= 0) {
            return true;
        }
        else {
            if (cc.equalChars((char) d1.removeFirst(), (char) d2.removeFirst())) {
                return isPalindromeHelper(d1, d2, cc, checkLength - 1);
            }
            else {
                return false;
            }
        }
    }
}
