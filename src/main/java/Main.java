import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Main {
    public static void main(String[] args) throws IOException {
        assertThat(args.length, is(3));

        File labelConfigFile = new File(args[0]);
        File rowConfigFile = new File(args[1]);
        File inputFile = new File(args[2]);

        assertThat(labelConfigFile.exists(), is(true));
        assertThat(rowConfigFile.exists(), is(true));
        assertThat(inputFile.exists(), is(true));

        ExtractConfig labelConfig = ExtractConfig.fromReader(new FileReader(labelConfigFile));
        ExtractConfig rowConfig = ExtractConfig.fromReader(new FileReader(rowConfigFile));

        Translator translator = new Translator();
        translator.setLabelConfig(labelConfig);
        translator.setRowConfig(rowConfig);

        translator.translate(new FileReader(inputFile), new OutputStreamWriter(System.out));
    }
}
