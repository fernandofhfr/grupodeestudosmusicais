package br.com.androidpro.grupodeestudosmusicais.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.com.androidpro.grupodeestudosmusicais.config.ConfiguracaoFirebase;
import br.com.androidpro.grupodeestudosmusicais.helper.UsuarioFirebase;

public class Musico implements Serializable {

    private String idMusico;
    private String Nome;
    private String foto;
    private String dataNascimento;
    private String email;
    private String telefone;
    private String cep;
    private String numero;
    private String complemento;
    private String comumCongregacao;
    private String dataBatismo;
    private Boolean selado;
    private String instrumento;
    private String status;

    public Musico() {
        DatabaseReference musicoRef = ConfiguracaoFirebase.getFirebase()
                .child("musico");
    }

    public void atualizar(){

        String identificadorUsuario = UsuarioFirebase.getIdentificadorUsuario();
        DatabaseReference database = ConfiguracaoFirebase.getFirebase();

        DatabaseReference usuariosRef = database.child("usuarios")
                .child( identificadorUsuario );

        Map<String, Object> valoresUsuario = converterParaMap();

        usuariosRef.updateChildren( valoresUsuario );

    }

    @Exclude
    public Map<String, Object> converterParaMap(){

        HashMap<String, Object> usuarioMap = new HashMap<>();
        usuarioMap.put("email", getEmail() );
        usuarioMap.put("nome", getNome() );
        usuarioMap.put("foto", getFoto() );

        return usuarioMap;

    }

    public void salvar(){

        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference musicoRef = ConfiguracaoFirebase.getFirebase()
                .child("musico");
    }

    public void remover(){

        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference musicoRef = ConfiguracaoFirebase.getFirebase()
                .child("musico");

        musicoRef.removeValue();
    }

    public String getIdMusico() {
        return idMusico;
    }

    public void setIdMusico(String idMusico) {
        this.idMusico = idMusico;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getComumCongregacao() {
        return comumCongregacao;
    }

    public void setComumCongregacao(String comumCongregacao) {
        this.comumCongregacao = comumCongregacao;
    }

    public String getDataBatismo() {
        return dataBatismo;
    }

    public void setDataBatismo(String dataBatismo) {
        this.dataBatismo = dataBatismo;
    }

    public Boolean getSelado() {
        return selado;
    }

    public void setSelado(Boolean selado) {
        this.selado = selado;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
