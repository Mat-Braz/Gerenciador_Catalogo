package br.com.fatec.catalogo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name = "TB_USUARIO")
public class UsuarioModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank(message = "O usuário é obrigatório.")
    @Size(min = 3, max = 50, message = "O usuário deve ter entre 3 e 50 caracteres.")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 5, message = "A senha deve ter no mínimo 5 caracteres.")
    private String password;

    @NotBlank(message = "O perfil é obrigatório.")
    private String role; // USER ou ADMIN

    public UsuarioModel() {}

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
