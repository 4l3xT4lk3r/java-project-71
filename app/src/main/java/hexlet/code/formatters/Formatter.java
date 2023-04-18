package hexlet.code.formatters;

import java.util.TreeMap;

public abstract class Formatter {
    public abstract String format(TreeMap<String, TreeMap<Integer, Object>> diff);
}
