import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.naming.InitialContext;
import javax.naming.NamingException;

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
		
		
//		ITR itr1 = new ITR();
//		itr1.setDepartamento(departamentoBean.obtenerPorNombre("Montevideo"));
//		itr1.setNombre("ITR Capital");
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
		
		Analista a=new Analista();
		a.setActivo("Y");
		a.setApellido1("Perez");
		a.setApellido2("Gomez");
		a.setCedula("12345678");
		a.setContrasena("admin");
		a.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
		a.setFechaNac(new Date(2002-1900,02,04));
		a.setGenero(generoBean.obtenerPorNombre("Hombre"));
		a.setLocalidad("localida1");
		a.setMail("coco@adinet.com.uy");
		a.setMailInstitucional("admin@utec.edu.uy");
		a.setNombre1("Pedro");
		a.setNombre2("Andres");
		a.setTelefono("556798");
		a.setUsuario("admin");
		a.setValidado("Y");
		a.setItr(itrBean.obtenerPorNombre("ITR Capital"));
		usuarioBean.crear(a); //1
		
		Area at=new Area();
		at.setNombre("Matematicas");
		areaBean.crear(at);
		
		TipoTutor tp=new TipoTutor();
		tp.setNombre("Encargado");
		tipoTutorBean.crear(tp);
		
		Tutor tutor=new Tutor();
		
		tutor.setActivo("Y");
		tutor.setApellido1("Pirez");
		tutor.setApellido2("Muñoz");
		tutor.setCedula("12345679");
		tutor.setContrasena("Contra");
		tutor.setDepartamento(departamentoBean.obtenerPorNombre("Durazno"));
		tutor.setFechaNac(new Date(1997-1900,10,12));
		tutor.setGenero(generoBean.obtenerPorNombre("Hombre"));
		tutor.setLocalidad("El Pinar");
		tutor.setMail("juan@capo.com.uy");
		tutor.setMailInstitucional("juan.pirez@docentes.utec.edu.uy");
		tutor.setNombre1("Juan");
		tutor.setNombre2("Andres");
		tutor.setTelefono("556798");
		tutor.setUsuario("juan.pirez");
		tutor.setValidado("Y");
		tutor.setArea(areaBean.obtenerPorNombre("Matematicas"));
		tutor.setTipo(tipoTutorBean.obtenerPorNombre("Encargado"));
		tutor.setItr(itrBean.obtenerPorNombre("ITR CS"));
		usuarioBean.crear(tutor);
		
		Tutor t2 = new Tutor();
		
		t2.setActivo("Y");
		t2.setApellido1("Nahuelim");
		t2.setApellido2("Martinez");
		t2.setCedula("44433345");
		t2.setContrasena("Contra");
		t2.setDepartamento(departamentoBean.obtenerPorNombre("Maldonado"));
		t2.setFechaNac(new Date(1990-1900,12,30));
		t2.setGenero(generoBean.obtenerPorNombre("Hombre"));
		t2.setLocalidad("Piriapolis");
		t2.setMail("pepepepe@hotmail.com");
		t2.setMailInstitucional("pepe.pepe@utec.edu.uy");
		t2.setNombre1("Jose");
		t2.setNombre2("Sabia");
		t2.setTelefono("098373635");
		t2.setUsuario("jose.nahuelim");
		t2.setValidado("Y");
		
		t2.setArea(areaBean.obtenerPorNombre("Matematicas"));
		t2.setTipo(tipoTutorBean.obtenerPorNombre("Encargado"));
		t2.setItr(itrBean.obtenerPorNombre("ITR CS"));
		usuarioBean.crear(t2);
		
		Estudiante e=new Estudiante();
		
		e.setId_estudiante(1L);
		e.setActivo("Y");
		e.setApellido1("Raquel");
		e.setApellido2("Muñoz");
		e.setCedula("1234566679");
		e.setContrasena("Contra");
		e.setDepartamento(departamentoBean.obtenerPorNombre("Montevideo"));
		e.setFechaNac(new Date(1999-1900,11,28));
		e.setGenero(generoBean.obtenerPorNombre("Hombre"));
		e.setLocalidad("Buceo");
		e.setMail("juan22@capo.com.uy");
		e.setMailInstitucional("rodrigo.raquel@estudiantes.utec.edu.uy");
		e.setNombre1("Rodrigo");
		e.setNombre2("Nicolas");
		e.setTelefono("52256798");
		e.setUsuario("rodrigo.raquel");
		e.setValidado("Y");
		e.setGeneracion("2022");
		e.setRol(rolBean.buscarNombre("Estudiante"));
		e.setItr(itrBean.obtenerPorNombre("ITR Capital"));
		usuarioBean.crear(e);
		
		Estudiante e2=new Estudiante();
		
		e2.setId_estudiante(2L);
		e2.setActivo("Y");
		e2.setApellido1("Mosca");
		e2.setApellido2("Messi");
		e2.setCedula("56766544");
		e2.setContrasena("Contra");
		e2.setDepartamento(departamentoBean.obtenerPorNombre("Montevideo"));
		e2.setFechaNac(new Date(1999-1900,11,28));
		e2.setGenero(generoBean.obtenerPorNombre("Hombre"));
		e2.setLocalidad("Sarandi Grande");
		e2.setMail("martin@jkk.com.uy");
		e2.setMailInstitucional("martin.mosca@estudiantes.utec.edu.uy");
		e2.setNombre1("Martin");
		e2.setNombre2("Nicolas");
		e2.setTelefono("098575565");
		e2.setUsuario("martin.mosca");
		e2.setValidado("Y");
		e2.setGeneracion("2020");
		e2.setRol(rolBean.buscarNombre("Estudiante"));
		e2.setItr(itrBean.obtenerPorNombre("ITR Capital"));
		usuarioBean.crear(e2);
		

		
		//Registros de prueba en la tabla Reclamos
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
		reclamo1.setFechaInicioActividad(new Date(2000-2021,8,8));
		reclamo1.setSemestre(2);
		
		
		reclamoBean.crear(reclamo1);
		
		Reclamo reclamo2 = new Reclamo();
		
		reclamo2.setTitulo("Cantina");
		reclamo2.setTipo("Otros");
		reclamo2.setDetalle("La cantina no es una cantina de verdad");
		reclamo2.setEstudiante(1L);
		reclamo2.setFecha(Date.from(LocalDate.of(2016, 8, 19).atStartOfDay(defaultZoneId).toInstant()));
		reclamo2.setEstado("FINALIZADO");
		reclamoBean.crear(reclamo2);
		
		Reclamo reclamo3 = new Reclamo();
		
		reclamo3.setTitulo("APE");
		reclamo3.setTipo("APE");
		reclamo3.setDetalle("No tengo idea de lo que es APE");
		reclamo3.setEstudiante(2L);
		reclamo3.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
		reclamo3.setEstado("INGRESADO");
		reclamo3.setCreditosReclamados(1);
		reclamo3.setDocente_id(2L);
		reclamo3.setNombreActividadAPE("Actividad APE");
		reclamo3.setFechaInicioActividad(new Date(2000-2021,4,8));
		reclamo3.setSemestre(1);
		reclamoBean.crear(reclamo3);

