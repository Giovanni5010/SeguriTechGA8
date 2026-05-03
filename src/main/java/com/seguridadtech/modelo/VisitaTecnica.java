package com.seguridadtech.modelo;

public class VisitaTecnica {
    private int idVisita;
    private int idCliente;
    private int idUsuario;
    private String nombreCliente;
    private String nombreTecnico;
    private String fechaVisita;
    private String motivo;
    private String descripcion;
    private String observaciones;
    private String estado;

    public VisitaTecnica() {
    }

    public VisitaTecnica(int idVisita, int idCliente, int idUsuario, String nombreCliente, String nombreTecnico,
                         String fechaVisita, String motivo, String descripcion, String observaciones, String estado) {
        this.idVisita = idVisita;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.nombreCliente = nombreCliente;
        this.nombreTecnico = nombreTecnico;
        this.fechaVisita = fechaVisita;
        this.motivo = motivo;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.estado = estado;
    }

    public int getIdVisita() { return idVisita; }
    public void setIdVisita(int idVisita) { this.idVisita = idVisita; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public String getNombreTecnico() { return nombreTecnico; }
    public void setNombreTecnico(String nombreTecnico) { this.nombreTecnico = nombreTecnico; }

    public String getFechaVisita() { return fechaVisita; }
    public void setFechaVisita(String fechaVisita) { this.fechaVisita = fechaVisita; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
