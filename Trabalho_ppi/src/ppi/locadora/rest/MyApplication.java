package ppi.locadora.rest;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.jettison.JettisonFeature;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyApplication extends Application {
	
	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();
		
		// Driver do Jettison para gerar JSON. 
		singletons.add(new JettisonFeature());
		return singletons;
	}

	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();
		
		// Configura o pacote para fazer scan das classes com anota��es REST.
		properties.put("jersey.config.server.provider.packages", "ppi.locadora.rest");
		return properties;
	}
}
