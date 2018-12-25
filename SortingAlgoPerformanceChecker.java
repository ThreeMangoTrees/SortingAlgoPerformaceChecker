package sortingAnalysis;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Collections;
import java.util.Set;
import java.util.Map;

//import java.util.ArrayList;

public class sort {
	
	private static int comp = 0;
	private static int mov = 0;
	
	public sort() {
		comp = 0;
		mov = 0;
	}
	
	public static void set (int c, int m) {
		comp = c;
		mov = m;
	}
	
	public static int[] get () {
		int[] a = new int[2];
		a[0] = comp;
		a[1] = mov;
		return a;
	}
	
	// -------------------------------- PrintArray --------------------------------
	
	public static void printArray(int[] arr, int n) {
		int i;
		for (i=0; i < n; i++)
			System.out.print(arr[i] + " ");
		System.out.println("");
	}
	
	//  ----------------------------------- Heap Sort --------------------------------
	
	public static int[] heapSort(int arr[], int n)
    {
		comp = 0;
		mov = 0;
        //int n = arr.length;
 
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
			comp += 1;
			heapify(arr, n, i);
		}
		comp += 1;
 
        // One by one extract an element from heap
        for (int i=n-1; i>=0; i--) {
            // Move current root to end
			comp += 1;
			
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            //swap();
			mov += 1;
 
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
		comp += 1;
		
		System.out.println("Result of Heap Sort -- ");
		printArray(arr,n);
		
		int res[] = new int[2];
		res[0] = comp;
		res[1] = mov;
		
		return res;
    }
 
    private static void heapify(int arr[], int n, int i)
    {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left = 2*i + 1
        int r = 2*i + 2;  // right = 2*i + 2
 
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
		comp += 2;
 
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
		comp += 2;
		
        // If largest is not root
        if (largest != i) {
			
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
			//System.out.println(arr[i] + " -- " + arr[largest]);
			mov += 1;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
		comp += 1;
    }
	
	
	//  ----------------------------------- Radix Sort --------------------------------
	
	private static int getMax(int arr[], int n){
        int mx = arr[0];
        for (int i = 1; i < n; i++) {
			comp += 1;
            if (arr[i] > mx) {
                mx = arr[i];
			}
			comp += 1;
		}
		comp += 1;
        return mx;
    }
 
    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
    private static void countSort(int arr[], int n, int exp)
    {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count,0);
 
        // Store count of occurrences in count[]
        for (i = 0; i < n; i++) {
			comp += 1;
            count[ (arr[i]/exp)%10 ]++;
		}
		comp += 1;
 
        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++) {
			comp += 1;
            count[i] += count[i - 1];
		}
		comp += 1;
			 
