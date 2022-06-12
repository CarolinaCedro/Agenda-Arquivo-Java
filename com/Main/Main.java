package com.Main;

import java.util.ArrayList;
/*
* ArrayList é uma classe que introduz novas facilidades aos arrays.
*  Onde a principal “vantagem” é o fato de não ser necessário definir o tamanho na declaração
* / inicialização.
* */
//java.io é uma api do java que permite a manipulação de entrada e saida de dados
import java.io.BufferedReader;
//java.io.BufferedReader Lê texto de um fluxo de entrada de caracteres, armazenando caracteres em buffer para fornecer uma leitura eficiente de caracteres, matrizes e linhas.
import java.io.File;
//java.io.File É uma representação abstrata de nomes de caminhos de arquivos e diretórios.
import java.io.FileReader;
//java.io.FileReader em Java é uma classe que usamos para ler dados de um arquivo.
import java.io.FileWriter;
//java.io.FileWrite em Java é uma classe que usamos para escrever dados de um arquivo
import java.io.IOException;
//java.io.IOExceptionEssa exception ocorre quando algum sinal de entrada/saída falha ou é interrompido.
import java.io.PrintWriter;
//java.io.PrintWriter PrintWriter imprime representações formatadas de objetos em um fluxo como uma saída de texto.
import com.Contact.Contact; //pacote importado
import com.InOut.InOut;//pacote importado

public class Main {

    private static ArrayList<Contact> listaContatos = new ArrayList<Contact>(); //array list para contatos


    public static void main(String[] args)  throws IOException{
        menu(); //chamo o metodo menu
    }

    public static void menu() throws IOException {
        //metodo simples que cria a tela de menu
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
            op = InOut.InInt(opcoes); // eu mando pro pacote InOut os dados para fazer a representação em tela

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
        //InOut sendo chamado com o tipo de dados especifico que irá receber
        String nome = InOut.InString("Entre com o nome:\n");
        String profession = InOut.InString("Entre com sua Profissão: ");
        String city = InOut.InString("Entre com sua Cidade: ");
        String state = InOut.InString("Entre com seu Estado: ");
        int numero = InOut.InInt("Entre com o numero telefonico:\n");
        Contact contact = new Contact(nome,profession,city,state,numero);
        //construtor Contact criado recebendo os dados coletados
        listaContatos.add(contact); //array list sendo prenchido com os dados
    }

    public static void getContact() throws IOException {

        if(listaContatos.isEmpty()){
            //esse isEmpty verifica se uma variavel foi inicializada no contexto ai se não for ele vai retornar erro
            InOut.OutMessage("Nenhum Contato Cadastrado");
            return;
        }

        FileWriter arq = new FileWriter("listaContatos.txt"); // escreve no arquivo txt dados coletados pelo input
        PrintWriter gravaArq = new PrintWriter(arq);// Printa o  construtor criado acima arq com os dados e etc
        String relatorio = "";
        gravaArq.printf("--------------------------------------\r\n");//escreve no arquivo txt
        gravaArq.printf("          Agenda Fasoft 2022          \r\n");//escreve no arquivo txt
        gravaArq.printf("--------------------------------------\r\n");//escreve no arquivo txt

        for(int i = 0; i < listaContatos.size(); i++){ //percorre arraylist
            Contact contato = listaContatos.get(i); // pega dados do construtor iterado
            gravaArq.printf(" - |CODIGO| %d |NOME| %s |PROFESSION| %s |CITY| %s |STATE| %s|NUMERO| %d\r\n",
                    contato.getCodigo(), contato.getNome(),contato.getProfession(),contato.getCity(),contato.getState(), contato.getNumber());
            //escreve no arquivo txt e chama também os metodos de get ou seja puxam todos dados
            relatorio += "\nCodigo: " + contato.getCodigo() +
                    "\nNome: " + contato.getNome() +
                    "\nProfissão: " + contato.getProfession() +
                    "\nCidade: " + contato.getCity() +
                    "\nEstado: " + contato.getState()+
                    "\n----------------------------------------------------------\r";
            // aqui esse relatorio recebe todos os dados e imprime na tela do console pra interação em tela
        }
        gravaArq.close(); //chama o construtor e fecha arquivo
        InOut.OutMessage(relatorio);//Imprime na tela o relatorio
    }

    public static void updateContact(){

        try {
            if(listaContatos.size() == 0){
                InOut.OutMessage("Lista Vazia");
                return;
            }
            String nomeProdutoPesquisar = InOut.InString("Digite o Nome do Contato que deseja procurar:");
            for(int i=0; i < listaContatos.size(); i++){
                Contact contato = listaContatos.get(i); //chama o construtor passando a lista e ele trás todos os dados

                if(nomeProdutoPesquisar.equalsIgnoreCase(contato.getNome())){
                    //esse equalsIgnore vai comparar o nome digitado com as posiçoes da lista caso exista ele vai fazer as modificações
                    String nomeNovo = InOut.InString("Digite o novo Nome do Contato:");
                    contato.setNome(nomeNovo); //seta novos valores para os metodos com o novo dado
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
                listaContatos.remove(i);//remove os dados da lista indicado no input acima
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

        //aqui ao ser escolhido a opção de apagar tudo eu já chamo esse metodo que automaticamente apaga TUDO da lista
        listaContatos.clear();
        InOut.OutMessage("Todos os Contatos foram Apagados!");
    }




}
