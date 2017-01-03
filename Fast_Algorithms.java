/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fast_algorithms;
// todo add org.apache.commons.lang.mutable.MutableInt

/**
 *
 * @author dsaelman
 */
public class Fast_Algorithms {

    public static final int NUMBER_ASCII_CHARS = 256;
    public static final int NUMBER_ASCII_NUMERALS = 10;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            int[]A = new int[] {1,2,3,4,5,6,7,8};
            ShuffleElements(A);
        
            Boolean result = IsAnagram("my first test", "first my test");
            result = IsAnagram("bbaaccddeeffgg", "bbaaccddeeffhh");

            StringBuilder outputStr = new StringBuilder();
            result = ReturnUniqueSymbols("bbaaccddeeffgg",  outputStr);
            int[] inputArray = new int[] { 2, 2,7,6,5,4,8,4,4 };

            outputStr.setLength(0);
            
            // we want to minimize the amount of times we resize
            // NUMBER_ASCII_NUMERALS is an upperbounds for the capacity
            outputStr.setLength(NUMBER_ASCII_NUMERALS);

            result = ReturnUniqueNumericalSymbols(inputArray,  outputStr);
            int length = 0;
            result = ReturnUniqueNumericalSymbols(inputArray,  length);
            
            inputArray = new int[] { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
            
            length = LongestIncreasingSubsequenceLength(inputArray);
            

    }
    
     public static Boolean IsAnagram(String str1, String str2)
     {
            // assuming we only have ascii characters and we are limiting the number of any one character to 2^32-1
            int[] lookUp1 = new int[NUMBER_ASCII_CHARS];
            int[] lookUp2 = new int[NUMBER_ASCII_CHARS];

            int len1 = str1.length();
            int len2 = str2.length();

            // lengths have to be equal
            if (len1 != len2)
                return false;

            // tabulate our lookup tables for each string
            for (int i = 0; i < len1; i++)
            {
                lookUp1[(byte)str1.charAt(i)]++;
                lookUp2[(byte)str2.charAt(i)]++;
            }

            // if the strings are anagrams than the char counts are equal
            for (int i = 0; i < NUMBER_ASCII_CHARS; i++)
            {
                if (lookUp1[i] != lookUp2[i])
                    return false;
            }

            // it passes the test
            return true;
        }
     
        // in c# JAVA we can use a StringBuilder data structure, for C++ we use an unordered_set
        // O[n] function rountine
        public static Boolean ReturnUniqueSymbols(String in_str, StringBuilder outputStr)
        {
            // assuming we only have ascii characters and we are limiting the number of any one character to 2^32-1
            // if we assume only ascii numeric cahracters, we can limit the size of our lookup tables to 4* 10 40 Bytes!
            int[] lookUp1 = new int[NUMBER_ASCII_CHARS];

            // we peroform one pass of the string O[n]
            for (int i = 0; i < in_str.length(); i++)
            {
                if (lookUp1[(int)in_str.charAt(i)] == 0) // first occurance of the symbol
                {
                    // add the char to the output
                    outputStr.append(in_str.charAt(i));
                    // don't use this character again
                    ++(lookUp1[(int)in_str.charAt(i)]);
                }
            }

            if (outputStr.length() == 0)
                return false;

            return true;
        }
        
               // in c# JAVA we can use a StringBuilder data structure, for C++ we use an unordered_set
        static Boolean ReturnUniqueNumericalSymbols(int[] in_str,  StringBuilder outputStr)
        {
            // assuming we only have ascii characters and we are limiting the number of any one character to 2^32-1
            int[] lookUp1 = new int[NUMBER_ASCII_NUMERALS];

            // we peroform one pass of the string O[n]
            for (int i = 0; i < in_str.length; i++)
            {
                if (lookUp1[(int)in_str[i]] == 0) // first occurance of the symbol
                {
                    // add the char to the output
                    outputStr.append(in_str[i]);
                    // don't use this character again
                    ++(lookUp1[(int)in_str[i]]);
                }
            }

            if (outputStr.length() == 0)
                return false;

            // this copy is O[n] but we do it once at the end
            return true;
        }
        
        // this algorithm does not use an output StringBuilder
        // if we can overwrite the orignal input array
        static Boolean ReturnUniqueNumericalSymbols(int[] in_str,  int outputLength)
        {
            // assuming we only have ascii characters and we are limiting the number of any one character to 2^32-1
            int[] lookUp1 = new int[NUMBER_ASCII_NUMERALS];
            outputLength = 0;

            // we peroform one pass of the string O[n]
            for (int i = 0; i < in_str.length; i++)
            {
                if (lookUp1[in_str[i]] == 0) // first occurance of the symbol
                {
                    // add the char to the output
                    in_str[outputLength++] = in_str[i];
                    // don't use this character again
                    ++(lookUp1[(int)in_str[i]]);
                }
            }

            if (outputLength == 0)
                return false;

            // this copy is O[n] but we do it once at the end
            return true;
        }
        
         // returns the character which occurs the maximum times
        // returns the number of times it appears
        // O[n] - 1 scan to build lookup table, 1 scan to find max occurance
        static int ReturnGMaxCharOccurance(String in_str, char maxChar)
        {
            int length = in_str.length();
            if (length == 0)
                return 0;

            int[] lookUp1 = new int[NUMBER_ASCII_CHARS];

            // build lookup table
            for (int i = 0; i < length; i++)
                 lookUp1[in_str.charAt(i)]++;

            // find character with max occurance
            int max = 1;

            for (int i = 0; i < NUMBER_ASCII_CHARS; i++)
            {
                if (lookUp1[i] > max)
                {
                    max = lookUp1[i];
                    maxChar = (char)i;
                }
            }
            return max;
        }
        
        static int LongestIncreasingSubsequenceLength(int[]inputArr)
        {
            int len = inputArr.length;
            if (len == 0)
                return 0;
            int[] tail = new int[len];
            // the minimum length will be 1
            int subseqLen = 1;
            tail[0] = inputArr[0];
            for (int i =0; i<len; i++)
            {
                if (inputArr[i] < tail[0])                
                    tail[0] = inputArr[i]; // new smallest value
                else if (inputArr[i] > tail[subseqLen-1])           
                    tail[subseqLen++] = inputArr[i];  //  v[i] extends largest subsequence
                else
                {
                    // v[i] will become end candidate of an existing subsequence or
                    // Throw away larger elements in all LIS, to make room for upcoming grater elements than v[i]
                    // (and also, v[i] would have already appeared in one of LIS, identify the location and replace it)
                    tail[CeilIndex(tail, -1, subseqLen-1, inputArr[i])] = inputArr[i];                   
                }
            } 
            return 1;
        }
        
        // Binary search (note boundaries in the caller)
        public static int CeilIndex(int[]v, int l, int r, int key) 
        {
            while (r-l > 1) 
            {
                int m = l + (r-l)/2;
                if (v[m] >= key)
                    r = m;
                else
                    l = m;
            }
              return r;
        }
        
        // O[n] based on Fisher Yates algorithm
        public static void ShuffleElements(int[]A)
        {
            int n = A.length;
            while(n >0)
            {
                int i = (int)Math.floor(Math.random()*n--);
                
                // swap with current element (starting from back of array
                // And swap it with the current element (starting frm back of array.
                int tmp = A[n];
                A[n] = A[i];
                A[i] = tmp;
            }
        }
        
        

}
