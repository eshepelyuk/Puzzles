package assetcontrol

import org.apache.commons.io.output.WriterOutputStream
import org.testng.annotations.Test
import assetcontrol.Main

class IntegrationTest {
    static def EXPECTED = ["OURID\tOURCOL1\tOURCOL3", "OURIDXXX\tVAL21\tVAL23"]

    @Test
    void withRealFiles() {
        Writer w = new StringWriter()
        System.setOut(new PrintStream(new WriterOutputStream(w)))
        Main.main("src/test/resources/assetcontrol/label.txt", "src/test/resources/assetcontrol/row.txt", "src/test/resources/assetcontrol/str.txt")
        w.toString().readLines().eachWithIndex {item, cnt ->
            assert item == EXPECTED[cnt]
        }
    }
}
