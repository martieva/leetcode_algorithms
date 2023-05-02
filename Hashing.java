//Pangram
//Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.
class Solution {
    public boolean checkIfPangram(String sentence) {
        Set<Character> letters = new HashSet<>();
        
        for (int i = 0; i < sentence.length(); i++) {
            letters.add(sentence.charAt(i));
        }
        
        return letters.size() == 26;
    }
}

//Missing Number
//Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
class Solution {
    public int missingNumber(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numSet.add(nums[i]);
        }
        
        for (int i = 0; i <= numSet.size(); i++) {
            if (!numSet.contains(i)) {
                return i;
            }
        }
        
        return -1;
    }
}

//Count Elements
//Given an integer array arr, count how many elements x there are, such that x + 1 is also in arr. 
//If there are duplicates in arr, count them separately.
class Solution {
    public int countElements(int[] arr) {
        int count = 0;
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            integers.add(arr[i]);
        }
        
        for (Integer integer : integers) {
            if (integers.contains(integer+1)) {
                count++;
            }
        }  
        
        return count;
    }
}

//Find Players With Zero or One Losses
//Given an integer array matches where matches[i] = [winneri, loseri] indicates that the player winneri defeated player loseri in a match.
//Return a list answer of size 2 where:
//answer[0] is a list of all players that have not lost any matches.
//answer[1] is a list of all players that have lost exactly one match.
//The values in the two lists should be returned in increasing order.
class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> loserMap = new HashMap<>();
        for (int[] match: matches) {
            loserMap.put(match[0], loserMap.getOrDefault(match[0], 0));
            loserMap.put(match[1], loserMap.getOrDefault(match[1], 0) + 1);
        }
        List<Integer> onlyWon = new ArrayList<>();
        List<Integer> lostOnce = new ArrayList<>();
        for (int key: loserMap.keySet()) {
            if (loserMap.get(key) == 0) {
                onlyWon.add(key);
            } else if (loserMap.get(key) == 1) {
                lostOnce.add(key);
            }
        }
        Collections.sort(onlyWon);
        Collections.sort(lostOnce);
        List<List<Integer>> result = new ArrayList();
        result.add(onlyWon);
        result.add(lostOnce);
        return result;
    }
}

//Largest Unique Number
//Given an integer array nums, return the largest integer that only occurs once. If no integer occurs once, return -1.
class Solution {
    public int largestUniqueNumber(int[] nums) {
        Map<Integer, Integer> counts = new HashMap();
        for (int num: nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        
        int largestSingle = -1;
        for (int key: counts.keySet()) {
            if (counts.get(key) == 1) {
                largestSingle = Math.max(largestSingle, key);
            }
        }
        return largestSingle;
    }
}

//Maximum Number of Baloons
//Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
//You can use each character in text at most once. 
//Return the maximum number of instances that can be formed.
class Solution {
    public int maxNumberOfBalloons(String text) {
        Map<Character, Integer> letterCount = new HashMap();
        String balloon = "balon";
        for (int i = 0; i < text.length(); i++) {
            if (balloon.indexOf(text.charAt(i))>-1) {
                letterCount.put(text.charAt(i), letterCount.getOrDefault(text.charAt(i), 0) + 1);
            }
        }
        
        int lCount = letterCount.getOrDefault('l', 0);
        int oCount = letterCount.getOrDefault('o', 0);
        if (letterCount.size() != 5 || lCount == 1 || oCount == 1) {
            return 0;
        }
        
        if (lCount % 2 != 0) {
            lCount--;
        }
        if (oCount % 2 != 0) {
            oCount--;
        }
        letterCount.put('l', lCount/2);
        letterCount.put('o', oCount/2);
        int minCount = -1;
        for (char key: letterCount.keySet()) {
            if (minCount == -1) {
                minCount = letterCount.get(key);
            } else {
                minCount = Math.min(minCount, letterCount.get(key));   
            }
        }
        return minCount;
    }
}

//Ransom Note
//Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
//Each letter in magazine can only be used once in ransomNote.
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> magazineMap = new HashMap();
        for (char magazineLetter : magazine.toCharArray()) {
            magazineMap.put(magazineLetter, magazineMap.getOrDefault(magazineLetter, 0) + 1);
        }
        
        for (char ransomLetter : ransomNote.toCharArray()) {
            if (magazineMap.getOrDefault(ransomLetter, 0) > 0) {
                magazineMap.put(ransomLetter, magazineMap.get(ransomLetter) - 1);
            } else {
                return false;
            }
        }
        return true;
    }
}

//Jewels and Stones
//You're given strings jewels representing the types of stones that are jewels, and stones representing the stones you have. Each character in stones is a type of stone you have. You want to know how many of the stones you have are also jewels.
//Letters are case sensitive, so "a" is considered a different type of stone from "A".
class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Map<Character, Integer> groupedStones = new HashMap();
        for (char stone : stones.toCharArray()) {
            groupedStones.put(stone, groupedStones.getOrDefault(stone, 0) + 1);
        }
        
        int ownedJewels = 0;
        for (char jewel : jewels.toCharArray()) {
            ownedJewels += groupedStones.getOrDefault(jewel, 0);
        }
        return ownedJewels;
    }
}

//Longest substring without repeating characters
//Given a string s, find the length of the longest substring without repeating characters.
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charMap = new HashMap();
        int left = 0;
        int longestSubstring = 0;
        int currentLength = 0;
        char[] characters = s.toCharArray();
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            currentLength++;
            
            while (charMap.containsKey(rightChar)) {
                char leftChar = s.charAt(left);
                charMap.put(leftChar, charMap.get(leftChar) - 1);
                if (charMap.get(leftChar) == 0) {
                    charMap.remove(leftChar);
                }
                left++;
                currentLength--;
            }
            
            charMap.put(rightChar, charMap.getOrDefault(rightChar, 0) + 1);
            longestSubstring = Math.max(longestSubstring, currentLength);
            
        }
        
        return longestSubstring;
    }
}

