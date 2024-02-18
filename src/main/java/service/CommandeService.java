package service;

import entities.Commande;

import entities.statusCommande;
import entities.Personne;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeService implements IService<Commande> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public CommandeService() {
        conn = DataSource.getInstance().getCnx();
    }

    @Override
    public void add(Commande commande) {
        int ispayed = 0 ;
        if (commande.isEstpayee())
        {
            ispayed = 1 ;
        }

        String requete = "insert into commande (dateCommande,statusCommande,prixTotale,estpayee) values ('" + commande.getDateCommande() + "','" + commande.getStatus() + "','" + commande.getPrixTotale() + "' ,'" + ispayed + "'  )";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Commande commande) {
        try {
            String req = "DELETE FROM commande WHERE id_commande = " + commande.getId_commande();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("commande deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Commande commande) {
        int ispayed = 0 ;
        if (commande.isEstpayee())
        {
            ispayed = 1 ;
        }
        try {
            System.out.println(ispayed);
            System.out.println(commande.getPrixTotale());
            System.out.println(commande.getId_commande());
            String req = "UPDATE commande SET dateCommande = ?, statusCommande = ? ,prixTotale = ? , estpayee = ? WHERE id_commande = ?";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setDate(1, java.sql.Date.valueOf(commande.getDateCommande()));
            ps.setString(2, commande.getStatus().toString());
            ps.setDouble(3, commande.getPrixTotale());
            ps.setInt(4, ispayed);

            ps.setInt(5, commande.getId_commande());

            ps.executeUpdate();
            System.out.println("Commande updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Commande> readAll() {
        String requete = "select * from commande";
        List<Commande> list = new ArrayList<>();
        try {
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(requete);
            while (rs.next()) {
                if (rs.getString("statusCommande").equals(statusCommande.enCours.toString())) {
                    list.add(new Commande(rs.getInt("id_commande"), rs.getDate("dateCommande").toLocalDate(), statusCommande.enCours, rs.getDouble("prixTotale"), rs.getBoolean("estpayee")));
                } else if (rs.getString("statusCommande").equals(statusCommande.Annule.toString())) {
                    list.add(new Commande(rs.getInt("id_commande"), rs.getDate("dateCommande").toLocalDate(), statusCommande.Annule, rs.getDouble("prixTotale"), rs.getBoolean("estpayee")));
                } else {
                    list.add(new Commande(rs.getInt("id_commande"), rs.getDate("dateCommande").toLocalDate(), statusCommande.effectue, rs.getDouble("prixTotale"), rs.getBoolean("estpayee")));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Commande readById(int id) {
        Commande c = null;
        try {
            String req = "Select * from commande where id_commande = '" + id + "'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                if (rs.getString("statusCommande").equals(statusCommande.enCours)) {
                    c = new Commande(rs.getInt("id_commande"), rs.getDate("dateCommande").toLocalDate(), statusCommande.enCours, rs.getDouble("prixTotale"), rs.getBoolean("estpayee"));
                } else if (rs.getString("statusCommande").equals(statusCommande.Annule)) {
                    c = new Commande(rs.getInt("id_commande"), rs.getDate("dateCommande").toLocalDate(), statusCommande.Annule, rs.getDouble("prixTotale"), rs.getBoolean("estpayee"));
                } else {
                    c = new Commande(rs.getInt("id_commande"), rs.getDate("dateCommande").toLocalDate(), statusCommande.effectue, rs.getDouble("prixTotale"), rs.getBoolean("estpayee"));
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c;
    }
}
