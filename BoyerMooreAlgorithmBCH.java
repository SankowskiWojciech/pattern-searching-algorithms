import java.util.ArrayList;
import java.util.List;

public class BoyerMooreAlgorithmBCH {

    private final int FIRST_CHAR_CODE = 0;
    private final int LAST_CHAR_CODE = 255;

    public List<Integer> search(final String data, final String pattern) {
        int[] last = setLast(pattern);
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

    private int[] setLast(final String pattern) {
        int[] last = new int[LAST_CHAR_CODE - FIRST_CHAR_CODE + 1];
        for(int i = 0; i < last.length; i++)
            last[i] = -1;
        for(int i = 0; i < pattern.length(); i++) {
            last[pattern.charAt(i) - FIRST_CHAR_CODE] = i;
        }
        return last;
    }
}
