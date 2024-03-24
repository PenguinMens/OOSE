package edu.curtin.vehicleapp;

import java.time.*;
import java.time.format.*;
import java.util.regex.Pattern;

/**
 * Some utility methods to help implement subclasses of CustomerRequest.
 */
public final class Util
{
    /**
     * Produces a user-friendly string representation of a LocalDateTime object.
     */
    public static String formatTime(LocalDateTime time)
    {
        return time.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    }

    /**
     * Extracts integers from part of a string. For instance, if you have a string that might
     * contain the substring "coords N,M", where N and M are integers, and you want to extract
     * those numbers, you can call this method as follows:
     *
     * String s = "Treasure at coords 33,16. Hurry!";
     * int[] numbers = extractNumbers(s, "coords \\d+,\\d+");
     *
     * @param from   The string containing the numbers you want to extract.
     * @param regex  A regular expression matching part of the string containing the numbers.
     * @return       An int array containing the parsed numbers, or null if none were found.
     */
    public static int[] extractNumbers(String from, String regex)
    {
        var matcher = Pattern.compile(regex).matcher(from);
        int[] numbers = null;
        if(matcher.find())
        {
            numbers = Pattern
                .compile("\\d+")
                .matcher(matcher.group())
                .results()
                .mapToInt(r -> Integer.parseInt(r.group()))
                .toArray();
        }
        return numbers;
    }
}
