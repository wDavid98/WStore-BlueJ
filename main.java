import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
/**
 * WStore-App es una aplicación que permite la administración de una
 * tienda desde un computador por medio de un sistema de gestión de
 * inventario el cual permite ver y modificar los productos en el
 * inventario y también permite el registro de ventas y compras hechos
 * por el usuario. El inventario muestra la información de los 
 * productos disponibles en el negocio con toda su información.
 * las ventas y las compras se componen por productos los cuales 
 * son agregados uno a uno por el usuario, y son almacenados 
 * en la base de datos. Esta base de datos guarda los atributos 
 * que tienen los registros de las ventas, las compras, los productos, 
 * los clientes y los proveedores, Además, la aplicación también contará
 * con un historial que permite ver las ventas y compras que ha registrado
 * el usuario en la base de datos. Cuando el usuario registra una venta 
 * en la aplicación, se genera un recibo que tiene la información
 * de este registro y se relaciona con un cliente, y cuando registra 
 * una compra, debe diligenciar la información de los productos 
 * correspondientes. 
 * 
 * @author (William Romero) 
 * @version (V.01)
 */
public class main
{
    private Scanner sc;
    private Historial historial = new Historial();
    private Agenda agenda = new Agenda();   
    private Inventario inventario = new Inventario();

    public main(){        
        sc = new Scanner(System.in);
        Producto prod1 = new Producto("Aceite",1900,2400);
        prod1.setStock(150);
        Producto prod2 = new Producto("Leche",1200,1500);
        prod2.setStock(300);
        Producto prod3 = new Producto("Arroz",800,1000);
        prod3.setStock(400);
        inventario.addProduct(prod1);
        inventario.addProduct(prod2);
        inventario.addProduct(prod3);
        
        Cliente cli1 = new Cliente(123,"NN1",35625,"dir1");
        Cliente cli2 = new Cliente(456,"NN2",35625,"dir3");
        Cliente cli3 = new Cliente(789,"NN3",35625,"dir2");
        agenda.addCliente(cli1);
        agenda.addCliente(cli2);
        agenda.addCliente(cli3);
        
        Proveedor prov1 = new Proveedor(1255,"PP1",35625,"dir1");
        Proveedor prov2 = new Proveedor(5545,"PP2",35625,"dir2");
        Proveedor prov3 = new Proveedor(6876,"PP2",35625,"dir3");
        agenda.addProveedor(prov1);
        agenda.addProveedor(prov2);
        agenda.addProveedor(prov3);
        
        runApp(); 
    }

    private void initialize()
    {
        System.out.println("*****  Bienvenido a WStore-App  ******");         
    }
    
    private void runApp()
    {           
        boolean terminado = false;      
        while(!terminado)   
        {
            System.out.println("------- ¿Qué desea hacer? ------");
            verMenu();
            
            int opcion = getOpcion();
            switch(opcion)
            {   
                case 0: break;
                case 1: registrarVenta();
                break;
                case 2: registrarCompra();
                break;
                case 3: verInventario();
                break;
                case 4: verCompras();
                break;
                case 5: verVentas();
                break;
                case 6: verClientes();
                break;
                case 7: verProveedores();
                break;
                case 10: verMenu();
                break;
                case 11:
                break;                
                default: System.out.println("Número fuera del Menú");
                break;
            }
            terminado = endRun(opcion);
        }
        dispose();  
    }
    
    private void verMenu()
    {
        System.out.println("1. Registrar Venta");
        System.out.println("2. Registrar Compra");
        System.out.println("3. Ver Inventario");
        System.out.println("4. Ver Compras");
        System.out.println("5. Ver Ventas");
        System.out.println("6. Ver Clientes");
        System.out.println("7. Ver Proveedores");
        System.out.println("       0. Salir");
    }
    
