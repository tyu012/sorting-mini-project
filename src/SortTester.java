import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.math.BigInteger;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Tests of Sorter objects.
 *
 * @author Tim Yu
 */
public class SortTester {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  Sorter sorter = new FakeSort();
  Random random = new Random();
  int maxLength = 100;
  int maxRandom = 65536;

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  @Test
  public void orderedStringTest() {
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    String[] expected = original.clone();
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // orderedStringTest

  @Test
  public void reverseOrderedStringTest() {
    String[] original = { "foxtrot", "delta", "charlie", "bravo", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    sorter.sort(original, (x, y) -> x.compareTo(y));
    assertArrayEquals(original, expected);
  } // orderedStringTest

  // Tests that I have added

  /**
   * Sorts an empty BigInteger array, which is already sorted.
   */
  @Test
  public void sortEmptyTest() {
    BigInteger[] empty = {};
    BigInteger[] expected = {};
    sorter.sort(empty, (x, y) -> x.compareTo(y));
    assertArrayEquals(empty, expected);
  }

  /** 
   * Sorts BigInteger arrays of length 1, which are already sorted.
   */
  @Test
  public void sortLength1ArrayTest() {
    for (int i = 0; i < 20; i++) {
      int val = random.nextInt();
      BigInteger[] original = { BigInteger.valueOf(val) };
      BigInteger[] expected = { BigInteger.valueOf(val) };
      sorter.sort(original, (x, y) -> x.compareTo(y));
      assertArrayEquals(original, expected);
    }
  }
  
  /**
   * Sorts longer BigInteger arrays that are already sorted
   */
  @Test
  public void sortSortedLongerArrayTest() {
    for (int arrLen = 2; arrLen <= maxLength; arrLen++) {

      // fill expected array with ordered values:
      BigInteger[] expected = orderedBigIntegerArray(arrLen);

      // copy original array from expected array:
      BigInteger[] original = expected.clone();
      
      // sort and compare sorted to expected array
      sorter.sort(original, (x, y) -> x.compareTo(y));
      assertArrayEquals(original, expected);
    }
  }
  
  /**
   * Sorts BigInteger arrays that are in reverse order
   */
  @Test
  public void sortReverseOrderLongerArrayTest() {
    for (int arrLen = 2; arrLen <= maxLength; arrLen++) {

      // fill expected array with ordered values:
      BigInteger[] expected = orderedBigIntegerArray(arrLen);

      // make original array that has reversed order:
      BigInteger[] original = new BigInteger[arrLen];
      for (int i = 0; i < arrLen; i++) {
        original[i] = expected[arrLen - 1 - i];
      }
      
      // sort and compare sorted to expected array
      sorter.sort(original, (x, y) -> x.compareTo(y));
      assertArrayEquals(original, expected);
    }
  }

  /**
   * Sorts longer BigInteger arrays of arbitrary order.
   */
  @Test
  public void sortLongerArrayTest() {
    // generate arrays of length 2 to 100:
    for (int arrLen = 2; arrLen <= maxLength; arrLen++) {

      // fill expected array with ordered values:
      BigInteger[] expected = orderedBigIntegerArray(arrLen);

      // create "original" array by shuffling expected array
      // using Collections.shuffle
      // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Collections.html
      // https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array/21454317#21454317
      List<BigInteger> toShuffle = new ArrayList<BigInteger>();
      for (BigInteger b : expected) {
        toShuffle.add(b);
      }
      Collections.shuffle(toShuffle);
      BigInteger[] original = new BigInteger[arrLen];
      for (int i = 0; i < arrLen; i++) {
        original[i] = toShuffle.get(i);
      }

      // sort and compare sorted to expected array
      sorter.sort(original, (x, y) -> x.compareTo(y));
      assertArrayEquals(original, expected);
    }
  }

  // Helper methods
  public BigInteger[] orderedBigIntegerArray(int arrLen) {
    BigInteger[] ret = new BigInteger[arrLen];
    BigInteger nextValue = BigInteger.valueOf(random.nextInt(maxRandom));
    for (int i = 0; i < arrLen; i++) {
      ret[i] = nextValue;
      nextValue = nextValue.add(BigInteger.valueOf(random.nextInt(maxRandom)));
    }
    return ret;
  }

} // class SortTester
