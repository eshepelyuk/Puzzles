package assetcontrol;

import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.io.IOUtils.lineIterator;

public class ExtractConfig {
    /**
     * Created and initializes new config from reader. Close file after parse.
     *
     * @param reader
     */
    public static ExtractConfig fromReader(Reader reader) {
        Map<String, String> cfgMap = new HashMap<String, String>();
        LineIterator it = lineIterator(reader);
        try {
            while (it.hasNext()) {
                String[] arr = StringUtils.split(it.nextLine(), '\t');
                if (arr.length >= 2) {
                    Main.LOG.warn("Сonfig parse: invalid line {}", arr);
                    cfgMap.put(arr[0], arr[1]);
                }
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
        Main.LOG.info("Сonfig parse: detected {} mappings", cfgMap.size());
        return new ExtractConfig(cfgMap);
    }

    private final Map<String, String> cfgMap;

    public ExtractConfig(Map<String, String> cfgMap) {
        this.cfgMap = cfgMap;
    }

    public String transformed(String label) {
        return cfgMap.get(label);
    }
}