    private int getOpcion()
    {
        int opt = 0;
        try
        {
            opt = sc.nextInt(); 
        }
        catch(Exception e)
        {
            System.out.println("Formato Incorrecto");
        }
        return opt;
    }
    
    private boolean endRun(int opt){
        if(opt == 0)
        {return true;}
        else        
        {return false;}
    }
    
    private void dispose()
    {
        System.out.println("Adiós");
    }
            
    private void registrarVenta()
    {   
        boolean noprods = false;                
        int way;   //Variable de desición  
        HashMap<Producto,Integer> listprods = new HashMap<Producto,Integer>(); 
        //Diccionario que guarda el producto vendido y la cantidad que se vendió
        ArrayList<Producto> intario = inventario.getInventario();
        //ArrayList que carga todo el inventario.
        ArrayList<Producto> oldProducts = new ArrayList<Producto>();
        //Array para identificar los productos a Editar
        int idprd;      
        //Seleccionar Productos a vender.
        if(intario.isEmpty()) //Verificar que existan productos para la venta
        {
            inventario.mostrarProductos();
        }
        else
        {
            //Proceso de agregar los productos a una lista junto a sus cantidades
            while(!noprods) 
            {   
                try
                {
                    System.out.println("Añadir producto: ");
                    inventario.mostrarProductos(); //Deben existir productos
                    System.out.println("Ingrese ID: ");                
                    idprd = sc.nextInt();  
                    boolean fnd = inventario.verificarID(idprd); 
                    //Verificar existencia del producto
                    if(fnd) //Si el producto buscado existe
                    {
                        //Registrar la cantidad a vender del producto buscado
                        System.out.println("Ingrese Cantidad: "); 
                        int cnt = sc.nextInt(); 
                        //Verificar existencia  
                        boolean ext = inventario.verificarExistencia(idprd,cnt);
                        if(ext) // Si hay suficiente para vender
                        {
                            Producto prod = inventario.productoAVender(idprd,cnt);
                            listprods.put(prod,cnt);
                            oldProducts.add(prod);
                            System.out.println("Producto añadido");
                        }
                        else //Si no hay suficiente para vender
                        {
                            System.out.println("No hay suficiente existencia");
                        }
                    }
                    else //Si el producto buscado no existe
                    {
                        System.out.println("Producto no añadido (No se encontró");
                    }
                    //Añadir mas productos a la lista
                    System.out.println("¿Quiere añadir otro producto? (1)Sí (0)No");
                    way = sc.nextInt(); 
                    // Si no quiere añadir mas sale a la sigueinte parte de la venta
                    if(way == 0) 
                    {
                        noprods = true;     
                    }
                }
                catch(Exception e)
                {
                    System.out.println("Formato Incorrecto");
                    noprods = true;
                }
            }
            System.out.println("¿Quiere continuar la Venta? (1)Sí (0)No");
            try  
            {
                way = sc.nextInt();  
            }   
            catch(Exception e)
            {
                System.out.println("Formato Incorrecto");
                way=0;  
            }
            //Cancelar la venta -- Se deshace totalmente y vuelve al menú
            if(way == 0)       
            {
                System.out.println("No se registró la Venta");
                return;
            }
            else 
            {            
                //Pregunta si añade un cliente a la venta nuevo o ya registrado.
                System.out.println("¿Añadir Cliente? (1)Sí (0)No");
                try
                {
                    way = sc.nextInt(); 
                }
                catch(Exception e)
                {
                    way = 0;
                }
                                      
                if(way == 1)        
                {   
                    Cliente cln = null; 
                    int cc;   
                    System.out.println("¿Cliente Nuevo? (1)Sí (0)No");  
                    try
                    {
                        way = sc.nextInt(); 
                    }
                    catch(Exception e)
                    {
                        way = 0;
                    }
                    boolean agd = agenda.getAgendaClientes().isEmpty();
                    //agd es true si no existen clientes en la agenda.
                    if(way == 1) //Cliente Nuevo
                    {
                        try
                        {
                            way = sc.nextInt(); // borrar
                            System.out.println("Nombre: ");
                            String prnom = sc.next();
                            System.out.println("telefono: ");
                            int ptel = sc.nextInt();
                            System.out.println("Dirección: ");
                            String dir = sc.next();
                            System.out.println("CC: ");
                            int nd = sc.nextInt(); 
                            cln = agenda.clienteNuevo(nd,prnom,ptel,dir);
                        }
                        catch(Exception e)
                        {   
                            way = 0;
                            System.out.println("Formato Incorrecto");
                        }
                    }
                    else  //Cliente Antiguo
                    {
                        if(agd) //No existen clientes
                        {
                            agenda.showClientes();
                        }
                        else //Si existen clientes
                        {
                            try
                            {
                                agenda.showClientes(); //Muestra los clientes
                                System.out.print("CC: ");   
                                cc = sc.nextInt();  
                                //Busca al cliente por su C.C
                                boolean cfnd = agenda.existeCliente(cc);
                                if(!cfnd) //No existe Cliente
                                {
                                    System.out.println("Cliente no encontrado");
                                    System.out.println("No se añade Cliente, edite mas tarde");
                                }
                                else //Sí existe
                                {
                                    //Se guarda el cliente en la variable cln
                                    cln=agenda.buscarCliente(cc); 
                                }
                            }catch(Exception e)
                            {
                                System.out.println("Formato Incorrecto");
                            }
                        }
                    } 
                    //Al finalizar la venta, se guarda
                    System.out.println("¿Quiere guardar la Venta? (1)Sí (0)No");
                    try
                    {
                        way = sc.nextInt(); 
                    }
                    catch(Exception e)
                    {
                        way = 0;
                    }   
                    if(way == 0)
                    {
                        //Se deschecha la venta.    
                        System.out.println("No se registro la Venta");
                        return; 
                    }
                    else
                    {
                        //Para guardar la venta se necesita:
                        //Crear la venta a partir del diccionario producto-cantidad
                        Venta venta = historial.crearVenta(listprods); 
                        //En este caso, asignar un cliente a la venta
                        venta.setCliente(cln);
                        //Guardar Venta en el historial.
                        historial.agregarVenta(venta);
                        //Modificar el stock de los productos vendidos.
                        inventario.addOldProducts(oldProducts);
                        //Guardar el cliente en la agenda
                        agenda.addCliente(cln);
                        System.out.println("Se registro la venta");
                    }
                } //Si no se quiere anexar un cliente
                else
                {
                    System.out.println("¿Quiere guardar la venta? (1)Sí (0)No");
                    way = sc.nextInt(); 
                    if(way == 0)
                    {
                        System.out.println("No se registró la venta");
                        return;
                    }
                    else
                    {
                        //Para guardar la venta se necesita:
                        //Crear la venta a partir del diccionario producto-cantidad
                        Venta venta = historial.crearVenta(listprods);
                        //Guardar Venta en el historial.
                        historial.agregarVenta(venta);
                        //Modificar el stock de los productos vendidos.
                        inventario.addOldProducts(oldProducts);
                        System.out.println("Se registró la venta");
                    }
                }     
            } 
        }
    }           
        
