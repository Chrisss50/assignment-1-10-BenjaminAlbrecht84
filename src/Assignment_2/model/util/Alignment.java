package Assignment_2.model.util;

import Assignment_2.model.io.FastaReader;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Benjamin on 17.10.16.
 */
public class Alignment {

    private HashMap<String, String> idToSequence;
    private StringProperty aliRepresentation;
    private int lineWidth;

    public Alignment(File aliFile, int lineWidth) {

        idToSequence = new HashMap<String, String>();
        aliRepresentation = new SimpleStringProperty();
        this.lineWidth = lineWidth;

        initAlignment(aliFile);

    }

    private void initAlignment(File aliFile) {
        new FastaReader().run(aliFile, this);
        createRepresentation(true, true, true, true);
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

    public String createRepresentation(boolean showHeader, boolean showNumbering, boolean showSequence, boolean showStatistics) {

        StringBuffer buf = new StringBuffer();
        int maxId = getMaxIDLength() + 4;
        int aliLength = getLength();

        for (int b = 0; b < getLength(); b += lineWidth) {

            int e = b + lineWidth < aliLength ? b + lineWidth - 1 : aliLength;

            // writing header
            if (showNumbering) {
                buf = buf.append(fillString(maxId, ""));
                String begIndex = (b + 1) + "", endIndex = (e + 1) + "";
                buf = buf.append(fillString((e - b) - endIndex.length(), begIndex + ""));
                buf = buf.append(endIndex);
                buf = buf.append("\n");
            } else
                buf = buf.append("\n");

            // writing alignments
            for (String id : idToSequence.keySet()) {
                if (showHeader)
                    buf = buf.append(fillString(maxId, id));
                else
                    buf = buf.append(fillString(maxId, ""));
                if (showSequence)
                    buf = buf.append(idToSequence.get(id).substring(b, e) + "\n");
                else
                    buf = buf.append("\n");
            }

            // adding block separator
            buf = buf.append("\n");

        }

        if (showStatistics) {

            // calculating alignment statistics
            AlignmentStatistic stats = new AlignmentStatistic();
            stats.run(idToSequence);

            // writing alignment statistics
            buf = buf.append("--- \n");
            buf = buf.append(stats.toString());

        }


        aliRepresentation.set(buf.toString());
        System.out.println(buf.toString());
        return buf.toString();

    }

    private String fillString(int max, String s) {
        while (s.length() < max)
            s = s.concat(" ");
        return s;
    }

    public void setLineWidth(int width) {
        this.lineWidth = width - getMaxIDLength() - 4;
    }

    private int getMaxIDLength() {
        int max = 0;
        for (String id : idToSequence.keySet())
            max = id.length() > max ? id.length() : max;
        return max;
    }

    public String getAliRepresentation() {
        return aliRepresentation.get();
    }

    public StringProperty aliRepresentationProperty() {
        return aliRepresentation;
    }

    public void setAliRepresentation(String aliRepresentation) {
        this.aliRepresentation.set(aliRepresentation);
    }

}
