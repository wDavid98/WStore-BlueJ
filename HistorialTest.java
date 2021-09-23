
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HistorialTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class HistorialTest
{
    /**
     * Default constructor for test class HistorialTest
     */
    public HistorialTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    /*Validaremos la creación de ventas y compras de forma 
        sencilla a partir de sus caracteristicas ya que no poseen la misma 
        dirección en memoria y solo queremos probar que las cree*/
    @Test
    public void testCrearVenta()
    {
        Date date = new Date(); 
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        Venta venta = new Venta(0,date,productos);
        Historial historial = new Historial();
        assertEquals(venta.getID(),historial.crearVenta(productos).getID());
        assertEquals(venta.getTotal(),historial.crearVenta(productos).getTotal());
    }

    @Test
    public void testCrearCompra()
    {
        Date date = new Date(); 
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        productos.put(prod2,10);
        Compra compra = new Compra(0,date,productos);
        Historial historial = new Historial();  
        assertEquals(compra.getID(),historial.crearCompra(productos).getID());
        assertEquals(compra.getTotal(),historial.crearCompra(productos).getTotal());
    }   

    @Test
    public void testVerificarIDCompra()
    {
        Date date = new Date(); 
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        HashMap<Producto,Integer> productos2 = new HashMap<Producto,Integer>();
        productos2.put(prod2,10);
        Historial historial = new Historial();      
        historial.agregarCompra(historial.crearCompra(productos));
        historial.agregarCompra(historial.crearCompra(productos2));
        assertEquals(2,historial.getIDCompra());
        //Se espera que el nuevo ID sea 2 porque se agregaron 2 compras
    }

    @Test
    public void testBuscarCompra()
    {
        Date date = new Date(); 
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        HashMap<Producto,Integer> productos2 = new HashMap<Producto,Integer>();
        productos2.put(prod2,10);
        Historial historial = new Historial();      
        historial.agregarCompra(historial.crearCompra(productos));
        historial.agregarCompra(historial.crearCompra(productos2));
        assertEquals(null,historial.buscarCompra(2));
        assertEquals(12000,historial.buscarCompra(1).getTotal());
    }

    @Test
    public void testEliminarCompra()
    {
        Date date = new Date(); 
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        HashMap<Producto,Integer> productos2 = new HashMap<Producto,Integer>();
        productos2.put(prod2,10);
        Historial historial = new Historial();      
        historial.agregarCompra(historial.crearCompra(productos));
        historial.agregarCompra(historial.crearCompra(productos2));
        historial.eliminarCompra(0);
        assertEquals(null,historial.buscarCompra(0));
    }

    @Test
    public void verificarIDVenta()
    {
        Date date = new Date(); 
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        HashMap<Producto,Integer> productos2 = new HashMap<Producto,Integer>();
        productos2.put(prod2,10);
        Historial historial = new Historial();      
        historial.agregarVenta(historial.crearVenta(productos));
        historial.agregarVenta(historial.crearVenta(productos2));
        assertEquals(2,historial.getIDVenta());
        //Se espera que el nuevo ID sea 2 porque se agregaron 2 ventas
    }

    @Test
    public void testBuscarVenta()
    {
        Date date = new Date(); 
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        HashMap<Producto,Integer> productos2 = new HashMap<Producto,Integer>();
        productos2.put(prod2,10);
        Historial historial = new Historial();      
        historial.agregarVenta(historial.crearVenta(productos));
        historial.agregarVenta(historial.crearVenta(productos2));
        assertEquals(null,historial.buscarVenta(2));
        assertEquals(15000,historial.buscarVenta(1).getTotal());
    }

    @Test
    public void testEliminarVenta()
    {
        Date date = new Date(); 
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        HashMap<Producto,Integer> productos2 = new HashMap<Producto,Integer>();
        productos2.put(prod2,10);
        Historial historial = new Historial();      
        historial.agregarVenta(historial.crearVenta(productos));
        historial.agregarVenta(historial.crearVenta(productos2));
        historial.eliminarVenta(1);
        assertEquals(null,historial.buscarVenta(1));
    }
}










