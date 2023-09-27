package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {

        List<String> split = new ArrayList<>();
        String commonDel = delimiters.iterator().next();
        for (String del : delimiters) {
            source = source.replace(del, commonDel);
        }
        StringTokenizer st = new StringTokenizer(source, commonDel);
        while (st.hasMoreTokens()) {
            split.add(st.nextToken());
        }
        return split;
    }
}
