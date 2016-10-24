package Assignment_1.model.util;

import java.util.HashMap;

/**
 * Created by Benjamin on 17.10.16.
 */
public class Alignment {

    private HashMap<String, String> idToSequence;

    public Alignment() {
        idToSequence = new HashMap<String, String>();
    }

    public void addLine(String id, String seq) {
        idToSequence.put(id, seq);
    }

    public String getSeq(String id) {
        if (idToSequence.containsKey(id))
            return idToSequence.get(id);
        return null;
    }

    public HashMap<String, String> getAll() {
        return idToSequence;
    }

    public int getLength() {
        if (idToSequence.size() == 0)
            return -1;
        return idToSequence.get(idToSequence.keySet().iterator().next()).length();
    }

    public String toString(int lineWidth) {

        StringBuffer buf = new StringBuffer();
        int maxId = getMaxIDLength() + 4;
        int aliLength = getLength();

        for (int b = 0; b < getLength(); b += lineWidth) {

            int e = b + lineWidth < aliLength ? b + lineWidth - 1 : aliLength;

            // writing header
            buf = buf.append(fillString(maxId, ""));
            String begIndex = (b + 1) + "", endIndex = (e + 1) + "";
            buf = buf.append(fillString((e - b) - endIndex.length(), begIndex + ""));
            buf = buf.append(endIndex);
            buf = buf.append("\n");

            // writing alignments
            for (String id : idToSequence.keySet()) {
                buf = buf.append(fillString(maxId, id));
                buf = buf.append(idToSequence.get(id).substring(b, e) + "\n");
            }

            // adding block separator
            buf = buf.append("\n");

        }

        return buf.toString();
    }

    private String fillString(int max, String s) {
        while (s.length() < max)
            s = s.concat(" ");
        return s;
    }

    private int getMaxIDLength() {
        int max = 0;
        for (String id : idToSequence.keySet())
            max = id.length() > max ? id.length() : max;
        return max;
    }

}