    private void registrarCompra()          
    {    
        //Falta manejar fecha de vencimiento    
        boolean noprods = false;                   
        int way;   
        //Array para gestionar los productos nuevos para agregar al inventario
        ArrayList<Producto> newlistprods = new ArrayList<Producto>();
        //Array para gestionar los productos viejos para modificar en el inventario
        ArrayList<Producto> oldlistprods = new ArrayList<Producto>();
        //hashmap para gestionar los productos y su cantidad comprada
        HashMap<Producto,Integer> listprods = new HashMap<Producto,Integer>(); 
        //Array que carga el inventario. -- creo que sobra
        ArrayList<Producto> intario = inventario.getInventario();
        int idprd;          
        while(!noprods) 
        {
            //Producto nuevo o ya existente.
            System.out.println("Añadir producto: ");
            System.out.println("¿Producto nuevo? (1)Sí (0)No");            
            way = sc.nextInt();     
            int stk;
            if(way == 0) //Añade producto ya existente en inventario
            {
                if(intario.isEmpty()) //Si el inventario está vacio
                {
                    inventario.mostrarProductos();
                }
                else //Si hay producto en inventario
                {
                    inventario.mostrarProductos();
                    //Buscar producto por el ID 
                    System.out.println("Ingrese ID: ");                
                    idprd = sc.nextInt();  
                    boolean fnd = inventario.verificarID(idprd);
                    //fnd nos dice si existe ese ID
                    if(!fnd)    //Si no existe se sale
                    {
                        System.out.println("Producto no añadido (No se encontró)");
                    }
                    else //Si existe lo añade a las listas
                    {
                        System.out.println("Ingrese Cantidad: "); 
                        stk = sc.nextInt(); 
                        /*Crear producto antiguo a partir de ID y cantidad
                        con un stok actualizado*/
                        Producto prod = inventario.productoAntiguo(idprd,stk);
                        //Se guarda producto en el diccionario prod-cantidad
                        listprods.put(prod,stk);    
                        //se guarda en la lista de antiguos para modificación de inventario
                        oldlistprods.add(prod);
                        System.out.println("Producto añadido");      
                    }
                }   
            }
            else //Añade producto nuevo
            {   
                //Crea producto solicitando los datos del producto.
                Producto prod = inventario.ProductoNuevo(); 
                //Añade el productoa al diccionario producto-cantidad
                //Como es nuevo el stock es la misma cantidad
                listprods.put(prod,prod.getStock());
                //añade el producto a la lista de nuevos para añadir al inventario.
                newlistprods.add(prod);
                System.out.println("Producto añadido");                                                
            }   
            System.out.println("¿Quiere añadir otro producto? (1)Sí (0)No");
            way = sc.nextInt(); 
            if(way == 0)
            {
                noprods = true;     
            }
        } 
        System.out.println("¿Quiere continuar la compra? (1)Sí (0)No");
        way = sc.nextInt();                     
        if(way == 0)
        {
            System.out.println("No se registro la Compra");
            return;
        }
        else
        {       
            //Para añadir un proveedor primero inicializamos la variable de forma nula
            Proveedor prov = null; 
            int nid; //variable para buscar el proveedor por nid
            //variable para verificar que la agenda no esté vacia -- creo que sobra
            boolean agda = agenda.getAgendaProveedores().isEmpty();
            System.out.println("¿Añadir Proveedor? (1)Sí (0)No");
            way = sc.nextInt();
            if(way == 1)  //En caso de agregar un proveedor  
            {
                System.out.println("¿Proveedor Nuevo? (1)Sí (0)No");  
                way = sc.nextInt();
                if(way == 1) //Proveedor Nuevo - lo crea y guarda en variable
                {
                    System.out.println("Nombre: ");
                    String prnom = sc.next();
                    System.out.println("telefono: ");
                    int ptel = sc.nextInt();
                    System.out.println("Dirección: ");
                    String dir = sc.next();
                    System.out.println("NID: ");
                    int nd = sc.nextInt(); 
                    prov = agenda.provNuevo(nd,prnom,ptel,dir);
                }                
                else  //Proveedor Antiguo
                {
                    if(agda) //Si la agenda está vacía
                    {
                        agenda.showProveedores();
                    }
                    else //si la agenda tiene proveedores
                    {
                        agenda.showProveedores();
                        System.out.print("NID: ");   
                        nid = sc.nextInt(); 
                        //Busca al proveedor por su nid
                        boolean pfnd = agenda.existeProveedor(nid);
                        if(!pfnd) //No existe Cliente
                        {
                            System.out.println("Proveedor no encontrado");
                            System.out.println("No se añade Proveedor, edite mas tarde");
                        }
                        else //Sí existe
                        {
                            //Se guarda el proveedor en la variable prov
                            prov=agenda.buscarProveedor(nid); 
                        }
                    }   
                    
                }   
                System.out.println("¿Quiere guardar la compra? (1)Sí (0)No");
                way = sc.nextInt(); 
                if(way == 0)
                {
                    System.out.println("No se registro la Compra");
                    return;
                }
                else
                {
                    //Para guardar la compra con proveedor se sigue:
                    //se crea la con el diccionario prod-cant
                    Compra compra = historial.crearCompra(listprods);
                    //Se le asigna el proveedor a la compra
                    compra.setProveedor(prov);
                    // se agrega la compra al historial
                    historial.agregarCompra(compra);
                    //se agregan al inventario los nuevos productos
                    inventario.addNewProducts(newlistprods);
                    //se modifican en el inventario los productos antiguos
                    inventario.addOldProducts(oldlistprods);
                    //se añade el proveedor 
                    agenda.addProveedor(prov);
                    System.out.println("Se registro la compra");
                }
            }
            else //En caso de no agregar ningún proveedor.
            {
                System.out.println("¿Quiere guardar la compra? (1)Sí (0)No");
                way = sc.nextInt(); 
                if(way == 0)
                {
                    System.out.println("No se registró la Compra");
                    return;
                }
                else
                {
                    //Para guardar la compra con proveedor se sigue:
                    //se crea la con el diccionario prod-cant
                    Compra compra = historial.crearCompra(listprods);
                    //No se agrega proveedor.
                    // se agrega la compra al historial
                    historial.agregarCompra(compra);
                    //se agregan al inventario los nuevos productos
                    inventario.addNewProducts(newlistprods);
                    //se modifican en el inventario los productos antiguos
                    inventario.addOldProducts(oldlistprods);
                    //se añade el proveedor 
                    agenda.addProveedor(prov);
                    System.out.println("Se registro la compra");
                }
            }     
        }
    }   
    
