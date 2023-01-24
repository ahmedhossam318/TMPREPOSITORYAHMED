package mts.ftth.vc4.security;

import java.util.Date;
import java.util.Hashtable;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import org.jboss.logging.Logger;

import mts.ftth.vc4.configuration.WLSConfig;

public class WLSJmxInterface {

	public static final String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	  public static final String MBEAN_SERVER = "weblogic.management.mbeanservers.domainruntime";
	  public static final String JNDI_ROOT = "/jndi/";
	  //public static final String DEFAULT_PROTOCOL = "t3s";
	  public static final String PROTOCOL_PROVIDER_PACKAGES = "weblogic.management.remote";
	  //This how we get our DomainRuntimeService, this is where DomainConfigurationMBeans exists
	  public static final String DOMAIN_MBEAN_NAME = "com.bea:Name=DomainRuntimeService,Type=weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean";
	  private MBeanServerConnection connection;
	  private ObjectName defaultAuthenticator;
	  private ObjectName[] authenticationProviders;
	  private static String authenticatorName="DefaultAuthenticator";
	  
	  private static final Logger log = Logger
				.getLogger(WLSJmxInterface.class);
	  
	  //@PostConstruct
	  public void postConstruct(WLSConfig wlsConfig) {
	          try {
	        	connection = null;  
	        	defaultAuthenticator = null;
//	            String host = wlsConfig.getSERVER_IP();
	        	String host = wlsConfig.getSERVER_IP();
	            String port = wlsConfig.getSERVER_PORT();
	            String username = wlsConfig.getSERVER_USERNAME();
	            String password = wlsConfig.getSERVER_PASSWORD();
	            String DEFAULT_PROTOCOL = wlsConfig.getPROTOCOL();
	              //Decryption dec=null;
	              /*MTSPermission mtsPer = MTSPermission.getInstance();
	              if(mtsPer.getServerIp()==null)
	                  mtsPer = new MTSPermission();
	              host = mtsPer.getServerIp();
	              port = mtsPer.getServerPort();
	               dec=new Decryption();
	              username = mtsPer.getServerUsername();
	              password =  "dec.decrypt(mtsPer.getServerPassword());*/
	/*            SecurityAppModule am =
	      (SecurityAppModule)Configuration.createRootApplicationModule("MTS.security.SecurityAppModule", "SecurityAppModuleLocal");
	              ViewObject webLogicVo = am.findViewObject("WebLogicConfView1");
	            Row[] r = webLogicVo.getAllRowsInRange();
	                if(r.length>0)
	                {
	                  host = r[0].getAttribute("ServerIp").toString();
	                  port = r[0].getAttribute("ServerPort").toString();
	                  username = r[0].getAttribute("ServerUsername").toString();
	                  password = r[0].getAttribute("ServerPassword").toString();
	                }
	            Configuration.releaseRootApplicationModule(am, true);0
	*/
	              Hashtable h = new Hashtable();
	              JMXServiceURL serviceURL;
	              
	              //System.out.println("WLS Protocol: " + DEFAULT_PROTOCOL);
	              
	              serviceURL =
	                      new JMXServiceURL(DEFAULT_PROTOCOL, host, Integer.valueOf(port).intValue(),
	                                        "/jndi/weblogic.management.mbeanservers.domainruntime");

	              h.put("java.naming.security.principal", username);
	              h.put("java.naming.security.credentials", password);
	              h.put("jmx.remote.protocol.provider.pkgs",
	                    "weblogic.management.remote");

	              //Creating a JMXConnector to connect to JMX
	              JMXConnector connector =
	                  JMXConnectorFactory.connect(serviceURL, h);

	              connection = connector.getMBeanServerConnection();

	              /****
	                We Get Objects by creating ObjectName with it's Qualified name.
	                The constructor take a String of the full Qualified name of the MBean
	                We then use connection to get Attribute out of this ObjectName but specifying a String of
	                this Attribute
	                *****/
	              

	              ObjectName configurationMBeans=
	                  new ObjectName(DOMAIN_MBEAN_NAME);
	              ObjectName domain =
	                  (ObjectName)connection.getAttribute(configurationMBeans, "DomainConfiguration");

	              ObjectName security =
	                  (ObjectName)connection.getAttribute(domain, "SecurityConfiguration");

	              ObjectName realm =
	                  (ObjectName)connection.getAttribute(security, "DefaultRealm");

	              authenticationProviders =
	                      (ObjectName[])connection.getAttribute(realm,
	                                                            "AuthenticationProviders");

	              for (int i = 0; i < authenticationProviders.length; i++) {
	                  String name =
	                      (String)connection.getAttribute(authenticationProviders[i],
	                                                      "Name");

	                  if (name.equals(authenticatorName))
	                      defaultAuthenticator = authenticationProviders[i];
	              }
	          } catch (Exception e) {
	              e.printStackTrace();
	            //  throw new RuntimeException(e);
	          }
	      }
	      
	    public boolean changeUserPassword(String username, String oldPassword,String newPassword) {
	    	//System.out.println("ana hena ahooooo gwa el method");
	        try {
	            if (!username.equalsIgnoreCase("weblogic")) {
	                connection.invoke(defaultAuthenticator, "changeUserPassword",
	                                  new Object[] { username, oldPassword, newPassword },
	                                  new String[] { "java.lang.String","java.lang.String","java.lang.String" });
	           
	               // System.out.println("ana hena ahooooo gwa el if");
	            }
	            
	           // System.out.println("ana hena ahooooo ha return true");
	            return true;
	        } catch (Exception e) {
	            //addMessage(e.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
//	            if(e.getMessage() != null && e.getMessage().contains("Validation of old password failed"))
//	                addMessage(StringManager.getString("MTS.security.ModelBundle", "OLD_PASSWORD_WRONG", ""), FacesMessage.SEVERITY_ERROR);
//	            else
//	            addMessage(StringManager.getString("MTS.security.ModelBundle", "OPERATION_FAILED", ""), FacesMessage.SEVERITY_ERROR);
	        	log.error("Invalid username or password" + (new Date()).toString());
	        	e.printStackTrace();
	        }
	        return false;
	    }
	
}
