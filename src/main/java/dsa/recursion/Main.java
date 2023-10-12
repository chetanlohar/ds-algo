package dsa.recursion;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Reverse String: "+reverseString("hello"));
        System.out.println("isPalindrome: "+isPalindrome("racecar"));
        System.out.println("toDecimal: "+toDecimal(233,""));
        System.out.println("Sum of numbers: "+sumOfNumber(10));
        System.out.println("Binary search: "+search(new int[]{1,2,3,4,5,6,7,8,9,10},15));
        System.out.println("Fibonacci series (without memoization): "+fibo(10));

        int N = 4;
        int [][] trust = {{1,3},{1,4},{2,3}};
        int[] indegrees = new int[N + 1];
        int[] outdegrees = new int[N + 1];

        for (int[] relation : trust) {
            outdegrees[relation[0]]++;
            indegrees[relation[1]]++;
        }

        System.out.println(Arrays.toString(indegrees));
        System.out.println(Arrays.toString(outdegrees));

        // Merge Sort
        int [] data = {-5,20,10,3,2,0};
        mergeSort(data,0,data.length-1);
        System.out.println(Arrays.toString(data));

    }

    //  Simple Problem: String reversal
    public static String reverseString(String source) {
        if(source.isEmpty()) return "";
        return reverseString(source.substring(1))+source.charAt(0);
    }

    //  Simple Problem: Palindrome
    public static boolean isPalindrome(String source){
        if(source.isEmpty() || source.length() == 1){
            return true;
        }
        for(int i=0;i<source.length();i++){
            if(source.charAt(0) == source.charAt(source.length()-1)){
                return isPalindrome(source.substring(1,source.length()-1));
            }
        }
        return false;
    }

    // Decimal to Binary
    public static String toDecimal(int number, String result){
        if(number == 0){
            return result;
        }
        result = (number%2) + result;
        return toDecimal(number/2,result);
    }

    // sum of natural numbers
    public static int sumOfNumber(int number) {
        if(number == 1) {
            return 1;
        }
        return sumOfNumber(number-1)+number;
    }

    // binary search
    public static boolean search(int[] sortedArray,int target){
        return binarySearch(sortedArray,0,sortedArray.length-1,sortedArray.length/2,target);
    }

    private static boolean binarySearch(int[] sortedArray, int start, int end, int mid, int target) {
        if(start > end) return false;
        if(target == sortedArray[mid]) {
            return true;
        }
        if(target < mid) {
            return binarySearch(sortedArray,start,mid-1,(start+end)/2,target);
        }
        return binarySearch(sortedArray,mid+1,end,(start+end)/2,target);
    }

    public static long fibo(long n){
        if(n == 0 || n == 1){
            return n;
        }
        //        a.contains()

        return fibo(n-1) + fibo(n-2);
    }


    public static void mergeSort(int [] arr, int start, int end){
        if(start < end) {
            int mid = (start+end)/2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid+1, end);
            merge(arr, start, mid, end);
        }

    }

    public static void merge(int [] arr, int start, int mid, int end){

        int i = start, j = mid+1, k = 0;
        int [] temp = new int[end-start+1];

        while (i <= mid && j <= end) {
            if(arr[i] < arr[j]){
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i<=mid) {
            temp[k++] = arr[i++];
        }

        while (j<=end) {
            temp[k++] = arr[j++];
        }

        for(i=start; i <= end; i++){
            arr[i] = temp[i-start];
        }

    }

}