    private void verInventario()
    {
        //Mostrar inventario
        inventario.mostrarProductos();
        //Seleccionar ¿qué desea hacer con los productos?
        boolean back = false;   
        while(!back)
        {       
            System.out.println("¿Qué desea hacer?");
            verInventarioMenu();
            int opcion = getOpcion();
            switch(opcion)
            {   
                case 0: break;
                case 1: ModificarProducto();
                break;
                case 2: inventario.EliminarProducto();
                break;     
                default: System.out.println("Número fuera del Menú");
                break;
            }
            back= endRun(opcion);
        }                  
    }       
    
    private void ModificarProducto()
    {
        //Identificar producto a modificar -- obtener id}
        System.out.println("¿Qué producto desea modificar? -- Ingrese ID");
        int id = sc.nextInt();
        //verificar si el id está 
        boolean fnd = inventario.verificarID(id);
        //Si el id está,pedir que ingrese los datos
        if(!fnd)
        {
            System.out.println("Producto no encontrado");
        }
        else
        {
            System.out.println("Ingrese los valores que desea cambiar - Ignore los que no");
            System.out.print("('-'Para no editar)Nombre: ");
            String nNmb = sc.next();
            System.out.println("");
            System.out.print("('999'Para no editar) Precio de compra: ");
            Integer nPcm = sc.nextInt(); 
            System.out.println("");
            System.out.print("('999'Para no editar) Precio de venta: ");
            Integer nPvnt = sc.nextInt();
            System.out.println("");
            System.out.print("('999'Para no editar)Stock: ");
            Integer nStk = sc.nextInt();
            inventario.ModificarProducto(id,nNmb,nPcm,nPvnt,nStk);
        }
    }
    
