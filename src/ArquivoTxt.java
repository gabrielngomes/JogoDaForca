import java.io.*;

public class ArquivoTxt {

    // criar os atributos da classe
    File _objArquivo;
    FileWriter _objEscritorArquivo;
    BufferedWriter _objBufferDeEscrita;
    PrintWriter _objEscritor;

    // instanciar no construtor
    public ArquivoTxt(String nomeDoArquivo) {
        try{
            this._objArquivo =  new File(nomeDoArquivo + ".txt");
            this._objEscritorArquivo = new FileWriter(_objArquivo);
            this._objBufferDeEscrita = new BufferedWriter(_objEscritorArquivo);
            this. _objEscritor = new PrintWriter(_objBufferDeEscrita, true);
        }
        catch(Exception e){
            System.out.println("Erro ao criar o arquivo");
        }
    }

    public void gravarTexto(String texto) {
        this._objEscritor.println(texto);
    }
}
