
package proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DBConnection extends javax.swing.JFrame {
    String host="localhost";
    String user ="root";
    String password="";
    String db="bibliotecalibros";
   public Connection con;
   public PreparedStatement estado;
   public ResultSet rs;
    Date date= new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String fecha = dateFormat.format(date); 
    
    public void conectar() throws SQLException{
        String url="jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+password;
        con = DriverManager.getConnection(url);
        System.out.println("conecte!");
    }
    
     public Connection conectar(int l) throws SQLException{
        String url="jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+password;
        con = DriverManager.getConnection(url);
        System.out.println("conecte!");
        return con;
    }
    
    public void desconectar() throws SQLException{
    if(con!=null){
    con.close();
            System.out.println("desconecte!");
    }
    
    }
    
    

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public PreparedStatement getEstado() {
        return estado;
    }

    public void setEstado(PreparedStatement estado) {
        this.estado = estado;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    
   public void guardar(Libro libro) throws SQLException{
       conectar();
    String sql= "insert into registro (nombre, url, fecha, tipo)"+"values (?,?,?,?)";
    
    try {
            estado= con.prepareStatement(sql);
            estado.setString(1,libro.getNombre());
            estado.setString(2,libro.getUrl());
            estado.setString(3,fecha);
            estado.setString(4,libro.getTipo());
            estado.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    desconectar();
    }
    
    public void eliminar(Libro libro) throws SQLException {
         conectar();
        String sql = "delete from registro where id = ?";
        try { 
            PreparedStatement pstmnt = con.prepareStatement(sql);
            pstmnt.setInt(1, libro.getId());
            pstmnt.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         desconectar();
    }
    
      private List<Libro> getAll() throws SQLException{
        conectar();
        ArrayList<Libro> libroList= new ArrayList<>();
        String sql="SELECT* from registro ORDER by fecha DESC ";
        estado = con.prepareStatement(sql);
        rs = estado.executeQuery();
        Libro libro;
        while(rs.next()){
            libro= new Libro(rs.getInt("id"),rs.getString("nombre"),rs.getString("url"),rs.getDate("fecha"),rs.getString("tipo"));
            libroList.add(libro);
        }
        desconectar();
        return libroList;
        
    }
    //meter los datos en el jtable 
    public void mostrarDatos(JTable tabla) throws SQLException{
    ArrayList<Libro> list =(ArrayList<Libro>) getAll();
    tabla.setDefaultRenderer(Object.class, new Render());
    tabla.setRowHeight(30);
    DefaultTableModel modelo=(DefaultTableModel)tabla.getModel();
     
    Object[] row = new Object[7];
    
    JButton btnAbrir=new JButton("Abrir");
    btnAbrir.setName("Abrir");
    
    JButton btnEditar = new JButton("Editar");
    btnEditar.setName("Editar");
    
    JButton btnEliminar=new JButton("Eliminar");
    btnEliminar.setName("Eliminar");
    
    for (int i =0; i<list.size(); i++){
    row[0]= list.get(i).getId();
    row[1]= list.get(i).getNombre();
    row[2]= list.get(i).getTipo();
    row[3]= list.get(i).getFecha();
    row[4]= btnAbrir;
    row[5]= btnEditar;
    row[6]= btnEliminar;
    modelo.addRow(row);
   
    }
    }
    
    

    
    
}

    
    
    