    private void verInventarioMenu()    
    {
        System.out.println("1. Modificar Producto");
        System.out.println("2. Eliminar Producto");
        System.out.println("       0. Volver");
    }
    
    private void verCompras()
    {
        //Mostrar Compras
        historial.mostrarCompras();
        //Seleccionar ¿qué desea hacer con las compras?
        if(historial.getCompras().isEmpty())
        {
            System.out.println("No se han registrado ventas");
        }
        else
        {
            boolean back = false;
            while(!back)
            {       
                System.out.println("¿Qué desea hacer?");
                verComprasMenu();
                int opcion = getOpcion();
                switch(opcion)
                {   
                    case 0: break;
                    case 1: historial.verCompra();
                    break;
                    case 2: eliminarCompra();
                    break;     
                    default: System.out.println("Número fuera del Menú");
                    break;
                }
                back= endRun(opcion);
            }         
        }
        
    }
    
    private void eliminarCompra()
    {
        //Identificar compra a modificar -- obtener id}
        System.out.println("¿Qué compra desea eliminar? -- Ingrese ID");
        int id = sc.nextInt();  
        historial.eliminarCompra(id);
    }
    
    private void verComprasMenu()
    {
        System.out.println("1. Ver Compra");
        System.out.println("2. Eliminar Compra");
        System.out.println("       0. Volver");
    }
    
