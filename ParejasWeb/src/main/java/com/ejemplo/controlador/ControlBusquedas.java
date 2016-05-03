package com.ejemplo.controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp.BasicDataSource;

import com.ejemplo.modelo.Persona;
import com.ejemplo.negocio.ServicioBuscadorParejaPersonaDaoImpl;
import com.ejemplo.negocio.ServicioBusquedaParejas;
import com.ejemplo.persistencia.PersonaDaoJDBCImpl;


public class ControlBusquedas extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ServicioBusquedaParejas buscador;
    
    @Override
    public void init() throws ServletException {
    	//inicializacion del resto de las capas esto se hace 
    	//en spring o al menos con un ContextListener y un patron
    	//Factoria
    	
    	ServicioBuscadorParejaPersonaDaoImpl buscador=new ServicioBuscadorParejaPersonaDaoImpl();
    	BasicDataSource dataSource = new BasicDataSource();
		/*dataSource.setUrl("jdbc:mysql://localhost:3306/pruebas");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("root");*/
    	dataSource.setUrl("jdbc:oracle:thin:@192.168.111.128:1521:xe");
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUsername("pruebas");
		dataSource.setPassword("pruebas");
		
		PersonaDaoJDBCImpl personaDao = new PersonaDaoJDBCImpl();
		personaDao.setDataSource(dataSource);
		buscador.setPersonaDao(personaDao);
		this.buscador=buscador;
		
    }
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//obtenemos los datos del usuario
		Persona usuario=sacarDatosUsuario(request);
		Persona ideal = buscador.encontrarIdeal(usuario);
		//todas las peticiones devuelten los datos a la vista + 1 mensaje
		String msg;

		if(ideal!=null){
			msg="Enhorabuena "+usuario.getNombre()+"!";
			request.setAttribute("ideal", ideal);
		}else{
			msg="Lastima "+usuario.getNombre()+"!";
			request.setAttribute("afines", buscador.encontrarAfines(usuario));
		}
		request.setAttribute("msg", msg);
		//vamos a la pantalla de resultados
		request.getRequestDispatcher("resultados.jsp").forward(request, response);
		
	}


	private Persona sacarDatosUsuario(HttpServletRequest request) {
		Persona usuario=new Persona();
		usuario.setNombre(request.getParameter("nombre"));
		usuario.setEdad(Integer.parseInt(request.getParameter("edad")));
		usuario.setAltura(Float.parseFloat(request.getParameter("altura")));
		usuario.setSexo(request.getParameter("sexo").trim().charAt(0));
		return usuario;
	}

}
