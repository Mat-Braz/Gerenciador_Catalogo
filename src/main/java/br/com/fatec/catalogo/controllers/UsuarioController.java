package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.UsuarioModel;
import br.com.fatec.catalogo.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("usuario", new UsuarioModel());
        return "cadastro-usuario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("usuario") UsuarioModel usuario,
                         BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "cadastro-usuario";
        }

        boolean salvo = service.salvar(usuario);

        if (!salvo) {
            model.addAttribute("erro", "Usuário '" + usuario.getUsername() + "' já está cadastrado!");
            return "cadastro-usuario";
        }

        model.addAttribute("sucesso", "Usuário cadastrado com sucesso!");
        model.addAttribute("usuario", new UsuarioModel()); // limpa o formulário
        return "cadastro-usuario";
    }
}