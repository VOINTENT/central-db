package ru.kpfu.icmit.client4.service;

import org.springframework.stereotype.Service;
import ru.kpfu.icmit.client4.model.Metric;
import ru.kpfu.icmit.client4.repository.MetricRepository;

@Service
public class MetricService extends CrudServiceImpl<Metric, MetricRepository> {

    public MetricService(MetricRepository genericRepository) {
        super(genericRepository);
    }
}
