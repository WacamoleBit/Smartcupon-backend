/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import modelo.DireccionDAO;
import modelo.pojo.Cliente;
import modelo.pojo.Direccion;
import modelo.pojo.Mensaje;

/**
 * REST Web Service
 *
 * @author Dell
 */
@Path("direcciones")
public class DireccionWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DireccionWS
     */
    public DireccionWS() {
    }

    /**
     * Retrieves representation of an instance of ws.DireccionWS
     * @return an instance of java.lang.String
     */
    
    //Los siguientes tres metodos son para agregar una Direccion a un cliente
    @POST
    @Path("obtenerDireccion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Direccion obtenerDomicilioPorId(String json){
        
        Gson gson = new Gson();
        Direccion direccion = null;
        Cliente cliente= gson.fromJson(json, Cliente.class);
        if(cliente != null && cliente.getIdCliente() != null && cliente.getIdCliente()>0){
            return direccion = DireccionDAO.obtenerDireccionPorId(cliente.getIdCliente());
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
    
    
    
    @PUT
    @Path("editarDireccionCliente")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje modificarDireccionCliente(String json){
        Gson gson = new Gson();
        Direccion direccion = gson.fromJson(json, Direccion.class);
        if(direccion!=null && direccion.getIdDomicilio()!=0 && direccion.getIdDomicilio()>0 && !direccion.getCalle().isEmpty() && direccion.getNumero()!=0){
            return DireccionDAO.modificarDireccionCliente(direccion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
    
    //Para una empresa
    //TODO
    
    
    //Para una sucursal
    @POST
    @Path("registrarDireccionSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarDireccionSucursal(String json){
        Gson gson = new Gson();
        Direccion direccion = gson.fromJson(json, Direccion.class);
        if(direccion!=null){
            return DireccionDAO.ingresarDireccionSucursal(direccion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
    
    @PUT
    @Path("editarDireccionSucursal")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje modificarDireccionSucursal(String json){
        Gson gson = new Gson();
        Direccion direccion = gson.fromJson(json, Direccion.class);
        if(direccion!= null){
            return DireccionDAO.modificarDireccionSucursal(direccion);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}
