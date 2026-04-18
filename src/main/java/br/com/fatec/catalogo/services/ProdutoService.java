package br.com.fatec.catalogo.services;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoModel> listarTodos(){
        return repository.findAll();
    }

    public ProdutoModel buscarPorId(long id){
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
    }

    @Transactional
    public boolean salvar(ProdutoModel produto){
        if (repository.existsByNomeIgnoreCase(produto.getNome())) {
            return false;
        }
        repository.save(produto);
        return true;
    }

    @Transactional
    public void excluir(long id){
        repository.deleteById(id);
    }

    @Transactional
    public void alterar(long id, ProdutoModel produtoAtualizado) {
        ProdutoModel produto = buscarPorId(id);
        produto.setNome(produtoAtualizado.getNome());
        produto.setValor(produtoAtualizado.getValor()); // BigDecimal, não double
        repository.save(produto);
    }

    public List<ProdutoModel> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            return List.of();
        }
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}
