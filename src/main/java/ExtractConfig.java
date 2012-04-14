import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.io.IOUtils.lineIterator;

public class ExtractConfig {

    private Map<String, String> cfgMap = new HashMap<String, String>();

    /**
     * parse labels config and closes file
     *
     * @param reader
     */
    public void parse(Reader reader) {
        LineIterator it = lineIterator(reader);
        try {
            while (it.hasNext()) {
                String[] arr = StringUtils.split(it.nextLine(), '\t');
                if (arr.length >= 2) {
                    cfgMap.put(arr[0], arr[1]);
                }
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
    }

    public String transformed(String label) {
        return cfgMap.get(label);
    }
}
