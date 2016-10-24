package Assignment_2.model.util;

import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * Created by Benjamin on 17.10.16.
 */
public class AlignmentStatistic {

    private int maxSeqLength, avgSeqLength, numOfChars;
    private int[] charsContent;

    public void run(HashMap<String, String> idToSequence) {

        int a = 0, u = 0, c = 0, g = 0, r = 0;
        maxSeqLength = 0;
        numOfChars = 0;
        for (String seq : idToSequence.values()) {

            // removing gaps
            String s = seq.replace("-", "");

            // updating maxLength and number of characters
            maxSeqLength = s.length() > maxSeqLength ? s.length() : maxSeqLength;
            numOfChars += s.length();

            // counting characters
            for (int i = 0; i < s.length(); i++) {
                switch (s.charAt(i)) {
                    case 'A':
                    case 'a':
                        a++;
                        break;
                    case 'U':
                    case 'u':
                        u++;
                        break;
                    case 'C':
                    case 'c':
                        c++;
                        break;
                    case 'G':
                    case 'g':
                        g++;
                        break;
                    default:
                        r++;
                }
            }

        }

        // computing character contents
        charsContent = new int[5];
        System.out.println(a);
        charsContent[0] = (int) Math.round(100 * ((double) a / (double) numOfChars));
        charsContent[1] = (int) Math.round(100 * ((double) u / (double) numOfChars));
        charsContent[2] = (int) Math.round(100 * ((double) c / (double) numOfChars));
        charsContent[3] = (int) Math.round(100 * ((double) g / (double) numOfChars));
        charsContent[4] = (int) Math.round(100 * ((double) r / (double) numOfChars));

        // computing average sequence length
        avgSeqLength = (int) Math.round((double) numOfChars / (double) idToSequence.keySet().size());

    }

    public int getMaxSeqLength() {
        return maxSeqLength;
    }

    public int getNumOfChars() {
        return numOfChars;
    }

    public int[] getCharsContent() {
        return charsContent;
    }

    public int getCGContent() {
        return charsContent[2] + charsContent[3];
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf = buf.append("Maximal Sequence Length: " + maxSeqLength + "\n");
        buf = buf.append("Average Sequence Length: " + avgSeqLength + "\n");
        buf = buf.append("#Characters: " + numOfChars + "\n");
        buf = buf.append("CG-Content: " + getCGContent() + "%");
        return buf.toString();
    }

}
