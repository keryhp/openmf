package uk.ac.openmf.utils;

import uk.ac.openmf.model.OpenMFModelException;

/**
 * A utility class.
 *
 */
public final class Utils {

  private Utils() {

  }

  /**
   * Helper method to assert the condition is satisfied.
   *
   * @param condition the condition to assert.
   * @param msg the message if condition is not satisfied.
   */
  public static void assertTrue(boolean condition, String msg) {
    if (!condition) {
      if (msg != null) {
        throw new OpenMFModelException(msg);
      } else {
        throw new OpenMFModelException();
      }
    }
  }
}
