import java.util.Comparator;

/**
 * Sort using insertion sort.
 *
 * @author Tim Yu
 */

public class InsertionSort implements Sorter {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The one sorter you can access.
   */
  public static Sorter SORTER = new InsertionSort();

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter.
   */
  InsertionSort() {
  } // InsertionSort()

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  @Override
  public <T> void sort(T[] values, Comparator<? super T> order) {
    for (int i = 0; i < values.length; i++) {
      for (int j = i; j >= 1 && order.compare(values[j - 1], values[j]) > 0; j--) {
        Helpers.swap(values, j, j - 1);
      }
    }
  } // sort(T[], Comparator<? super T>
} // class InsertionSort
