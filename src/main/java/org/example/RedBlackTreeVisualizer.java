package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RedBlackTreeVisualizer extends Application {
    private RedBlackTree bst;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Red-Black Tree Visualizer");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label label = new Label("تعداد گره‌ها:");
        GridPane.setConstraints(label, 0, 0);
        grid.getChildren().add(label);

        TextField nodeCountField = new TextField();
        nodeCountField.setPromptText("تعداد گره‌ها");
        GridPane.setConstraints(nodeCountField, 1, 0);
        grid.getChildren().add(nodeCountField);

        Button button = new Button("ساخت درخت");
        GridPane.setConstraints(button, 1, 1);
        grid.getChildren().add(button);


        Label labelDelete = new Label("دیتای اون گره ای که میخوای حذف کنی:");
        GridPane.setConstraints(labelDelete, 2, 0);
        grid.getChildren().add(labelDelete);

        TextField value = new TextField();
        nodeCountField.setPromptText("دیتای اون گره ای که میخوای حذف کنی:");
        GridPane.setConstraints(value, 2, 1);
        grid.getChildren().add(value);

        Button buttonDelete = new Button("حذف گره");
        GridPane.setConstraints(buttonDelete, 3, 1);
        grid.getChildren().add(buttonDelete);


        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GridPane.setConstraints(canvas, 0, 2, 2, 1);
        grid.getChildren().add(canvas);
        bst = new RedBlackTree(gc);

        button.setOnAction(e -> {
            String input = nodeCountField.getText();
            if (input.matches("\\d+")) {
                int n = Integer.parseInt(input);
                for (int i = 0; i < n; i++) {
                    TextField nodeValueField = new TextField();
                    nodeValueField.setPromptText("مقدار گره " + (i + 1));
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("مقدار گره " + (i + 1));
                    alert.setHeaderText(null);
                    alert.getDialogPane().setContent(nodeValueField);
                    alert.showAndWait();

                    String nodeValue = nodeValueField.getText();
                    if (nodeValue.matches("\\d+")) {
                        try {
                            bst.insert(Integer.parseInt(nodeValue));
                            bst.printTree();
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        Alert errorAlert = new Alert(AlertType.ERROR);
                        errorAlert.setTitle("خطا");
                        errorAlert.setHeaderText("مقدار وارد شده نامعتبر است");
                        errorAlert.setContentText("لطفا یک عدد صحیح وارد کنید.");
                        errorAlert.showAndWait();
                        break;
                    }
                }
                bst.printTree();
            } else {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("خطا");
                errorAlert.setHeaderText("مقدار وارد شده نامعتبر است");
                errorAlert.setContentText("لطفا یک عدد صحیح وارد کنید.");
                errorAlert.showAndWait();
            }
        });

        buttonDelete.setOnAction(e -> {
            String input = value.getText();
            if (input.matches("\\d+")) {
                int val = Integer.parseInt(input);
                try {
                    bst.deleteByVal(val);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                bst.printTree();
            } else {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("خطا");
                errorAlert.setHeaderText("مقدار وارد شده نامعتبر است");
                errorAlert.setContentText("لطفاً یک عدد صحیح وارد کنید.");
                errorAlert.showAndWait();
            }
        });

        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
