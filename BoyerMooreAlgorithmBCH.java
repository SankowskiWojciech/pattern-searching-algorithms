import java.util.ArrayList;
import java.util.List;

public class BoyerMooreAlgorithmBCH {
    private int[] last;
    private final String data;
    private final String pattern;

    private final int FIRST_CHAR_CODE = 0;
    private final int LAST_CHAR_CODE = 255;


    public BoyerMooreAlgorithmBCH(final String data, final String pattern) {
        this.data = data.toUpperCase();
        this.pattern = pattern.toUpperCase();
        setLast();
    }

    public List<Integer> search() {
        List<Integer> foundIndexes = new ArrayList<>();
        int i = 0;
        int j;
        while( i <= data.length() - pattern.length() ) {
            for(j = pattern.length() - 1; j > -1 && pattern.charAt(j) == data.charAt(i + j); j--)
                ;
            if(j > -1)  //not found
                i += Integer.max(1, j - last[data.charAt(i + j) - FIRST_CHAR_CODE]);
            else //found
                foundIndexes.add(i++);
        }
        return foundIndexes.size() > 0 ? foundIndexes : null;
    }

    private void setLast() {
        last = new int[LAST_CHAR_CODE - FIRST_CHAR_CODE + 1];
        for(int i = 0; i < last.length; i++)
            last[i] = -1;
        for(int i = 0; i < pattern.length(); i++) {
            last[pattern.charAt(i) - FIRST_CHAR_CODE] = i;
        }
    }
}
