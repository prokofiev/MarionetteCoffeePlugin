package org.buffagon.intellij.marionette.coffee;

/**
 * @author Prokofiev Alex
 */
public final class StringUtil {
  public static String toCamelCase(String value, String separator)
  {
    StringBuilder sb = new StringBuilder();
    for(String part : value.split(separator)) {
      if(part.isEmpty())
        continue;
      sb.append(part.substring(0, 1));
      if(part.length() > 1)
        sb.append(part.substring(1));
    }
    return sb.toString();
  }
}
