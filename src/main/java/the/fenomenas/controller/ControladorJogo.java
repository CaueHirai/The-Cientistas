package the.fenomenas.controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import the.fenomenas.jogo.Atributo;
import the.fenomenas.jogo.Dificuldade;
import the.fenomenas.jogo.Partida;
import the.fenomenas.jogo.Resultado;
import the.fenomenas.model.AreaConhecimento;
import the.fenomenas.model.Mulher;
import the.fenomenas.service.AreaConhecimentoDAO;
import the.fenomenas.service.MulherDAO;

/**
 * Fluxo do jogo: início -> área, dificuldade e nome da carreira -> sorteio das cientistas
 * (roubar um atributo de cada uma) -> resultado.
 */
@Controller
public class ControladorJogo {

    private static final String CHAVE = "partida";
    private static final int TAMANHO_MAX_NOME = 40;

    private final MulherDAO mulherDAO;
    private final AreaConhecimentoDAO areaDAO;

    public ControladorJogo(MulherDAO mulherDAO, AreaConhecimentoDAO areaDAO) {
        this.mulherDAO = mulherDAO;
        this.areaDAO = areaDAO;
    }

    // 1) Página inicial, com o botão JOGAR
    @GetMapping("/")
    public String inicio() {
        return "primeira";
    }

    // 2) Escolha da área de conhecimento e do nome da carreira
    @RequestMapping(value = "/start", method = {RequestMethod.GET, RequestMethod.POST})
    public String start(HttpSession sessao, Model model) {
        List<AreaConhecimento> areas = areaDAO.listar();
        if (areas.isEmpty() || mulherDAO.listar().isEmpty()) {
            return erro(model, "Não há áreas de conhecimento ou cientistas cadastradas para jogar.");
        }
        sessao.setAttribute(CHAVE, new Partida());
        model.addAttribute("areas", areas);
        model.addAttribute("dificuldades", Dificuldade.values());
        return "escolha";
    }

    // 2.1) Recebe área + nome da carreira e sorteia a primeira cientista
    @PostMapping("/jogo/definir")
    public String definir(@RequestParam(required = false) Integer area,
            @RequestParam(defaultValue = "") String nomeCarreira,
            @RequestParam(required = false) String dificuldade,
            HttpSession sessao, Model model) {
        Dificuldade modo = Dificuldade.de(dificuldade);
        String nome = nomeCarreira.trim();
        if (nome.length() > TAMANHO_MAX_NOME) {
            nome = nome.substring(0, TAMANHO_MAX_NOME);
        }

        if (area == null || nome.isEmpty()) {
            model.addAttribute("areas", areaDAO.listar());
            model.addAttribute("dificuldades", Dificuldade.values());
            model.addAttribute("erro", "Escolha uma área e dê um nome à sua carreira.");
            return "escolha";
        }

        try {
            areaDAO.findByCodigo(area);
        } catch (NoSuchElementException ex) {
            model.addAttribute("areas", areaDAO.listar());
            model.addAttribute("dificuldades", Dificuldade.values());
            model.addAttribute("erro", "Área de conhecimento inválida.");
            return "escolha";
        }

        List<Integer> codigos = mulherDAO.listar().stream().map(Mulher::getCodigo).toList();
        if (codigos.isEmpty()) {
            return erro(model, "Não há cientistas cadastradas para jogar.");
        }

        Partida p = new Partida();
        p.definir(area, nome, modo, codigos);
        p.sortear();
        sessao.setAttribute(CHAVE, p);
        return "redirect:/jogo/sorteio";
    }

    // 3) Mostra a cientista sorteada para o jogador escolher o atributo a roubar
    @GetMapping("/jogo/sorteio")
    public String sorteio(HttpSession sessao, Model model) {
        Partida p = partida(sessao);
        if (p == null || !p.iniciada()) {
            return "redirect:/";
        }
        if (p.completa()) {
            return "redirect:/jogo/resultado";
        }

        Mulher mulher;
        try {
            mulher = mulherDAO.findByCodigo(p.getCodMulherAtual());
        } catch (NoSuchElementException ex) {
            return erro(model, "Não foi possível carregar a cientista sorteada.");
        }

        model.addAttribute("mulher", mulher);
        model.addAttribute("atributos", Atributo.values());
        model.addAttribute("escolhas", p.getEscolhas());
        model.addAttribute("feitos", p.getEscolhas().size());
        model.addAttribute("total", Atributo.values().length);
        model.addAttribute("nomeCarreira", p.getNomeCarreira());
        model.addAttribute("dificuldade", p.getDificuldade());
        model.addAttribute("rerolls", p.getRerollsRestantes());
        return "sorteio";
    }

    // 3.1) Registra o atributo roubado e sorteia a próxima cientista
    @PostMapping("/jogo/roubar")
    public String roubar(@RequestParam(required = false) String atributo,
            HttpSession sessao, Model model) {
        Partida p = partida(sessao);
        if (p == null || !p.iniciada()) {
            return "redirect:/";
        }
        if (p.completa()) {
            return "redirect:/jogo/resultado";
        }

        Atributo escolhido = Atributo.de(atributo);
        if (escolhido == null || p.jaEscolhido(escolhido)) {
            return "redirect:/jogo/sorteio";
        }

        try {
            Mulher mulher = mulherDAO.findByCodigo(p.getCodMulherAtual());
            p.registrar(escolhido, mulher.getNome(), escolhido.valorDe(mulher));
        } catch (NoSuchElementException ex) {
            return erro(model, "Não foi possível carregar a cientista sorteada.");
        }

        p.seguir();
        sessao.setAttribute(CHAVE, p);
        return p.completa() ? "redirect:/jogo/resultado" : "redirect:/jogo/sorteio";
    }

    // 3.3) Reroll (modo amador): troca a cientista sorteada
    @PostMapping("/jogo/reroll")
    public String reroll(HttpSession sessao) {
        Partida p = partida(sessao);
        if (p == null || !p.iniciada()) {
            return "redirect:/";
        }
        p.rerolar();
        sessao.setAttribute(CHAVE, p);
        return "redirect:/jogo/sorteio";
    }

    // 4) Página final com os dados da carreira montada
    @GetMapping("/jogo/resultado")
    public String resultado(HttpSession sessao, Model model) {
        Partida p = partida(sessao);
        if (p == null || !p.iniciada()) {
            return "redirect:/";
        }
        if (!p.completa()) {
            return "redirect:/jogo/sorteio";
        }

        try {
            AreaConhecimento area = areaDAO.findByCodigo(p.getCodArea());
            model.addAttribute("r", Resultado.calcular(p, area, areaDAO.listar()));
            model.addAttribute("dificuldade", p.getDificuldade());
            return "resultado";
        } catch (NoSuchElementException ex) {
            return erro(model, "A área escolhida não existe mais.");
        }
    }

    private Partida partida(HttpSession sessao) {
        Object o = sessao.getAttribute(CHAVE);
        return (o instanceof Partida p) ? p : null;
    }

    private String erro(Model model, String msg) {
        model.addAttribute("msg", msg);
        return "mensagem";
    }
}
