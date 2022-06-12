package com.Contact;
public class Contact {

    private int codigo;
    private String nome,profession,city,state;
    private int numero;

    private static int contadorContatos = 0;

    public Contact(){
        //criei esse contador só pra contar os contatos
        contadorContatos++;
        this.codigo = contadorContatos;
    }



    public Contact(String nome, String profession,String city,String state,int numero){
        contadorContatos++;
        this.codigo = contadorContatos;
        this.nome = nome;
        this.profession = profession;
        this.city =  city;
        this.state = state;
        this.numero = numero;
    }


    public int getCodigo() {
        return codigo;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getProfession(){
        return profession;
    }

    public void setProfession(String profession){
        this.profession = profession;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }


    public int getNumber() {
        return numero;
    }


    public void setNumber(int numero) {
        this.numero = numero;
    }
}
