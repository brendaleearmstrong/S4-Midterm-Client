package com.misight.client.service;

import com.misight.client.config.RESTClient;
import com.misight.client.exception.ResourceNotFoundException;
import com.misight.client.exception.ServiceException;
import org.springframework.core.ParameterizedTypeReference;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {
    protected final RESTClient restClient;
    protected final String baseEndpoint;

    protected BaseService(RESTClient restClient, String baseEndpoint) {
        this.restClient = restClient;
        this.baseEndpoint = baseEndpoint;
    }

    protected List<T> getAll(ParameterizedTypeReference<List<T>> responseType) {
        try {
            return restClient.getList(baseEndpoint, responseType);
        } catch (Exception e) {
            throw new ServiceException("Error fetching all records: " + e.getMessage(), e);
        }
    }

    protected Optional<T> getById(Long id, Class<T> responseType) {
        try {
            T result = restClient.get(baseEndpoint + "/" + id, responseType);
            return Optional.ofNullable(result);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Record not found with id: " + id);
        }
    }

    protected T create(T entity, Class<T> responseType) {
        try {
            return restClient.post(baseEndpoint, entity, responseType);
        } catch (Exception e) {
            throw new ServiceException("Error creating record: " + e.getMessage(), e);
        }
    }

    protected T update(Long id, T entity, Class<T> responseType) {
        try {
            return restClient.put(baseEndpoint + "/" + id, entity, responseType);
        } catch (Exception e) {
            throw new ServiceException("Error updating record: " + e.getMessage(), e);
        }
    }

    protected void delete(Long id) {
        try {
            restClient.delete(baseEndpoint + "/" + id);
        } catch (Exception e) {
            throw new ServiceException("Error deleting record: " + e.getMessage(), e);
        }
    }
}