package com.Main;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import com.Contact.Contact;
import com.InOut.InOut;

public class Main {

    private static ArrayList<Contact> listaContatos = new ArrayList<Contact>();


    public static void main(String[] args)  throws IOException{
        menu();
    }

    public static void menu() throws IOException {
        int op;
        do{
            String opcoes =
                    "  AGENDA TELEFONICA FASOFT 2022\n"+
                    "\nEscolha uma das Opções Abaixo:\n\n"+
                    "1 - Cadastrar contato\n"+
                    "2 - Listar contato\n"+
                    "3 - Atualizar contato\n"+
                    "4 - Deletar contato\n"+
                    "5 - Deletar todos os contatos\n"+
                    "0 - Sair\n\n";
            op = InOut.InInt(opcoes);

            switch(op){
                case 0:
                    InOut.OutMessage("O programa será Finalizado", "Atenção");
                    break;
                case 1:
                    createContact();
                    break;
                case 2:
                    getContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    deleteAllContacts();
                    break;
                default:
                    InOut.OutMessage("Opção Invalida!", "Erro!");
                    break;
            }
        }while(op != 0);
    }

    public static void createContact() throws IOException{
        String nome = InOut.InString("Entre com o nome:\n");
        String profession = InOut.InString("Entre com sua Profissão: ");
        String city = InOut.InString("Entre com sua Cidade: ");
        String state = InOut.InString("Entre com seu Estado: ");
        int numero = InOut.InInt("Entre com o numero telefonico:\n");
        Contact contact = new Contact(nome,profession,city,state,numero);
        listaContatos.add(contact);
    }

    public static void getContact() throws IOException {

        if(listaContatos.isEmpty()){
            InOut.OutMessage("Nenhum Contato Cadastrado");
            return;
        }
        FileWriter arq = new FileWriter("listaContatos.txt");
        PrintWriter gravaArq = new PrintWriter(arq);
        String relatorio = "";
        gravaArq.printf("--------------------------------------\r\n");
        gravaArq.printf("          Agenda Fasoft 2022          \r\n");
        gravaArq.printf("--------------------------------------\r\n");

        for(int i = 0; i < listaContatos.size(); i++){
            Contact contato = listaContatos.get(i);
            gravaArq.printf(" - |CODIGO| %d |NOME| %s |PROFESSION| %s |CITY| %s |STATE| %s|NUMERO| %d\r\n",
                    contato.getCodigo(), contato.getNome(),contato.getProfession(),contato.getCity(),contato.getState(), contato.getNumber());

            relatorio += "\nCodigo: " + contato.getCodigo() +
                    "\nNome: " + contato.getNome() +
                    "\nProfissão: " + contato.getProfession() +
                    "\nCidade: " + contato.getCity() +
                    "\nEstado: " + contato.getState()+
                    "\n----------------------------------------------------------\r";
        }
        gravaArq.close();
        InOut.OutMessage(relatorio);
    }

    public static void updateContact(){

        try {
            if(listaContatos.size() == 0){
                InOut.OutMessage("Lista Vazia");
                return;
            }
            String nomeProdutoPesquisar = InOut.InString("Digite o Nome do Contato que deseja procurar:");
            for(int i=0; i < listaContatos.size(); i++){
                Contact contato = listaContatos.get(i);

                if(nomeProdutoPesquisar.equalsIgnoreCase(contato.getNome())){
                    String nomeNovo = InOut.InString("Digite o novo Nome do Contato:");
                    contato.setNome(nomeNovo);
                    String profissaoNovo = InOut.InString("Digite a nova Profissão do Contato:");
                    contato.setProfession(profissaoNovo);
                    String cidadeNovo = InOut.InString("Digite a nova cidade do Contato:");
                    contato.setCity(cidadeNovo);
                    String estadoNovo = InOut.InString("Digite o novo estado do Contato:");
                    contato.setState(estadoNovo);
                    InOut.OutMessage("Dados alterados com sucesso!");
                    break;
                }
            }
        }catch (Exception e){
            InOut.OutMessage("Nome não encontrado");
        }
    }

    public static void deleteContact(){
        if(listaContatos.size() == 0){
            InOut.OutMessage("Lista Vazia");
            return;
        }
        String nomeProdutoPesquisar = InOut.InString("Digite o Nome do Contato que deseja Deletar:");
        for(int i=0; i < listaContatos.size(); i++){
            Contact contato = listaContatos.get(i);

            if(nomeProdutoPesquisar.equalsIgnoreCase(contato.getNome())){
                listaContatos.remove(i);
                InOut.OutMessage("Contato deletado com Sucesso!");
                break;
            }
        }

    }

    public static void deleteAllContacts() {
        if(listaContatos.isEmpty()){
            InOut.OutMessage("Nenhum Contato Cadastrado");
            return;
        }
        listaContatos.clear();
        InOut.OutMessage("Todos os Contatos foram Apagados!");
    }




}
