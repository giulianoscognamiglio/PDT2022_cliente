package controlador;

import javax.naming.InitialContext;

import com.servicios.AnalistasBeanRemote;
import com.servicios.AreasBeanRemote;
import com.servicios.DepartamentosBeanRemote;
import com.servicios.FuncionalidadBeanRemote;
import com.servicios.GenerosBeanRemote;
import com.servicios.ItrsBeanRemote;
import com.servicios.ReclamosBeanRemote;
import com.servicios.RolBeanRemote;
import com.servicios.TiposTutorBeanRemote;
import com.servicios.UsuariosBeanRemote;


public class DAOGeneral {
	
		public static DepartamentosBeanRemote departamentoBean ;
		public static GenerosBeanRemote generoBean;
		public static UsuariosBeanRemote usuarioBean;
		public static AreasBeanRemote areaBean;
		public static TiposTutorBeanRemote tipoTutorBean;
		public static ItrsBeanRemote itrBean;
		public static RolBeanRemote rolBean;
		public static FuncionalidadBeanRemote funcionalidadBean;
		public static AnalistasBeanRemote analistaBean;
		public static ReclamosBeanRemote reclamoBean;
		
	public DAOGeneral(){
		initConexion();
	}
	
	public static void initConexion(){
		try{
			departamentoBean = (DepartamentosBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/DepartamentosBean!com.servicios.DepartamentosBeanRemote");
			generoBean = (GenerosBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/GenerosBean!com.servicios.GenerosBeanRemote");
			usuarioBean = (UsuariosBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/UsuariosBean!com.servicios.UsuariosBeanRemote");
			areaBean = (AreasBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/AreasBean!com.servicios.AreasBeanRemote");
			tipoTutorBean = (TiposTutorBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/TiposTutorBean!com.servicios.TiposTutorBeanRemote");	
			itrBean = (ItrsBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/ItrsBean!com.servicios.ItrsBeanRemote");	
			rolBean=(RolBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/RolBean!com.servicios.RolBeanRemote");
			funcionalidadBean=(FuncionalidadBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/FuncionalidadBean!com.servicios.FuncionalidadBeanRemote");
			analistaBean=(AnalistasBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/AnalistasBean!com.servicios.AnalistasBeanRemote");
			reclamoBean=(ReclamosBeanRemote) InitialContext.doLookup("ejb:/PDT2022_v3/ReclamosBean!com.servicios.ReclamosBeanRemote");
		}catch(Exception e){
			e.printStackTrace();
		}	
	}

}
