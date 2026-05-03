package com.seguridadtech.modelo;

public class Cliente {
    private int idCliente;
    private String nombreCliente;
    private String documentoNit;
    private String direccion;
    private String telefono;
    private String correo;
    private String estado;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombreCliente, String documentoNit, String direccion,
                   String telefono, String correo, String estado) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.documentoNit = documentoNit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getDocumentoNit() { return documentoNit; }
    public void setDocumentoNit(String documentoNit) { this.documentoNit = documentoNit; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
