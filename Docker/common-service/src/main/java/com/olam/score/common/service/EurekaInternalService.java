package com.olam.score.common.service;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

/**
 *
 * @author munna.chauhan
 *
 */
@Service
public class EurekaInternalService {

	@Autowired
	private LoadBalancerClient loadBalancer;

    private String getMicroServiceUri(String serviceName, String resourceName) {
    	
        ServiceInstance instance = Optional.of(loadBalancer.choose(serviceName)).get();
        URI serviceUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));
        return serviceUri.toString() + resourceName;
    }
	
	  /**
	   * Returns the data from Internal service.
	   */
	public String callInternalService(String serviceName, String resourceName) {
		return getMicroServiceUri(serviceName, resourceName);
		
	}
}
