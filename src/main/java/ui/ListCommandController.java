package ui;

import entities.Commande;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.CommandeService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;


public class ListCommandController implements Initializable {
    @javafx.fxml.FXML
    private TableView<Commande> Tv_Commandes;
    @javafx.fxml.FXML
    private TableColumn<Commande, LocalDate> col_date;
    @javafx.fxml.FXML
    private TableColumn <Commande, Double>col_prix;
    @javafx.fxml.FXML
    private TableColumn<Commande, Integer> col_num;
    @javafx.fxml.FXML
    private TableColumn<Commande, Boolean> col_etat;
    @javafx.fxml.FXML
    private TableColumn<Commande, String>col_status;
    public static int id_modif ;

    public void afficherCommandes() {
        CommandeService sc = new CommandeService();

        List<Commande> lu = sc.readAll();
        ObservableList<Commande> userList = FXCollections.observableArrayList(lu);

        col_num.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("estpayee"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prixTotale"));


        Tv_Commandes.setItems(userList);
    }

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        afficherCommandes();
    }


    @javafx.fxml.FXML
    public void btnSuppAction(ActionEvent actionEvent) {
        int SelectedRowIndex = Tv_Commandes.getSelectionModel().getSelectedIndex();

        int ColumnIndex = Tv_Commandes.getColumns().indexOf(col_num);


        Alert A = new Alert(Alert.AlertType.CONFIRMATION);
        if (SelectedRowIndex == -1) {
            A.setAlertType(Alert.AlertType.WARNING);
            A.setContentText("Selectionnez une colonne ! ");
            A.show();
        } else {
            String IdCell = Tv_Commandes.getColumns().get(ColumnIndex).getCellData(SelectedRowIndex).toString();
            int id_supp = Integer.parseInt(IdCell);
            CommandeService su = new CommandeService();
            A.setAlertType(Alert.AlertType.CONFIRMATION);

            A.setContentText("Voulez vous supprimer la commande dont l'ID : " + IdCell + " ?");
            Optional<ButtonType> result = A.showAndWait();
            Commande c = new Commande();
            c.setId_commande(Integer.parseInt(IdCell));
            if (result.isPresent() && result.get() == ButtonType.OK) {

                su.delete(c);
                A.setAlertType(Alert.AlertType.INFORMATION);
                A.setContentText("Commande Supprimé ! ");
                A.show();
                afficherCommandes();
            }

        }
    }

    @javafx.fxml.FXML
    public void btnodifAction(ActionEvent actionEvent) {
        int SelectedRowIndex = Tv_Commandes.getSelectionModel().getSelectedIndex();

        int ColumnIndex = Tv_Commandes.getColumns().indexOf(col_num);


        Alert A = new Alert(Alert.AlertType.CONFIRMATION);
        if (SelectedRowIndex == -1) {
            A.setAlertType(Alert.AlertType.WARNING);
            A.setContentText("Selectionnez une colonne ! ");
            A.show();
        } else {
            String IdCell = Tv_Commandes.getColumns().get(ColumnIndex).getCellData(SelectedRowIndex).toString();
            id_modif = Integer.parseInt(IdCell);

            try {

                Parent page1 = FXMLLoader.load(getClass().getResource("/AjoutCommande.fxml"));

                Scene scene = new Scene(page1);

                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                stage.setScene(scene);

                stage.show();

            } catch (IOException ex) {

                System.out.println(ex.getMessage());

            }
        }
    }

    @javafx.fxml.FXML
    public void btnAjouterAction(ActionEvent actionEvent) {
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/ModifCommande.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());

        }
    }
}
