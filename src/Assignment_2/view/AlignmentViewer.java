package Assignment_2.view;

import Assignment_2.model.util.Alignment;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;

/**
 * Created by Benjamin on 17.10.16.
 */
public class AlignmentViewer extends BorderPane {

    private Alignment ali;

    // Graphical components
    private Label userInfo = new Label("Your Alignment:");
    public TextArea aliField = new TextArea("");

    // Checkboxes
    public CheckBox showHeaders = new CheckBox("Show Headers");
    public CheckBox showSequences = new CheckBox("Show Sequences");
    public CheckBox showNumbering = new CheckBox("Show Numbers");
    public CheckBox showStatistics = new CheckBox("Show Statistics");

    // Buttons
    public Button selectAll = new Button("Select All");
    public Button clearSelection = new Button("Clear Selection");
    public Button apply = new Button("Apply");


    public AlignmentViewer(Alignment ali) {
        this.ali = ali;
        createLayout();
        initFieldData();
        bindFieldsToModel();
    }

    private void bindFieldsToModel() {
        aliField.textProperty().bindBidirectional(ali.aliRepresentationProperty());
    }

    private void initFieldData() {
        String aliText = ali.getAliRepresentation();
        if (aliText != null)
            aliField.setText(aliText);
    }

    private void createLayout() {

        Insets borderInsets = new Insets(10, 10, 10, 10);
        Insets nodeInsets = new Insets(5, 5, 5, 5);

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        // setting padding for borders
        this.setPadding(borderInsets);

        // setting top
        userInfo.setPadding(nodeInsets);
        this.setTop(userInfo);

        // setting center
        aliField.setStyle("-fx-font-family: monospace");
        aliField.setPrefSize(primaryScreenBounds.getWidth() / 2, primaryScreenBounds.getHeight() / 2);
        this.setCenter(aliField);

        //setting bottom
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.getChildren().addAll(selectAll, clearSelection, apply);
        buttons.setPadding(nodeInsets);
        this.setBottom(buttons);

        //setting right
        showHeaders.setSelected(true);
        showSequences.setSelected(true);
        showNumbering.setSelected(true);
        showStatistics.setSelected(true);
        VBox checkBoxes = new VBox();
        checkBoxes.getChildren().addAll(showHeaders, showSequences, showNumbering, showStatistics);
        checkBoxes.setPadding(nodeInsets);
        this.setRight(checkBoxes);

    }

}
