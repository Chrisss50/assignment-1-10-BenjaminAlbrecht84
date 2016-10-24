package Assignment_1.model.io;

import Assignment_1.model.util.Alignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by Benjamin on 17.10.16.
 */
public class FastaReader {

    public Alignment run(File fastaFile) {

        Alignment ali = new Alignment();
        try {

            BufferedReader buf = new BufferedReader(new FileReader(fastaFile));

            try {

                String id = "";
                StringBuffer seq = new StringBuffer();
                String line;

                while ((line = buf.readLine()) != null) {
                    if (line.startsWith(">")) {
                        if (!id.isEmpty())
                            ali.addLine(id, seq.toString());
                        id = line.substring(1);
                        seq = new StringBuffer();
                    } else
                        seq = seq.append(line);
                }

            } finally {
                buf.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ali;

    }

}
