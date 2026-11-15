package padroesestruturais.flyweight.damas;

import java.util.HashMap;
import java.util.Map;

public class TipoPecaFactory {

    private static final String COMUM = "Comum";
    private static final String DAMA = "Dama";

    private static Map<String, TipoPeca> tipos = new HashMap<>();

    public static TipoPeca getComum() {
        return tipos.computeIfAbsent(COMUM, nome -> new TipoPeca(nome, "o", false));
    }

    public static TipoPeca getDama() {
        return tipos.computeIfAbsent(DAMA, nome -> new TipoPeca(nome, "K", true));
    }

    public static int getTotalTipos() {
        return tipos.size();
    }

}
