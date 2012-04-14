import java.io.File;

public class Main {
    public static void main(String[] args) {
        assert args.length == 3;

        File labelConfig = new File(args[1]);
        File rowConfig = new File(args[2]);
        File inputFile = new File(args[3]);
    }
}
