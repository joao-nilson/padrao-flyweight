package padroesestruturais.flyweight.damas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TabuleiroTest {

    @Test
    void deveMontarTabuleiroInicialCom24Pecas() {
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.montarInicial();

        assertEquals(24, tabuleiro.getTotalPecas());
    }

    @Test
    void deveCompartilharMesmoTipoEntrePecasComuns() {
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.montarInicial();

        TipoPeca primeiro = tabuleiro.getPecas().get(0).getTipo();
        for (Peca peca : tabuleiro.getPecas()) {
            assertSame(primeiro, peca.getTipo());
        }
    }

    @Test
    void deveManterEstadoIndividualEmPecasQueCompartilhamTipo() {
        Peca branca = new Peca(Cor.BRANCA, 5, 0);
        Peca preta = new Peca(Cor.PRETA, 2, 1);

        assertSame(branca.getTipo(), preta.getTipo());
        assertEquals(Cor.BRANCA, branca.getCor());
        assertEquals(Cor.PRETA, preta.getCor());

        branca.mover(4, 1);

        assertEquals(4, branca.getLinha());
        assertEquals(1, branca.getColuna());
        assertEquals(2, preta.getLinha());
        assertEquals(1, preta.getColuna());
    }

    @Test
    void devePermitirPecaComumAndarApenasParaFrente() {
        Peca branca = new Peca(Cor.BRANCA, 4, 3);
        Peca preta = new Peca(Cor.PRETA, 3, 4);

        assertFalse(branca.mover(5, 4)); // para tras
        assertTrue(branca.mover(3, 4));  // para frente

        assertFalse(preta.mover(2, 3));  // para tras
        assertTrue(preta.mover(4, 3));   // para frente
    }

    @Test
    void naoDevePermitirMovimentoForaDaDiagonalOuComMaisDeUmaCasa() {
        Peca peca = new Peca(Cor.BRANCA, 4, 3);

        assertFalse(peca.mover(3, 3)); // reta
        assertFalse(peca.mover(2, 5)); // duas casas
    }

    @Test
    void devePermitirDamaAndarParaTras() {
        Peca dama = new Peca(Cor.BRANCA, 4, 3);
        dama.promover();

        assertTrue(dama.mover(5, 4));
        assertEquals(5, dama.getLinha());
        assertEquals(4, dama.getColuna());
    }

    @Test
    void deveVirarDamaAoChegarNaUltimaLinha() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Peca branca = tabuleiro.adicionar(Cor.BRANCA, 1, 2);
        Peca preta = tabuleiro.adicionar(Cor.PRETA, 6, 3);

        assertFalse(branca.isDama());
        assertFalse(preta.isDama());

        assertTrue(tabuleiro.mover(1, 2, 0, 1));
        assertTrue(tabuleiro.mover(6, 3, 7, 4));

        assertTrue(branca.isDama());
        assertTrue(preta.isDama());
    }

    @Test
    void deveUsarFlyweightDaDamaAoSerPromovida() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Peca branca = tabuleiro.adicionar(Cor.BRANCA, 1, 2);
        Peca preta = tabuleiro.adicionar(Cor.PRETA, 6, 3);
        Peca comum = tabuleiro.adicionar(Cor.BRANCA, 5, 0);

        tabuleiro.mover(1, 2, 0, 1);
        tabuleiro.mover(6, 3, 7, 4);

        assertSame(branca.getTipo(), preta.getTipo());
        assertNotSame(branca.getTipo(), comum.getTipo());
    }

    @Test
    void deveManterCorEPosicaoAoPromover() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Peca peca = tabuleiro.adicionar(Cor.BRANCA, 1, 2);

        tabuleiro.mover(1, 2, 0, 3);

        assertEquals(Cor.BRANCA, peca.getCor());
        assertEquals(0, peca.getLinha());
        assertEquals(3, peca.getColuna());
    }

    @Test
    void naoDeveMoverParaCasaOcupadaOuForaDoTabuleiro() {
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.adicionar(Cor.BRANCA, 4, 3);
        tabuleiro.adicionar(Cor.PRETA, 3, 2);
        tabuleiro.adicionar(Cor.BRANCA, 1, 0);

        assertFalse(tabuleiro.mover(4, 3, 3, 2)); // ocupada
        assertFalse(tabuleiro.mover(1, 0, 0, -1)); // fora do tabuleiro
        assertFalse(tabuleiro.mover(7, 7, 6, 6)); // sem peca na origem
    }

    @Test
    void deveCriarApenasDoisTiposDePeca() {
        Tabuleiro tabuleiro = new Tabuleiro();
        tabuleiro.montarInicial();
        tabuleiro.getPecas().get(0).promover();

        assertEquals(24, tabuleiro.getTotalPecas());
        assertEquals(2, TipoPecaFactory.getTotalTipos());
    }

    @Test
    void deveRetornarDescricaoDaPeca() {
        Tabuleiro tabuleiro = new Tabuleiro();
        Peca peca = tabuleiro.adicionar(Cor.PRETA, 6, 3);

        assertEquals("Peca{tipo='Comum', aparencia='o', cor='PRETA', linha=6, coluna=3}",
                peca.obterPeca());

        tabuleiro.mover(6, 3, 7, 4);

        assertEquals("Peca{tipo='Dama', aparencia='K', cor='PRETA', linha=7, coluna=4}",
                peca.obterPeca());
    }

}
