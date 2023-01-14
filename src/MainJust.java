import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.entities.Justificado;
import com.exceptions.ServiciosException;
import com.servicios.AreasBeanRemote;
import com.servicios.DepartamentosBeanRemote;
import com.servicios.FuncionalidadBeanRemote;
import com.servicios.GenerosBeanRemote;
import com.servicios.ItrsBeanRemote;
import com.servicios.JustificadosBeanRemote;
import com.servicios.ReclamosBeanRemote;
import com.servicios.RolBeanRemote;
import com.servicios.TiposTutorBeanRemote;
import com.servicios.UsuariosBeanRemote;

public class MainJust {

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
		
		
		//fecha actual (la usamos para hacer reclamos de prueba)
		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		
		Justificado justif1 = new Justificado();
		
		justif1.setDetalle("No falté, se olvidaron de mí");
		justif1.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
		justif1.setEstudiante(3);
		justif1.setEstado("EN PROCESO");
		System.out.println(justif1);
		justificadoBean.crear(justif1);
		
		Justificado justif2 = new Justificado();
		
		justif2.setDetalle("Estaba enfermo y no pude ir");
		justif2.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
		justif2.setEstudiante(3);
		justif2.setEstado("FINALIZADO");
		justificadoBean.crear(justif2);
		
		Justificado justif3 = new Justificado();
		
		justif3.setDetalle("Se rompió el autobús y llegué mas tarde, tengo constancia de Copsa");
		justif3.setFecha(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
		justif3.setEstudiante(3);
		justif3.setEstado("INGRESADO");
		justificadoBean.crear(justif3);
		
	}

}
