import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class VentaTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class VentaTest
{
    /**
     * Default constructor for test class VentaTest
     */
    public VentaTest()
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
    public void testClienteID()
    {
        Producto prod1 = new Producto("Aceite",1900,2400);
        Cliente cli1 = new Cliente(123,"NN1",35625,"dir1");
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        Date date = new Date();
        Venta venta = new Venta(1,date,productos);
        venta.setCliente(cli1);
        assertEquals("123",venta.getClienteID());
    }

    @Test
    public void testClienteNombre()
    {
        Producto prod1 = new Producto("Aceite",1900,2400);
        Cliente cli1 = new Cliente(123,"NN1",35625,"dir1");
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        Date date = new Date();
        Venta venta = new Venta(1,date,productos);
        venta.setCliente(cli1);
        assertEquals("NN1",venta.getClienteNombre());
    }

    @Test
    public void testTotal()
    {
        Producto prod1 = new Producto("Aceite",1900,2400);
        Cliente cli1 = new Cliente(123,"NN1",35625,"dir1");
        HashMap<Producto,Integer> productos = new HashMap<Producto,Integer>();
        productos.put(prod1,5);
        Date date = new Date();
        Venta venta = new Venta(1,date,productos);
        venta.setCliente(cli1);
        assertEquals(12000,venta.getTotal());
    }
}



