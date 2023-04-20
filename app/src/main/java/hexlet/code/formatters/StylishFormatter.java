package hexlet.code.formatters;

import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;

public final class Stylish extends Formatter {
    @Override
    public String format(TreeMap<String, TreeMap<Integer, Object>> diff) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, TreeMap<Integer, Object>> entry : diff.entrySet()) {
            String key = entry.getKey();
            TreeMap<Integer, Object> changes = entry.getValue();
            for (Map.Entry<Integer, Object> change : changes.entrySet()) {
                String sign = "";
                switch (change.getKey().toString()) {
                    case "1":
                        sign = "  + ";
                        break;
                    case "-1":
                        sign = "  - ";
                        break;
                    default:
                        sign = "    ";
                }
                list.add(sign + key + ": " + change.getValue());
            }
        }
        return "{\n" + String.join("\n", list) + "\n}";
    }
}
