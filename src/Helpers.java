public class Helpers {
  /**
   * Swaps elements on indices i and j in arr
   */
  public static <T> void swap(T[] arr, int i, int j) {
    T temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
  
}
