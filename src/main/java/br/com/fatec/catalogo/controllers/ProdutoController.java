package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.repositories.ProdutoRepository;
import br.com.fatec.catalogo.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    /*@GetMapping
    public String listar(Model model){
        model.addAttribute("produtos", service.listarTodos());
        return "lista-produtos";
    }*/

    @GetMapping("/novo")
    public String exibirFormulario(Model model){
        model.addAttribute("produto", new ProdutoModel());
        return "cadastro-produto";
    }

    /*@PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("produto") ProdutoModel produto, BindingResult result){
        if (result.hasErrors()){
            return "cadastro-produto";
        }
        service.salvar(produto);
        return "redirect:/produtos";
    }*/

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("produto") ProdutoModel produto, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
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
    public String exibirEdicao(@PathVariable long id, Model model){
        model.addAttribute("produto", service.buscarPorId(id));
        return "editar-produto";
    }

    @PostMapping("/editar/{id}")
    public String alterar(@PathVariable Long id, @ModelAttribute ProdutoModel produto) {
        service.alterar(id, produto);
        return "redirect:/produtos";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable long id){
        service.excluir(id);
        return "redirect:/produtos";
    }

    @GetMapping
    public String listarProdutos(@RequestParam(required = false) String nome, Model model) {

        if (nome != null) {
            model.addAttribute("produtos", service.buscarPorNome(nome));
        } else {
            model.addAttribute("produtos", service.listarTodos());
        }

        model.addAttribute("nome", nome);
        return "lista-produtos";
    }
}
