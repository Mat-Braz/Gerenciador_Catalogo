package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.UsuarioModel;
import br.com.fatec.catalogo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public boolean salvar(UsuarioModel usuario) {
        if (repository.existsByUsername(usuario.getUsername())) {
            return false;
        }
        // {noop} para manter o padrão atual sem criptografia
        usuario.setPassword("{noop}" + usuario.getPassword());
        repository.save(usuario);
        return true;
    }
}