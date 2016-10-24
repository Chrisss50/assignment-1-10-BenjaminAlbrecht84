package Assignment_2;

import Assignment_2.model.util.Alignment;
import Assignment_2.presenter.AlignmentPresenter;
import Assignment_2.view.AlignmentViewer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by Benjamin on 17.10.16.
 */
public class AlignmentApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {

        File fastaFile = new File("/Users/Benjamin/Documents/Uni_Tuebingen/Lehre/WS16 Advanced Java For Bioinformatics/JavaFX_Assignments/src/Assignment_1/example.fa");
        Alignment ali = new Alignment(fastaFile, 60);

        AlignmentViewer aliView = new AlignmentViewer(ali);

        Scene scene = new Scene(aliView);

        AlignmentPresenter aliPresenter = new AlignmentPresenter(ali, aliView);

        stage.setScene(scene);
        stage.setTitle("Alignment Viewer");
        stage.show();

    }

}
