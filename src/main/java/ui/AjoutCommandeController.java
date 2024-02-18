package ui;

import entities.Commande;
import entities.statusCommande;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.CommandeService;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class AjoutCommandeController implements Initializable {
    @javafx.fxml.FXML
    private ComboBox<String> comboStatus;
    @javafx.fxml.FXML
    private TextField prix_totale;
    @javafx.fxml.FXML
    private Label labelTitle;
    @javafx.fxml.FXML
    private ComboBox<String> ComboEtat;
    @javafx.fxml.FXML
    private DatePicker Datepicker;
    @javafx.fxml.FXML
    private TextField id_commande;
    @javafx.fxml.FXML
    private Button button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        comboStatus.getItems().addAll("enCours", "Effectue", "Annule");
        ComboEtat.getItems().addAll("Paye", "Non paye");
        CommandeService su = new CommandeService();
        Commande aold = su.readById(ListCommandController.id_modif);
        labelTitle.setText("modifier commande");
        if(aold.getStatus().toString().equals("enCours"))
        {
            comboStatus.setValue("enCours");
        }
        else if(aold.getStatus().toString().equals("Effectue"))
        {
            comboStatus.setValue("Effectue");
        }
        else
        {
            comboStatus.setValue("Annule");
        }
        comboStatus.getSelectionModel().select(aold.getStatus().toString());
        if (aold.isEstpayee())
        {
            ComboEtat.setValue("Paye");
        }
        else {
            ComboEtat.setValue("Non paye");
        }
        Datepicker.setValue(aold.getDateCommande());
        prix_totale.setText(aold.getPrixTotale().toString());
        id_commande.setText(String.valueOf(ListCommandController.id_modif));
        button.setText("modifier");
        id_commande.setDisable(true);
    }

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
        c.setId_commande(Integer.valueOf(id_commande.getText()));
        CommandeService sc = new CommandeService();
        sc.update(c);
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
}
