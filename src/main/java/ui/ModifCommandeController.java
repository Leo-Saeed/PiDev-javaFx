package ui;

import entities.Commande;
import entities.statusCommande;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.CommandeService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifCommandeController implements Initializable {
    @javafx.fxml.FXML
    private Button button;
    @javafx.fxml.FXML
    private ComboBox<String> comboStatus;
    @javafx.fxml.FXML
    private TextField prix_totale;
    @javafx.fxml.FXML
    private ComboBox<String> ComboEtat;
    @javafx.fxml.FXML
    private DatePicker Datepicker;

    @javafx.fxml.FXML
    public void buttonAction(ActionEvent actionEvent) {
        Commande c = new Commande();
        c.setPrixTotale(Double.valueOf(prix_totale.getText()));
        c.setDateCommande(Datepicker.getValue());
        if (comboStatus.getValue().equals(statusCommande.Annule.toString())) {
            c.setStatus(statusCommande.Annule);
        }else if (comboStatus.getValue().equals(statusCommande.enCours.toString()))
        {
            c.setStatus(statusCommande.enCours);
        }
        else
        {
            c.setStatus(statusCommande.effectue);
        }
        System.out.println(ComboEtat.getValue());
        if(ComboEtat.getValue().equals("Paye")) {
            System.out.println("test");
            c.setEstpayee(true);
        }
        else
        {
            c.setEstpayee(false);
        }

        CommandeService sc = new CommandeService();
        sc.add(c);
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/ListCommands.fxml"));

            Scene scene = new Scene(page1);

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(scene);

            stage.show();

        } catch (IOException ex) {

            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboStatus.getItems().addAll("enCours", "Effectue", "Annule");
        ComboEtat.getItems().addAll("Paye", "Non paye");
    }
}
