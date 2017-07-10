import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Application {

    //单词|音标1|音标2|词性1|释义1|词性2|释义2|词性3|释义3|词性4|释义4

    private static final List<String> CIXINGS = Arrays.asList("a&ad", "conj&n", "vt&aux", "vt&vi&n",
            "vi&vt&n", "n&vi&vt", "n&vt&vi", "n&vt", "vi&n", "n&vi", "vt&n", "n&v", "vi&vt",
            "prep", "vt&vi", "pron", "n", "a", "v", "conj", "vi", "vt", "aux", "adj", "adv", "art",
            "num.", "int.", "u.", "c.", "pl.");

    public static void main(String[] args) throws IOException {
        File file = new File("/Users/sbyan/Desktop/highschool_error2.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        File outFile = new File("/Users/sbyan/Desktop/highschool_new1.txt");
        FileOutputStream outputStream = new FileOutputStream(outFile, true);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        File errorOutFile = new File("/Users/sbyan/Desktop/highschool_error3.txt");
        FileOutputStream errorOutputStream = new FileOutputStream(errorOutFile);
        BufferedWriter errorWriter = new BufferedWriter(new OutputStreamWriter(errorOutputStream));

        while (reader.ready()) {
            String line = reader.readLine();
            String[] segments = line.split("\\[");
            if (segments.length == 2) {
                String word = "";
                String sound1 = "";
                String sound2 = "";
                String part1 = "";
                String paraphrase1 = "";
                String part2 = "";
                String paraphrase2 = "";
                String part3 = "";
                String paraphrase3 = "";
                String part4 = "";
                String paraphrase4 = "";

                word = segments[0].trim();

                String[] items = segments[1].split("\\]");
                if (items.length == 2) {
                    sound1 = "[" + items[0] + "]";

                    String[] parts = items[1].split(" ");
                    for (String part:parts){

                    }
                }

                String result = word + "|" + sound1 + "|" + sound2 + "|" + part1 + "|" + paraphrase1 + "|" + part2 + "|" +
                        paraphrase2 + "|" + part3 + "|" + paraphrase3 + "|" + part4 + "|" + paraphrase4;

                writer.write(result);
                writer.newLine();
            } else {
                //Save to a new file
                if (line.contains("[")) {
                    errorWriter.write(line);
                    errorWriter.newLine();
                }
            }
        }
        reader.close();
        writer.close();
        errorWriter.close();
    }
}
