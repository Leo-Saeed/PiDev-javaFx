package entities;


import java.time.LocalDate;

public class Commande {
  int id_commande ;
  LocalDate dateCommande ;
  statusCommande status ;
  Double prixTotale ;
  boolean estpayee ;

  public Commande() {}

  public Commande(LocalDate dateCommande, statusCommande status, Double prixTotale, boolean estpayee) {
    this.dateCommande = dateCommande;
    this.status = status;
    this.prixTotale = prixTotale;
    this.estpayee = estpayee;
  }

  public Commande(int id_commande, LocalDate dateCommande, statusCommande status, Double prixTotale, boolean estpayee) {
    this.id_commande = id_commande;
    this.dateCommande = dateCommande;
    this.status = status;
    this.prixTotale = prixTotale;
    this.estpayee = estpayee;
  }

  public int getId_commande() {
    return id_commande;
  }

  public void setId_commande(int id_commande) {
    this.id_commande = id_commande;
  }

  public LocalDate getDateCommande() {
    return dateCommande;
  }

  public void setDateCommande(LocalDate dateCommande) {
    this.dateCommande = dateCommande;
  }

  public statusCommande getStatus() {
    return status;
  }

  public void setStatus(statusCommande status) {
    this.status = status;
  }

  public Double getPrixTotale() {
    return prixTotale;
  }

  public void setPrixTotale(Double prixTotale) {
    this.prixTotale = prixTotale;
  }

  public boolean isEstpayee() {
    return estpayee;
  }

  public void setEstpayee(boolean estpayee) {
    this.estpayee = estpayee;
  }

  @Override
  public String toString() {
    return "Commande{" +
            "id_commande=" + id_commande +
            ", dateCommande=" + dateCommande +
            ", status=" + status +
            ", prixTotale=" + prixTotale +
            ", estpayee=" + estpayee +
            '}';
  }
}
