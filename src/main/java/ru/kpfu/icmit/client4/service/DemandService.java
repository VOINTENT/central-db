package ru.kpfu.icmit.client4.service;

import org.springframework.stereotype.Service;
import ru.kpfu.icmit.client4.model.Demand;
import ru.kpfu.icmit.client4.repository.DemandRepository;

@Service
public class DemandService extends CrudServiceImpl<Demand, DemandRepository> {

    public DemandService(DemandRepository genericRepository) {
        super(genericRepository);
    }
}
