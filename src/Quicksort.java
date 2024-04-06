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
    quicksort(values, order, 0, values.length);
  } // sort(T[], Comparator<? super T>

  // +----------------------+----------------------------------------
  // | Semi-private methods |
  // +----------------------+
  
  /**
   * Sort the subarray of T given by [lb..ub) in place using
   * the Quicksort algorithm.
   */
  <T> void quicksort(T[] values, Comparator<? super T> order, int lb, int ub) {
    // base case: singleton array is sorted
    if (ub - lb <= 1) {
      return;
    }

    // recursive case: partition, then quicksort [lb, pivotLoc) and [pivotLoc+1, ub)
    int pivotLoc = partition(values, order, lb, ub);
    quicksort(values, order, lb, pivotLoc);
    quicksort(values, order, pivotLoc + 1, ub);
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
  public <T> int partition(T[] arr, Comparator<? super T> order, int lb, int ub) {
    int midpoint = ((ub - lb) / 2) + lb;
    T pivotValue = arr[midpoint];
    int small = lb;
    int large = ub;

    // swap the pivot with lb=small
    Helpers.swap(arr, small, midpoint);
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
    int pivotLoc = small - 1;
    Helpers.swap(arr, lb, pivotLoc);
    return pivotLoc;
  }
} // class Quicksort
