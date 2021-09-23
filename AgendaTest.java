import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class AgendaTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AgendaTest
{
    /**
     * Default constructor for test class AgendaTest
     */
    public AgendaTest()
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
    
    /*
     * Verificar el ID permite provar otros métodos como
     * provNuevo()
     * ClienteNuevo()
     * agregarProveedor()
     * agregarCliente()
     */
    @Test
    public void testVerfificarIDProveedor()
    {
        Agenda agenda = new Agenda();
        Proveedor prov1 = agenda.provNuevo(1255,"PP1",35625,"dir1");
        Proveedor prov2 = agenda.provNuevo(5545,"PP2",35625,"dir2");
        Proveedor prov3 = new Proveedor(6876,"PP2",35625,"dir3");
        agenda.addProveedor(prov1);
        agenda.addProveedor(prov2);
        agenda.addProveedor(prov3);
        assertEquals(false,agenda.existeProveedor(3000));
        assertEquals(false,agenda.existeProveedor(1032));
        assertEquals(true,agenda.existeProveedor(1255));
        assertEquals(true,agenda.existeProveedor(6876));
    }

    @Test
    public void testVerfificarIDClientes()
    {
        Agenda agenda = new Agenda();
        Cliente cli1 = agenda.clienteNuevo(123,"NN1",35625,"dir1");
        Cliente cli2 = new Cliente(456,"NN2",35625,"dir3");
        Cliente cli3 = agenda.clienteNuevo(789,"NN3",35625,"dir2");
        agenda.addCliente(cli1);
        agenda.addCliente(cli2);
        agenda.addCliente(cli3);
        assertEquals(false,agenda.existeCliente(1235));
        assertEquals(false,agenda.existeCliente(4521));
        assertEquals(true,agenda.existeCliente(456));
        assertEquals(true,agenda.existeCliente(123));
    }

    @Test
    public void testBuscarCliente()
    {
        Agenda agenda = new Agenda();
        Cliente cli1 = new Cliente(123,"NN1",35625,"dir1");
        Cliente cli2 = new Cliente(456,"NN2",35625,"dir3");
        Cliente cli3 = new Cliente(789,"NN3",35625,"dir2");
        agenda.addCliente(cli1);
        agenda.addCliente(cli2);
        agenda.addCliente(cli3);
        assertEquals("NN1",agenda.buscarCliente(123).getNombre());
        assertEquals("NN3",agenda.buscarCliente(789).getNombre());
        assertEquals(null,agenda.buscarCliente(5));
    }

    @Test
    public void testBuscarProveedor()
    {
        Agenda agenda = new Agenda();
        Proveedor prov1 = new Proveedor(1255,"PP1",35625,"dir1");
        Proveedor prov2 = new Proveedor(5545,"PP2",35625,"dir2");
        Proveedor prov3 = new Proveedor(6876,"PROVEJEMPLO",35625,"dir3");
        agenda.addProveedor(prov1);
        agenda.addProveedor(prov2);
        agenda.addProveedor(prov3);
        assertEquals("PP1",agenda.buscarProveedor(1255).getNombre());
        assertEquals("PROVEJEMPLO",agenda.buscarProveedor(6876).getNombre());
        assertEquals(null,agenda.buscarCliente(5));
    }

    @Test
    public void testEliminarCliente()
    {
        Agenda agenda = new Agenda();
        Cliente cli1 = new Cliente(123,"NN1",35625,"dir1");
        Cliente cli2 = new Cliente(456,"NN2",35625,"dir3");
        Cliente cli3 = new Cliente(789,"NN3",35625,"dir2");
        agenda.addCliente(cli1);
        agenda.addCliente(cli2);
        agenda.addCliente(cli3);
        assertEquals("NN1",agenda.buscarCliente(123).getNombre());
        assertEquals("NN2",agenda.buscarCliente(456).getNombre());
        agenda.eliminarCliente(456);
        assertEquals(null,agenda.buscarCliente(456));
    }

    @Test
    public void testEliminarProveedor()
    {
        Agenda agenda = new Agenda();
        Proveedor prov1 = new Proveedor(1255,"PP1",35625,"dir1");
        Proveedor prov2 = new Proveedor(5545,"PP2",35625,"dir2");
        Proveedor prov3 = new Proveedor(6876,"PROVEJEMPLO",35625,"dir3");
        agenda.addProveedor(prov1);
        agenda.addProveedor(prov2);
        agenda.addProveedor(prov3);
        assertEquals("PROVEJEMPLO",agenda.buscarProveedor(6876).getNombre());
        agenda.eliminarProveedor(6876);
        assertEquals(null,agenda.buscarProveedor(6876));  
    }

    

    @Test
    public void testModificarCliente()
    {
        Agenda agenda = new Agenda();
        Cliente cli1 = new Cliente(123,"NN1",99988,"dir1");
        Cliente cli2 = new Cliente(456,"NN2",152455,"dir3");
        Cliente cli3 = new Cliente(789,"NN3",35625,"dir2");
        agenda.addCliente(cli1);
        agenda.addCliente(cli2);
        agenda.addCliente(cli3);
        assertEquals("NN2",agenda.buscarCliente(456).getNombre());
        assertEquals(152455,agenda.buscarCliente(456).getTelefono());
        assertEquals("NN1",agenda.buscarCliente(123).getNombre());
        assertEquals(99988,agenda.buscarCliente(123).getTelefono());
        agenda.modificarCliente(456,"+",55555,"Cra30");
        agenda.modificarCliente(123,"Juan",0,"+");
        assertEquals("NN2",agenda.buscarCliente(456).getNombre());
        assertEquals(55555,agenda.buscarCliente(456).getTelefono());
        assertEquals("Cra30",agenda.buscarCliente(456).getDireccion());
        assertEquals("Juan",agenda.buscarCliente(123).getNombre());
        assertEquals(99988,agenda.buscarCliente(123).getTelefono());
        assertEquals("dir1",agenda.buscarCliente(123).getDireccion());
    }

    @Test
    public void testModificarProveedor()
    {
        Agenda agenda = new Agenda();
        Proveedor prov1 = new Proveedor(1255,"PP1",1111,"dir1");
        Proveedor prov2 = new Proveedor(5545,"PP2",35625,"dir2");
        Proveedor prov3 = new Proveedor(6876,"PROVEJEMPLO",35625,"dir3");
        agenda.addProveedor(prov1);
        agenda.addProveedor(prov2);
        agenda.addProveedor(prov3);
        assertEquals("PROVEJEMPLO",agenda.buscarProveedor(6876).getNombre());
        assertEquals(35625,agenda.buscarProveedor(6876).getTelefono());
        assertEquals("PP1",agenda.buscarProveedor(1255).getNombre());
        assertEquals(1111,agenda.buscarProveedor(1255).getTelefono());
        agenda.modificarProveedor(6876,"+",55555,"Cra30");
        agenda.modificarProveedor(1255,"Prov2",0,"+");
        assertEquals("PROVEJEMPLO",agenda.buscarProveedor(6876).getNombre());
        assertEquals(55555,agenda.buscarProveedor(6876).getTelefono());
        assertEquals("Cra30",agenda.buscarProveedor(6876).getDireccion());
        assertEquals("Prov2",agenda.buscarProveedor(1255).getNombre());
        assertEquals(1111,agenda.buscarProveedor(1255).getTelefono());
        assertEquals("dir1",agenda.buscarProveedor(1255).getDireccion());
    }
}












