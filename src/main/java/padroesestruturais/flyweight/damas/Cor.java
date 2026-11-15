package padroesestruturais.flyweight.damas;

public enum Cor {

    BRANCA(-1), // brancas sobem no tabuleiro (linha diminui)
    PRETA(1);   // pretas descem no tabuleiro (linha aumenta)

    private final int direcao;

    Cor(int direcao) {
        this.direcao = direcao;
    }

    public int getDirecao() {
        return direcao;
    }
}
