package padroesestruturais.flyweight.damas;

import java.util.ArrayList;
import java.util.List;

public class Tabuleiro {

    public static final int TAMANHO = 8;

    private List<Peca> pecas = new ArrayList<>();

    public void montarInicial() {
        for (int linha = 0; linha < TAMANHO; linha++) {
            for (int coluna = 0; coluna < TAMANHO; coluna++) {
                if ((linha + coluna) % 2 == 0) {
                    continue; // pecas ficam apenas nas casas escuras
                }
                if (linha < 3) {
                    adicionar(Cor.PRETA, linha, coluna);
                } else if (linha > 4) {
                    adicionar(Cor.BRANCA, linha, coluna);
                }
            }
        }
    }

    public Peca adicionar(Cor cor, int linha, int coluna) {
        Peca peca = new Peca(cor, linha, coluna);
        pecas.add(peca);
        return peca;
    }

    public Peca obterPeca(int linha, int coluna) {
        for (Peca peca : pecas) {
            if (peca.getLinha() == linha && peca.getColuna() == coluna) {
                return peca;
            }
        }
        return null;
    }

    public boolean mover(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        Peca peca = obterPeca(linhaOrigem, colunaOrigem);
        if (peca == null || !dentroDoTabuleiro(linhaDestino, colunaDestino)
                || obterPeca(linhaDestino, colunaDestino) != null) {
            return false;
        }
        if (!peca.mover(linhaDestino, colunaDestino)) {
            return false;
        }
        if (linhaDestino == linhaPromocao(peca.getCor())) {
            peca.promover(); // passa a usar o flyweight da dama
        }
        return true;
    }

    public int getTotalPecas() {
        return pecas.size();
    }

    public List<Peca> getPecas() {
        return pecas;
    }

    private boolean dentroDoTabuleiro(int linha, int coluna) {
        return linha >= 0 && linha < TAMANHO && coluna >= 0 && coluna < TAMANHO;
    }

    private int linhaPromocao(Cor cor) {
        return cor == Cor.BRANCA ? 0 : TAMANHO - 1;
    }
}
