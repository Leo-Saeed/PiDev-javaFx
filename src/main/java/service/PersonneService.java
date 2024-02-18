package service;

import entities.Personne;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonneService implements IService<Personne> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pst;

    public PersonneService() {
        conn= DataSource.getInstance().getCnx();
    }


    public void add(Personne p){
        String requete="insert into personne (nom,prenom) values ('"+p.getNom()+"','"+p.getPrenom()+"')";

        try {
            ste=conn.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPst(Personne p){
        String redquete="insert into personne (nom,prenom) values (?,?)";
        try {
            pst=conn.prepareStatement(redquete);
            pst.setString(1,p.getNom());
            pst.setString(2,p.getPrenom());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Personne personne) {

    }

    @Override
    public void update(Personne personne) {

    }

    @Override
    public List<Personne> readAll() {
        String requete="select * from personne";
        List<Personne> list=new ArrayList<>();
        try {
            ste=conn.createStatement();
          ResultSet rs= ste.executeQuery(requete);
          while(rs.next()){
              list.add(new Personne(rs.getInt(1),rs.getString("nom"),rs.getString(3)));
          }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Personne readById(int id) {
        return null;
    }

}


















