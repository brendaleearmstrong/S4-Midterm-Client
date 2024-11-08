package com.misight.client.service.setup;

import com.misight.client.model.Mines;
import com.misight.client.model.Minerals;
import com.misight.client.config.RESTClient;
import com.misight.client.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.core.ParameterizedTypeReference;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MinesService extends BaseService<Mines> {

    public MinesService(RESTClient restClient) {
        super(restClient, "/api/mines");
    }

    public List<Mines> getAllMines() {
        return getAll(new ParameterizedTypeReference<List<Mines>>() {});
    }

    public Optional<Mines> getMineById(Long id) {
        return getById(id, Mines.class);
    }

    public Mines createMine(Mines mine) {
        return create(mine, Mines.class);
    }

    public Mines updateMine(Long id, Mines mine) {
        return update(id, mine, Mines.class);
    }

    public void deleteMine(Long id) {
        delete(id);
    }

    public Set<Minerals> getMineMinerals(Long mineId) {
        return restClient.get("/api/mines/" + mineId + "/minerals", Set.class);
    }

    public Mines updateMineMinerals(Long mineId, Set<Minerals> minerals) {
        return restClient.put("/api/mines/" + mineId + "/minerals", minerals, Mines.class);
    }
}