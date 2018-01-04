
package proyecto;

import java.sql.Date;

public class Libro {
    
    int id;
    String nombre;
    String url;
    Date fecha;
    String tipo;

    public Libro(String nombre, String url, String tipo) {
        this.nombre = nombre;
        this.url = url;
        this.tipo = tipo;
    }

    public Libro(int id, String nombre, String url, Date fecha, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.url = url;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public Libro() {
 
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
   
    
    
    
}
