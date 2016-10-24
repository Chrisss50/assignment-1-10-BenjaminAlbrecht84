package Assignment_2.presenter;

import Assignment_2.model.util.Alignment;
import Assignment_2.view.AlignmentViewer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.text.Text;

/**
 * Created by Benjamin on 17.10.16.
 */
public class AlignmentPresenter {

    private Alignment aliModel;
    private AlignmentViewer aliView;

    public AlignmentPresenter(Alignment aliModel, AlignmentViewer aliView) {
        this.aliModel = aliModel;
        this.aliView = aliView;
        attachEvents();
    }

    private void attachEvents() {

        // reacting on clearSelection button
        aliView.clearSelection.setOnAction(e -> setAllCheckBoxes(false));

        // reacting on selectAll button
        aliView.selectAll.setOnAction(e -> setAllCheckBoxes(true));

        // reacting on apply button
        boolean showHeader = aliView.showHeaders.isSelected();
        boolean showNumbering = aliView.showNumbering.isSelected();
        boolean showSequence = aliView.showSequences.isSelected();

        aliView.apply.setOnAction(e -> aliModel.createRepresentation(aliView.showHeaders.isSelected(),
                aliView.showNumbering.isSelected(), aliView.showSequences.isSelected(), aliView.showStatistics.isSelected()));

        // reacting on resizing window
        aliView.aliField.getScene().widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                Text text = new Text("A");
                text.setFont(aliView.aliField.getFont());
                int width = (int) Math.round((newSceneWidth.doubleValue() / text.getLayoutBounds().getWidth()) - 23);
                System.out.println(width);
                aliModel.setLineWidth(width);
                aliModel.createRepresentation(aliView.showHeaders.isSelected(), aliView.showNumbering.isSelected(),
                        aliView.showSequences.isSelected(), aliView.showStatistics.isSelected());
            }
        });

    }

    private void setAllCheckBoxes(boolean isSelected) {
        aliView.showHeaders.setSelected(isSelected);
        aliView.showNumbering.setSelected(isSelected);
        aliView.showSequences.setSelected(isSelected);
    }

}
