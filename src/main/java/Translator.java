import java.io.Reader;
import java.io.Writer;

public class Translator {
    private ExtractConfig labelConfig;
    private ExtractConfig rowConfig;

    public ExtractConfig getLabelConfig() {
        return labelConfig;
    }

    public void setLabelConfig(ExtractConfig labelConfig) {
        this.labelConfig = labelConfig;
    }

    public ExtractConfig getRowConfig() {
        return rowConfig;
    }

    public void setRowConfig(ExtractConfig rowConfig) {
        this.rowConfig = rowConfig;
    }

    public void translate(Reader input, Writer output) {

    }
}
