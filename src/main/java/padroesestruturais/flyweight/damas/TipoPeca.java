package padroesestruturais.flyweight.damas;

public class TipoPeca {

    private final String nome;
    private final String aparencia;
    private final boolean andaParaTras;

    public TipoPeca(String nome, String aparencia, boolean andaParaTras) {
        this.nome = nome;
        this.aparencia = aparencia;
        this.andaParaTras = andaParaTras;
    }

    public String getNome() {
        return nome;
    }

    public String getAparencia() {
        return aparencia;
    }

    public boolean movimentoValido(Cor cor, int linhaOrigem, int colunaOrigem,
                                   int linhaDestino, int colunaDestino) {
        int deltaLinha = linhaDestino - linhaOrigem;
        int deltaColuna = colunaDestino - colunaOrigem;

        if (Math.abs(deltaLinha) != 1 || Math.abs(deltaColuna) != 1) {
            return false; // uma casa na diagonal
        }
        return andaParaTras || deltaLinha == cor.getDirecao();
    }
}
