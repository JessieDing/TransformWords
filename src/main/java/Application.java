import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
            getResult(line, writer, errorWriter);
        }
        reader.close();
        writer.close();
        errorWriter.close();
    }

    // 给这种情况的解析： repeat [ri'pi:t] vt＆vi 重做；重复；复述 n 重复；反复
    private static void getResult(String line, BufferedWriter writer, BufferedWriter errorWriter) throws IOException {
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

                String[] parts = items[1].trim().split(" ");
                if (parts.length > 0 && parts.length % 2 == 0) {
                    if (parts.length >= 2) {
                        part1 = parts[0];
                        if (!CIXINGS.contains(part1)) {
                            return;
                        }
                        paraphrase1 = parts[1];
                    }
                    if (parts.length >= 4) {
                        part2 = parts[2];
                        if (!CIXINGS.contains(part1)) {
                            return;
                        }
                        paraphrase2 = parts[3];
                    }
                    if (parts.length >= 6) {
                        part3 = parts[4];
                        if (!CIXINGS.contains(part1)) {
                            return;
                        }
                        paraphrase3 = parts[5];
                    }
                } else {
                    return;
                }
            }

            String result = word + "|" + sound1 + "|" + sound2 + "|" + part1 + "|" + paraphrase1 + "|" + part2 + "|" +
                    paraphrase2 + "|" + part3 + "|" + paraphrase3 + "|" + part4 + "|" + paraphrase4;

            System.out.print(result);
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
}
