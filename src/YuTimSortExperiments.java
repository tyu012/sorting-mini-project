import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Random;

/**
 * Experiments for determining the best threshold for YuTimSort.
 * 
 * @author Tim Yu
 */
public class YuTimSortExperiments {
  static Random random = new Random();

  /**
   * Length of experimented arrays.
   */
  static int LENGTH = 65536;

  /**
   * Number of threshold sizes to test
   */
  static int THRESHOLDS = 18;

  /**
   * Number of random arrays generated
   */
  static int ARRAYS = 100;

  /**
   * Generate a random integer array of given length.
   */
  static Integer[] randomArray(int length) {
    Integer[] randomArray = new Integer[length];

    for (int i = 0; i < length; i++) {
      randomArray[i] = random.nextInt();
    }

    return randomArray;
  }

  /**
   * Time the execution of sorter on an array in nanoseconds.
   * Returns running time.
   */
  static <T> long recordSortTime(T[] vals, Sorter sorter, Comparator<T> order) {
    long startTime = System.nanoTime();

    sorter.sort(vals, order);

    long endTime = System.nanoTime();

    return endTime - startTime;
  }

  public static void main(String[] args) {
    Comparator<Integer> ascending = (x, y) -> x.compareTo(y);
    PrintWriter pen = new PrintWriter(System.out, true);

    // Corresponds to values for 0, 2, 4, 8, 16, 32, ..., 2^{i-1} thresholds.
    long[] times = new long[THRESHOLDS];
    Sorter[] sorters = new Sorter[THRESHOLDS];

    // set up sorters
    for (int i = 0; i < THRESHOLDS; i++) {
      times[i] = 0;
      sorters[i] = new YuTimSort(i == 0 ? 0 : (int) Math.pow(2, i-1));
    }

    // record time with different sorters
    for (int i = 0; i < ARRAYS; i++) {
     
      Integer[] arr = randomArray(LENGTH);
      for (int j = 0; j < THRESHOLDS; j++) {
        pen.println("Testing array #" + i + " with sorter threshold " +
            (j == 0 ? 0 : (int) Math.pow(2, j - 1)));
        times[j] += recordSortTime(arr.clone(), sorters[j], ascending);
      }
    }

    // print results
    for (int i = 0; i < THRESHOLDS; i++) {
      int threshold = i == 0 ? 0 : (int) Math.pow(2, i-1);
      pen.println("Threshold " + threshold + " : " + times[i] + " ns");
    }
  }
}