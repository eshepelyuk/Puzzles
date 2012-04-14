import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import static org.apache.commons.io.IOUtils.lineIterator;
import static org.apache.commons.lang.StringUtils.split;

public class Translator {
    private ExtractConfig labelConfig;
    private ExtractConfig rowConfig;

    public void setLabelConfig(ExtractConfig labelConfig) {
        this.labelConfig = labelConfig;
    }

    public void setRowConfig(ExtractConfig rowConfig) {
        this.rowConfig = rowConfig;
    }

    public static String[] readLine(LineIterator it) {
        String[] retval = split(it.nextLine(), '\t');
        return (retval != null && retval.length > 0) ? retval : null;
    }

    public static void writeLine(Writer output, List<String> list) throws IOException {
        if (list.size() > 0) {
            output.write(StringUtils.join(list, "\t") + "\n");
        }
    }

    private String[] processHeader(LineIterator it, Writer output) throws IOException {
        String[] headerLabels = null;
        List<String> list = new LinkedList<String>();

        if (it.hasNext()) {
            headerLabels = readLine(it);
            if (headerLabels != null) {
                for (int i = 0; i < headerLabels.length; i++) {
                    String transformed = labelConfig.transformed(headerLabels[i]);
                    if (transformed != null) {
                        list.add(transformed);//translating column labels
                    }
                }
                writeLine(output, list);
            }
            Main.LOG.info("Parsed header: detected {} columns", headerLabels.length);
        }
        return headerLabels;
    }

    public void translate(Reader input, Writer output) throws IOException {
        LineIterator it = lineIterator(input);

        String[] headerLabels = null;

        String rowId = null;
        String[] arr = null;
        List<String> list = new LinkedList<String>();
        try {
            headerLabels = processHeader(it, output);
            while (it.hasNext()) {
                arr = readLine(it);
                if (arr == null || arr.length < headerLabels.length) {
                    Main.LOG.warn("Skipped line - number of records less than number of columns");
                    continue;
                }
                rowId = rowConfig.transformed(arr[0]);
                if (rowId != null) {
                    arr[0] = rowId;//translating rowId
                    list.clear();
                    for (int i = 0; i < headerLabels.length; i++) {
                        String transformed = labelConfig.transformed(headerLabels[i]);
                        if (transformed != null) {
                            list.add(arr[i]);
                        }
                    }
                    writeLine(output, list);
                }
            }
        } finally {
            LineIterator.closeQuietly(it);
            IOUtils.closeQuietly(output);
        }
    }
}
