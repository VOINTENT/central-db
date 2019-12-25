package ru.kpfu.icmit.client4.service;

import org.springframework.stereotype.Service;
import ru.kpfu.icmit.client4.model.Organization;
import ru.kpfu.icmit.client4.repository.OrganizationRepository;

@Service
public class OrganizationService extends CrudServiceImpl<Organization, OrganizationRepository> {

    public OrganizationService(OrganizationRepository genericRepository) {
        super(genericRepository);
    }
}
