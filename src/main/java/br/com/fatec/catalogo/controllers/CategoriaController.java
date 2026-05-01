package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.CategoriaModel;
import br.com.fatec.catalogo.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // 🔹 ABRE A TELA
    @GetMapping("/novo")
    public String novaCategoria(Model model) {
        model.addAttribute("categoria", new CategoriaModel());
        return "cadastrar-categoria";
    }

    // 🔹 SALVA
    @PostMapping("/salvar")
    public String salvarCategoria(
            @Valid @ModelAttribute("categoria") CategoriaModel categoria,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "cadastrar-categoria";
        }

        categoriaRepository.save(categoria);
        model.addAttribute("sucesso", "Categoria cadastrada com sucesso!");
        model.addAttribute("categoria", new CategoriaModel());

        return "cadastrar-categoria";
    }
}