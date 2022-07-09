import javax.swing.*;
import java.util.Random;

public class Forca {

    private static final String[] palavras = {"cervo", "rei", "terra", "lobo", "asa", "ostra", "ovo", "exemplo", "astro", "jogo"}; // criacao do vetor com 10 palavras
    private static byte erros = 0;
    private static String palavra;
    private static String[] resposta;
    private static final ArquivoTxt forcaTxt = new ArquivoTxt("forca");

    public static void main(String[] args) {

        System.out.println("Desenvolvido por: Allan Cesar da Silva Alves\nElisangela da Rocha Cardoso\nGabriel Nascimento Gomes\nLillian Nascimento Serrano");

        alterarBotoes();
        JOptionPane.showMessageDialog(null, "Bem vindo ao Jogo da Forca!");

        byte novoJogo;

        do {

            palavra = sortearPalavra();
            resposta = new String [palavra.length()];

            for (byte i = 0; i < palavra.length(); i++) {
                resposta[i] = "_";
            }

            novaPartida();

            novoJogo = (byte) JOptionPane.showConfirmDialog(null, "Deseja jogar novamente?", "Selecione uma opção", JOptionPane.YES_NO_OPTION);

        } while (novoJogo == 0);

    }

    private static void alterarBotoes() {  // alteração do idioma dos botões
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");
        UIManager.put("OptionPane.noButtonText", "Não");
        UIManager.put("OptionPane.yesButtonText", "Sim");
    }

    public static String sortearPalavra() { // sorteia uma palavra do vetor
        Random sorteio = new Random(); // criacao do objeto randomico para sortear um numero de 0 a 9 no indice do vetor
        byte n = (byte) sorteio.nextInt(palavras.length - 1); // delimitar que o sorteio devera ser feito com o limite ate o tamanho do vetor
        return palavras[n];
    }

    public static void novaPartida() { // inicia nova partida pedindo a letra do usuário, verifica se ela está presente na palavra, quantas vezes ela aparece e verifica se o usuário acertou todas as letras

        try {

            char letra = (JOptionPane.showInputDialog("Digite uma letra:").charAt(0));

            byte contador = 0, qtd = 0;
            byte qtdEncontradaNaPalavra = validarLetra(contador, letra, qtd);

            if (qtdEncontradaNaPalavra == 0) {
                forcaTxt.gravarTexto(letra + "- palpite incorreto");
                erros++;

            } else {
                forcaTxt.gravarTexto(letra + " - palpite correto");
            }

            enforcar(erros);

            boolean acertouTudo = true;

            for (String s : resposta) {
                if (s.equals("_")) {
                    acertouTudo = false;
                    break;
                }
            }

            if (acertouTudo) {
                JOptionPane.showMessageDialog(null, "Venceu!");
                return;
            }

            if (erros >= 6) {
                JOptionPane.showMessageDialog(null, "Você perdeu!");
                return;
            }

            novaPartida();

            erros = 0;
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,"Jogo cancelado");
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Nenhuma letra digitada");
            novaPartida();
        }
    }

    public static byte validarLetra(byte posicao, char letra, byte qtd) { // compara a posicao da letra digitada com as letras da palavra

        while (!Character.isAlphabetic(letra)) {
            JOptionPane.showMessageDialog(null, "Por favor, digite apenas letras");
            letra = (JOptionPane.showInputDialog("Digite uma letra:").charAt(0));
        }

        if (letra == palavra.charAt(posicao)) {
            resposta[posicao] = "" + letra;
            qtd++;
        }

        posicao++;

        if (posicao < palavra.length()) {
            qtd = validarLetra(posicao, letra, qtd); // recursividade
        }

        return qtd;
    }

    //palavra.replace(letra, letra);

    public static void enforcar(int erros) { // exibe a forca e as letras acertadas

        String resultado = String.join(" ", resposta);

        switch (erros) {
            case 0 -> JOptionPane.showMessageDialog(null, String.format("""
                      _ _ _ _ _ 
                     |               | 
                     |             
                     |
                     |
                     |
                    ---       %s""", resultado));
            case 1 -> JOptionPane.showMessageDialog(null, String.format("""
                      _ _ _ _ _ 
                     |               | 
                     |             ('-')
                     |
                     |
                     |
                    ---       %s""", resultado));
            case 2 -> JOptionPane.showMessageDialog(null, String.format("""
                      _ _ _ _ _ 
                     |               | 
                     |            \\('-')
                     |
                     |
                     |
                    ---       %s""", resultado));
            case 3 -> JOptionPane.showMessageDialog(null, String.format("""
                      _ _ _ _ _ 
                     |               | 
                     |            \\('-')/
                     |
                     |
                     |
                    ---       %s""", resultado));
            case 4 -> JOptionPane.showMessageDialog(null, String.format("""
                      _ _ _ _ _ 
                     |               | 
                     |            \\('-')/
                     |               | |
                     |
                     |
                    ---       %s""", resultado));
            case 5 -> JOptionPane.showMessageDialog(null, String.format("""
                      _ _ _ _ _ 
                     |               | 
                     |            \\('-')/
                     |               | |
                     |             _/ 
                     |
                    ---       %s""", resultado));
            case 6 -> JOptionPane.showMessageDialog(null, String.format("""
                      _ _ _ _ _ 
                     |               | 
                     |            \\('-')/
                     |               | |
                     |             _/  \\_ 
                     |
                    ---       %s""", resultado));
        }
    }
}
