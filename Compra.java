import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;   
/**
 * Write a description of class Compra here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Compra
{   
    // instance variables - replace the example below with your own
    private int ID;
    private Date fecha;
    static private Proveedor proveedor;
    private HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();   
    private int total;  

    /** 
     * Constructor for objects of class Compra
     */
    public Compra(int id,Date fech,HashMap<Producto,Integer> productos)
    {   
        // initialise instance variable
        ID = id;    
        fecha = fech;
        calcularTotal(productos);
    }   
    
    public void setProveedor(Proveedor prov)
    {   
        proveedor = prov;
    }

    public void añadirProducto(Producto prod,int cnt)
    {
       productos.put(prod,cnt);
    }
    
    public int getID()  
    {
        return ID;
    }
    
    public Date getFecha()  
    {
        return fecha;
    }
    
    public static String getProveedorNombre()
    {
        String a = "Null";
        if(proveedor == null)
        {
            a = "Null";
        }
        else
        {
            a = proveedor.getNombre();
        }
        return a;
    }   
    
    public String getProveedorID()  
    {
        String a = "Null";
        if(proveedor == null)
        {
            a = "Null";
        }
        else
        {
            a = ""+proveedor.getNID();
        }
        return a;
    }   
    
    private void calcularTotal(HashMap<Producto,Integer> prd)    
    {
        for(Map.Entry<Producto,Integer> data : prd.entrySet())
        {
            total += data.getKey().getPrecio_Compra()*data.getValue();
        }
    }
    
    public int getTotal()   
    {
        return total;
    }
    
    public void imprimir()  
    {   
        System.out.println("---------------------------");
        System.out.println("ID Compra: "+getID());
        System.out.println("Fecha Compra: "+getFecha());
        System.out.println("Proveedor: "+getProveedorNombre()+" -- "+getProveedorID());
        System.out.println("Productos: ");
        System.out.println(" ID -   Nombre  -   Cantidad ");
        for(Map.Entry<Producto,Integer> data : productos.entrySet())
        {
            System.out.println(data.getKey().getID()+"  -  "+data.getKey().getNombre()+" - "+data.getValue());
        }
        System.out.println("---------------------------");
    }
   
}
