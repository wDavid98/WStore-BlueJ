import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CompraTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CompraTest
{
    /**
     * Default constructor for test class CompraTest
     */
    public CompraTest()
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
    public void testNombreProveedor()
    {
        Producto prod1 = new Producto("Aceite",1900,2400);
        Proveedor prov1 = new Proveedor(1255,"PP1",35625,"dir1");
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        Date date = new Date();
        Compra compra = new Compra(1,date,productos);
        compra.setProveedor(prov1);
        assertEquals("PP1",compra.getProveedorNombre());
    }

    @Test
    public void testIDProveedor()
    {
        Producto prod1 = new Producto("Aceite",1900,2400);
        Proveedor prov1 = new Proveedor(1255,"PP1",35625,"dir1");
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        Date date = new Date();
        Compra compra = new Compra(1,date,productos);
        compra.setProveedor(prov1);
        assertEquals("1255",compra.getProveedorID());
    }

    @Test
    public void testTotal()
    {
        Producto prod1 = new Producto("Aceite",1900,2400);
        Proveedor prov1 = new Proveedor(1255,"PP1",35625,"dir1");
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        Date date = new Date();
        Compra compra = new Compra(0,date,productos);
        compra.setProveedor(prov1);
        assertEquals(9500,compra.getTotal());
    }
}



