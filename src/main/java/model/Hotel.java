package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Hotel {
    String nomHotel;
    ArrayList<Habitacio> llistaHab = new ArrayList<Habitacio>();
    ArrayList<Client> llistaClients = new ArrayList<Client>();
    ArrayList<Reserva> llistaReservesPendents = new ArrayList<Reserva>();
    ArrayList<Reserva> llistaReservesConfirmades = new ArrayList<Reserva>();
    public Hotel() {
        super();
    }

    public String getNomHotel() {
        return nomHotel;
    }
    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }
    public ArrayList<Habitacio> getLlistaHab() {
        return llistaHab;
    }
    public void setLlistaHab(ArrayList<Habitacio> llistaHab) {
        this.llistaHab = llistaHab;
    }
    public ArrayList<Client> getLlistaClients() {
        return llistaClients;
    }
    public void setLlistaClients(ArrayList<Client> llistaClients) {
        this.llistaClients = llistaClients;
    }
    public ArrayList<Reserva> getLlistaReservesPendents() {
        return llistaReservesPendents;
    }
    public void setLlistaReservesPendents(ArrayList<Reserva> llistaReservesPendents) {
        this.llistaReservesPendents = llistaReservesPendents;
    }
    public ArrayList<Reserva> getLlistaReservesConfirmades() {
        return llistaReservesConfirmades;
    }
    public void setLlistaReservesConfirmades(ArrayList<Reserva> llistaReservesConfirmades) {
        this.llistaReservesConfirmades = llistaReservesConfirmades;
    }
    public ArrayList<String> castClients (){
        ArrayList<String> clients = new ArrayList<>();
        for (Client cli : llistaClients) {
            clients.add(cli.getDni()+";"+cli.getNom()+";"+cli.getCognoms());
        }
        return  clients;
    }

    public ArrayList<String> castReserves (ArrayList<Reserva> arrayReserva){
        ArrayList<String> reserves = new ArrayList<>();
        for (Reserva res : arrayReserva) {
            reserves.add(res.getClient().getDni()+";"+res.getHabitacio().getNumHabitacio()+";"+res.getDiaEntrada()+";"+res.getDiaSortida()+";"+res.getNumPersones());
        }
        return  reserves;
    }

    public ArrayList<String> castHabitacions (){
        ArrayList<String> habitacio = new ArrayList<>();
        for (Habitacio hab : llistaHab) {
            habitacio.add(hab.getNumHabitacio()+";"+hab.getNumPersones());
        }
        return  habitacio;
    }

    public void crearClientFitxer(ArrayList<String> contingutFitxer){
        for (String linia : contingutFitxer) {
            String[] contingutLinia = linia.split(";");
            Client cli = new Client(contingutLinia[1],contingutLinia[2],contingutLinia[0]);
            llistaClients.add(cli);
        }
    }

    public void crearHabitacioFitxer(ArrayList<String> contingutFitxer){
        for (String linia : contingutFitxer) {
            String[] contingutLinia = linia.split(";");
            Habitacio hab = new Habitacio(Integer.parseInt(contingutLinia[0]) ,Integer.parseInt(contingutLinia[1]));
            getLlistaHab().add(hab);
        }
    }

    public  void crearReservaPendent(ArrayList<String> contingutFitxer){
        System.out.println("ABANS FOR");
        for (String linia : contingutFitxer) {
            String[] contingutLinia = linia.split(";");
            for (Client cli : llistaClients) {
                if (cli.getDni().equalsIgnoreCase(contingutLinia[0])){
                    for (Habitacio hab : llistaHab) {
                        if (hab.getNumHabitacio()==Integer.parseInt(contingutLinia[1])){
                            String[] diaEntradaString = contingutLinia[2].split("-");
                            String[] diaSortidaString = contingutLinia[3].split("-");
                            LocalDate diaEntrada =LocalDate.of(Integer.parseInt(diaEntradaString[0]),Integer.parseInt(diaEntradaString[1]),Integer.parseInt(diaEntradaString[2]));
                            LocalDate diaSortida = LocalDate.of(Integer.parseInt(diaSortidaString[0]),Integer.parseInt(diaSortidaString[1]),Integer.parseInt(diaSortidaString[2]));
                            Reserva res = new Reserva(cli,diaEntrada,diaSortida,hab.getNumPersones());
                            res.setHabitacio(hab);
                            llistaReservesPendents.add(res);
                        }
                    }
                }
            }
        }
        System.out.println("DESPRES FOR");
    }

    public  void crearReservaConfirmada(ArrayList<String> contingutFitxer){
        for (String linia : contingutFitxer) {
            String[] contingutLinia = linia.split(";");
            for (Client cli : llistaClients) {
                if (cli.getDni().equalsIgnoreCase(contingutLinia[0])){
                    for (Habitacio hab : llistaHab) {
                        if (hab.getNumHabitacio()==Integer.parseInt(contingutLinia[1])){
                            String[] diaEntradaString = contingutLinia[2].split("-");
                            String[] diaSortidaString = contingutLinia[3].split("-");
                            LocalDate diaEntrada =LocalDate.of(Integer.parseInt(diaEntradaString[0]),Integer.parseInt(diaEntradaString[1]),Integer.parseInt(diaEntradaString[2]));
                            LocalDate diaSortida = LocalDate.of(Integer.parseInt(diaSortidaString[0]),Integer.parseInt(diaSortidaString[1]),Integer.parseInt(diaSortidaString[2]));
                            Reserva res = new Reserva(cli,diaEntrada,diaSortida,hab.getNumPersones());
                            res.setHabitacio(hab);
                            llistaReservesConfirmades.add(res);
                        }
                    }
                }
            }
        }
    }


}
