package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.CategoriaModel;
import br.com.fatec.catalogo.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaModel> listarTodas() {
        return repository.findAll();
    }

    public CategoriaModel buscarPorId(Long id) {
        Optional<CategoriaModel> obj = repository.findById(id);
        return obj.orElse(null); // Ou lançar uma exception customizada
    }

    public CategoriaModel salvar(CategoriaModel categoria) {
        return repository.save(categoria);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}