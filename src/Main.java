import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Analista;
import com.entities.Area;
import com.entities.Departamento;
import com.entities.Estudiante;
import com.entities.Funcionalidad;
import com.entities.Genero;
import com.entities.ITR;
import com.entities.Reclamo;
import com.entities.Rol;
import com.entities.TipoTutor;
import com.entities.Tutor;
import com.exceptions.ServiciosException;
import com.servicios.AreasBeanRemote;
import com.servicios.DepartamentosBeanRemote;
import com.servicios.FuncionalidadBeanRemote;
import com.servicios.GenerosBeanRemote;
import com.servicios.ItrsBeanRemote;
import com.servicios.ReclamosBeanRemote;
import com.servicios.RolBeanRemote;
import com.servicios.TiposTutorBeanRemote;
import com.servicios.UsuariosBeanRemote;


public class Main {

	public static void main(String[] args) throws NamingException, ServiciosException {
		DepartamentosBeanRemote departamentoBean = (DepartamentosBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/DepartamentosBean!com.servicios.DepartamentosBeanRemote");
		GenerosBeanRemote generoBean = (GenerosBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/GenerosBean!com.servicios.GenerosBeanRemote");
		UsuariosBeanRemote usuarioBean = (UsuariosBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/UsuariosBean!com.servicios.UsuariosBeanRemote");
		AreasBeanRemote areaBean = (AreasBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/AreasBean!com.servicios.AreasBeanRemote");
		TiposTutorBeanRemote tipoTutorBean = (TiposTutorBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/TiposTutorBean!com.servicios.TiposTutorBeanRemote");
		ItrsBeanRemote itrBean = (ItrsBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/ItrsBean!com.servicios.ItrsBeanRemote");
		RolBeanRemote rolBean=(RolBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/RolBean!com.servicios.RolBeanRemote");
		FuncionalidadBeanRemote funcionalidadBean=(FuncionalidadBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/FuncionalidadBean!com.servicios.FuncionalidadBeanRemote");
		ReclamosBeanRemote reclamoBean=(ReclamosBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/ReclamosBean!com.servicios.ReclamosBeanRemote");
		//fecha actual (la usamos para hacer reclamos de prueba)
		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		
		Departamento d1 = new Departamento();
		d1.setNombre("Durazno");
		departamentoBean.crear(d1);
		
		Departamento d2 = new Departamento();
		d2.setNombre("Montevideo");
		departamentoBean.crear(d2);
		
		Departamento d3 = new Departamento();
		d3.setNombre("Maldonado");
		departamentoBean.crear(d3);
		
		Funcionalidad f1=new Funcionalidad();
		f1.setNombre("Solicitar constancia");
		f1.setDescripcion("Ingreso de solicitud de constancia");
		funcionalidadBean.crearFuncionalidad(f1);
		
		Funcionalidad f2=new Funcionalidad();
		f2.setNombre("Presentar Reclamo");
		f2.setDescripcion("Ingreso de Reclamo");
		funcionalidadBean.crearFuncionalidad(f2);
		
		Funcionalidad f3=new Funcionalidad();
		f3.setNombre("Registrar Asistencia");
		f3.setDescripcion("Registro de asistencia de estudiantes a eventos");
		funcionalidadBean.crearFuncionalidad(f3);
		
		Funcionalidad f4=new Funcionalidad();
		f4.setNombre("Activar usuarios");
		f4.setDescripcion("Activar un usuario luego de creado el mismo");
		funcionalidadBean.crearFuncionalidad(f4);
		
		Funcionalidad f5=new Funcionalidad();
		f5.setNombre("Modificar usuarios");
		f5.setDescripcion("Modificar los datos de un usuario ya existente");
		funcionalidadBean.crearFuncionalidad(f5);
		
		Rol r1=new Rol();
		r1.setNombre("Estudiante");
		r1.setDescripcion("Usuario Estudiante de UTEC");
		r1.addFuncionalidad(funcionalidadBean.buscarNombre("Solicitar constancia"));
		r1.addFuncionalidad(funcionalidadBean.buscarNombre("Presentar Reclamo"));
		rolBean.crearRol(r1);
		
		Rol r2=new Rol();
		r2.setNombre("Tutor");
		r2.setDescripcion("Usuario Tutor de UTEC");
		r2.addFuncionalidad(funcionalidadBean.buscarNombre("Registrar Asistencia"));
		rolBean.crearRol(r2);
		
		Rol r3=new Rol();
		r3.setNombre("Analista");
		r3.setDescripcion("Usuario Analista de UTEC");
		r3.addFuncionalidad(funcionalidadBean.buscarNombre("Activar usuarios"));
		r3.addFuncionalidad(funcionalidadBean.buscarNombre("Modificar usuarios"));
		rolBean.crearRol(r3);
		
		Genero genero=new Genero();
		genero.setNombre("Hombre");
		generoBean.crear(genero);
		
		ITR itr1 = new ITR();
		itr1.setDepartamento(departamentoBean.obtenerPorNombre("Montevideo"));
		itr1.setNombre("ITR Capital");
		itrBean.crear(itr1);
		
		ITR itr2 = new ITR();
		itr2.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
		itr2.setNombre("ITR CS");
		itrBean.crear(itr2);
		
		Analista a=new Analista();
		a.setActivo("X");
		a.setApellido1("Perez");
		a.setApellido2("Gomez");
		a.setCedula("12345678");
		a.setContrasena("admin");
		a.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
		a.setFechaNac(new Date(2002,02,04));
		a.setGenero(generoBean.obtenerPorNombre("Hombre"));
		a.setLocalidad("localida1");
		a.setMail("coco@adinet.com.uy");
		a.setMailInstitucional("admin@utec.edu.uy");
		a.setNombre1("Pedro");
		a.setNombre2("Andres");
		a.setTelefono("556798");
		a.setUsuario("admin");
		a.setValidado("SI");
		a.setItr(itrBean.obtenerPorNombre("ITR Capital"));
		usuarioBean.crear(a); //1
		
		Area at=new Area();
		at.setNombre("Matematicas");
		areaBean.crear(at);
		
		TipoTutor tp=new TipoTutor();
		tp.setNombre("Encargado");
		tipoTutorBean.crear(tp);
		
		Tutor tutor=new Tutor();
		
		tutor.setActivo("X");
		tutor.setApellido1("Pirez");
		tutor.setApellido2("Muñoz");
		tutor.setCedula("12345679");
		tutor.setContrasena("Contra");
		tutor.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
		tutor.setFechaNac(new Date(1997,10,12));
		tutor.setGenero(generoBean.obtenerPorNombre("Hombre"));
		tutor.setLocalidad("El Pinar");
		tutor.setMail("juan@capo.com.uy");
		tutor.setMailInstitucional("juan.pirez@docentes.utec.edu.uy");
		tutor.setNombre1("Juan");
		tutor.setNombre2("Andres");
		tutor.setTelefono("556798");
		tutor.setUsuario("juan.pirez");
		tutor.setValidado("SI");
		tutor.setArea(areaBean.obtenerPorNombre("Matematicas"));
		tutor.setTipo(tipoTutorBean.obtenerPorNombre("Encargado"));
		tutor.setItr(itrBean.obtenerPorNombre("ITR CS"));
		usuarioBean.crear(tutor);
		
		Estudiante e=new Estudiante();
		
		e.setActivo("X");
		e.setApellido1("Raquel");
		e.setApellido2("Muñoz");
		e.setCedula("1234566679");
		e.setContrasena("Contra");
		e.setDepartamento(departamentoBean.obtenerPorNombre("Montevideo"));
		e.setFechaNac(new Date(1999,11,28));
		e.setGenero(generoBean.obtenerPorNombre("Hombre"));
		e.setLocalidad("Buceo");
		e.setMail("juan22@capo.com.uy");
		e.setMailInstitucional("rodrigo.raquel@estudiantes.utec.edu.uy");
		e.setNombre1("Rodrigo");
		e.setNombre2("Nicolas");
		e.setTelefono("52256798");
		e.setUsuario("rodrigo.raquel");
		e.setValidado("SI");
		e.setGeneracion("2022");
		e.setRol(rolBean.buscarNombre("Estudiante"));
		e.setItr(itrBean.obtenerPorNombre("ITR Capital"));
		usuarioBean.crear(e);

		
		
		//Registros de prueba en la tabla Reclamos
		
		Reclamo reclamo1 = new Reclamo();
		
		reclamo1.setDetalle("Los baños siempre están sucios");
		reclamo1.setEstudiante(3);
		reclamo1.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
		reclamo1.setEstado("EN PROCESO");
		reclamoBean.crear(reclamo1);
		
		Reclamo reclamo2 = new Reclamo();
		
		reclamo2.setDetalle("El salón A34 no tiene techo");
		reclamo2.setEstudiante(3);
		reclamo2.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
		reclamo2.setEstado("FINALIZADO");
		reclamoBean.crear(reclamo2);
		
		Reclamo reclamo3 = new Reclamo();
		
		reclamo3.setDetalle("La cantina no es una cantina de verdad");
		reclamo3.setEstudiante(3);
		reclamo3.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
		reclamo3.setEstado("INGRESADO");
		reclamoBean.crear(reclamo3);
		
	}

}
