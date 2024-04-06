import java.util.Comparator;

/**
 * A sorting algorithm that combines merge sort and quicksort.
 * When the partition size is less than or equal to a threshold, use quicksort;
 * otherwise, use merge sort.
 * 
 * @author Tim Yu
 * This sorting algorithm was written using references to ChatGPT.
 * 
 * Some comments about using LLM assistance:
 * I begin the chat by asking how I can combine merge sort and quicksort.
 * I liked that the LLM provided me with a "skeleton" of the code that will let me do this,
 * which reduces the amount of time I need.
 * From here, I was able to create wrapper methods that sorted partitions.
 * I was also wondering how to find the best threshold value to know when to use a sorting algorithm,
 * but I felt like I knew that I should show it experimentally.
 * 
 * Altogether, I didn't find the AI to be very useful, since I already have a general idea of what
 * I am trying to do.
 * I think that I need to give more specific prompts to the AI for it to actually describe the
 * implementations. If not, I don't get examples of specific implementations.
 * 
 * One thing that I found useful was being able to ask questions about what methods to use for
 * doing an experiment.
 * For example, I can ask the AI for classes which allow me to time how fast the algorithm runs.
 */
public class YuTimSort implements Sorter {
  // ======
  // Fields
  // ======

  /**
   * The one sorter you can access
   */
  public static Sorter SORTER = new YuTimSort();

  /**
   * For accessing quicksort method in Quicksort class (since it is not static)
   */
  private static Quicksort QSORTER = new Quicksort();

  /**
   * When partition size is less than or equal to threshold, we use quicksort; otherwise, use merge sort.
   */
  private int threshold = 256;

  // ============
  // Constructors
  // ============

  /**
   * Create a sorter with default threshold.
   */
  YuTimSort() {
  }

  /**
   * Create a sorter with given threshold.
   */
  YuTimSort(int threshold) {
    this.threshold = threshold;
  }

  // =======
  // Methods
  // =======

  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    hybridMergeSort(values, 0, values.length, order, values.clone());
  }

  // ====================
  // Semi-private methods
  // ====================

  /**
   * Recursively merge sort in indices [lo, hi), until we reach partition size at most THRESHOLD for
   * quicksort.
   * @param unsortedHelper We will pass a helper array to each call that is a duplicate of vals
   *    so merge sort can copy values from this array over to vals when merging.
   *    This array receives all changes made to vals at the end of merging.
   */
  public <T> void hybridMergeSort(T[] vals, int lo, int hi, Comparator<? super T> order,
      T[] unsortedHelper) {
    // base case: singleton is sorted
    if (hi - lo <= 1) {
      return;

    } else if (hi - lo <= threshold) {
      quicksort(vals, lo, hi, order);

      for (int i = lo; i < hi; i++) {
        unsortedHelper[i] = vals[i];
      }

      return;

    } else {
      int mid = (hi + lo) / 2;

      hybridMergeSort(vals, lo, mid, order, unsortedHelper);
      hybridMergeSort(vals, mid, hi, order, unsortedHelper);
      
      MergeSort.merge(vals, lo, mid, hi, order, unsortedHelper);
    }
  }

  /**
   * A wrapper for the subarray sorting method in Quicksort class.
   */
  public <T> void quicksort(T[] vals, int lo, int hi, Comparator<? super T> order) {
    QSORTER.quicksort(vals, order, lo, hi);
  }


}
