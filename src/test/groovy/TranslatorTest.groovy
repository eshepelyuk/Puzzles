import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

public class TranslatorTest {

    Translator translator
    ExtractConfig labelConfig
    ExtractConfig rowConfig

    StringReader reader
    StringWriter writer

    @BeforeMethod
    void setUp() {
        labelConfig = mock(ExtractConfig)
        when(labelConfig.transformed(anyString())).thenAnswer(new Answer<String>() {
            @Override
            String answer(InvocationOnMock invocation) {
                return invocation.arguments[0]
            }
        })
        rowConfig = mock(ExtractConfig)
        when(rowConfig.transformed(anyString())).thenAnswer(new Answer<String>() {
            @Override
            String answer(InvocationOnMock invocation) {
                return invocation.arguments[0]
            }
        })
        writer = new StringWriter()
        translator = new Translator(rowConfig: rowConfig, labelConfig: labelConfig)
    }

    @Test
    void whenTranslateEmptyFileThenEmptyOutput() {
        //given
        reader = new StringReader("")
        //when
        translator.translate(reader, writer)
        //then
        assert writer.toString() == ""
    }

    @Test
    void whenTranslateFileWithEmptyLinesThenEmptyOutput() {
        //given
        reader = new StringReader("""
\t\t


""")
        //when
        translator.translate(reader, writer)
        //then
        assert writer.toString() == ""
    }

    @Test
    void whenTranslateThenIncludeOnlyConfiguredRows() {
        //given
        when(rowConfig.transformed(anyString())).thenAnswer(new Answer<String>() {
            @Override
            String answer(InvocationOnMock invocation) {
                return ["ID0", "ID2"].contains(invocation.arguments[0]) ? invocation.arguments[0] : null
            }
        })

        reader = new StringReader("""COL0\tCOL1\tCOL2
ID0\tVAL01\tVAL02
ID1\tVAL11\tVAL22
ID2\tVAL21\tVAL22
ID3\tVAL31\tVAL22
""")
        //when
        translator.translate(reader, writer)
        //then
        assert writer.toString().readLines().size() == 3
    }

    @Test
    void whenTranslateThenRowIdTranslatedUsingRowConfig() {
        //given
        when(rowConfig.transformed(anyString())).thenAnswer(new Answer<String>() {
            @Override
            String answer(InvocationOnMock invocation) {
                return "MY${invocation.arguments[0]}"
            }
        })
        reader = new StringReader("""COL0\tCOL1\tCOL2
ID0\tVAL01\tVAL02
ID1\tVAL11\tVAL22
ID2\tVAL21\tVAL22
ID3\tVAL31\tVAL22
""")
        //when
        translator.translate(reader, writer)
        //then
        writer.toString().readLines()[1..-1].eachWithIndex {item, cnt ->
            assert item.startsWith("MYID${cnt}")
        }

    }

    @Test
    void whenTranslateThenIncludeOnlyConfiguredColumns() {
        //given
        when(labelConfig.transformed(anyString())).thenAnswer(new Answer<String>() {
            @Override
            String answer(InvocationOnMock invocation) {
                return ["COL0", "COL2"].contains(invocation.arguments[0]) ? invocation.arguments[0] : null
            }
        })
        reader = new StringReader("""COL0\tCOL1\tCOL2\tCOL3
ID0\tVAL1\tVAL2\tVAL3
""")
        //when
        translator.translate(reader, writer)
        //then
        def lines = writer.toString().readLines()
        assert lines[0] == "COL0\tCOL2"
        assert lines[1] == "ID0\tVAL2"
    }

    @Test
    void whenTranslateThenLabelTranslatedUsingLabelConfig() {
        //given
        when(labelConfig.transformed(anyString())).thenAnswer(new Answer<String>() {
            @Override
            String answer(InvocationOnMock invocation) {
                return "MY${invocation.arguments[0]}"
            }
        })
        reader = new StringReader("COL0\tCOL1\tCOL2\tCOL3")
        //when
        translator.translate(reader, writer)
        //then
        def lines = writer.toString().readLines()
        assert lines[0].split("\t").every {it.startsWith "MYCOL"}
    }

    @Test
    void whenTranslateAndNoLabelsConfiguredThenEmptyOutput() {
        //given
        when(labelConfig.transformed(anyString())).thenReturn(null);
        reader = new StringReader("COL0\tCOL1\tCOL2\tCOL3")
        //when
        translator.translate(reader, writer)
        //then
        assert writer.toString() == ""
    }
}

