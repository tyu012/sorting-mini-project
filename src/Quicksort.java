import java.util.Comparator;

/**
 * Sort using Quicksort.
 *
 * @author Tim Yu
 * @author Zack Abdilahi
 * 
 * Based on lab of 4/1 done by myself and Zack Abdilahi.
 */

public class Quicksort implements Sorter {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The one sorter you can access.
   */
  public static Sorter SORTER = new Quicksort();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter.
   */
  Quicksort() {
  } // Quicksort()

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    // STUB
  } // sort(T[], Comparator<? super T>

  // +----------------------+----------------------------------------
  // | Semi-private methods |
  // +----------------------+
  
  /**
   * Sort the subarray of T given by [lb..ub) in place using
   * the Quicksort algorithm.
   */
  <T> void quicksort(T[] values, Comparator<? super T> order, int lb, int ub) {
    // STUB
  } // quicksort(T[], Comparator<? super T>, lb, ub)
  
  /**
   * Select a pivot and partition the subarray from [lb .. ub) into 
   * the following form.
   *
   * <pre>
   * ---+-----------------+-+----------------+---
   *    | values <= pivot |p| values > pivot |
   * ---+-----------------+-+----------------+---
   *    |                 |                  |
   *    lb                pivotLoc           ub
   * </pre>
   *
   * @return pivotLoc.
   */
  public static <T> int partition(T[] arr, Comparator<? super T> order, int lb, int ub) {
    int pivotLoc = (ub - lb) / 2;
    T pivotValue = arr[pivotLoc];
    int small = lb;
    int large = ub;

    // swap the pivot with lb
    Helpers.swap(arr, small, pivotLoc);
    small++;

    // compare and swap
    while (small < large) {
      if (order.compare(arr[small], pivotValue) <= 0) {
        small++;
      } else if (order.compare(arr[large - 1], pivotValue) > 0) {
        large--;
      } else {
        Helpers.swap(arr, small, large - 1);
        small++;
        large--;
      }
    }

    // move the pivot to small-1 at the end
    Helpers.swap(arr, lb, small - 1);

    return pivotLoc;
  }
} // class Quicksort
