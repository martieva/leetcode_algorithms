//Simplify Path
//Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
class Solution {
    public String simplifyPath(String path) {
        Stack<String> pathStack = new Stack();
        String[] parts = path.split("/");
        for (String part : parts) {
            if ("".equals(part) || ".".equals(part)) {
                continue;
            }
            if ("..".equals(part)) {
                if (!pathStack.empty()) {
                    pathStack.pop();
                }
                continue;
            }
            pathStack.push(part);
            
        }  
        
        StringBuilder sb = new StringBuilder();
        while (!pathStack.empty()) {
            sb.insert(0, pathStack.pop());
            sb.insert(0, "/");
        }

        return sb.length() == 0 ? "/" : sb.toString().trim();
    }
}

//Manipulate a String
//Given a string s of lower and upper case English letters.
// A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
// 0 <= i <= s.length - 2
// s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
// To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.
// Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
class Solution {
    public String makeGood(String s) {
        Stack<Character> stack = new Stack();
        for (char ch : s.toCharArray()) {
            if (!stack.empty()) {
                if (Character.isUpperCase(ch)) {
                    if (Character.toLowerCase(ch) == stack.peek()) {
                        stack.pop();
                        continue;
                    }
                } else {
                    if (Character.toUpperCase(ch) == stack.peek()) {
                        stack.pop();
                        continue;
                    }
                }
            }
            stack.push(ch);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
        
    }
}

//Moving Average
//Given a collection of integers and a window size, calculate the moving average of all integers in the sliding window.
//Implement the MovingAverage class:
//MovingAverage(int size) Initializes the object with the size of the window size.
//double next(int val) Returns the moving average of the last size values of the stream.
/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
 class MovingAverage {
    int size = 0;
    double sum = 0;
    Queue<Integer> queue;

    public MovingAverage(int size) {
        this.size = size;
        queue = new LinkedList();
    }
    
    public double next(int val) {
        sum += val;
        queue.offer(val);
        while (queue.size() > size) {
            sum -= queue.poll();
        }
        return queue.size() == 0 ? 0 : sum / queue.size();
    }
}

//Next Greater Element
// The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
// You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
// For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
// Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> arrayMap = new HashMap();
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[nums1.length];
        
        for (int i = 0; i < nums1.length; i++) {
            arrayMap.put(nums1[i], i);
            answer[i] = -1;
        }
            
        for (int j = 0; j < nums2.length; j++) {
            while (!stack.empty() && nums2[stack.peek()] < nums2[j]) {
                if (arrayMap.containsKey(nums2[stack.peek()])) {
                    answer[arrayMap.get(nums2[stack.peek()])] = nums2[j];
                }
                stack.pop();
            }
            stack.push(j);
        }
        
        return answer;
    }
}

//Stock Span
//Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.
//The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.
//Implement the StockSpanner class:
// StockSpanner() Initializes the object of the class.
// int next(int price) Returns the span of the stock's price given that today's price is price.
/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
 class StockSpanner {
    List<Integer> prices;
    Stack<Integer> stack;

    public StockSpanner() {
        prices = new ArrayList();
        stack = new Stack();
    }
    
    public int next(int price) {
        int lastDay = 0;
        while (!stack.empty() && prices.get(stack.peek() - 1) <= price) {
            stack.pop();
        }
        if (!stack.empty()) {
            lastDay = stack.peek();
        }
        prices.add(price);
        stack.push(prices.size());
        return prices.size() - lastDay;
    }
}

