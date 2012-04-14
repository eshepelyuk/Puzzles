import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.io.IOUtils.lineIterator;

public class LabelConfig {

    private Map<String, String> labels = new HashMap<String, String>();

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
                    labels.put(arr[0], arr[1]);
                }
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
    }

    public String transformedLabel(String label) {
        return labels.get(label);
    }
}
