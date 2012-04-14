import org.testng.annotations.Test

public class ExtractConfigTest {

    ExtractConfig config

    @Test
    void whenParseThenLabelStoredToMap() {
        //given
        StringReader inp = new StringReader("ID1\tMYID1\n")
        //when
        config = ExtractConfig.fromReader(inp)
        //then
        assert config.transformed("ID1") == "MYID1"
        assert config.transformed("ID2") == null
    }

    @Test
    void whenParseFileWithMultipleTabsThenLabelStoredToMap() {
        //given
        StringReader inp = new StringReader("ID1\t\tMYID1\n")
        //when
        config = ExtractConfig.fromReader(inp)
        //then
        assert config.transformed("ID1") == "MYID1"
    }

    @Test
    void whenParseEmptyStreamThenNoLabels() {
        //given
        StringReader inp = new StringReader("")
        //when
        config = ExtractConfig.fromReader(inp)
        //then
        assert config.transformed("ID1") == null
    }

    @Test
    void whenMoreThanTwoTokensThenTheyIgnored() {
        //given
        StringReader inp = new StringReader("ID1\tMYID1\tANOTHER\t\t\n")
        //when
        config = ExtractConfig.fromReader(inp)
        //then
        assert config.transformed("ID1") == "MYID1"
    }
}
