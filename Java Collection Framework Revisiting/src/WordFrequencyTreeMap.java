import java.util.TreeMap;

public class WordFrequencyTreeMap {
    public static void main(String[] args) {

        String text = "java is easy and java is powerful";
        String[] words = text.split(" ");

        TreeMap<String, Integer> map = new TreeMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        System.out.println(map);
    }
}
