package wt.muppety.presenter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import wt.muppety.controller.AbstractController;

public abstract class AbstractDialogPresenter<T> extends AbstractController<T> {

    protected Stage stage;

    protected boolean accepted;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setData(T data) {
        this.data = data;
        updateControls();
    }

    public boolean isAccepted() {
        return accepted;
    }

    @FXML
    protected void handleOkAction(ActionEvent event) {
        updateModel();
        stage.close();
        accepted = true;
    }

    @FXML
    protected void handleCancelAction(ActionEvent event) {
        accepted = false;
        stage.close();
    }

    protected abstract void updateModel();

    protected abstract void updateControls();
}