    private void verVentas()
    {
        //Mostrar Ventas
        historial.mostrarVentas();
        //Seleccionar ¿qué desea hacer con las compras?
        boolean back = false;
        if(historial.getVentas().isEmpty())
        {
            System.out.println("No se han registrado ventas");
        }
        else
        {
               while(!back)
            {       
                System.out.println("¿Qué desea hacer?");
                verVentasMenu();
                int opcion = getOpcion();
                switch(opcion)
                {   
                    case 0: break;
                    case 1: historial.verVenta();
                    break;
                    case 2: eliminarVenta();
                    break;     
                    default: System.out.println("Número fuera del Menú");
                    break;
                }
                back= endRun(opcion);
            }      
        }
    }
    
    private void eliminarVenta()
    {
        //Identificar compra a modificar -- obtener id}
        System.out.println("¿Qué venta desea eliminar? -- Ingrese ID");
        int id = sc.nextInt();
        historial.eliminarVenta(id);
    }
    
    private void verVentasMenu()
    {
        System.out.println("1. Ver Venta");
        System.out.println("2. Eliminar Venta");
        System.out.println("       0. Volver");
    }
        
        
    private void verClientes()
    {
        //Mostrar Clientes
        agenda.showClientes();
        //Seleccionar ¿qué desea hacer con los clientes?
        boolean back = false;   
        if(agenda.getClientes().isEmpty())
        {
            System.out.println("No existen clientes en la agenda");
        }
        else
        {
                while(!back)
            {       
                System.out.println("¿Qué desea hacer?");
                verClientesMenu();
                int opcion = getOpcion();
                switch(opcion)
                {   
                    case 0: break;
                    case 1: modificarCliente();
                    break;
                    case 2: eliminarCliente();
                    break;     
                    case 3: agregarCliente();
                    break;
                    default: System.out.println("Número fuera del Menú");
                    break;
                }
                back= endRun(opcion);
            }  
        }
           
    }
    
    private void modificarCliente()
    {
        //Identificar producto a modificar -- obtener id}
        System.out.println("¿A cuál Cliente desea modificar? -- Ingrese CC");
        int cc = sc.nextInt();
        //verificar si el id está 
        boolean fnd = agenda.existeCliente(cc);
        //Si el id está,pedir que ingrese los datos
        if(!fnd)
        {
            System.out.println("Cliente no encontrado");
        }
        else
        {
            try
            {
               System.out.println("Ingrese los valores que desea cambiar - Ignore los que no");
               System.out.print("Nombre (+ Para no editar): ");
               String nNmb = sc.next();
               System.out.println("");
               System.out.print("Telefono (0 Para no editar): ");
               Integer telf = sc.nextInt();
               System.out.println("");
               System.out.print("Dirección (+ Para no editar): ");
               String dir = sc.next();
               agenda.modificarCliente(cc,nNmb,telf,dir);
            }
            catch(Exception e)
            {
                System.out.println("Error, Formato Erroneo");
            }
        }
    }
    
