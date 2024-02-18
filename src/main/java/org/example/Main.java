package org.example;

import entities.Personne;
import entities.Commande;
import entities.statusCommande;
import service.PersonneService;
import service.CommandeService;
import utils.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {



    public static void main(String[] args) {

        Personne p1=new Personne("3A16","Elyes");
        Commande c = new Commande(3,LocalDate.now() , statusCommande.Annule ,77.7 , true  );
        PersonneService ps=new PersonneService();
        CommandeService cs = new CommandeService();
       /* cs.add(c);
        cs.add(c);
        cs.add(c);*/
        //cs.update(c);
        System.out.println(cs.readById(3));
        //cs.readAll().forEach(System.out::println);
    }
}