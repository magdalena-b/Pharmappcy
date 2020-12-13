package wt.muppety.presenter;
import javafx.scene.control.ComboBox;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import wt.muppety.model.Transaction;
import wt.muppety.model.Product;
import wt.muppety.dao.TransactionDao;
import wt.muppety.dao.ProductDao;

import javafx.collections.ObservableList;
import java.util.HashSet;
import java.util.Set;

public class EditTransactionDialogPresenter extends AbstractDialogPresenter<Transaction> {

    @FXML
    public ComboBox<Product> productComboBox;
    @FXML
    public TextField quantityTextField;

// Product product, Employee employee, int quantity, double value, LocalDateTime datetime

    @FXML
    private void initialize() {
        ProductDao productDao = new ProductDao();
        ObservableList<Product> products = productDao.listAll();
        productComboBox.setItems(products);
    }



    @Override
    protected void updateModel() {
        data.setQuantity(Integer.parseInt(quantityTextField.getText()));
        Product product = productComboBox.getValue();
        data.setProduct(product);
    }

    @Override
    protected void updateControls() {
        //IGNORE
    }
}