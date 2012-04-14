import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

public class LabelConfigTest {

    LabelConfig columnConfig

    @BeforeMethod
    void setUp() throws Exception {
        columnConfig = new LabelConfig()
    }

    @Test
    void whenParseThenLabelStoredToMap() {
        //given
        StringReader inp = new StringReader("ID1\tMYID1\n")
        //when
        columnConfig.parse(inp)
        //then
        assert columnConfig.transformedLabel("ID1") == "MYID1"
        assert columnConfig.transformedLabel("ID2") == null
    }

    @Test
    void whenParseFileWithMultipleTabsThenLabelStoredToMap() {
        //given
        StringReader inp = new StringReader("ID1\t\tMYID1\n")
        //when
        columnConfig.parse(inp)
        //then
        assert columnConfig.transformedLabel("ID1") == "MYID1"
    }

    @Test
    void whenParseEmptyStreamThenNoLabels() {
        //given
        StringReader inp = new StringReader("")
        //when
        columnConfig.parse(inp)
        //then
        assert columnConfig.transformedLabel("ID1") == null
    }

    @Test
    void whenMoreThanTwoTokensThenTheyIgnored() {
        //given
        StringReader inp = new StringReader("ID1\tMYID1\tANOTHER\t\t\n")
        //when
        columnConfig.parse(inp)
        //then
        assert columnConfig.transformedLabel("ID1") == "MYID1"
    }
}