//		Reclamo reclamo1 = new Reclamo();
//		
//		reclamo1.setDetalle("Los baños siempre están sucios");
//		reclamo1.setEstudiante_id_usuario(3L);
//		reclamo1.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
//		reclamo1.setEstado("EN PROCESO");
//		reclamoBean.crear(reclamo1);
//		
//		Reclamo reclamo2 = new Reclamo();
//		
//		reclamo2.setDetalle("El salón A34 no tiene techo");
//		reclamo2.setEstudiante_id_usuario(3L);
//		reclamo2.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
//		reclamo2.setEstado("FINALIZADO");
//		reclamoBean.crear(reclamo2);
//		
//		Reclamo reclamo3 = new Reclamo();
//		
//		reclamo3.setDetalle("La cantina no es una cantina de verdad");
//		reclamo3.setEstudiante_id_usuario(3L);
//		reclamo3.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
//		reclamo3.setEstado("INGRESADO");
//		reclamoBean.crear(reclamo3);

		Reclamo reclamo4 = new Reclamo();
		
		reclamo4.setTitulo("Otra vez los creditos");
		reclamo4.setTipo("VME");
		reclamo4.setDetalle("Me faltan los creditos de VME2");
		reclamo4.setEstudiante(1L);
		reclamo4.setFecha(Date.from(LocalDate.of(2019, 5, 19).atStartOfDay(defaultZoneId).toInstant()));
		reclamo4.setEstado("EN PROCESO");
		reclamo4.setCreditosReclamados(2);
		reclamo4.setDocente_id(3L);
		reclamo4.setNombreEventoVME("Charlas con empresas");
		reclamo4.setFechaInicioActividad(new Date(2000-2021,8,8));
		reclamo4.setSemestre(2);
		
		

		//Registros de prueba en la tabla Justificados
		
		Justificado justif1 = new Justificado();
		
		justif1.setDetalle("No falté, se olvidaron de mí");
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
		
		justif3.setDetalle("Se rompió el bondi y llegué mas tarde");
		justif3.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
		justif3.setEstudiante(2L);
		justif3.setEstado("FINALIZADO");
		justif3.setId_evento(1L);
		
		
		//Registros de prueba en la tabla Eventos
		Evento eve1 = new Evento();
		
		eve1.setTitulo("Presencial 4 de octubre de 2022");
		//eve1.setTutor(tutor);
		eve1.setFecha_inicio(new Date(2022,10,28));
		eve1.setFecha_fin(new Date(2022,10,28));
		
		Evento eve2 = new Evento();
		
		eve2.setTitulo("VME2 jornada 10 de setiembre de 2022");
	//	eve2.setTutor(tutor);
		eve2.setFecha_inicio(new Date(2022,11,28));
		eve2.setFecha_fin(new Date(2022,11,28));
		
		//Registros de prueba en la tabla Convocatoria_asistencias

		ConvocatoriaAsistencia conv1 = new ConvocatoriaAsistencia();
		conv1.setAsistencia(0);
		conv1.setCalificacion(0);
		conv1.setId_estudiante(2L);
		conv1.setId_evento(1L);
		convocatoriasAsistenciaBean.crear(conv1);
		
		ConvocatoriaAsistencia conv2 = new ConvocatoriaAsistencia();
		conv2.setAsistencia(0);
		conv2.setCalificacion(0);
		conv2.setId_estudiante(2L);
		conv2.setId_evento(2L);
		convocatoriasAsistenciaBean.crear(conv2);
		
		ConvocatoriaAsistencia conv3 = new ConvocatoriaAsistencia();
		conv3.setAsistencia(0);
		conv3.setCalificacion(0);
		conv3.setId_estudiante(1L);
		conv3.setId_evento(1L);
		
		
		
		convocatoriasAsistenciaBean.crear(conv3);
		eventoBean.crear(eve2);
		eventoBean.crear(eve1);

		
		justificadoBean.crear(justif3);
		justificadoBean.crear(justif2);
		justificadoBean.crear(justif1);


	}

}
