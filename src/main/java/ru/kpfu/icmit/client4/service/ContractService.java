package ru.kpfu.icmit.client4.service;

import org.springframework.stereotype.Service;
import ru.kpfu.icmit.client4.model.Contract;
import ru.kpfu.icmit.client4.repository.ContractRepository;

@Service
public class ContractService extends CrudServiceImpl<Contract, ContractRepository> {

    public ContractService(ContractRepository genericRepository) {
        super(genericRepository);
    }
}
