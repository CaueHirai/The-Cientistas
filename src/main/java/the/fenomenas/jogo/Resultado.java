package the.fenomenas.jogo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import the.fenomenas.model.AreaConhecimento;

/**
 * Dados mostrados na página final.
 */
public record Resultado(String nomeCarreira, AreaConhecimento area, int ovr, int media, String titulo,
        List<Linha> linhas, List<NotaArea> ranking, String melhorArea) {

    public record Linha(String atributo, String cientista, int valor, int peso) {
    }

    public record NotaArea(String nome, int ovr) {
    }

    public static Resultado calcular(Partida p, AreaConhecimento escolhida, List<AreaConhecimento> todas) {
        Map<Atributo, Partida.Escolha> escolhas = p.getEscolhas();

        List<Linha> linhas = new ArrayList<>();
        int soma = 0;
        for (Atributo a : Atributo.values()) {
            Partida.Escolha e = escolhas.get(a);
            linhas.add(new Linha(a.getRotulo(), e.cientista(), e.valor(), a.pesoDe(escolhida)));
            soma += e.valor();
        }
        int media = Math.round((float) soma / Atributo.values().length);

        List<NotaArea> ranking = new ArrayList<>();
        for (AreaConhecimento area : todas) {
            ranking.add(new NotaArea(area.getNome(), ovr(escolhas, area)));
        }
        ranking.sort(Comparator.comparingInt(NotaArea::ovr).reversed());

        int ovr = ovr(escolhas, escolhida);
        String melhor = ranking.isEmpty() ? escolhida.getNome() : ranking.get(0).nome();
        return new Resultado(p.getNomeCarreira(), escolhida, ovr, media, titulo(ovr), linhas, ranking, melhor);
    }

    /** Média ponderada dos atributos roubados pelos pesos da área. */
    private static int ovr(Map<Atributo, Partida.Escolha> escolhas, AreaConhecimento area) {
        long numerador = 0;
        long denominador = 0;
        long simples = 0;
        for (Atributo a : Atributo.values()) {
            int valor = escolhas.get(a).valor();
            int peso = a.pesoDe(area);
            numerador += (long) valor * peso;
            denominador += peso;
            simples += valor;
        }
        if (denominador == 0) {
            return Math.round((float) simples / Atributo.values().length);
        }
        return Math.round((float) numerador / denominador);
    }

    private static String titulo(int ovr) {
        if (ovr >= 88) return "A CIENTISTA";
        if (ovr >= 84) return "CIENTISTA DE DESTAQUE";
        if (ovr >= 80) return "PESQUISADORA";
        return "NOVATA";
    }
}
