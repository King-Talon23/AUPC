package TK.f_AUPC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class typo {

    static long X = 130941;
    static long X_INV = 111699163;
    static long MOD = 1000000007;

    static long computeWordHash(String word) {
        long hash = 0;
        for (int i = word.length() - 1; i >= 0; --i) {
            char letter = word.charAt(i);
            hash = letter + (X * hash);
            hash %= MOD;
        }
        return hash;
    }

    static void computeWordHashes(List<String> words, Map<String, Long> wordToHash,
                                   Map<Long, List<Integer>> hashToWords) {
        for (int i = 0; i < words.size(); ++i) {
            long hash = computeWordHash(words.get(i));
            wordToHash.put(words.get(i), hash);
            hashToWords.computeIfAbsent(hash, k -> new ArrayList<>()).add(i);
        }
    }

    static boolean isPresent(long hashWithoutLetter, int i, String word,
                             Map<Long, List<Integer>> hashToWords, List<String> words) {
        if (!hashToWords.containsKey(hashWithoutLetter))
            return false;

        String wordWithoutLetter = word.substring(0, i) + word.substring(i + 1);
        for (int wordIndex : hashToWords.get(hashWithoutLetter)) {
            if (wordWithoutLetter.equals(words.get(wordIndex)))
                return true;
        }
        return false;
    }

    static void typo(List<String> words) {
        Map<String, Long> wordToHash = new HashMap<>();
        Map<Long, List<Integer>> hashToWords = new HashMap<>();

        computeWordHashes(words, wordToHash, hashToWords);
        boolean typos = false;

        for (String word : words) {
            long wordHash = wordToHash.get(word);
            long partialHash = 0;
            long XPower = 1;

            for (int i = 0; i < word.length(); ++i) {
                char letter = word.charAt(i);

                long nextPartialHash = partialHash + (letter * XPower);
                nextPartialHash %= MOD;

                long hashWithoutLetter = (wordHash - nextPartialHash + MOD) % MOD;
                hashWithoutLetter *= X_INV;
                hashWithoutLetter %= MOD;
                hashWithoutLetter += partialHash;
                hashWithoutLetter %= MOD;

                if (isPresent(hashWithoutLetter, i, word, hashToWords, words)) {
                    System.out.println(word);
                    typos = true;
                    break;
                }

                partialHash = nextPartialHash;
                XPower = (XPower * X) % MOD;
            }
        }
        if (!typos) {
            System.out.println("NO TYPOS");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        List<String> words = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            words.add(reader.readLine());
        typo(words);
        reader.close();
    }
}