import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.AccionJustificacion;
import com.entities.AccionReclamo;
import com.entities.Analista;
import com.entities.Area;
import com.entities.ConvocatoriaAsistencia;
import com.entities.Departamento;
import com.entities.Estudiante;
import com.entities.Evento;
import com.entities.Funcionalidad;
import com.entities.Genero;
import com.entities.ITR;
import com.entities.Reclamo;
import com.entities.Justificado;
import com.entities.Rol;
import com.entities.TipoTutor;
import com.entities.Tutor;
import com.exceptions.ServiciosException;
import com.servicios.AccionesJustificadoBeanRemote;
import com.servicios.AccionesReclamoBeanRemote;
import com.servicios.AreasBeanRemote;
import com.servicios.DepartamentosBeanRemote;
import com.servicios.FuncionalidadBeanRemote;
import com.servicios.GenerosBeanRemote;
import com.servicios.ItrsBeanRemote;
import com.servicios.ReclamosBeanRemote;
import com.servicios.JustificadosBeanRemote;
import com.servicios.RolBeanRemote;
import com.servicios.TiposTutorBeanRemote;
import com.servicios.UsuariosBeanRemote;
import com.servicios.EventosBeanRemote;
import com.servicios.ConvocatoriasAsistenciaBeanRemote;




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
		JustificadosBeanRemote justificadoBean=(JustificadosBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/JustificadosBean!com.servicios.JustificadosBeanRemote");
		EventosBeanRemote eventoBean=(EventosBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/EventosBean!com.servicios.EventosBeanRemote");
		ConvocatoriasAsistenciaBeanRemote convocatoriasAsistenciaBean=(ConvocatoriasAsistenciaBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/ConvocatoriasAsistenciaBean!com.servicios.ConvocatoriasAsistenciaBeanRemote");
			AccionesReclamoBeanRemote accionesRBean = (AccionesReclamoBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/AccionesReclamoBean!com.servicios.AccionesReclamoBeanRemote");
		AccionesJustificadoBeanRemote accionesJBean = (AccionesJustificadoBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/AccionesJustificadoBean!com.servicios.AccionesJustificadoBeanRemote");


		//fecha actual (la usamos para hacer reclamos de prueba)
		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
//		Departamento d1 = new Departamento();
//		d1.setNombre("Durazno");
//		departamentoBean.crear(d1);
//		
//		Departamento d2 = new Departamento();
//		d2.setNombre("Montevideo");
//		departamentoBean.crear(d2);
//		
//		Departamento d3 = new Departamento();
//		d3.setNombre("Maldonado");
//		departamentoBean.crear(d3);

// DEPARTAMENTOS
		
		Departamento d1 = new Departamento();
		d1.setNombre("Artigas");
		departamentoBean.crear(d1);
		
		Departamento d2 = new Departamento();
		d2.setNombre("Canelones");
		departamentoBean.crear(d2);
		
		Departamento d3 = new Departamento();
		d3.setNombre("Cerro Largo");
		departamentoBean.crear(d3);
		
		Departamento d4 = new Departamento();
		d4.setNombre("Colonia");
		departamentoBean.crear(d4);
		
		Departamento d5 = new Departamento();
		d5.setNombre("Durazno");
		departamentoBean.crear(d5);
		
		Departamento d6 = new Departamento();
		d6.setNombre("Flores");
		departamentoBean.crear(d6);
		
		Departamento d7 = new Departamento();
		d7.setNombre("Florida");
		departamentoBean.crear(d7);
		
		Departamento d8 = new Departamento();
		d8.setNombre("Lavalleja");
		departamentoBean.crear(d8);
		
		Departamento d9 = new Departamento();
		d9.setNombre("Maldonado");
		departamentoBean.crear(d9);
		
		Departamento d10 = new Departamento();
		d10.setNombre("Montevideo");
		departamentoBean.crear(d10);
		
		Departamento d11 = new Departamento();
		d11.setNombre("Paysandú");
		departamentoBean.crear(d11);
		
		Departamento d12 = new Departamento();
		d12.setNombre("Río Negro");
		departamentoBean.crear(d12);
		
		Departamento d13 = new Departamento();
		d13.setNombre("Rivera");
		departamentoBean.crear(d13);
		
		Departamento d14 = new Departamento();
		d14.setNombre("Rocha");
		departamentoBean.crear(d14);
		
		Departamento d15 = new Departamento();
		d15.setNombre("Salto");
		departamentoBean.crear(d15);
		
		Departamento d16 = new Departamento();
		d16.setNombre("San José");
		departamentoBean.crear(d16);
		
		Departamento d17 = new Departamento();
		d17.setNombre("Soriano");
		departamentoBean.crear(d17);
		
		Departamento d18 = new Departamento();
		d18.setNombre("Tacuarembó");
		departamentoBean.crear(d18);
		
		Departamento d19 = new Departamento();
		d19.setNombre("Treinta y Tres");
		departamentoBean.crear(d19);

//FUNCIONALIDADES
		
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
		
// GENEROS

		Genero genero=new Genero();
		genero.setNombre("Hombre");
		generoBean.crear(genero);
		
		Genero genero2=new Genero();
		genero2.setNombre("Mujer");
		generoBean.crear(genero2);
		
		Genero genero3=new Genero();
		genero3.setNombre("Otro");
		generoBean.crear(genero3);

//ITRs
		
//		ITR itr1 = new ITR();
//		itr1.setDepartamento(departamentoBean.obtenerPorNombre("Montevideo"));
//		itr1.setNombre("ITR CAPITAL");
//		itr1.setActivo("Y");
//		itrBean.crear(itr1);
//		
//		ITR itr2 = new ITR();
//		itr2.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
//		itr2.setNombre("ITR CS");
//		itr2.setActivo("Y");
//		itrBean.crear(itr2);
		
		ITR itr1 = new ITR();
		itr1.setDepartamento(departamentoBean.obtenerPorNombre("Río Negro"));
		itr1.setNombre("ITR SO");
		itr1.setActivo("Y");
		itrBean.crear(itr1);
		
		ITR itr2 = new ITR();
		itr2.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
		itr2.setNombre("ITR CS");
		itr2.setActivo("Y");
		itrBean.crear(itr2);
		
		ITR itr3 = new ITR();
		itr3.setDepartamento(departamentoBean.obtenerPorNombre("Rivera"));
		itr3.setNombre("ITR NO");
		itr3.setActivo("Y");
		itrBean.crear(itr3);
		
		ITR itr4 = new ITR();
		itr4.setDepartamento(departamentoBean.obtenerPorNombre("Cerro Largo"));
		itr4.setNombre("ITR CE");
		itr4.setActivo("Y");
		itrBean.crear(itr4);
		
		ITR itr5 = new ITR();
		itr5.setDepartamento(departamentoBean.obtenerPorNombre("Lavalleja"));
		itr5.setNombre("ITR SE");
		itr5.setActivo("Y");
		itrBean.crear(itr5);
		
		ITR itr6 = new ITR();
		itr6.setDepartamento(departamentoBean.obtenerPorNombre("Montevideo"));
		itr6.setNombre("ITR CAPITAL");
		itr6.setActivo("Y");
		itrBean.crear(itr6);
		
// ANALISTA ADMIN

		Analista a = new Analista();
		a.setActivo("Y");
		a.setApellido1("Perez");
		a.setApellido2("Gomez");
		a.setCedula("56555344");
		a.setContrasena("admin");
		a.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
		a.setFechaNac(new Date(2002 - 1900, 02, 04));
		a.setGenero(generoBean.obtenerPorNombre("Hombre"));
		a.setLocalidad("localida1");
		a.setMail("coco@adinet.com.uy");
		a.setMailInstitucional("admin@utec.edu.uy");
		a.setNombre1("Pedro");
		a.setNombre2("Andres");
		a.setTelefono("098778776");
		a.setUsuario("admin");
		a.setValidado("Y");
		a.setItr(itrBean.obtenerPorId(1));
		usuarioBean.crear(a); // 1

// ANALISTA ADMIN

		Analista a2 = new Analista();
		a2.setActivo("Y");
		a2.setApellido1("Ramirez");
		a2.setApellido2("Gomez");
		a2.setCedula("56746455");
		a2.setContrasena("admin2");
		a2.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
		a2.setFechaNac(new Date(2002 - 1900, 02, 04));
		a2.setGenero(generoBean.obtenerPorNombre("Hombre"));
		a2.setLocalidad("localida1");
		a2.setMail("coco@adinet.com.uy");
		a2.setMailInstitucional("admin2@utec.edu.uy");
		a2.setNombre1("Pedro");
		a2.setNombre2("Andres");
		a2.setTelefono("098987676");
		a2.setUsuario("admin2");
		a2.setValidado("Y");
		a2.setItr(itrBean.obtenerPorId(1));
		usuarioBean.crear(a2); // 1




// AREAS		
		Area at=new Area();
		at.setNombre("Programación");
		areaBean.crear(at);
		
		Area at1=new Area();
		at1.setNombre("Testing");
		areaBean.crear(at1);
		
		Area at2=new Area();
		at2.setNombre("Bases de Datos");
		areaBean.crear(at2);

//TIPOS TUTOR
		
		TipoTutor tp=new TipoTutor();
		tp.setNombre("Encargado");
		tipoTutorBean.crear(tp);
		
		TipoTutor tp2=new TipoTutor();
		tp2.setNombre("Profesor");
		tipoTutorBean.crear(tp2);

//TUTORES

		Tutor tutor = new Tutor();

		tutor.setActivo("Y");
		tutor.setId_tutor(1L);
		tutor.setApellido1("Martin");
		tutor.setApellido2("Martinez");
		tutor.setCedula("56433456");
		tutor.setContrasena("martin.martinez11");
		tutor.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
		tutor.setFechaNac(new GregorianCalendar(1985, Calendar.AUGUST, 15).getTime());
		tutor.setGenero(generoBean.obtenerPorNombre("Hombre"));
		tutor.setLocalidad("El Pinar");
		tutor.setMail("juan@capo.com.uy");
		tutor.setMailInstitucional("martin.martinez@docentes.utec.edu.uy");
		tutor.setNombre1("Juan");
		tutor.setNombre2("Andres");
		tutor.setTelefono("098378474");
		tutor.setUsuario("martin.martinez");
		tutor.setValidado("Y");
		tutor.setArea(areaBean.obtenerPorNombre("Programación"));
		tutor.setTipo(tipoTutorBean.obtenerPorNombre("Profesor"));
		tutor.setItr(itrBean.obtenerPorId(1));
		usuarioBean.crear(tutor);

		Tutor t2 = new Tutor();

		t2.setActivo("Y");
		t2.setId_tutor(2L);
		t2.setNombre1("Pedro");
		t2.setNombre2("Juan");
		t2.setApellido1("Pedrales");
		t2.setApellido2("Lopez");
		t2.setCedula("43452345");
		t2.setContrasena("pedro.pedrales11");
		t2.setDepartamento(departamentoBean.obtenerPorNombre("Maldonado"));
		t2.setFechaNac(new GregorianCalendar(1990, Calendar.DECEMBER, 30).getTime());
		t2.setGenero(generoBean.obtenerPorNombre("Hombre"));
		t2.setLocalidad("Piriapolis");
		t2.setMail("pedrito@hotmail.com");
		t2.setMailInstitucional("pedro.pedrales@utec.edu.uy");
		t2.setTelefono("098373635");
		t2.setUsuario("pedro.pedrales");
		t2.setValidado("Y");
		t2.setArea(areaBean.obtenerPorNombre("Bases de Datos"));
		t2.setTipo(tipoTutorBean.obtenerPorNombre("Encargado"));
		t2.setItr(itrBean.obtenerPorId(2));
		usuarioBean.crear(t2);

		Tutor t3 = new Tutor();

		t3.setActivo("Y");
		t3.setId_tutor(3L);
		t3.setApellido1("Maria");
		t3.setApellido2("Martinez");
		t3.setCedula("43333345");
		t3.setContrasena("maria.martinez11");
		t3.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
		t3.setFechaNac(new GregorianCalendar(1990, Calendar.FEBRUARY, 24).getTime());
		t3.setGenero(generoBean.obtenerPorNombre("Mujer"));
		t3.setLocalidad("Durazno");
		t3.setMail("mary@hotmail.com");
		t3.setMailInstitucional("maria.martinez@utec.edu.uy");
		t3.setNombre1("Jose");
		t3.setNombre2("Sabia");
		t3.setTelefono("098373635");
		t3.setUsuario("maria.martinez");
		t3.setValidado("Y");
		t3.setArea(areaBean.obtenerPorNombre("Testing"));
		t3.setTipo(tipoTutorBean.obtenerPorNombre("Profesor"));
		t3.setItr(itrBean.obtenerPorId(3));
		usuarioBean.crear(t3);

//ESTUDIANTES

		Estudiante e = new Estudiante();

		e.setId_estudiante(1L);
		e.setActivo("Y");
		e.setNombre1("Juan");
		e.setNombre2("Nicolas");
		e.setApellido1("Juanes");
		e.setApellido2("Goncalvez");
		e.setCedula("56744533");
		e.setContrasena("juan.juanes11");
		e.setDepartamento(departamentoBean.obtenerPorNombre("Montevideo"));
		e.setFechaNac(new GregorianCalendar(1999, Calendar.NOVEMBER, 28).getTime());
		e.setGenero(generoBean.obtenerPorNombre("Hombre"));
		e.setLocalidad("Buceo");
		e.setMail("juan22@gmail.com");
		e.setMailInstitucional("juan.juanes6@estudiantes.utec.edu.uy");
		e.setTelefono("52256798");
		e.setUsuario("juan.juanes");
		e.setValidado("Y");
		e.setGeneracion("2022");
		e.setRol(rolBean.buscarNombre("Estudiante"));
		e.setItr(itrBean.obtenerPorId(2));
		usuarioBean.crear(e);

		Estudiante e2 = new Estudiante();

		e2.setId_estudiante(2L);
		e2.setActivo("Y");
		e2.setNombre1("Marcos");
		e2.setNombre2("Pedro");
		e2.setApellido1("Aguirregaray");
		e2.setApellido2("Messi");
		e2.setCedula("45636355");
		e2.setContrasena("marcos.aguirregaray11");
		e2.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
		e2.setFechaNac(new GregorianCalendar(1997, Calendar.JUNE, 20).getTime());
		e2.setGenero(generoBean.obtenerPorNombre("Hombre"));
		e2.setLocalidad("Sarandi del Yí");
		e2.setMail("martin@hotmail.com");
		e2.setMailInstitucional("marcos.aguirregaray@estudiantes.utec.edu.uy");
		e2.setTelefono("098575565");
		e2.setUsuario("marcos.aguirregaray");
		e2.setValidado("Y");
		e2.setGeneracion("2020");
		e2.setRol(rolBean.buscarNombre("Estudiante"));
		e2.setItr(itrBean.obtenerPorId(2));
		usuarioBean.crear(e2);

		Estudiante e3 = new Estudiante();

		e3.setId_estudiante(3L);
		e3.setActivo("Y");
		e3.setNombre1("Gimena");
		e3.setNombre2("Carla");
		e3.setApellido1("Gimenez");
		e3.setApellido2("Gonzalez");
		e3.setCedula("56747444");
		e3.setContrasena("gimena.gimenez11");
		e3.setDepartamento(departamentoBean.obtenerPorNombre("Maldonado"));
		e3.setFechaNac(new GregorianCalendar(1997, Calendar.JUNE, 20).getTime());
		e3.setGenero(generoBean.obtenerPorNombre("Mujer"));
		e3.setLocalidad("Piriápolis");
		e3.setMail("gimena32@adinet.com");
		e3.setMailInstitucional("martin.martinez@estudiantes.utec.edu.uy");
		e3.setTelefono("097456366");
		e3.setUsuario("martin.martinez");
		e3.setValidado("Y");
		e3.setGeneracion("2022");
		e3.setRol(rolBean.buscarNombre("Estudiante"));
		e3.setItr(itrBean.obtenerPorId(1));
		usuarioBean.crear(e3);

		Estudiante e4 = new Estudiante();

		e4.setId_estudiante(4L);
		e4.setActivo("Y");
		e4.setNombre1("Hernan");
		e4.setNombre2("Martin");
		e4.setApellido1("Hernández");
		e4.setApellido2("Gonzalez");
		e4.setCedula("56466744");
		e4.setContrasena("hernan.hernandez11");
		e4.setDepartamento(departamentoBean.obtenerPorNombre("Maldonado"));
		e4.setFechaNac(new GregorianCalendar(1990, Calendar.APRIL, 1).getTime());
		e4.setGenero(generoBean.obtenerPorNombre("Hombre"));
		e4.setLocalidad("Piriápolis");
		e4.setMail("hernan@gmail.com");
		e4.setMailInstitucional("hernan.hernandez@estudiantes.utec.edu.uy");
		e4.setTelefono("099834527");
		e4.setUsuario("hernan.hernandez");
		e4.setValidado("Y");
		e4.setGeneracion("2021");
		e4.setRol(rolBean.buscarNombre("Estudiante"));
		e4.setItr(itrBean.obtenerPorId(2));
		usuarioBean.crear(e4);

		Estudiante e5 = new Estudiante();

		e5.setId_estudiante(5L);
		e5.setActivo("Y");
		e5.setNombre1("Juana");
		e5.setNombre2("Martina");
		e5.setApellido1("Juanes");
		e5.setApellido2("Gonzalez");
		e5.setCedula("56476544");
		e5.setContrasena("juana.juanes11");
		e5.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
		e5.setFechaNac(new GregorianCalendar(2000, Calendar.MAY, 15).getTime());
		e5.setGenero(generoBean.obtenerPorNombre("Mujer"));
		e5.setLocalidad("Santa Bernardina");
		e5.setMail("juana@gmail.com");
		e5.setMailInstitucional("juana.juanes@estudiantes.utec.edu.uy");
		e5.setTelefono("091876678");
		e5.setUsuario("juana.juanes");
		e5.setValidado("Y");
		e5.setGeneracion("2021");
		e5.setRol(rolBean.buscarNombre("Estudiante"));
		e5.setItr(itrBean.obtenerPorId(1));
		usuarioBean.crear(e5);

		Estudiante e6 = new Estudiante();

		e6.setId_estudiante(6L);
		e6.setActivo("N");
		e6.setNombre1("Martina");
		e6.setNombre2("Laura");
		e6.setApellido1("Martinez");
		e6.setApellido2("Perez");
		e6.setCedula("56336644");
		e6.setContrasena("martina.martinez11");
		e6.setDepartamento(departamentoBean.obtenerPorNombre("Montevideo"));
		e6.setFechaNac(new GregorianCalendar(2000, Calendar.JULY, 18).getTime());
		e6.setGenero(generoBean.obtenerPorNombre("Mujer"));
		e6.setLocalidad("La Blanqueada");
		e6.setMail("martinalaura@hotmail.com");
		e6.setMailInstitucional("martina.martinez@estudiantes.utec.edu.uy");
		e6.setTelefono("099456378");
		e6.setUsuario("martina.martinez");
		e6.setValidado("Y");
		e6.setGeneracion("2021");
		e6.setRol(rolBean.buscarNombre("Estudiante"));
		e6.setItr(itrBean.obtenerPorId(2));
		usuarioBean.crear(e6);

		Estudiante e7 = new Estudiante();

		e7.setId_estudiante(7L);
		e7.setActivo("N");
		e7.setNombre1("Gonzalo");
		e7.setNombre2("Ramiro");
		e7.setApellido1("Gonzalez");
		e7.setApellido2("Perez");
		e7.setCedula("45655655");
		e7.setContrasena("gonzalo.gonzalez11");
		e7.setDepartamento(departamentoBean.obtenerPorNombre("Montevideo"));
		e7.setFechaNac(new GregorianCalendar(2003, Calendar.OCTOBER, 30).getTime());
		e7.setGenero(generoBean.obtenerPorNombre("Hombre"));
		e7.setLocalidad("La Blanqueada");
		e7.setMail("gonza@gmail.com");
		e7.setMailInstitucional("gonzalo.gonzalez@estudiantes.utec.edu.uy");
		e7.setTelefono("099457378");
		e7.setUsuario("gonzalo.gonzalez");
		e7.setValidado("Y");
		e7.setGeneracion("2019");
		e7.setRol(rolBean.buscarNombre("Estudiante"));
		e7.setItr(itrBean.obtenerPorId(2));
		usuarioBean.crear(e7);

		Estudiante e8 = new Estudiante();

		e8.setId_estudiante(8L);
		e8.setActivo("N");
		e8.setNombre1("Miriam");
		e8.setNombre2("");
		e8.setApellido1("Lopez");
		e8.setApellido2("Bravo");
		e8.setCedula("45333655");
		e8.setContrasena("miriam.lopez11");
		e8.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
		e8.setFechaNac(new GregorianCalendar(2000, Calendar.JANUARY, 11).getTime());
		e8.setGenero(generoBean.obtenerPorNombre("Otro"));
		e8.setLocalidad("Durazno");
		e8.setMail("mirlo@gmail.com");
		e8.setMailInstitucional("miriam.lopez@estudiantes.utec.edu.uy");
		e8.setTelefono("099458388");
		e8.setUsuario("miriam.lopez");
		e8.setValidado("N");
		e8.setGeneracion("2019");
		e8.setRol(rolBean.buscarNombre("Estudiante"));
		e8.setItr(itrBean.obtenerPorId(3));
		usuarioBean.crear(e8);

		// Registros de prueba en la tabla Reclamos

		Reclamo reclamo1 = new Reclamo();
		reclamo1.setTitulo("Creditos VME");
		reclamo1.setTipo("VME");
		reclamo1.setDetalle("Me faltan los creditos de VME2");
		reclamo1.setEstudiante(1L);
		reclamo1.setFecha(Date.from(LocalDate.of(2022, 7, 19).atStartOfDay(defaultZoneId).toInstant()));
		reclamo1.setEstado("EN PROCESO");
		reclamo1.setCreditosReclamados(2);
		reclamo1.setDocente_id(3L);
		reclamo1.setNombreEventoVME("Charlas con empresas");
		reclamo1.setFechaInicioActividad(new GregorianCalendar(2021, Calendar.NOVEMBER, 8).getTime());
		reclamo1.setSemestre(2);
		reclamoBean.crear(reclamo1);

		Reclamo reclamo2 = new Reclamo();

		reclamo2.setTitulo("Cantina");
		reclamo2.setTipo("Otros");
		reclamo2.setDetalle("La cantina no es una cantina de verdad");
		reclamo2.setEstudiante(1L);
		reclamo2.setFecha(new GregorianCalendar(2016, Calendar.AUGUST, 10).getTime());
		reclamo2.setEstado("FINALIZADO");
		reclamoBean.crear(reclamo2);

		Reclamo reclamo3 = new Reclamo();

		reclamo3.setTitulo("APE");
		reclamo3.setTipo("APE");
		reclamo3.setDetalle("No se me avisó con tiempo de la actividad");
		reclamo3.setEstudiante(2L);
		reclamo3.setFecha(new GregorianCalendar(2022, Calendar.DECEMBER, 1).getTime());
		reclamo3.setEstado("INGRESADO");
		reclamo3.setCreditosReclamados(1);
		reclamo3.setDocente_id(2L);
		reclamo3.setNombreActividadAPE("Actividad APE");
		reclamo3.setFechaInicioActividad(new GregorianCalendar(2022, Calendar.OCTOBER, 15).getTime());
		reclamo3.setSemestre(4);
		reclamoBean.crear(reclamo3);

		Reclamo reclamo4 = new Reclamo();

		reclamo4.setTitulo("Créditos Chino");
		reclamo4.setTipo("Optativas");
		reclamo4.setDetalle("No me dieron los créditos del curso de Chino");
		reclamo4.setEstudiante(8L);
		reclamo4.setFecha(new GregorianCalendar(2022, Calendar.SEPTEMBER, 5).getTime());
		reclamo4.setEstado("INGRESADO");
		reclamo4.setCreditosReclamados(1);
		reclamo4.setDocente_id(1L);
		reclamo4.setNombreActividadAPE("Prueba Chino Mandarín");
		reclamo4.setFechaInicioActividad(new GregorianCalendar(2022, Calendar.AUGUST, 15).getTime());
		reclamo4.setSemestre(3);
		reclamoBean.crear(reclamo4);

		Reclamo reclamo5 = new Reclamo();

		reclamo5.setTitulo("Créditos Chino");
		reclamo5.setTipo("Optativas");
		reclamo5.setDetalle("No me dieron los créditos del curso de Chino");
		reclamo5.setEstudiante(7L);
		reclamo5.setFecha(new GregorianCalendar(2022, Calendar.SEPTEMBER, 6).getTime());
		reclamo5.setEstado("INGRESADO");
		reclamo5.setCreditosReclamados(1);
		reclamo5.setDocente_id(2L);
		reclamo5.setNombreActividadAPE("Prueba Chino Mandarín");
		reclamo5.setFechaInicioActividad(new GregorianCalendar(2022, Calendar.AUGUST, 15).getTime());
		reclamo5.setSemestre(3);
		reclamoBean.crear(reclamo5);

		Reclamo reclamo6 = new Reclamo();

		reclamo6.setTitulo("Créditos Chino");
		reclamo6.setTipo("Optativas");
		reclamo6.setDetalle("No me dieron los créditos del curso de Chino");
		reclamo6.setEstudiante(5L);
		reclamo6.setFecha(new GregorianCalendar(2022, Calendar.SEPTEMBER, 6).getTime());
		reclamo6.setEstado("INGRESADO");
		reclamo6.setCreditosReclamados(1);
		reclamo6.setDocente_id(3L);
		reclamo6.setNombreActividadAPE("Actividad APE");
		reclamo6.setFechaInicioActividad(new GregorianCalendar(2022, Calendar.AUGUST, 15).getTime());
		reclamo6.setSemestre(3);
		reclamoBean.crear(reclamo6);

		Reclamo reclamo7 = new Reclamo();

		reclamo7.setTitulo("Créditos VME2");
		reclamo7.setTipo("VME");
		reclamo7.setDetalle("Me faltan los creditos de VME2");
		reclamo7.setEstudiante(3L);
		reclamo7.setFecha(new GregorianCalendar(2019, Calendar.MAY, 19).getTime());
		reclamo7.setEstado("INGRESADO");
		reclamo7.setCreditosReclamados(2);
		reclamo7.setDocente_id(1L);
		reclamo7.setNombreEventoVME("Instancia presencial en LATU");
		reclamo7.setFechaInicioActividad(new GregorianCalendar(2019, Calendar.APRIL, 3).getTime());
		reclamo7.setSemestre(4);
		reclamoBean.crear(reclamo7);

		Reclamo reclamo8 = new Reclamo();

		reclamo8.setTitulo("Créditos Lectura y Escritura Universitaria");
		reclamo8.setTipo("Optativas");
		reclamo8.setDetalle("Me faltan los creditos del curso Lectura y Escritura Universitaria");
		reclamo8.setEstudiante(6L);
		reclamo8.setFecha(new GregorianCalendar(2020, Calendar.SEPTEMBER, 20).getTime());
		reclamo8.setEstado("INGRESADO");
		reclamo8.setCreditosReclamados(2);
		reclamo8.setDocente_id(1L);
		reclamo8.setNombreEventoVME("Prueba Lectura y Escritura Universitaria");
		reclamo8.setFechaInicioActividad(new GregorianCalendar(2020, Calendar.APRIL, 3).getTime());
		reclamo8.setSemestre(5);
		reclamoBean.crear(reclamo8);

		Reclamo reclamo9 = new Reclamo();

		reclamo9.setTitulo("Créditos Lectura y Escritura Universitaria");
		reclamo9.setTipo("Optativas");
		reclamo9.setDetalle("Me faltan los creditos del curso Lectura y Escritura Universitaria");
		reclamo9.setEstudiante(1L);
		reclamo9.setFecha(new GregorianCalendar(2020, Calendar.SEPTEMBER, 20).getTime());
		reclamo9.setEstado("INGRESADO");
		reclamo9.setCreditosReclamados(2);
		reclamo9.setDocente_id(1L);
		reclamo9.setNombreEventoVME("Prueba Lectura y Escritura Universitaria");
		reclamo9.setFechaInicioActividad(new GregorianCalendar(2020, Calendar.APRIL, 3).getTime());
		reclamo9.setSemestre(5);
		reclamoBean.crear(reclamo9);

		Reclamo reclamo10 = new Reclamo();

		reclamo10.setTitulo("Créditos Lectura y Escritura Universitaria");
		reclamo10.setTipo("Optativas");
		reclamo10.setDetalle("Me faltan los creditos del curso Lectura y Escritura Universitaria");
		reclamo10.setEstudiante(4L);
		reclamo10.setFecha(new GregorianCalendar(2020, Calendar.SEPTEMBER, 20).getTime());
		reclamo10.setEstado("INGRESADO");
		reclamo10.setCreditosReclamados(2);
		reclamo10.setDocente_id(1L);
		reclamo10.setNombreEventoVME("Prueba Lectura y Escritura Universitaria");
		reclamo10.setFechaInicioActividad(new GregorianCalendar(2020, Calendar.APRIL, 3).getTime());
		reclamo10.setSemestre(5);
		reclamoBean.crear(reclamo10);

		Reclamo reclamo11 = new Reclamo();

		reclamo11.setTitulo("Pisos sucios");
		reclamo11.setTipo("Otros");
		reclamo11.setDetalle("Los pisos del pasillo suelen estar sucios");
		reclamo11.setEstudiante(1L);
		reclamo11.setFecha(new GregorianCalendar(2022, Calendar.JULY, 10).getTime());
		reclamo11.setEstado("INGRESADO");
		reclamoBean.crear(reclamo11);

		Reclamo reclamo12 = new Reclamo();

		reclamo12.setTitulo("Ventana rota");
		reclamo12.setTipo("Otros");
		reclamo12.setDetalle("El salón A3 tiene una ventana rota");
		reclamo12.setEstudiante(3L);
		reclamo12.setFecha(new GregorianCalendar(2022, Calendar.JANUARY, 20).getTime());
		reclamo12.setEstado("INGRESADO");
		reclamoBean.crear(reclamo12);

		Reclamo reclamo13 = new Reclamo();

		reclamo13.setTitulo("Ventana rota");
		reclamo13.setTipo("Otros");
		reclamo13.setDetalle("El salón A3 tiene una ventana rota");
		reclamo13.setEstudiante(3L);
		reclamo13.setFecha(new GregorianCalendar(2022, Calendar.JANUARY, 20).getTime());
		reclamo13.setEstado("INGRESADO");
		reclamoBean.crear(reclamo13);

		Reclamo reclamo14 = new Reclamo();

		reclamo14.setTitulo("Ventana rota");
		reclamo14.setTipo("Otros");
		reclamo14.setDetalle("El salón A3 tiene una ventana rota");
		reclamo14.setEstudiante(5L);
		reclamo14.setFecha(new GregorianCalendar(2022, Calendar.JANUARY, 20).getTime());
		reclamo14.setEstado("INGRESADO");
		reclamoBean.crear(reclamo14);

		Reclamo reclamo15 = new Reclamo();

		reclamo15.setTitulo("Ventana rota");
		reclamo15.setTipo("Otros");
		reclamo15.setDetalle("El salón A3 tiene una ventana rota");
		reclamo15.setEstudiante(5L);
		reclamo15.setFecha(new GregorianCalendar(2022, Calendar.JANUARY, 20).getTime());
		reclamo15.setEstado("INGRESADO");
		reclamoBean.crear(reclamo15);

//JUSTIFICACIONES DE INASISTENCIAS

		Justificado justif1 = new Justificado();

		justif1.setDetalle("Me fracturé la pierna en las escaleras de la UTEC");
		justif1.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
		justif1.setEstudiante(1L);
		justif1.setEstado("INGRESADO");
		justif1.setId_evento(1L);

		Justificado justif2 = new Justificado();

		justif2.setDetalle("Estaba enfermo y no pude ir");
		justif2.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
		justif2.setEstudiante(1L);
		justif2.setEstado("EN PROCESO");
		justif2.setId_evento(2L);

		Justificado justif3 = new Justificado();

		justif3.setDetalle("Perdí el ómnibus y llegué tarde");
		justif3.setFecha(new GregorianCalendar(2022, Calendar.OCTOBER, 21).getTime());
		justif3.setEstudiante(2L);
		justif3.setEstado("FINALIZADO");
		justif3.setId_evento(1L);

		Justificado justif4 = new Justificado();

		justif4.setDetalle("No pude ir por trabajo");
		justif4.setFecha(new GregorianCalendar(2022, Calendar.FEBRUARY, 10).getTime());
		justif4.setEstudiante(6L);
		justif4.setEstado("INGRESADO");
		justif4.setId_evento(2L);

		Justificado justif5 = new Justificado();

		justif5.setDetalle("Se rompió el  ómnibus y llegué mas tarde");
		justif5.setFecha(new GregorianCalendar(2022, Calendar.OCTOBER, 21).getTime());
		justif5.setEstudiante(5L);
		justif5.setEstado("INGRESADO");
		justif5.setId_evento(3L);

		Justificado justif6 = new Justificado();

		justif6.setDetalle("Se rompió el  ómnibus y llegué mas tarde");
		justif6.setFecha(new GregorianCalendar(2022, Calendar.OCTOBER, 21).getTime());
		justif6.setEstudiante(7L);
		justif6.setEstado("INGRESADO");
		justif6.setId_evento(3L);

		Justificado justif7 = new Justificado();

		justif7.setDetalle("Se rompió el  ómnibus y llegué mas tarde");
		justif7.setFecha(new GregorianCalendar(2022, Calendar.OCTOBER, 21).getTime());
		justif7.setEstudiante(8L);
		justif7.setEstado("INGRESADO");
		justif7.setId_evento(3L);

		// Registros de prueba en la tabla Eventos
		Evento eve1 = new Evento();

		eve1.setTitulo("Presencial 4 de octubre de 2022");
		eve1.setFecha_inicio(new GregorianCalendar(2022, Calendar.OCTOBER, 4).getTime());
		eve1.setFecha_fin(new GregorianCalendar(2022, Calendar.OCTOBER, 4).getTime());

		Evento eve2 = new Evento();

		eve2.setTitulo("VME2 jornada 10 de setiembre de 2022");
		eve2.setFecha_inicio(new GregorianCalendar(2022, Calendar.SEPTEMBER, 10).getTime());
		eve2.setFecha_fin(new GregorianCalendar(2022, Calendar.SEPTEMBER, 10).getTime());

		Evento eve3 = new Evento();

		eve3.setTitulo("Campamento de Inglés");
		eve3.setFecha_inicio(new GregorianCalendar(2022, Calendar.JUNE, 15).getTime());
		eve3.setFecha_fin(new GregorianCalendar(2022, Calendar.JUNE, 20).getTime());

//CONVOCATORIAS DE ASISTENCIA

		ConvocatoriaAsistencia conv1 = new ConvocatoriaAsistencia();
		conv1.setAsistencia(0);
		conv1.setCalificacion(3);
		conv1.setId_estudiante(2L);
		conv1.setId_evento(1L);
		convocatoriasAsistenciaBean.crear(conv1);

		ConvocatoriaAsistencia conv2 = new ConvocatoriaAsistencia();
		conv2.setAsistencia(0);
		conv2.setCalificacion(4);
		conv2.setId_estudiante(2L);
		conv2.setId_evento(2L);
		convocatoriasAsistenciaBean.crear(conv2);

		ConvocatoriaAsistencia conv3 = new ConvocatoriaAsistencia();
		conv3.setAsistencia(0);
		conv3.setCalificacion(5);
		conv3.setId_estudiante(1L);
		conv3.setId_evento(1L);

		ConvocatoriaAsistencia conv4 = new ConvocatoriaAsistencia();
		conv4.setAsistencia(0);
		conv4.setCalificacion(3);
		conv4.setId_estudiante(1L);
		conv4.setId_evento(3L);
		convocatoriasAsistenciaBean.crear(conv3);
		
		ConvocatoriaAsistencia conv5 = new ConvocatoriaAsistencia();
		conv5.setAsistencia(0);
		conv5.setCalificacion(2);
		conv5.setId_estudiante(1L);
		conv5.setId_evento(3L);
		convocatoriasAsistenciaBean.crear(conv5);
		
		ConvocatoriaAsistencia conv6 = new ConvocatoriaAsistencia();
		conv6.setAsistencia(0);
		conv6.setCalificacion(1);
		conv6.setId_estudiante(5L);
		conv6.setId_evento(3L);
		convocatoriasAsistenciaBean.crear(conv6);
		
		eventoBean.crear(eve2);
		eventoBean.crear(eve1);
		eventoBean.crear(eve3);
		justificadoBean.crear(justif7);
		justificadoBean.crear(justif6);
		justificadoBean.crear(justif5);
		justificadoBean.crear(justif4);
		justificadoBean.crear(justif3);
		justificadoBean.crear(justif2);
		justificadoBean.crear(justif1);

		AccionReclamo accionR = new AccionReclamo();
		
		accionR.setAnalista(1L);
		accionR.setDetalle("El reclamo pasa a estar En Proceso");
		accionR.setFecha(new GregorianCalendar(2023, Calendar.JANUARY, 15).getTime());
		accionR.setReclamo_id(1L);
		accionesRBean.crear(accionR);
		
		AccionReclamo accionR2 = new AccionReclamo();

		accionR2.setAnalista(1L);
		accionR2.setDetalle("El reclamo pasa a estar En Proceso");
		accionR2.setFecha(new GregorianCalendar(2023, Calendar.JANUARY, 15).getTime());
		accionR2.setReclamo_id(2L);
		accionesRBean.crear(accionR2);

		AccionReclamo accionR3 = new AccionReclamo();
		
		accionR3.setAnalista(1L);
		accionR3.setDetalle("El reclamo pasa a estar FINALIZADO");
		accionR3.setFecha(new GregorianCalendar(2023, Calendar.FEBRUARY, 10).getTime());
		accionR3.setReclamo_id(2L);
		accionesRBean.crear(accionR3);

		
		AccionJustificacion accionJ1 = new AccionJustificacion();
		
		accionJ1.setAnalista(1L);
		accionJ1.setDetalle("La justificación pasa a estar En Proceso");
		accionJ1.setFecha(new GregorianCalendar(2023, Calendar.FEBRUARY, 10).getTime());
		accionJ1.setJustificado_id(2L);
		accionesJBean.crear(accionJ1);
		
		AccionJustificacion accionJ2 = new AccionJustificacion();
		
		accionJ2.setAnalista(1L);
		accionJ2.setDetalle("La justificación pasa a estar En Proceso");
		accionJ2.setFecha(new GregorianCalendar(2023, Calendar.FEBRUARY, 10).getTime());
		accionJ2.setJustificado_id(3L);
		accionesJBean.crear(accionJ2);
		
		AccionJustificacion accionJ3 = new AccionJustificacion();
		
		accionJ3.setAnalista(1L);
		accionJ3.setDetalle("La justificación pasa a estar Finalizado");
		accionJ3.setFecha(new GregorianCalendar(2023, Calendar.FEBRUARY, 10).getTime());
		accionJ3.setJustificado_id(3L);
		accionesJBean.crear(accionJ3);
		


	}

}


