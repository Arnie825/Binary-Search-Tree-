import java.util.*;

import java.io.*;

import java.nio.charset.Charset;

import java.nio.file.FileSystems;

import java.nio.file.Files;

import java.nio.file.Path;

import java.nio.file.Paths;

  

// ExternalSort class

public class ExternalSort

{

public static int lo=0;

public static List<Integer> lines=new ArrayList<Integer>();

public static Integer[] inputArray;

  

//Path extsort method for sorting

public static Path extsort(Path t1, int n) throws IOException

{

int low = lo;

int high = n;

Scanner scanner = new Scanner(t1, "UTF-8");

//Taking file to be read in scanner object by path class

String word;

while(scanner.hasNext())

//loop to take input from file

{

word=scanner.next();

lines.add(Integer.parseInt(word));//convert string to integer

}

inputArray = lines.toArray(new Integer[lines.size()]);

//convert arraylist to array;

if (low >= high)

{

System.out.println("Wrong Input");

//checking if array size is 0

System.exit(0);

}

int middle = (low + high) / 2;

mergeSort(inputArray, 0, high); //calling method for sorting

return t1;

}

  

//mergeSort Method

public static void mergeSort(Integer[] array, int lo, int n)

{

int low = lo;

int high = n;

if (low >= high)

{

return;

}

  

int middle = (low + high) / 2;

mergeSort(array, low, middle);

//recursion for external sort which we can call merge sort also

mergeSort(array, middle + 1, high);

int end_low = middle;

int start_high = middle + 1;

//start of the main Algorithm for externasl sort

while ((lo <= end_low) && (start_high <= high))

{

if (array[low] < array[start_high])

{

low++;

}

else

{

int Temp = array[start_high];

for (int k = start_high - 1; k >= low; k--)

{

array[k + 1] = array[k];

}

array[low] = Temp;

low++;

end_low++;

start_high++;

}

}

//End of the mergeSort Method

}

  

  

// printArray Method

static void printArray(Integer arr[],int size)

{

int n = size;

for (int i=0; i<n; ++i)

System.out.print(arr[i] + " ");

System.out.println();

}

//Main Method

public static void main(String args[]) throws IOException

{

Scanner sc=new Scanner(System.in);

int size;

Path t1 = (Path) Paths.get("test.txt");

//USing path class of java.nio.files;

System.out.println("input the size of array");

size=sc.nextInt(); //Taking the size of the array

extsort(t1,size);

//Calling sorting method  

System.out.println("\nSorted array");

printArray(inputArray,size);

//Calling method to print sorted array

}

}
