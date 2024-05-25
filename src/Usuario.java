public class Usuario {
    private String nombreDeUsuario;
    private String contraseña;
    private String rol;

    public Usuario(String nombreDeUsuario, String contraseña, String rol) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }
    public Usuario(String nombreDeUsuario, String contraseña, String rol, boolean predeterminado) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.contraseña = contraseña;
        this.rol = rol;
        if (predeterminado) {
            System.out.println("Usuario predeterminado creado: " + nombreDeUsuario);
        }
    }

    public String getNombreDeUsuario() { return nombreDeUsuario; }
    public void setNombreDeUsuario(String nombreDeUsuario) { this.nombreDeUsuario = nombreDeUsuario; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public boolean verificarContraseña(String contraseña) {
        return this.contraseña.equals(contraseña);
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "nombreDeUsuario='" + nombreDeUsuario + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}