    private void agregarCliente()
    {
        System.out.println("Nombre: ");
        String prnom = sc.next();
        System.out.println("telefono: ");
        int ptel = sc.nextInt();
        System.out.println("Dirección: ");
        String dir = sc.next();
        System.out.println("CC: ");
        int cc = sc.nextInt(); 
        agenda.agregarCliente(cc,prnom,ptel,dir);
    }
    
    private void eliminarCliente()
    {
        System.out.println("¿A cuál Cliente desea eliminar? -- Ingrese ID:");
        int id = sc.nextInt();
        agenda.eliminarCliente(id);
    }
    
    private void verClientesMenu()
    {
        System.out.println("1. Modificar Cliente");
        System.out.println("2. Eliminar Cliente");
        System.out.println("3. Agregar Cliente");
        System.out.println("       0. Volver");
    }
        
    private void verProveedores()
    {
        //Mostrar Clientes
        agenda.showProveedores();
        //Seleccionar ¿qué desea hacer con los clientes?
        boolean back = false;
        if(agenda.getProveedores().isEmpty())
        {
            System.out.println("No existen proveedores");
        }
        else
        {   
               while(!back)
            {       
                System.out.println("¿Qué desea hacer?");
                verProveedoresMenu();
                int opcion = getOpcion();
                switch(opcion)
                {   
                    case 0: break;
                    case 1: modificarProveedor();
                    break;
                    case 2: eliminarProveedor();
                    break;     
                    case 3: agregarProveedor();
                    break;
                    default: System.out.println("Número fuera del Menú");
                    break;
                }
                back= endRun(opcion);
            }  
        }
            
    }
    
    private void modificarProveedor()
    {
        //Identificar producto a modificar -- obtener id}
        System.out.println("¿A cuál proveedor desea modificar? -- Ingrese NIT");
        int nit = sc.nextInt();
        //verificar si el id está 
        boolean fnd = agenda.existeProveedor(nit);
        //Si el id está,pedir que ingrese los datos
        if(!fnd)
        {
            System.out.println("Proveedor no encontrado");
        }
        else
        {
            try
            {
               System.out.println("Ingrese los valores que desea cambiar - Ignore los que no");
               System.out.print("Nombre (+ Para no editar): ");
               String nNmb = sc.next();
               System.out.println("");
               System.out.print("Telefono (0 Para no editar): ");
               Integer telf = sc.nextInt();
               System.out.println("");
               System.out.print("Dirección (+ Para no editar): ");
               String dir = sc.next();
               agenda.modificarProveedor(nit,nNmb,telf,dir);
            }
            catch(Exception e)
            {
                System.out.println("Error, Formato Erroneo");
            }
        }
    }
    
    private void agregarProveedor()
    {
        System.out.print("Nombre: ");
        String prnom = sc.next();
        System.out.print("telefono: ");
        int ptel = sc.nextInt();
        System.out.print("Dirección: ");
        String dir = sc.next();
        System.out.print("CC: ");
        int nd = sc.nextInt(); 
        agenda.agregarProveedor(nd,prnom,ptel,dir);
    }
    
    private void eliminarProveedor()
    {
        System.out.println("¿A cuál proveedor desea eliminar? -- Ingrese NIT:");
        int id = sc.nextInt();
        agenda.eliminarProveedor(id);
    }
    
    private void verProveedoresMenu()
    {
        System.out.println("1. Modificar Proveedor");
        System.out.println("2. Eliminar Proveedor");
        System.out.println("3. Agregar Proveedor");
        System.out.println("       0. Volver");
    }

}
