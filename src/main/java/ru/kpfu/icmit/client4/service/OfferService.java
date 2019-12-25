package ru.kpfu.icmit.client4.service;

import org.springframework.stereotype.Service;
import ru.kpfu.icmit.client4.model.Offer;
import ru.kpfu.icmit.client4.repository.OfferRepository;

@Service
public class OfferService extends CrudServiceImpl<Offer, OfferRepository> {

    public OfferService(OfferRepository genericRepository) {
        super(genericRepository);
    }
}
