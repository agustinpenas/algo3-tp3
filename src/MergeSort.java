public class MergeSort {
  private int[] numbers;
  private int[] helper;
  private int[] grados;
  private int[] helperGrados;

  private int number;

  public void sort(int[] values, int[] grados) {
    this.numbers = values;
    number = values.length;
    this.helper = new int[number];
    this.grados = grados;
    this.helperGrados = new int[number];
    mergesort(0, number - 1);
  }

  private void mergesort(int low, int high) {
    // check if low is smaller then high, if not then the array is sorted
    if (low < high) {
      // Get the index of the element which is in the middle
      int middle = low + (high - low) / 2;
      // Sort the left side of the array
      mergesort(low, middle);
      // Sort the right side of the array
      mergesort(middle + 1, high);
      // Combine them both
      merge(low, middle, high);
    }
  }

  private void merge(int low, int middle, int high) {

    // Copy both parts into the helper array
    for (int i = low; i <= high; i++) {
      helper[i] = numbers[i];
      helperGrados[i]= grados[i];
    }

    int i = low;
    int j = middle + 1;
    int k = low;
    // Copy the smallest values from either the left or the right side back
    // to the original array
    while (i <= middle && j <= high) {
      if (helperGrados[i] >= helperGrados[j]) {
        numbers[k] = helper[i];
        grados[k] = helperGrados[i];
        i++;
      } else {
        numbers[k] = helper[j];
        grados[k] = helperGrados[j];
        j++;
      }
      k++;
    }
    // Copy the rest of the left side of the array into the target array
    while (i <= middle) {
      numbers[k] = helper[i];
      grados[k] = helperGrados[i];
      k++;
      i++;
    }

  }
} 
