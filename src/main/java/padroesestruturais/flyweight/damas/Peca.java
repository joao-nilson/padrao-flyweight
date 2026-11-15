package padroesestruturais.flyweight.damas;

public class Peca {

    private final Cor cor;
    private int linha;
    private int coluna;

    private TipoPeca tipo;

    public Peca(Cor cor, int linha, int coluna) {
        this.cor = cor;
        this.linha = linha;
        this.coluna = coluna;
        this.tipo = TipoPecaFactory.getComum();
    }

    public boolean mover(int linhaDestino, int colunaDestino) {
        if (!tipo.movimentoValido(cor, linha, coluna, linhaDestino, colunaDestino)) {
            return false;
        }
        this.linha = linhaDestino;
        this.coluna = colunaDestino;
        return true;
    }

    public void promover() {
        this.tipo = TipoPecaFactory.getDama();
    }

    public boolean isDama() {
        return tipo == TipoPecaFactory.getDama();
    }

    public TipoPeca getTipo() {
        return tipo;
    }

    public Cor getCor() {
        return cor;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public String obterPeca() {
        return "Peca{" +
                "tipo='" + tipo.getNome() + '\'' +
                ", aparencia='" + tipo.getAparencia() + '\'' +
                ", cor='" + cor + '\'' +
                ", linha=" + linha +
                ", coluna=" + coluna +
                '}';
    }
}
