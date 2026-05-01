package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.services.CategoriaService;
import br.com.fatec.catalogo.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("produto", new ProdutoModel());
        // Envia as categorias para o <select> do formulário
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "cadastro-produto";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("produto") ProdutoModel produto,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // Se houver erro, precisamos recarregar as categorias antes de voltar à tela
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "cadastro-produto";
        }

        boolean salvo = service.salvar(produto);

        if (!salvo) {
            redirectAttributes.addFlashAttribute("erro", "Produto '" + produto.getNome() + "' já está cadastrado!");
            return "redirect:/produtos/novo";
        }

        redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
        return "redirect:/produtos";
    }

    @GetMapping("/editar/{id}")
    public String exibirEdicao(@PathVariable long id, Model model) {
        model.addAttribute("produto", service.buscarPorId(id));
        // Também envia aqui para permitir a troca de categoria na edição
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "editar-produto";
    }

    @PostMapping("/editar/{id}")
    public String alterar(@PathVariable Long id,
                          @Valid @ModelAttribute("produto") ProdutoModel produto,
                          BindingResult result,
                          Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "editar-produto";
        }

        service.alterar(id, produto);
        return "redirect:/produtos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable long id) {
        service.excluir(id);
        return "redirect:/produtos";
    }

    @GetMapping
    public String listarProdutos(@RequestParam(required = false) String nome,
                                 @RequestParam(required = false) Long categoriaId, // Adicionado para o filtro
                                 Model model) {

        // Adicionamos a lista de categorias para preencher o select de busca no HTML
        model.addAttribute("categorias", categoriaService.listarTodas());

        if (nome != null && !nome.isEmpty()) {
            model.addAttribute("produtos", service.buscarPorNome(nome));
        } else {
            model.addAttribute("produtos", service.listarTodos());
        }

        model.addAttribute("nome", nome);
        return "lista-produtos";
    }
}