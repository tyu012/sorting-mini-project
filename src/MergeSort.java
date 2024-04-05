import java.util.Comparator;

/**
 * Sort using merge sort.
 *
 * @author Tim Yu
 * @author Keely Miyamoto
 * 
 * Based on lab of 3/13 done by myself and Keely Miyamoto.
 */

public class MergeSort implements Sorter {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The one sorter you can access.
   */
  public static Sorter SORTER = new MergeSort();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter.
   */
  MergeSort() {
  } // MergeSort()

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    mergeSort(values, 0, values.length, order, values.clone());
  } // sort(T[], Comparator<? super T>

  // +-----------------+---------------------------------------------
  // | Local utilities |
  // +-----------------+

  /**
   * Merge the values from positions [lo..mid) and [mid..hi) back into
   * the same part of the array.
   * 
   * @param unsortedHelper the original unsorted array that is passed as a parameter
   *    (to prevent making multiple copies).
   *
   * Preconditions: Each subarray is sorted according to comparator.
   */
  static <T> void merge(T[] vals, int lo, int mid, int hi, Comparator<? super T> comparator,
      T[] unsortedHelper) {
    // referring to index in unsortedHelper
    int lIndex = lo;
    int rIndex = mid;
    // referring to index in vals
    int combinedIndex = lo;

    while (lIndex < mid || rIndex < hi) {
      if (lIndex < mid && rIndex < hi) {
        if (comparator.compare(unsortedHelper[lIndex], unsortedHelper[rIndex]) <= 0) {
          vals[combinedIndex] = unsortedHelper[lIndex];
          lIndex++;
        } else {
          vals[combinedIndex] = unsortedHelper[rIndex];
          rIndex++;
        }
      } else if (lIndex < mid) {
        vals[combinedIndex] = unsortedHelper[lIndex];
        lIndex++;
      } else {
        vals[combinedIndex] = unsortedHelper[rIndex];
        rIndex++;
      }
      combinedIndex++;
    }

    // copy merged part back to unsortedHelper
    for (int i = lo; i < hi; i++) {
      unsortedHelper[i] = vals[i];
    }
  } // merge

  /**
   * Helper version of mergeSort that takes these bounds as arguments. 
   * Initially you should pass 0 and vals.size() to this helper method 
   * to kick off the merge sort process.
   * 
   * @param unsortedHelper the original unsorted array that is passed as a parameter
   *    (to prevent making multiple copies).
   */
  static <T> void mergeSort(T[] vals, int lo, int hi, Comparator<? super T> comparator,
      T[] unsortedHelper) {
    if (lo >= (hi - 1)) {
      return;
    } 
    int mid = (hi + lo) / 2;
    
    mergeSort(vals, lo, mid, comparator, unsortedHelper);
    mergeSort(vals, mid, hi, comparator, unsortedHelper);

    merge(vals, lo, mid, hi, comparator, unsortedHelper);
  } // mergeSort
} // class MergeSort
