package Assignment_1;

import Assignment_1.model.io.FastaReader;
import Assignment_1.model.util.Alignment;

import java.io.File;

/**
 * Created by Benjamin on 17.10.16.
 */
public class Main {

    public static void main(String[] args) {

        File fastaFile = new File("/Users/Benjamin/Documents/Uni_Tuebingen/Lehre/WS16 Advanced Java For Bioinformatics/JavaFX_Assignments/src/Assignment_1/example.fa");
        Alignment ali = new FastaReader().run(fastaFile);
        System.out.println(ali.toString(60));

    }

}
