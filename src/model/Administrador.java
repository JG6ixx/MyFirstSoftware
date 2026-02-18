package model;

public class Administrador {
	  private String usuario;
	    private String senha;

	    public Administrador(String usuario, String senha) {
	        this.usuario = usuario;
	        this.senha = senha;
	    }

	    public boolean autenticar(String senhaDigitada) {
	        return senha.equals(senhaDigitada);
	    }

	    public String getUsuario() {
	        return usuario;
	    }
}

