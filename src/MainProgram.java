//Author:Sayeed Gulmahamad
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainProgram {

    private List<Sortable> sortingAlgorithms;
    private static final String outPutDir = "SortedFile";

    public static void main(String[] args) {
    	try {
        String path = System.getProperty("path");
        if(path == null) {
        	path ="data";
        }
        new MainProgram().start(path);
    	} catch (Exception e) {
    		System.err.println(e.getMessage());
    		e.printStackTrace();
		}
    }

    private void start(String path) throws IOException {
        sortingAlgorithms = new ArrayList<>();
        sortingAlgorithms.add(new RadixSort());
        sortingAlgorithms.add(new BucketSort());
        sortingAlgorithms.add(new CountingSort());
        File files = new File(outPutDir);
		 files.mkdir();
        Files.list(Paths.get(path))
                .filter(Files::isRegularFile)
                .forEach(filePath -> {
                            for (Sortable algorithm : sortingAlgorithms) {
                                try {
                                    int[] nums = getNums(filePath);
                                    String resultName = Paths.get(outPutDir, algorithm.getClass().getSimpleName()
                                            + "-" + filePath.getFileName().toString()).toString();
                                    Path resultPath = Paths.get(resultName);
                                    try (BufferedWriter writer = Files.newBufferedWriter(resultPath, StandardCharsets.UTF_8)) {
                                        Arrays.stream(algorithm.sort(nums)).forEach(num -> {
                                            try {
                                                writer.write(String.valueOf(num) + "\n");
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        });
                                        writer.write(String.valueOf("COUNT: " + (algorithm).getCount() + "; N: " + nums.length + ";"));
                                        writer.flush();
                                        System.out.println("COUNT: " + ( algorithm).getCount() + "; N: " + nums.length + "; " + resultName);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                );
    }

    private int[] getNums(Path filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            if (line.length() > 0) {
                lines.add(line);
            }
        }
        int[] nums = new int[lines.size()];
        int i = 0;

        for (String l : lines) {
            try {
                nums[i++] = Integer.parseInt(l);
            } catch (NumberFormatException e) {
                // skip non numbers
            }
        }
        return nums;

    }

}