        // Build the output array
        for (i = n - 1; i >= 0; i--) {
			comp += 1;
			
			//mov += (output[count[ (arr[i]/exp)%10 ] - 1] == arr[i] ? 0 : 1);
            output[count[ (arr[i]/exp)%10 ] - 1] = arr[i];
			
            count[ (arr[i]/exp)%10 ]--;
        }
		comp += 1;
 
        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++) {
			comp += 1;
			mov += (arr[i] == output[i]) ? 0 : 1;
            arr[i] = output[i];
			
		}
		comp += 1;
    }
 
    // The main function to that sorts arr[] of size n using
    // Radix Sort
    public static int[] radixSort(int arr[], int n)
    {
    	comp = 0;
    	mov = 0;
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);
 
        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10) {
			comp += 1;
			countSort(arr, n, exp);
		}
		comp += 1;
		
		System.out.println("Result of radixSort -- ");
		printArray(arr,n);
		
		int res[] = new int[2];
		res[0] = comp;
		res[1] = mov;
		
		return res;
    }
	
	
	//  ----------------------------------- Merge Sort --------------------------------
	
	public static void merge(int arr[], int l, int m, int r) 
	{ 
		// Find sizes of two subarrays to be merged 
		int n1 = m - l + 1; 
		int n2 = r - m; 

		/* Create temp arrays */
		int L[] = new int [n1]; 
		int R[] = new int [n2]; 

		/*Copy data to temp arrays*/
		for (int i=0; i<n1; ++i) {
			comp += 1;
			L[i] = arr[l + i]; 
			//mov += 1;
		}
		comp += 1;
		
		for (int j=0; j<n2; ++j) {
			comp += 1;
			R[j] = arr[m + 1+ j];
			//mov += 1;
		}			
		comp += 1;


		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays 
		int i = 0, j = 0; 

		// Initial index of merged subarry array 
		int k = l; 
		while (i < n1 && j < n2) { 
			comp += 2;
			if (L[i] <= R[j]) { 
				mov += (arr[k] == L[i]) ? 0 : 1;
				arr[k] = L[i]; 
				i++; 
			} else { 
				mov += (arr[k] == R[j]) ? 0 : 1;
				arr[k] = R[j]; 
				j++; 
			} 
			comp += 1;
			k++; 
		} 
		comp += 2;

		while (i < n1) { 
			comp += 1;
			mov += (arr[k] == L[i] ? 0 : 1);
			arr[k] = L[i]; 
			i++; 
			k++; 
		} 
		
		comp += 1;

		while (j < n2) 
		{ 
			comp += 1;
			mov += (arr[k] == R[j] ? 0 : 1);
			arr[k] = R[j]; 
			j++; 
			k++; 
		} 
		comp += 1;
	} 

	public static void mergeSort(int arr[], int l, int r) 
	{ 
		if (l < r) 
		{ 
			int m = (l+r)/2; 

			mergeSort(arr, l, m); 
			mergeSort(arr , m+1, r); 
 
			merge(arr, l, m, r); 
		} 
		comp += 1;
	} 
	
	//  ----------------------------------- Quick Sort --------------------------------
	
	public static int partition(int arr[], int low, int high) 
	{ 
		int pivot = arr[high]; 
		int i = (low-1); // index of smaller element 
		for (int j=low; j<high; j++) 
		{ 
			comp += 1; 
			// If current element is smaller than or 
			// equal to pivot 
			if (arr[j] <= pivot) { 
				i++; 

				// swap(arr[i], arr[j]);
				
				int temp = arr[i]; 
				arr[i] = arr[j]; 
				arr[j] = temp; 
				
				mov += 1;
			} 
			comp += 1;
		}
		comp += 1;		

		int temp = arr[i+1]; 
		arr[i+1] = arr[high]; 
		arr[high] = temp; 
		// swap (arr[i+1], arr[high]);
		
		mov += 1;

		return i+1; 
	} 

	public static void quickSort(int arr[], int low, int high) 
	{ 
		if (low < high) {
			
			/* pi is partitioning index, arr[pi] is 
			now at right place */
			int pi = partition(arr, low, high); 

			// Recursively sort elements before 
			// partition and after partition 
			quickSort(arr, low, pi-1); 
			quickSort(arr, pi+1, high); 
		} 
		comp += 1;
	} 
	
	
	// --------------------------------------- Selection Sort ---------------------------------
	
	public static int[] selectionSort(int[] arr, int n) 
	{  
		comp = 0;
		mov = 0;
		// One by one move boundary of unsorted subarray 
		for (int i = 0; i < n-1; i++) { 
			comp += 1;
			// Find the minimum element in unsorted array 
			int min_idx = i; 
			for (int j = i+1; j < n; j++) {
				comp += 1;
				if (arr[j] < arr[min_idx]) {
					min_idx = j; 
				}
				comp += 1;
			}
			comp += 1;

			// Swap the found minimum element with the first 
			// element 
			int temp = arr[min_idx]; 
			arr[min_idx] = arr[i]; 
			arr[i] = temp; 
			mov += 1;
		}
		comp += 1;
		
		System.out.println("Result of Selection Sort -- ");
		printArray(arr,n);
		
		int res[] = new int[2];
		res[0] = comp;
		res[1] = mov;
		
		return res;
	} 
	
	
	// ---------------------------------------- Insertion Sort --------------------------------------
	
	public static int[] insertionSort(int arr[], int n) { 
		comp = 0;
		mov = 0;
		
		for (int i=1; i<n; ++i) {
			comp += 1;
			int key = arr[i]; 
			int j = i-1; 
			Boolean indicator = false;

			while (j>=0 && arr[j] > key) { 
				comp += 2;
				arr[j+1] = arr[j]; 
				mov += 1;
				j = j-1; 
				indicator = true;
			} 
			comp += 2;
			arr[j+1] = key; 
			
			if (indicator) mov += 1;
		}	
		comp += 1;		
		
		System.out.println("Result of Insertion Sort -- ");
		printArray(arr,n);
		
		int res[] = new int[2];
		res[0] = comp;
		res[1] = mov;
		
		return res;
	} 
	
	
	public static String sortingGame(int arr[], int n) {
		
			int arr_is[] = new int[n];
			int arr_ss[] = new int[n];
			int arr_qs[] = new int[n];
			int arr_ms[] = new int[n];
			int arr_rs[] = new int[n];
			int arr_hs[] = new int[n];
			
			System.arraycopy(arr,0,arr_is,0,n);
			System.arraycopy(arr,0,arr_ss,0,n);
			System.arraycopy(arr,0,arr_qs,0,n);
			System.arraycopy(arr,0,arr_ms,0,n);
			System.arraycopy(arr,0,arr_rs,0,n);
			System.arraycopy(arr,0,arr_hs,0,n);
			
			long begin;
			double elapsedTimeInsertionSort = 0.0;
			double elapsedTimeSelectionSort = 0.0 ;
			double elapsedTimeQuickSort = 0.0 ;
			double elapsedTimeMergeSort = 0.0;
			double elapsedTimeHeapSort = 0.0;
			double elapsedTimeRadixSort = 0.0;
			
			int is[] = new int[2]; 
			int ss[] = new int[2];
			int qs[] = new int[2];
			int ms[] = new int[2];
			int rs[] = new int[2]; 
			int hs[] = new int[2]; 
			
			Map<String, Double> hmap = new HashMap<String, Double>();
			String sortingTech = "";
			
			begin = System.nanoTime();
			is = insertionSort(arr_is, n);
			elapsedTimeInsertionSort = System.nanoTime() - begin;
			sortingTech = "InsertionSort";
			hmap.put(sortingTech, elapsedTimeInsertionSort);
		
			
			begin = System.nanoTime();
			ss = selectionSort(arr_ss, n);
			elapsedTimeSelectionSort = System.nanoTime() - begin;
			sortingTech = "SelectionSort";
			hmap.put(sortingTech, elapsedTimeSelectionSort);
		
			comp = 0;
			mov = 0;
			begin = System.nanoTime();
			quickSort(arr_qs, 0, n-1);
			System.out.println("Result of Quick Sort -- ");
			printArray(arr_qs, n);
			elapsedTimeQuickSort = System.nanoTime() - begin;
			sortingTech = "QuickSort";
			hmap.put(sortingTech, elapsedTimeQuickSort);
			qs[0] = comp;
			qs[1] = mov;
		
			comp = 0;
			mov = 0;
			begin = System.nanoTime();
			mergeSort(arr_ms, 0, n-1);
			System.out.println("Result of Merge Sort -- ");
			printArray(arr_ms, n);
			elapsedTimeMergeSort = System.nanoTime() - begin;
			sortingTech = "MergeSort";
			hmap.put(sortingTech, elapsedTimeMergeSort);
			ms[0] = comp;
			ms[1] = mov;
		
			
			begin = System.nanoTime();
			rs = radixSort(arr_rs, n);
			elapsedTimeHeapSort = System.nanoTime() - begin;
			sortingTech = "RadixSort";
			hmap.put(sortingTech, elapsedTimeRadixSort);
		
			
			begin = System.nanoTime();
			hs = heapSort(arr_hs, n);
			elapsedTimeRadixSort = System.nanoTime() - begin;
			sortingTech = "HeapSort";
			hmap.put(sortingTech, elapsedTimeHeapSort);
			
			String res = "";
			double minValueInMap = (Collections.min(hmap.values()));   // This will return max value in the Hashmap

			for (String s : hmap.keySet()) {
				if(hmap.get(s) == minValueInMap)
					res = s;
			}
        	
			System.out.println("");
			System.out.println("Sorting Technique    " + " Comparisons " + " Movements " + "     Time" );
			System.out.println("");
			System.out.println("Insertion Sort" + "           " + is[0] + "         " + is[1] + "          " + elapsedTimeInsertionSort);
			System.out.println("Selection Sort" + "           " + ss[0] + "         " + ss[1] + "          " + elapsedTimeSelectionSort);
			System.out.println("Quick Sort    " + "           " + qs[0] + "         " + qs[1] + "          " + elapsedTimeQuickSort);
			System.out.println("Merge Sort    " + "           " + ms[0] + "         " + ms[1] + "          " + elapsedTimeMergeSort);
			System.out.println("Radix Sort    " + "           " + rs[0] + "         " + rs[1] + "          " + elapsedTimeRadixSort);
			System.out.println("Heap Sort     " + "           " + hs[0] + "         " + hs[1] + "          " + elapsedTimeHeapSort);
			System.out.println("");
		
		return res;
		
	}
	
	
	public static void inOrder(int size) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = i+1;
		}
		
		String res = sortingGame(a, size);
		System.out.println("The Winner for inOrder Sorting Game is " + res);
		
		//return a;
	}
	
	public static void reverseOrder(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = size-i;
		}
		
		
		String res = sortingGame(arr, size);
		System.out.println("The Winner for reverse Order Sorting Game is " + res);
		//return arr;
	}
	
	public static void almostOrder(int size) {
		int[] arr = new int[size];
		int size_80_percent = (int)0.8*size;
		int i = 0;
		for (i = 0; i < size_80_percent; i++) {
			arr[i] = i;
		}
		
		Random rand = new Random();
		
		for (; i < size; i++) {
			arr[i] = rand.nextInt(size*2) + 1;
		}
		
		String res = sortingGame(arr, size);
		System.out.println("The Winner for Almost Order Sorting Game is " + res);
		
		//return arr;
	}
	
	public static void randomOrder(int size) {
		int[] arr = new int[size];
		int i = 0;
		Random rand = new Random();
		for (; i < size; i++) {
			arr[i] = rand.nextInt(size*2) + 1;
		}
		
		
		String res = sortingGame(arr, size);
		System.out.println("The Winner for Random Order Sorting Game is " + res);
		
	}
	
	
	
	
	// ------------------------------ Main Class --------------------------------
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		System.out.print(" Enter the size of the array -- ");
		int n = reader.nextInt();
		System.out.println("");
		
		System.out.println("There are 4 options to chose the order of the array elements for the Sorting Game -- ");
		System.out.println("   inOrder  	  	 = A");
		System.out.println("   reverseOrder   	 = B");
		System.out.println("   almostOrder    	 = C");
		System.out.println("   randomOrder    	 = D");
		System.out.print("Please chose one of the options from above -- ");
		
		
		
		String array_order_choice = reader.next();
		System.out.println("");
		
		reader.close();
		
		
		System.out.println("Array_order_choice Entered by the user = " + array_order_choice);
		System.out.println("Size of the Array for the Sorting Game = " + n);
		System.out.println("");
		
		int[] arr = new int[n];
		
		if (array_order_choice.equals("A")) {
			inOrder(n);
		} else if (array_order_choice.equals("B")) {
			reverseOrder(n);
		} else if (array_order_choice.equals("C")) {
			almostOrder(n);
		} else if (array_order_choice.equals("D")) {
			randomOrder(n);
		}
		
	}

	 
	 
}
