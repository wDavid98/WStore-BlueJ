import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class InventarioTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class InventarioTest
{
    /**
     * Default constructor for test class InventarioTest
     */
    public InventarioTest()
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

    @Test
    public void testAddNewProducts()
    {
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos.add(prod1);
        productos.add(prod2);
        Inventario inventario = new Inventario();
        inventario.addNewProducts(productos);
        assertEquals(2,inventario.getNextID());
        //Como se añadieron 2 items se espera que el siguiente ID sea 2
    }

    @Test
    public void testAddOldProducts()
    {
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos.add(prod1);
        productos.add(prod2);
        Inventario inventario = new Inventario();
        inventario.addNewProducts(productos);
        //Hasta aquí se crean nuevos productos, ahora vamos a editarlos
        Producto prod3 = inventario.productoAVender(0, 50);
        Producto prod4 = inventario.productoAVender(1,100);
        ArrayList<Producto> productos2 = new ArrayList<Producto>();
        productos2.add(prod3);
        productos2.add(prod4);
        inventario.addOldProducts(productos2);
        //Solo deben descontarse en su stock
        assertEquals(100,inventario.buscarProducto(0).getStock());
        assertEquals(200,inventario.buscarProducto(1).getStock());
    }

    @Test
    public void testVerificarID()
    {
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos.add(prod1);
        productos.add(prod2);
        Inventario inventario = new Inventario();
        inventario.addNewProducts(productos);
        assertEquals(false,inventario.verificarID(2));
        assertEquals(true,inventario.verificarID(0));
    }

    @Test
    public void testVerficiarExistencia()
    {
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos.add(prod1);
        productos.add(prod2);
        Inventario inventario = new Inventario();
        inventario.addNewProducts(productos);
        assertEquals(false,inventario.verificarExistencia(0, 200));
        assertEquals(true,inventario.verificarExistencia(0, 80));
        assertEquals(false,inventario.verificarExistencia(1, 301));
        assertEquals(true,inventario.verificarExistencia(1, 300));
    }

    @Test
    public void testBuscarProducto()
    {
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos.add(prod1);
        productos.add(prod2);
        Inventario inventario = new Inventario();
        inventario.addNewProducts(productos);
        assertEquals(150,inventario.buscarProducto(0).getStock());
        assertEquals(300,inventario.buscarProducto(1).getStock());
    }

    @Test
    public void testModificarProducto()
    {
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos.add(prod1);
        productos.add(prod2);
        Inventario inventario = new Inventario();
        inventario.addNewProducts(productos);
        inventario.ModificarProducto(0, "Arroz", 3500, 6000, 20);
        inventario.ModificarProducto(1, "-", 999, 999, 50);
        assertEquals("Arroz",inventario.buscarProducto(0).getNombre());
        assertEquals(3500,inventario.buscarProducto(0).getPrecio_Compra());
        assertEquals(6000,inventario.buscarProducto(0).getPrecio_Venta());
        assertEquals(20,inventario.buscarProducto(0).getStock());
        assertEquals("Leche",inventario.buscarProducto(1).getNombre());
        assertEquals(1200,inventario.buscarProducto(1).getPrecio_Compra());
        assertEquals(1500,inventario.buscarProducto(1).getPrecio_Venta());
        assertEquals(50,inventario.buscarProducto(1).getStock());
    }
}








