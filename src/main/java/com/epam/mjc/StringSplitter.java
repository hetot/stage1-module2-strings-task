package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> dels = new ArrayList<>();
        for (String delimiter : delimiters) {
            String tmp = "";
            for (int i = 0; i < delimiter.length(); i++) {
                if (delimiter.charAt(i) == '\\') {
                    tmp = "[\\" + delimiter.charAt(i) + "]";
                } else {
                    tmp = "[" + delimiter.charAt(i) + "]";
                }
            }
            dels.add(tmp);
        }
        StringJoiner joiner = new StringJoiner(")|(");

        for (String delimiter : dels) {
            joiner.add(delimiter);
        }

        String reg = "(" + joiner.toString() + ")";
        String[] toProcess = source.split(reg);
        List<String> result = new ArrayList<>();
        for (String process : toProcess) {
            if (!Objects.equals(process, "")) {
                result.add(process);
            }
        }
        return result;
    }
}
